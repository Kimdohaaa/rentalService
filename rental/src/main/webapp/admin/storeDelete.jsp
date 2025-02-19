<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

 <style>
        .center-box {
            width: 50%;
            margin: 0 auto;
        }
    </style>
 

</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

	<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="center-box">

		 
      		<div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label ">수정할 지점</label>
            <select class="form-select sno" id="formGroupExampleInput2" >
            	
            </select>
        </div>

		 <div class="mb-3">
            <label for="formGroupExampleInput4" class="form-label ">운영상태 변경</label>
            <select class="form-control sstate" id="formGroupExampleInput4" >
            	<option value="1">운영</option>
            	<option value="2">임시휴업</option>
            	<option value="0">폐업</option>
            </select>
        </div>
      
      	<button onclick="onUpdate()" type="button" class="btn btn-dark"> 상태 변경</button>
      
       
        </div>


        
         <!--  <div class="mb-3">
            <label for="formGroupExampleInput4" class="form-label">가맹사진 첨부</label>
            <input type="file" class="form-control simg" id="formGroupExampleInput4" >
        </div>  나중에 가맹 사진 첨부 필요할시 사용 -->
        
        
  

<script src="/rental/JS/admin/adStatus.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


	
	
</body>
</html>
