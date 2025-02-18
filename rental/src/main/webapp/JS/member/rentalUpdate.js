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
					// findPay()
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

// 환불할 결제 정보 가져오기
const findPay = () =>{
	
	console.log(rno);
	
	fetch(`/rental/rental/delete?rno=${rno}`)
		.then(r => r.json())
		.then(data => {
			if(data != null){
				console.log(data)
				refundPayment(data)
			}else{
				console("조회 실패")
			}
		})
		.catch(e => {console.log(e)})
}


// 아임포트에서 환불처리하는 로직??
function refundPayment(data) {
	
	console.log(data)
  // 환불 요청 데이터를 서버로 보냄
  const obj = {
    imp_uid: data.imp_uid,  // 결제 시 받은 imp_uid
    amount: data.amount,    // 환불할 금액 (전체 금액 또는 일부 금액)
  };

  const option = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(obj),
  };

  console.log(JSON.stringify(obj))
  // 서버의 환불 엔드포인트로 요청//////
  fetch("/rental/rental/refund", option)
    .then(response => response.json())
    .then(res => {
      if (res.success) {
        alert("환불이 완료되었습니다.");
		refundDB(data)
      } else {
        alert("환불 실패: " + res.message);
      }
    })
    .catch(e => {
      console.error("환불 처리 중 오류 발생", e);
      alert("환불 처리 중 오류가 발생했습니다.");
    });
}


// 환불 DB 처리
function refundDB(data) {
	
  console.log(data)
 
   const obj = {
    imp_uid: data.imp_uid,  // 결제 시 받은 imp_uid
    amount: data.paid_amount,    // 환불할 금액 (전체 금액 또는 일부 금액)
  	rno : rno
   };

  const option = {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(obj),
  };

  fetch("/rental/rental/delete", option)  // 서버에서 처리할 엔드포인트
    .then(response => response.json())
    .then(data => {
      if (data.success) {
        alert("환불이 완료되었습니다.");
		location.href ="/rental/member/rentallist.jsp"
      } else {
        alert("환불 실패: " + data.message);
		location.href ="/rental/member/rentallist.jsp"
      }
    })
    .catch(e => {
      console.error("환불 처리 중 오류 발생", e);
      alert("환불 처리 중 오류가 발생했습니다.");
    });
}
