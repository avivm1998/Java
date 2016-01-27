package view;

import java.lang.reflect.Field;

import org.eclipse.swt.layout.GridLayout;

public class AutomaticClassFormMaker<T> extends BasicWindow{
	
	T t;
	
	public AutomaticClassFormMaker(String title, int width, int length, T t) {
		super(title, width, length);
		this.t = t;
	}
	
	public void createShell() {
		
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2,false));
		
		Field[] f = t.getClass().getFields();
		
		for(int i = 0; i < f.length; i++) {
			//Label i = new Label(shell, SWT.BORDER);
		}
	}
}
