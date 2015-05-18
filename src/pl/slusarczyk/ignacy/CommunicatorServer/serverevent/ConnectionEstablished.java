package pl.slusarczyk.ignacy.CommunicatorServer.serverevent;

import java.io.Serializable;

/**
 * Klasa reprezentująca zaakceptowanie przez serwer nowego połączenia
 * 
 * @author Ignacy Ślusarczyk
 */
public class ConnectionEstablished extends ServerEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Invormacja o nawiązaniu połączenia**/
	private boolean isEstablished;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie zadanych parametrów
	 * 
	 * @param isEstablished czy połączenie przyjęte
	 */
	public ConnectionEstablished(boolean isEstablished)
	{
		this.isEstablished = isEstablished;
	}
	
	/**
	 * Metoda zwracająca informację o zaakceptowaniu połączenia
	 * @return
	 */
	public boolean getConnectionInfrmation()
	{
		return this.isEstablished;
	}
}
