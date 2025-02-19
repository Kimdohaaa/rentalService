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

			  </thead>
				<tr>
					<th>예약번호</th>
					<th> 가맹점 </th>
					<th> 날짜 </th>
					<th> 시간 </th>
					<th> 금액 </th>
					<th> 예약 인원 </th>
					<th></th>
				</tr>
				
			  <tbody class="rentallist">
			  <div class="pagination"></div>

			  </tbody>

			</table>
			<nav aria-label="Page navigation example">
			  <ul class="pagination pagebtnbox">
			   
			  </ul>
			</nav>
			</div>
		</div>
	</section>
	


	<script src="/rental/JS/member/rentallist.js"></script>
</body>
</html>