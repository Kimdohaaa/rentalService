window.addEventListener('load', function() {
    if (typeof kakao !== 'undefined') { 
        var mapContainer = document.getElementById('map'),
            mapOption = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
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

