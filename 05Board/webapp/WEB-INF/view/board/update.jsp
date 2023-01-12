<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD UPDATE</title>
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
a {
	text-decoration: none;
	color: black;
}

ul {
	list-style: none;
	padding: 0px;
}

header {
	margin-bottom: 10px;
}

header nav {
	height: 80px;
	background-color: #F2F2F2;
}

header nav ul {
	height: 80px;
	display: flex;
	justify-content: space-around;
	align-items: center;
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
}

section {
	height: 600px;
}

.container {
	position: relative;
}

.container * {
	margin-bottom: 10px;
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
				<li><a href="${pageContext.request.contextPath}/auth/logout.do">로그아웃</a></li>
			</ul>
		</nav>
	</header>
	<section class="container">
		<div class="msg">${msg}</div>
		<div>
			<a href="${pageContext.request.contextPath}/main.do"><i
				class="bi bi-house-door"></i></a> > BOARD > READ > UPDATE
		</div>
		<h1>자유게시판</h1>
		<p></p>
		<form action="${pageContext.request.contextPath}/board/update.do" method="post" enctype="multipart/form-data">
			<table class="table w-50">
				<tr>
					<td>제목</td>
					<td><input type="text" class="form-control" name="subject" value="${boarddto.subject}" /></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" class="form-control" name="email" value="${boarddto.email}"/></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea id="" cols="30" rows="10" class="form-control" name="content" style="resize: none;">${boarddto.content}</textarea></td>
				</tr>
				<tr>
					<td>파일 업로드</td>
					<td>
						${boarddto.dirpath}<br>
						${boarddto.filename}<br>
						${boarddto.filesize}<br>
					</td>
				</tr>
				<tr>
					<td colspan=2>
						<input type="submit" value="수정" class="btn btn-primary" />
						<a class="btn btn-secondary" href="javascript:history.go(-1)">이전으로</a>
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>