package entity;

public abstract class User {
	private String password;
	private String address;
	private String phoneNo;

	public User(String password, String address, String phoneNo) {
		this.password = password;
		this.address = address;
		this.phoneNo = phoneNo;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
