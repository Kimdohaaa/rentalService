console.log( "header.js open");

const alarmSocket = new WebSocket('ws://192.168.40.72:8080/rental/alarmsocket');


console.log( alarmSocket);

alarmSocket.onopen = (e)=>{
	console.log( alarmSocket);
}

alarmSocket.onmessage = (msgEvent) => {
	console.log(msgEvent.data);
	
	const alarmbox = document.querySelector('.alarmbox')
	
	let html = `<div class="toast show" role="alert" aria-live="assertive" aria-atomic="true">
		                                  <div class="toast-header">
		                                    <strong class="me-auto">${ msgEvent.data }</strong>
		                                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
		                                  </div>
		                                </div>`
		       
		        alarmbox.innerHTML = html
}
