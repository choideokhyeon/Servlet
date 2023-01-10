<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><!-- jstl fn cdn -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD READ</title>
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
				class="bi bi-house-door"></i></a> > BOARD > READ
		</div>
		<h1>자유게시판</h1>
		<p></p>

		<table class="table w-75">
			<!-- Subject / Count -->
			<tr>
				<th style="width: 80px;">제목</th>
				<td style="width: 480px;">${boarddto.subject}</td>
				<th>조회수</th>
				<td>${boarddto.count}</td>
			</tr>

			<!-- Email / regdate -->
			<tr>
				<th>작성자</th>
				<td>${boarddto.email}</td>
				<th>등록날짜</th>
				<td>${boarddto.regdate}</td>
			</tr>

			<!-- Content -->
			<tr>
				<td colspan=4 style="height: 300px; overflow: auto;">${boarddto.content}</td>
			</tr>

			<!-- Button -->
			<tr>
				<td colspan=4><a class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#exampleModal">첨부파일</a> <a class="btn btn-primary"
					href="${pageContext.request.contextPath}/board/list.do?=pageno=${pagedto.criteria.pageno}">이전으로</a>
					<a class="btn btn-primary">수정하기</a> <a class="btn btn-primary">삭제하기</a>
				</td>
			</tr>
		</table>
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">첨부파일 리스트</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<!-- jstl fn -->
						<c:set var="filenames" value="${fn:split(boarddto.filename,';')}" />
						<c:set var="filesizes" value="${fn:split(boarddto.filesize,';')}" />
						<c:if test="${not empty filenames}">
							<c:forEach var="i" begin="0" step="1" end="${fn:length(filenames)-1}">
								<div>
									<a href="javascript:void(0)">${filenames[i]}</a>(${filesizes[i]}Byte)
								</div>
							</c:forEach>
						</c:if>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary">ZIP 받기</button>
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>