package pl.slusarczyk.ignacy.CommunicatorServer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;










import com.sun.security.ntlm.Client;

import pl.slusarczyk.ignacy.CommunicatorClient.applicationevent.*;
import pl.slusarczyk.ignacy.CommunicatorServer.model.*;
import pl.slusarczyk.ignacy.CommunicatorServer.serverevent.ConnectionEstablished;
import pl.slusarczyk.ignacy.CommunicatorServer.connection.*;

public class Controller 
{
	/**Kolejka blokująca do przesyłania zdarzeń pomiędzy widokiem a kontrolerem*/
	private BlockingQueue<ApplicationEvent> eventQueue;
	/**Referencja do modelu*/
	private Model model;
	/**Mapa strategii obsługi zdarzeń*/
	private Map<Class<? extends ApplicationEvent>, ApplicationEventStrategy> strategyMap;
	/**Referencja do Servera*/
	private Server server;
	
	
	public Controller(final BlockingQueue<ApplicationEvent>  eventQueue2, final Model model, final Server server)
	{
		this.eventQueue = eventQueue2;
		this.model = model;
		this.server = server;
		
		//Tworzenie mapy strategii obsługi zdarzeń
		strategyMap = new HashMap<Class<? extends ApplicationEvent>, ApplicationEventStrategy>();
		strategyMap.put(ButtonCreateNewRoomClickedEvent.class, new ButtonCreateNewRoomClickedEventStrategy());
		strategyMap.put(ButtonJoinExistingRoomClickedEvent.class, new ButtonJoinExistingRoomClickedEventStrategy());
		strategyMap.put(ButtonSendMessageClickedEvent.class, new ButtonSendMessageClickedEventStrategy());
		strategyMap.put(UserName.class, new UserNameStrategy());

		
	}
		
	
		/**
		 * Główna metoda kontrolera, czeka on w niej na zdarzenia, a następnie odpowiednio je obsługuje.
		 */
	
	
		public void work()
		{
			while (true)
			{
				try
				{
					ApplicationEvent applicationEvent = eventQueue.take();
					ApplicationEventStrategy applicationEventStrategy = strategyMap.get(applicationEvent.getClass());
					applicationEventStrategy.execute(applicationEvent);
				}
				catch(InterruptedException e)
				{
					//Nic nie robimy, ponieważ kontroler ma być zawieszony dopóki nie pojawi się zdarzenie
				}
			}
		}
		
		
		
		/**
		 * Abstrakcyjna klasa bazowa dla klas strategii obsługujących zdarzenia.
		 * 
		 * @author Ignacy Ślusarczyk
		 */
		abstract class ApplicationEventStrategy
		{
			/**
			 * Abstrakcyjna metoda opisująca obsługę danego zdarzenia.
			 * 
			 * @param applicationEvent zdarzenie aplikacji które musi zostać obsłużone
			 */
			abstract void execute(final ApplicationEvent applicationEvent);

		}
		
		/**Klasa wewnętrzna opisująca strategię obsługi kliknięcia przez użytkownika przycisku utworzenia nowego pokoju
		 * 
		 * @author Ignacy Ślusarczyk
		 *
		 */
		
		class ButtonCreateNewRoomClickedEventStrategy extends ApplicationEventStrategy
		{
			void execute(final ApplicationEvent applicationEventObject)
			{
				try
				{
				String roomName = ((ButtonCreateNewRoomClickedEvent) applicationEventObject).getRoomName();
				String firstUserName = ((ButtonCreateNewRoomClickedEvent) applicationEventObject).getUserName();
				System.out.println("Create new room " + roomName  );
				model.createNewRoom(roomName, firstUserName);
				
				server.userOutputStreams.get(firstUserName).writeObject(new ConnectionEstablished(true));
				}
				catch(IOException ex)
				{
					System.err.println(ex);
				}
			}
		}
		
		
		/**Klasa wewnętrzna opisująca strategię obsługi kliknięcia przez użytkownika przycisku utworzenia dołączenia do już istniejącego pokoju
		 * 
		 * @author Ignacy Ślusarczyk
		 *
		 */
		
		class ButtonJoinExistingRoomClickedEventStrategy extends ApplicationEventStrategy
		{
			void execute(final ApplicationEvent applicationEventObject)
			{
				String roomName = ((ButtonJoinExistingRoomClickedEvent) applicationEventObject).getRoomName();
				String newUserName = ((ButtonJoinExistingRoomClickedEvent) applicationEventObject).getUserName();
				model.addUserToSpecificRoom(roomName, newUserName);
			}
		
		}
	
		class ButtonSendMessageClickedEventStrategy extends ApplicationEventStrategy
		{
			void execute(final ApplicationEvent applicationEventObject)
			{
				
				String roomName = ((ButtonSendMessageClickedEvent) applicationEventObject).getRoomName();
				String userName = ((ButtonSendMessageClickedEvent) applicationEventObject).getUserName();
				String message = ((ButtonSendMessageClickedEvent) applicationEventObject).getMessage();
				model.addMessageOfUser(roomName, userName, message);
	
				String userList = model.getUsersFromRoom(roomName);
				String conversationToSend = model.createConversationFromRoom(roomName);
				System.out.println( userList + conversationToSend);
				
				server.sendDirectMessage(userName,conversationToSend, userList);
			//	server.sendMessageToAll(model.createConversationFromRoom("test"), model.getUsersFromRoom("test"));
				
			}
		}
	
		class UserNameStrategy extends ApplicationEventStrategy
		{
			void execute(final ApplicationEvent applicationEventObject)
			{
				
					String userName = ((UserName) applicationEventObject).getUserName();
					String roomName = ((UserName) applicationEventObject).getRoomName();
					System.out.println(userName);
					//model.addUserToSpecificRoom(roomName, userName);
				
				
			}
		}
	
	
	
}
