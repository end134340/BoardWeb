/*
 *  
 */

const table = new DataTable('#example', {
	ajax: 'replyListDatatable.do?bno=' + bno,
	columns: [
		{ data: 'REPLY_NO' },
		{ data: 'REPLY' },
		{ data: 'REPLYER' },
		{ data: 'REPLY_DATE' }
	],
	lengthMenu: [
		[5, 25, 50, -1],
		[5, 25, 50, 'All']
	],
	order: [[0, 'desc']]
});

async function addNewRow() {
	let replys;
	let reply = document.querySelector('#reply');
	await fetch('addReply.do?bno=' + bno + '&replyer=' + replyer + '&reply=' + reply.value)
		.then(result => result.json())
		.then(result => {
			if (result && result.retVal) {
				replys = result.retVal;
				console.log(replys);
				return replys;
			}
		})
		.catch(err => console.error(err));
	if (replys) {
		table.row
			.add({
				REPLY_NO: replys.replyNo,
				REPLY: replys.reply,
				REPLYER: replys.replyer,
				REPLY_DATE: replys.replyDate
			})
			.draw(false);
	}
	reply.value = '';
}



document.querySelector('#addRow').addEventListener('click', addNewRow);

let rno;
table.on('click', 'tbody tr', (e) => {
	let classList = e.currentTarget.classList; //classList => class목록을 배열로 가지고있음
	rno = e.currentTarget.children[0].innerHTML;
	if (classList.contains('selected')) {
		classList.remove('selected');
	}
	else {
		table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
		classList.add('selected');
	}
});
document.querySelector('#button').addEventListener('click', function() {
	table.row('.selected').remove().draw(false);
	
	fetch('removeReply.do?rno=' + rno)
	.then(result => result.json())
	.then(result =>{
		if(result.retCode == 'OK'){
			alert('댓글이 삭제되었습니다.');
		}else{
			alert('댓글이 삭제되지 못했습니다.');
		}
	}
	)
	.catch(err => console.error(err));
});