package pl.slusarczyk.ignacy.CommunicatorServer.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorClient.applicationevent.*;

/**
 * Klasa odpowiedzialna za nasłuchiwanie przez serwer żądań nowych połączeń od klientów
 * 
 * @author Ignacy Ślusarczyk
 */
public class ConnectionHandler extends Thread
{
	/**Socket servera**/
	ServerSocket serverSocket;
	/**Kolejka blokująca zdarzeń**/
	BlockingQueue<ApplicationEvent> eventQueue;
	/**Mapa użytkowników i ich output streamów**/
	HashMap <String,ObjectOutputStream> userOutputStreams;
	
	/**
	 * Konstruktor tworzący wątek nasłuchujący nowych połączeń
	 * @param serverSocket socket serwera
	 * @param eventQueue kolejka zdarzeń
	 * @param userOutputStreams mapa strumieni wyjściowych użytkowników
	 */
	public ConnectionHandler(ServerSocket serverSocket,BlockingQueue<ApplicationEvent> eventQueue,HashMap <String,ObjectOutputStream> userOutputStreams)
	{
		this.serverSocket = serverSocket;
		this.eventQueue = eventQueue;
		this.userOutputStreams = userOutputStreams;
	}
	
	/**
	 * Główna pętla klasy, w której nasłuchuje nowych połaćzeń od klientów
	 */
	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println("Serwer zaczął nasłuchiwanie nowych połączeń na porcie" + serverSocket.getLocalPort());
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
