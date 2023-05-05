package entity;

public class Product {
	int pno;
	String pname;
	int price;
	String vid;
	String enrolDate;
	
	public Product(int pno, String pname, int price, String vid, String enrolDate) {
		this.pno = pno; this.pname = pname;
		this.price = price; this.vid = vid;
		this.enrolDate = enrolDate;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getEnrolDate() {
		return enrolDate;
	}

	public void setEnrolDate(String enrolDate) {
		this.enrolDate = enrolDate;
	}
	
	
}
