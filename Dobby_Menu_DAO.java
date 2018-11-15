package miniproject;

import java.sql.*;
import java.util.*;

public class Dobby_Menu_DAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static Dobby_Menu_DAO mdao = new Dobby_Menu_DAO();
	//싱글톤 패턴 사용
	
	public Dobby_Menu_DAO() { }
	
	public static Dobby_Menu_DAO getInstance() {
		return mdao;
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
	
	public String menuName(int id) {
		String mName = "";
		try {
			conn = init();
			stmt = conn.createStatement();
			String sql = "SELECT menu_name FROM menu WHERE menu_id = " + id + " "; 	
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Dobby_Menu_DTO mdto = new Dobby_Menu_DTO();
				mdto.setMenu_name(rs.getString("menu_name"));
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
	} // end menuName()
	
	public int menuPrice(int id) {
		int mPrice = 0;
		try {
			conn = init();
			stmt = conn.createStatement();
			String sql = "SELECT menu_price FROM menu WHERE menu_id = " + id + " "; 	
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Dobby_Menu_DTO mdto = new Dobby_Menu_DTO();
				mdto.setMenu_price(rs.getInt("menu_price"));
				mPrice = mdto.getMenu_price();
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
		return mPrice;
	} // end menuPrice()
	
	public int menuId(String name) {
		int mId = 0;
		try {
			conn = init();
			stmt = conn.createStatement();
			String sql = "SELECT menu_id FROM menu WHERE menu_name = '" + name + "' "; 	
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Dobby_Menu_DTO mdto = new Dobby_Menu_DTO();
				mdto.setMenu_id(rs.getInt("menu_id"));
				mId = mdto.getMenu_id();
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
		return mId;
	} // end menuId()
}
