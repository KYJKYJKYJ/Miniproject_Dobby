package miniproject;


public class Dobby_Stock_DTO {
	private int recipe_id;
	private String stock_material;
	private int stock_amount;

	public Dobby_Stock_DTO() {
		
	}
	
	public Dobby_Stock_DTO(int recipe_id, String stock_material, int stock_amount) {
		this.recipe_id = recipe_id;
		this.stock_material = stock_material;
		this.stock_amount = stock_amount;
	}

	public int getRecipe_id() {
		return recipe_id;
	}


	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
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
