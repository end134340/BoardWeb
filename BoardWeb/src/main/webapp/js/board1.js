/*
 * XMLHttpRequest, fetch => 실행순서 
 */

let page = 1;

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
	//console.log(result);
	//기존 목록 화면에서 지우기
	document.querySelectorAll('div.content li').forEach(function(item, idx) {
		if (idx) { //자바스크립트에서 참이거나 거짓인 것처럼 여겨지는 값: truthy, falsy(0, null, '', undefined)
			//idx가 0(no. 내용. 작성자 등의 라벨)이면 지우지 않겠다는 뜻
			item.remove();
		}
	})
	result.forEach(item => makeRow2(item))
}

function errorCallback(err) { //정상적인 실행이 되지 않으면(에러가 나면) catch()함수 안의 코드를 실행.
	console.error(err);
}

//페이징 콜백
function pagingCallback(result) {
	//기존에 있던 페이지 목록 지우기
	let liTag = document.querySelectorAll('ul.pagination li');
	liTag.forEach((li) => li.remove());


	console.log(result);
	let totalCnt = result.totalCnt;

	//첫 페이지, 마지막 페이지 => 현재 페이지를 기준으로 계산.
	let startPage, endPage;
	//이전 페이지, 이후 페이지
	let prev, next;

	endPage = Math.ceil(page / 10) * 10;
	startPage = endPage - 9;
	let realEnd = Math.ceil(totalCnt / 10);
	endPage = endPage > realEnd ? realEnd : endPage; //마지막 페이지가 실제 마지막 페이지보다 크면 실제 마지막 페이지를 할당
	prev = startPage == 1 ? false : true; //시작 페이지가 1이면 false
	next = endPage < realEnd ? true : false; //계산상의 마지막 페이지가 실제 마지막 페이지보다 작으면 true

	let html = '';
	let target = document.querySelector('ul.pagination');

	//이전페이지
	// 페이지가 없는 경우: <li class="page-item disabled"> <span class="page-link">Previous</span> </li>
	// 페이지가 있는 경우: <li class="page-item"> <a class="page-link" href="#">Next</a> </li>

	if (!prev) {
		html += `<li class="page-item disabled"> <span class="page-link">Previous</span> </li>`;
	} else {
		html += `<li class="page-item"> <a class="page-link" data-page="${startPage - 1}" href="#">Previous</a> </li>`;
	}

	for (let p = startPage; p <= endPage; p++) {
		if (p == page) {
			html += `<li class="page-item active" aria-current="page">
			      		<span class="page-link">${p}</span>
			    	</li>`;
		} else {
			html += `<li class="page-item"><a class="page-link" data-page="${p}" href="#">${p}</a></li>`;
		}

	}

	//이후페이지
	if (!next) {
		html += `<li class="page-item disabled"> <span class="page-link">Next</span> </li>`;
	} else {
		html += `<li class="page-item"> <a class="page-link" data-page="${endPage + 1}" href="#">Next</a> </li>`;
	}
	target.insertAdjacentHTML("beforeend", html)
	//링크(페이지) 이벤트 등록
	pageLink();
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
				//document.querySelector('#rno_' + rno).remove();
				svc.replyList({ bno, page }, successCallback, errorCallback);
				svc.pagingList(bno, pagingCallback, errorCallback);
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
			//makeRow2(item);
			document.querySelector('#reply').value = '';
		} else {
			alert('댓글 등록에 실패했습니다.');
		}
		svc.replyList({ bno, page }, successCallback, errorCallback);
		svc.pagingList(bno, pagingCallback, errorCallback);
		console.log(bno, reply, replyer);
	}, errorCallback);


}

//페이지 링크에 이벤트 등록
function pageLink() {
	let paging = document.querySelectorAll('ul.pagination a');

	paging.forEach(function(aTag) {
		aTag.addEventListener('click', function(e) {
			e.preventDefault(); //이벤트의 기본 기능을 차단하겠다는 메소드 (링크를 클릭해도 이동하지 않겠다는 것)
			page = aTag.dataset.page; //data-page 속성을 추가함. data-~~속성을 읽어올 때에는 dataset.~~로 가능.  //innerHTML; //<a>3</a> 등의 값
			svc.replyList({ bno, page }, successCallback, errorCallback);

			svc.pagingList(bno, pagingCallback, errorCallback);
		})
	});

}


//목록 보여주기       
//기능을 boardService에 svc라는 객체를 선언, 메소드를 하나 만들어 이곳에서 호출.
svc.replyList({ bno, page }, successCallback, errorCallback);
//원래는 { bno: bno, page: page }이지만 필드와 값이 같을 경우에는 생략이 가능함.

//페이징 목록 보여주기
svc.pagingList(bno, pagingCallback, errorCallback);


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
