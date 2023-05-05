package entity;

public class CanceledPurchase extends Purchase{
	private String cancelDate;
	private String cancelResult;
	
	public CanceledPurchase(String cancelDate, String cancelResult, int orderNo, String cid, int pno, int orderCount, int paymentAmount, String orderDate, String paymentMethod, String orderStatus) {
		super(orderNo, cid, pno, orderCount, paymentAmount, orderDate, paymentMethod, orderStatus); // Purchase의 생성자 먼저 호출
		this.cancelDate = cancelDate; this.cancelResult = cancelResult;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCancelResult() {
		return cancelResult;
	}

	public void setCancelResult(String cancelResult) {
		this.cancelResult = cancelResult;
	}

}
