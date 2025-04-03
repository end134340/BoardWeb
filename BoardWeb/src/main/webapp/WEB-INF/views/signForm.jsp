<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h3>회원 가입</h3>
<!-- application/x-www-form-urlencoded: key:value 방식으로 전달(미지정시 자동으로 지정) -->
<!--multipart/form-data: 파일도 업로드 가능하고 텍스트도 전달 가능함-->
<form action="signup.do" method="post" enctype="multipart/form-data">
	<table class="table">
		<tr>
			<th>아이디</th>
			<td><input class="form-control" type="text" name="userId"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input class="form-control" type="password" name="userPw"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input class="form-control" type="text" name="userName"></td>
		</tr>
        <tr>
            <th>이미지</th>
            <td><input class="form-control" type="file" name="userImg"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input class="btn btn-primary" type="submit" value="회원가입">
                <input class="btn btn-danger" type="reset" value="초기화">
            </td>
        </tr>
	</table>
</form>
