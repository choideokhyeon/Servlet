<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
	<ul>
		<li onclick="kakaoLogout();"><a href="javascript:void(0)"> <span>카카오
					로그아웃</span>
		</a></li>
	</ul>
	<section class="container">
		<h1>프로필정보</h1>
		 
			아이디 : ${param.email }<br>
			성별 : ${param.gender }<br>
			프로필 사진 :  <img src="${param.profile_image }" style="width:50px;height:50px;"  /> <br>
	</section>
	
	<!-- 카카오 스크립트 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
		Kakao.init('aef5851bf9fb04c0eae67f9672337707'); //발급받은 키 중 javascript키를 사용해준다.
		console.log(Kakao.isInitialized()); // sdk초기화여부판단

		//카카오로그아웃  
		function kakaoLogout() {
			if (Kakao.Auth.getAccessToken()) {
				Kakao.API.request({
					url : '/v1/user/unlink',
					success : function(response) {
						console.log(response)
						//location.href = "login.jsp"
					},
					fail : function(error) {
						console.log(error)

					},
				})
				Kakao.Auth.setAccessToken(undefined)
			}
		}
		
	</script>	
		


</body>
</html>