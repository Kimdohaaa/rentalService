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
	
	fetch("/rental/member/login",option)
		.then(r => r.json())
		.then(data => {
			if(data == true){
				alert("로그인 성공")
				location.href= "/rental/member/index.jsp" // 메인 페이지 연결
			}else{
				alert("로그인 실패")
			}
		})
		.catch(e => {console.log(e)})
}