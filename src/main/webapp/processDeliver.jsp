<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="controller.*"%>>

<html>
<head>

<title>HMarket</title>
</head>
<body>
	<%
		String orderNoS = request.getParameter("selectOrder");
		if (DeliverMgmt.doDeliverProcess(response, session.getAttribute("user"), orderNoS))
			out.print("<script>location.href = 'checkRequestedOrder.jsp';</script>");
	%>
</body>
</html>