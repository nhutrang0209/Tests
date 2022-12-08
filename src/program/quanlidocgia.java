/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program;

import java.sql.*;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

public class quanlidocgia extends JFrame implements ActionListener,
		MouseListener {
	Connection conn;
	Statement stm;
	ResultSet rst;
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	JScrollPane tableResult;
	DefaultTableModel model;
	JTable tb = new JTable();
	JButton edit, delete, insert;
	int selectrow = 0;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;

	public  quanlidocgia(String s) {
		super(s);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/quanlysach", "root", "");
			stm = conn.createStatement();
			JPanel p = new JPanel();
			p.setBounds(0, 430, 986, 33);
			getContentPane().setLayout(null);
			p.setLayout(null);
			
			lblNewLabel_3 = new JLabel("Qu\u1EA3n l\u00FD \u0111\u1ED9c gi\u1EA3");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_3.setBounds(352, 22, 188, 43);
			getContentPane().add(lblNewLabel_3);
			edit = new JButton("Sửa");
			edit.setBounds(319, 407, 65, 23);
			getContentPane().add(edit);
			edit.addActionListener(this);
			delete = new JButton("Xóa");
			delete.setBounds(412, 407, 80, 23);
			getContentPane().add(delete);
			delete.addActionListener(this);
			insert = new JButton("Thêm");
			insert.setBounds(512, 407, 72, 23);
			getContentPane().add(insert);
			insert.addActionListener(this);
			
			btnNewButton = new JButton("Trang chủ");
			btnNewButton.setIcon(new ImageIcon(quanlidocgia.class.getResource("/image/home.jpg")));
			btnNewButton.setBounds(41, 22, 116, 23);
			getContentPane().add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					view.Admin ad = new view.Admin();
					ad.setVisible(true);
					dispose();
				}
			});
			
			lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(quanlidocgia.class.getResource("/image/nen-background-trang-dep-va-don-gian_110344503.jpg")));
			lblNewLabel_1.setBounds(-10, -437, 876, 465);
			p.add(lblNewLabel_1);
			getContentPane().add(p);
			reload();
			model = new DefaultTableModel(vData, vTitle);
			tb = new JTable(model);
			tb.addMouseListener(this);
			tableResult = new JScrollPane(tb);
			tableResult.setBounds(56, 76, 743, 301);
			this.getContentPane().add(tableResult);
			
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(quanlidocgia.class.getResource("/image/nen-background-trang-dep-va-don-gian_110344503.jpg")));
			lblNewLabel_2.setBounds(0, 1, 861, 474);
			getContentPane().add(lblNewLabel_2);
			this.setSize(875,534);
                        this.setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			mnNewMenu = new JMenu("Hệ thống");
			menuBar.add(mnNewMenu);
			
			mntmNewMenuItem = new JMenuItem("Đăng xuất");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					view.test dangnhap = new view.test();
					dangnhap.setVisible(true);
					dispose();
				}
			});
			mnNewMenu.add(mntmNewMenuItem);
			
			mntmNewMenuItem_1 = new JMenuItem("Exit");
			mntmNewMenuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mnNewMenu.add(mntmNewMenuItem_1);
			this.setVisible(true);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public void reload() {
		try {
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery("select * from docgia");
			ResultSetMetaData rstmeta = rst.getMetaData();
			int num_column = rstmeta.getColumnCount();
			for(int i = 1; i<=num_column;i++)
			{
				vTitle.add(rstmeta.getColumnLabel(i));
			}
			
			while (rst.next())
			{
				Vector row = new Vector(num_column);
				for(int i = 1; i<=num_column;i++)
					row.add(rst.getString(i));
				vData.add(row);
			}
			rst.close();
		} catch (Exception e) {
		}
	}

	// thao tac xoa du lieu voi button delete
	public void delete() {
		try {
			Vector st = (Vector)vData.elementAt(selectrow);
			String sql = "Delete from docgia where madocgia = \""+st.elementAt(0) + "\"";
			stm.executeUpdate(sql);
			
			vData.remove(selectrow);
			
			model.fireTableDataChanged();
		} catch (Exception e) {
		}
	}

	public void actionPerformed(ActionEvent e) {
		// khi an delete
		if (e.getActionCommand().equals("Delete")) {
			delete();

		}
		// khi an insert
		if (e.getActionCommand().equals("Insert"))
		{
			// cua so nhap moi
			new UpdateForm("Insert form", this,"","","","","","","");
		}
		// khi an edit

		if (e.getActionCommand().equals("Edit")) {
			Vector st = (Vector)vData.elementAt(selectrow);
			
			new UpdateForm("Edit form",this,(String)st.elementAt(0),(String)st.elementAt(1),(String)st.elementAt(2),(String)st.elementAt(3),(String)st.elementAt(4),(String)st.elementAt(5),(String)st.elementAt(6));
		}
		}
	
	public void mouseClicked(MouseEvent e) {
		selectrow = tb.getSelectedRow();
	}

	public static void main(String[] args) {
		new  quanlidocgia(" quanlidg");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class UpdateForm extends JFrame implements ActionListener {
	JLabel madocgialb;
	JTextField madocgia;
	
	JLabel tendocgialb;
	JTextField tendocgia;
	
	JLabel ngaysinhlb;
	JTextField ngaysinh;
	
	JLabel noisinhlb;
	JTextField noisinh;
	
	JLabel diachilb;
	JTextField diachi;
	
	JLabel dienthoailb;
	JTextField dienthoai;
	
	JLabel maloaidocgialb;
	JTextField maloaidocgia;
	
	
	
	JLabel errorlb;
	JLabel errordetails;
	
	JButton ok;
	JButton cancel;
	
	
	
	 quanlidocgia mst;
	String id;
	

	public   UpdateForm(String dg,  quanlidocgia bb, String mdg, String tdg, String ns, String nois, String dc, String dt, String mldg)
	{
		super(dg);
		mst = bb;
		Container cont = this.getContentPane();
		cont.setLayout(new GridLayout(24,2));
		
		madocgialb = new JLabel("madocgia");
		madocgia = new JTextField(mdg);
		cont.add(madocgialb);
		cont.add(madocgia);
		
		tendocgialb = new JLabel("tendocgia");
		tendocgia = new JTextField(tdg);
		cont.add(tendocgialb);
		cont.add(tendocgia);
		
		ngaysinhlb = new JLabel("ngaysinh");
		ngaysinh = new JTextField(ns);
		cont.add(ngaysinhlb);
		cont.add(ngaysinh);
		
		noisinhlb = new JLabel("noisinh");
		noisinh = new JTextField(nois);
		cont.add(noisinhlb);
		cont.add(noisinh);	
		
		diachilb = new JLabel("diachi");
		diachi = new JTextField(dc);
		cont.add(diachilb);
		cont.add(diachi);
		
		dienthoailb = new JLabel("dienthoai");
		dienthoai = new JTextField(dt);
		cont.add(dienthoailb);
		cont.add(dienthoai);	
		
		maloaidocgialb = new JLabel("maloaidocgia");
		maloaidocgia = new JTextField(mldg);
		cont.add(maloaidocgialb);
		cont.add(maloaidocgia);
		
		
		errorlb = new JLabel("");
		errordetails = new JLabel("");
		errorlb.setVisible(false);
		errordetails.setVisible(false);
		cont.add(errorlb);
		cont.add(errordetails);
		JButton ok = new JButton("Ok");
		JButton cancel = new JButton("Cancel");
		cont.add(ok);
		cont.add(cancel);
		ok.addActionListener(this);
		cancel.addActionListener(this);
		this.setSize(430,500);
		this.setLocation(250, 100);
		this.setVisible(true);
		id = mdg;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ok")) {
			insertDB();
		} else
			this.dispose();
	}

	public void insertDB()
	{
		if(madocgia.getText().equals("")|| tendocgia.getText().equals("")||ngaysinh.getText().equals("")||noisinh.getText().equals("")||diachi.getText().equals("")||dienthoai.getText().equals("")||maloaidocgia.getText().equals(""))
		{
			errorlb.setText("Error");
			errordetails.setText("empty value");
			errorlb.setForeground(Color.RED);
			errordetails.setForeground(Color.RED);
			
			errorlb.setVisible(true);
			errordetails.setVisible(true);
			
		}
		else
		{
			try
			{
				String mdg = madocgia.getText();
				String tdg = tendocgia.getText();
				String ns = ngaysinh.getText();
				String nois = noisinh.getText();
				String dc = diachi.getText();
				Integer dt = Integer.parseInt(dienthoai.getText());
				String mldg = maloaidocgia.getText();
				
				String sql=" ";
				
				if(this.getTitle().equals("Insert form"))
					sql = "insert into docgia (MADOCGIA,TENDOCGIA,NGAYSINH,NOISINH,DIACHI,DIENTHOAI,MALOAIDOCGIA)" +
							" values ('"+mdg+"','"+tdg+"','"+ns+"','"+nois+"','"+dc+"',"+dt+",'"+mldg+"')";
				else
					sql = "update docgia set MADOCGIA= '"+mdg+"',TENDOCGIA ='"+tdg+"',NGAYSINH='"+ns+"',NOISINH='"+nois+"',DIACHI='"+dc+"',DIENTHOAI= "+dt+",MALOAIDOCGIA='"+mldg+"' where MADOCGIA= '" +id+"'";
				
				mst.stm.executeUpdate(sql);
				mst.reload();
				mst.model.fireTableDataChanged();
				this.dispose();
			}
			catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Loi");
			}

		}
	}

}

