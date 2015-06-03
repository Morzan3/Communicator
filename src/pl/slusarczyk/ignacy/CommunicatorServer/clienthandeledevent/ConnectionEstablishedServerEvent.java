package pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent;

import java.io.Serializable;

import pl.slusarczyk.ignacy.CommunicatorServer.model.UserId;

/**
 * Klasa reprezentująca zaakceptowanie przez serwer nowego połączenia, sygnalizująca możliwość otworzenia głównego okna chatu w aplikacji klienckiej
 * 
 * @author Ignacy Ślusarczyk
 */
public class ConnectionEstablishedServerEvent extends ClientHandeledEvent implements Serializable
{
	 private static final long serialVersionUID = 1L;
	/**Invormacja o nawiązaniu połączenia*/
	 private  final boolean isEstablished;
	 /**UserId użytkownika*/
	 private final UserId userID;
	 /**Nazwa pokoju do którego został dołączony*/
	 private final String roomName;
	 
	/**
	 * Konstruktor tworzący zdarzenie na podstawie zadanych parametrów
	 * 
	 * @param isEstablished czy połączenie przyjęte
	 */
	public ConnectionEstablishedServerEvent(final boolean isEstablished, UserId userId,String roomName)
	{
		this.isEstablished = isEstablished;
		this.userID = userId;
		this.roomName = roomName;
	}
	
	/**
	 * Metoda zwracająca informację o zaakceptowaniu połączenia
	 * 
	 * @return
	 */
	public boolean getConnectionInfrmation()
	{
		return this.isEstablished;
	}
	
	public UserId getUserID()
	{
		return userID;
	}
	
	public String getRoomName()
	{
		return roomName;
	}
	
}
