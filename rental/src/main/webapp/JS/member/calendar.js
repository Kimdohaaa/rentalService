document.addEventListener("DOMContentLoaded", function() {
	let sno = new URL (location.href).searchParams.get("sno")
    
	// 현재 날짜 시작
    let startDate = new Date(); // 시작 날짜를 현재 날짜로 설정

    // 캘린더 출력 함수
    function generateCalendar() {
        const table = document.getElementById("calendarTable");
        const daybox = document.querySelector(".daybox"); 
        
        if (!daybox) {
            console.error("daybox 요소 없음");
            return;
        }

        let currentDate = new Date(startDate); // 캘린더에 사용할 날짜

        // 해당 월 출력 
        const monthDisplay = document.getElementById("monthDisplay"); 
        const options = { year: 'numeric', month: 'long' }; 
        monthDisplay.textContent = currentDate.toLocaleDateString('ko-KR', options); // "YYYY년 MM월" 형식으로 표시

        // 날짜 출력 (현재 날짜 ~ 60일 후까지)
        let bodyhtml = ``; // 날짜 셀들을 담을 문자열 변수

        // 첫 번째 날이 일요일 이 아닐 경우 
        for (let i = 0; i < currentDate.getDay(); i++) {
            bodyhtml += `<td></td>`; // 첫 번째 날 전까지 빈 셀 추가
        }

        // 날짜 출력
        while (currentDate.getMonth() === startDate.getMonth()) { // 해당 월이 끝날 때까지 반복
            bodyhtml += `<td><a href="/rental/member/rental.jsp?sno=${sno}&rdate=${currentDate.toISOString().split('T')[0]}" onclick="rentalList()">${currentDate.getDate()}</a></td>`; 

			console.log(currentDate.toISOString().split('T')[0])
            // 날짜 1일 추가
            currentDate.setDate(currentDate.getDate() + 1); // 하루를 더해서 다음 날짜로 이동

            // 한 주가 끝나면 새로운 행으로
            if (currentDate.getDay() === 0) {  // 일요일이면
                bodyhtml += `</tr><tr>`; // 새로운 행을 시작
            }
        }

        // 마지막 날이 일요일이 아닐 경우
        if (currentDate.getDay() !== 0) {  // 마지막 날이 일요일이 아니면
            const remainingCells = 7 - currentDate.getDay(); // 부족한 셀 수 계산
            for (let i = 0; i < remainingCells; i++) { // 부족한 셀을 채우기
                bodyhtml += `<td></td>`;  // 빈 셀 추가
            }
        }

        // daybox에 날짜 HTML 추가 (tbody에 날짜 삽입)
        daybox.innerHTML = `<tr>${bodyhtml}</tr>`; 
    }


	// 이전 월 버튼
	document.getElementById("prevMonthBtn").addEventListener("click", function() {
	    startDate.setMonth(startDate.getMonth() - 1); // 이전 월로 설정
	    startDate.setDate(1); // 날짜를 1일로 설정
	    generateCalendar(); // 캘린더 갱신
	});

	// 다음 월 버튼
	document.getElementById("nextMonthBtn").addEventListener("click", function() {
	    startDate.setMonth(startDate.getMonth() + 1); // 다음 월로 설정
	    startDate.setDate(1); // 날짜를 1일로 설정
	    generateCalendar(); // 캘린더 갱신
	});
    // 초기 캘린더 생성
    generateCalendar(); // 초기 캘린더 출력
});






