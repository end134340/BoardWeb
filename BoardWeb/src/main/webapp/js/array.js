/*
 * array.js 
 */

const ary = [];
//배열의 추가 관련 메소드: push, unshift
ary.push('홍길동'); //제일 마지막 위치에 하나씩 추가
ary.push('김길동');
ary.unshift('최길동'); //맨 처음에 추가

//배열의 삭제 관련 메소드: pop, shift
//ary.pop(); //마지막에 있는 요소 삭제
//ary.shift(); //처음에 있는 요소 삭제

//배열의 추가, 수정, 삭제 가능 메소드: splice
//ary.splice(0, 1, '박길동', '황길동'); //첫 번째 매개값: 위치(idx) 두 번째 매개값: 개수(크기). 0일 경우 잘라내지 않고 추가하는게 됨. 세 번째 매개값: 대체할 값. 


//forEach 메소드
ary.forEach(function(item, idx, ary) {
	console.log(`item => ${item}`); //요소
	console.log(`idx => ${idx}`); //인덱스
	console.log(`ary => ${ary}`); //배열 그 자체
})

//함수

function addElement(ele = 'noEle') { //매개 변수에 초기 값을 지정할 수 있음(매개변수로 값이 들어오지 않으면 할당한 값을 사용하겠다는 의미)
	ary.push(ele);
}

//화면요소 제거
function deleteElement(e){
	//e.target: 눌린 버튼
	console.log(e.target.parentElement);
	e.target.parentElement.remove();
	alert('삭제 되었습니다.');
}

//추가 버튼에 클릭 이벤트
let addBtn = document.querySelector('#addBtn');
addBtn.addEventListener('click', addFnc);

function addFnc() {
	let input = document.querySelector('#userInput');
	addElement(input.value); //ary배열에 추가
	//화면에 출력
	let html = '';
	ary.forEach(function(item, idx, ary){
		html += `<li>${item}<button onclick="deleteElement(event)">x</button></li>`;
	});
	//ul태그의 하위에 그려주기
	document.querySelector('ul#list').innerHTML = html;
}//
