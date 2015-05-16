package pl.slusarczyk.ignacy.CommunicatorClient.applicationevent;

import java.io.Serializable;

public class ButtonJoinExistingRoomClickedEvent extends ApplicationEvent implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomName;
	private String userName;
	private String hostName;
	private String portNumber;
	
	
	public ButtonJoinExistingRoomClickedEvent (String roomName, String userName, String hostName, String portNumber)
	{
		this.roomName = roomName;
		this.userName = userName;
		this.hostName=hostName;
		this.portNumber=portNumber;
		
	}
	
	
	public String getRoomName()
	{
		return this.roomName;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	/**
	 * @return the hostName
	 */
	public String getHostName()
	{
		return hostName;
	}


	/**
	 * @return the portNumber
	 */
	public String getPortNumber() 
	{
		return portNumber;
	}

	
}


