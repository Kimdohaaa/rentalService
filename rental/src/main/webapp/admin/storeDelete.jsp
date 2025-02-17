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
	<script src="/rental/JS/admin/adStatus.js"></script>

	<section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

			<table class="table">
			  <thead class="table-light">
			  
			  	<tr class = "#">
					
					
					<th> 사업자등록번호 </th>
					<th> 운영지점 </th>
					<th> 주소 </th>
					<th> 운영상태 </th>
					<th> 운영변경이유 </th>
					
					
				</tr>
					
			  </thead>
			 
			  <tbody class="rentalbox status"> <!-- 출력하는곳 -->
			  			 
				
			  	
						
			  </tbody>

			</table>
			
			
			</div>
		</div>
	</section>
	
	
	
	<script src="count.js"></script>

	
</body>
</html>