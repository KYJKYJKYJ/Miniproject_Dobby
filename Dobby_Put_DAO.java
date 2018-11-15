package miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dobby_Put_DAO {
   private Connection conn;
   private Statement stmt;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private static Dobby_Put_DAO pdao = new Dobby_Put_DAO();
   //싱글톤 패턴 사용
   
   private Dobby_Put_DAO() { }
   
   public static Dobby_Put_DAO getInstance() {
      return pdao;
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
   
   //DB에 입고 내역 저장
   public void insertPutlist(Dobby_Put_DTO pdto) {
      try {
         conn = init();
         String sql = "INSERT INTO PUT " + 
        		 	  "VALUES (?, ?, ?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, pdto.getPut_date());
         pstmt.setString(2, pdto.getPut_material());
         pstmt.setInt(3, pdto.getPut_amount());
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
   }//insert_orderlist()
   
   //DB에 저장된 주문내역을 판매자 GUI에 주문내역 테이블에 출력
   public List<Dobby_Orders_DTO> readOrderlist(Dobby_Orders_DTO odto) {
      List<Dobby_Orders_DTO> aList = new ArrayList<Dobby_Orders_DTO>();
      try {
         conn = init(); // 리턴해주는 Connection 값을 conn 으로 받는다.
         stmt = conn.createStatement();
         String sql = "SELECT * FROM ORDERS ORDER BY order_num";
         rs = stmt.executeQuery(sql);
         
         while(rs.next()) { // 읽어올 레코드가 있으면 가지고 온다.
            Dobby_Orders_DTO dto = new Dobby_Orders_DTO();
            dto.setOrder_id(rs.getString("order_id"));
            dto.setOrder_name(rs.getString("order_name"));
            dto.setOrder_quantity(rs.getInt("order_quantity"));
            dto.setOrder_sumprice(rs.getInt("order_sumprice"));
            aList.add(dto);
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
   } //end Read_orderlist()
   
   //데이터베이스에 재고내역 갱신
   public void updateStocklist(String name, int amount) {
	      try {
	         conn = init();
	         String sql = "UPDATE stock " + 
	        		 	  "SET stock_amount = ((SELECT stock_amount FROM stock WHERE stock_material = ?) + ?) " +
	        		 	  "WHERE stock_material = ?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, name);
	         pstmt.setInt(2, amount);
	         pstmt.setString(3, name);
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
   } //updateStocklist()
}