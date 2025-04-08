/*
 * 
 */

const svc = {
	name: "홍길동"
	//목록
	, replyList: function(search = { bno, page }, successCallback, errorCallback) {
		//넘어와야 할 파라미터값?, 성공했을 때 실행할 함수, 실패했을 때 실행할 함수
		//첫 번째 then은 가지고 온 data를 파싱하는 역할을 함.
		fetch('replyList.do?bno=' + search.bno + '&page=' + search.page)
			.then(result => result.json())
			.then(successCallback)
			.catch(errorCallback)
	}
	//삭제
	, removeReply(rno, successCallback, errorCallback) {
		fetch('removeReply.do?rno=' + rno)
			.then(result => result.json())
			.then(successCallback)
			.catch(errorCallback)
	}

	//등록
	, addReply(rep = { bno, reply, replyer }, successCallback, errorCallback) {
		fetch('addReply.do?bno=' + rep.bno + '&reply=' + rep.reply + '&replyer=' + rep.replyer)
			.then(result => result.json())
			.then(successCallback)
			.catch(errorCallback)
	}
	//페이지 계산
	, pagingList(bno = 234, successCallback, errorCallback) {
		fetch('replyCount.do?bno=' + bno)
			.then(result => result.json())
			.then(successCallback)
			.catch(errorCallback)
	}

}


