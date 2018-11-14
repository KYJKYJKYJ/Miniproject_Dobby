package miniproject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dobby_Manager_Main extends JFrame implements ActionListener {
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

		mMenu.resetB.addActionListener(this);
		mMenu.putRegisterB.addActionListener(this);
		mMenu.putRemoveB.addActionListener(this);
		mMenu.putDecideB.addActionListener(this);

	}

	public static void main(String[] args) {
		new Dobby_Manager_Main();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == mMenu.resetB) {
			mMenu.ohTableModel.setRowCount(0);
			
		} else if (obj == mMenu.putRegisterB) {
			putRegAction();
		} else if (obj == mMenu.putRemoveB) {
			putDelAction();
		} else if (obj == mMenu.putDecideB) {
			putDecAction();
		}

	}

	private void putRegAction() { // 입고 등록버튼 액션
		if (mMenu.putCountTF.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "수량을 입력해주세요!", "등록 오류!", JOptionPane.ERROR_MESSAGE);
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

	private void putDelAction() { // 입고 취소 버튼 액션
		int row = mMenu.putT.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "입고 취소 행을 선택해주세요!", "입고 오류!", JOptionPane.ERROR_MESSAGE);
			return;
		} // 삭제할 게 없으면 출력
		mMenu.putTableModel.removeRow(row);// 행 삭제

	} // end delAction()

	public void putDecAction() { // 입고 결정 버튼 액션

		if (mMenu.putT.getRowCount() == 0) {
			// 메뉴를 등록하지 않았으면 총 가격이 0이므로 메뉴를 등록한 후에 결정버튼을 누를 수 있도록 메세지 출력
			JOptionPane.showMessageDialog(this, "입고 등록해주세요!", "등록 오류!", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int chk = JOptionPane.showConfirmDialog(this, "입고 결정하시겠습니까?", "입고 결정", JOptionPane.YES_NO_OPTION);

		if (chk == JOptionPane.NO_OPTION) {
			return;
		} else if (chk == JOptionPane.YES_OPTION) {
			for (int i = 0; i < mMenu.putT.getRowCount(); i++) {
				pInsert((String) mMenu.putT.getValueAt(i, 0), (String) mMenu.putT.getValueAt(i, 1),
						Integer.parseInt((String) mMenu.putT.getValueAt(i, 2)));
				updateList((String) mMenu.putT.getValueAt(i, 1), Integer.parseInt((String) mMenu.putT.getValueAt(i, 2)));
			}
			
			JOptionPane.showMessageDialog(this, "입고 완료");
			mMenu.stTableModel.setRowCount(0); // 재고 table 초기화
			stockList(); // 재고 table 다시 불러옴
			mMenu.putTableModel.setRowCount(0); // 입고 table 초기화

		}
	} // end decAction()

	public void pInsert(String put_date, String put_material, int put_amount) {
		Dobby_Put_DAO pdao = Dobby_Put_DAO.getInstance();
		Dobby_Put_DTO pdto = new Dobby_Put_DTO(put_date, put_material, put_amount);
		pdao.insert_putlist(pdto);
	}

	public void stockList() {
		Dobby_Stock_DAO sdao = Dobby_Stock_DAO.getInstance();
		List<Dobby_Stock_DTO> sList = sdao.readStocklist();

		for (Dobby_Stock_DTO sdto : sList) {
			Object[] line = new Object[2];
			line[0] = sdto.getStock_material();
			line[1] = sdto.getStock_amount();

			mMenu.stTableModel.addRow(line);
		}
	}
	
	private void updateList(String name, int amount) {
		Dobby_Put_DAO pdao = Dobby_Put_DAO.getInstance();
		pdao.updateStocklist(name, amount);
	}
}// end class
