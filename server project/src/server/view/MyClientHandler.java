package server.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Observable;

/**
 * MyClientHandler is a concrete type of client handler which treats the clients of the server.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class MyClientHandler extends Observable implements ClientHandler {

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient, String clientID) {
		setChanged();
		notifyObservers(outToClient);
		
		String command = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(inFromClient));
		
		try {
			while((command = in.readLine()) != null) {
				if(command.equals("done"))
					return;
				
				setChanged();
				notifyObservers(clientID + " " + command);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
