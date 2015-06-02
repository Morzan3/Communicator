package pl.slusarczyk.ignacy.CommunicatorServer.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent.ServerHandeledEvent;
import pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent.ConnectionEstablishedServerEvent;
import pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent.ConversationInformationServerEvent;
import pl.slusarczyk.ignacy.CommunicatorServer.model.*;
import pl.slusarczyk.ignacy.CommunicatorServer.model.data.RoomData;
import pl.slusarczyk.ignacy.CommunicatorServer.model.data.UserData;

/**
 * Klasa główna serwera przechowująca informacje o strumieniach wyjściowych użytkowników i rozsyłająca wiadomości
 * 
 * @author Ignacy Ślusarczyk
 */
public class MainConnectionHandler 
{
	/** Mapa przetrzymująca strumienie wyjściowe użytkowników, w której kluczem jest userId */
	private final HashMap <UserId, ObjectOutputStream> userOutputStreams;
	/**Socket servera*/
	private ServerSocket serverSocket;
	/**Port na którym serwer nasłuchuje*/
	private final int portNumber;
	/**Kolejka bloująca zdarzeń*/
	private final BlockingQueue<ServerHandeledEvent> eventQueue; 
	
	/**
	 * Konstruktor włączający serwer na podanym porcie, który tworzy oddzielny wątek do nasłuchiwania nowych połączeń
	 * 
	 * @param portNumber numer portu
	 * @param eventQueue kolejka blokująca zdarzeń
	 */
	public MainConnectionHandler (final int portNumber, final BlockingQueue<ServerHandeledEvent> eventQueue)
	{
		this.eventQueue = eventQueue;
		this.portNumber = portNumber;
		this.userOutputStreams = new HashMap <UserId, ObjectOutputStream>();
		
		createServerSocket();
		ServerSocketHandler serverSocketHandler = new ServerSocketHandler(serverSocket, this.eventQueue, userOutputStreams);
		serverSocketHandler.start();
	}
	
	/**
	 * Metoda tworząca server socket na zadanym porcie
	 */
	public void createServerSocket()
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
	
	/**
	 * Metoda do bezpiecznego zakończenia połączenia
	 */
	public void closeServer()
	{
		try 
		{
			this.serverSocket.close();
		}
		catch(IOException ex)
		{
			System.err.println("Nastąpił błąd podczas zamykania połączenia " + ex);
			System.exit(2);	
		}
	}
			
	/**
	 * Metoda wysyłająca bezpośrednio wiadomość do użytkownika o zadanym nicku
	 * 
	 * @param userName nazwa użytkownika
	 * @param usersConversation rozmowa pomiędzy użytkownikami
	 * @param listOfUsers lista użytkowników aktualnie będących na chacie
	 */
	public void sendDirectMessage (final UserId userID,final RoomData roomData)
	{
		try
		{
			ConversationInformationServerEvent userConversationToSend = new ConversationInformationServerEvent (roomData);
			ObjectOutputStream userOutputStream;
			userOutputStream = userOutputStreams.get(userID);
			userOutputStream.writeObject(userConversationToSend);
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
	
	/**
	 * Metoda wysyłająca rozmowę do wszystkich użytkowników znajdujących się w tym samym pokoju
	 * 
	 * @param roomData Opakowany obiekt pokój, w którym znajduje się konwersacja do wyśweitlenia oraz lista użytkowników
	 */
	public void sendMessageToAll (final RoomData roomData)
	{
		for (UserData userData: roomData.getUserSet())
		{
			if(userData.isUserActive()==true)
			{
			sendDirectMessage(userData.getUserId(), roomData);
			}
		}
	}
	
	/**
	 * Metoda wysyłająca do klienta potwierdzenie nawiązania połączeni
	 * @param userId
	 * @param connectionEstablished
	 */
	public void connectionEstablished(final UserId userId,final boolean connectionEstablished)
	{
		try 
		{
			userOutputStreams.get(userId).writeObject(new ConnectionEstablishedServerEvent(connectionEstablished));
		} 
		catch (IOException e) 
		{
			System.err.println(e);
		}
	}	
}
