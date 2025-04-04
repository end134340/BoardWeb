/*
 * object.js 
 */

console.log('start');

//DOM 제어
let li = document.createElement('li') //요소(Element)를 만드는 메소드?
li.innerHTML = 'cherry'; //생성한 li태그의 속성에 값을 담아줌.

document.querySelector('ul#list').appendChild(li); //ul의  자식 요소로 li를 넣어줌
document.querySelector('ul#list').style.display = 'none';





//값이 할당되는 시점에 데이터 타입이 정해짐
let name = 100; //"Hong";
console.log(typeof name);

//객체 선언
const obj = {
	name: "홍길동"
	, age: 20
	, friends: ['김길동', '박길동']
	, pets: [{ name: '멍멍이', age: 3 }
		, { name: '야옹이', age: 2 }]
}

obj.height = 165.8;

/*console.log(`obj의 타입: ${typeof obj}`);
console.log(`이름: ${obj.name}, 나이: ${obj["age"]}`);
console.log(`홍길동의 첫 번째 친구 = ${obj.friends[0]}`);
console.log(`홍길동의 두 번째 친구 = ${obj['friends'][1]}`);
console.log(`홍길동의 첫 번째 동물 ${obj.pets[0].name}는 사실 고양이입니다.`);
*/
//최길동이라는 친구가 생김
obj.friends[2] = '최길동';

for (let i = 0; i < obj.friends.length; i++) {
	//console.log(`홍길동의 친구들: ${obj.friends[i]}`);
}

//토끼가 생김
obj.pets.push({ name: '톳이', age: 1 }); //배열에 값을 추가하는 메소드

//애완동물 목록
//table형식으로 출력
let tblHtml = '<table class="table">';
tblHtml += '<thead><tr><th>이름</th><th>나이</th></tr></thead>'
for(let pet of obj.pets){ //확장된 for
	tblHtml += `<tr><td>${pet.name}</td><td>${pet.age}살</td><tr>`;
}
tblHtml += '</table>';
document.querySelector('nav+div.container-fluid').innerHTML += tblHtml;



