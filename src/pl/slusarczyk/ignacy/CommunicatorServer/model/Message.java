package pl.slusarczyk.ignacy.CommunicatorServer.model;




/** Klasa reprezentująca pojedyńczą wiadomość które składają się na całą rozmowę użytkownika 
 * 
 * @author Ignacy ŚLusarczyk
 *
 */

public class Message 
{
	String message;
	java.sql.Date createdOn;


	
	public Message (String message,java.sql.Date timestamp)
	{
		this.message = message;
		this.createdOn = timestamp;
	}
	
	
	public String getMessage()
	{
		return this.message;
	}
	
	public java.sql.Date getCreateDate ()
	{
		return this.createdOn;
	}
	
	public void seTTimestamp (java.sql.Date currentTime)
	{
		this.createdOn = currentTime;
	}
	
	
}
