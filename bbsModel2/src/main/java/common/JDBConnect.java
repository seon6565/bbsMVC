package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBConnect {
	public Connection conn;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	public JDBConnect() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");	
			String url = "jdbc:mariadb://localhost:3306/maria";
			String dbId = "root";
			String dbPwd = "1234";
			conn = DriverManager.getConnection(url,dbId,dbPwd);
			System.out.println("DB 연결 성공 기본 생성자");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public JDBConnect(String driver, String url, String dbId, String dbPwd) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,dbId,dbPwd);
			System.out.println("DB 연결 성공 사용자 정의 생성자1");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public JDBConnect(ServletContext application) {
		try {
			String driver = application.getInitParameter("Driver");
			String url = application.getInitParameter("URL");
			String dbId = application.getInitParameter("dbId");
			String dbPwd = application.getInitParameter("dbPwd");
			Class.forName(driver);
			conn = DriverManager.getConnection(url,dbId,dbPwd);
			System.out.println("DB 연결 성공 사용자 정의 생성자2");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt !=null) stmt.close();
			if(psmt !=null) psmt.close();
			if(conn!=null) conn.close();
			System.out.println("JDBC 연결 자원 해지 성공");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
