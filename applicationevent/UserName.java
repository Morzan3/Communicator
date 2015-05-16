package pl.slusarczyk.ignacy.CommunicatorClient.applicationevent;

public class UserName extends ApplicationEvent 
{
	private String firstUserName;
	
	public UserName( String firstUserName)
	{
	
		this.firstUserName = firstUserName;

		
		
	}
	
	

	public String getUserName()
	{
		return this.firstUserName;
	}




	
}
