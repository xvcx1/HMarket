package db_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import entity.Customer;
import entity.Deliver;
import entity.Product;
import entity.Purchase;
import entity.Vendor;
import util.DateUtils;


/**
 * [ DB ] 클래스: 학생 등록, 학생 검색 등을 선택적으로 수행하는 클래스
 *   
 *  o 이 클래스의 모든 멤버변수와 메소드는 static으로 정의
 *  
 *  o main() 메소드에서 DB 클래스 객체 생성하지 않고서 
 *    static 메소드를 호출하여 작업 수행
 */

public class DB {

	static Connection con  = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	public static void getConnection() {
        
		String database 	= "hmarketproject"; 
        String user_name    = "root";
        String password     = "onlyroot";        
             
        // 드라이버 로딩
        try {
            	Object o = Class.forName("com.mysql.jdbc.Driver");
        } catch ( java.lang.ClassNotFoundException e ) {
                System.err.println("  !! <JDBC 오류 > Driver load 오류: " + e.getMessage() );
                e.printStackTrace();
        }
        
        try {
                // 연결하기 - 주어진 serverID의 서버와 연결시키기 위해 server[] 배열 사용
                con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database + "?characterEncoding=euckr", user_name, password);
                // Statement 생성
                stmt = con.createStatement();
 
		} catch( SQLException ex ) {               
                System.err.println("conn 오류:" + ex.getMessage() );
                ex.printStackTrace();
		} 
	}
	
	public static void close() { // 닫기 수행하는 메소드	
    	try {
     		if( stmt != null ) stmt.close();
    		if( con != null ) con.close();
                    
    	} catch (SQLException ex ) {}; 	
    }
	
	public static ResultSet select(String sql) { // SELECT SQL문을 최종적으로 실행하는 최상위 모듈
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}
	
	public static int update(String sql) { // INSERT, UPDATE, DELETE SQL문을 최종적으로 실행하는 최상위 모듈
		int resultCnt = 0;
		try {
			resultCnt = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		}
		return resultCnt;
	}
	
	public static String getStringValueInRs(ResultSet rs, String attr) { // rs값과 원하는 속성을 매개변수로 입력하면 해당 속성의 값(문자열)이 반환되는 메소드
		String result = "";
		try {
			while(rs.next()) {
				result = rs.getString(attr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public static int getIntValueInRs(ResultSet rs, String attr) { // rs값과 원하는 속성을 매개변수로 입력하면 해당 속성의 값(정수)이 반환되는 메소드
		int result = 0;
		try {
			while(rs.next()) {
				result = rs.getInt(attr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return result;
	}
	
	public static Customer searchCustomer_tmp(String cid, String password) {
		String sql = "select * from customer where cid = '" +cid+ "' and password = '" +password+ "';"; // 매개변수로 받은 아이디와 패스워드가 일치하는 회원을 select로 검색
		rs = select(sql);
		try {
			while (rs.next()) { // 한줄씩 읽음 (rs가 받은 결과값이 없을경우 이 while문은 작동되지 않는다)
				Customer c = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)); // rs가 받은 값들을 이용해서 c객체에 저장
				return c;
			}
		} catch (SQLException e) {
			System.err.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet getAllProduct() { // 모든 상품 rs값 반환하는 메소드
		String sql = "select * from product";
		rs = select(sql);
		return rs;
	}
	
	public static ResultSet getProductByPno(int pno) { // pno주어지면 해당 상품 정보들 출력하는 메소드
		String sql = "select * from product where pno = '"+pno+"';";
		rs = select(sql);
		return rs;
	}
	
	public static Vector<Purchase> getVectorSelectedPurchase(String sql){ // 주어진 sql문에 준수한 구입 정보들을 구입 클래스 백터 객체에 담아서 반환
		Vector<Purchase> purchases = new Vector<Purchase>();

		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int orderNo = rs.getInt("orderNo");
				String cid = rs.getString("cid");
				int pno = rs.getInt("pno");
				int orderCount = rs.getInt("orderCount");
				int paymentAmount = rs.getInt("paymentAmount");
				String orderDate = rs.getString("orderDate");
				String paymentMethod = rs.getString("paymentMethod");
				String orderStatus = rs.getString("orderStatus");
				Purchase pc = new Purchase(orderNo, cid, pno, orderCount, paymentAmount, orderDate, paymentMethod, orderStatus);
				purchases.add(pc);
			}
		}
		catch(SQLException e) {
			System.err.println("SQL문 결과 추출 에러:" + e.getMessage() );
		}
		return purchases;
	}
	
	
	public static ResultSet getLoginedPurchaseInfo(String cid) { // 로그인한 고객이 주문조회 할때 출력할 정보들 rs값으로 반환 (쿼리에 조인 사용)
		String sql = "select orderNo, pname, orderCount, paymentAmount, orderDate, paymentMethod, orderStatus"
				+ " from purchase, product where purchase.pno = product.pno and cid = '"+cid+"';";
		rs = select(sql);
		return rs;
	}
	
	public static ResultSet getLoginedCanceledPurchaseInfo(String cid) { // 로그인한 고객이 취소된 주문을 조회 할때 출력할 정보들 rs값으로 반환 (쿼리에 조인 사용)
		String sql = "select cancelDate, cancelResult, pname, orderCount, paymentAmount, orderDate, paymentMethod"
				+ " from canceled_purchase, product where canceled_purchase.pno = product.pno and cid = '"+cid+"';";
		rs = select(sql);
		return rs;
	}
	
	public static ResultSet getLoginedDeliverInfo(String cid) { // 로그인한 고객의 배송정보를 rs값으로 반환
		String sql = "select dno, vname, pno, sendDate, arrivalDate, deliveryStatus from deliver, vendor where deliver.vid = vendor.vid and cid = '"+cid+"';";
		rs = select(sql);
		return rs;
	}
	
	public static ResultSet getLoginedVendorProduct(String vid) { // 로그인한 업체의 등록상품들을 rs값으로 반환
		String sql = "select pno, pname, price, enrolDate from product where vid = '"+vid+"';";
		rs = select(sql);
		return rs;
	}
	
	public static ResultSet getRequestedOrder(String vid) { // 로그인한 업체가 요청받은 주문건 rs값으로 반환
		String sql = "select orderNo, purchase.pno, orderCount, orderDate, paymentMethod from purchase, product where purchase.pno = product.pno and vid = '"+vid+"' and orderStatus = '주문처리전';";
		rs = select(sql);
		return rs;
	}
	
	public static String getCidByOrderNo(int orderNo) { // 주문번호 주어지면 해당 주문을 한 고객아이디를 반환
		String sql = "select cid from purchase where orderNo = '"+orderNo+"';";
		rs = select(sql);
		return getStringValueInRs(rs, "cid");
	}
	
	public static String getPnameByPno(String pno) { // pno로 pname 찾아서 반환
		String sql = "select pname from product where pno = '"+pno+"';";
		rs = select(sql);
		return getStringValueInRs(rs, "pname");
	}
	
	public static int getPnoByOrderNo(int orderNo) { // 주문번호 주어지면 해당 주문에서의 상품번호를 반환
		String sql = "select pno from purchase where orderNo = '"+orderNo+"';";
		rs = select(sql);
		return getIntValueInRs(rs, "pno");
	}

	public static int insertCustomerObject(Customer c) { // 회원 클래스 객체를 받아서 DB에 고객 회원 추가하는 메소드
		String sql = "insert into customer values('"+c.getCid()+"', '"+c.getPassword()+"', '"+c.getCname()+"', '"+c.getAddress()+"', '"+c.getPhoneNo()+"');"; // insert sql문
		int cnt = update(sql);
		return cnt;
	}
	
	public static Customer searchCustomer(String cid, String password) { // DB에서 입력된 아이디와 패스워드가 일치하는 회원을 찾아서 반환하는 메소드 (로그인 기능에 쓰임)
		String sql = "select * from customer where cid = '" +cid+ "' and password = '" +password+ "';"; // 매개변수로 받은 아이디와 패스워드가 일치하는 회원을 select로 검색
		try {
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) { // 한줄씩 읽음 (rs가 받은 결과값이 없을경우 이 while문은 작동되지 않는다)
				Customer c = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)); // rs가 받은 값들을 이용해서 c객체에 저장
				return c;
			}
		}
		catch (SQLException e) {
			System.err.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null; // while문이 작동안되면 바로 null을 반환한다
	}
	
	public static int insertVendorObject(Vendor v) { // 판매업체 클래스 객체를 받아서 DB에 회원 추가하는 메소드
		String sql = "insert into vendor values('"+v.getVid()+"', '"+v.getPassword()+"', '"+v.getVname()+"', '"+v.getAddress()+"', '"+v.getPhoneNo()+"');"; // insert sql문
		int cnt = update(sql);
		return cnt;
	}
	
	public static Vendor searchVendor(String vid, String password) { // DB에서 입력된 아이디와 패스워드가 일치하는 판매업체 회원을 찾아서 반환하는 메소드 (판매업체 로그인 기능에 쓰임)
		String sql = "select * from vendor where vid = '" +vid+ "' and password = '" +password+ "';"; // 매개변수로 받은 아이디와 패스워드가 일치하는 회원을 select로 검색
		try {
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) { // 한줄씩 읽음 (rs가 받은 결과값이 없을경우 이 while문은 작동되지 않는다)
				Vendor v = new Vendor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)); // rs가 받은 값들을 이용해서 c객체에 저장
				return v;
			}
		}
		catch (SQLException e) {
			System.err.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return null; // while문이 작동안되면 바로 null을 반환한다
	}

	
	public static int insertProductObject(Product p) { // 상품 테이블에 새로운 상품 객체 삽입
		String sql = "insert into product values('"+p.getPno()+"', '"+p.getPname()+"', '"+p.getPrice()+"', '"+p.getVid()+"', '"+p.getEnrolDate()+"')";
		int cnt = update(sql);
		return cnt;
	}
	
	public static int insertDeliverObject(Deliver d) { // 배송 테이블에 새로운 배송 객체 삽입
		String sql = "insert into deliver values('"+d.getDno()+"', '"+d.getVid()+"', '"+d.getCid()+"', '"+d.getPno()+"', '"+d.getSendDate()+"', '"+d.getArrivalDate()+"', '"+d.getDeliveryStatus()+"')";
		int cnt = update(sql);
		return cnt;
	}
		
	public static int insertPurchaseObject(Purchase p) { // 구입 테이블에 새로운 구입 객체 삽입
		String sql = "insert into purchase values('"+p.getOrderNo()+"', '"+p.getCid()+"', '"+p.getPno()+"', '"+p.getOrderCount()+"', '"+p.getPaymentAmount()+"', '"+p.getOrderDate()+"', '"+p.getPaymentMethod()+"', '"+p.getOrderStatus()+"');";
		int cnt = update(sql);
		return cnt;
	}
	
	public static int calculatePaymentAmount(int pno, int orderCount) { // 상품번호와 구입 개수를 매개변수로 받아 총 결제금액 계산해서 반환하는 메소드
		String sql = "select price from product where pno = '"+pno+"';";
		rs = select(sql);
		int price = getIntValueInRs(rs, "price");
		int paymentAmount = price * orderCount;
		return paymentAmount;
	}
	
	public static int deletePurchaseObject(int orderNo) { // 주문 삭제 처리 메소드
		String sql = "delete from purchase where orderNo = '"+orderNo+"'";
		int cnt = update(sql);
		return cnt;
	}
	
	public static int deleteProductObject(int pno) { // 상품 삭제 처리 메소드
		String sql = "delete from product where pno = '"+pno+"'";
		int cnt = update(sql);
		return cnt;
	}
	
	public static int insertCanceledPurchaseObject(int orderNo) { // 주문 삭제 후 삭제 정보를 canceled_purchase 테이블에 삽입
		Vector<Purchase> p = getVectorSelectedPurchase("select * from purchase where orderNo = '"+orderNo+"';");
		String sql = "insert into canceled_purchase values('"+DateUtils.getCurrntTimePlusDay(0)+"', '"+"취소완료"+"', '"+p.get(0).getCid()+"', '"+p.get(0).getPno()+"', '"+p.get(0).getOrderCount()+"', '"+p.get(0).getPaymentAmount()+"', '"+p.get(0).getOrderDate()+"', '"+p.get(0).getPaymentMethod()+"', '"+p.get(0).getOrderStatus()+"');";
		int cnt = update(sql);
		return cnt;
	}
	
	public static boolean isBeforeProcessOrder(int orderNo) { // 주문번호가 주어지면 해당 주문의 처리상태가 "주문처리전" 상태이면 true를 반환
		String sql = "select orderStatus from purchase where orderNo = '"+orderNo+"';";
		try {
			String result = "";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result = rs.getString("orderStatus");
			}
			if(result.equals("주문처리전")) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public static int getNewPurchaseOrderNo() { // 새로운 구입 객체가 생성될때 새롭게 생성될 주문번호를 계산해서 반환
		try {
			rs = stmt.executeQuery("select * from purchase;");
			rs.last(); // 마지막 커서로 이동
			int lastOrderNo = rs.getInt("orderNo");
			return lastOrderNo + 1; // 마지막 주문번호 + 1을 반환
		} catch (SQLException e) {
			System.err.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getNewProductNo() { // 새로운 상품 객체가 생성될때 새롭게 생성될 상품번호를 계산해서 반환
		try {
			rs = stmt.executeQuery("select * from Product;");
			rs.last(); // 마지막 커서로 이동
			int lastPno = rs.getInt("pno");
			return lastPno + 100; // 마지막 번호 + 100을 반환
		} catch (SQLException e) {
			System.err.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getNewDeliverNo() { // 새로운 배송 객체가 생성될때 새롭게 생성될 송장번호를 계산해서 반환
		try {
			rs = stmt.executeQuery("select * from deliver;");
			rs.last(); // 마지막 커서로 이동
			int lastDno = rs.getInt("dno");
			return lastDno + 100; // 마지막 번호 + 100을 반환
		} catch (SQLException e) {
			System.err.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	
//	public static int getNewNo(String table, int addValue) { // 번호 자동 생성 메소드 통합
//		try {
//			rs = stmt.executeQuery("select * from '"+table+"';");
//			rs.last(); // 마지막 커서로 이동
//			int lastNo = rs.getInt("no");
//			return lastNo + addValue; // 마지막 번호 + addValue 반환
//		} catch (SQLException e) {
//			System.err.println("SQL 오류 : " + e.getMessage());
//			e.printStackTrace();
//		}
//		return 0;
//	}

	
	public static void updateOrderStatus(int orderNo) { // 배송처리하면 주문처리현황 업데이트 해주는 메소드 (주문번호를 매개변수로 받음)
		String sql = "update purchase set orderStatus = '"+"주문처리완료"+"' where orderNo = '"+orderNo+"';";
		int cnt = update(sql);
	}
}
