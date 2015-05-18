package pl.slusarczyk.ignacy.CommunicatorClient.applicationevent;

import java.io.Serializable;

/**
 * Klasa reprezentująca zdarzenie naciśnięcia przez użytkownika przycisku utworzenia nowego pokoju
 * 
 * @author Ignacy Ślusarczyk
 */

public class ButtonCreateNewRoomClickedEvent extends ApplicationEvent implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	/**Nazwa pokoju**/
	private String roomName;
	/**Nazwa użytkownika**/
	private String firstUserName;
	/**Nazwa hosta**/
	private String hostName;
	/**Numer portu**/
	private int portNumber;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie zadanych parametrów
	 * 
	 * @param roomName nazwa pokoju
	 * @param firstUserName nazwa użytkownika
	 * @param hostName nazwa hosta
	 * @param portNumber numer portu 
	 */
	public ButtonCreateNewRoomClickedEvent(String roomName, String firstUserName, String hostName, int portNumber)
	{
		this.roomName = roomName;
		this.firstUserName = firstUserName;
		this.hostName=hostName;
		this.portNumber=portNumber;
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
	 * Metoda zwracająca nazwę hosta
	 * 
	 * @return nazwa hosta
	 */
	public String getHostName()
	{
		return hostName;
	}

	/**
	 * Metoda zwracająca numer portu
	 * 
	 * @return numer portu
	 */
	public int getPortNumber() 
	{
		return portNumber;
	}

	/**
	 * Metoda zmieniająca nazwę pokoju
	 * 
	 * @param roomName nazwa pokoju
	 */
	public void setRoomName (String roomName)
	{
		this.roomName= roomName;
	}
}


