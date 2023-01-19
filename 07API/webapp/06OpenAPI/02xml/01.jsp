<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02xml</title>

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>

<div class="confirm" style="width:95%; height:700px; overflow:auto;">
	
</div>


<script>
	const cpath = '${pageContext.request.contextPath}';
	$.ajax({
		type : "GET",
		url : cpath + "/tour.do",
		dataType : 'xml',
		data : {"pageNo":1, "numOfRows":20},
		/* contextType : 'application/xml', */
		success : function(result){
			//console.log(result);
			//console.log($(result).find('item'));
			$(result).find('item').each(function(){
				console.log($(this).find('attractname').text());
				console.log($(this).find('homepage').text());
				console.log($(this).find('address').text());
				console.log($(this).find('email').text());
				console.log($(this).find('tel').text());
				console.log($(this).find('attractcontents').text());
				console.log("----------------------------------------------");
			});
		},
		error : function(request, status, error){
			alert(error);
		}
	})
</script>
</body>
</html>