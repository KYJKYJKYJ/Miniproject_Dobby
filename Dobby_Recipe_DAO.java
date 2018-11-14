package miniproject;

import java.sql.*;
import java.util.*;

public class Dobby_Recipe_DAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static Dobby_Recipe_DAO rdao = new Dobby_Recipe_DAO();
	//싱글톤 패턴 사용
	
	private Dobby_Recipe_DAO() { }
	
	public static Dobby_Recipe_DAO getInstance() {
		return rdao;
	}
	
	private Connection init() throws ClassNotFoundException, SQLException {
		//1. 드라이버 로딩
		Class.forName("oracle.jdbc.OracleDriver");
		
		//2. 서버연결
		String url = "jdbc:oracle:thin://@127.0.0.1:1521:xe";
		String username = "dobby";
		String password = "a1234";
		return DriverManager.getConnection(url, username, password);
	} // end init()
	
	private void exit() throws SQLException {
		if (rs != null)
			rs.close();

		if (stmt != null)
			stmt.close();

		if (pstmt != null)
			pstmt.close();

		if (conn != null)
			conn.close();

	}// end exit()
}
