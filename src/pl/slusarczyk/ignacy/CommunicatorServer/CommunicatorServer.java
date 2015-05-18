package pl.slusarczyk.ignacy.CommunicatorServer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import pl.slusarczyk.ignacy.CommunicatorClient.applicationevent.*;
import pl.slusarczyk.ignacy.CommunicatorServer.connection.Server;
import pl.slusarczyk.ignacy.CommunicatorServer.controller.Controller;
import pl.slusarczyk.ignacy.CommunicatorServer.model.Model;

/**
 * Główna klasa applikacji odpowiada za odpowiednie zainicjalizowanie wszystkich komponentów
 * 
 * @author Ignacy Ślusarczyk
 *
 */
public class CommunicatorServer 
{
	/**
	 * Głowna metoda aplikacji,tworzy model, kolejkę zdarzeń oraz kontroler.
	 * 
	 * @param args argumenty wywołania programu
	 */
	public static void main(String args[])
	{
		BlockingQueue<ApplicationEvent> eventQueue = new LinkedBlockingQueue<ApplicationEvent>();
		Server server = new Server(5000,eventQueue);
		Model model = new Model();
		Controller controller = new Controller(eventQueue, model, server);
		controller.work();
	}	
}
