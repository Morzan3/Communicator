package pl.slusarczyk.ignacy.CommunicatorServer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorClient.applicationevent.*;
import pl.slusarczyk.ignacy.CommunicatorServer.model.*;
import pl.slusarczyk.ignacy.CommunicatorServer.serverevent.ConnectionEstablished;
import pl.slusarczyk.ignacy.CommunicatorServer.connection.*;

/**
 * Klasa kontrolera odpowiadajaca za odpowiednią komunikację pomiędzy klientem a serwerem, zawierająca w sobie klasy
 * odpowiadające strategiom obsługi zdarzeń pochodzących od klienta
 * 
 * @author Ignacy Ślusarczyk
 */
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
	
	/**
	 * Konstruktor tworzący controler na podstawie zadanych parametrów
	 * @param eventQueue kolejka blokująca
	 * @param model model
	 * @param server serwer
	 */
	public Controller(final BlockingQueue<ApplicationEvent>  eventQueue, final Model model, final Server server)
	{
		this.eventQueue = eventQueue;
		this.model = model;
		this.server = server;
		
		//Tworzenie mapy strategii obsługi zdarzeń
		strategyMap = new HashMap<Class<? extends ApplicationEvent>, ApplicationEventStrategy>();
		strategyMap.put(ButtonCreateNewRoomClickedEvent.class, new ButtonCreateNewRoomClickedEventStrategy());
		strategyMap.put(ButtonJoinExistingRoomClickedEvent.class, new ButtonJoinExistingRoomClickedEventStrategy());
		strategyMap.put(ButtonSendMessageClickedEvent.class, new ButtonSendMessageClickedEventStrategy());	
		strategyMap.put(CloseMainWindowClickedEvent.class, new CloseMainWindowEventStrategy());
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
		
		/**
		 * Klasa wewnętrzna opisująca strategię obsługi kliknięcia przez użytkownika przycisku utworzenia nowego pokoju
		 *
		 * @author Ignacy Ślusarczyk
		 */
		class ButtonCreateNewRoomClickedEventStrategy extends ApplicationEventStrategy
		{
			void execute(final ApplicationEvent applicationEventObject)
			{
				try
				{
				String roomName = ((ButtonCreateNewRoomClickedEvent) applicationEventObject).getRoomName();
				String firstUserName = ((ButtonCreateNewRoomClickedEvent) applicationEventObject).getUserName();
				
				model.createNewRoom(roomName, firstUserName);
				server.userOutputStreams.get(firstUserName).writeObject(new ConnectionEstablished(true));
				}
				catch(IOException ex)
				{
					System.err.println(ex);
				}
			}
		}
		
		
		/**
		 * Klasa wewnętrzna opisująca strategię obsługi kliknięcia przez użytkownika przycisku dołączenia do już istniejącego pokoju
		 *
		 * @author Ignacy Ślusarczyk
		 */
		class ButtonJoinExistingRoomClickedEventStrategy extends ApplicationEventStrategy
		{
			void execute(final ApplicationEvent applicationEventObject)
			{
				try
				{
				String roomName = ((ButtonJoinExistingRoomClickedEvent) applicationEventObject).getRoomName();
				String newUserName = ((ButtonJoinExistingRoomClickedEvent) applicationEventObject).getUserName();
				model.addUserToSpecificRoom(roomName, newUserName);
				
				server.userOutputStreams.get(newUserName).writeObject(new ConnectionEstablished(true));
				}
				catch(IOException ex)
				{
					System.err.println(ex);
				}
			}
		}
	
		/**
		 * Klasa wewnętrzna opisująca strategię obsługi kliknięcia przez użytkownika przycisku dołączenia do już istniejącego pokoju
		 * 
		 * @author Ignacy Ślusarczyk
		 */
		class ButtonSendMessageClickedEventStrategy extends ApplicationEventStrategy
		{
			void execute(final ApplicationEvent applicationEventObject)
			{
				
				String roomName = ((ButtonSendMessageClickedEvent) applicationEventObject).getRoomName();
				String userName = ((ButtonSendMessageClickedEvent) applicationEventObject).getUserName();
				String message = ((ButtonSendMessageClickedEvent) applicationEventObject).getMessage();
				model.addMessageOfUser(roomName, userName, message);
				server.sendMessageToAll(model.createConversationFromRoom(roomName),model.getUsersFromRoom(roomName), model.getUsersSetFromRoom(roomName));	
			}
		}
		
		class CloseMainWindowEventStrategy extends ApplicationEventStrategy
		{

			@Override
			void execute(ApplicationEvent applicationEventObject) 
			{
				String roomName = ((CloseMainWindowClickedEvent) applicationEventObject).getRoomName();
				String userName = ((CloseMainWindowClickedEvent) applicationEventObject).getUserName();
				
				int  shouldClose =model.deleteUserFromSpecificRoom(roomName, userName);
				if(shouldClose ==1)
				{
					server.closeServer();
					System.exit(0);
				}
			}
			
		}
}
