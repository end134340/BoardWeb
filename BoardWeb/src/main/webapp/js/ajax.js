/*
 * ajax.js
 */
let dataAry;
const xhtp = new XMLHttpRequest(); //서버상의... 페이지의 데이터를 읽어올수있는 메소드
xhtp.open('get', 'data/MOCK_DATA.json');
xhtp.send(); //읽어오는 메소드
xhtp.onload = function() { //로드 이벤트가 발생하면 함수 실행
	let obj = JSON.parse(xhtp.responseText); //json형태의 문자열을 obj로 만드는 메소드
	console.log(obj);
	dataAry = obj;
	obj.forEach(function(item, idx, ary) {
		let tr = makeRow(item);
		let tbody = document.querySelector('#target');
		tbody.appendChild(tr);
	})
}

//데이터 한 건을 매개값으로 해서 tr 하나를 생성하는 함수.
function makeRow(emp = { id, first_name, last_name, email, gender, salary }) {
	const fields = ['id', 'first_name', 'last_name', 'gender'];

	let tr = document.createElement('tr');
	let td = document.createElement('td');
	let ckbox = document.createElement('input');
	ckbox.setAttribute('type', 'checkbox');
	td.appendChild(ckbox);
	tr.appendChild(td);
	for (let i = 0; i < fields.length; i++) {
		td = document.createElement('td');

		td.innerHTML = emp[fields[i]];
		tr.appendChild(td);
	}
	td = document.createElement('td');
	let delBtn = document.createElement('button');
	delBtn.innerText = 'X';
	delBtn.setAttribute('class', 'btn btn-light');

	delBtn.addEventListener('click', deleteRow)

	td.appendChild(delBtn);
	tr.appendChild(td);


	return tr;
}


//delBtn에 삭제 이벤트 추가
function deleteRow(e) {
	e.target.parentElement.parentElement.remove();
}

let thCheck = document.querySelector('thead input[type="checkbox"]');
thCheck.addEventListener('change', checkFnc);

function checkFnc(e) {
	let checkList = document.querySelectorAll('tbody input[type="checkbox"]');
	//교수님 코드(target의 checked 값을 변수에 하나 저장해서 그 값을 checkList에도 똑같이 적용)
	let checked = e.target.checked;
	checkList.forEach(function(item) {
		item.checked = checked;
	})
	//내 코드
	/*if (thCheck.checked == true) {
		for (let i = 0; i < checkList.length; i++) {
			checkList[i].checked = true;
		}
	} else if (thCheck.checked == false) {
		for (let i = 0; i < checkList.length; i++) {
			checkList[i].checked = false;
		}
	}*/
}

let searchTag = document.querySelector('#searchGender');
searchTag.addEventListener('change', selectGender)

function selectGender(e) {
	document.querySelector('#target').innerHTML = '';
	let evalue = e.target.value;
	dataAry.forEach(function(item) {
		if (evalue == 'all' || item.gender == evalue) {
			let tr = makeRow(item);
			let tbody = document.querySelector('#target');
			tbody.appendChild(tr);
		}
	})

}//