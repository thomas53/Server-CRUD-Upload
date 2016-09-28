package com.socket;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.dao.DaoGolongan;
import com.dao.DaoPegawai;
import com.model.ModelFile;
import com.model.ModelPegawai;

public class Pegawai extends Thread{
	private ServerSocket serverSocket;
	public final static int SOCKET_PORT = 8800;
	public final static String PATH = "c:/";
	public final static int FILE_SIZE = 10000000; 
	
	public Pegawai(int port) throws IOException{
		serverSocket = new ServerSocket(port);
	}
	
	
	
	public void run(){
		System.out.println("Waiting for client on port ...");
		
		while(true)
		{
			try
			{
				Socket server = serverSocket.accept();
				
				DataInputStream dataIn = new DataInputStream(server.getInputStream());
				String act = dataIn.readUTF();
				ObjectInputStream in = null;
				ModelPegawai pegawai = null;
				ModelFile modelFile = null;
				
				if(!act.equals("get") && !act.equals("get_gol")){
					// Recive data
					in = new ObjectInputStream(server.getInputStream());
					pegawai = (ModelPegawai) in.readObject();
				}
				DaoPegawai daoPegawai = new DaoPegawai();
				DaoGolongan daoGolongan = new DaoGolongan();
				
				// pilih aksi
				ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
				if(act.equals("ins")){
					out.writeObject(daoPegawai.insertPegawai(pegawai));
				}else if(act.equals("upd")){
					out.writeObject(daoPegawai.updatePegawai(pegawai));
				}else if(act.equals("del")){
					out.writeObject(daoPegawai.deletePegawai(pegawai.getIdpegawai()));
				}else if(act.equals("get")){
					out.writeObject(daoPegawai.ambilPegawai());
				}else if(act.equals("getId")){
					out.writeObject(daoPegawai.ambilPegawayById(pegawai.getIdpegawai()));
				}else if(act.equals("get_gol")){
					out.writeObject(daoGolongan.ambilDaftarGolongan());
				}
				
				
				server.close();
			}catch(SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			}catch(IOException e) {
				e.printStackTrace();
				break;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		int port = 8888;
		try{
			Thread t = new Pegawai(port);
			t.start();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
