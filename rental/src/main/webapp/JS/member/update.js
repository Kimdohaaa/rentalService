console.log("myinfo.js open")
// [1] 내정보 조회
const myinfo = () => {
	console.log("myinfomethod")
	fetch("/rental/member/myinfo")
		.then(r => r.json())
		.then(data => {
			if(data != null){
				document.querySelector(".midbox").value = data.mid;
				document.querySelector(".mpwdbox").value = data.mpwd;
				document.querySelector(".mphonebox").value = data.mphone;
				document.querySelector(".maddrbox").value = data.maddr;
				document.querySelector(".mgenderbox").value = data.mgender;
			}
		})
		.catch(e => {console.log(e)})
}
myinfo();

// [2] 회원 정보 수정
const onupdate = () => {
	const mpwdin = document.querySelector(".mpwdbox");
	const mphonein = document.querySelector(".mphonebox");
	const maddrin = document.querySelector(".maddrbox");
	const mgenderin = document.querySelector(".mgenderbox");
	
	const mpwd = mpwdin.value;
	const mphone = mphonein.value;
	const maddr = maddrin.value;
	const gender = mgenderin.value;
	
	const mgender = 0;
	if(gender == "여자"){
		mgender = 1;
	}
	
	const obj = {
		mpwd : mpwd,
		mphone : mphone,
		maddr : maddr,
		mgender : mgender
	}
	
	const option = {
		method : 'PUT',
		headers : {"Content-Type" : "application/json"},
		body : JSON.stringify(obj)		
	}
	
	fetch('/rental/member/myinfo', option)
		.then(r => r.json())
		.then(data => {
			if(data == true){
				alert("정보 수정 완료")
				location.href = "/rental/member/myinfo.jsp" 
			}else{
				alert("정보 수정 실패")
			}
		})
		.catch(e => {console.log(e)})
	
}
