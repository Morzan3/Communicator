package pl.slusarczyk.ignacy.CommunicatorServer.serverevent;

import java.io.Serializable;

/**
 * Klasa reprezentująca wysłanie przez serwer konwersacji do wyświetlenia u klienta
 * 
 * @author Ignacy Ślusarczyk
 */

public class ConversationInformation extends ServerEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Konwersacja do wyświetlenia**/
	String userConversation;
	/**Lista użytkowników do wyświetlenia**/
	String listOfUsersInTheRoom;
	
	/**
	 * Konstruktor tworzący zdarzenie na podstawie zadanych parametrów
	 * 
	 * @param userConversation rozmowa użytkowników do wyświetlenia
	 * @param listOfUsers lista użytkowników do wyświetlenia
	 */
	public ConversationInformation (String userConversation, String listOfUsers)
	{
		this.userConversation = userConversation;
		this.listOfUsersInTheRoom = listOfUsers;
	}

	/**
	 * Metoda zwracająca rozmowę użytkowników
	 * 
	 * @return rozmowa użytkowników
	 */
	public String getUserConversation() 
	{
		return userConversation;
	}

	/**
	 * Metoda zwracająca listę użytkowników do wyświetlenia
	 * 
	 * @return lista użytkowników
	 */
	public String getListOfUsers ()
	{
		return this.listOfUsersInTheRoom;
	}
}

