package view;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
	
	/**
	 * Dandles a client, interacts with the client, gets his command and treats it. 
	 * 
	 * @param inFromClient [IN] the InputStream from the client
	 * @param outToClient  [IN] the OutputStream to the client
	 * @param clientID     [IN] the client's ID
	 */
	void handleClient(InputStream inFromClient, OutputStream outToClient, String clientID);
}
