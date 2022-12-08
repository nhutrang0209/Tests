package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Trogiup extends JFrame {

	private JLayeredPane contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Trogiup frame = new Trogiup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Trogiup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 795, 626);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Tùy chọn");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Đăng xuất");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Thoát");
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.test tuychon = new view.test();
		        dispose();
		        tuychon.setVisible(true);
			}
		});
		btnNewButton.setForeground(UIManager.getColor("CheckBox.light"));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\84899\\Downloads\\home.jpg"));
		btnNewButton.setBounds(32, 11, 100, 31);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Hướng dẫn độc giả");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(288, 49, 255, 51);
		contentPane.add(lblNewLabel_1);
		
		JTextPane txtpnTiKhon = new JTextPane();
		txtpnTiKhon.setEditable(false);
		txtpnTiKhon.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtpnTiKhon.setText("1. Tài khoản: Mỗi độc giả đến đăng ký tại thư viện được cung cấp 1 tài khoản bao gồm username và password. Các bạn muốn đăng ký tài khoản sẽ đến trực tiếp tại thư viện để đăng ký");
		txtpnTiKhon.setBounds(29, 137, 714, 82);
		contentPane.add(txtpnTiKhon);
		
		JTextPane txtpnQunMt = new JTextPane();
		txtpnQunMt.setText("2. Qu\u00EAn m\u1EADt kh\u1EA9u: nh\u1EA5n qu\u00EAn m\u1EADt kh\u1EA9u, m\u1EADt kh\u1EA9u m\u1EDBi s\u1EBD \u0111\u01B0\u1EE3c g\u1EEDi v\u1EC1 email m\u00E0 \u0111\u1ED9c gi\u1EA3 \u0111\u00E3 \u0111\u0103ng k\u00FD.");
		txtpnQunMt.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtpnQunMt.setEditable(false);
		txtpnQunMt.setBounds(29, 257, 714, 82);
		contentPane.add(txtpnQunMt);
		
		JTextPane txtpnGiM = new JTextPane();
		txtpnGiM.setText("3. Gi\u1EDD m\u1EDF c\u1EEDa th\u01B0 vi\u1EC7n:\r\n    - Th\u1EE9 2 \u0111\u1EBFn th\u1EE9 6: 8h - 20h\r\n    - Th\u1EE9 7, ch\u1EE7 nh\u1EADt: 8h - 18h.");
		txtpnGiM.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtpnGiM.setEditable(false);
		txtpnGiM.setBounds(29, 378, 714, 82);
		contentPane.add(txtpnGiM);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Trogiup.class.getResource("/image/nen-background-trang-dep-va-don-gian_110344503.jpg")));
		lblNewLabel.setBounds(-10, -22, 781, 589);
		contentPane.add(lblNewLabel);
	}
}
