package boot;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import presenter.Properties;

public class XMLMaker {
	
	public static void main(String[] args) throws Exception {

		Properties settings = new Properties();
		settings.setThreadPoolSize(10);
		settings.setSearchingAlogrithm("bfs");

		FileOutputStream fos = new FileOutputStream("Properties.xml");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		XMLEncoder xmlEncoder = new XMLEncoder(bos);
		xmlEncoder.writeObject(settings);
		xmlEncoder.close();
			
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("Properties.xml")));
		Properties properties = new Properties();
		properties = (Properties)decoder.readObject();
		System.out.println(properties.getThreadPoolSize());
		System.out.println(properties.getSearchingAlogrithm());
		decoder.close();

	}
}