// [1] 회원가입
const signup = () => {
	let midin = document.querySelector(".midin");
	let mpwdin = document.querySelector(".mpwdin");
	let maddrin = document.querySelector(".maddrin");
	let mphonein = document.querySelector(".mphonein");
	let mgenderin = document.querySelector(".mgenderin");
	
	const mid = midin.value;
	const mpwd = mpwdin.value;
	const mphone = mphonein.value;
	const maddr = maddrin.value;
	const mgender = mgenderin.value;	
	
	const obj = {
		mid : mid,
		mpwd : mpwd,
		mphone : mphone,
		maddr : maddr,
		mgender : mgender
	}
	
	console.log(obj);
	
	const option = {
		method : 'POST',
		headers : {"Content-Type" : "application/json"},
		body : JSON.stringify(obj)
	}
	
	fetch("/rental/member/signup", option)
		.then(r => r.json())
		.then(data => {
			if(data == true){
				alert("회원가입 성공")
				location.href = "/rental/login/login.jsp"  // 메인페이지 연결
			}else{
				alert("회원가입 실패")
			}
		})
		.catch(e => {console.log(e)})
}