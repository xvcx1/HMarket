package entity;

public class Customer extends User{
	private String cid;
	private String cname;
	
	public Customer(String cid, String password, String cname, String address, String phoneNo){ // 객체생성자
		super(password, address, phoneNo);
		this.cid = cid; this.cname = cname;
	}
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return getCname();
	}
	
}
