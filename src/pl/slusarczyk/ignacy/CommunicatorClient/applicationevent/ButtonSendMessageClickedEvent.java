package pl.slusarczyk.ignacy.CommunicatorClient.applicationevent;

import java.io.Serializable;

/**
 * Klasa opisująca zdarzenie naciśnięcia przez użytkownika przycisku wysłania wiadomości
 * 
 * @author Ignacy Śłusarczyk
 */
public class ButtonSendMessageClickedEvent extends ApplicationEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Nazwa pokoju**/
	private String roomName;
	/**Nazwa użytkownika**/
	private String userName;
	/**Wiadomość, którą użytkownik chce wysłać**/
	private String message;
	
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie podancych parametrów
	 * 
	 * @param roomName nazwa pokoju
	 * @param userName nazwa użytkownika
	 * @param message wiadomość
	 */
	public ButtonSendMessageClickedEvent (String roomName, String userName, String message)
	{
		this.roomName = roomName;
		this.userName = userName;
		this.message = message;
	}
	
	/**
	 * Metoda zwracająca nazwę pokoju, w którym dany użytkownik się znajduje 
	 * 
	 * @return nazwa pkokju
	 */
	public String getRoomName()
	{
		return this.roomName;
	}
	
	/**
	 * Metoda zwracająca nazwę użytkownika, który wysłał wiadomość
	 * 
	 * @return nazwa użytkownika
	 */
	public String getUserName ()
	{
		return this.userName;
	}
	
	/**
	 * Metoda zwracająca wiadomość, którą użytkownik wysłał
	 * 
	 * @return wiadomość
	 */
	public String getMessage ()
	{
		return this.message;
	}
	
	
	
}
