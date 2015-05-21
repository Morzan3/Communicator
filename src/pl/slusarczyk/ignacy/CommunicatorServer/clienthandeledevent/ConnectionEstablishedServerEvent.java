package pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent;

import java.io.Serializable;

/**
 * Klasa reprezentująca zaakceptowanie przez serwer nowego połączenia, sygnalizująca możliwość otworzenia głównego okna chatu w aplikacji klienckiej
 * 
 * @author Ignacy Ślusarczyk
 */
public class ConnectionEstablishedServerEvent extends ClientHandeledEvent implements Serializable
{
	 private static final long serialVersionUID = 1L;
	/**Invormacja o nawiązaniu połączenia**/
	 private  final boolean isEstablished;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie zadanych parametrów
	 * 
	 * @param isEstablished czy połączenie przyjęte
	 */
	public ConnectionEstablishedServerEvent(final boolean isEstablished)
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
