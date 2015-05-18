package pl.slusarczyk.ignacy.CommunicatorServer.model;



import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;


/**Klasa User zawiera wszystkie informacje o konkretnym użytkowniku
 * Na te informacji składa się jego nick oraz zbiór wiadomości typu Message, które reprezentują wszystkie wiadomości wysłane przez niego
 *  
 * @author Ignacy ŚLusarczyk
 *
 */

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	/** Nazwa danego użytkownika **/
	private String userName;
	/**Zbiór wysłanych przez użytkownika wiadomości **/
	private HashSet<Message> messageHistory;
	
	/**Konstruktor tworzący użytkownika o podanym imieniu
	 * 
	 * @param userName Nazwa użytkownika, którego tworzymy
	 */
	
	public User (String userName)
	{
		this.userName = userName;
		messageHistory = new HashSet<Message>();
	}
	
	
	/**Metoda zwracająca nazwę danego użytkownika**/
	public String getUserName ()
	{
		return this.userName;
	}
	
	/**Metoda zwracająca zbiór wiadomości wysłanych przez danego użytkowniak **/
	public HashSet<Message> getUserMessageHistory ()
	{
		return this.messageHistory;
	}
	
	/**Metoda dodająca wiadomość do zbioru wiadomości danego użytkownika
	 * 
	 * @param textMessage Treść dodawanej wiadomości
	 * @param timestamp Znacznik czasowy dodawanej wiadomości
	 */
	public void addMessage (String textMessage, Date timestamp)
	{
		textMessage = userName + ":" + textMessage + "\n";
		Message message = new Message(textMessage,timestamp);
		messageHistory.add(message);
	}
}