package io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * MyCompressorOutputStream is an OutputStream which writes the maze in a compressed way.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class MyCompressorOutputStream extends OutputStream {
	private OutputStream out;
	
	/**
	 * Constructor initializing the type of output.
	 * 
	 * @param out [IN] The type of output {@link OutputStream}.
	 */
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@Override
	public void write(int arg0) throws IOException {
		out.write(arg0);
	}
	
	/**
	 * Writes with the given output type while compressing the maze from the given byte array.
	 * 
	 * @param b [IN] The array to be written from {@link Byte[]}.
	 * 
	 * @throws IOException [THROWN] When an IO exception occurred {@link IOException}.
	 */
	@Override
	public void write(byte[] b) throws IOException {
		int counter = 1;
		
		for (int i = 0; i < 9; i++) {
			out.write(b[i]);
		}
		
		for(int i = 9; i < b.length - 1; i++) {
			if(b[i] == b[i + 1]) {
				counter++;
			}
			else {
				out.write(b[i]);
				out.write(counter);
				counter = 1;
			}
		}
		out.write(b[b.length - 1]);
		out.write(counter);
	}
}
