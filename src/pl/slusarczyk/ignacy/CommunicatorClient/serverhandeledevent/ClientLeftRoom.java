package pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent;

import java.io.Serializable;

import pl.slusarczyk.ignacy.CommunicatorServer.model.UserId;

/**
 * Klasa opisująca zdarzenie naciśnięcia przez użytkownika przycisku wyjścia z chatu w oknie rozmowy.
 * 
 * @author Ignacy Ślusarczyk
 */
public class ClientLeftRoom extends ServerHandeledEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Nazwa użytkownika, który wyszedł z chatu**/
	private final UserId userId;
	/**Nazwa pokoju, w którym użytkownik się znajdował**/
	private final String roomName;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie podanych parametrów
	 * 
	 * @param userName nazwa użytkownika
	 * @param roomName nazwa pokoju
	 */
	public ClientLeftRoom(final UserId userID,final String roomName)
	{
		this.userId=userID;
		this.roomName=roomName;
	}
	
	/**
	 * Metoda zwracająca nazwę użytkownika
	 *
	 * @return nazwa użytkownika
	 */
	public UserId getUserID() 
	{
		return userId;
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
