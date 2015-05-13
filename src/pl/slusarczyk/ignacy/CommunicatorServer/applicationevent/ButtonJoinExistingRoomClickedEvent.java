package pl.slusarczyk.ignacy.CommunicatorServer.applicationevent;

public class ButtonJoinExistingRoomClickedEvent extends ApplicationEvent 
{
	private String roomName;
	private String firstUserName;

	
	
	public ButtonJoinExistingRoomClickedEvent (String roomName, String firstUserName)
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


