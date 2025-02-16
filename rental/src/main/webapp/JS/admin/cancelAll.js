const cancelAll = () => {
	fetch('/rental/admin/cancel')
	    .then(r => r.json())
	    .then(data => {
	        console.log("Received Data:", data); // 데이터 확인

	        if (Object.keys(data).length > 0) {
	            const labels = Object.keys(data);
	            const values = Object.values(data);

	            // 값에 0이 없으면 강제로 0 추가
	            if (!values.includes(0)) {
	                values.push(0); // values에 0을 추가
	            }

	            // Chart.js로 그래프 그리기
	            const ctx = document.querySelector('#cancelChart').getContext('2d');
	            new Chart(ctx, {
	                type: 'bar',
	                data: {
	                    labels: labels,
	                    datasets: [{
	                        label: '사유',
	                        data: values,
	                        backgroundColor: 'rgba(75, 192, 192, 1)',
	                        borderColor: 'rgba(75, 192, 192, 1)',
	                        borderWidth: 1,
	                    }],
	                },
	                options: {
	                    responsive: true,
	                    scales: {
	                        y: {
	                            beginAtZero: true,   // y축 시작값을 0으로 설정
	                            ticks: {
	                                min: 0,   // y축 최소값을 0으로 설정
	                                stepSize: 1,   // y축 눈금 간격 설정
	                                max: Math.max(...values), // 데이터에 맞춰 최대값 설정
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
	                                    return context.raw.toLocaleString(); // 툴팁 포맷팅
	                                }
	                            }
	                        }
	                    }
	                }
	            });
	        } else {
	            document.querySelector('.cancelAll').innerHTML = `<p>0</p>`;
	        }
	    })
	    .catch(err => console.error('Error fetching data:', err));

}
// 함수 호출
cancelAll();