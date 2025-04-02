<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />
<!-- webapp/WEB-INF/views/boardList.jsp -->
<%
//request: jsp가 가지고 있는 내장 객체(요청 정보 값을 가지고 있는 객체)
/* List<BoardVO> list = (List<BoardVO>) request.getAttribute("blist");
PageDTO pageDTO = (PageDTO) request.getAttribute("paging");
SearchDTO search = (SearchDTO) request.getAttribute("search"); */
//컨트롤러에서 setAttribute에 지정한 이름으로 담은 값을 getAttribute로 가져옴.
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h3>게시글 목록</h3>

<!-- 검색 조건 -->
<form action="boardList.do">
	<div class="row">
		<div class="col-sm-2">
			<select name="searchCondition" class="form-control" id="searchC">
				<option>선택하세요</option>
				<option value="T">제목</option>
				<option value="W">작성자</option>
				<option value="TW">제목&작성자</option>
			</select>
		</div>
		<div class="col-sm-4">
			<input type="text" id="keyword" name="keyword" class="form-control">
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

		<c:forEach var="board" items="${blist}">
			<tr>
				<td>${board.boardNo}</td>
				<td><a
					href='board.do?bno=${board.boardNo}&page=${paging.currentPage}'>${board.title}</a></td>
				<td>${board.writer }</td>
				<td><fmt:formatDate value="${board.writeDate}"
						pattern="yyyy년 MM월 dd일" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 페이징 처리 -->
<nav aria-label="...">
	<ul class="pagination">
		<!-- 이전 10개 페이지가 있는지 여부 -->
		<c:choose>
			<c:when test="${paging.prev}">
				<li class="page-item"><a class="page-link"
					href="boardList.do?page=${paging.startPage - 1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><span class="page-link">Previous</span></li>
			</c:otherwise>
		</c:choose>


		<c:forEach var="p" begin="${paging.startPage}" end="${paging.endPage}"
			step="1">
			<c:choose>
				<c:when test="${paging.currentPage == p }">
					<li class="page-item active" aria-current="page"><span class="page-link">${p}</span></li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a class="page-link" href="boardList.do?page=${p}&searchCondition=${search.searchCondition}&keyword=${search.keyword}">${p}</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 이후 10개 페이지가 있는지 여부 -->
		<c:choose>
			<c:when test="${paging.next}">
				<li class="page-item"><a class="page-link" href="boardList.do?page=${paging.endPage + 1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><span class="page-link">Next</span></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>
<script>
	let sc = '${search.searchCondition}'';
	let kw = '${search.keyword};
	
	if(sc != null){
		console.log(sc);
		console.log(kw);
		let scval = document.querySelector('#searchC').options;
		for(let i = 0; i <= scval.length; i++){
			if(scval[i].value == sc){
				scval[i].selected = true;
			}
		}
	}
	
	if(kw != null){
		document.querySelector('#keyword').value = kw;
	}
</script>
<jsp:include page="includes/footer.jsp" />