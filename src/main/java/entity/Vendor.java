package entity;

public class Vendor extends User{
	private String vid;
	private String vname;
	
	public Vendor(String vid, String password, String vname, String address, String phoneNo){ // 객체생성자
		super(password, address, phoneNo);
		this.vid = vid; this.vname = vname;
	}
	
	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	@Override
	public String toString() {
		return getVname();
	}
}
