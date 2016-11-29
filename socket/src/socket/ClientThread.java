package socket;

import java.net.Socket;
import java.util.Vector;
import java.io.*;
import java.sql.*;

public class ClientThread extends Thread{
	private Socket sock;
	private ObjectOutputStream oos;
	private BufferedReader br;
	private String string;
	
	public ClientThread(Socket sock){
		this.sock=sock;
	}
	
	@Override
	public void run(){
		try {
			oos= new ObjectOutputStream(sock.getOutputStream());
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			System.out.println("thread start");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
