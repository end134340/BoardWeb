<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='js/index.global.js'></script>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
	var events = [];
	fetch('eventList.do')
	.then(result => result.json())
	.then(result => {
		console.log(result);
		result.forEach(item => {
			console.log(item);
			events.push({title: item.title , start: item.start, end: item.end})
		})
		
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
		      select: function(arg) {
		        var title = prompt('Event Title:');
		        if (title) {
		          calendar.addEvent({
		            title: title,
		            start: arg.start,
		            end: arg.end,
		            allDay: arg.allDay
		          })
		        }
		        calendar.unselect()
		        fetch('addEvent.do?title=' + title + '&start=' + arg.startStr + '&end=' + arg.endStr)
		          	.then(result=>result.json())
		          	.then(result=>{
		          		console.log(result);
		          		if(result.retCode == 'OK'){
		          			alert('일정이 등록되었습니다.');
		          		}

		          	})
		          	.catch(err=>console.error(err))
		      },
		      eventClick: function(arg) {
		        if (confirm('이 이벤트를 지우시겠습니까?')) {
		          arg.event.remove()
		          console.log(arg.event._def.title);
              let title = arg.event._def.title;
		          fetch('removeEvent.do?title=' + title)
              .then(result => result.json())
              .then(result => {
                  if(result.retCode == 'OK'){
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
	})
	.catch(err=>console.error(err));
	
    
	
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
