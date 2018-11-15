package miniproject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Dobby_Consumer_Main extends JFrame {
	Dobby_Manager_Menu mMenu;
	Dobby_Manager_Main mMain = new Dobby_Manager_Main();
	Dobby_Consumer_Menu cMenu = new Dobby_Consumer_Menu();

	public Dobby_Consumer_Main() {
		this.setTitle("Dobby"); // 프레임 타이틀

		// 프레임에 붙임
		this.add(BorderLayout.CENTER, cMenu);
		this.setSize(900, 600);
		this.setVisible(true);
		setResizable(false); // 크기 조절 불가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 버튼 이벤트 리스너 연결
		cMenu.chickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.soyChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.yangChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.creamChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.tokbokiChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.hotbChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.marahChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.shrimpChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.garlicChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.tokbokiChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.greenOChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.honeyChickenB.addActionListener(new Dobby_mInfo_listener());
		cMenu.snowChickenB.addActionListener(new Dobby_mInfo_listener());

		cMenu.plusB.addActionListener(new Dobby_PlusB_listener());
		cMenu.minusB.addActionListener(new Dobby_MinusB_listener());

		cMenu.registerB.addActionListener(new Dobby_mRegisterB_listener());
		cMenu.removeB.addActionListener(new Dobby_mRemoveB_listener());
		cMenu.decideB.addActionListener(new Dobby_mDecideB_listener());
	} // end Dobby_Main()

	public static void main(String[] args) {
		//new Dobby_Consumer_Main();
	} // end main()

	// 액션 리스너 내부클래스로 선언
	private class Dobby_mInfo_listener implements ActionListener {
		// 버튼의 이름을 받아서 메뉴 이름을 받아 온 후, 메뉴 이름을 DAO를 사용하여 메뉴의 이름, 가격 정보를 받아옴
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == cMenu.chickenB) {
				name_Actions(cMenu.chickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.yangChickenB) {
				name_Actions(cMenu.yangChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.soyChickenB) {
				name_Actions(cMenu.soyChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.snowChickenB) {
				name_Actions(cMenu.snowChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.hotbChickenB) {
				name_Actions(cMenu.hotbChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.greenOChickenB) {
				name_Actions(cMenu.greenOChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.creamChickenB) {
				name_Actions(cMenu.creamChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.honeyChickenB) {
				name_Actions(cMenu.honeyChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.tokbokiChickenB) {
				name_Actions(cMenu.tokbokiChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.marahChickenB) {
				name_Actions(cMenu.marahChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.garlicChickenB) {
				name_Actions(cMenu.garlicChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			} else if (btn == cMenu.shrimpChickenB) {
				name_Actions(cMenu.shrimpChickenB.getName(), cMenu.mealNameTF, cMenu.mealPriceTF, cMenu.mealAmountTF);
			}
		} // end actionPerformed()

		private void name_Actions(String btnName, JTextField nameTF, JTextField priceTF, JTextField amountTF) {
			Dobby_Menu_DAO mdao = new Dobby_Menu_DAO();
			String name = mdao.menuName(mdao.menuId(btnName));
			int price = mdao.menuPrice(mdao.menuId(btnName));

			int amount = 1; // 메뉴를 누를 경우 기본 수량을 1로 하여 메뉴 선택
			nameTF.setText(name);
			priceTF.setText(Integer.toString(price));
			amountTF.setText(Integer.toString(amount));
		} // end name_Actions();
	} // end Dobby_minfolistener

	private class Dobby_PlusB_listener implements ActionListener {
		// 수량 증가 버튼
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == cMenu.plusB) {
				p_Action();
			}
		}

		private void p_Action() {
			if (cMenu.mealNameTF.getText().equals("")) {
				JOptionPane.showMessageDialog(cMenu, "메뉴를 먼저 선택해주세요!", "메뉴 선택", JOptionPane.ERROR_MESSAGE);
				return; // 메뉴 이름이 담긴 텍스트필드를 읽어서 공백일 경우 메뉴가 선택되지 않은 것이므로 메세지 출력
			} else {
				int count = Integer.parseInt(cMenu.mealAmountTF.getText());
				count++;
				cMenu.mealAmountTF.setText(Integer.toString(count));
			}
		} // end pAction()
	} // end Dobby_plusBlistener

	private class Dobby_MinusB_listener implements ActionListener {
		// 수량 감소 버튼
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == cMenu.minusB) {
				m_Action();
			}
		}

		private void m_Action() {
			if (cMenu.mealNameTF.getText().equals("")) {
				JOptionPane.showMessageDialog(cMenu, "메뉴를 먼저 선택해주세요!", "메뉴 선택", JOptionPane.ERROR_MESSAGE);
				return; // 메뉴 이름이 담긴 텍스트필드를 읽어서 공백일 경우 메뉴가 선택되지 않은 것이므로 메세지 출력
			} else {
				int count = Integer.parseInt(cMenu.mealAmountTF.getText());
				if (count <= 1) {
					JOptionPane.showMessageDialog(cMenu, "0 마리 이하는 주문 하실 수 없습니다!", "수량 오류", JOptionPane.ERROR_MESSAGE);
					return;
				} // 음수로 주문 할 수 없도록 경고 메세지 출력
				count--;
				cMenu.mealAmountTF.setText(Integer.toString(count));
			}
		} // end mAction()
	} // end Dobby_minusBlistener

	private class Dobby_mRegisterB_listener implements ActionListener {
		// 주문 정보 등록 버튼
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == cMenu.registerB) {
				regAction();
			}
		}

		private void regAction() { // 등록버튼 액션
			if (cMenu.mealNameTF.getText().equals("") || cMenu.order_idTF.getText().equals("")) {
				JOptionPane.showMessageDialog(cMenu, "이름 또는 음식을 입력하거나 선택해 주세요!", "주문 오류!", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				cMenu.order_idTF.setEditable(false); // 초기 이름 값을 받으면 입력불가
				int allPrice = (Integer.parseInt(cMenu.mealPriceTF.getText())
						* Integer.parseInt(cMenu.mealAmountTF.getText()));
				String[] row = new String[4];
				row[0] = cMenu.order_idTF.getText().trim();
				row[1] = cMenu.mealNameTF.getText().trim();
				row[2] = cMenu.mealAmountTF.getText().trim();
				row[3] = Integer.toString(allPrice); // 텍스트 필드들 정보를 컬럼에 삽입하기 위해

				cMenu.tableModel.addRow(row); // 음식 정보를 받아서 테이블에 삽입

				int fPrice = 0;
				for (int i = 0; i < cMenu.table.getRowCount(); i++) {
					fPrice += Integer.parseInt((String) cMenu.tableModel.getValueAt(i, 3));
					// 테이블로 부터 금액 컬럼 값을 읽어나간 후 변수에 덧셈하여 저장
					cMenu.fullPriceTF.setText(Integer.toString(fPrice));
				}
			}

			// 음식 정보 테이블 삽입 후 텍스트 필드 초기화
			cMenu.mealNameTF.setText("");
			cMenu.mealAmountTF.setText("");
			cMenu.mealPriceTF.setText("");

		} // end regAction()
	} // end Dobby_mRegisterB

	private class Dobby_mRemoveB_listener implements ActionListener {
		// 주문 정보 등록 버튼
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == cMenu.removeB) {
				rmAction();
			}
		}

		private void rmAction() { // 삭제버튼 액션
			int row = cMenu.table.getSelectedRow(); // 행을 선택하면 행 번호를 받아옴
			if (row == -1) {
				JOptionPane.showMessageDialog(cMenu, "주문 제거할 메뉴를 선택해주세요!", "주문 오류!", JOptionPane.ERROR_MESSAGE);
				return;
			} // 삭제할 게 없으면 출력
			cMenu.tableModel.removeRow(row);// 행 삭제

			int fPrice = 0;
			if (cMenu.table.getRowCount() == 0)
				cMenu.fullPriceTF.setText(Integer.toString(0)); // 행이 없을 경우 주문하려는 메뉴가 없는 것 이므로 총 가격 0으로 설정
			else {
				for (int i = 0; i < cMenu.table.getRowCount(); i++) {
					fPrice += Integer.parseInt((String) cMenu.tableModel.getValueAt(i, 3));
					// 테이블로 부터 금액 컬럼 값을 읽어나간 후 변수에 덧셈하여 저장
					cMenu.fullPriceTF.setText(Integer.toString(fPrice));
				}
			}
		} // end rmAction()
	} // end Dobby_mRemoveB

	private class Dobby_mDecideB_listener implements ActionListener {
		// 주문 정보 등록 버튼
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == cMenu.decideB) {
				dcAction();
			}
		}

		private void dcAction() { // 결정버튼 액션
			/*Dobby_Orders_DAO odao = Dobby_Orders_DAO.getInstance();*/
			Dobby_RecipeJoin_DAO jdao = Dobby_RecipeJoin_DAO.getInstance();

			if (cMenu.fullPriceTF.getText().equals(Integer.toString(0))) {
				// 메뉴를 등록하지 않았으면 총 가격이 0이므로 메뉴를 등록한 후에 결정버튼을 누를 수 있도록 메세지 출력
				JOptionPane.showMessageDialog(cMenu, "음식을 먼저 등록해주세요!", "주문 오류!", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int chk = JOptionPane.showConfirmDialog(cMenu, "주문 결정하시겠습니까?", "주문 결정", JOptionPane.YES_NO_OPTION);

			if (chk == JOptionPane.NO_OPTION) {
				return;
			} else if (chk == JOptionPane.YES_OPTION) {
				for (int i = 0; i < cMenu.table.getRowCount(); i++) {
					odto_Insert((String) cMenu.table.getValueAt(i, 0), (String) cMenu.table.getValueAt(i, 1),
							Integer.parseInt((String) cMenu.table.getValueAt(i, 2)),
							Integer.parseInt((String) cMenu.table.getValueAt(i, 3)));

					int id = jdao.ridList((String) cMenu.table.getValueAt(i, 1));
					String material = jdao.rmList(id);
					jdao.minusStocklist(id, Integer.parseInt((String) cMenu.table.getValueAt(i, 2)), material);
				}

				mMain.mMenu.stTableModel.setRowCount(0);
				mMain.stockList();
				Dobby_orderTAprint();
				Dobby_orderHistoryInsert();
				mMain.totalPricelist();

				JOptionPane.showMessageDialog(cMenu, "주문이 완료 되었습니다.");
				cMenu.order_idTF.setText("");
				cMenu.tableModel.setRowCount(0); // table 초기화
				cMenu.fullPriceTF.setText(Integer.toString(0)); // 총 금액 초기화
				cMenu.order_idTF.setEditable(true);
			}
		} // end decAction()

		// 값을 받아와서 DTO를 사용하여 값 셋팅
		private void odto_Insert(String order_id, String order_name, int order_quantity, int order_sumprice) {
			Dobby_Orders_DAO odao = Dobby_Orders_DAO.getInstance();
			Dobby_Orders_DTO odto = new Dobby_Orders_DTO(order_id, order_name, order_quantity, order_sumprice);
			odao.insert_Orderlist(odto); 
		} // end odto_Insert()
		
		// 현재 시간 정보, 주문자 이름, 주문한 메뉴와 수량 정보를 판매자 텍스트Area에 출력해줌
		private void Dobby_orderTAprint() {
			Calendar cal = Calendar.getInstance();// 현재 시간 구하기.
			SimpleDateFormat sdfm = new SimpleDateFormat("hh시 mm분");
			String time = sdfm.format(cal.getTime());
			mMain.mMenu.orderHistoryTA.append(time + " 주문이 들어왔습니다.\r\n");
			mMain.mMenu.orderHistoryTA.append("[주문자 : " + (String) cMenu.table.getValueAt(0, 0) + "]" + "\r\n");

			for (int i = 0; i < cMenu.tableModel.getRowCount(); i++)
				mMain.mMenu.orderHistoryTA.append("[" + cMenu.table.getValueAt(i, 1) + "] " + "["
						+ cMenu.table.getValueAt(i, 2) + "마리]" + "\r\n");

			mMain.mMenu.orderHistoryTA.append("\r\n");

		}// end orderTAprint()

		// 판매자 주문 내역 테이블 삽입
		private void Dobby_orderHistoryInsert() {
			mMain.mMenu.ohTableModel.setRowCount(0);
			Dobby_Orders_DAO odao = Dobby_Orders_DAO.getInstance();
			List<Dobby_Orders_DTO> aList = odao.read_Orderlist(); // DTO에 getter를 이용하여 값을 읽어옴

			for (Dobby_Orders_DTO odto : aList) {
				Object[] line = new Object[4];
				line[0] = odto.getOrder_id();
				line[1] = odto.getOrder_name();
				line[2] = odto.getOrder_quantity();
				line[3] = odto.getOrder_sumprice();
				mMain.mMenu.ohTableModel.addRow(line);
			}
		}// end orderHistoryInsert()
	} // end Dobby_mDecideB
} // end class
