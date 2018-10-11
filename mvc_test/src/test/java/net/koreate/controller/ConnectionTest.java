package net.koreate.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionTest {
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/mydata?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul";
	private static String USER = "java";
	private static String PASS = "java";
	
	@Test
	public void test() {
		Connection conn = null;
		PreparedStatement pstmt = null; // 프리페어드 스테이트먼트
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("database 연결 성공 : " + conn);
			
			String sql = "INSERT INTO test_board(title, content, writer)"
					+ "VALUES(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "안녕1");
			pstmt.setString(2, "안녕2");
			pstmt.setString(3, "안녕3");
			int result = pstmt.executeUpdate();
			
			if(result != 0) { System.out.println("성공"); }
			else { System.out.println("실패"); }
		}
		catch (ClassNotFoundException e) { System.out.println("DRIVER 클래스를 찾을수 없습니다."); }
		catch (SQLException e) { System.out.println("Connection 연결할 수 없거나 Query문이 잘못 됬습니다."); }
		finally {
			try { if(conn != null) { conn.close(); } if(pstmt != null) { pstmt.close(); } }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
}
