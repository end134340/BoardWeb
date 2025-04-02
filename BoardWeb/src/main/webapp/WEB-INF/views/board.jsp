<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jsp에서 제공해주는 액션 태그 -->
<jsp:include page="includes/header.jsp" />

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
<p>
	<a href="boardList.do">목록으로</a>
</p>
<script>
let btn = document.querySelector('#delBtn');
btn.addEventListener('click', deleteFnc);

//삭제 함수
function deleteFnc(){
	//삭제 페이지로 이동하는 컨트롤 호출
	location.href = 'deleteForm.do?bno=${brd.boardNo}&page=${page}';
	}
</script>
<jsp:include page="includes/footer.jsp" />