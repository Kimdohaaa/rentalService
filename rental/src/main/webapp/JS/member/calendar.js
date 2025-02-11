function generateCalendar() {
    const today = new Date(); // 현재 날짜를 가져옵니다.
    const calendarContainer = document.getElementById('calendar'); // 캘린더를 표시할 컨테이너 가져오기

    // 60일 후의 날짜를 구합니다.
    const twoMLater = new Date(today);
    twoMLater.setDate(today.getDate() + 60);

    // 월 이름을 가져옵니다.
    const monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

    // 현재 년도와 월 정보
    const currentYear = today.getFullYear();
    const currentMonth = today.getMonth();
    const currentMonthName = monthNames[currentMonth];

    // 캘린더의 상단에 월과 년도 표시
    calendarContainer.innerHTML = `<div class="calendar-header">${currentMonthName} ${currentYear} - ${today.toLocaleDateString()} ~ ${twoMLater.toLocaleDateString()}</div>`;

    // 요일 이름을 추가 (일, 월, 화, 수, 목, 금, 토)
    const dayNames = ["일", "월", "화", "수", "목", "금", "토"];
    let dayRow = '<div class="dates">';
    dayNames.forEach(day => {
        dayRow += `<div class="date">${day}</div>`; // 각 요일을 칸에 표시
    });
    dayRow += '</div>';
    calendarContainer.innerHTML += dayRow; // 요일을 화면에 출력

    // 날짜 버튼을 생성할 행을 만듭니다.
    const datesRow = document.createElement('div');
    datesRow.classList.add('dates');

    // 날짜를 생성하는 변수
    let date = new Date(today);

    // 현재 날짜부터 60일 후까지 날짜를 생성
    while (date <= twoMLater) {
        const isToday = date.toDateString() === today.toDateString() ? 'today' : ''; // 오늘 날짜인지 확인
        const dateString = date.toISOString(); // 날짜를 ISO 형식으로 변환

        // 날짜 버튼을 생성하여 화면에 추가
        datesRow.innerHTML += `
            <button class="date ${isToday}" onclick="selectDate('${dateString}')">
                ${date.getDate()}
            </button>
        `;

        // 날짜를 하루씩 증가
        date.setDate(date.getDate() + 1);

        // 주간마다 새로운 줄을 추가 (7일마다 줄 바꿈)
        if (date.getDay() === 0) {
            calendarContainer.appendChild(datesRow); // 해당 주의 날짜 행을 캘린더에 추가
            datesRow.innerHTML = ''; // 새로운 날짜 행을 준비
        }
    }

    // 마지막 날짜 행을 캘린더에 추가
    if (datesRow.innerHTML) {
        calendarContainer.appendChild(datesRow);
    }
}

function selectDate(selectedDate) {
    const date = new Date(selectedDate); // 날짜를 선택하는 방식으로 변환
    const selectedButton = document.querySelector(`button[onclick="selectDate('${selectedDate}')"]`);
    selectedButton.classList.toggle('selected'); // 선택된 날짜에 스타일을 토글
    
    // 서버로 선택된 날짜를 전송
    fetch('/your-backend-endpoint', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            selectedDate: date.toISOString(), // 선택된 날짜를 ISO 형식으로 전송
        }),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Server Response:', data);
        alert('Selected date sent successfully!');
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
generateCalendar()