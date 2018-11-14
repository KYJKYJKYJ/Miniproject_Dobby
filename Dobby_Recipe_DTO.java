package miniproject;

public class Dobby_Recipe_DTO {
	private int recipe_id;
	private String recipe_material;
	private int recipe_amount;
	
	private Dobby_Stock_DTO sdto;
	private Dobby_Menu_DTO mdto;
	
	public Dobby_Recipe_DTO(){
		
	}

	public Dobby_Recipe_DTO(int recipe_id, String recipe_material, int recipe_amount, Dobby_Stock_DTO sdto,
			Dobby_Menu_DTO mdto) {
		this.recipe_id = recipe_id;
		this.recipe_material = recipe_material;
		this.recipe_amount = recipe_amount;
		this.sdto = sdto;
		this.mdto = mdto;
	}

	public int getRecipe_id() {
		return recipe_id;
	}


	public Dobby_Stock_DTO getSdto() {
		return sdto;
	}

	public void setSdto(Dobby_Stock_DTO sdto) {
		this.sdto = sdto;
	}

	public Dobby_Menu_DTO getMdto() {
		return mdto;
	}

	public void setMdto(Dobby_Menu_DTO mdto) {
		this.mdto = mdto;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getRecipe_material() {
		return recipe_material;
	}

	public void setRecipe_material(String recipe_material) {
		this.recipe_material = recipe_material;
	}

	public int getRecipe_amount() {
		return recipe_amount;
	}

	public void setRecipe_amount(int recipe_amount) {
		this.recipe_amount = recipe_amount;
	}

	
	
}//end RecipeDTO
