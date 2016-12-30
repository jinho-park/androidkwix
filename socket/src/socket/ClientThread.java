package socket;

import java.net.Socket;
import com.example.owner.practice.*;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
				String sql = "SELECT * FROM Notice";
				conn = DriverManager.getConnection(url,user,pass);
				System.out.println("success");
				java.sql.Statement st = null;
				ResultSet rs = null;
				st = conn.createStatement();
				if(string.equals("all")) {
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
				}
				else if(string.equals("new")) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
					Date day1 = null;
					Date day2 = null;
					Date present = new Date();
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						try {
							day1 = format.parse(format.format(present));
							day2 = format.parse(rs.getString("Date"));
						} catch(ParseException e) {
							e.printStackTrace();
						}
						int compare = day1.compareTo(day2);
						if(compare > 0) {
						}
						else {
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
					}
				}
				else if(string.equals("nor")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Category").contains("일반")) {
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
					}
				}
				else if(string.equals("stu")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Category").contains("학생")) {
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
					}
				}
				else if(string.equals("hak")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Category").contains("학사")) {
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
					}
				}
				else if(string.equals("vol")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Category").contains("봉사")) {
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
					}
				}
				else if(string.equals("jan")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Category").contains("장학")) {
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
					}
				}
				else if(string.equals("ent")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Category").contains("입학")) {
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
					}
				}
				else if(string.equals("sul")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Category").contains("시설")) {
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
					}
				}
				else if(string.equals("mil")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Category").contains("병무")) {
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
					}
				}
				else if(string.equals("out")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Category").contains("외부")) {
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
					}
				}
				else if(string.substring(0,4).equals("sch/")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Title").contains(string.substring(4,string.length()))) {
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
					}
				}
				else if(string.substring(0,4).equals("new/")) {
					sql = "SELECT * FROM Notice";
					rs = st.executeQuery(sql);
					
					if(st.execute(sql)) {
						rs = st.getResultSet();
					}
					while(rs.next()) {
						if(rs.getString("Title").contains(string.substring(4,string.length()))) {
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
					}
				}
				else {
					sql = "SELECT * FROM Notice WHERE Id = 1";
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
				}

				System.out.println("end");
				Notice_List data = new Notice_List();
				data.setTitle("end");
				oos.writeObject(data);
				oos.flush();
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
