package net.koreate.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class DataSourceTest {

	@Inject
	DataSource ds;

	@Test
	public void ConnectTest() {
		Connection conn = null;

		try {
			conn = ds.getConnection();
			System.out.println("database 연결성공 Connection : " + conn);
		}
		catch (SQLException e) { e.printStackTrace(); System.out.println("연결 실패"); }
		finally {
			if (conn != null) {
				try { conn.close(); }
				catch (SQLException e) { e.printStackTrace(); }
			}
		}
	}
}