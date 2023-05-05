<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="controller.*"%>

<html>
<head>
<title>HMarket</title>
</head>
<body>
	<%
		String selectedOrder = request.getParameter("selectOrder");
		if (PurchaseMgmt.doCancelOrderProcess(response, selectedOrder))
			out.print("<script>location.href = 'checkOrder.jsp';</script>"); // 새로고침을 위해 뒤로가기말고 새 링크를 불러온다.
	%>
</body>
</html>