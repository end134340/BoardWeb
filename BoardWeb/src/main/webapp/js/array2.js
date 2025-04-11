/*
 * 
 */

const numAry = [10, 17, 23, 26, 49];

//filter(): 메소드 안에 매개값으로 받는 값이 함수. return이 true인 값만 새로운 배열을 생성해 반환.
let filterAry = numAry.filter(function (item, idx, ary) { //배열의 요소, 인덱스, 배열 그 자체를 가짐
  return item % 2 == 0; //true값이 반환되는 시점의 item값을 새로운 배열에 저장.
});

console.log(filterAry);

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

const jsonAry = JSON.parse(jsonStr);

filterAry = jsonAry.filter(item => item.gender == "Male" && item.salary >= 5000);

console.log(filterAry);

//reduce(): 배열 메소드. 각각의 요소들을 반복하며 정의되어 있는 함수를 실행.
let result = [10, 23, 34, 48, 51].reduce(function (acc, item, idx, ary) {
  //acc: 이전 순번에서 반환해준 결과값
  //return item; 으로 하면 item의 값을 acc의 값으로 쓰기 위해??? 반환하는게 됨.
  console.log(`acc: ${acc}, item: ${item}`)
  if (item > 30) {
    acc.push(item);
  }
  return acc;
}, []); //reduce는 함수와 초기값의 두 매개값을 가지기 때문에 acc로 사용할 초기값으로 0을 할당.

console.log(result);
let list = document.querySelector('#list');
[10, 23, 34, 48, 51].reduce((acc, item) => {
  let li = document.createElement('li');
  li.innerHTML = item;
  acc.appendChild(li);
  return acc;
}, list); //DOM요소를 초기값으로 전달.

const ary = [{
    name: "홍길동",
    phone: "010-1111-2222"
  },
  {
    name: "김길동",
    phone: "010-2222-3333"
  },
  {
    name: "최길동",
    phone: "010-3333-4444"
  },
  {
    name: "이길동",
    phone: "010-4444-5555"
  },
];

list.innerHTML = '';

ary.reduce((acc, item) => {
  let li = document.createElement('li');
  li.innerHTML = item.name + ' - ' + item.phone;
  acc.appendChild(li);
  return acc;
}, list)