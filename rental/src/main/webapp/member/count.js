function count(type,rtime)  {

  // 결과를 표시할 element
  const resultElement = document.querySelector(`#result${rtime < 10 ? "0" + rtime: rtime}`);
  console.log(rtime)
  // 현재 화면에 표시된 값
  let number = resultElement.innerText;
  
  // 더하기/빼기
  if(type === 'plus') {
    number = parseInt(number) + 1;
  }else if(type === 'minus')  {
	if(number <= 0){
		return;
	}else {
    	number = parseInt(number) - 1;
	}
  }
  
  // 결과 출력
  resultElement.innerText = number;
}