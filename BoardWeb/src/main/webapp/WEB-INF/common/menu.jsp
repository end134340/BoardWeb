<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="border-end bg-white" id="sidebar-wrapper">
    <div class="sidebar-heading border-bottom bg-light">Start Bootstrap</div>
    <div class="list-group list-group-flush">
       <a class="list-group-item list-group-item-action list-group-item-light p-3" href="boardList.do">글 목록</a> 
		<c:choose>
		<c:when test="${logId == null }">
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="loginForm.do">로그인</a> 
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="signForm.do">회원가입</a> 
		</c:when>
		<c:otherwise>
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="logout.do">로그아웃(${logId})</a>
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="addBoard.do">등록 화면</a> 
		</c:otherwise>
		</c:choose>
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="javascript.do">JS연습</a>
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="eventForm.do">달력</a>
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="chart.do">chart</a>
    </div>
</div>