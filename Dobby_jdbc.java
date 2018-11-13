package miniproject;

public class Dobby_jdbc {
	
	public String mName(int num) { // 메뉴 선택하기 위한 아이디		
		String menuName;
		Dobby_Menu_DAO mdao = Dobby_Menu_DAO.getInstance();
		menuName = mdao.namelist(num);
		
		return menuName;
	}
	
	public int mPrice(int num) { // 메뉴 선택하기 위한 아이디		
		int menuPrice;
		Dobby_Menu_DAO mdao = Dobby_Menu_DAO.getInstance();
		menuPrice = mdao.pricelist(num);
		
		return menuPrice;
	}
	
	public void oInsert(String order_id, String order_name, int order_quantity, int order_sumprice) {
		Dobby_Orders_DAO odao = Dobby_Orders_DAO.getInstance();
		Dobby_Orders_DTO odto = new Dobby_Orders_DTO(order_id, order_name, order_quantity, order_sumprice);
		odao.insert_orderlist(odto);
	}
	
}
