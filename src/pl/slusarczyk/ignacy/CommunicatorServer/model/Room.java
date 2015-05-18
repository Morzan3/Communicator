package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.util.HashSet;

/** 
 * Klasa reprezentująca jeden pokój chatu, zawierająca zbiór użytkowników danego pokoju oraz jego nazwę
 *
 * @author Ignacy Ślusarczyk
 */
public class Room
{
	/**Nazwa danego pokoju chatu**/
	private String roomName;
	/**Lista obiektów klasy User**/
	private HashSet<User> listOfUsers;
	
	/**Konstruktor tworzący nowy pokój na podstawie nazwy pokoju oraz nazwy użytkownika, który ten pokój założył
	 * 
	 * @param roomName Nazwa pokoju
	 * @param firstUserName Nazwa użytkownika zakładającego pokój
	 */
	public Room (String roomName, String firstUserName)
	{
		this.roomName = roomName;
		listOfUsers = new HashSet<User>();
		User firstUser = new User(firstUserName);
		listOfUsers.add(firstUser);
	}
	
	/**
	 * Metoda zwracająca zbiór użytkowników danego pokoju
	 * 
	 * @return zbiór użytkowników pokoju
	 */
	public HashSet<User> getUserList()
	{
		return this.listOfUsers;
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
	public void addUser (String userName)
	{
		System.out.println("Dodaje uzytkownika do konkretnego pokoju ");
		User newUser = new User (userName);
		listOfUsers.add(newUser);
	}
	
	/**Metoda usuwająca użytkownika o zadanym imieniu z danego pokoju 
	 * 
	 * @param userToDelete Nazwa użytkownika, którego chcemy usunąć 
	 */
	public void deleteUserByName (String userToDelete)
	{
		for (User user : listOfUsers)
		{
			if(userToDelete.equals(user.getUserName()))
			{
				listOfUsers.remove(user);
				return;
			}
		}
	}
}