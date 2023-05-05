<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<link href="webDesign.css" rel="stylesheet" type="text/css"/>
		<title>HMarket</title>
	</head>
	<body>
		<header>
			<h1>HMarket</h1>
  			<nav>
	  			<ul>
	    			<span><li><%=session.getAttribute("user").toString()%>님 환영합니다</li></span>
	    			<span><li><a href="logout.jsp">로그아웃</a></li></span>
    			</ul>
  			</nav>
		</header>
	</body>
</html>