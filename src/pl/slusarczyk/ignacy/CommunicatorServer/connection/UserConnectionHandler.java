package pl.slusarczyk.ignacy.CommunicatorServer.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent.*;
import pl.slusarczyk.ignacy.CommunicatorServer.model.UserId;

/** Klasa odpowiedzialna za odbieranie zdarzeń od klienta i dodawanie ich do blocking queue 
 * 
 * @author Ignacy Ślusatczyk
 */
public class UserConnectionHandler extends Thread
{
	/**Socket klienta**/
	private final Socket userSocket;
	/**Strumień wejściowy**/
	private ObjectInputStream inputStream;
	/**Strumień wyjściowy**/
	private ObjectOutputStream outputStream;
	/**Mapa ID userów oraz ich strumieni wyjściowych**/
	 private final HashMap<UserId,ObjectOutputStream> userOutputStreams;
	/**Kolejka blokująca zdarzeń**/
	 private final BlockingQueue<ServerHandeledEvent> eventQueue;
	
	/**
	 * Konstruktor tworzący nowy wątek nasłuchujący połączeń od danego użytkownika
	 * 
	 * @param userSocket socket użytkownika
	 * @param eventQueue kolejka zdarzeń
	 * @param userOutputStreams mapa strumieni wyjściowych
	 */
	public UserConnectionHandler (final Socket userSocket,final BlockingQueue<ServerHandeledEvent> eventQueue,final HashMap <UserId,ObjectOutputStream> userOutputStreams)
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
			ServerHandeledEvent appEvent;
			while(true)
			{	
				try
				{
					appEvent = (ServerHandeledEvent) inputStream.readObject();
					
					if (appEvent instanceof UserName) 
					{
						UserName userNameInformation = (UserName) appEvent;
						userOutputStreams.put(userNameInformation.getUserID(),outputStream);
					}
					else if(appEvent instanceof ClientLeftRoom)
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
					System.err.println(ex);
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
