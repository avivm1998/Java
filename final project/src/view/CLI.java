package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CLI - Command Line Interface is a View using the console.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class CLI extends MyView {
	

	@Override
	public void display(String args) {
		System.out.println(args);
		
	}

	@Override
	public void getUserCommand() {
		String command = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			while(!(command = in.readLine()).equals("exit")) {
				this.setChanged();
				this.notifyObservers(command);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}		

	