<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>

<!-- BSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

<style>

h1,.bi{
	text-align:center;
}
body {
	font-family: 메이플스토리;
}

td {
	border: 1px solid;
}

table {
	border-collapse: collapse;
	text-align: center;
	width : 300px;
}

.container {
	width: 300px;
	margin: 100px auto;
	position : relative;
}

.container .msg{
	position : absolute;
	left : 0px;
	top : 0px;
	font-size : 0.5rem;
}

.container * {
	margin: 10px 0px 10px 0px;
}

a{
	text-decoration:none;
	color : black;
}


</style>

<body>
	<section class="container">
	
		<div class="msg">${msg}</div><br>
		
		<h1>LOGIN</h1>
		<form name="loginform"
			action="${pageContext.request.contextPath}/auth/login.do" method="post">
			<table>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email"
						placeholder="example@example.com" class="form-control" /></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="text" name="pwd"
						placeholder="Insert Password" class="form-control" /></td>
				</tr>
				<tr>
					<td colspan=2>
						<button class="btn btn-primary w-100">로그인</button>
						<a class="w-100 btn btn-warning">카카오 로그인</a>
						<a href="${pageContext.request.contextPath}/member/join.do">회원가입</a>
						<a href="javascript:void(0)">아이디 찾기</a>
						<a href="javascript:void(0)">패스워드 찾기</a>
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>

<script defer>
	const isValid = function() {
		const loginform = document.loginform;
		alert("[JavaScript] func isValid");
		
		//email 공백여부 등 Validation Check
		loginform.submit();
	}
</script>

</html>