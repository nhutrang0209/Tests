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

public class Docgia extends JFrame implements ActionListener,
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

	public  Docgia(String s) {
		super(s);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/quanlisach", "root", "");
			stm = conn.createStatement();
			JPanel p = new JPanel();
                        insert = new JButton("ThÃªm");
			insert.addActionListener(this);
			edit = new JButton("Sá»­a");
			edit.addActionListener(this);
			delete = new JButton("XÃ³a");
			delete.addActionListener(this);
			p.add(edit);
			p.add(delete);
			p.add(insert);
			this.add(p,"South");
			reload();
			model = new DefaultTableModel(vData, vTitle);
			tb = new JTable(model);
			tb.addMouseListener(this);
			tableResult = new JScrollPane(tb);
			this.getContentPane().add(tableResult, "North");
			this.setSize(500,500);
			this.setLocation(200, 500);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

	// XÃ³a dá»¯ liá»‡u
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
		// Khi áº¥n XÃ³a
		if (e.getActionCommand().equals("XÃ³a")) {
			delete();

		}
		// Khi áº¥n thÃªm
		if (e.getActionCommand().equals("ThÃªm"))
		{
			
			new UpdateForm("Insert form", this,"","","","","","","");
		}
		// Khi áº¥n sá»­a

		if (e.getActionCommand().equals("Sá»­a")) {
			Vector st = (Vector)vData.elementAt(selectrow);
			
			new UpdateForm("Edit form",this,(String)st.elementAt(0),(String)st.elementAt(1),(String)st.elementAt(2),(String)st.elementAt(3),(String)st.elementAt(4),(String)st.elementAt(5),(String)st.elementAt(6));
		}
		}
	
	public void mouseClicked(MouseEvent e) {
		selectrow = tb.getSelectedRow();
	}

	public static void main(String[] args) {
		new  Docgia(" quanlidocgia");
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
	
	
	
	 Docgia mst;
	String id;
	

	public   UpdateForm(String dg,  Docgia bb, String mdg, String tdg, String ns, String nois, String dc, String dt, String mldg)
	{
		super(dg);
		mst = bb;
		Container cont = this.getContentPane();
		cont.setLayout(new GridLayout(24,2));
		
		madocgialb = new JLabel("MÃ£ Ä‘á»™c giáº£");
		madocgia = new JTextField(mdg);
		cont.add(madocgialb);
		cont.add(madocgia);
		
		tendocgialb = new JLabel("TÃªn Ä‘á»™c giáº£");
		tendocgia = new JTextField(tdg);
		cont.add(tendocgialb);
		cont.add(tendocgia);
		
		ngaysinhlb = new JLabel("NgÃ y sinh");
		ngaysinh = new JTextField(ns);
		cont.add(ngaysinhlb);
		cont.add(ngaysinh);
		
		noisinhlb = new JLabel("NÆ¡i sinh");
		noisinh = new JTextField(nois);
		cont.add(noisinhlb);
		cont.add(noisinh);	
		
		diachilb = new JLabel("Ä�á»‹a chá»‰");
		diachi = new JTextField(dc);
		cont.add(diachilb);
		cont.add(diachi);
		
		dienthoailb = new JLabel("Ä�iá»‡n thoáº¡i");
		dienthoai = new JTextField(dt);
		cont.add(dienthoailb);
		cont.add(dienthoai);	
		
		maloaidocgialb = new JLabel("MÃ£ loáº¡i Ä‘á»™c giáº£");
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
		this.setSize(600,800);
                this.setLocationRelativeTo(null);
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
					sql = "insert into docgia (MADOCGIA, TENDOCGIA, NGAYSINH, NOISINH, DIACHI, DIENTHOAI, MALOAIDOCGIA)" +
							" values ('"+mdg+"','"+tdg+"','"+ns+"','"+nois+"','"+dc+"',"+dt+",'"+mldg+"')";
				else
					sql = "update docgia set MADOGIA= '"+mdg+"',TENDOCGIA ='"+tdg+"',NGAYSINH='"+ns+"',NOISING='"+nois+"',DIACHI='"+dc+"',DIENTHOAI= "+dt+",MALOAIDOCGIA='"+mldg+"' where MADOCGIA= '" +id+"'";
				
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
    
