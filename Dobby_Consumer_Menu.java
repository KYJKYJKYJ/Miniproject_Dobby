package miniproject;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Dobby_Consumer_Menu extends JPanel{
   //주문자이름, 음식이름, 음식가격, 음식수량, 총 가격에 대한 라벨
   JLabel order_idL, mealNameL, mealPriceL, mealAmountL, fullPriceL;
   //음식이름, 음식가격에 대한 텍스트필드
   JTextField order_idTF, mealNameTF, mealPriceTF, mealAmountTF, fullPriceTF;
   //주문 정보를 등록, 제거, 결정 버튼
   JButton registerB, removeB, decideB;
   //음식, 음료 버튼
   JButton chickenB, yangChickenB, soyChickenB, creamChickenB, greenOChickenB, 
   tokbokiChickenB, hotbChickenB, garlicChickenB, marahChickenB, shrimpChickenB, snowChickenB,
   honeyChickenB;
   
   //더하기, 빼기 버튼
   JButton plusB, minusB;
   //주문 정보를 보여줄 테이블
   JTable table;
   JScrollPane scroll;
   DefaultTableModel tableModel;
   
   //테이블 헤드 이름
   String[] cols = { "주문자이름", "음식명", "수량", "금액" };

   public Dobby_Consumer_Menu() {
      //JLabel 부분
      order_idL = new JLabel("주문자이름");
	  mealNameL = new JLabel("음식명");
      mealPriceL = new JLabel("음식가격");
      mealAmountL = new JLabel("음식수량");
      fullPriceL = new JLabel("총 금액");
      
      order_idL.setFont(new Font("맑은 고딕", 1, 15));
      mealNameL.setFont(new Font("맑은 고딕", 1, 15));
      mealPriceL.setFont(new Font("맑은 고딕", 1, 15));
      mealAmountL.setFont(new Font("맑은 고딕", 1, 15));
      fullPriceL.setFont(new Font("맑은 고딕", 1, 15));
      
      //라벨 위치 조정
      order_idL.setHorizontalAlignment(JLabel.RIGHT);
      mealNameL.setHorizontalAlignment(JLabel.CENTER);
      mealPriceL.setHorizontalAlignment(JLabel.CENTER);
      mealNameL.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
      mealPriceL.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
      mealAmountL.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
      
      fullPriceL.setHorizontalAlignment(JLabel.CENTER);
      
      //JTextField 부분
      order_idTF = new JTextField(10);
      mealNameTF = new JTextField(10);
      mealPriceTF = new JTextField(7);
      mealAmountTF = new JTextField(4);
      fullPriceTF = new JTextField(7);
      fullPriceTF.setText(Integer.toString(0)); // 초기 총 금액은 0
      
      order_idTF.setHorizontalAlignment(JTextField.CENTER);
      mealNameTF.setHorizontalAlignment(JTextField.CENTER);
      mealPriceTF.setHorizontalAlignment(JTextField.CENTER);
      mealAmountTF.setHorizontalAlignment(JTextField.CENTER);
      fullPriceTF.setHorizontalAlignment(JTextField.CENTER);
      
      mealNameTF.setEditable(false); // 음식을 고르면 값, 이름을 출력하기 위해 수정 불가 설정
      mealPriceTF.setEditable(false);
      mealAmountTF.setEditable(false);
      fullPriceTF.setEditable(false);
      
      
      //JButton 부분
      registerB = new JButton("주문 등록");
      removeB = new JButton("주문 제거");
      decideB = new JButton("주문 결정");
      
      //버튼에 등록할 이미지 크기 조절 (내부 클래스 이용)
      class Resizing {
    	  public ImageIcon Icon(String str) {
    		  ImageIcon Icon = new ImageIcon(str); // 이미지 아이콘 받음
    		  Image getImg = Icon.getImage(); // 아이콘에서 이미지 추출
    		  Image imgResize = getImg.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // 40*40 사이즈로 리사이징
    		  ImageIcon reIcon = new ImageIcon(imgResize); // 리사이징한 이미지 아이콘으로 만듬
    		  
    		  return reIcon;
    	  }
      } // end Resizing
      
      chickenB = new JButton(new Resizing().Icon("src/miniproject/Chicken.jpg")); // 후라이드치킨
      chickenB.setName("후라이드치킨");
      soyChickenB = new JButton(new Resizing().Icon("src/miniproject/SoyChicken.png")); // 간장치킨
      soyChickenB.setName("간장치킨");
      yangChickenB = new JButton(new Resizing().Icon("src/miniproject/YangyumChicken.png")); // 양념치킨
      yangChickenB.setName("양념치킨");
      snowChickenB = new JButton(new Resizing().Icon("src/miniproject/SnowingChicken.jpg")); // 스노윙치킨
      snowChickenB.setName("스노윙치킨");
      hotbChickenB = new JButton(new Resizing().Icon("src/miniproject/HotblingChicken.jpg")); // 핫블링치킨
      hotbChickenB.setName("핫블링치킨");
      greenOChickenB = new JButton(new Resizing().Icon("src/miniproject/GreenOnionChicken.jpg")); // 파닭치킨
      greenOChickenB.setName("파닭치킨");
      creamChickenB = new JButton(new Resizing().Icon("src/miniproject/CreamianChicken.jpg")); // 크리미언치킨
      creamChickenB.setName("크리미언치킨");
      honeyChickenB = new JButton(new Resizing().Icon("src/miniproject/HoneyChicken.png")); // 허니멜로치킨
      honeyChickenB.setName("허니멜로치킨");
      tokbokiChickenB = new JButton(new Resizing().Icon("src/miniproject/TokbokiChicken.png")); // 쌀떡볶이치킨
      tokbokiChickenB.setName("쌀떡볶이치킨");
      marahChickenB = new JButton(new Resizing().Icon("src/miniproject/MarahotChicken.png")); // 마라핫치킨
      marahChickenB.setName("마라핫치킨");
      garlicChickenB = new JButton(new Resizing().Icon("src/miniproject/GarlicChicken.png")); // 갈릭치킨
      garlicChickenB.setName("갈릭치킨");
      shrimpChickenB = new JButton(new Resizing().Icon("src/miniproject/ShrimpChicken.png")); // 새우치킨
      shrimpChickenB.setName("새우치킨");
      
      plusB = new JButton("+");
      minusB = new JButton("-");
      
      chickenB.setToolTipText("후라이드치킨");
      yangChickenB.setToolTipText("양념치킨");
      soyChickenB.setToolTipText("간장치킨");
      snowChickenB.setToolTipText("스노윙치킨");
      hotbChickenB.setToolTipText("핫블링치킨");
      greenOChickenB.setToolTipText("파닭");
      creamChickenB.setToolTipText("크리미언치킨");
      honeyChickenB.setToolTipText("허니멜로치킨");
      tokbokiChickenB.setToolTipText("쌀떡볶이치킨");
      marahChickenB.setToolTipText("마라핫치킨");
      garlicChickenB.setToolTipText("갈릭치킨");
      shrimpChickenB.setToolTipText("새우치킨");
      
      
      //Jtable 부분
      //Innerclass 사용하여 셀을 건드리지 못하도록 설정
      tableModel = new DefaultTableModel(cols, 0) {
         public boolean isCellEditable(int row, int col) {
            return false;
         }
      };
      table = new JTable(tableModel);
      scroll = new JScrollPane(table);
 
      table.getColumnModel().getColumn(0).setPreferredWidth(50); // 주문자이름
      table.getColumnModel().getColumn(1).setPreferredWidth(50); // 음식명
      table.getColumnModel().getColumn(2).setPreferredWidth(10); // 음식 수량
      table.getColumnModel().getColumn(3).setPreferredWidth(50); // 총 금액
      table.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼 이동 불가 설정
      table.setRowSelectionAllowed(false);
      //특정 셀이 선택되었을 때, 동일한 행에 있는 나머지 셀들이 전부 선택되는 기본 동작 불가 설정
      table.setColumnSelectionAllowed(false);
      //특정 셀이 선택되었을 때, 동일한 열에 있는 나머지 셀들이 전부 선택되는 기본 동작 불가 설정
      
      //배치하기
      //주문자 이름, 메뉴이름, 메뉴가격, 메뉴수량 라벨 패널
      JPanel mlabelPanel = new JPanel(new GridLayout(1, 4));
      mlabelPanel.add(order_idL);
      mlabelPanel.add(mealNameL);
      mlabelPanel.add(mealPriceL);
      mlabelPanel.add(mealAmountL);
      
      //수량 조절을 위한 플러스, 마이너스 버튼 패널
      JPanel pmPanel = new JPanel(new GridLayout(2, 1));
      pmPanel.add(plusB);
      pmPanel.add(minusB);
      
      //음식에 대한 정보를 출력할 텍스트 필드가 담긴 패널
      JPanel minfoPanel = new JPanel(new FlowLayout());
      minfoPanel.add(order_idTF);
      minfoPanel.add(mealNameTF);
      minfoPanel.add(mealPriceTF);
      minfoPanel.add(mealAmountTF);
      minfoPanel.add(pmPanel);
      
      //총 가격에 대한 패널
      JPanel mfullPricePanel= new JPanel(new FlowLayout());
      mfullPricePanel.add(fullPriceL);
      mfullPricePanel.add(fullPriceTF);
      mfullPricePanel.setBorder(new TitledBorder("총 가격"));
      
      //음식 고르기 버튼
      JPanel meal_buttonPanel = new JPanel(new GridLayout(3, 4));
      meal_buttonPanel.add(chickenB);
      meal_buttonPanel.add(soyChickenB);
      meal_buttonPanel.add(yangChickenB);
      meal_buttonPanel.add(snowChickenB);
      meal_buttonPanel.add(hotbChickenB);
      meal_buttonPanel.add(greenOChickenB);
      meal_buttonPanel.add(creamChickenB);
      meal_buttonPanel.add(honeyChickenB);
      meal_buttonPanel.add(tokbokiChickenB);
      meal_buttonPanel.add(marahChickenB);
      meal_buttonPanel.add(garlicChickenB);
      meal_buttonPanel.add(shrimpChickenB);
      meal_buttonPanel.setBorder(new TitledBorder("음료 & 음식"));
      
      //주문 등록, 주문 제거, 주문 결정 버튼 패널
      JPanel buybutton_Panel = new JPanel(new FlowLayout());
      buybutton_Panel.add(registerB);
      buybutton_Panel.add(removeB);
      buybutton_Panel.add(decideB);
      
      //음식 정보, 라벨의 패널들을 묶은 패널
      JPanel mlabel_info_Panel = new JPanel(new GridLayout(4, 1));
      mlabel_info_Panel.add(mlabelPanel);
      mlabel_info_Panel.add(minfoPanel);
      mlabel_info_Panel.add(buybutton_Panel);
      mlabel_info_Panel.add(mfullPricePanel);
      
      //음식을 고르는 버튼 패널과 음식 정보가 담길 패널이 담긴 종합 패널
      JPanel mealPanel = new JPanel(new GridLayout(2, 1));
      mealPanel.add(meal_buttonPanel);
      mealPanel.add(mlabel_info_Panel);
       
      //총 배치
      setLayout(new GridLayout(1, 2));
      add(mealPanel);
      add(scroll); // 테이블
   }      
}//end Restaurant_Menu