<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="controller.*"%>>

<html>
<head>

<title>HMarket</title>
</head>
<body>
	<%
		Object loginedUser = session.getAttribute("user");
		Object selectedPno = session.getAttribute("selectedPno");
		String inputCnt = request.getParameter("input_cnt");
		String paymentMethod = request.getParameter("paymentMethod");
		
		if (PurchaseMgmt.doOrderProcess(response, loginedUser, selectedPno, inputCnt, paymentMethod))
			 out.print("<script>location.href = 'customerLoginSuccess.jsp';</script>"); // 구매 후 홈화면으로
			 session.setAttribute("buyPno", null); // 세션 초기화
	%>
</body>
</html>