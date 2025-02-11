<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style>
        .calendar-header {
            font-size: 20px;
            text-align: center;
            margin-bottom: 10px;
        }
        .dates {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }
        .date {
            width: 30px;
            height: 30px;
            text-align: center;
            margin: 2px;
            cursor: pointer;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .today {
            background-color: yellow;
        }
        .selected {
            background-color: lightblue;
        }
    </style>
</head>
<body>
	<!-- header.jsp 연결 -->
	<jsp:include page="header.jsp"></jsp:include>
    
    <div id="calendar">
    
    </div>
    
    
    <jsp:include page="footer.jsp"></jsp:include>

	<script src="/rental/JS/member/calendar.js"></script>
</body>
</html>