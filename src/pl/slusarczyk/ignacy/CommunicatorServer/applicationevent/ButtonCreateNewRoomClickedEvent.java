package pl.slusarczyk.ignacy.CommunicatorServer.applicationevent;

public class ButtonCreateNewRoomClickedEvent extends ApplicationEvent 
{
	private String roomName;
	private String firstUserName;
	
	
	public ButtonCreateNewRoomClickedEvent(String roomName, String firstUserName)
	{
		this.roomName = roomName;
		this.firstUserName = firstUserName;
		
		
	}
	
	
	public String getRoomName()
	{
		return this.roomName;
	}
	
	public String getUserName()
	{
		return this.firstUserName;
	}
	
	
	
}


