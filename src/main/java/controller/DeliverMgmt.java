package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import db_access.DB;
import entity.Deliver;
import entity.Vendor;
import util.DateUtils;
import util.ScriptUtils;

public class DeliverMgmt {
	// 배송 처리 메소드
	public static boolean doDeliverProcess(HttpServletResponse response, Object loginedUser, String orderNoS) {
		
		try {
			if(orderNoS == null){
				ScriptUtils.alertAndBackPage(response, "주문건을 선택하세요");
				return false;
			}
			else{
					int orderNo =  Integer.parseInt(orderNoS);
					Vendor v = (Vendor)loginedUser;
					String loginedVid = v.getVid(); // 로그인한 업체 아이디 가져오기
					int dno = DB.getNewDeliverNo(); // 주문번호 생성
					String cid = DB.getCidByOrderNo(orderNo); // 주문한 고객아이디 가져오기
					int pno = DB.getPnoByOrderNo(orderNo);
					String sendDate = DateUtils.getCurrntTimePlusDay(0);
					String arrivalDate = DateUtils.getCurrntTimePlusDay(3); // 현재날짜 저장
					String deliveryStatus = "배송시작";
					
					Deliver newD = new Deliver(dno, loginedVid, cid, pno, sendDate, arrivalDate, deliveryStatus);
					int cnt = DB.insertDeliverObject(newD);
				if(cnt == 1){
					DB.updateOrderStatus(orderNo);
					ScriptUtils.alertAndMovePage(response, "배송처리 완료하였습니다", "checkRequestedOrder.jsp");
					return true;
				}
				else{
					ScriptUtils.alertAndBackPage(response, "배송처리에 실패하였습니다");
					return false;
				}
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
