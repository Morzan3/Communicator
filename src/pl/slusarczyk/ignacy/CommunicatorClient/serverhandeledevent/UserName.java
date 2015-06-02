package pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent;

import java.io.Serializable;

import pl.slusarczyk.ignacy.CommunicatorServer.model.UserId;



/**
 * Klasa opisująca zdarzenie nawiązania nowego połączenia od klienta, z któreg wątek pobiera userId aby na jego podstawie
 * móc znaleźć output stream danego użytkownika w mapie <userId, ObjectOutputStream>
 * 
 * @author Ignacy Ślusarczyk
 */
public class UserName extends ServerHandeledEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	/** Nazwa użytkownika*/
	private final UserId userID;
	/**Nazwa pokoju*/
	private final String roomName;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie zadanych parametrów 
	 * 
	 * @param userID
	 * @param roomName
	 */
	public UserName(final UserId userId,final String roomName)
	{
		this.userID = userId;
		this.roomName = roomName;
	}
	
	/**
	 * Metoda zwracająca ID użytkownika
	 * 
	 * @return ID użytkownika
	 */
	public UserId getUserID()
	{
		return userID;
	}

	/**
	 * Metoda zwracająca nazwę pokoju
	 * 
	 * @return nazwa pokoju 
	 */
	public String getRoomName()
	{
		return roomName;
	}
}
