package presenter;

/**
 * Command is an input from the user to the controller. 
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public interface Command {
	/**
	 * Executes the calling command's details.
	 * 
	 * @param args [IN] The parameters for the command {@link String}.
	 * 
	 * @throws Exception In case something went wrong in the command or bad parameters.
	 */
	void doCommand(String args) throws Exception;
}
