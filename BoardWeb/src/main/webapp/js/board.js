/*
 * 댓글 정보를 ajax로 받아옴
 */

console.log(bno);

const xhtp = new XMLHttpRequest();
xhtp.open('get', 'replyList.do?bno=' + bno);
xhtp.send(); //send메소드가 실행되어야 데이터를 가져옴.
xhtp.onload = function() {
	console.log(xhtp.responseText); //응답 결과 정보가 responseText라는 문자열?에 들어감.
	let data = JSON.parse(xhtp.responseText);
	console.log(data);

	data.forEach(function(item) {
		makeRow2(item);
	})
}

function makeRow2(item) {
	let html = `<li>
				 	<span class="col-sm-2">${item.replyNo}</span>
				 	<span class="col-sm-5">${item.reply}</span>
				 	<span class="col-sm-2">${item.replyer}</span>
				 	<span class="col-sm-2"><button class="btn btn-danger">삭제</button></span>
		       </li>`;
	let templ = document.querySelector('div.content>ul');
	templ.insertAdjacentHTML('beforeend', html); //어느 위치에 넣겠다는 옵션 afterbegin: 역순으로 정렬? beforend: 아마 끝 태그 앞?
	//insertAdjacentHTML: 태그에 값을 추가?할?수있는?메소드???
}


function makeRow(item) {
	//template
	let templ = document.querySelector('div.content>ul>li').cloneNode(true); //cloneNode: 요소를 하나 복제하는 메소드. true: 하위 요소들까지 clone하겠다는 옵션.

	console.log(templ);
	templ.querySelector('span:nth-of-type(1)').innerHTML = item.replyNo; //nth-of-type: 그 태그 중 몇 번째 태그?인지?
	templ.querySelector('span:nth-of-type(2)').innerHTML = item.reply;
	templ.querySelector('span:nth-of-type(3)').innerHTML = item.replyer;
	templ.querySelector('span:nth-of-type(4)').innerHTML = '<button class="btn btn-danger">삭제</button>';

	document.querySelector('div.content>ul').appendChild(templ);

}
