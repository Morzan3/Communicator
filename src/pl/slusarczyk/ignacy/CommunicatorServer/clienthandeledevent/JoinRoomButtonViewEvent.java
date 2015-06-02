package pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent;

/**
 * Klasa reprezentująca naciśnięcie przez użytkownika przycisku dołączenia do istniejącego pokoju, po podaniu odpowiednich informacji.
 * 
 * @author Ignacy Ślusarczyk
 */
public class JoinRoomButtonViewEvent extends ClientHandeledEvent
{
	private final String userName;
	/**Nazwa hosta z którym jest połączony*/
	private final String hostName;
	/**Port na którym się łączy*/
	private final int port;
	/**Nazwa pokoju na którym prowadzi rozmowę*/
	private final String roomName;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie podanych informacji
	 * @param hostName nazwa hosta 
	 * @param port numer portu 
	 * @param userName nazwa użytkownika
	 * @param roomName nazwa pokoju
	 */
	public JoinRoomButtonViewEvent(final String hostName,final int port,final String userName,final String roomName)
	{
		this.userName = userName;
		this.hostName = hostName;
		this.port = port;
		this.roomName = roomName;
	}

	/**
	 * Metoda zwracająca nazwę użytkownika
	 * 
	 * @return the userName
	 */
	public String getUserName() 
	{
		return userName;
	}

	/**
	 * Metoda zwracająca nazwę hosta
	 * 
	 * @return the hostName
	 */
	public String getHostName() 
	{
		return hostName;
	}

	/**
	 * Metoda zwracająca numer portu
	 * 
	 * @return the port
	 */
	public int getPort() 
	{
		return port;
	}

	/**
	 * Metoda zwracająca nazwę pokoju
	 * 
	 * @return the roomName
	 */
	public String getRoomName() 
	{
		return roomName;
	}
	
	
}
