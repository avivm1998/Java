package boot;

import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import view.CLI;
import view.MazeWindow;

public class Run {

	public static void main(String[] args) {
		System.out.println("Client side");
		MyModel m = new MyModel();
		//CLI cli = new CLI();
		MazeWindow v = new MazeWindow("SWEET GAME!", 1000, 500);
		Presenter p = new Presenter(m, v);
		m.addObserver(p);
		v.addObserver(p);
		v.getUserCommand();
		//cli.getUserCommand();
	}

}
