package pl.slusarczyk.ignacy.CommunicatorServer.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import javax.swing.text.View;

import pl.slusarczyk.ignacy.CommunicatorServer.applicationevent.ApplicationEvent;
import pl.slusarczyk.ignacy.CommunicatorServer.applicationevent.ButtonCreateNewRoomClickedEvent;
import pl.slusarczyk.ignacy.CommunicatorServer.applicationevent.ButtonJoinExistingRoomClickedEvent;
import pl.slusarczyk.ignacy.CommunicatorServer.applicationevent.ButtonSendMessageClickedEvent;
import pl.slusarczyk.ignacy.CommunicatorServer.model.Model;
import pl.slusarczyk.ignacy.CommunicatorServer.connection.*;

public class Controller 
{
	/**Kolejka blokująca do przesyłania zdarzeń pomiędzy widokiem a kontrolerem*/
	private BlockingQueue<ApplicationEvent> eventQueue;
	/**Referencja do modelu*/
	private Model model;
	/**Mapa strategii obsługi zdarzeń*/
	private Map<Class<? extends ApplicationEvent>, ApplicationEventStrategy> strategyMap;
	private Server server;
	
	
	public Controller(final BlockingQueue<ApplicationEvent> eventQueue, final Model model)
	{
		this.eventQueue = eventQueue;
		this.model = model;
		
		//Tworzenie mapy strategii obsługi zdarzeń
		strategyMap = new HashMap<Class<? extends ApplicationEvent>, ApplicationEventStrategy>();
		strategyMap.put(ButtonCreateNewRoomClickedEvent.class, new ButtonCreateNewRoomClickedEventStrategy());
		strategyMap.put(ButtonJoinExistingRoomClickedEvent.class, new ButtonJoinExistingRoomClickedEventStrategy());
		strategyMap.put(ButtonSendMessageClickedEvent.class, new ButtonSendMessageClickedEventStrategy());
		//strategyMap.put(ButtonRemoveChannelClickedEvent.class, new ButtonRemoveChannelClickedEventStrategy());
		//strategyMap.put(ChannelClickedEvent.class, new ChannelClickedEventStrategy());
		//strategyMap.put(ArticleWasOpenedEvent.class, new ArticleWasOpenedEventStrategy());
		
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
				String roomName = ((ButtonCreateNewRoomClickedEvent) applicationEventObject).getRoomName();
				String firstUserName = ((ButtonCreateNewRoomClickedEvent) applicationEventObject).getUserName();
				model.createNewRoom(roomName, firstUserName);
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
		}
	}
	
	
}
