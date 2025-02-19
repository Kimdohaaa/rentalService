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

	 <section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

			<table class="table">
			  <thead class="table-light">
			  
			  		<tr class = "#">
					
					<th> 회원 번호 </th>
					<th> 회원 아이디 </th>
					<th> 회원 전화번호</th>
					<th> 회원 가입일 </th> 
					
				</tr>
					
			  </thead>
			 
			  <tbody class="rentalbox store"> <!-- 출력하는곳 -->
			  			 
				
			  	
						
			  </tbody>

			</table>
			
			
			</div>
		</div>
	</section>
	
	<script src="count.js"></script>
	<script src="/rental/JS/admin/memberInfo.js"></script>

</body>
</html>