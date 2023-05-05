package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import db_access.DB;
import entity.Customer;
import entity.Purchase;
import util.DateUtils;
import util.ScriptUtils;

public class PurchaseMgmt {
	// 상품 주문 처리 메소드
	public static boolean doOrderProcess(HttpServletResponse response, Object loginedUser, Object selectedPno, String inputCnt, String paymentMethod) {
		
		Customer c = (Customer)loginedUser;
		String loginedCid = c.getCid(); // 로그인한 고객 아이디 가져오기
		int orderNo = DB.getNewPurchaseOrderNo(); // 주문번호 생성
		int pno = (Integer)selectedPno; // 세션에 저장해둔 구매할 상품의 상품번호 가져오기
		
		String pmData = "";
		String nowDate = DateUtils.getCurrntTimePlusDay(0); // 현재날짜 저장
		
		try {
			if(inputCnt == null){
				ScriptUtils.alertAndBackPage(response, "구매 개수를 입력하세요");
		        return false;
			}
			else if(paymentMethod.equals("")){
			    ScriptUtils.alertAndBackPage(response, "결제방식을 선택하세요");
		        return false;
			}
			else{
				int orderCount = 0;
				try{
					orderCount = Integer.parseInt(inputCnt);
				}
				catch(Exception e){
		       		ScriptUtils.alertAndBackPage(response, "숫자를 입력하세요");
	        		return false;
				}
				int paymentAmount = DB.calculatePaymentAmount(pno, orderCount);
				if(paymentMethod.equals("card")){ 	// 신용카드 선택
					pmData = "신용카드";
				}
				else if(paymentMethod.equals("deposit")){ 	// 무통장입금 선택
					pmData = "무통장입금";
				}
				else{ 	// 간편결제 선택
					pmData = "간편결제";
				}
				Purchase newP = new Purchase(orderNo, loginedCid, pno, orderCount, paymentAmount, nowDate, pmData, "주문처리전"); // purchase 객체 생성
				DB.insertPurchaseObject(newP); // purchase 테이블에 추가
			    ScriptUtils.alert(response, "상품을 구매하였습니다");
				return true;
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	// 상품 주문 취소 처리 메소드
	public static boolean doCancelOrderProcess(HttpServletResponse response, String selectedOrder) {
		
		try {
			if(selectedOrder == null){ // 취소할 주문을 선택 안했을 경우
	   			ScriptUtils.alertAndBackPage(response, "취소할 주문을 선택하지 않았습니다");
				return false;
			}
			else{
				int orderNo = Integer.parseInt(selectedOrder); // 선택된 주문번호를 가져옴 (int형으로 Cast)
				boolean isBefore = DB.isBeforeProcessOrder(orderNo);
				if(isBefore == true){
					int cnt = DB.insertCanceledPurchaseObject(orderNo); // 삭제 전, 주문정보 취소테이블에 저장
					DB.deletePurchaseObject(orderNo); // 주문 정보 삭제
					
					if(cnt == 1){ // 성공적으로 삽입
						ScriptUtils.alert(response, "주문 취소가 완료되었습니다");
						return true;
					}
					else{
						ScriptUtils.alertAndBackPage(response, "주문취소에 실패하였습니다");
						return false;
					}
				}
				else{
		   			ScriptUtils.alertAndBackPage(response, "이미 발송된 상품의 주문건은 취소가 불가능 합니다. 판매업체에 문의하세요");
					return false;
				}
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
