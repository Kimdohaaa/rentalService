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
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

			<table class="table">
			  <thead class="table-light">

			  </thead>
				<tr>
					<th> 날짜 </th>
					<th> 시간 </th>
					<th> 기본 금액 </th>
					<th> 예약 인원 </th>
					<th> 예약하기 </th>
				</tr>
				
			  <tbody class="rentalbox">

			  </tbody>

			</table>
			
			
			</div>
		</div>
	</section>
	
	<!--  푸터 인크루드 하기 -->
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="count.js"></script>
	<script src="/rental/JS/member/rental.js"></script>
	
</body>
</html>