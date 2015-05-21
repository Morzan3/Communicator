package pl.slusarczyk.ignacy.CommunicatorServer.clienthandeledevent;

/**
 * Klasa reprezentująca naciśnięcie przez użytkownika przycisku wysłania wiadoomści w oknie chatu
 * 
 * @author Ignacy Ślusarczyk
 */
public class SendMessageButtonViewEvent extends ClientHandeledEvent
{
	private final String message;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie podanych parametrów
	 * 
	 * @param message treść wiadomości
	 */
	public SendMessageButtonViewEvent(final String message)
	{
		this.message = message;
	}

	/**
	 * Metoda zwracająca wpisaną przez użytkowika wiadomość
	 * 
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}
}
