<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.sql.*,controller.*,db_access.*,entity.*,util.*"%>
<%@ include file="topFrame.jsp" %>
<%@ include file="vendorMenu.jsp"%>
<html>
<head>
<link href="webDesign.css" rel="stylesheet" type="text/css"/>
<title>HMarket</title>
</head>
<body>
<div class="etc_window">
	<%
		Vendor v = (Vendor)session.getAttribute("user");
	%>
		<h2>[내 정보]</h2><br> 
		아이디 : <%=v.getVid()%><br><br> 
		패스워드 : <%=v.getPassword()%><br><br> 
		이름 : <%=v.getVname()%><br><br>
		주소 : <%=v.getAddress()%><br><br> 
		연락처 : <%=v.getPhoneNo()%><br><br><br>
		<button onclick="location.href='vendorLoginSuccess.jsp'" class="etc_Button">홈으로</button>
</div>
</body>
</html>