package pl.slusarczyk.ignacy.CommunicatorServer.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorServer.applicationevent.ApplicationEvent;

public class ConnectionHandler extends Thread
{
	ServerSocket serverSocket;
	BlockingQueue<ApplicationEvent> eventQueue;
	HashMap <String,ObjectOutputStream> userOutputStreams;
	
	
	public ConnectionHandler(ServerSocket serverSocket,BlockingQueue<ApplicationEvent> eventQueue,HashMap <String,ObjectOutputStream> userOutputStreams)
	{
		this.serverSocket = serverSocket;
		this.eventQueue = eventQueue;
		this.userOutputStreams = userOutputStreams;
		
		
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println("Conncetion Handler właczony");
				Socket userSocket = serverSocket.accept();
				RequestHandler newConnection = new RequestHandler (userSocket, eventQueue, userOutputStreams);
			
				newConnection.start();	
				System.out.println("Zaakceptowano nowe polaczenie");
			}
			catch (IOException ex)
			{
				System.err.println(ex);
			}
		}
	}
	
}
