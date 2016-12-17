package socket;

import java.net.Socket;
import com.example.owner.practice.*;
import java.io.*;
import java.sql.*;

public class ClientThread extends Thread{
	private Socket sock;
	private ObjectOutputStream oos;
	private BufferedReader br;
	private String string;
	static String url = "jdbc:mysql://localhost/Notice?useSSL=false";		//database site for MySQL
	static String user = "root";												//MySQL access username
	static String pass = "jinsejin2";	
	
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
			try {
				Connection conn;
				String sql;
				conn = DriverManager.getConnection(url,user,pass);
				System.out.println("success");
				java.sql.Statement st = null;
				ResultSet rs = null;
				st = conn.createStatement();
				sql = "SELECT * FROM Notice";
				rs = st.executeQuery(sql);
				
				if(st.execute(sql)) {
					rs = st.getResultSet();
				}
				
				while(rs.next()) {
					Notice_List data = new Notice_List();
					String str = rs.getString("Category");
					if(str.equals("")) {
						data.setCat("[없음]");
					}
					else {
						data.setCat(str);
					}
					str = rs.getString("Title");
					data.setTitle(str);
					str = rs.getString("Date");
					data.setDate(str);
					str = rs.getString("Hyperlink");
					data.setUrls(str);
					
					oos.writeObject(data);
					oos.flush();
				}
				rs.close();
				st.close();
				conn.close();
			} catch(SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
			}
			

			
			
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
