import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pl.slusarczyk.ignacy.CommunicatorClient.applicationevent.*;
import pl.slusarczyk.ignacy.CommunicatorServer.connection.Server;
import pl.slusarczyk.ignacy.CommunicatorServer.controller.Controller;
import pl.slusarczyk.ignacy.CommunicatorServer.model.Model;



public class CommunicatorServer 
{
	public static void main(String args[])
	{
		BlockingQueue<ApplicationEvent> eventQueue = new LinkedBlockingQueue<ApplicationEvent>();
		Server server = new Server(0,eventQueue);
		Model model = new Model();
		Controller controller = new Controller(eventQueue, model, server);
		controller.work();
	}
	
}
