const rentalRead = () => {
    const option = { method: 'GET' };
    fetch(`/rental/admin/memberinfo`, option) 
        .then(response => response.json())
        .then(data => {
            console.log(data);

            const rentalbox = document.querySelector('.rentalbox');
            let html = '';

            if (data && data.length > 0) {
                data.forEach(member => {
                    html += `<tr>
                        <td>${member.mno}</td>
                        <td>${member.mid}</td>
						<td>${member.mphone}</td>
						<td>${member.mdate}</td>
                    </tr>`;
                });
            } else {
                html = '<tr><td colspan="5">회원정보 없음</td></tr>';
            }

            rentalbox.innerHTML = html;
        })
        .catch(error => {
            console.error('Error fetching member data:', error);
        });
};
rentalRead();