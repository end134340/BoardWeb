<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />
	<!-- webapp/WEB-INF/views/boardList.jsp -->
	<%
	//request: jsp가 가지고 있는 내장 객체(요청 정보 값을 가지고 있는 객체)
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("blist");
	//컨트롤러에서 setAttribute에 지정한 이름으로 담은 값을 getAttribute로 가져옴.
	%>

	<h3>게시글 목록</h3>
	<table class="table">
		<thead>
			<th>글 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일시</th>
		</thead>
		<tbody>
			<%
			for (BoardVO board : list) {
			%>
			<tr>
				<td><%=board.getBoardNo()%></td>
				<td><a href='board.do?bno=<%=board.getBoardNo()%>'><%=board.getTitle()%></a></td>
				<td><%=board.getWriter()%></td>
				<td><%=board.getWriteDate()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
<jsp:include page="includes/footer.jsp" />