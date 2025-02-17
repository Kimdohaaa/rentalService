<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
 -->	
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
	
	
   <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
	<script src="count.js"></script>
	<script src="/rental/JS/member/rental.js"></script>
	
</body>
</html>