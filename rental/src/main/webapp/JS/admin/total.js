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
