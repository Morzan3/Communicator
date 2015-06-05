package pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent;

import java.io.Serializable;

import pl.slusarczyk.ignacy.CommunicatorServer.model.data.UserIdData;

/**
 * Klasa służąca do wysyłania informacji do wyświetlenia u użytkownika np do informowania, iż dany nick jest już zajęty.
 * 
 * @author Ignacy Ślusarczyk
 */
public class InformationMessageServerEvent extends ClientHandeledEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Wiadomość, która ma zostać wyświetlona u użytkownika*/
	private final String message;
	/**Opakowana nazwa użytkownika, u którego dana wiadomość ma zostać wyświetlona*/
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
	public UserIdData getUserIDData()
	{
		return userIDData;
	}
}
