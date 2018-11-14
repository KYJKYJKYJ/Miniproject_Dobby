package miniproject;

public class Dobby_Put_DTO {
   private String put_date; //입고날짜
   private String put_material;//재료명
   private int put_amount;//입고 수량
   
   public Dobby_Put_DTO() {
      
   }

   public Dobby_Put_DTO(String put_date, String put_material, int put_amount) {  
      this.put_date = put_date;
      this.put_material = put_material;
      this.put_amount = put_amount;
   }

   public String getPut_date() {
      return put_date;
   }

   public void setPut_date(String put_date) {
      this.put_date = put_date;
   }

   public String getPut_material() {
      return put_material;
   }

   public void setPut_material(String put_material) {
      this.put_material = put_material;
   }

   public int getPut_amount() {
      return put_amount;
   }

   public void setPut_amount(int put_amount) {
      this.put_amount = put_amount;
   }
   
}//end Dobby_Put_DTO