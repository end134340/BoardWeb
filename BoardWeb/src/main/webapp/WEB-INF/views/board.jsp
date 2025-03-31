<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jsp에서 제공해주는 액션 태그 -->
<jsp:include page="includes/header.jsp" />
<!-- 저기에 저장한거를 가져오는???태그인거같음 -->
<%
BoardVO brd = (BoardVO) request.getAttribute("bselect");
%>
<table class="table">
	<tr>
		<th>글 번호</th>
		<td><%=brd.getBoardNo()%></td>
		<th>글 제목</th>
		<td><%=brd.getTitle()%></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=brd.getWriter()%></td>
		<th>작성일시</th>
		<td><%=brd.getWriteDate()%></td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="3"><%=brd.getContent()%></td>
	</tr>
</table>
<p>
	<a href="boardList.do">목록으로</a>
</p>
<jsp:include page="includes/footer.jsp" />