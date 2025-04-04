/*
 * 
 */

const jsonStr = `[{"id":1,"first_name":"Kandy","last_name":"Gauntley","email":"kgauntley0@tripod.com","gender":"Female","salary":4343},
{"id":2,"first_name":"Karyl","last_name":"Aveson","email":"kaveson1@about.com","gender":"Female","salary":3138},
{"id":3,"first_name":"Monika","last_name":"Tourot","email":"mtourot2@cnbc.com","gender":"Female","salary":6881},
{"id":4,"first_name":"Rudolf","last_name":"Steers","email":"rsteers3@noaa.gov","gender":"Male","salary":6172},
{"id":5,"first_name":"Claiborn","last_name":"Heal","email":"cheal4@engadget.com","gender":"Male","salary":8484},
{"id":6,"first_name":"Timmy","last_name":"D'Andrea","email":"tdandrea5@abc.net.au","gender":"Male","salary":3452},
{"id":7,"first_name":"Sybille","last_name":"McTerrelly","email":"smcterrelly6@vinaora.com","gender":"Female","salary":4490},
{"id":8,"first_name":"Joel","last_name":"Kerwick","email":"jkerwick7@nymag.com","gender":"Male","salary":4736},
{"id":9,"first_name":"Alvie","last_name":"Farrell","email":"afarrell8@gmpg.org","gender":"Male","salary":5028},
{"id":10,"first_name":"Marti","last_name":"Elson","email":"melson9@nasa.gov","gender":"Female","salary":6305},
{"id":11,"first_name":"Delila","last_name":"Munkley","email":"dmunkleya@rediff.com","gender":"Female","salary":6096},
{"id":12,"first_name":"Camellia","last_name":"McKeefry","email":"cmckeefryb@stanford.edu","gender":"Female","salary":1107},
{"id":13,"first_name":"Suellen","last_name":"MacFadden","email":"smacfaddenc@weather.com","gender":"Female","salary":1367},
{"id":14,"first_name":"Shana","last_name":"Tuffs","email":"stuffsd@friendfeed.com","gender":"Female","salary":8256},
{"id":15,"first_name":"Saul","last_name":"MacRitchie","email":"smacritchiee@xinhuanet.com","gender":"Male","salary":6365}]`;

let obj = JSON.parse(jsonStr); //JSON.parse: json타입의 문자열을 자바 스크립트의 object 타입으로 변경시켜줌.
let str = JSON.stringify(obj); //JSON.stringify: object타입을 json타입의 문자열로 변경시켜줌.
console.log(obj);

//데이터 한 건을 매개값으로 해서 tr 하나를 생성하는 함수.
function makeRow(emp = { id, first_name, last_name, email, gender, salary }) {
	const fields = ['id', 'first_name', 'last_name', 'email'];

	let tr = document.createElement('tr');
	for (let i = 0; i < fields.length; i++) {
		let td = document.createElement('td');
		td.innerHTML = emp[fields[i]];
		tr.appendChild(td);
	}
	return tr;
}

//화면 출력
obj.forEach(function(item, idx, ary) {
	let tr = makeRow(item);
	let tbody = document.querySelector('#target');
	tbody.appendChild(tr);
})



