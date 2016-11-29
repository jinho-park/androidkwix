package socket;
import java.net.*; 
import java.util.Vector;
import java.awt.event.*;
import java.io.*;
public class socket {
	public static void main(String[] args){
		final int PORT = 8000;
		ServerSocket sock;
		Vector<Socket> vec;
		Socket socket;
		BufferedReader br;
		
		try {
			sock = new ServerSocket(PORT);
			System.out.println("server start");
			while(true){
				socket = sock.accept();
				System.out.println("connected");
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				System.out.println(br.readLine());
				
				new ClientThread(socket).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

