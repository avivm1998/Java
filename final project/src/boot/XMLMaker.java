package boot;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import presenter.Properties;

/**
 * XMLMaker is a class that is in charge of starting up and saving an XML file. 
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class XMLMaker {
	
	public static void main(String[] args) throws Exception {

		Properties settings = new Properties();
		settings.setThreadPoolSize(10);
		settings.setSearchingAlogrithm("bfs");
		settings.setCharacter(2);
		settings.setPort(2000);
		settings.setIp("127.0.0.1");

		FileOutputStream fos = new FileOutputStream("DefulteDeadpoolProperties.xml");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		XMLEncoder xmlEncoder = new XMLEncoder(bos);
		xmlEncoder.writeObject(settings);
		xmlEncoder.close();
			
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("Properties.xml")));
		Properties properties = new Properties();
		properties = (Properties)decoder.readObject();
		System.out.println(properties.getThreadPoolSize());
		System.out.println(properties.getSearchingAlogrithm());
		System.out.println(properties.getCharacter());
		System.out.println(properties.getPort());
		System.out.println(properties.getIp());
		decoder.close();

	}
}