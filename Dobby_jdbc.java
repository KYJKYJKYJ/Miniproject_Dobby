package miniproject;


public class Dobby_jdbc {
	//메뉴 이름 받아오기
	public String mName(int num) { // 메뉴 선택하기 위한 아이디		
		String menuName;
		Dobby_Menu_DAO mdao = Dobby_Menu_DAO.getInstance();
		menuName = mdao.namelist(num);
		
		return menuName;
	}
	
	//메뉴 가격 받아오기
	public int mPrice(int num) { // 메뉴 선택하기 위한 아이디		
		int menuPrice;
		Dobby_Menu_DAO mdao = Dobby_Menu_DAO.getInstance();
		menuPrice = mdao.pricelist(num);
		
		return menuPrice;
	}
	
	//주문 내역을 받아서 DB에 데이터 삽입
	public void oInsert(String order_id, String order_name, int order_quantity, int order_sumprice) {
		Dobby_Orders_DAO odao = Dobby_Orders_DAO.getInstance();
		Dobby_Orders_DTO odto = new Dobby_Orders_DTO(order_id, order_name, order_quantity, order_sumprice);
		odao.insert_orderlist(odto);
	}
	
	public void sRead() {

	}
	
}
