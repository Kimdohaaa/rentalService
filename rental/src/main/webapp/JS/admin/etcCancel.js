const etcCancel = () => {
	const option = {method : 'GET'}
	fetch('/rental/admin/etc', option)
		.then(r => r.json())
		.then(data => {
			console.log(data);
			const cancelbox = document.querySelector('.cancelbox')
			let html = ``;
			data.forEach(rreason =>{
				html += `<tr>
						<td> 기타 </td>
					    <td> ${ rreason.rreasonEtc } </td>
						<td> ${ rreason.mid } </td>
						<td> ${ rreason.sname } </td>
						</tr>`;
			})
			cancelbox.innerHTML = html;
		})
		.catch(e => {console.log(e);})
}
etcCancel();