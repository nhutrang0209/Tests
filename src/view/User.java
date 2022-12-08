package view;

public class User {
	public User(String email, String matkhau, String phanloai) {
		super();
		this.email = email;
		this.matkhau = matkhau;
		this.phanloai = phanloai;
	}

	public User() {
		super();
	}

	private String email, matkhau, phanloai;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getPhanloai() {
		return phanloai;
	}

	public void setPhanloai(String phanloai) {
		this.phanloai = phanloai;
	}
	
}
