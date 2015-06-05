package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.util.Calendar;
import java.util.HashSet;

import pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent.ClientLeftRoom;
import pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent.CreateNewRoom;
import pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent.JoinExistingRoom;
import pl.slusarczyk.ignacy.CommunicatorClient.serverhandeledevent.NewMessage;
import pl.slusarczyk.ignacy.CommunicatorServer.model.data.MessageData;
import pl.slusarczyk.ignacy.CommunicatorServer.model.data.RoomData;
import pl.slusarczyk.ignacy.CommunicatorServer.model.data.UserData;
import pl.slusarczyk.ignacy.CommunicatorServer.model.data.UserIdData;

/**Klasa, która udostępnia cały interfejs modelu 
 * 
 * @author Ignacy Ślusarczyk
 *
 */
public class Model
{
	/**Zbiór zawierający listę aktywnych pokoi*/
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
	 * @param createNewRoomInformation opakowane informacje potrzebne do założenia nowego pokoju
	 */
	public boolean createNewRoom(final CreateNewRoom createNewRoomInformation)
	{
		for(Room room : roomList)
		{
			if (room.getRoomName().equals(createNewRoomInformation.getRoomName()))
			{
				return false;
			}
		}
	
		roomList.add(new Room(createNewRoomInformation.getRoomName(), new UserId(createNewRoomInformation.getUserIdData().getUserName())));
		return true;
	}

	/**
	 * Metoda dodająca użytkownika o zadanym nicku do pokoju o zadanej nazwie 
	 * 
	 * @param joinExistingRoomInformation opakowane informacje potrzebne do dołączenia użytkownika do danego pokoju
	 */
	public boolean addUserToSpecificRoom (final JoinExistingRoom joinExistingRoomInformation)
	{
		for (Room room : roomList)
		{
			if (joinExistingRoomInformation.getRoomName().equals(room.getRoomName()))
			{
				room.addUser(new UserId(joinExistingRoomInformation.getUserIdData().getUserName()));
				return true;
			}
		}
		return false;
	}
		
	/**
	 * Metoda odpowiedzialna za dodanie wiadomości od użytkownika do jego historii wiadomości
	 * 
	 * @param neMessageInformation opakowane informacje potrzebne do dodania nowej wiaodmości użytownika
	 */
	public void addMessageOfUser (final NewMessage newMessageInformation)
	{
		for (Room room : roomList)
		{
			if (newMessageInformation.getRoomName().equals(room.getRoomName()))
			{
				for (User user: room.getUserList())
				{		
					if (new UserId(newMessageInformation.getUserIdData().getUserName()).equals(user.getUserID()))
					{
						user.addMessage(newMessageInformation, Calendar.getInstance().getTime());
					}
				}
			}
		}	
	}
	
	/**
	 * Metoda opakowująca dane pokoju, do którego przyszła nowa wiadomość 
	 * 
	 * @param newMessageInformation opakowana wiadomość wysłana do pokoju, z którego dane chcemy pobrać
	 * 
	 * @return opakowane informacje o pokoju
	 */
	public RoomData getRoomDataFromRoom (final NewMessage newMessageInformationObject)
	{
		HashSet<UserData> userSet= new HashSet<UserData>();
	
		for (Room room : roomList)
		{
			if (newMessageInformationObject.getRoomName().equals(room.getRoomName()))
			{
				for(User user: room.getUserList())
				{
					HashSet<MessageData> messagesOfUser= new HashSet<MessageData>();
					for(Message message: user.getUserMessageHistory())
					{
						messagesOfUser.add(new MessageData(message.getMessage(),message.getDate()));
					}
					UserData userData = new UserData(new UserIdData(user.getUserID()), messagesOfUser, user.getUserStatus());
					userSet.add(userData);
				}
				return new RoomData(userSet);
			}
		}
		return null;
	}
	
	/**
	 * Metoda oznaczająca danego użytkownika jako nieaktywnego
	 * 
	 * @param clientLeftRoomInformation informacje o użytkowniku, który wyszedł z chatu
	 */
	public void setUserToInactive (ClientLeftRoom clientLeftRoomInformation)
	{
		for (Room room: roomList)
		{
			if(room.getRoomName().equals(clientLeftRoomInformation.getRoomName()))
			{
				for (User user:room.getUserList())
				{
					if(user.getUserID().equals(new UserId(clientLeftRoomInformation.getUserIDData().getUserName())))
					{
						user.setUserToInactive();
					}
				}
			}
		}
	}
}
