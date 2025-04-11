<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>gov.jsp</title>
	<link rel="stylesheet" href="css/styles.css" />
</head>
시도선택:
<select id="choice" class="form-control">

</select>
<table class="table table-striped">
	<thead>
		<tr>
			<th>ID</th>
			<th>센터명</th>
			<th>연락처</th>
			<th>시/도 정보</th>
		</tr>
	</thead>
	<tbody id="centerList">

	</tbody>
</table>
<ul id="list"></ul>

<body>
	<script>
		let url =
			'https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=eh1Jd6AyDgjeGHXSu%2FMXLNCuCXQIlYP5lOapbw5ztmkaxqsfrtluMqLVj8o%2FuUSDgjOgbcwQ1Jh5tqQ93jU7nw%3D%3D';
		//센터 목록을 저장하기 위한 배열
		let centerList = [];
		//배열에서만 사용할 수 있는 메소드
		//filter메소드
		//reduce메소드

		fetch(url)
			.then(result => result.json())
			.then(result => {
				//console.log(result.data);
				centerList = result.data;
				let sidoAry = []; //284건의 데이터 중 시도정보
				//중복 정보만 제거하고 담아주려고 함.
				centerList.forEach(center => {
					if (sidoAry.indexOf(center.sido) == -1) {
						sidoAry.push(center.sido); //중복되지 않은 값만 등록.

					}
				})
				//console.log(sidoAry);
				//시도정보 생성(option태그 생성)
				sidoAry.forEach(sido => {
					let option = document.createElement('option');
					option.innerHTML = sido;
					document.querySelector('#choice').appendChild(option);
				})

				//초기목록출력(처음에는 센터를 10개 정도만 보여줌.)
				centerList.forEach((center, idx) => {
					if (idx < 10) {
						let tr = makeCenter(center);
						document.querySelector('#centerList').appendChild(tr);
					}
				})
			})
			.catch(err => console.error(err));

		//이벤트 목록
		document.querySelector('#choice').addEventListener('change', changeSido);
		//javascript의 this 키워드: 함수 안에서의 this라는 키워드는 window라는 최상위 존재를 가리킴.
		//event 안에서의 this라는 키워드는 이벤트를 받는 대상.
		// function test() {
		// 	console.log(this);
		// }
		// test();
		function changeSido(e) {
			//console.log(this); //이벤트가 일어나고 있는 대상
			let val = this.value;
			document.querySelector('#centerList').innerHTML = '';

			//filter메소드: 반환되는 값이 true인 요소들만 새로운 배열에 담아줌
			centerList.filter(center => center.sido == val //true기 반환되는 시점의 값을 새로운 배열에 담음
			).forEach((center, idx) => { //filter가 반환해주는 값은 배열이기 때문에 바로 forEach가능
				let tr = makeCenter(center);
				document.querySelector('#centerList').appendChild(tr);

			})
		} //체인지 이벤트 끝부분


		function makeCenter(center) {
			let tr = document.createElement('tr');
			//tr영역 클릭 이벤트
			tr.addEventListener('click', function (e) {
				openWindow(center);
			});
			let fields = ['id', 'centerName', 'phoneNumber', 'sido'];
			//td생성
			fields.forEach(field => {
				let td = document.createElement('td');
				td.innerHTML = center[field];
				tr.appendChild(td);
			})
			return tr;
		} //makeCenter

		//tr클릭 이벤트 함수
		function openWindow(center = {}) {
			//window객체가 가진 open 메소드(새 창을 열어줌)

			window.open('map.jsp?lat=' + center.lat + '&lng=' + center.lng);

		}
	</script>
	<script src="js/array2.js"></script>
</body>

</html>