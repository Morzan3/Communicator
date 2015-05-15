package pl.slusarczyk.ignacy.CommunicatorServer.applicationevent;

public class UserName extends ApplicationEvent
{
	String userName;
	
	
	public UserName(String name)
	{
		this.userName = name;
	}


	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}
	
	
	
}
