<%@page import="com.yedam.common.SearchDTO"%>
<%@page import="com.yedam.common.PageDTO"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />
<!-- webapp/WEB-INF/views/boardList.jsp -->
<%
//request: jsp가 가지고 있는 내장 객체(요청 정보 값을 가지고 있는 객체)
List<BoardVO> list = (List<BoardVO>) request.getAttribute("blist");
PageDTO pageDTO = (PageDTO) request.getAttribute("paging");
SearchDTO search = (SearchDTO) request.getAttribute("search");
//컨트롤러에서 setAttribute에 지정한 이름으로 담은 값을 getAttribute로 가져옴.
%>
<p><%=pageDTO%></p>
<h3>게시글 목록</h3>

<!-- 검색 조건 -->
<form action="boardList.do">
	<div class="row">
		<div class="col-sm-2">
			<select name="searchCondition" class="form-control">
				<option>선택하세요</option>
				<option value="T">제목</option>
				<option value="W">작성자</option>
				<option value="TW">제목&작성자</option>
			</select>
		</div>
		<div class="col-sm-4">
			<input type="text" name="keyword" class="form-control">
		</div>
		<div class="col-sm-2">
			<button type="submit" class="btn btn-info">검색</button>
		</div>
	</div>
</form>

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
			<td><a
				href='board.do?bno=<%=board.getBoardNo()%>&page=<%=pageDTO.getCurrentPage()%>'><%=board.getTitle()%></a></td>
			<td><%=board.getWriter()%></td>
			<td><%=board.getWriteDate()%></td>
		</tr>
		<%
		}
		%>
	</tbody>
</table>
<!-- 페이징 처리 -->
<nav aria-label="...">
	<ul class="pagination">
		<!-- 이전 10개 페이지가 있는지 여부 -->
		<%
		if (pageDTO.isPrev()) {
		%>
		<li class="page-item"><a class="page-link" href="boardList.do?page=<%=pageDTO.getStartPage() - 1%>">Previous</a></li>
		<%
		} else {
		%>
		<li class="page-item disabled"><span class="page-link">Previous</span>
		</li>
		<%
		}
		%>
		<%
		for (int p = pageDTO.getStartPage(); p <= pageDTO.getEndPage(); p++) {
		%>
		<%
		if (pageDTO.getCurrentPage() == p) {
		%>
		<li class="page-item active" aria-current="page"><span
			class="page-link"><%=p%></span></li>
		<%
		} else {
		%>
		<li class="page-item"><a class="page-link"
			href="boardList.do?page=<%=p%>&searchCondition=<%=search.getSearchCondition()%>
		&keyword=<%=search.getKeyword()%>"><%=p%></a></li>

		<%
		}
		}
		%>

		<!-- 이후 10개 페이지가 있는지 여부 -->
		<%
		if (pageDTO.isNext()) {
		%>
		<li class="page-item"><a class="page-link"
			href="boardList.do?page=<%=pageDTO.getEndPage() + 1%>">Next</a></li>
		<%
		} else {
		%>
		<li class="page-item disabled"><span class="page-link">Next</span>
		</li>
		<%
		}
		%>
	</ul>
</nav>
<jsp:include page="includes/footer.jsp" />