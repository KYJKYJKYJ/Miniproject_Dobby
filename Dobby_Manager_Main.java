package miniproject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;


public class Dobby_Manager_Main extends JFrame implements ActionListener {
	Dobby_Manager_Menu mMenu;
	
	public Dobby_Manager_Main() {
		mMenu = new Dobby_Manager_Menu();
		
		//프레임에 붙임
		this.add(BorderLayout.CENTER, mMenu);
		this.setTitle("판매자 관리 시스템");
		this.setSize(950, 600);
		this.setVisible(true);
		setResizable(false);// 크기 조절 가능 or 불가능
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		stockList();
		
		mMenu.resetB.addActionListener(this);
	}
	
	public static void main(String[] args) {
		new Dobby_Manager_Main();
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == mMenu.resetB) {
			mMenu.ohTableModel.setRowCount(0);
		}	
	}
	
	public void stockList() {
		Dobby_Stock_DAO sdao = Dobby_Stock_DAO.getInstance();
		List<Dobby_Stock_DTO> sList = sdao.readStocklist();
	
		for(Dobby_Stock_DTO sdto : sList) {
			Object[] line = new Object[2];
			line[0] = sdto.getStock_material();
			line[1] = sdto.getStock_amount();
		
			mMenu.stTableModel.addRow(line);
		}		
	}
}//end class
	
