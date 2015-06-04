package pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent;

import java.io.Serializable;

import pl.slusarczyk.ignacy.CommunicatorServer.model.UserId;

public class InformationMessageServerEvent extends ClientHandeledEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private final String message;
	private final UserId userID;
	
	public InformationMessageServerEvent(final String message, final UserId userID)
	{
		this.message = message;
		this.userID = userID;
	}

	/**
	 * Metoda zwracająca treść wiadomości
	 * 
	 * @return the message
	 */
	public String getMessage() 
	{
		return message;
	}

	/**
	 * Metoda zwracająca userId nadawcy
	 * 
	 * @return the userID
	 */
	public UserId getUserID()
	{
		return userID;
	}
}
