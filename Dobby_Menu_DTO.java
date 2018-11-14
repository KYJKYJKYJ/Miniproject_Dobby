package miniproject;

public class Dobby_Menu_DTO {
	private int menu_id;
	private int recipe_id;
	private String menu_name;
	private int menu_price;
	
	public Dobby_Menu_DTO() {
		
	}

	public Dobby_Menu_DTO(int menu_id, int recipe_id, String menu_name, int menu_price) {
		this.menu_id = menu_id;
		this.recipe_id = recipe_id;
		this.menu_name = menu_name;
		this.menu_price = menu_price;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getMenu_price() {
		return menu_price;
	}

	public void setMenu_price(int menu_price) {
		this.menu_price = menu_price;
	}
	
	
	
	
}//end MenuDTO
