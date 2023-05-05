<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.sql.*,controller.*,db_access.*,entity.*"%>
<html>
  <head>
    <title>HMarket</title>
  </head>
  <body>
    <%    
    	String inputID = request.getParameter("input_id");
   		String inputPW = request.getParameter("input_pw");
   		String loginType = request.getParameter("select");
   		
   		User user = UserMgmt.doLoginProcess(response, inputID, inputPW, loginType);
   		session.setAttribute("user", user);
		session.setAttribute("isLogined", true);
    %>
  </body>
</html>