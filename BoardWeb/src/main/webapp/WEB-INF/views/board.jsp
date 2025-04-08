<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jsp에서 제공해주는 액션 태그 -->
<%-- <jsp:include page="includes/header.jsp" /> --%>

<form action="modifyForm.do">
	<input type="hidden" name="bno" value="${brd.boardNo}"> 
	<input type="hidden" name="page" value="${page}">
	<table class="table">
		<tr>
			<th>글 번호</th>
			<td>${brd.boardNo}</td>
			<th>글 제목</th>
			<td><c:out value="${brd.title}" /></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${brd.writer }</td>
			<th>작성일시</th>
			<td><fmt:formatDate value="${brd.writeDate}"
					pattern="yyyy년 MM월 dd일" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3"><textarea class="form-control" name="content"
					rows="5" cols="40" readonly>${brd.content }</textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="submit" value="수정"
				class="btn btn-info"> <c:choose>
					<c:when test="${logId  != null && logId == brd.writer}">
						<button type="button" id="delBtn" class="btn btn-danger">삭제</button>
					</c:when>
					<c:otherwise>
						<button type="button" id="delBtn" class="btn btn-danger" disabled>삭제</button>
					</c:otherwise>
				</c:choose></td>
		</tr>
		<c:if test="${ msg != null }"> 
		<tr>
			<td style="color: red;" align="center" colspan="4">${msg }</td>
		</tr>
		</c:if>
	</table>
</form>
<!-- 댓글 관련 -->
<style>
div.reply span{
	display: inline-block;
}
div.reply ul {
	list-style: none;
}
div.content ul li{
	margin-bottom: 5px;
}
</style>
<div class="container reply">
	<!-- 등록 -->
	<div class="header">
		<input class="col-sm-8" id="reply">
		<button class="col-sm-2 btn btn-primary" id="addReply">등록</button>
	</div>
	<!-- 목록 -->
	<div class="content">
		<ul>
		 <li>
		 	<span class="col-sm-2">no.</span>
		 	<span class="col-sm-5">내용</span>
		 	<span class="col-sm-2">작성자</span>
		 	<span class="col-sm-2"></span>
		 	
		 </li>
		</ul>
	</div>
		
	<!-- 페이징 -->
	<nav aria-label="...">
  <ul class="pagination pagination-sm justify-content-center">
   <!--  <li class="page-item disabled">
      <span class="page-link">Previous</span>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item active" aria-current="page">
      <span class="page-link">2</span>
    </li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" href="#">Next</a>
    </li> -->
  </ul>
</nav>
</div>
<!-- 댓글관련 끝 -->
<script>
const bno = "${brd.boardNo}";
const replyer = "${logId}";
let btn = document.querySelector('#delBtn');
btn.addEventListener('click', deleteFnc);

//삭제 함수
function deleteFnc(){
	//삭제 페이지로 이동하는 컨트롤 호출
	location.href = 'deleteForm.do?bno=${brd.boardNo}&page=${page}';
	}
</script>
<script src="js/board2.js"></script>
<!-- <script src="js/boardService.js"></script> -->
<!-- <script src="js/board.js"></script> -->
<!-- <script src="js/board1.js"></script> -->

<%-- <jsp:include page="includes/footer.jsp" /> --%>