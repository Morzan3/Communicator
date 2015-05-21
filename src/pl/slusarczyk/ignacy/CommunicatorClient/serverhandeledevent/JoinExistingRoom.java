package pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent;

import java.io.Serializable;

import pl.slusarczyk.ignacy.CommunicatorServer.model.UserId;

/**
 * Klasa opisująca zdarzenie naciśnięcia przez użytkownika przycisku dołączenia do pokoju
 * 
 * @author Ignacy Ślusarczyk
 *
 */
public class JoinExistingRoom extends ServerHandeledEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Nazwa pokoju**/
	private final String roomName;
	/**Nazwa użytkownika**/
	private final UserId userID;

	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie zadanych parametrów
	 * 
	 * @param roomName nazwa pokoju
	 * @param userId ID użytkownika 
	 */
	public JoinExistingRoom (final String roomName,final UserId userId)
	{
		this.roomName = roomName;
		this.userID = userId;
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

	/**
	 * Metoda zwracająca ID użytkownika
	 * 
	 * @return nazwa użytkownika
	 */
	public UserId getUserId()
	{
		return userID;
	}
}


