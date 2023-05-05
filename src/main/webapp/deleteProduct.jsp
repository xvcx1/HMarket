<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="controller.*"%>

<html>
<head>
<title>HMarket</title>
</head>
<body>
	<%
		String selectedProduct = request.getParameter("selectProduct");
		if(ProductMgmt.doDeleteProductProcess(response, selectedProduct))
			out.print("<script>location.href = 'vendorLoginSuccess.jsp';</script>");
	%>
</body>
</html>