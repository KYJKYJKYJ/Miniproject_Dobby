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

public class Dobby_Manager_Main extends JFrame {
	Dobby_Manager_Menu mMenu;

	public Dobby_Manager_Main() {
		mMenu = new Dobby_Manager_Menu();

		// 프레임에 붙임
		this.add(BorderLayout.CENTER, mMenu);
		this.setTitle("판매자 관리 시스템");
		this.setSize(950, 600);
		this.setVisible(true);
		setResizable(false);// 크기 조절 가능 or 불가능
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		stockList();

		mMenu.closeB.addActionListener(new Dobby_CloseB_listener());
		mMenu.putRegisterB.addActionListener(new Dobby_putRegisterB_listener());
		mMenu.putRemoveB.addActionListener(new Dobby_putRemoveB_listener());
		mMenu.putDecideB.addActionListener(new Dobby_putDecideB_listener());

	}

	public static void main(String[] args) {
		// new Dobby_Manager_Main();
	}

	// DAO를 이용하여 현재 재고 내용 출력
	public void stockList() {
		Dobby_Stock_DAO sdao = Dobby_Stock_DAO.getInstance();
		List<Dobby_Stock_DTO> sList = sdao.readStocklist();

		for (Dobby_Stock_DTO sdto : sList) {
			Object[] line = new Object[2];
			line[0] = sdto.getStock_material();
			line[1] = sdto.getStock_amount();

			mMenu.stTableModel.addRow(line);
		}
	} // end stockList()

	// DAO를 통해 판매자 주문 내역에서 가격을 합산해서 테이블에 삽입
	public void totalPricelist() {
		int totalPrice = 0;
		for (int i = 0; i < mMenu.ohTableModel.getRowCount(); i++) {
			totalPrice += (int) mMenu.ohTableModel.getValueAt(i, 3);
		}
		
		Dobby_Totals_DAO tdao = Dobby_Totals_DAO.getInstance();
		tdao.update_Totallist(totalPrice);
		mMenu.totalSalesCountTF.setText(Integer.toString(tdao.read_Totallist()));
	}// end read_orderlist()

	private class Dobby_CloseB_listener implements ActionListener {
		// 리셋 버튼 , 주문내역 테이블과 DB의 주문 내역 삭제
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == mMenu.closeB) {
				CloseB_Action();
			}
		}

		private void CloseB_Action() {
			int chk = JOptionPane.showConfirmDialog(mMenu, "마감 하시겠습니까? 반드시 확인하세요", "마감 결정", JOptionPane.YES_NO_OPTION);

			if (chk == JOptionPane.NO_OPTION) {
				return;
			} else if (chk == JOptionPane.YES_OPTION) {
				mMenu.ohTableModel.setRowCount(0);
				Dobby_Orders_DAO odao = Dobby_Orders_DAO.getInstance();
				odao.delete_Orderlist();
				System.exit(0);
			}
		} // end CloseB_Action()
	} // end Dobby_ResetB_listener

	private class Dobby_putRegisterB_listener implements ActionListener {
		// 입고 재고 등록 버튼
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == mMenu.putRegisterB) {
				putRegAction();
			}
		}

		private void putRegAction() { // 입고 등록버튼 액션
			if (mMenu.putCountTF.getText().equals("")) {
				JOptionPane.showMessageDialog(mMenu, "수량을 입력해주세요!", "등록 오류!", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				Calendar cal = Calendar.getInstance();// 현재 시간 구하기.
				SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
				String time = sdfm.format(cal.getTime());
				String[] row = new String[3];
				row[0] = time;
				row[1] = (String) mMenu.materialNameBox.getSelectedItem();
				row[2] = mMenu.putCountTF.getText().trim();

				mMenu.putTableModel.addRow(row); // 입고 정보를 받아서 테이블에 삽입
			}
			// 음식 정보 테이블 삽입 후 텍스트 필드 초기화
			mMenu.putCountTF.setText("");
		} // end regAction()
	} // end Dobby_putRegisterB_listener

	private class Dobby_putRemoveB_listener implements ActionListener {
		// 입고 재고 테이블의 선택한 행을 삭제해주는 기능 수행
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == mMenu.putRemoveB) {
				putRmAction();
			}
		}

		private void putRmAction() { // 입고 취소 버튼 액션
			int row = mMenu.putT.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(mMenu, "입고 취소 행을 선택해주세요!", "입고 오류!", JOptionPane.ERROR_MESSAGE);
				return;
			} // 선택을 하지 않았거나 선택할 행이 없는 경우
			mMenu.putTableModel.removeRow(row);// 행 삭제
		} // end delAction()
	} // end Dobby_putRemoveB_listener

	private class Dobby_putDecideB_listener implements ActionListener {
		// 입고 재고 결정 버튼
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == mMenu.putDecideB) {
				putDcAction();
			}
		}

		private void putDcAction() { // 입고 결정 버튼 액션
			if (mMenu.putT.getRowCount() == 0) {
				// 메뉴를 등록하지 않았으면 총 가격이 0이므로 메뉴를 등록한 후에 결정버튼을 누를 수 있도록 메세지 출력
				JOptionPane.showMessageDialog(mMenu, "입고 등록해주세요!", "등록 오류!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int chk = JOptionPane.showConfirmDialog(mMenu, "입고 결정하시겠습니까?", "입고 결정", JOptionPane.YES_NO_OPTION);

			if (chk == JOptionPane.NO_OPTION) {
				return;
			} else if (chk == JOptionPane.YES_OPTION) {
				for (int i = 0; i < mMenu.putT.getRowCount(); i++) {
					put_Insert((String) mMenu.putT.getValueAt(i, 0), (String) mMenu.putT.getValueAt(i, 1),
							Integer.parseInt((String) mMenu.putT.getValueAt(i, 2)));
					put_Update((String) mMenu.putT.getValueAt(i, 1),
							Integer.parseInt((String) mMenu.putT.getValueAt(i, 2)));
				}

				JOptionPane.showMessageDialog(mMenu, "입고 완료");
				mMenu.stTableModel.setRowCount(0); // 재고 table 초기화
				stockList(); // 재고 table 다시 불러옴
				mMenu.putTableModel.setRowCount(0); // 입고 table 초기화
			}
		} // end decAction()

		// DAO에 DB삽입 기능 호출하여 DB에 입고 삽입
		private void put_Insert(String put_date, String put_material, int put_amount) {
			Dobby_Put_DAO pdao = Dobby_Put_DAO.getInstance();
			Dobby_Put_DTO pdto = new Dobby_Put_DTO(put_date, put_material, put_amount);
			pdao.insertPutlist(pdto);
		}

		// DAO에 DB갱신 기능 호출하여 입고 갱신
		private void put_Update(String name, int amount) {
			Dobby_Put_DAO pdao = Dobby_Put_DAO.getInstance();
			pdao.updateStocklist(name, amount);
		} // end updateList()
	} // end Dobby_putDecideB_listener
}// end class
