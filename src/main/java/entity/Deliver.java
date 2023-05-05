package entity;

public class Deliver {
	int dno;
	String vid;
	String cid;
	int pno;
	String sendDate;
	String arrivalDate;
	String deliveryStatus;
	
	private Purchase purchase; // 배송과 연관된 구매정보
	
	public Deliver(int dno, String vid, String cid, int pno, String sendDate, String arrivalDate, String deliveryStatus) {
		this.dno = dno; this.vid = vid; this.cid = cid; this.pno = pno; this.sendDate = sendDate;
		this.arrivalDate = arrivalDate; this.deliveryStatus = deliveryStatus;
	}

	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	
}
