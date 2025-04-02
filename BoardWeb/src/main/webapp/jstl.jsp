<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%
	request.setAttribute("name", "Hong");
	List<String> list = new ArrayList<String>();
	list.add("Hello");
	list.add("Nice");
	list.add("Good");
	request.setAttribute("list", list);
	request.setAttribute("today", new Date());
	//java에서 담은 걸 바로 jstl로 사용 불가. setAttribute에 값을 담아줘야함
	%>

	<!-- set: 변수 선언 태그. c는 prefix에서 정한 접두사 core는 주로 c로 씀 
	     var: 변수명 value: 값 -->
	<c:set var="msg" value="Hello" />
	<!-- out: 변수에 있는 값을 가지고 와서 출력?하는? 태그. \$\{} <변수나 간단한 연산을 할 때 사용할 수 있는 표현식. 주석이나 ""안에 들어가도 실행이 됨. -->
	<p>
		<c:out value="${msg}" />
	</p>
	<p>
		<c:out value="${msg == 'Hello' ? '같음' : '다름'}" />
	</p>
	<p>
		<c:out value="${msg == 'Hello'}" />
	</p>
	<!-- ne: 같지 않음 eq: 같음 -->
	<p>
		<c:out value="${msg eq 'Hello'}" />
	</p>
	<p>
		<c:out value="${msg ne 'Hello'}" />
	</p>

	<!-- setAttribute로 담은 값은 다 읽어올 수 있음 -->
	<p>request.setAttribute로 값을 담은 name은 ${name}</p>
	<p>session.setAttribute로 값을 담은 로그인한 아이디는 ${logId}</p>

	<!-- 조건문 -->
	<c:if test="${msg == 'Hello'}">
		<c:out value="msg의 값이 Hello가 맞습니다." />
	</c:if>

	<c:set var="age" value="20" />
	<c:choose>
		<c:when test="${age >= 20 }">
			<p>성인입니다.</p>
		</c:when>
		<c:otherwise>
			<p>미성년자입니다.</p>
		</c:otherwise>
	</c:choose>
	
	<!-- 반복문 var:변수 begin: 시작값 end: 끝?값 step: 증가치 -->
	<c:forEach var="i" begin="1" end="10" step="1">
		<p> 2 X ${i} = ${ 2 * i } </p>
	</c:forEach>
	<!-- items: 값 -->
	<ul>
	<c:forEach var="str" items="${list}">
		<li><c:out value="${str}" /></li>		
	</c:forEach>
	</ul>
	
	<!-- fmt: format -->
	<fmt:formatDate value="${today}" pattern="yyyy-MM-dd HH:mm:ss"/>
	<br>
	<c:set var="cnt" value="100000" />
	<fmt:formatNumber value="${cnt}" pattern="##,###" />

</body>
</html>