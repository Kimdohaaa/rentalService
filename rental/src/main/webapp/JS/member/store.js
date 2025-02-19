console.log("store js open");
/*
let lan = null;
let lon = null;

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
                                // 매출 최고 매장 처리
                                if (s.sname === b) {
                                    console.log(b);
                                    html += createStoreHTML(s, "red", "★ 예약 최다 ★");
                                } 

                                // 취소율 최저 매장 처리
                                if (s.sname === rName) {
                                    console.log(rName);
                                    html += createStoreHTML(s, "blue", "★ 취소율 최저 ★");
                                }

                                // 일반 매장 처리
                                if(s.sname !== b && s.sname !== rName) {
                                    html += createStoreHTML(s);
                                }
                            });

                            storebox.innerHTML = html;  // 최종적으로 HTML 업데이트
                            loadMap(data); // Fix the reference to loadMap
                        })
                        .catch(e => console.log(e)); // 오류 처리
                })
                .catch(e => console.log(e));  // 오류 처리
        })
        .catch(e => console.log(e));  // 오류 처리
};

const createStoreHTML = (store, color = "black", label = "") => {
    return `
        <div class="col mb-5 store">
            <div class="card h-100">
                <img class="card-img-top" src="/rental/upload/${store.simg}"/>
                <div class="card-body p-4">
                    <div class="text-center">
                        <div style="color : ${color}; font-size : 20px;">${label}</div>
                        <h3>${store.sname}</h3>
                        <div class="fw-bolder">${store.saddr}</div>
                        10,000~
                    </div>
                </div>
                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                    <div class="text-center">
                        <a class="btn btn-outline-dark mt-auto" href="calendar.jsp?sno=${store.sno}">
                            예약 하기
                        </a>
                        <button class="btn btn-primary storeinfo" type="button" onclick="openStoreDetails(${store.sno}, '${store.sname}', '${store.saddr}', ${store.lat}, ${store.lon})">
                            상세 보기
                            <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight_${store.sno}" aria-labelledby="offcanvasRightLabel_${store.sno}">
                                <div class="offcanvas-header">
                                    <h5 id="offcanvasRightLabel_${store.sno}">${store.sname} 상세 정보</h5>
                                    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                                </div>
                                <div class="offcanvas-body">
                                    <!-- Map and details will be dynamically inserted here -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>`;
};

const openStoreDetails = (sno, sname, saddr, lat, lon) => {
    // Dynamically populate the offcanvas content
    const offcanvasHeader = document.querySelector(`#offcanvasRight_${sno} .offcanvas-header`);
    const offcanvasBody = document.querySelector(`.offcanvas-body`);
        
    // Fill in the offcanvas content with the selected store's details
    offcanvasHeader.innerHTML = `<h5 id="offcanvasRightLabel_${sno}">${sname} 상세 정보</h5>
                                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>`;
    offcanvasBody.innerHTML = `
        <div id="map_${sno}" style="width:100%;height:350px;"></div>
        <div style="margin-top: 15px;">${saddr}</div>
        <div style="margin-top: 15px;">운영시간: 00:00 ~ 24:00 연중무휴</div>
    `;

    // Initialize the map for the selected store
    loadMap(sno, lat, lon);
};

// Function to load a single map for a store.
const loadMap = (sno, lat, lon) => {
    const mapContainer = document.getElementById(`map_${sno}`);
    
    if (mapContainer) {
        const mapOption = {
            center: new kakao.maps.LatLng(lat, lon), // Make sure to use 'new'
            level: 3
        };

        const map = new kakao.maps.Map(mapContainer, mapOption);

        const markerPosition = new kakao.maps.LatLng(lat, lon); // Use 'new' here as well
        const marker = new kakao.maps.Marker({
            position: markerPosition
        });

        marker.setMap(map);
    }
};

findStore();

/*
// HTML 출력
const createStoreHTML = (store, color = "black", label = "") => {
    return `
        <div class="col mb-5 store">
            <div class="card h-100">
                <img class="card-img-top" src="/rental/upload/${store.simg}"/>
                <div class="card-body p-4">
                    <div class="text-center">
                        <div style="color : ${color}; font-size : 20px;">  ${label} </div>
                        <h3>${store.sname}</h3>
                        <div class="fw-bolder">${store.saddr}</div>
                        10,000~
                    </div>
                </div>
                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                    <div class="text-center">
                        <a class="btn btn-outline-dark mt-auto" href="calendar.jsp?sno=${store.sno}">
                            예약 하기
                        </a>
                        <button class="btn btn-primary storeinfo" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight" >
                            상세 보기
                        </button>
                        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
                            <div class="offcanvas-header">
                                <h5 id="offcanvasRightLabel">${store.sname} 상세 정보</h5>
                                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                            </div>
                            <div class="offcanvas-body">
                                <div id="map_${store.sno}" style="width:100%;height:350px;"></div>
								<div style="margin-top : 15px;">${store.saddr} </div>
								<div style="margin-top : 15px;"> 운영시간 : 00:00 ~ 24:00 연중무휴 </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>`;
};

// 카카오 지도 api
const loadMaps = (stores) => {
    if (typeof kakao !== 'undefined') {
        stores.forEach((store) => {

            kakao.maps.load(function() {
                const mapContainer = document.getElementById(`map_${store.sno}`);
                const mapOption = {
                    center: new kakao.maps.LatLng(store.lat, store.lon), 
                    level: 3
                };

                const map = new kakao.maps.Map(mapContainer, mapOption);

                const markerPosition = new kakao.maps.LatLng(store.lat, store.lon);
                const marker = new kakao.maps.Marker({
                    position: markerPosition
                });

                marker.setMap(map);
            });
        });
    } else {
        console.error("카카오 지도 API 로딩 실패");
    }
};

findStore();
*/
// 위도 경도 받아올 변수
let lat = 33.450701;
let lon = 126.570667;

// 가맹점 조회
const findStore = () => {
    let storebox = document.querySelector(".storebox");
    let html = ``;

    // 매출 최고 매장 정보를 먼저 가져옴
    fetch("/rental/sales")
        .then(r => r.json())  // Fixed typo from .josn() to .json()
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

window.addEventListener('load', function(lat, lon) {
    if (typeof kakao !== 'undefined') { 
        var mapContainer = document.getElementById(`map_1`),
            mapOption = {
                center: new kakao.maps.LatLng(lat, lon),
                level: 3
            };

        var map = new kakao.maps.Map(mapContainer, mapOption);

        var markerPosition = new kakao.maps.LatLng(33.450701, 126.570667);

        var marker = new kakao.maps.Marker({
            position: markerPosition
        });

        marker.setMap(map);
    } else {
        console.error("카카오 지도 API 로딩 실패");
    }
});





/*
주속로 지도 
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('인천광역시 참외전로 323', function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    

*/
