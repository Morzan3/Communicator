package pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent;

import java.io.Serializable;

import pl.slusarczyk.ignacy.CommunicatorServer.model.data.RoomData;

/**
 * Klasa reprezentująca otrzymanie wiadomości na serwerze i konieczność uaktualnienia rozmowy w oknie klienta, wysyła 
 * opakowane dane o pokoju, w którym została dodana nowa wiadomość
 * 
 * @author Ignacy Ślusarczyk
 */
public class ConversationInformationServerEvent extends ClientHandeledEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Opakowane informacje o pokoju*/
	private final RoomData roomData;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie zadanych parametrów
	 * 
	 * @param userConversation rozmowa użytkowników do wyświetlenia
	 * @param listOfUsers lista użytkowników do wyświetlenia
	 */
	public ConversationInformationServerEvent (final RoomData room)
	{
		this.roomData = room;
	}

	/**
	 * Metoda zwracająca rozmowę użytkowników
	 * 
	 * @return rozmowa użytkowników
	 */
	public RoomData getRoom() 
	{
		return roomData;
	}
}

