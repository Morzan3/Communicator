package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;

/**Klasa, która udostępnia cały interfejs moelu 
 * 
 * @author Ignacy Ślusarczyk
 *
 */
public class Model
{
	/**Zbiór zawierający listę aktywnych pokoi**/
	HashSet<Room> roomList;
	
	/**
	 * Konstruktor tworzy pusty zbiór pokoi
	 */
	public Model()
	{
		this.roomList = new HashSet<Room>();
	}
	
	/** 
	 * Metoda tworząca nowy pokój oraz dodająca pierwszego użytkownika, który ten pokój utworzył
	 * 
	 * @param roomName Nazwa pokoju który zakładamy
	 * @param nameOfFirstUser Nazwa użytkownika, który zakłada pokój 
	 */
	public void createNewRoom(String roomName, String nameOfFirstUser)
	{
		Room room = new Room(roomName,nameOfFirstUser);
		roomList.add(room);
	}
	
	/**
	 * Metoda zwracająca obiekt Pokój odpowiadający pokojowi o danej nazwie
	 * 
	 * @param roomName Nazwa pokoju który chcemy otrzymać
	 * @return Obiekt Pokój którego nazwa opowiada zadanej
	 */
	public Room getRoomByName (String roomName)
	{
		for (Room roomToReturn : roomList)
		{
			if (roomName.equals(roomToReturn.getRoomName()))
			{
				return roomToReturn;
			}
		}	
		return null;
	}
	
	/**
	 * Metoda dodająca użytkownika o zadanym nicku do pokoju o zadanej nazwie 
	 * 
	 * @param roomName Nazwa pokoju, do której dodajemy użytkownika
	 * @param userToAddName Nick użytkownika, którego dodajemy
	 */
	
	public void addUserToSpecificRoom (String roomName, String userToAddName)
	{
		
		for (Room room : roomList)
		{
			if (roomName.equals(room.getRoomName()))
			{
				room.addUser(userToAddName);
			}
		}	
	}
	
	/**
	 * Metoda usuwajaca użytkownika o zadanym nicku z list użytkowników, z pokoju o zadanej nazwie
	 *  
	 * @param roomName Nazwa pokoju, z którego usuwamy użytkownika
	 * @param userToDelete Nazwa użytkownika, którego chcemy usunąć
	 */
	
	public int deleteUserFromSpecificRoom (String roomName, String userToDelete )
	{
		for (Room room : roomList)
		{
			if (roomName.equals(room.getRoomName()))
			{
				for(User user: room.getUserList())
				{
					if(user.getUserName().equals(userToDelete));
					room.getUserList().remove(user);
				}
				if(room.getUserList().size() == 0)
				{
					roomList.remove(room);
					if (roomList.size() == 0);
					{
						return 1;
					}
				}
				
			}
			
		}
		return 0;	
	}
	
	
	/**
	 * Metoda odpowiedzialna za dodanie wiadomości od użytkownika do jego historii wiadomości
	 * 
	 * @param roomName Nazwa pokoju, w którym użytkownik odbywa rozmowę
	 * @param nameOfSender Nazwa użytkownika, do którego historii mamy dodać daną wiadomość
	 * @param message Wiadomość do dodania.
	 */
	
	public void addMessageOfUser (String roomName, String nameOfSender, String message)
	{
		for (Room room : roomList)
		{
			if (roomName.equals(room.getRoomName()))
			{
				for (User user: room.getUserList())
				{
					if (nameOfSender.equals(user.getUserName()))
					{
						
						user.addMessage(message,Calendar.getInstance().getTime());
					}
				}
			}
		}	
	}
	
	
	/**
	 * Metoda tworząca jeden String z listy użytkowników pokoju o zadanej nazwie
	 * 
	 * @param roomName Nazwa pokoju, z którego chcemy pobrać listę użytkowników
	 * @return Obiekt typu String, który zawiera wszystkich użytkowników pokoju, gotowy do wyświetlenia u klienta
	 */
	public String getUsersFromRoom (String roomName)
	{
		String userList = new String("");
		
		for (Room room : roomList)
		{
			if (roomName.equals(room.getRoomName()))
			{
				for(User user: room.getUserList())
				{
					userList = user.getUserName() + "\n";
				}
				return userList;
			}
		
		}
		return null;	
	}
	
	/**
	 * Metoda zwracająca zbiór użytkowników danego pokoju
	 * 
	 * @param roomName nazwa pokoju
	 * @return zbiór użytkowników
	 */
	public HashSet<User> getUsersSetFromRoom (String roomName)
	{
		for (Room room : roomList)
		{
			if (room.getRoomName().equals(roomName))
			{
				return room.getUserList();
			}
		}
		return null;
	}
	
	
	/**
	 * Metoda tworząca jeden String, w którym jest cała konwersacja z pokoju o zadanej nazwie. Wiadomości są posortowane wg daty dodania.
	 * 
	 * @param roomName Nazwa pokoju, z którego chcemy pobrać całą rozmowę 
	 * @return Obiekt typu String, w którym jest cała rozmowa z danego pokoju, gotowa do wyświetlenia u klienta
	 */
	public String createConversationFromRoom (String roomName)
	{
		HashSet<Message> conversation = new HashSet<Message>();
		String sortedConversation = new String("");
		for (Room room : roomList)
			if (roomName.equals(room.getRoomName()))
			{
				for(User user : room.getUserList())
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
