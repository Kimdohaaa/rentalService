const findTotalSales = () => {
    fetch('/rental/admin/total')
        .then(r => r.json())
        .then(data => {
         console.log(data);
            if (data.length > 0) {
                const totalSales = data[0].totalSales;

                // 숫자에 toLocaleString()을 사용하여 천 단위 구분기호 추가
                const formattedSales = totalSales.toLocaleString();
            
            const today = new Date();
                   const year = today.getFullYear();
                   const month = (today.getMonth() + 1).toString().padStart(2, '0');
                   const day = today.getDate().toString().padStart(2, '0');
                   const formattedDate = `${year}년${month}월${day}일 까지 매출 `;
                     
            const totalSalesBox = document.querySelector('#totalSalesBox')
                totalSalesBox.innerHTML = `<p>${formattedDate}: ${formattedSales} 원</p>`; 
            } else {
                console.error("총매출 데이터를 가져오지 못했습니다.");
            }
        })
        .catch(e => {
            console.error('총매출 데이터를 가져오는 중 오류 발생:', e);
        });
};

findTotalSales();

let myChart = null;  

// 그래프 그리기 함수
const annualTotal = ( ) => {
   

	const sno = new URL( location.href ).searchParams.get('sno')
	console.log('호점 :', sno);
	
    fetch(`/rental/admin/annual?sno=${sno}`)
        .then(r => r.json())
        .then(data => {
            if (Object.keys(data).length > 0) {
                const labels = Object.keys(data);  // 년도
                const values = Object.values(data);  // 매출

                // 그래프가 이미 존재하면 삭제 (업데이트 용)
                const ctx = document.querySelector('#annualChart').getContext('2d');
                if (myChart) {
                    myChart.destroy();
                }

                // 새로 그래프 그리기
                myChart = new Chart(ctx, {
                    type: 'line',  // 선 그래프
                    data: {
                        labels: labels,
                        datasets: [{
                            label: `${sno}호점`,
                            data: values,
                            backgroundColor: 'rgba(0, 0, 0, 0)', 
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1,
                        }],
                    },
                    options: {
                        responsive: true,
                        scales: {
                            y: {
                                beginAtZero: true,
                                ticks: {
                                    callback: function (value) {
                                        return value.toLocaleString(); // 숫자 포맷팅
                                    }
                                }
                            }
                        },
                        plugins: {
                            tooltip: {
                                callbacks: {
                                    label: function (context) {
                                        return context.raw.toLocaleString(); // 숫자 포맷팅
                                    }
                                }
                            }
                        }
                    }
                });
            } else {
                document.querySelector('.annualTotal').innerHTML = ``;
            }
        })
        .catch(err => console.error('Error fetching data:', err));
};

/*
// 드롭다운에서 항목 클릭 시 그래프 업데이트
document.querySelectorAll('.dropdown-item').forEach(item => {
    item.addEventListener('click', function(event) {
        event.preventDefault();  // 링크 기본 동작 방지
        const sno = this.getAttribute('data-sno');  // 선택한 가맹점의 sno 값
        annualTotal(sno);  // 해당 지점에 맞는 그래프 그리기
    });
});

annualTotal(1);
*/
annualTotal();

