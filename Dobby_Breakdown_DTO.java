package miniproject;

public class Dobby_Breakdown_DTO {
	private String order_id; // 주문번호
	private String breakdown_name; // 주문내역 - 메뉴명
	private int breakdown_price; // 주문내역 - 메뉴가격
	private int breakdown_quantity; // 메뉴수량

	public Dobby_Breakdown_DTO() {

	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getBreakdown_name() {
		return breakdown_name;
	}

	public void setBreakdown_name(String breakdown_name) {
		this.breakdown_name = breakdown_name;
	}

	public int getBreakdown_price() {
		return breakdown_price;
	}

	public void setBreakdown_price(int breakdown_price) {
		this.breakdown_price = breakdown_price;
	}

	public int getBreakdown_quantity() {
		return breakdown_quantity;
	}

	public void setBreakdown_quantity(int breakdown_quantity) {
		this.breakdown_quantity = breakdown_quantity;
	}
	
	
}