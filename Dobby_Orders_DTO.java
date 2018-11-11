package miniproject;

public class Dobby_Orders_DTO {
	private String order_id; //주문 번호
	private int menu_id; //메뉴 번호
	private int order_quantity; //주문 수량
	private int order_sumprice; //메뉴 총 가격
	
	public Dobby_Orders_DTO() {
		
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	public int getOrder_sumprice() {
		return order_sumprice;
	}

	public void setOrder_sumprice(int order_sumprice) {
		this.order_sumprice = order_sumprice;
	}
	
	
}//end OrdersDTO
