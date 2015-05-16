package pl.slusarczyk.ignacy.CommunicatorServer.serverevent;

import java.io.Serializable;
import java.util.HashSet;



public class ConversationInformation extends ServerEvent implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String userConversation;
	String listOfUsersInTheRoom;
	
	public ConversationInformation (String userConversation, String listOfUsers)
	{
		this.userConversation = userConversation;
		this.listOfUsersInTheRoom = listOfUsers;
	}

	/**
	 * @return the userConversation
	 */
	public String getUserConversation() 
	{
		return userConversation;
	}

	/**
	 * @param userConversation the userConversation to set
	 */
	public void setUserConversation(String userConversation) {
		this.userConversation = userConversation;
	}
	
	
	public String getListOfUsers ()
	{
		return this.listOfUsersInTheRoom;
	}
	
	
	
	
}

