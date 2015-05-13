package pl.slusarczyk.ignacy.CommunicatorServer.model;


import java.sql.Timestamp;
import java.util.HashSet;



public class User 
{
	String userName;
	Integer ID;
	HashSet<Message> messageHistory;
	
	
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
	
	public void addMessage (String textMessage, Timestamp timestamp)
	{
		Message message = new Message(textMessage,timestamp);
		messageHistory.add(message);
	}
	
	
}