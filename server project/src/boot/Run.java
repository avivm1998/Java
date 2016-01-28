package boot;

import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import view.MyServer;

public class Run {

	public static void main(String[] args) {
		MyModel m = new MyModel();
		MyServer server = new MyServer();
		Presenter p = new Presenter(m, server);
		m.addObserver(p);
		server.handler.addObserver(p);
		server.addObserver(p);
		server.getUserCommand();
	}

}
