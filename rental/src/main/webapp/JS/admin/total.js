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

const annualTotal = () => {
    fetch('/rental/admin/annual?sno=1') // 필요한 sno를 query parameter로 전달
        .then(r => r.json())
        .then(data => {
            if (Object.keys(data).length > 0) {
                // 데이터를 분리
                const labels = Object.keys(data); // 년도
                const values = Object.values(data); // 매출

                // Chart.js로 그래프 그리기
                const ctx = document.getElementById('annualChart').getContext('2d');
                new Chart(ctx, {
                    type: 'line', // 막대 그래프
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'Annual Revenue',
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
                document.querySelector('.annualTotal').innerHTML = `<p>0</p>`;
            }
        })
        .catch(err => console.error('Error fetching data:', err));
};

// 함수 호출
annualTotal();
