
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
					
					<th> 번호 </th>
					<th> 사업자등록번호 </th>
					<th> 주소 </th>
					<th> 운영지점 </th>
					<th> 지점 사진 나중에 링크 연결이나 다른걸로 갈것 위쪽에 뜨게 하는것도 좋을듯 </th>
				</tr>
					
			  </thead>
			 
			  <tbody class="rentalbox store"> <!-- 출력하는곳 -->
			  			 
				
			  	
						
			  </tbody>

			</table>
			
			
			</div>
		</div>
	</section>
	
	<script src="count.js"></script>
	<script src="/rental/JS/admin/admStore.js"></script>

</body>
</html>