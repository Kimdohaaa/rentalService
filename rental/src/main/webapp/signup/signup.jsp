<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="signup.css" rel="stylesheet" />

</head>
<body>
	<!-- 로그인 페이지 연결해주세요 -->
	<h2>이미 아이디가 있으신가요? <a href="/rental/login/login.jsp">로그인</a></h2>
	<div class="container" id="container">
	  <div class="form-container sign-in-container">
	    <form action="#">
	      <h1>회원 회원가입</h1>
	   
	      <!-- 회원가입 시 입력 받을 값에 맞춰서 수정해주세요 -->
	      <input type="text" placeholder="Id" class="midin" />
	      <input type="password" placeholder="Password" class="mpwdin" />
	      <input type="text" placeholder="Phone" class="mphonein"/>
	    <!--   <input type="text" placeholder="Aadrr" class="maddrin"/>  -->
	    <div class="findbox">
	      <input type="text" id="sample4_postcode" placeholder="우편번호">
			<input type="button" class="findAddr" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
			</div>
			<input type="text" class="maddrin" id="sample4_roadAddress" placeholder="도로명주소">
		
	     <select class="mgenderin">
		     <option value="none" disabled selected>성별</option>
			  <option class="gender" value="1">남성</option>
			  <option class="gender" value="2">여성</option>
			  
			</select>
	     
	      <button type="button" onclick="signup()" style="background-color : rgba(36,39,43,1); border: none;">회원가입</button>
	    </form>
	  </div>
	  
	  <div class="overlay-container">
	    <div class="overlay">
	      <div class="overlay-panel overlay-left">
	        <h1>관리자  <br/> 페이지 입니다.</h1>
	        <p>일반 회원 회원가입 시 <br/> 아래 버튼을 눌러주세요.</p>
	        <button class="ghost" id="signIn" >일반회원</button>
	      </div>
	      <div class="overlay-panel overlay-right">
	        <h1>일반회원 회원가입 <br/> 페이지 입니다.</h1>
	        <p>관리자 일 시 <br/>로그인 버튼을 눌러 주세요</p>
	      </div>
	    </div>
	  </div>
	</div>

	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	    function sample4_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수
	
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample4_postcode').value = data.zonecode;
	                document.getElementById("sample4_roadAddress").value = roadAddr;
	                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                if(roadAddr !== ''){
	                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
	                } else {
	                    document.getElementById("sample4_extraAddress").value = '';
	                }
	
	                var guideTextBox = document.getElementById("guide");
	                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';
	
	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }
	            }
	        }).open();
	    }
	</script>
	<script src="/rental/JS/member/signup.js"></script>
	<script src="signup.js"></script>
	
</body>
</html>