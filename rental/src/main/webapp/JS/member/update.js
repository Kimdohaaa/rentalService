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
				const mgenderin = document.querySelector(".mgenderin");
				if(data.mgender == 1){
					mgenderin.value = "1";
					document.querySelector(".default").textContent = "남성";
				}else if(data.mgender == 2){
					mgenderin.value = "2";
					document.querySelector(".default").textContent = "여성";
				}else {
					mgenderin.value = "none";
				     mgenderin.value = "";
				 }
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
	const mgenderin = document.querySelector(".mgenderin");
	
	const mpwd = mpwdin.value;
	const mphone = mphonein.value;
	const maddr = maddrin.value;
	const mgender = mgenderin.value;
	console.log(mgender)
	if (mgender === "none" || mgender === "") {
	        console.log("성별이 선택되지 않았습니다.");
	    } else {
	        console.log("선택된 성별: ", mgender);
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

