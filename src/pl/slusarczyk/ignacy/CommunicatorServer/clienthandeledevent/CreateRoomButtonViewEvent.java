package pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent;

/**
 * Klasa reprezentująca naciśnięcie przez użytkownika przycisku tworzenia nowego pokoju, po podaniu odpowiednich informacji.
 * 
 * @author Ignacy Ślusarczyk
 */
public class CreateRoomButtonViewEvent extends ClientHandeledEvent
{
	/**Nazwa użytkownika, który tworzy pokój, zamieniana potem na userId**/
	private  final String userName;
	/**Nazwa hosta z którym się łączy**/
	private final String hostName;
	/**Port na którym się łączy**/
	private final int port;
	/**Nazwa pokoju na którym chce prowadzić rozmowę**/
	private final String roomName;
	
	public CreateRoomButtonViewEvent(final String hostName,final int port,final String userName,final String roomName)
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
