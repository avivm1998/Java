package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MyDecompressorInputStream extends InputStream {
	
	private InputStream in;
	
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}
	
	@Override
	public int read() throws IOException {
		return in.read();
	}
	
	@Override
	public int read(byte[] b) throws IOException {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(in)));
		int currentIndex = 0;
		int value = 0;
		int amount = 0;
		
		while(scan.hasNextByte()) {
			value = scan.nextByte();
			amount = scan.nextInt();
			for(int i = currentIndex; i < amount; i++) {
				b[i] = (byte)value;
			}
			
			currentIndex += amount - 1;
		}
		return -1;
	}

}
