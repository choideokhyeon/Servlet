<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<style>

h1{
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
	width : 500px;
}

.container {
	width: 500px;
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


</style>

<body>
	<section class="container">
	
		<div class="msg">${msg}</div>
		
		<h1>MEMBER JOIN</h1>
		<form name="joinform"
			action="${pageContext.request.contextPath}/member/join.do"
			method="post" onsubmit="return false">
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
					<td>연락처</td>
					<td><input type="text" name="phone"
						placeholder="010-xxxx-xxxx" class="form-control" /></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" name="zipcode"
						placeholder="우편번호를 입력하세요" class="form-control" /></td>
				</tr>
				<tr>
					<td>기본주소</td>
					<td><input type="text" name="addr1" placeholder="기본주소 입력"
						class="form-control" /></td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td><input type="text" name="addr2" placeholder="상세주소 입력"
						class="form-control" /></td>
				</tr>
				<tr>
					<td colspan=2><button class="btn btn-secondary"
							style="margin-right: 10px;" onclick="isValid()">회원가입</button>
						<input type="reset" class="btn btn-warning" /></td>
				</tr>
			</table>
		</form>
	</section>
</body>

<script defer>
	const isValid = function() {
		const joinform = document.joinform;
		alert("[JavaScript] func isValid");
		
		//email 공백여부 등 Validation Check
		joinform.submit();
	}
</script>

</html>