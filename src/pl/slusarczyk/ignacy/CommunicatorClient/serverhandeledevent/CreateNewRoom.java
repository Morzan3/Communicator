package pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent;

import java.io.Serializable;

import pl.slusarczyk.ignacy.CommunicatorServer.model.UserId;

/**
 * Klasa reprezentująca zdarzenie naciśnięcia przez użytkownika przycisku utworzenia nowego pokoju
 * 
 * @author Ignacy Ślusarczyk
 */
public class CreateNewRoom extends ServerHandeledEvent implements Serializable
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
	 * @param userID ID użytkownika
	 */
	public CreateNewRoom(final String roomName,final UserId userID)
	{
		this.roomName = roomName;
		this.userID = userID;
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


