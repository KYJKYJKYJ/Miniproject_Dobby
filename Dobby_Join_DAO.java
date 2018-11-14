package miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dobby_Join_DAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Dobby_Join_DAO dao = new Dobby_Join_DAO();

	private Dobby_Join_DAO() {

	}

	public static Dobby_Join_DAO getInstance() {
		return dao;
	}

	private Connection init() throws ClassNotFoundException, SQLException {
		// 1. 드라이버 로딩
		Class.forName("oracle.jdbc.OracleDriver");

		// 2. 서버연결
		String url = "jdbc:oracle:thin://@127.0.0.1:1521:xe";
		String username = "dobby";
		String password = "a1234";
		return DriverManager.getConnection(url, username, password);
	}// end init()

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

	public void minusStocklist(int id, int amount, String material) {
		try {
			conn = init();

			String sql = "UPDATE stock SET stock_amount = (SELECT stock_amount FROM stock s, recipe r, menu m WHERE r.recipe_id = m.recipe_id AND m.recipe_id = s.recipe_id AND m.recipe_id = ?) " + 
						 "- ? " + 
						 "WHERE stock_material = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, amount);
			pstmt.setString(3, material);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}// end listMethod()
}