<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jsp에서 제공해주는 액션 태그 -->
<jsp:include page="includes/header.jsp" />
<!-- 저기에 저장한거를 가져오는???태그인거같음 -->
<%
BoardVO brd = (BoardVO) request.getAttribute("bselect");
int currPage = (int) request.getAttribute("page");
%>
<form action="modifyForm.do">
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
			<th>내용</th>
			<td colspan="3"><textarea class="form-control" name="content"
					rows="5" cols="40" readonly><%=brd.getContent()%></textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="submit" value="수정" 	class="btn btn-info">
			<button type="button" id="delBtn" class="btn btn-danger">삭제</button>
			</td>
		</tr>
	</table>
</form>
<p>
	<a href="boardList.do">목록으로</a>
</p>
<script>
let btn = document.querySelector('#delBtn');
btn.addEventListener('click', deleteFnc);

//삭제 함수
function deleteFnc(){
	//삭제 페이지로 이동하는 컨트롤 호출
	location.href = 'deleteForm.do?bno=<%=brd.getBoardNo()%>&page=<%=currPage%>';
}
</script>
<jsp:include page="includes/footer.jsp" />