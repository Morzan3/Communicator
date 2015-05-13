package pl.slusarczyk.ignacy.CommunicatorServer.applicationevent;

public class ButtonSendMessageClickedEvent extends ApplicationEvent 
{
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
