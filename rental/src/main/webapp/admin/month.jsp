<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=ko>        

    <jsp:include page="header.jsp"></jsp:include>

    <section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="table-container">
<div class="container mt-4 total" >
    <h3>총매출</h3>
    <p id="totalSalesBox" ></p> 
</div>
                <!-- 드롭다운 메뉴 (테이블 헤더 바로 위에 위치) -->
                <div class="dropdown mb-2">
                    <button class="btn btn-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        가맹점 선택
                    </button>
                    <ul class="dropdown-menu" id="storeDropdown">
        				
    				</ul>
                </div>

                <!-- 테이블 -->
                <table class="table">
                    <thead class="table-light">

                    </thead>
                    <tbody class="rentalbox">
                    <div class="monthTotal">
    					<canvas id="monthChart"></canvas>
					</div>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <!-- Bootstrap JavaScript 및 Popper.js 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-QFODSHaEtUrx9nUd7HNd1GhXHV4+JXv45myi6mPSUeVqYUXo3U8+frqZRxkaBtJj" crossorigin="anonymous"></script>
	<script src="/rental/JS/admin/month.js"></script>
</body>
</html>