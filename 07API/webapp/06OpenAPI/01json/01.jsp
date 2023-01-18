<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01json</title>

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>

<div class="confirm" style="width:95%; height:700px; overflow:auto;">
	${tmp}
</div>


<script>
	const cpath = '${pageContext.request.contextPath}';
	$.ajax({
		type : "GET",
		url : cpath + "/meme.do",
		dataType : 'json',
		contextType : 'application/json',
		success : function(result){
			/* console.log(result);
			console.log(result.status);
			console.log(result.total);
			console.log(result.data); */
			
			let tmp;
			let divtg = document.querySelector('.confirm');	//divtg에 confirm 클래스를 불러옴
			$.each(result.data,function(idx, item){
				tmp="<div>";
				tmp += "<span>" + item.BZ_NM + "</span><br>";
				tmp += "<span>" + item.MBZ_HR + "</span><br>";
				tmp += "<span>" + item.MNU + "</span><br>";
				tmp += "<span>" + item.TLNO + "</span>";
				tmp += "</div>";
				tmp += "<hr>";
				
				divtg.innerHTML += tmp;	//confirm 클래스 안에 tmp의 html문을 다 집어넣음
			})
		},
		error : function(request, status, error){
			alert(error);
		}
	})
</script>
</body>
</html>