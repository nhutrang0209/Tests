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

public class Quanlimuontra extends JFrame implements ActionListener,
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
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	public Quanlimuontra(String s) {
		super(s);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/quanlysach", "root", "");
			stm = conn.createStatement();
			JPanel p = new JPanel();
			p.setBounds(0, 430, 986, 33);
			getContentPane().setLayout(null);
			
			JButton btnNewButton = new JButton("Trang chủ");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					view.Admin admin = new view.Admin();
					admin.setVisible(true);
					dispose();
				}
			});
			btnNewButton.setIcon(new ImageIcon(Quanlimuontra.class.getResource("/image/home.jpg")));
			btnNewButton.setBounds(289, 418, 107, 23);
			getContentPane().add(btnNewButton);
			insert = new JButton("Thêm");
			insert.setBounds(573, 418, 77, 23);
			getContentPane().add(insert);
			insert.addActionListener(this);
			delete = new JButton("Xóa");
			delete.setBounds(486, 418, 77, 23);
			getContentPane().add(delete);
			delete.addActionListener(this);
			edit = new JButton("Sửa");
			edit.setBounds(411, 418, 65, 23);
			getContentPane().add(edit);
			edit.addActionListener(this);
			
			lblNewLabel_2 = new JLabel("Qu\u1EA3n l\u00FD m\u01B0\u1EE3n tr\u1EA3");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_2.setBounds(378, 38, 197, 51);
			getContentPane().add(lblNewLabel_2);
			p.setLayout(null);
			getContentPane().add(p);
			
			lblNewLabel_1 = new JLabel("Backgr");
			lblNewLabel_1.setIcon(new ImageIcon(Quanlimuontra.class.getResource("/image/nen-background-trang-dep-va-don-gian_110344503.jpg")));
			lblNewLabel_1.setBounds(0, -426, 976, 474);
			p.add(lblNewLabel_1);
			reload();
			model = new DefaultTableModel(vData, vTitle);
			tb = new JTable(model);
			tb.addMouseListener(this);
			tableResult = new JScrollPane(tb);
			tableResult.setBounds(44, 100, 861, 271);
			this.getContentPane().add(tableResult);
			
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(Quanlimuontra.class.getResource("/image/nen-background-trang-dep-va-don-gian_110344503.jpg")));
			lblNewLabel.setBounds(0, 0, 956, 463);
			getContentPane().add(lblNewLabel);
			this.setSize(949,532);
			this.setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu mnNewMenu = new JMenu("Hệ thống");
			menuBar.add(mnNewMenu);
			
			JMenuItem mntmNewMenuItem = new JMenuItem("Đăng xuất");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					view.test login = new view.test();
					login.setVisible(true);
					dispose();
				}
			});
			mnNewMenu.add(mntmNewMenuItem);
			
			JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
			mntmNewMenuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(ABORT);
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
			ResultSet rst = stm.executeQuery("select * from bangmuontra");
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
			String sql = "Delete from bangmuontra where maphieu = \""+st.elementAt(0) + "\"";
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
			new UpdateForm3("Insert form", this,"","","","","","");
		}
		// khi an edit

		if (e.getActionCommand().equals("Edit")) {
			Vector st = (Vector)vData.elementAt(selectrow);
			
			new UpdateForm3("Edit form",this,(String)st.elementAt(0),(String)st.elementAt(1),(String)st.elementAt(2),(String)st.elementAt(3),(String)st.elementAt(4),(String)st.elementAt(5));
		}
		}
	
	public void mouseClicked(MouseEvent e) {
		selectrow = tb.getSelectedRow();
	}

	public static void main(String[] args) {
		new Quanlimuontra("quanlimuontra");
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

class UpdateForm3 extends JFrame implements ActionListener {
	JLabel maphieulb;
	JTextField maphieu;
	
	JLabel madocgialb;
	JTextField madocgia;
	
	JLabel masachlb;
	JTextField masach;

	JLabel ngaymuonlb;
	JTextField ngaymuon;

	JLabel ngaytralb;
	JTextField ngaytra;

	JLabel trangthaisachlb;
	JTextField trangthaisach;

	
	
	
	
	
	JLabel errorlb;
	JLabel errordetails;
	
	JButton ok;
	JButton cancel;
	
	
	
	Quanlimuontra mst;
	String id;
	

	public   UpdateForm3(String s, Quanlimuontra aa, String mp,String mdg , String ms, String nm, String nt, String tts)
	{
		super(s);
		mst = aa;
		Container cont = this.getContentPane();
		cont.setLayout(new GridLayout(24,2));
		
		maphieulb = new JLabel("maphieu");
		maphieu = new JTextField(mp);
		cont.add(maphieulb);
		cont.add(maphieu);
		
		madocgialb = new JLabel("madocgia");
		madocgia = new JTextField(mdg);
		cont.add(madocgialb);
		cont.add(madocgia);
		
		masachlb = new JLabel("masach");
		masach = new JTextField(ms);
		cont.add(masachlb);
		cont.add(masach);
		
		ngaymuonlb = new JLabel("ngaymuon");
		ngaymuon = new JTextField(nm);
		cont.add(ngaymuonlb);
		cont.add(ngaymuon);	
		
		ngaytralb = new JLabel("ngaytra");
		ngaytra = new JTextField(nt);
		cont.add(ngaytralb);
		cont.add(ngaytra);
		
		trangthaisachlb = new JLabel("trangthaisach");
		trangthaisach = new JTextField(tts);
		cont.add(trangthaisachlb);
		cont.add(trangthaisach);	
		
		
		
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
		id = mp;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Ok")) {
			insertDB();
		} else
			this.dispose();
	}

	public void insertDB()
	{
		if(maphieu.getText().equals("")|| madocgia.getText().equals("")||masach.getText().equals("")||ngaymuon.getText().equals("")||ngaytra.getText().equals("")||trangthaisach.getText().equals(""))
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
				String mp = maphieu.getText();
				String mdg = madocgia.getText();
				String ms = masach.getText();
				String nm = ngaymuon.getText();
				
				String nt = ngaytra.getText();
				String tts = trangthaisach.getText();
				
				String sql=" ";
				
				if(this.getTitle().equals("Insert form"))
					sql = "insert into bangmuontra (MAPHIEU,MADOCGIA,MASACH,NGAYMUON,NGAYTRA,TRANGTHAISACH)" +
							" values ('"+mp+"','"+mdg+"','"+ms+"','"+nm+"','"+nt+"','"+tts+"')";
				else
					sql = "update bangmuontra set MAPHIEU= '"+ms+"',MADOCGIA ='"+mdg+"',MASACH='"+ms+"',NGAYMUON='"+nm+"',NGAYTRA='"+nt+"',TRANGTHAISACH='"+tts+"' where MAPHIEU= '" +id+"'";
				
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

