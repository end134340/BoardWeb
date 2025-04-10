<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset='utf-8' />
	<script src='js/index.global.js'></script>
	<script>
		//1
		document.addEventListener('DOMContentLoaded', async function () {
			var calendarEl = document.getElementById('calendar');
			var events = [];

			//2
			//async 함수 안에 await를 쓰면 결과를 가지고 올 때까지 기다림
			let result = await fetch('eventList.do');
			let result2 = await result.json();
			events = result2;

			//3
			var calendar = new FullCalendar.Calendar(calendarEl, {
				headerToolbar: {
					left: 'prev,next today',
					center: 'title',
					right: 'dayGridMonth,timeGridWeek,timeGridDay'
				},
				initialDate: '2023-01-12',
				navLinks: true, // can click day/week names to navigate views
				selectable: true,
				selectMirror: true,
				select: async function (arg) {
					var title = prompt('Event Title:');
					if (title) {
						console.log(arg);
						let allDay = arg.allDay; // 전체 일정인지 부분 일정인지에 대한 여부
						let start = allDay ? arg.startStr : arg.startStr.substring(0, 19);
						let end = allDay ? arg.endStr : arg.endStr.substring(0, 19);
						//let r1 = await fetch('addEvent.do?title=' + title + '&start=' + start + '&end=' + end);
						//post방식
						let r1 = await fetch('addEvent.do', {
							method: 'post',
							//post타입이면 반드시 header가 들어가야 함.
							headers: {
								'Content-Type': 'application/x-www-form-urlencoded'
							},
							//body에는 파라미터값이 넘어감(아마)
							body: 'title=' + title + '&start=' + start + '&end=' + end
						});
						let r2 = await r1.json();
						if (r2.retCode == 'OK')
							calendar.addEvent({
								title: title,
								start: arg.start,
								end: arg.end,
								allDay: arg.allDay
							})
						else
							alert('등록에 실패했습니다.');
					} //it title
					calendar.unselect()
				},
				eventClick: function (arg) {
					if (confirm('이 이벤트를 지우시겠습니까?')) {
						arg.event.remove()
						console.log(arg.event._def.title);
						let title = arg.event._def.title;
						fetch('removeEvent.do?title=' + title)
							.then(result => result.json())
							.then(result => {
								if (result.retCode == 'OK') {
									alert('일정이 삭제되었습니다.');
								}
							})
					}
				},
				editable: true,
				dayMaxEvents: true, // allow "more" link when too many events
				events: events
			});

			calendar.render();




		});
	</script>
	<style>
		body {
			margin: 40px 10px;
			padding: 0;
			font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
			font-size: 14px;
		}

		#calendar {
			max-width: 1100px;
			margin: 0 auto;
		}
	</style>
</head>

<body>

	<div id='calendar'></div>

</body>

</html>