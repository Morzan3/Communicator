package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.io.Serializable;
import java.util.HashSet;

/** 
 * Klasa reprezentująca jeden pokój chatu, zawierająca zbiór użytkowników danego pokoju oraz jego nazwę
 *
 * @author Ignacy Ślusarczyk
 */
class Room implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**Nazwa danego pokoju chatu**/
	private String roomName;
	/**Lista obiektów klasy User**/
	private HashSet<User> listOfUsers;
	
	/**Konstruktor tworzący nowy pokój na podstawie nazwy pokoju oraz nazwy użytkownika, który ten pokój założył
	 * 
	 * @param roomName Nazwa pokoju
	 * @param firstUserName Nazwa użytkownika zakładającego pokój
	 */
	public Room (final String roomName,final UserId userId)
	{
		this.roomName = roomName;
		listOfUsers = new HashSet<User>();
		User firstUser = new User(userId);
		listOfUsers.add(firstUser);
	}
	
	/**
	 * Metoda zwracająca zbiór użytkowników danego pokoju
	 * 
	 * @return zbiór użytkowników pokoju
	 */
	public HashSet<User> getUserList()
	{
		return listOfUsers;
	}
	
	/**
	 * Metoda zwracająca nazwę pokoju
	 * 
	 * @return nazwa pokoju
	 */
	public String getRoomName ()
	{
		return this.roomName;
	}
		
	/** Metoda dodaję użytkownika o zadanym imieniu do pokoju
	 * 
	 * @param userName Nazwa użytkownika którego dodajemy
	 */
	public void addUser (final UserId userId)
	{
		System.out.println("Dodaje uzytkownika do konkretnego pokoju ");
		User newUser = new User (userId);
		listOfUsers.add(newUser);
	}
	
	/**Metoda usuwająca użytkownika o zadanym imieniu z danego pokoju 
	 * 
	 * @param userToDelete Nazwa użytkownika, którego chcemy usunąć 
	 */
	public void deleteUserByName (final UserId userToDelete)
	{
		for (User user : listOfUsers)
		{
			if(userToDelete.hashCode() == user.getUserID().hashCode())
			{
				listOfUsers.remove(user);
				return;
			}
		}
	}
}