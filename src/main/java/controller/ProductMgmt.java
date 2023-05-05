package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import db_access.DB;
import entity.Product;
import entity.Vendor;
import util.DateUtils;
import util.ScriptUtils;

public class ProductMgmt {
	// 상품 등록 처리 메소드
	public static boolean doEnrolProductProcess(HttpServletResponse response, Object loginedUser, String inputPname, String inputPrice) {
    	
		try {
			Vendor vLogined = (Vendor)loginedUser;
			
	    	if(inputPname.equals("") || inputPrice.equals("")){
	    		ScriptUtils.alertAndBackPage(response, "상품정보를 입력하세요");
	    		return false;
	    	}
	    	
	    	else {
	    		int price = 0;
	    		try{
	    			price = Integer.parseInt(inputPrice);
	    		}
	    		catch(Exception e){
	    			ScriptUtils.alertAndBackPage(response, "숫자를 입력하세요");
		       		return false;
	    		}
	    		Product newP = new Product(DB.getNewProductNo(), inputPname, price, vLogined.getVid(), DateUtils.getCurrntTimePlusDay(0));
	    		
	    		int cnt = DB.insertProductObject(newP);
	    		if(cnt == 1){
	    			ScriptUtils.alert(response, "상품을 등록하였습니다");
	    			return true;
	    		}
	    		else{
	    			ScriptUtils.alertAndBackPage(response, "상품등록에 실패하였습니다");
	        		return false;
	    		}
	    	}
		} catch(IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	// 상품 삭제 처리 메소드
	public static boolean doDeleteProductProcess(HttpServletResponse response, String selectedProduct) {

		try {
			if(selectedProduct == null){ // 삭제할 상품을 선택 안했을 경우
				ScriptUtils.alertAndBackPage(response, "삭제할 상품을 선택하지 않았습니다");
				return false;
			}
			else{
				int pno = Integer.parseInt(selectedProduct); // 선택된 상품번호를 가져옴 (int형으로 Cast)
	//			int cnt = DB.insertDeletedPurchaseObject(pno); // 삭제 전, 상품정보 삭제테이블에 저장 ??
				int cnt = DB.deleteProductObject(pno); // 상품 정보 삭제
					
				if(cnt == 1){ // 성공적으로 삭제
					ScriptUtils.alert(response, "상품 삭제가 완료되었습니다");
					return true;
				}
				else{
					ScriptUtils.alertAndBackPage(response, "상품삭제에 실패하였습니다");
					return false;
				}
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
