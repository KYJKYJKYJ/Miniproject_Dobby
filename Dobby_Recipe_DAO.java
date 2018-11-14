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
	
	public List<Dobby_Recipe_DTO> searchMethod() {
		List<Dobby_Recipe_DTO> aList = new ArrayList<Dobby_Recipe_DTO>();
		try {
			conn = init();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM recipe"; 	
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Dobby_Recipe_DTO rdto = new Dobby_Recipe_DTO();
				rdto.setRecipe_id(rs.getInt("recipe_id"));
				rdto.setRecipe_chicken(rs.getInt("recipe_chicken"));
				rdto.setRecipe_cheese(rs.getInt("recipe_cheese"));
				rdto.setRecipe_sweet(rs.getInt("recipe_sweet"));
				rdto.setRecipe_soy(rs.getInt("recipe_soy"));
				rdto.setRecipe_spicy(rs.getInt("recipe_spicy"));
				rdto.setRecipe_spingonion(rs.getInt("recipe_springonion"));
				rdto.setRecipe_onion(rs.getInt("recipe_onion"));
				rdto.setRecipe_honey(rs.getInt("recipe_honey"));
				rdto.setRecipe_dduck(rs.getInt("recipe_dduck"));
				rdto.setRecipe_sacheon(rs.getInt("recipe_sacheon"));
				rdto.setRecipe_garlic(rs.getInt("recipe_garlic"));
				rdto.setRecipe_shrimp(rs.getInt("recipe_shrimp"));
				aList.add(rdto);
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
	}
}
