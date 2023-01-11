<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>TEST 1 PAGE</h1>
<%
	String id = request.getParameter("id");
	out.println("<p>" + id + "</p>");
%>
<img src="https://ac2-p2.namu.la/20211014s2/f8f88a272299e9f05999cae329d9cdd35099f39970a378d879be1ea84a95214f.png" alt="" />


</body>
</html>