<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- header.jsp 연결 -->
	<jsp:include page="header.jsp"></jsp:include>
    <div style="text-align: center; margin-top: 50px; margin-bottom: 50px">
    	<button id="prevMonthBtn" style="text-align: center; background-color: #212529; border: none;" class="btn btn-primary" >
			이전 월
		</button>
		<span id="monthDisplay" style="text-align: center;"></span>
	
		<button id="nextMonthBtn" style="text-align: center; background-color: #212529; border: none;" class="btn btn-primary" >
			다음 월
		</button>
    </div>
    <div id="calendar" style="text-align: center;">
    <table id="calendarTable" class="table" style="width:  900px; margin:  0 auto; margin-bottom: 100px;">
        <thead class="header">
            <tr >
                <th>일</th>
                <th>월</th>
                <th>화</th>
                <th>수</th>
                <th>목</th>
                <th>금</th>
                <th>토</th>
            </tr>
        </thead>
        <tbody class="daybox" style="color: white;">
            <!-- 날짜들이 여기에 동적으로 삽입됩니다. -->
        </tbody>
    </table>
</div>
    
    

	<script src="/rental/JS/member/calendar.js"></script>
</body>
</html>