<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	div{
	width:500px;
	height:300px;
	margin : 10px;
	padding:10px;
	border : 1px solid;
	}
</style>
<body>

	<div>
		<button onclick="sendRequest('test1','test1.jsp?id=user10@naver.com')">test1.jsp요청</button>
		<p id="test1"></p>
	</div>
	<div>
		<button onclick="sendRequest('test2','test2.jsp?id=user20@naver.com')">test2.jsp요청</button>
		<p id="test2"></p>
	</div>
	
	
	<script defer>
	function sendRequest(tag,url) {
		var httpRequest = new XMLHttpRequest();	//비동기처리 request 객체 생성
		httpRequest.onreadystatechange = function() {
			//XMLHttpRequest 객체의 현재 상태가 요청 완료이고, 서버에 문서가 존재하면 받은 데이터를 출력
			if (httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200 )
			{
				document.getElementById(tag).innerHTML = httpRequest.responseText;
			}
		};
		
		// GET 방식으로 요청을 보내면서 데이터를 동시에 전달함.
		httpRequest.open("GET", url, true);
		httpRequest.send();
	}
	</script>
	
</body>
</html>