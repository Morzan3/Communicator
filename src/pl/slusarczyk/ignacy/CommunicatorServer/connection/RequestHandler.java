package pl.slusarczyk.ignacy.CommunicatorServer.connection;
import pl.slusarczyk.ignacy.CommunicatorServer.applicationevent.*;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorServer.applicationevent.ApplicationEvent;

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
			String newUserName; 
			
			
			newUserName = (String) inputStream.readObject();
			userOutputStreams.put(newUserName, outputStream);
			//Dodaje nowego użytkownika do mojej mapy. 
		}
		catch (IOException ex)
		{
			System.err.println("Nastapił błąd podczas tworzenia strumienia " + ex);
			return;
		}
		catch (ClassNotFoundException ex2)
		{
			System.err.println("Błąd rzutowania przychodzącej informacji" + ex2);
		}
	
	}
	
		public void run()
		{
			try
			{
			ApplicationEvent appEvent;
			appEvent = (ApplicationEvent) inputStream.readObject();
			
			eventQueue.add(appEvent);
			}
			catch (IOException ex)
			{
				System.err.println(ex);
			}
			catch (ClassNotFoundException ex)
			{
				System.err.println("Błąd rzutowania przychodzącej informacji" + ex);
			}
		}
		
}
