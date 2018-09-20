package net.koreate.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class MySqlConnectionTest {
	
	private static String DRIVER="com.mysql.cj.jdbc.Driver";
	private static String URL="jdbc:mysql://localhost:3306/mydata?useSSL=false&serverTimezone=Asia/Seoul";
	private static String USER="java";
	private static String PASS="java";
	
	
	@Test
	public void testConnection() {
		
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("database 연결 성공" + con);
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER 클래스를 찾을수 없다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection 연결할 수 없습니다.");
			e.printStackTrace();
		}
		
	}

}
