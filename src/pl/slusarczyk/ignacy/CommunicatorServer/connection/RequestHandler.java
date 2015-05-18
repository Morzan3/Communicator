package pl.slusarczyk.ignacy.CommunicatorServer.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import pl.slusarczyk.ignacy.CommunicatorClient.applicationevent.*;

/** Klasa odpowiedzialna za odbieranie zdarzeń od klienta i dodawanie ich do blocking queue 
 * 
 * @author Ignacy Ślusatczyk
 */
public class RequestHandler extends Thread
{
	/**Socket klienta**/
	private Socket userSocket;
	/**Strumień wejściowy**/
	private ObjectInputStream inputStream;
	/**Strumień wyjściowy**/
	private ObjectOutputStream outputStream;
	/**Mapa nazw użytkowników oraz ich strumieni wyjściowych**/
	private HashMap<String,ObjectOutputStream> userOutputStreams;
	/**Kolejka blokująca zdarzeń**/
	private BlockingQueue<ApplicationEvent> eventQueue;
	
	/**
	 * Konstruktor tworzący nowy wątek nasłuchujący połączeń od danego użytkownika
	 * 
	 * @param userSocket socket użytkownika
	 * @param eventQueue kolejka zdarzeń
	 * @param userOutputStreams mapa strumieni wyjściowych
	 */
	public RequestHandler (Socket userSocket, BlockingQueue<ApplicationEvent> eventQueue,HashMap <String,ObjectOutputStream> userOutputStreams)
	{
		this.userSocket = userSocket;
		this.eventQueue = eventQueue;
		this.userOutputStreams = userOutputStreams;
		
		try
		{
			outputStream = new ObjectOutputStream(userSocket.getOutputStream());
			inputStream = new ObjectInputStream(userSocket.getInputStream());
		}
		catch (IOException ex)
		{
			System.err.println("Nastapił błąd podczas tworzenia strumienia z klientem" + ex);
			return;
		}
	}
	
	/**
	 * Główna pętla klasy, w której nasłuchuje zdarzeń od klienta i dodaje je do kolejki blokującej
	 */
		public void run()
		{
			ApplicationEvent appEvent;
			System.out.println("Serwer oczekuje na eventy od klienta.");
			while(true)
			{	
				try
				{
					
					appEvent = (ApplicationEvent) inputStream.readObject();
					
					if (appEvent instanceof UserName) 
					{
						String nazwa = ((UserName) appEvent).getUserName();
						userOutputStreams.put(nazwa,outputStream);
					}
					else if(appEvent instanceof CloseMainWindowClickedEvent)
					{
						this.userSocket.close();
						break;
					}
					else 
					{
						eventQueue.add(appEvent);
					}
					
				}
				catch (IOException ex)
				{
					System.err.println("RequestHandler" + ex);
				}
				catch (ClassNotFoundException ex)
				{
					System.err.println("Błąd rzutowania przychodzącej informacji" + ex);
				}
				catch (NullPointerException ex3)
				{
					System.err.println("Błąd odbierania obiektu");
				}
			}
		}
}
