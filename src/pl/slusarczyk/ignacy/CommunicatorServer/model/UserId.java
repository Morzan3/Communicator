package pl.slusarczyk.ignacy.CommunicatorServer.model;

import java.io.Serializable;

/**
 * Klasa opakowująca nazwę użytkownika impelmentująca metody equals oraz hashCode, stanowiąca sposób identyfikacji przynależności obiektów
 * 
 * @author Ignacy Ślusarczyk
 */
public class UserId implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Nazwa użytkownika**/
	private final String userName;
	
	
	public UserId(final String userName)
	{
		this.userName = userName;
	};
	
	
	@Override
	public boolean equals(Object other) 
	{
		if(other == null)
		{
			return false;
		}
		
		if(!(other instanceof UserId))
		{
			return false;
		}
		
		String otherUserName = (String) other;
		return this.userName.equals(otherUserName);
	}
	
	
	@Override
	public int hashCode() 
	{
		return userName.hashCode();
	}
	
	/**
	 * Metoda zwracająca nazwę użytkownika z ID konieczna przy wyświetlaniu wiadomości w widoku ponieważ
	 * bez niej nie da się ich zidentyfikować
	 * 
	 * @return user name
	 */
	public String getUserName()
	{
		return userName;
	}
}
