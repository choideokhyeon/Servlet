<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/ServletTest02" method="post">
	<input type="text" name="email" placeholder="이메일 입력" /><br>
	<input type="password" name="pwd" placeholder="비밀번호 입력" /><br>
	<input type="submit" value="전송" />
</form>

</body>
</html>