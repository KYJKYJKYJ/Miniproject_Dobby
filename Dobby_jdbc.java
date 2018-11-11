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
}
