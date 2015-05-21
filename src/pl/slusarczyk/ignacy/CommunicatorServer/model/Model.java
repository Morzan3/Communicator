package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.util.Calendar;
import java.util.HashSet;
import pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent.CreateNewRoom;
import pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent.JoinExistingRoom;
import pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent.NewMessage;
import pl.slusarczyk.ignacy.CommunicatorServer.model.data.MessageData;
import pl.slusarczyk.ignacy.CommunicatorServer.model.data.RoomData;
import pl.slusarczyk.ignacy.CommunicatorServer.model.data.UserData;

/**Klasa, która udostępnia cały interfejs moelu 
 * 
 * @author Ignacy Ślusarczyk
 *
 */
public class Model
{
	/**Zbiór zawierający listę aktywnych pokoi**/
	private final HashSet<Room> roomList;
	
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
	public void createNewRoom(final CreateNewRoom createNewRoomInformation)
	{
		Room room = new Room(createNewRoomInformation.getRoomName(), createNewRoomInformation.getUserId());
		roomList.add(room);
	}

	/**
	 * Metoda dodająca użytkownika o zadanym nicku do pokoju o zadanej nazwie 
	 * 
	 * @param roomName Nazwa pokoju, do której dodajemy użytkownika
	 * @param userToAddName Nick użytkownika, którego dodajemy
	 */
	public void addUserToSpecificRoom (final JoinExistingRoom joinExistingRoominformation)
	{
		for (Room room : roomList)
		{
			if (joinExistingRoominformation.getRoomName().equals(room.getRoomName()))
			{
				room.addUser(joinExistingRoominformation.getUserId());
			}
		}	
	}
		
	/**
	 * Metoda odpowiedzialna za dodanie wiadomości od użytkownika do jego historii wiadomości
	 * 
	 * @param roomName Nazwa pokoju, w którym użytkownik odbywa rozmowę
	 * @param nameOfSender Nazwa użytkownika, do którego historii mamy dodać daną wiadomość
	 * @param message Wiadomość do dodania.
	 */
	
	public void addMessageOfUser (final NewMessage newMessageIfnormation)
	{
		for (Room room : roomList)
		{
			if (newMessageIfnormation.getRoomName().equals(room.getRoomName()))
			{
				for (User user: room.getUserList())
				{		
					if (newMessageIfnormation.getUserId().hashCode() == user.getUserID().hashCode())
					{
						user.addMessage(newMessageIfnormation,Calendar.getInstance().getTime());
					}
				}
			}
		}	
	}
	
	/**
	 * Metoda opakowująca dane z pokoju o zadanej nazwie w obiekt typu RoomData
	 * 
	 * @param roomName Nazwa pokoju, z którego chcemy pobrać listę użytkowników
	 * @return Obiekt typu String, który zawiera wszystkich użytkowników pokoju, gotowy do wyświetlenia u klienta
	 */
	public RoomData getRoomDataFromRoom (String roomName)
	{
		HashSet<UserData> userSet= new HashSet<UserData>();
	
		
		for (Room room : roomList)
		{
			if (roomName.equals(room.getRoomName()))
			{
				for(User user: room.getUserList())
				{
					HashSet<MessageData> messagesOfUser= new HashSet<MessageData>();
					for(Message message: user.getUserMessageHistory())
					{
						messagesOfUser.add(new MessageData(message.getMessage(),message.getDate()));
					}
					UserData userData = new UserData(user.getUserID(), messagesOfUser);
					userSet.add(userData);
				}
				return new RoomData(userSet);
			}
		
		}
		return null;
	}
}
