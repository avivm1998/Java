package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	private OutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@Override
	public void write(int arg0) throws IOException {
		out.write(arg0);
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		int counter = 0;
		
		for(int i = 0; i < b.length - 1; i++) {
			if(b[i] == b[i + 1]) {
				counter++;
			}
			else {
				out.write(b[i]);
				out.write(counter);
				counter = 0;
			}
		}
	}

}
