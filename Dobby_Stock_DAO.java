package miniproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dobby_Stock_DAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static Dobby_Stock_DAO sdao = new Dobby_Stock_DAO();
	//싱글톤 패턴 사용
	
	private Dobby_Stock_DAO() { }
	
	public static Dobby_Stock_DAO getInstance() {
		return sdao;
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
	
	public List<Dobby_Stock_DTO> readStocklist() {
		List<Dobby_Stock_DTO> aList = new ArrayList<Dobby_Stock_DTO>();
		try {
			conn = init();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM stock"; 	
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Dobby_Stock_DTO sdto = new Dobby_Stock_DTO();
				sdto.setStock_material(rs.getString("stock_material"));
				sdto.setStock_amount(rs.getInt("stock_amount"));
				
				aList.add(sdto);		
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