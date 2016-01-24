package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Observable;

public class MyClientHandler extends Observable implements ClientHandler {

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		setChanged();
		notifyObservers(outToClient);
		
		String command = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(inFromClient));
		
		try {
			while(!(command = in.readLine()).equals("exit")) {
				setChanged();
				notifyObservers(command);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
