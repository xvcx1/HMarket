<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="controller.*"%>
<html>
<head>
<title>HMarket</title>
</head>
<body>
	<%
		session.setAttribute("user", null);
		out.print("<script>location.href='index.html';</script>");
	%>
</body>
</html>