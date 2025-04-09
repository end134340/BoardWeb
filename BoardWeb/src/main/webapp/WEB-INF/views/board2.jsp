<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jsp에서 제공해주는 액션 태그 -->
<link rel="stylesheet" href="https://cdn.datatables.net/2.2.2/css/dataTables.dataTables.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.2/moment.min.js"></script>
<script src="https://cdn.datatables.net/2.2.2/js/dataTables.js"></script>
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
<p><input type="text" id="reply" class="col-sm-7"><button id="addRow" class="btn btn-light">등록</button></p>
<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>No.</th>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일시</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>No.</th>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일시</th>
            </tr>
        </tfoot>
    </table>
    <p><button id="button" class="btn btn-danger">삭제</button></p>
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