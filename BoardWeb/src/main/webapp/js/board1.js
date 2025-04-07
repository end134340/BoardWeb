/*
 * XMLHttpRequest, fetch => 실행순서 
 */

//동기, 비동기(Asynchronous Javascript And Xml => AJAX(비동기 방식으로 처리해줌))
//동기식 처리: 하나의 코드가 끝나면 다음 코드 실행.
//비동기식 처리: 하나의 코드가 끝나기 전 미리 요청을 해둠. 상대적으로? 속도가 빠름
setTimeout(function() {
	console.log('1');
	setTimeout(function() {
		console.log('2');
		setTimeout(function() {
			console.log('3');
		}, 1000);
	}, 1000);

}, 1000); ///setTimeout: 함수와, 그 함수를 실행하기 전 딜레이? 시간을 매개값으로 가지는 함수.

/*fetch('replyList.do?bno=' + bno)
	.then(result => result.json())//정상적인 실행이 되면 then()함수 안의 코드를 실행.
	//then함수에서 return되는 값은 다음 then함수에 전달됨. 화살표 함수가 될 경우 return 생략 가능.
	//json() => json문자열을 파싱해주는 것과 같?음
	.then(successCallback)
	.catch(errorCallback)*/



//함수 선언식 - 함수 표현식
let successCallback = function successCallback(result) {
	console.log(result);
	result.forEach(item => makeRow2(item))
}

function errorCallback(err) { //정상적인 실행이 되지 않으면(에러가 나면) catch()함수 안의 코드를 실행.
	console.error(err);
}

function deleteReplyFnc(rno) {
	let deleteOK = confirm("삭제하시겠습니까?");
	if (!deleteOK) {
		return;
	}
	svc.removeReply(rno
		, function(result) {
			console.log(result); //{"retCode": "OK"}
			if (result.retCode == 'OK') {
				console.log('rno: ' + rno);
				alert('댓글을 삭제했습니다.');
				document.querySelector('#rno_' + rno).remove();
			} else {
				alert('댓글이 삭제되지 못했습니다.');
			}
		}, function(err) {
			console.error(err);
		});
}

//이벤트
let addBtn = document.querySelector('#addReply');
addBtn.addEventListener('click', addFnc);

function addFnc(e) {
	if (replyer == '') {
		alert('로그인한 사용자만 이용할 수 있습니다.');
		return;
	}
	//bno, replyer, reply 필요
	let reply = document.querySelector('#reply').value;
	if (reply == '') {
		alert('빈 댓글은 등록할 수 없습니다.');
		return;
	}

	svc.addReply({ bno, reply, replyer }, function(result) {
		console.log(result);
		if (result.retCode == 'OK') {
			alert('댓글이 등록되었습니다.');
			console.log(result.retVal);
			let item = result.retVal; //반환되는 결과값 활용.
			makeRow2(item);
			document.querySelector('#reply').value = '';
		} else {
			alert('댓글 등록에 실패했습니다.');
		}
		console.log(bno, reply, replyer);
	}, errorCallback);


}



//기능을 boardService에 svc라는 객체를 선언, 메소드를 하나 만들어 이곳에서 호출.
svc.replyList(bno, successCallback, errorCallback);

function makeRow2(item) {//data-어쩌고저쩌고 = attribute
	let html = `<li data-rno="${item.replyNo}" id="rno_${item.replyNo}" > 
					 	<span class="col-sm-2">${item.replyNo}</span>
					 	<span class="col-sm-5">${item.reply}</span>
					 	<span class="col-sm-2">${item.replyer}</span>
					 	<span class="col-sm-2"><button class="btn btn-danger" onclick="deleteReplyFnc(${item.replyNo})">삭제</button></span>
			    </li>`;
	let templ = document.querySelector('div.content>ul');
	templ.insertAdjacentHTML('beforeend', html); //어느 위치에 넣겠다는 옵션 afterbegin: 역순으로 정렬? beforend: 아마 끝 태그 앞?
	//insertAdjacentHTML: 태그에 값을 추가?할?수있는?메소드???
}
