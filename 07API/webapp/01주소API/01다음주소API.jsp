<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>Insert title here</title>
</head>
<style>
input{
	width:200px;
}
</style>
<body>

	<form name="joinform" onsubmit="return false">
	<table>
		<tr>
			<td>우편번호</td>
			<td><input type="text" name="zipcode" /><button onclick="search();">우편번호 검색</button></td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
				<input type="text" name="addr1" />
			</td>
		</tr>
		<tr>
			<td>세부주소</td>
			<td><input type="text" name="addr2" /></td>
		</tr>
	</table>
	</form>


</body>
<script defer>
	const search = function() {
		const form = document.joinform;	//form 태그를 JavaScript에서 불러온다
		new daum.Postcode({
			oncomplete : function(data) {
				//반환 데이터 확인 : https://postcode.map.daum.net/guide
				console.log(data.zonecode);			//zonecode 우편번호
				console.log(data.userSelectedType);	//userSelectedType 선택한 주소 종류
				console.log(data.roadAddress);		//roadAddress 도로명 주소
				console.log(data.jibunAddress);		//jibunAddress 지번 주소
				form.zipcode.value = data.zonecode;
				
				if(data.userSelectedType == 'R')	//userSelectedType가 도로명주소(R)일 경우
					form.addr1.value = data.roadAddress;	//form안의 addr1의 value값에 roadAddress 넣기
				else if(data.userSelectedType == 'J')	//userSeletedType가 지번주소(J)일 경우
					form.addr1.value = data.jibunAddress;	//form안의 addr1의 value값에 jibunAddress 넣기
			}
		}).open();
	}
</script>

</html>