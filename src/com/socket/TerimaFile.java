package com.socket;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TerimaFile {
	public final static int SOCKET_PORT = 8800;
	public final static String PATH = "c:/";
	public final static int FILE_SIZE = 10000000; 
	
	public static void main(String[] args) throws IOException {
		int bytesRead;
	    int current = 0;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    try {
	        servsock = new ServerSocket(SOCKET_PORT);
	        while (true) {
	          System.out.println("Waiting...");
	          try {
	            sock = servsock.accept();
	            System.out.println("Accepted connection : " + sock);
	            
	            // receive file
	            byte [] mybytearray  = new byte [FILE_SIZE];
		  	    InputStream is = sock.getInputStream();
		  	    DataInputStream in = new DataInputStream(is);
		  	    String FILE_TO_RECEIVED = PATH + in.readUTF();
		  	    
		  	    // saving File		  	    
		  	    fos = new FileOutputStream(FILE_TO_RECEIVED);
		  	    bos = new BufferedOutputStream(fos);
		  	    bytesRead = is.read(mybytearray,0,mybytearray.length);
		  	    current = bytesRead;
	
				do {
				   bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
				   if(bytesRead >= 0) current += bytesRead;
				} while(bytesRead > -1);
	
		  	    bos.write(mybytearray, 0 , current);
		  	    bos.flush();
		  	    System.out.println("File " + FILE_TO_RECEIVED
		  	          + " downloaded (" + current + " bytes read)");
	          }
	          finally {
	            if (bos != null) bos.close();
	            if (fos != null) fos.close();
	            if (sock!=null) sock.close();
	          }
	        }
	      }
	      finally {
	        if (servsock != null) servsock.close();
	      }
	}
}
