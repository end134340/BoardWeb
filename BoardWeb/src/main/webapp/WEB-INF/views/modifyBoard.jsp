<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 주소 표시줄에 직접 치거나 링크 누름: get방식.  -->
<form action="modifyBoard.do" method="post" accept-charset="utf-8">
	<input type="hidden" name="bno" value="${brd.boardNo}">
	<input type="hidden" name="page" value="${page}">
	<table class="table">
		<tr>
			<th>글 번호</th>
			<td>${brd.boardNo}</td>
			<th>글 제목</th>
			<td><input type="text" name="title" class="form-control"
				value="${brd.title}"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${brd.writer}</td>
			<th>작성일시</th>
			<td><fmt:formatDate value="${brd.writeDate}" pattern="yyyy년 MM월 dd일"/> </td>
		</tr>
		<tr>
			<th>본문</th>
			<td colspan="3"><textarea name="content" rows="4" cols="40"
					class="form-control">${brd.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="submit"
				class="btn btn-info" value="저장"></td>
		</tr>
	</table>
</form>
<p>
	<a href="boardList.do">목록으로</a>
</p>