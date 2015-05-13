package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.sql.Timestamp;



/** Klasa reprezentująca pojedyńczą wiadomość które składają się na całą rozmowę użytkownika 
 * 
 * @author Ignacy ŚLusarczyk
 *
 */

public class Message 
{
	String message;
	Timestamp createdOn;


	
	public Message (String message,Timestamp timestamp)
	{
		this.message = message;
		this.createdOn = timestamp;
	}
	
	
	public String getMessage()
	{
		return this.message;
	}
	
	public Timestamp getCreateDate ()
	{
		return this.createdOn;
	}
	
}
