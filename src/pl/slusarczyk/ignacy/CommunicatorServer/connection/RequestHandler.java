package pl.slusarczyk.ignacy.CommunicatorServer.connection;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorClient.applicationevent.ApplicationEvent;
import pl.slusarczyk.ignacy.CommunicatorClient.applicationevent.ButtonCreateNewRoomClickedEvent;
import pl.slusarczyk.ignacy.CommunicatorClient.applicationevent.UserName;


public class RequestHandler extends Thread
{
	
	private Socket userSocket;
	private ObjectInputStream inputStream;
	
	private ObjectOutputStream outputStream;

	private HashMap<String,ObjectOutputStream> userOutputStreams;
	private BlockingQueue<ApplicationEvent> eventQueue;
	
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
			System.err.println("Nastapił błąd podczas tworzenia strumienia serwer " + ex);
			return;
		}
		
		
		
	
	}
	
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
						System.out.println("Dodano uzytkownika do listy output streamów");
					}
					
					System.out.println("Request handler obiekt" + appEvent);
					eventQueue.add(appEvent);
				}
				catch (IOException ex)
				{
					System.err.println("RequestHandler" + ex);
				}
				catch (ClassNotFoundException ex)
				{
					System.err.println(" Req Błąd rzutowania przychodzącej informacji" + ex);
				}
				catch (NullPointerException ex3)
				{
					System.err.println("Błąd odbierania obiektu");
				}
			}
		}
		
}
