let sno = new URL(location.href).searchParams.get("sno");
let rdate = new URL(location.href).searchParams.get("rdate")  ;  
console.log(sno)
console.log(rdate)
// 예약 현황 조회에서 rno 랑 rdate 가져오기

// [1] 사용자가 선택한 날짜를 백으로 보내 해당 날짜에 이미 완료된 시간 가져오기
function rentalList() {
  // Select the rental box element
  let rentbox = document.querySelector(".rentalbox");
  let html = ``;

  console.log("확인", sno, rdate); // Log the values for sno and rdate
  
  // Fetch rental state data
  fetch(`/rental/rental/state?sno=${sno}&rdate=${rdate}`)
    .then(response => response.json())
    .then(data => {
      console.log(data);
      // Create a Set to store unavailable times to avoid duplicates
      let unavailableTimes = new Set();
      if (data && data.length > 0) {
        // Add all unavailable times to the Set
        data.forEach((t) => {
          unavailableTimes.add(t);
        });
      }

      // Loop through 24 hours (from 00:00 to 23:00)
      for (let i = 0; i <= 24; i++) {
        let rtime = i < 10 ? `0${i}` : `${i}`; // Format time to 2 digits (e.g., "01" for 1)

        // If the time is unavailable, display as "예약불가"
        if (unavailableTimes.has(rtime)) {
          html += `
            <tr>
              <td>${rdate}</td>
              <td>${rtime}</td>
              <td>10,000</td>
			  <td>예약불가합니다</td>
              <td><button class="btn btn-danger" disabled>예약불가</button></td>
            </tr>
          `;
        } else {
          // If the time is available, display as "예약가능"
          html += `
            <tr>
              <td>${rdate}</td>
              <td>${rtime}</td>
              <td>10,000</td>
              <td>
                <input type='button' onclick='count("minus", ${rtime})' value='-' class="btn btn-primary" style="background-color: #212529;"/>
                <span id="result${rtime}">0</span>
                <input type='button' onclick='count("plus", ${rtime})' value='+' class="btn btn-primary" style="background-color: #212529;"/>
              </td>
              <td class="rentalbtn">
                <button type="button" onclick="addRental(${rtime})" class="btn btn-primary" style="background-color: #212529; border: none;">예약 가능</button>
              </td>
            </tr>
          `;
        }
      }

      // Insert the generated HTML into the rental box
      rentbox.innerHTML = html;
    })
    .catch(error => {
      console.error('요청 처리 오류:', error); // Error handling
    });
}

rentalList()


const addRental = (rtime) => {
	rtime = (rtime < 10 ? "0" +rtime : rtime )
	let rcountin = document.getElementById(`result${rtime}`);
	console.log(rtime)
	
	// span 마크업은 innerHTML 로 값 가져오기!!!!
	let rcount = rcountin.innerHTML;
	 
	console.log(rtime)

	
	
	let obj = {
		rcount : rcount,
		rdate : rdate,
		sno : sno,
		rtime : rtime
	}
	const option = {
		method :  `POST`,
		headers : {"Content-Type" : "application/json"},
		body : JSON.stringify(obj)
	}
	
	fetch("/rental/member/rental", option)
		.then(r => r.json())
		.then(data => {
			if(data == true){
				alert("예약 성공")
				location.reload(true);
				//location.href = "/rental/member/rental.jsp"
			}else{
				alert("예약 불가 : 관리자에게 문의하세요.")
				//location.href = "/rental/member/rental.jsp"
			}
		})
		.catch(e => {console.log(e)})
}