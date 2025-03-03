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
	
	console.log("성별확인",mgender);

	if (!mid || mid.trim() === "" || !mpwd || mpwd.trim() === "" || !mphone || mphone.trim() === "" || !maddr || maddr.trim() === "" || !mgender || mgender.trim() === "") {
	    alert("모든 항목을 입력하세요.");
	    return;
	}
		console.log(maddr)
		
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
				
				console.log(data)
				if(data == 0){
					alert("회원가입 성공")
					location.href= "/rental/login/login.jsp" // 메인 페이지 연결
				}else if(data == 1 ){
					alert("실패 : 관리자에게 문의")
				}else if(data == 2){
					alert("이미 존재하는 아이디 입니다.")
				}else if(data == 3){
					alert("이미 존재하는 전화번호 입니다.")
				}else if(data == 4){
					alert("올바른 형식의 전화번호를 입력하세요.")
				}else if(data == 5){
					alert("모든 항목을 입력하세요.")
				}							
			})
			.catch(e => {console.log(e)})
}