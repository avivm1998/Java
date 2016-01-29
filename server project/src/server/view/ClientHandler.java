package server.view;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Client handler is the server's client treatment-giver.
 *  
 * @author Aviv Moran & Ayal Naim.
 *
 */
public interface ClientHandler {
	
	/**
	 * Dandles a client, interacts with the client, gets his command and treats it. 
	 * 
	 * @param inFromClient [IN] The InputStream from the client. {@link InputStream}
	 * @param outToClient  [IN] The OutputStream to the client. {@link OutputStream}
	 * @param clientID     [IN] The client's ID.
	 */
	void handleClient(InputStream inFromClient, OutputStream outToClient, String clientID);
}
