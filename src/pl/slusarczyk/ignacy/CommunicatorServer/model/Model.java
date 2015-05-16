package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;

public class Model
{
	HashSet<Room> roomList;
	Calendar calendar = Calendar.getInstance();
	java.util.Date now = calendar.getTime();
	
	public Model()
	{
		this.roomList = new HashSet<Room>();
	}
	
	
	public void createNewRoom(String roomName, String nameOfFirstUser)
	{
		Room room = new Room(roomName,nameOfFirstUser);
		
		roomList.add(room);
		System.out.println("Lista uzytkownikow model" + room.listOfUsers);
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
					if (nameOfSender.equals(user.getUserName()))
					{
						
						user.addMessage(message,Calendar.getInstance().getTime());
					}
				}
			}
		}	
	}
	
	
	public String getUsersFromRoom (String roomName)
	{
		String userList = new String("");
		
		for (Room room : roomList)
		{
			if (roomName.equals(room.roomName))
			{
				for(User user: room.listOfUsers)
				{
					userList = user.getUserName() + "\n";
				}
				return userList;
			}
		
		}
		return null;	
	}
	
	public String createConversationFromRoom (String roomName)
	{
		HashSet<Message> conversation = new HashSet<Message>();
		String sortedConversation = new String("");
		for (Room room : roomList)
			if (roomName.equals(room.roomName))
			{
				for(User user : room.listOfUsers)
				{
					conversation.addAll(user.getUserMessageHistory());
				}
				
				ArrayList<Message> listOfConversations = new ArrayList<Message>();
				listOfConversations.addAll(conversation);
				Collections.sort(listOfConversations);
				
				for(Message message :listOfConversations)
				{
					sortedConversation = sortedConversation + message.getMessage();
				}
				
				return sortedConversation;
			}
		return null;
	}
}
