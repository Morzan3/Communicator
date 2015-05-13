package pl.slusarczyk.ignacy.CommunicatorServer.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorServer.applicationevent.*;
import pl.slusarczyk.ignacy.CommunicatorServer.model.*;

public class Server 
{
	private HashMap <String, ObjectOutputStream> userOutputStreams; //hashmapa potrzeba przy przesyłaniuwiadomości do wszystkich, 
	private HashMap <String, RequestHandler> userRequestHandlerMap;  //jeszcze nie jestem pewien czy to konieczne 
	private ServerSocket serverSocket;
	private final int portNumber;
	private BlockingQueue<ApplicationEvent> eventQueue;
	Calendar calendar; 
	
	public Server (int portNumber, BlockingQueue<ApplicationEvent> eventQueue)
	{
		this.eventQueue = eventQueue;
		this.portNumber = portNumber;
		this.userOutputStreams = new HashMap <String, ObjectOutputStream>();
		this.calendar = Calendar.getInstance();
		
		
		runServer();
		
		ConnectionHandler connectionHandler = new ConnectionHandler(this.serverSocket, this.eventQueue, this.userOutputStreams);
		connectionHandler.start();
		
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
	
	
	
	public void sendDirectMessage (String userName, HashSet<Message> usersConversation )
	{
		try
		{
		UserConversation userConversationToSend = new UserConversation (usersConversation);
		ObjectOutputStream userOutputStream;
		userOutputStream = userOutputStreams.get(userName);
		userOutputStream.writeObject(userConversationToSend);
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
	
	public void sendMessageToAll (HashSet<Message> userConversation, HashSet<User> usersToSendTo)
	{
		for (User user : usersToSendTo)
		{
			sendDirectMessage(user.getUserName(), userConversation);
		}
	}
	
	
	
}
