package miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dobby_Totals_DAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Dobby_Totals_DAO tdao = new Dobby_Totals_DAO();
	// 싱글톤 패턴 사용

	private Dobby_Totals_DAO() {
	}

	public static Dobby_Totals_DAO getInstance() {
		return tdao;
	}

	private Connection init() throws ClassNotFoundException, SQLException {
		// 1. 드라이버 로딩
		Class.forName("oracle.jdbc.OracleDriver");

		// 2. 서버연결
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

	public void update_Totallist(int totalPrice) {
		try {
			conn = init();
			String sql = "UPDATE totals SET total_price = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, totalPrice);
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
	}// update_Totallist()

	public int read_Totallist() {
		int tPrice = 0;
		try {
			conn = init();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM totals";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Dobby_Totals_DTO tdto = new Dobby_Totals_DTO();
				tdto.setTotal_totalprice(rs.getInt("total_price"));
				tPrice = tdto.getTotal_totalprice();
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
		return tPrice;
	}
}// end Dobby_Totals_DAO()