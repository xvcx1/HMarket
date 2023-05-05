<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.sql.*,controller.*,db_access.*,entity.*"%>
<%request.setCharacterEncoding("UTF-8");%>
<html>
  <head>
    <title>HMarket</title>
  </head>
  <body>
    <%	
	    String joinType = request.getParameter("select");
	    String inputID = request.getParameter("input_id");
		String inputPW = request.getParameter("input_pw");
		String inputName = request.getParameter("input_name");
		String inputAddress = request.getParameter("input_address");
		String inputPhoneNo = request.getParameter("input_tel");
		
		if(UserMgmt.doJoinmembershipProcess(response, joinType, inputID, inputPW, inputName, inputAddress, inputPhoneNo))
			out.print("<script>location.href = 'index.html';</script>");
    %>
  </body>
</html>
