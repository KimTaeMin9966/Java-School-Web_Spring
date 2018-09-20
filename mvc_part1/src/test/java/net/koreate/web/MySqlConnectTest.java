package net.koreate.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySqlConnectTest {
	
	//private static final String DRIVER ="com.mysql.jdbc.Driver";
	//private static final String URL ="jdbc:mysql://localhost:3306/mydata";
	private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
	//allowPublicKeyRetrieval=true&
	private static final String URL ="jdbc:mysql://localhost:3306/mydata?useSSL=false&serverTimezone=Asia/Seoul";
	private static final String USER="java";
	private static final String PASS="java";
	
	
	@Test
	public void testConnection() throws ClassNotFoundException {
		Class.forName(DRIVER);		
		try(Connection con = DriverManager.getConnection(URL,USER,PASS)){
			System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
