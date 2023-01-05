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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

<style>
a{
	text-decoration : none;
	color : black;
}
ul{
	list-style : none;
	padding : 0px;
}

header {
	margin-bottom : 10px;
}

header nav {
	height : 80px;
	background-color : #F2F2F2;
}

header nav ul {
	height : 80px;
	display: flex;
	justify-content: space-around;
	align-items : center;
}

header nav ul li {
	
}

h1, .bi {
	text-align: center;
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
	width: 500px;
}

section{
	height : 600px;
}

.container {
	position: relative;
}

.container * {
	margin-bottom : 10px;
}

.container .msg {
	position: absolute;
	left: 0px;
	top: 0px;
	font-size: 0.5rem;
}

</style>
<body>
	<header>
		<nav>
			<ul>
				<li><a href="javascript:void(0)">회사소개</a></li>
				<li><a href="${pageContext.request.contextPath}/notice/list.do">공지사항</a></li>
				<li><a href="${pageContext.request.contextPath}/board/list.do">자유게시판</a></li>
				<li><a href="javascript:void(0)">개인정보수정</a></li>
			</ul>
		</nav>
	</header>

	<section class="container">
		<div class="msg">${msg}</div>
	</section>
</body>
</html>