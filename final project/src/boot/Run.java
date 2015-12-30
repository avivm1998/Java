package boot;

import model.MyModel;
import presenter.Presenter;
import view.CLI;

public class Run {

	public static void main(String[] args) {
		MyModel m = new MyModel();
		CLI cli = new CLI();
		Presenter p = new Presenter(m, cli);
		m.addObserver(p);
		cli.addObserver(p);
		cli.getUserCommand();
	}

}
