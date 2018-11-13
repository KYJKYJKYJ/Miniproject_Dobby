package miniproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Dobby_Manager_Main extends JFrame {
	Dobby_Manager_Menu mMenu;
	
	public Dobby_Manager_Main() {
		mMenu = new Dobby_Manager_Menu();
	
		Thread th = new Thread(mMenu);
		th.start();
		
		//프레임에 붙임
		this.add(BorderLayout.CENTER, mMenu);
		this.setTitle("판매자 관리 시스템");
		this.setSize(950, 600);
		this.setVisible(true);
		setResizable(false);// 크기 조절 가능 or 불가능
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Dobby_Manager_Main();
		
	}
}//end class
	
