package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import entity.Customer;
import entity.User;
import entity.Vendor;
import db_access.DB;
import util.ScriptUtils;

public class UserMgmt {
	// 로그인 처리 메소드
	public static User doLoginProcess(HttpServletResponse response, String inputID, String inputPW, String loginType) {
    	
		DB.getConnection();
		
		try {
			if (inputID.equals("") || inputPW.equals("")) { // 아이디와 패스워드 입력하지 않았을 경우
	       		ScriptUtils.alertAndBackPage(response, "아이디 또는 비밀번호를 입력하지 않았습니다");
	       		return null;
	        }
	   		
	   		else if(loginType == null){ // 라디오 버튼 선택하지 않았을 경우
	   			ScriptUtils.alertAndBackPage(response, "고객과 판매업체중 선택하지 않았습니다");
	   			return null;
	   		}
			
			if(loginType.equals("customer")){ // 고객 로그인
				
				Customer customer = DB.searchCustomer(inputID, inputPW);
				
				if(customer == null) {
					ScriptUtils.alertAndBackPage(response, "로그인에 실패하였습니다");
					return null;
				}
				else {
					ScriptUtils.movePage(response, "customerLoginSuccess.jsp");
					return customer;
				}
			}
			
			else if(loginType.equals("vendor")) { // 판매업체 로그인
				
				Vendor vendor = DB.searchVendor(inputID, inputPW);
				
				if(vendor == null) {
					ScriptUtils.alertAndBackPage(response, "로그인에 실패하였습니다");
					return null;
				}
				else {
					ScriptUtils.movePage(response, "vendorLoginSuccess.jsp");
					return vendor;
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// 회원가입 처리 메소드
	public static boolean doJoinmembershipProcess(HttpServletResponse response, String joinType, String inputID, String inputPW, String inputName, String inputAddress, String inputPhoneNo) {
    	
		DB.getConnection();
		
		try {
			if(inputID.equals("") || inputPW.equals("") || inputName.equals("") || inputAddress.equals("") || inputPhoneNo.equals("")){
				ScriptUtils.alertAndBackPage(response, "가입 정보를 입력하지 않은 란이 존재합니다");
		   		return false;
			}
			
			if(joinType == null){
				ScriptUtils.alertAndBackPage(response, "고객과 판매업체 중 선택하세요");
				return false;
			}
			
			else if(joinType.equals("customer")){ // 고객 회원가입
				
				Customer newC = new Customer(inputID, inputPW, inputName, inputAddress, inputPhoneNo);
				int cnt = DB.insertCustomerObject(newC);
				
				if(cnt == 1){
					ScriptUtils.alertAndMovePage(response, "회원가입이 완료되었습니다", "index.html");
					return true;
				}
				
				else{
					ScriptUtils.alertAndBackPage(response, "중복되는 아이디가 존재합니다");
					return false;
				}
			}
			
			else { // 판매업체 회원가입
				
				Vendor newV = new Vendor(inputID, inputPW, inputName, inputAddress, inputPhoneNo);
				int cnt = DB.insertVendorObject(newV);
				
				if(cnt == 1){
					ScriptUtils.alertAndMovePage(response, "회원가입이 완료되었습니다", "index.html");
					return true;
				}
				
				else{
					ScriptUtils.alertAndBackPage(response, "중복되는 아이디가 존재합니다");
					return false;
				}
				
			}
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
}
