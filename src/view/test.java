package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.NguoiDungDao;

import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JEditorPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Dimension;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class test extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JPasswordField txtPass;
	private final Action action = new SwingAction();
	private ActionEvent e;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) {
		try {
			program.ConnectMySQL.openConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	
	private void btnLoginActionPerformed(java.awt.event.ActionEvent e) {
		if (txtEmail.getText().equals("email")) {
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(this, "Sai dang nhap", "Thongbao", 
					JOptionPane.WARNING_MESSAGE);
		}
		StringBuilder sb = new StringBuilder();
		DataValidator.validateEmpty(txtEmail, sb, "sai mail");
		DataValidator.validateEmpty(txtPass, sb, "matkhau");
		if (sb.length()>0) {
			MessageDialog.showErrorDialog(txtEmail, sb.toString(), "Khong de trong");
		}
		NguoiDungDao dao = new NguoiDungDao();
		String email = txtEmail.getText();
		String pass = String.valueOf(txtPass.getPassword());
		try {
			User user = dao.checkLogin(email,pass);
			if (user==null) {
				MessageDialog.showErrorDialog(this, "Sai email", "Lỗi");
			}else {
				System.out.println("444");
				this.dispose();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			MessageDialog.showErrorDialog(this, e1.getMessage(), "Lỗi 2");
		}
	}
	
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 574);
		getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Đăng nhập với tư cách độc giả");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginActionPerformed(e);
			}
		});
		
		
		
		JButton btnLogInAs = new JButton("Đăng nhập với tư cách quản trị viên");
		
		btnLogInAs.setBounds(405, 350, 242, 39);
		getContentPane().add(btnLogInAs);
		btnLogin.setBounds(160, 350, 235, 39);
		getContentPane().add(btnLogin);
		
		JButton btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(353, 400, 108, 39);
		getContentPane().add(btnNewButton_1);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(265, 232, 272, 30);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(140, 238, 108, 24);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mật khẩu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(160, 292, 88, 24);
		getContentPane().add(lblNewLabel_1);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(265, 286, 272, 30);
		getContentPane().add(txtPass);
		
		JLabel lblNewLabel_2 = new JLabel("Thư viện Tạ Quang Bửu");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(287, 112, 288, 70);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Quên mật khẩu?");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Nhập username hoặc email");
				JOptionPane.showMessageDialog(rootPane,"Mật khẩu mới đã được gửi về mail của bạn");
			}
		});
		btnNewButton_2.setBounds(585, 463, 114, 23);
		getContentPane().add(btnNewButton_2);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 786, 23);
		getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Tùy chọn");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Trợ giúp");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.Trogiup trogiup = new view.Trogiup();
				trogiup.setVisible(true);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Về ứng dụng");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JLabel lblTrngiHc = new JLabel("Trường Đại học Bách Khoa Hà Nội");
		lblTrngiHc.setIcon(new ImageIcon(test.class.getResource("/image/hust.png")));
		lblTrngiHc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTrngiHc.setBounds(160, 34, 464, 148);
		getContentPane().add(lblTrngiHc);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(test.class.getResource("/image/nen-background-trang-dep-va-don-gian_110344503.jpg")));
		lblNewLabel_4.setBounds(-32, 11, 818, 526);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(431, 350, 89, 23);
		getContentPane().add(btnNewButton_3);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
