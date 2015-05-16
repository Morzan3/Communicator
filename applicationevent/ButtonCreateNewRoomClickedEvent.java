package pl.slusarczyk.ignacy.CommunicatorClient.applicationevent;

import java.io.Serializable;

public class ButtonCreateNewRoomClickedEvent extends ApplicationEvent implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomName;
	private String firstUserName;
	private String hostName;
	private String portNumber;
	
	public ButtonCreateNewRoomClickedEvent(String roomName, String firstUserName, String hostName, String portNumber)
	{
		this.roomName = roomName;
		this.firstUserName = firstUserName;
		this.hostName=hostName;
		this.portNumber=portNumber;
		
		
	}
	
	
	public String getRoomName()
	{
		return this.roomName;
	}
	
	public String getUserName()
	{
		return this.firstUserName;
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


