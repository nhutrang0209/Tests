package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import program.ConnectMySQL;
import view.User;

public class NguoiDungDao {
	public User checkLogin(String email, String matkhau) throws Exception{
		String sql = "select * from dangnhap where email = '" + email + "' and matkhau = '"
				+ matkhau + "'";
		try(
				Connection  connection = ConnectMySQL.openConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				){
			pstmt.setString(1, email);
			pstmt.setString(2, matkhau);
			
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					User user = new User();
					user.setEmail(rs.getString(email));
					user.setMatkhau(rs.getString(matkhau));
					user.setPhanloai(rs.getString("phanloai"));
					return user;
				}
			}
		}
		User user1= new User();
		return user1;
	}
}
