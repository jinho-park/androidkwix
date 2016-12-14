package socket;

import java.net.Socket;
import com.example.owner.practice.*;
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
			
			string=br.readLine();
			System.out.println(string);

			//start
			Notice_List data = new Notice_List();
			data.setCat("all");
			data.setDate("12/31");
			data.setTitle("birthday");
			data.setUrls("www.naver.com");
			oos.writeObject(data);
			oos.flush();
			//end
			
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
