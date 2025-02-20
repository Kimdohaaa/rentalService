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
	
	<!-- Section-->
    <section class="py-5">
       	<div id="wrap">
						<!-- 부트스트랩 -->
					  
			<form class="p-4 p-md-5 border rounded-3 bg-body-tertiary" style="width: 500px; margin: 0 auto;">
					      	  
			    <div class="form-floating mb-3 mimgbox">
					<img src="" class="mimg"/> 
				</div>     
					      	  
					      	  <!-- form-date 타입으로 전송할 경우 속성명을 'name' 속성으로 사용/구분 -->
				<div class="form-floating mb-3">
				   <input type="text" readonly name="mid" class="form-control midbox" id="floatingInput" placeholder="회원아이디">
				   <label for="floatingInput">회원 아이디</label> 
				</div>     
					 
				<div class="form-floating mb-3">
				   <input type="text" name="mpwd" class="form-control mpwdbox" id="floatingInput" placeholder="회원비밀번호">
				   <label for="floatingInput">회원 비밀번호</label>
				</div>
					          	    
				<div class="form-floating mb-3">
				   <input type="text" name="mphone" class="form-control mphonebox" id="floatingInput" placeholder="회원연락처">
				   <label for="floatingInput">회원 연락처</label>
				</div>
				<div class="form-floating mb-3">
				   <input type="text" name="mstate" class="form-control maddrbox" id="floatingInput" placeholder="회원주소">
				   <label for="floatingInput">회원 주소</label>
				</div>
				<div class="form-floating mb-3">
				   <select class="mgenderin">
				     <option class="default"  disabled selected></option>
					  <option class="gender" value="1">남성</option>
					  <option class="gender" value="2">여성</option>
					</select>
			     
				</div>
				
					          
					          
					          <!--  disabled input 막기 -->
					          <!--  readonly : 읽기 모드(수정금지) -->
					          
					          
				<button class="w-100 btn btn-lg btn-primary" type="button" onclick="onupdate()" style="background-color : #212529; boarder: none;">
					회원 수정
				</button>
					      	  
			
			</form>
		
		</div>
	</section>
	

	<script src="/rental/JS/member/update.js"></script>
</body>
</html>