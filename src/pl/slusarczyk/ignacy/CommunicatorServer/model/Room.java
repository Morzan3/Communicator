package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.util.HashSet;




public class Room
{
	String roomName;
	HashSet<User> listOfUsers;
	
	
	
	public Room (String roomName, String firstUserName)
	{
		this.roomName = roomName;
		listOfUsers = new HashSet<User>();
		User firstUser = new User(firstUserName);
		listOfUsers.add(firstUser);
	}
	
	
	
	public void addUser (String userName)
	{
		System.out.println("Dodaje uzytkownika do konkretnego pokoju ");
		User newUser = new User (userName);
		listOfUsers.add(newUser);
	}
	
	
	
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
	
	public HashSet<User> getUserList()
	{
		return this.listOfUsers;
	}
	
	
	
	
}