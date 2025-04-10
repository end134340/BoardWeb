<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/async.jsp</title>
</head>
<body>
	<button id="button">버튼</button>
	<script>
		//비동기 코드 => 동기방식(async await)
		//async: promise객체가 모두 처리될 때까지 코드가 실행되지 않고 기다림.
		//await: 기다려줄? 코드
		document.querySelector('button').addEventListener('click', async function(){
			console.log('1');
			let result = await fetch('eventList.do');
			console.log('2');
			
			let resolve = await result.json();
			console.log(resolve);

		// 	.then(result=>result.json())
		// 	.then(result=>{
		// 		console.log(result);
		// 	}) 
		// 	.catch(err=>console.error(err));
		})
	</script>
</body>
</html>