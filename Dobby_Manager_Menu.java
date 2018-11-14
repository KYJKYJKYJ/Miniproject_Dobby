package miniproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Dobby_Manager_Menu extends JPanel {
   // 주문내역, 재고 수량, 입고 , 총매출금액 라벨
   JLabel orderHistoryL, stockL, putL, totalSalesCountL;
   // 주문내역 , 재고 수량, 입고 테이블
   JTable orderHistoryT, stockT, putT;
   // 총매출금액 표시 텍스트필드
   JTextField totalSalesCountTF;
   //scroll1 = 주문 내역, scroll2 = 재고 , scroll3 = 입고, scroll4 = 주문 내역 TA
   JScrollPane scroll, scroll2, scroll3 ,scroll4;
   
   DefaultTableModel ohTableModel, stTableModel, putTableModel;
   
   // 주문내역 메세지 텍스트아레아
   JTextArea orderHistoryTA;
   
   // 초기화 버튼
   JButton resetB;

   String[] cols = { "주문자이름", "음식명", "수량", "금액"};
   String[] cols2 = { "재료명", "수량" };
   String[] cols3 = { "입고 날짜", "재료명", "수량" };

   public Dobby_Manager_Menu() {
      
      // JLabel 부분
      orderHistoryL = new JLabel("주문 내역");
      stockL = new JLabel("재고");
      putL = new JLabel("입고");
      totalSalesCountL = new JLabel("총매출금액");
      
      // 라벨 위치 조정
      orderHistoryL.setHorizontalAlignment(JLabel.CENTER);
      stockL.setHorizontalAlignment(JLabel.CENTER);
      putL.setHorizontalAlignment(JLabel.CENTER);

      // JTextField 부분
      totalSalesCountTF = new JTextField(20);

      // JTextArea 부분
      orderHistoryTA = new JTextArea();
      orderHistoryTA.setBorder(new TitledBorder("주문 확인"));
      orderHistoryTA.setEditable(false);
      scroll4 = new JScrollPane(orderHistoryTA);

      // Jbutton 부분
      resetB = new JButton("초기화");

      // JTable 부분 (입고)
      // Innerclass 사용하여 셀을 건드리지 못하도록 설정
      ohTableModel = new DefaultTableModel(cols, 0) {
         public boolean isCellEditable(int row, int col) {
            return false;
         }
      };
      orderHistoryT = new JTable(ohTableModel);
      scroll = new JScrollPane(orderHistoryT);
      orderHistoryT.setRowHeight(20);// 라인의 높이
      orderHistoryT.getColumnModel().getColumn(0).setPreferredWidth(30); // 주문자 이름
      orderHistoryT.getColumnModel().getColumn(1).setPreferredWidth(40); // 음식명
      orderHistoryT.getColumnModel().getColumn(2).setPreferredWidth(30); // 수량
      orderHistoryT.getColumnModel().getColumn(3).setPreferredWidth(30); // 금액
      
      orderHistoryT.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼 이동 불가 설정
      orderHistoryT.setRowSelectionAllowed(false);
      // 특정 셀이 선택되었을 때, 동일한 행에 있는 나머지 셀들이 전부 선택되는 기본 동작 불가 설정
      orderHistoryT.setColumnSelectionAllowed(false);
      // 특정 셀이 선택되었을 때, 동일한 열에 있는 나머지 셀들이 전부 선택되는 기본 동작 불가 설정

      // JTable 부분 (재고)
      stTableModel = new DefaultTableModel(cols2, 0) {
         public boolean isCellEditable(int row, int col) {
            return false;
         }
      };

      stockT = new JTable(stTableModel);
      scroll2 = new JScrollPane(stockT);
      stockT.setRowHeight(20);// 라인의 높이
      stockT.getColumnModel().getColumn(0).setPreferredWidth(50); // 재고 재료명
      stockT.getColumnModel().getColumn(1).setPreferredWidth(50); // 재고 재료 수량
      stockT.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼 이동 불가 설정
      stockT.setRowSelectionAllowed(false);
      // 특정 셀이 선택되었을 때, 동일한 행에 있는 나머지 셀들이 전부 선택되는 기본 동작 불가 설정
      stockT.setColumnSelectionAllowed(false);
      // 특정 셀이 선택되었을 때, 동일한 열에 있는 나머지 셀들이 전부 선택되는 기본 동작 불가 설정

      // JTable 부분 (판매 집계)
      putTableModel = new DefaultTableModel(cols3, 50) {
         public boolean isCellEditable(int row, int col) {
            return false;
         }
      };

      putT = new JTable(putTableModel);
      scroll3 = new JScrollPane(putT);
      putT.setRowHeight(20);// 라인의 높이
      putT.getColumnModel().getColumn(0).setPreferredWidth(30); // 입고 날짜
      putT.getColumnModel().getColumn(1).setPreferredWidth(30); // 입고 재료명
      putT.getColumnModel().getColumn(2).setPreferredWidth(30); // 입고 재료 수량
      
      putT.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼 이동 불가 설정
      putT.setRowSelectionAllowed(false);
      // 특정 셀이 선택되었을 때, 동일한 행에 있는 나머지 셀들이 전부 선택되는 기본 동작 불가 설정
      putT.setColumnSelectionAllowed(false);
      // 특정 셀이 선택되었을 때, 동일한 열에 있는 나머지 셀들이 전부 선택되는 기본 동작 불가 설정

      // 주문 내역 패널
      JPanel ohPanel = new JPanel(new GridLayout(2, 1)); //주문내역 전체 틀 패널
      JPanel ohL_S_Panel = new JPanel(new BorderLayout());//주문내역 label,scroll 패널
      JPanel ohLPanel = new JPanel();//주문내역 label 패널
      JPanel ohTAPanel = new JPanel(new BorderLayout());//주문내역 TextArea 패널
      ohLPanel.add(orderHistoryL);
      ohL_S_Panel.add("North", ohLPanel);
      ohL_S_Panel.add("Center", scroll);
      ohTAPanel.add("Center", scroll4);  //textarea 스크롤
      ohTAPanel.add("North", resetB);
      
      //총 매출금액 TextField 패널
      JPanel tscPanel = new JPanel();
      tscPanel.add(totalSalesCountL);
      tscPanel.add(totalSalesCountTF);
      ohTAPanel.add("South",tscPanel);
      
      ohPanel.add(ohL_S_Panel);
      ohPanel.add(ohTAPanel);
      
      // 재고 수량 패널
      JPanel stPanel = new JPanel(new BorderLayout());
      JPanel stLPanel = new JPanel();
      stLPanel.add(stockL);
      stPanel.add("North", stLPanel);
      stPanel.add("Center", scroll2);

      // 입고 집계 패널
      JPanel putPanel = new JPanel(new BorderLayout());
      JPanel putLPanel = new JPanel();
      putLPanel.add(putL);
      putPanel.add("North", putLPanel);
      putPanel.add("Center", scroll3);   

      JPanel putTotalPanel = new JPanel(new GridLayout(2, 1));
      putTotalPanel.add(putPanel);

      this.setLayout(new GridLayout(1, 3));
      this.add(ohPanel);
      this.add(stPanel);
      this.add(putTotalPanel);

   }// end Dobby_Manager_Menu()//////

}// end class