<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
            <label for="formGroupExampleInput2" class="form-label ">사업자등록번호</label>
            <input type="text" class="form-control rtime smno" id="formGroupExampleInput2" >
        </div>
        
        <div class="mb-3">
            <label for="formGroupExampleInput3" class="form-label ">주소</label>
            <input type="text" class="form-control rcount saddr" id="formGroupExampleInput3" >
        </div>
        
        <div class="mb-3">
            <label for="formGroupExampleInput4" class="form-label ">가맹지점</label>
            <input type="text" class="form-control sname" id="formGroupExampleInput4" >
        </div>
        
        <!--  
        <div class="mb-3">
            <label for="formGroupExampleInput4" class="form-label">가맹사진 첨부</label>
            <input type="file" class="form-control simg" id="formGroupExampleInput4" >
        </div>
         --> 
        
        
        
	<button onclick="onUpdate()" type="button" class="btn btn-dark">수정 신청</button>
    </div>
    
	
</div>

<script src="/rental/JS/admin/admStore.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


	
	
</body>
</html>