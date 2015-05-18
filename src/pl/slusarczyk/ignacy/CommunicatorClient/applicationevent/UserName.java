package pl.slusarczyk.ignacy.CommunicatorClient.applicationevent;

import java.io.Serializable;

/**
 * Klasa opisująca zdarzenie nawiązania nowego połączenia od klienta 
 * 
 * @author Ignacy Ślusarczyk
 */
public class UserName extends ApplicationEvent implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	/** Nazwa użytkownika**/
	private String firstUserName;
	/**Nazwa pokoju**/
	private String roomName;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie zadanych parametrów 
	 * 
	 * @param firstUserName
	 * @param roomName
	 */
	public UserName( String firstUserName, String roomName)
	{
		this.firstUserName = firstUserName;
		this.roomName = roomName;
	}
	
	/**
	 * Metoda zwracająca nazwę użytkownika
	 * 
	 * @return nazwa użytkownika
	 */
	public String getUserName()
	{
		return this.firstUserName;
	}

	/**
	 * Metoda zwracająca nazwę pokoju
	 * 
	 * @return nazwa pokoju 
	 */
	public String getRoomName()
	{
		return this.roomName;
	}
}
