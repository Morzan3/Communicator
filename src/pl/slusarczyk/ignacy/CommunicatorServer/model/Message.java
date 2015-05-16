package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.io.Serializable;
import java.util.Date;




/** Klasa reprezentująca pojedyńczą wiadomość które składają się na całą rozmowę użytkownika 
 * 
 * @author Ignacy ŚLusarczyk
 *
 */

public class Message implements Comparable<Message>,  Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private Date createdOn;


	
	public Message (String message,Date timestamp)
	{
		this.message = message;
		this.createdOn = timestamp;
	}
	
	
	public String getMessage()
	{
		return this.message;
	}
	
	public Date getDate ()
	{
		return this.createdOn;
	}
	
	public void setDate (Date currentTime)
	{
		this.createdOn = currentTime;
	}
	
	  public int compareTo(Message o) 
	  {
	    return getDate().compareTo(o.getDate());
	  }
	
}
