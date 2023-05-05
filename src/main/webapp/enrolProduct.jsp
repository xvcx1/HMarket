<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="controller.*"%>
<%request.setCharacterEncoding("UTF-8");%>
<html>
  <head>
    <title>HMarket</title>
  </head>
  <body>
    <%
        String inputPname = request.getParameter("input_pname");
        String inputPrice = request.getParameter("input_price");
		if (ProductMgmt.doEnrolProductProcess(response, session.getAttribute("user"), inputPname, inputPrice))
			out.print("<script>location.href = 'vendorLoginSuccess.jsp';</script>");
    %>
  </body>
</html>
