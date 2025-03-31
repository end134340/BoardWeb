<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.yedam.mapper.BoardMapper"%>
<%@page import="com.yedam.common.DataSource"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- webapp/view/test.jsp -->
	<!-- jsp: 웹 페이지?안에서 java코드를 사용 -->
	<!-- jsp에서 자바 코드?를 임포트하기 위해서는 임포트할 코드 위에서 ctrl+space하면 됨. -->
	<%
	//java영역
	String msg = "Hello, World.";
	System.out.println(msg);
	int age = 30;

	//java영역이기 때문에 여기서도 이 코드를 쓸 수 있음.
	SqlSession sqlSession = DataSource.getInstance().openSession();
	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
	List<BoardVO> list = mapper.selectBoard();
	%>
	<h3>글 목록</h3>
	<ul>
		<%
		for (BoardVO board : list) {
		%>
		<li>글 번호(<%=board.getBoardNo()%>) / 글 제목(<%=board.getTitle()%>)
		</li>
		<%
		}
		%>
	</ul>




	<p>저장 정보</p>
	<p>
		age에 저장된 값:
		<%=age%></p>
	<%
	if (age > 20) {
	%>
	<p>성인입니다.</p>
	<%
	} else {
	%>
	<p>미성년입니다.</p>
	<%
	}
	%>
</body>
</html>