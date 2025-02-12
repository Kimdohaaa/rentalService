<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div>
		<h1>대여 신청</h1>
	   
	      <!-- 회원가입 시 입력 받을 값에 맞춰서 수정해주세요 -->
	      날짜 <input type="text" placeholder="date" class="rdate" /> <br />
	      시간 <input type="text" placeholder="time" class="rtime" /> <br />
	      인원수 <input type="text" placeholder="count" class="rcount"/> <br />
	      회원번호 <input type="text" placeholder="mno" class="mno"/> <br />
	      가맹점번호 <input type="text" placeholder="sno" class="sno"/> <br />

	      <button style="background-color : rgba(36,39,43,1); border: none;">신청</button>
	</div>
</body>
</html>