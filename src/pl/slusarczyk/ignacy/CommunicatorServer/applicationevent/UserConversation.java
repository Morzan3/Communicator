package pl.slusarczyk.ignacy.CommunicatorServer.applicationevent;

import java.util.HashSet;
import pl.slusarczyk.ignacy.CommunicatorServer.model.*;

public class UserConversation 
{
	HashSet<Message> userConversation;
	
	public UserConversation (HashSet<Message> userConversation)
	{
		this.userConversation = userConversation;
	}

	/**
	 * @return the userConversation
	 */
	public HashSet<Message> getUserConversation() {
		return userConversation;
	}

	/**
	 * @param userConversation the userConversation to set
	 */
	public void setUserConversation(HashSet<Message> userConversation) {
		this.userConversation = userConversation;
	}
	
	
	
	
}
