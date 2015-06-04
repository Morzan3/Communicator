package pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent;

import java.io.Serializable;

import pl.slusarczyk.ignacy.CommunicatorServer.model.data.UserIdData;

public class InformationMessageServerEvent extends ClientHandeledEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private final String message;
	private final UserIdData userIDData;
	
	public InformationMessageServerEvent(final String message, final UserIdData userIDData)
	{
		this.message = message;
		this.userIDData = userIDData;
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
	public UserIdData getUserID()
	{
		return userIDData;
	}
}
