<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />
	<!-- addForm.jsp -->
	<%
	String logId = (String) session.getAttribute("logId");
	%>
	<form action="addBoard.do" method="post" accept-charset="utf-8"> <!-- 주소 표시줄에 직접 치거나 링크 누름: get방식.  -->
		<table class="table">
			<tr>
				<th>글 제목</th>
				<td><input type="text" name="title" class="form-control"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=logId%><input type="hidden" name="writer" class="form-control" value="<%=logId%>"></td>
			</tr>
			<tr>
				<th>본문</th>
				<td><textarea name="content" rows="3" cols="40" class="form-control"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" class="btn btn-primary"> <!--  -->
					<input type="reset" class="btn btn-secondary"></td>
			</tr>
		</table>
	</form>
<jsp:include page="includes/footer.jsp" />