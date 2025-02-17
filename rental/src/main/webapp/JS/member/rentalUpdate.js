let rno = new URL(location.href).searchParams.get("rno")

function rentalDelete() {
    // 선택된 라디오 버튼 찾기
    const rreasonin = document.querySelector('.rreason:checked');
    
	if (rreasonin) {
	        let rreason = rreasonin.value;
	        let rreason_detail = ""; // 기타 사유 디테일 초기화

	        // 기타 사유인 경우, prompt로 입력받기
	        if (rreason === "reason") {
	            rreason_detail = prompt('취소 사유를 입력하세요.');
	        }

        // 콘솔에 선택된 value 값 출력
        console.log("취소번호" , rreason);
        
		console.log("기타 사유:", rreason_detail);
		
		let obj = {
				rno : rno,
				rreason : rreason,
				rreasonEtc: rreason_detail
			}
			
		const option = {
			method : 'PUT',
			headers : {"Content-Type" : "application/json"},
			body : JSON.stringify(obj)
		}	
		
		fetch("/rental/rental/delete", option)
			.then(r => r.json())
			.then(data => {
				if(data == true){
					alert("예약 취소 완료")
					location.href ="/rental/member/rentallist.jsp"
				}else{
					alert("실패 : 관리자에게 문의하세요.")
					location.href ="/rental/member/rentallist.jsp"
				}
			})
    } else {
        alert('예약 취소 사유를 선택해주세요.');
    }
	
	
	
}