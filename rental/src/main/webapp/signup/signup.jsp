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
	<h2>이미 아이디가 있으신가요? <a href="#">로그인</a></h2>
	<div class="container" id="container">
	  <div class="form-container sign-in-container">
	    <form action="#">
	      <h1>회원 회원가입</h1>
	   
	      <!-- 회원가입 시 입력 받을 값에 맞춰서 수정해주세요 -->
	      <input type="email" placeholder="Email" />
	      <input type="password" placeholder="Password" />
	      <input type="email" placeholder="Email" />
	      <input type="password" placeholder="Password" />
	      <input type="email" placeholder="Email" />

	      <button style="background-color : rgba(36,39,43,1); border: none;">회원가입</button>
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

	<script src="signup.js"></script>
</body>
</html>