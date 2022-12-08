package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import program.ConnectMySQL;
import view.User;

public class NguoiDungDao1 {
	public User checkLogin(String email, String matkhau) {
		String sql = "select * from dangnhap where email = '" + email + "' and matkhau = '"
				+ matkhau + "'";
		Connection  connection = ConnectMySQL.openConnection();
		PreparedStatement pstmt = null;
				
     return null;
	}
}	
