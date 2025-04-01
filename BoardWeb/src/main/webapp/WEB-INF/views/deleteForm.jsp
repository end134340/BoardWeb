<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />
<!-- 주소 표시줄에 직접 치거나 링크 누름: get방식.  -->
<%
BoardVO brd = (BoardVO) request.getAttribute("board");
int currPage = (int) request.getAttribute("page");
%>
<form action="deleteBoard.do" method="post" accept-charset="utf-8">
	<input type="hidden" name="bno" value="<%=brd.getBoardNo()%>">
	<input type="hidden" name="page" value="<%=currPage %>">
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
			<th>본문</th>
			<td colspan="3"><textarea name="content" rows="4" cols="40" class="form-control" readonly><%=brd.getContent()%></textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input type="submit" class="btn btn-info" value="삭제"> 
			</td>
		</tr>
	</table>
</form>
<p>
	<a href="boardList.do">목록으로</a>
</p>
<jsp:include page="includes/footer.jsp" />