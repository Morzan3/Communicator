package pl.slusarczyk.ignacy.CommunicatorServer.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import pl.slusarczyk.ignacy.CommunicatorClient.applicationevent.ApplicationEvent;
import pl.slusarczyk.ignacy.CommunicatorServer.model.User;
import pl.slusarczyk.ignacy.CommunicatorServer.serverevent.ConversationInformation;

/**
 * Klasa główna serwera przechowująca informacje o strumieniach wyjściowych użytkowników i rozsyłająca wiadomości
 * 
 * @author Ignacy Ślusarczyk
 */
public class Server 
{
	/** Mapa przetrzymująca strumienie wyjściowe użytkowników, w której ich nazwa jest kluczem **/
	public HashMap <String, ObjectOutputStream> userOutputStreams;
	/**Socket servera**/
	private ServerSocket serverSocket;
	/**Port na którym serwer nasłuchuje**/
	private final int portNumber;
	/**Kolejka bloująca zdarzeń**/
	private BlockingQueue<ApplicationEvent> eventQueue; 
	
	/**
	 * Konstruktor włączający serwer na podanym porcie, który tworzy oddzielny wątek do nasłuchiwania nowych połączeń
	 * 
	 * @param portNumber numer portu
	 * @param eventQueue kolejka blokująca zdarzeń
	 */
	public Server (int portNumber, BlockingQueue<ApplicationEvent> eventQueue)
	{
		this.eventQueue = eventQueue;
		this.portNumber = 5000;
		this.userOutputStreams = new HashMap <String, ObjectOutputStream>();
		
		runServer();
		ConnectionHandler connectionHandler = new ConnectionHandler(this.serverSocket, this.eventQueue, this.userOutputStreams);
		connectionHandler.start();
	}
	
	
	/**
	 * Metoda tworząca server socket na zadanym porcie
	 */
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
	
	/**
	 * Metoda do bezpiecznego zakończenia połączenia
	 */
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
		
	
	/**
	 * Metoda wysyłająca bezpośrednio wiadomość do użytkownika o zadanym nicku
	 * 
	 * @param userName nazwa użytkownika
	 * @param usersConversation rozmowa pomiędzy użytkownikami
	 * @param listOfUsers lista użytkowników aktualnie będących na chacie
	 */
	public void sendDirectMessage (String userName, String usersConversation,String listOfUsers )
	{
		try
		{
		ConversationInformation userConversationToSend = new ConversationInformation (usersConversation,listOfUsers);
		ObjectOutputStream userOutputStream;
		userOutputStream = userOutputStreams.get(userName);
		userOutputStream.writeObject(userConversationToSend);
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
	
	/**
	 * Metoda wysyłająca rozmowę do wszystkich użytkowników z podanej listy
	 *
	 * @param userConversation konwersacja pomiędzy użytkownikami
	 * @param userListToDisplay lista użytkowników do wyświetlenia
	 * @param usersToSendTo lista użytkowników, do której chcemy wysłać wiaomość
	 */
	public void sendMessageToAll (String userConversation,String userListToDisplay, HashSet<User> usersToSendTo)
	{
		for (User user : usersToSendTo)
		{
			sendDirectMessage(user.getUserName(), userConversation, userListToDisplay);
		}
	}
}
