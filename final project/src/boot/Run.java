package boot;

import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import view.CLI;
import view.MazeWindow;

public class Run {

	public static void main(String[] args) {
		MyModel m = new MyModel();
		//CLI cli = new CLI();
		MazeWindow v = new MazeWindow("Ayal Faggot", 1000, 500);
		Presenter p = new Presenter(m, v);
		//Properties settings = new Properties();
		m.addObserver(p);
		v.addObserver(p);
		v.getUserCommand();
		//cli.getUserCommand();
	}

}
