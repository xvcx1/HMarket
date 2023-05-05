package entity;

public class Purchase {
	private int orderNo;
	private String cid;
	private int pno;
	private int orderCount;
	private int paymentAmount;
	private String orderDate;
	private String paymentMethod;
	private String orderStatus;
	
	private Product product; // 주문과 연관된 상품
	
	public Purchase(int orderNo, String cid, int pno, int orderCount, int paymentAmount, String orderDate, String paymentMethod, String orderStatus){
		this.orderNo = orderNo; this.cid = cid; this.pno = pno; 
		this.orderCount = orderCount; this.paymentAmount = paymentAmount;
		this.orderDate = orderDate; this.paymentMethod = paymentMethod;
		this.orderStatus = orderStatus;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}
