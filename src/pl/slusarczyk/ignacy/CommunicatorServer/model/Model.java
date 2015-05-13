package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.util.HashSet;

public class Model
{
	HashSet<Room> roomList;
	
	
	public Model()
	{
		this.roomList = new HashSet<Room>();
	}
	
	
	public void createNewRoom(String roomName, String nameOfFirstUser)
	{
		Room room = new Room(roomName,nameOfFirstUser);
		roomList.add(room);
		return;
	}
	
	public Room getRoomByName (String roomName)
	{
		for (Room roomToReturn : roomList)
		{
			if (roomName.equals(roomToReturn.roomName))
			{
				return roomToReturn;
			}
		}	
		return null;
	}
	
	
	public void addUserToSpecificRoom (String roomName, String userToAddName)
	{
		for (Room room : roomList)
		{
			if (roomName.equals(room.roomName))
			{
				room.addUser(userToAddName);
			}
		}	
	}
	
	
	
	/**Metoda odpowiedzialna za dodanie wiadomości od użytkownika do jego historii**/
	
	public void addMessageOfUser (String roomName, String nameOfSender, String message)
	{
		for (Room room : roomList)
		{
			if (roomName.equals(room.roomName))
			{
				for (User user: room.listOfUsers)
				{
					if (nameOfSender.equals(user.userName))
					{
						user.addMessage(message, null);
					}
				}
			}
		}	
	}
	
	
	public HashSet<User> getUsersFromRoom (String roomName)
	{
		for (Room room : roomList)
			if (roomName.equals(room.roomName))
			{
				return room.listOfUsers;
			}
		return null;
	}
	
	public HashSet<Message> createConversationFromRoom (String roomName)
	{
		HashSet<Message> conversation = new HashSet<Message>();;
		for (Room room : roomList)
			if (roomName.equals(room.roomName))
			{
				for(User user : room.listOfUsers)
				{
					conversation.addAll(user.messageHistory);
				}
				return conversation;
			}
		return null;
	}
	
	
}
