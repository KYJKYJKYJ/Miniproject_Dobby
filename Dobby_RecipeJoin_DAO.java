package miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dobby_RecipeJoin_DAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static Dobby_RecipeJoin_DAO jdao = new Dobby_RecipeJoin_DAO();

	private Dobby_RecipeJoin_DAO() {

	}

	public static Dobby_RecipeJoin_DAO getInstance() {
		return jdao;
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
		// 주문이 완료되면 재고에서 자동으로 제거되는 기능 수행
		try {
			conn = init();

			String sql = "UPDATE stock SET stock_amount = " +
						 "(SELECT stock_amount FROM stock s, recipe r, menu m " +
						 "WHERE r.recipe_id = m.recipe_id AND m.recipe_id = s.recipe_id AND m.recipe_id = ?) " + 
						 "- ? " + 
						 "WHERE stock_material = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); // 레시피 아이디
			pstmt.setInt(2, amount); // 기존 재고 수량
			pstmt.setString(3, material); // 레시피에서 소모되는 재료
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
	}// end minusStocklist()
	
	public int ridList(String name) { // 레시피 아이디를 받아오는 기능 수행
		int rId = 0;
		try {
			conn = init();
			stmt = conn.createStatement();
			String sql = "SELECT recipe_id FROM menu WHERE menu_name = '" + name + "' "; 	
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Dobby_Recipe_DTO rdto = new Dobby_Recipe_DTO();
				rdto.setRecipe_id(rs.getInt("recipe_id"));
				rId = rdto.getRecipe_id();
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
		return rId;
	} // end ridList()
	
	public String rmList(int id) { // 레시피 재료를 받아오는 역할 수행
		String rmName = "";
		try {
			conn = init();
			stmt = conn.createStatement();
			String sql = "SELECT recipe_material FROM recipe WHERE recipe_id = " + id + " "; 	
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Dobby_Recipe_DTO rdto = new Dobby_Recipe_DTO();
				rdto.setRecipe_material(rs.getString("recipe_material"));
				rmName = rdto.getRecipe_material();
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
		return rmName;
	} // end rmList()
} // end class