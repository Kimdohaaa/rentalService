<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title> 메인페이지</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/storebox.css" rel="stylesheet" />

        <link href="css/styles.css" rel="stylesheet" />

    </head>
    <body>

	<!-- header.jsp 연결 -->
	<jsp:include page="header.jsp"></jsp:include>
	
	
   <!-- Section-->
        <section class="py-5 ">
            <div class="container px-4 px-lg-5 mt-5 ">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center wrap storebox">
                	<div class="storebox">
                    
                    
                    
                    </div> 
                    <div class="sidebox">
                    
                    </div>   
                </div>
            </div>
 	           
        </section>
        <!-- jQuery 라이브러리 추가 : 카카오지도에 필요한 라이브러리 -->
		<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
		<!-- 카카오지도 클러스터 기능을 사용하기 위해 하이퍼링크 뒤에 ?libraries=clusterer 추가  -->	
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e44bcf37b0e8c0b221f1c539eaf7887&autoload=false"></script>
		
		<!-- <script src="/rental/JS/member/kakao.js">		</script>
			 -->
        
        <script src="/rental/JS/member/store.js"></script>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
     
    </body>
</html>