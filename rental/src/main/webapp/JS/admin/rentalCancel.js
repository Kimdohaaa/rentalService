const rentalCancel = () => {
    const rnoin = document.querySelector('.rno');
    const rreasonin = document.querySelector('.rreason:checked');

    if (rreasonin) {
        let rno = rnoin.value;
        let rreason = rreasonin.value;
        let rreason_detail = ""; // 기타 사유 디테일 초기화

        // 기타 사유인 경우, prompt로 입력받기
        if (rreason === "reason") {
            rreason_detail = prompt('취소 사유를 입력하세요.');
        }

        // 콘솔 출력 확인
        console.log("취소 사유:", rreason);
        console.log("기타 사유:", rreason_detail);

        // 전송할 객체 구성
        let obj = {
            rno: rno,
            rreason: rreason,
            rreasonEtc: rreason_detail  // 기타 사유를 rreasonEtc 필드로 전송
        };

        const option = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(obj)
        };

        fetch('/rental/admin/cancel', option)
            .then(response => response.json())
            .then(data => {
                if (data === true) {
                    alert('예약 취소 완료');
                    location.href = "rentalCancel.jsp";
                } else {
                    alert('예약 취소 실패');
                }
            })
            .catch(e => {
                console.log(e);
            });
    } else {
        alert('예약 취소 사유를 선택하세요.');
    }
};
