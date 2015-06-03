package pl.slusarczyk.ignacy.CommunicatorServer.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent.*;
import pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent.UserAlreadyExistsServerEvent;
import pl.slusarczyk.ignacy.CommunicatorServer.model.UserId;

/** Klasa odpowiedzialna za odbieranie zdarzeń od klienta i dodawanie ich do blocking queue 
 * 
 * @author Ignacy Ślusatczyk
 */
public class UserConnectionHandler extends Thread
{
	/**Socket klienta*/
	private final Socket userSocket;
	/**Strumień wejściowy*/
	private ObjectInputStream inputStream;
	/**Strumień wyjściowy*/
	private ObjectOutputStream outputStream;
	/**Mapa ID userów oraz ich strumieni wyjściowych*/
	 private final HashMap<UserId,ObjectOutputStream> userOutputStreams;
	/**Kolejka blokująca zdarzeń*/
	 private final BlockingQueue<ServerHandeledEvent> eventQueue;
	 /**Flaga określająca czy wątek pracuje*/
	 private boolean running;
	
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
		this.running = true;
		
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
			outer:
			while(running)
			{	
				try
				{
					appEvent = (ServerHandeledEvent) inputStream.readObject();
					
					if (appEvent instanceof UserName) 
					{
						UserName userNameInformation = (UserName) appEvent;
						
						Iterator it = userOutputStreams.entrySet().iterator();
						while (it.hasNext())
						{
							System.out.println("Sprawdzam");
							Map.Entry pair = (Map.Entry)it.next();
							UserId userID = (UserId)pair.getKey();
							if (userNameInformation.getUserID().equals(userID)==true)
							{
								outputStream.writeObject(new UserAlreadyExistsServerEvent());
								appEvent = (ServerHandeledEvent) inputStream.readObject();
								continue outer;
							}
						}
						
							System.out.println("Nie istnieje jeszcze");
							userOutputStreams.put(userNameInformation.getUserID(),outputStream);
							System.out.println(outputStream);
					
					}
					else if(appEvent instanceof ClientLeftRoom)
					{
						eventQueue.add(appEvent);
						userSocket.close();
						running = false;
						break;
					}
					else 
					{
						eventQueue.add(appEvent);
					}
				}
				catch (IOException ex)
				{
					//System.exit(0);
				}
				catch (ClassNotFoundException ex)
				{
					System.err.println("Błąd rzutowania przychodzącej informacji" + ex);
				}
				catch (NullPointerException ex3)
				{
					System.err.println("Błąd odbierania obiektu");
				}
				catch(ClassCastException ex4)
				{
					System.err.println("Tutaj" + ex4);
				}
			}
		}
}
