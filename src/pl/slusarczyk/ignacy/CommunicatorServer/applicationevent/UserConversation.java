package pl.slusarczyk.ignacy.CommunicatorServer.applicationevent;

import java.util.HashSet;
import pl.slusarczyk.ignacy.CommunicatorServer.model.*;

public class UserConversation extends ApplicationEvent
{
	HashSet<Message> userConversation;
	HashSet<User> listOfUsersInTheRoom;
	
	public UserConversation (HashSet<Message> userConversation, HashSet<User> listOfUsers)
	{
		this.userConversation = userConversation;
		this.listOfUsersInTheRoom = listOfUsers;
	}

	/**
	 * @return the userConversation
	 */
	public HashSet<Message> getUserConversation() 
	{
		return userConversation;
	}

	/**
	 * @param userConversation the userConversation to set
	 */
	public void setUserConversation(HashSet<Message> userConversation) {
		this.userConversation = userConversation;
	}
	
	
	public HashSet<User> getListOfUsers ()
	{
		return this.listOfUsersInTheRoom;
	}
	
	
	
	
}
