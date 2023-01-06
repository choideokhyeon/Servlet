<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD LIST</title>
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

body {
	font-family: 메이플스토리;
}

table {
	border-collapse: collapse;
	text-align: center;
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
		<!-- 메세지 -->
		<div class="msg">${msg}</div>

		<!-- 페이지경로표시 -->
		<div>
			<a href="${pageContext.request.contextPath}/main.do"><i
				class="bi bi-house-door"></i></a> > BOARD<br>
		</div>
		
		<h1>자유게시판</h1>

		<table class="table">
			<thead>
				<tr>
					<th>NO</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td>1</td>
					<td><a href="">게시물 제목입니다</a></td>
					<td>example@example.com</td>
					<td>2023-01-06</td>
					<td>0</td>
				</tr>
			</tbody>

			<tfoot>
				<tr>
					<!-- 페이지네이션 -->
					<td colspan=3>
						<nav aria-label="Page navigation example">
							<ul class="pagination" style="height:30px;">
								<li class="page-item"><a class="page-link" href="javascript:void(0)"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
								<li class="page-item"><a class="page-link" href="javascript:void(0)">1</a></li>
								<li class="page-item"><a class="page-link" href="javascript:void(0)">2</a></li>
								<li class="page-item"><a class="page-link" href="javascript:void(0)">3</a></li>
								<li class="page-item"><a class="page-link" href="javascript:void(0)"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</ul>
						</nav>
					</td>
					<td colspan=2 style="text-align:right;">
					<a
						href="${pageContext.request.contextPath}/board/post.do"
						class="btn btn-warning"><i class="bi bi-pencil-square"></i>글쓰기</a>
						<!-- 글쓰기/처음으로 -->
					<a
						href="${pageContext.request.contextPath}/board/list.do"
						class="btn btn-danger">처음으로</a></td>
				</tr>
			</tfoot>
		</table>
	</section>
</body>
</html>