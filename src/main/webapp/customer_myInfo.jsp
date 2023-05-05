<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.sql.*,controller.*,db_access.*,entity.*"%>
<%@ include file="customerMenu.jsp" %>
<%@ include file="topFrame.jsp" %>
<html>
<head>
<link href="webDesign.css" rel="stylesheet" type="text/css"/>
<title>HMarket</title>
</head>
<body>
<div class="etc_window">
	<%
		Customer c = (Customer)session.getAttribute("user");
	%>
			<h2>[내 정보]</h2><br> 
			아이디 : <%=c.getCid()%><br><br> 
			패스워드 : <%=c.getPassword()%><br><br> 
			이름 : <%=c.getCname()%><br><br> 
			주소 : <%=c.getAddress()%><br><br> 
			연락처 : <%=c.getPhoneNo()%><br><br><br>
			<button onclick="location.href='checkOrder.jsp'" class="etc_Button">주문조회</button>
			<button onclick="location.href='customerLoginSuccess.jsp'" class="etc_Button">홈으로</button>
		<%
		
	%>
</div>
</body>
</html>