<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- JQuery CDN -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>

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
		<button onclick="sendRequest('test1' , 'test1.jsp' , 'id=user10@naver.com')">test1.jsp요청</button>
		<p id="test1"></p>
	</div>
	<div>
		<button onclick="sendRequest('test2' , 'test2.jsp' , 'id=user20@naver.com')">test2.jsp요청</button>
		<p id="test2"></p>
	</div>
	
	
	<script defer>
	function sendRequest(TAG,URL,ID)
	{
		$.ajax
		({
			url:URL,									//요청 URL
			type:'GET',									//요청 Method
			dataType:'html',							//문서타입 html/xml/json
			data:{"id":ID}, 							//요청파라미터
			success:function(result){					//성공시 처리할 코드
				alert(result);
			},					
			error:function(request,status,error){}		//실패시 처리할 코드
		});
	}
	</script>
	
</body>
</html>