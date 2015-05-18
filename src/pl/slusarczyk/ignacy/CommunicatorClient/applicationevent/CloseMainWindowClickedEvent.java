package pl.slusarczyk.ignacy.CommunicatorClient.applicationevent;

import java.io.Serializable;

/**
 * Klasa opisująca zdarzenie naciśnięcia przez użytkownika przycisku wyjścia z chatu w oknie rozmowy
 * 
 * @author Ignacy Ślusarczyk
 */
public class CloseMainWindowClickedEvent extends ApplicationEvent implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	/**Nazwa użytkownika, który wyszedł z chatu**/
	String userName;
	/**Nazwa pokoju, w którym użytkownik się znajdował**/
	String roomName;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie podanych parametrów
	 * 
	 * @param userName nazwa użytkownika
	 * @param roomName nazwa pokoju
	 */
	public CloseMainWindowClickedEvent(String userName, String roomName)
	{
		this.userName=userName;
		this.roomName=roomName;
	}
	
	/**
	 * Metoda zwracająca nazwę użytkownika
	 *
	 * @return nazwa użytkownika
	 */
	public String getUserName() 
	{
		return userName;
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
