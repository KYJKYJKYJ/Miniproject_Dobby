package miniproject;

import java.sql.*;
import java.util.*;

public class Dobby_Orders_DAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static Dobby_Orders_DAO odao = new Dobby_Orders_DAO();
	//싱글톤 패턴 사용
	
	private Dobby_Orders_DAO() { }
	
	public static Dobby_Orders_DAO getInstance() {
		return odao;
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
	
	public String morderlist(int id) {
		String mName = "";
		try {
			conn = init();
			stmt = conn.createStatement();
			String sql = "INSERT INTO ";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Dobby_Menu_DTO mdto = new Dobby_Menu_DTO();
				mdto.setMenu_name(rs.getString("menu_name"));
				mdto.setMenu_price(rs.getInt("menu_price"));
				mName = mdto.getMenu_name();
			}		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mName;
	}
}