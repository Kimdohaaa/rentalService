<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>슬로건짐 관리자 페이지</title>
<link href="/rental/CSS/delete.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<h3 style="margin-left : 43%; margin-top: 100px;"> 취소 사유를 선택해 주세요.</h3>
	
	<table class="table" 
		style=" margin: 0 atuo; text-align: center; width: 500px; 
		margin-bottom : 80px; margin-left: 39%; margin-top: 50px">	
	<thead>
		<tr>
		<th>		
        
            <label for="formGroupExampleInput1" class="form-label">대여번호</label>
            <input type="text" class="form-control rno" id="formGroupExampleInput1" placeholder="예시 : 0">
        
		</th>
		</tr>
		<tr>
		<th>
		<div class="form-check">
		  <input class="form-check-input rreason" name="rreason" type="radio" value="0" id="flexCheckDefault">
		  <label class="form-check-label" for="flexCheckDefault" >
		    공간 협소
		  </label>
		</div>
		</th>
		</tr>
		
		<tr>
		<th>
			<div class="form-check">
			  <input class="form-check-input rreason " name="rreason" type="radio" value="1" id="flexCheckChecked" >
			  <label class="form-check-label" for="flexCheckChecked">
			    위생
			  </label>
			</div>
		</th>
		</tr>
		
		<tr>
		<th>
			<div class="form-check">
			  <input class="form-check-input rreason " name="rreason" type="radio" value="2" id="flexCheckChecked" >
			  <label class="form-check-label" for="flexCheckChecked">
			    기구 부족
			  </label>
			</div>
		</th>
		</tr>
		
		<tr>
		<th>
			<div class="form-check">
			  <input class="form-check-input rreason " name="rreason" type="radio" value="reason" id="flexCheckChecked" >
			  <label class="form-check-label" for="flexCheckChecked">
			    기타
			  </label>
			</div>
		</th>
		</tr>
		
		<tr>
		<th>
			<button type="button" class="btn btn-primary" onclick="rentalCancel()" style="width : 200px; background-color: #212529; border: none;" >
				예약 취소                                            
			</button>
		</th>
		</tr>
		
		<thead>
	</table>
	


	<script src="/rental/JS/admin/rentalCancel.js"></script>
</body>
</html>