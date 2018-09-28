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
@ContextConfiguration(locations= {"file:src/main/resources/context/root-context.xml"})
public class DataSourceTest {
	
	@Inject
	DataSource ds;
	
	@Test
	public void testConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
			System.out.println("연결 성공 : " + con);
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}finally {
			try {
				if(con!=null) {
					con.close();
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

}
