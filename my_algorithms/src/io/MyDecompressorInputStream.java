package io;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyDecompressorInputStream is an InputStream which reads the compressed maze and decompresses it.
 * 
 * @author Aviv Moran & Ayal Naim
 *
 */
public class MyDecompressorInputStream extends InputStream {
	
	private InputStream in;
	
	/**
	 * Constructor initializing the type of input.
	 * 
	 * @param in [IN] The type of input {@link InputStream}.
	 */
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}
	
	@Override
	public int read() throws IOException {
		return in.read();
	}
	
	/**
	 * Reads with the given input type while decompressing the maze into the given byte array.
	 * 
	 * @param b [IN] The array to be read into {@link Byte[]}.
	 * 
	 * @throws IOException [THROWN] When an IO exception occurred {@link IOException}.
	 */
	@Override
	public int read(byte[] b) throws IOException {
		
		int currentIndex = 9;
		int value = 0;
		int amount = 0;
		
		for (int i = 0; i < 9; i++) {
			b[i] = (byte) in.read();
		}
		
		while((value = in.read()) != -1) { 
			amount = in.read();
			
			for (int i = 0; i < amount; i++) {
				b[i + currentIndex] = (byte)value;
			}
			
			currentIndex += amount;
		}
		
		return -1;
	}

}
