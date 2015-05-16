package pl.slusarczyk.ignacy.CommunicatorServer.model;



import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;



public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	//private Integer ID;
	private HashSet<Message> messageHistory;
	
	
	public User (String userName)
	{
		this.userName = userName;
		messageHistory = new HashSet<Message>();
	}
	
	
	public String getUserName ()
	{
		return this.userName;
	}
	
	public HashSet<Message> getUserMessageHistory ()
	{
		return this.messageHistory;
	}
	
	public void addMessage (String textMessage, Date timestamp)
	{
		textMessage = userName + ":" + textMessage + "\n";
		Message message = new Message(textMessage,timestamp);
		messageHistory.add(message);
	}
	
	
}