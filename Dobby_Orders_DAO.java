package miniproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dobby_Orders_DAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static Dobby_Orders_DAO odao = new Dobby_Orders_DAO();
	// 싱글톤 패턴 사용

	private Dobby_Orders_DAO() { }

	public static Dobby_Orders_DAO getInstance() {
		return odao;
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

	public void insert_Orderlist(Dobby_Orders_DTO odto) {
		try {
			conn = init();
			String sql = "INSERT INTO orders " + "VALUES (order_sq.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, odto.getOrder_id());
			pstmt.setString(2, odto.getOrder_name());
			pstmt.setInt(3, odto.getOrder_quantity());
			pstmt.setInt(4, odto.getOrder_sumprice());
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
	} // end insert_Orderlist()

	public List<Dobby_Orders_DTO> read_Orderlist() {
		List<Dobby_Orders_DTO> aList = new ArrayList<Dobby_Orders_DTO>();
		try {
			conn = init();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM orders ORDER BY order_num";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Dobby_Orders_DTO odto = new Dobby_Orders_DTO();
				odto.setOrder_id(rs.getString("order_id"));
				odto.setOrder_name(rs.getString("order_name"));
				odto.setOrder_quantity(rs.getInt("order_quantity"));
				odto.setOrder_sumprice(rs.getInt("order_sumprice"));

				aList.add(odto);
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
		return aList;
	} // end read_Orderlist()

	public void delete_Orderlist() {
		try {
			conn = init();
			String sql = "DELETE FROM orders";
			pstmt = conn.prepareStatement(sql);
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
	}// end deleteMethod
} // end class