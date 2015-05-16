package pl.slusarczyk.ignacy.CommunicatorClient.applicationevent;

import java.io.Serializable;

public class UserName extends ApplicationEvent implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstUserName;
	private String roomName;
	
	public UserName( String firstUserName, String roomName)
	{
	
		this.firstUserName = firstUserName;
		this.roomName = roomName;
		
		
	}
	
	

	public String getUserName()
	{
		return this.firstUserName;
	}

	public String getRoomName()
	{
		return this.roomName;
	}



	
}
