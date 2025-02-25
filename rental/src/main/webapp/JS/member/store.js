console.log("store js open");

// 위도 경도 받아올 변수
let lat = 33.450701;
let lon = 126.570667;

// 가맹점 조회
const findStore = () => {
    let storebox = document.querySelector(".storebox");
    let html = ``;

    // 매출 최고 매장 정보를 먼저 가져옴
    fetch("/rental/sales")
        .then(r => r.json())  
        .then(b => {
            fetch("/rental/rowD")
                .then(r => r.json())
                .then(rName => {
                    // 매장 정보를 가져옴
                    fetch("/rental/member/store")
                        .then(r => r.json())
                        .then(data => {
                            // 각 매장에 대해
                            data.forEach((s) => {
								lat = s.lan;
								lon = s.lon;
                                // 매출 최고 매장 처리
                                if (s.sname === b) {
                                    console.log(b);
                                    html += `
                                        <div class="col mb-5 store">
                                                         
                                            <div class="card h-100">
                                                <img class="card-img-top" src="/rental/upload/${s.simg}"/>
                                                <div class="card-body p-4">
                                                    <div class="text-center">
                                                        <div style="color : red; font-size : 20px;"> ★ 예약 최다 ★</div>
                                                        <h3>${s.sname}</h3>
                                                        <div class="fw-bolder">${s.saddr}</div>
                                                        10,000~
                                                    </div>
                                                </div>
                                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                                    <div class="text-center">
                                                        <a class="btn btn-outline-dark mt-auto" href="calendar.jsp?sno=${s.sno}">
                                                            예약 하기
                                                        </a>
                                                        <button class="btn btn-primary storeinfo" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight${s.sno}" aria-controls="offcanvasRight" >
                                                            상세 보기
                                                        </button>
                                                        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight${s.sno}" aria-labelledby="offcanvasRightLabel">
                                                            <div class="offcanvas-header">
                                                                <h5 id="offcanvasRightLabel">${s.sname} 상세 정보</h5>
                                                                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                                                            </div>
                                                            <div class="offcanvas-body">
                                                                <div id="map_${s.sno}" style="width:100%;height:350px;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>`;
                                } 

                                // 취소율 최저 매장 처리
                                if (s.sname === rName) {
                                    console.log(rName);
                                    html += `
                                        <div class="col mb-5 store">
                                                           
                                            <div class="card h-100">
                                                <img class="card-img-top" src="/rental/upload/${s.simg}"/>
                                                <div class="card-body p-4">
                                                    <div class="text-center">
                                                        <div style="color : blue; font-size : 20px;"> ★ 취소율 최저 ★</div>
                                                        <h3>${s.sname}</h3>
                                                        <div class="fw-bolder">${s.saddr}</div>
                                                        10,000~
                                                    </div>
                                                </div>
                                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                                    <div class="text-center">
                                                        <a class="btn btn-outline-dark mt-auto" href="calendar.jsp?sno=${s.sno}">
                                                            예약 하기
                                                        </a>
                                                        <button class="btn btn-primary storeinfo" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight${s.sno}" aria-controls="offcanvasRight" >
                                                            상세 보기
                                                        </button>
                                                        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight${s.sno}" aria-labelledby="offcanvasRightLabel">
                                                            <div class="offcanvas-header">
                                                                <h5 id="offcanvasRightLabel">${s.sname} 상세 정보</h5>
                                                                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                                                            </div>
                                                            <div class="offcanvas-body">
                                                                <div id="map_${s.sno}" style="width:100%;height:350px;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>`;
                                } 
								if(s.sname != b && s.sname != rName) {
                                    html += `
                                        <div class="col mb-5 store">
                                            <div class="card h-100">
                                                <img class="card-img-top" src="/rental/upload/${s.simg}"/>
                                                <div class="card-body p-4">
                                                    <div class="text-center">
                                                        <h3>${s.sname}</h3>
                                                        <div class="fw-bolder">${s.saddr}</div>
                                                        10,000~
                                                    </div>
                                                </div>
                                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                                    <div class="text-center">
                                                        <a class="btn btn-outline-dark mt-auto" href="calendar.jsp?sno=${s.sno}">
                                                            예약 하기
                                                        </a>
                                                        <button class="btn btn-primary storeinfo" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight${s.sno}" aria-controls="offcanvasRight" >
                                                            상세 보기
                                                        </button>
                                                        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight${s.sno}" aria-labelledby="offcanvasRightLabel">
                                                            <div class="offcanvas-header">
                                                                <h5 id="offcanvasRightLabel">${s.sname} 상세 정보</h5>
                                                                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                                                            </div>
                                                            <div class="offcanvas-body">
                                                                <div id="map_${s.sno}" style="width:100%;height:350px;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>`;
                                }
                            });

                            storebox.innerHTML = html;  // 최종적으로 HTML 업데이트
                        })
                        .catch(e => console.log(e)); // 오류 처리
                })
                .catch(e => console.log(e));  // 오류 처리
        })
        .catch(e => console.log(e));  // 오류 처리
};

findStore();

