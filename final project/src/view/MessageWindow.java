package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

public class MessageWindow extends BasicWindow implements View {

	public MessageWindow(String title, int width, int height) {
		super(title, width, height);
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(1,true));
	}
	
	@Override
	public void display(String args) {
		Text text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		text.setText(args);
	}

	@Override
	public void getUserCommand() {
		this.run();
	}
}
