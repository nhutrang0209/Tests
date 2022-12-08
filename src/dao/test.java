package dao;

public class test {
  public static void main(String[] args) {
	NguoiDungDao dao=  new NguoiDungDao();
//	System.out.print(dao.checkLogin("dieptran@gmail.com", "iloveyou01").getEmail());
    String text= dao.checkLogin("dieptran@gmail.com", "iloveyou01").getEmail();
}
}
