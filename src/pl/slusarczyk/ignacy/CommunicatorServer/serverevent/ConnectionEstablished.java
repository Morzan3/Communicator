package pl.slusarczyk.ignacy.CommunicatorServer.serverevent;

import java.io.Serializable;

public class ConnectionEstablished extends ServerEvent implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isEstablished;
	
	
	public ConnectionEstablished(boolean isEstablished)
	{
		this.isEstablished = isEstablished;
	}
	
	
	public boolean getConnectionInfrmation()
	{
		return this.isEstablished;
	}
	
	
}
