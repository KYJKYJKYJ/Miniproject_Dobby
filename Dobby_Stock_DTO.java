package miniproject;

import java.sql.Date;

public class Dobby_Stock_DTO {
	private int recipe_id;
	private Date put_date;
	private String stock_material;
	private int stock_amount;

	public Dobby_Stock_DTO() {
		
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public Date getPut_date() {
		return put_date;
	}

	public void setPut_date(Date put_date) {
		this.put_date = put_date;
	}

	public String getStock_material() {
		return stock_material;
	}

	public void setStock_material(String stock_material) {
		this.stock_material = stock_material;
	}

	public int getStock_amount() {
		return stock_amount;
	}

	public void setStock_amount(int stock_amount) {
		this.stock_amount = stock_amount;
	}

	
	
	
}//end StockDTO
