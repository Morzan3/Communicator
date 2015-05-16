package pl.slusarczyk.ignacy.CommunicatorClient.applicationevent;

import java.io.Serializable;

public class ButtonSendMessageClickedEvent extends ApplicationEvent implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomName;
	private String userName;
	private String message;
	
	
	public ButtonSendMessageClickedEvent (String roomName, String userName, String message)
	{
		this.roomName = roomName;
		this.userName = userName;
		this.message = message;
	}
	
	
	public String getRoomName()
	{
		return this.roomName;
	}
	
	public String getUserName ()
	{
		return this.userName;
	}
	
	
	public String getMessage ()
	{
		return this.message;
	}
	
	
	
}
