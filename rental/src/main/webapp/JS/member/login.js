console.log("login.js open")
// [1] 회원로그인
const login = () => {
	let midin = document.querySelector(".midin");
	let mpwdin = document.querySelector(".mpwdin");
	
	const mid = midin.value;
	const mpwd = mpwdin.value;
	
	const obj = {
		mid : mid,
		mpwd : mpwd
	}
	
	const option = {
		method : 'POST',
		headers : {"Content-Type" : "application/json"},
		body : JSON.stringify(obj)	
	}
	
	fetch(`/rental/member/login`,option)
		.then(r => r.json())
		.then(data => {
			if(data == true){
				alert("로그인 성공")
				location.href= "/rental/member/index.jsp" // 메인 페이지 연결
			}else {
				alert("실패 : 관리자에게 문의")
			}
		})
		.catch(e => {console.log(e)})
}

// [2] 회원비밀번호 찾기
const findMpwd = () => {
	
	let mid = prompt("가입된 아이디를 입력하세요.")
	let mphone = prompt("000-0000-0000 형식으로 가입된 전화번호를 입력하세요.")

	console.log(mid , mphone)
	
	let obj = {
		mid : mid,
		mphone : mphone
	}	
	
	const option = {
		method : 'POST',
		headers : {"Content-Type" : "application/json"},
		body : JSON.stringify(obj)
	}
	
	fetch("/rental/member/find", option)
		.then(r => r.json())
		.then(data => {
			console.log(data)
			if(data != null){
				alert(`조회된 비밀번호 : ${data.mpwd} 입니다.`)
			}else{
				alert("조회된 비밀번호가 없습니다.")
			}
		})	
		.catch(e => {console.log(e)})
}




