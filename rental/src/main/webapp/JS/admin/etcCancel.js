const etcCancel = () => {
	
	let page = new URL(location.href).searchParams.get('page')
	if(page == null) page = 1;
	const option = {method : 'GET'}
	fetch(`/rental/admin/etc?page=${page}`, option)
		.then(r => r.json())
		.then(response => {
			console.log(response);
			const cancelbox = document.querySelector('.cancelbox')
			let html = ``;
			let cancelBox = response.data;
			cancelBox.forEach(rreason =>{
				html += `<tr>
						<td> 기타 </td>
					    <td> ${ rreason.rreasonEtc } </td>
						<td> ${ rreason.mid } </td>
						<td> ${ rreason.sname } </td>
						</tr>`;
			})
			cancelbox.innerHTML = html;
			getPageBtn(response)
		})
		.catch(e => {console.log(e);})
}
etcCancel();
const getPageBtn = (response) =>{
	page = parseInt(response.page);
	const pagebtnbox = document.querySelector('.pagebtnbox');
	let html = ``;
	html += `	<li class="page-item">
	            <a class="page-link" href="etcCancel.jsp?page=${ page <= 1 ? 1 : page-1 }">이전</a>
	             </li>`;
	for( let index = response.startbtn ; index <= response.endbtn ; index++ ){
		html += `<li class="page-item">
				<a class="page-link ${ page == index ? 'active' : '' }" href="etcCancel.jsp?page=${ index }">
                   ${ index }
				      </a>
				</li>`
	}
	html +=`<li class="page-item">
	        <a class="page-link" href="etcCancel.jsp?page=${ page >= response.totalpage ? page : page+1  }">다음</a>
	        </li>`
	pagebtnbox.innerHTML = html;		
}