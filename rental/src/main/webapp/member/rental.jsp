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
					<th> 번호 </th>
					<th> 날짜 </th>
					<th> 시간 </th>
					<th> 기본 금액 </th>
					<th> 예약 인원 </th>
					<th> 예약하기 </th>
				</tr>
			  <tbody>
				<tr>
					<td>1</td>
					<td> 2020-03-03 </td>
					<td> 12 시 </td>
					<td> 20,000 </td>
					<td>
						<input type='button'
						       onclick='count("minus")'
						       value='-'
						       class="btn btn-primary" style="background-color : #212529;"/>
						<span id='result'>0</span>
						<input type='button'
						       onclick='count("plus")'
						       value='+'
						       class="btn btn-primary" style="background-color : #212529;"/>

					</td>
					<!-- 예약 가능 여부에 따라 dis~ 지정 / 테두리 지우기...(밑에 푸터 때문에 적용 안되나...?) -->
					<td> <button type="button" class="btn btn-primary" style="background-color : #212529; boarder: none;">
							예약
						</button> 
					</td>
				</tr>
			  </tbody>

			</table>
			
			
			</div>
		</div>
	</section>
	
	<!--  푸터 인크루드 하기 -->
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="count.js"></script>
</body>
</html>