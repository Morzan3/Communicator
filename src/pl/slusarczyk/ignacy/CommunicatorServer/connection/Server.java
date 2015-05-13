package pl.slusarczyk.ignacy.CommunicatorServer.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorServer.applicationevent.ApplicationEvent;

public class Server 
{
	private HashMap <String, ObjectOutputStream> userOutputStreams; //hashmapa potrzeba przy przesyłaniuwiadomości do wszystkich, 
	private HashMap <String, RequestHandler> requestHandler;  //jeszcze nie jestem pewien czy to konieczne 
	private ServerSocket serverSocket;
	private final int portNumber;
	private BlockingQueue<ApplicationEvent> eventQueue;
	
	
	public Server (int portNumber, BlockingQueue<ApplicationEvent> eventQueue)
	{
		this.eventQueue = eventQueue;
		this.portNumber = portNumber;
		this.userOutputStreams = new HashMap <String, ObjectOutputStream>();
		
		runServer();
		listen();
		
	}
	
	
	public void runServer()
	{
		try
		{
			this.serverSocket = new ServerSocket(portNumber);
		}
		catch(IOException ex)
		{
			System.err.println("Nastąpił błąd podczas tworzenia połączenia " + ex);
			System.exit(1);	
		}
	}
	
	public void closeServer()
	{
		try 
		{
			this.serverSocket.close();
		}
		catch(IOException ex) {
			System.err.println("Nastąpił błąd podczas zamykania połączenia " + ex);
			System.exit(2);	
		}
	}
	
	
	public void listen()
	{
		try
		{
			Socket userSocket = serverSocket.accept();
			RequestHandler newConnection = new RequestHandler (userSocket, eventQueue, userOutputStreams);
			
			newConnection.start();
		}
		catch (IOException ex)
		{
			System.err.println(ex);
		}
	}
	
	
	
	
}
