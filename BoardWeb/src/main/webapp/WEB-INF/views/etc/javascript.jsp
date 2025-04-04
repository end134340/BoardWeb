<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h3>javascript.jsp</h3>

<div class="row">
	<div class="col-sm-6">
		<input class="form-control" type="text" id="userInput">
	</div>
	<div class="col-sm-2">
		<button class="btn btn-primary" id="addBtn">추가</button>
	</div>
</div>

<ul id="list" style="display: none;">
	<li>apple</li>
	<li>banana</li>
</ul>
<!-- 성별 목록 -->
<select class="form-control" id="searchGender">
	<option value="all">전체</option>
	<option value="Male">남성</option>
	<option value="Female">여성</option>
</select>

<table class="table">
	<thead>
		<tr>
			<th><input type="checkbox"></th>
			<th>id</th>
			<th>first name</th>
			<th>last name</th>
			<th>gender</th>
			<th></th>
		</tr>
	</thead>
	<tbody id="target">
	</tbody>

</table>

<!-- <script src="js/object.js" ></script> -->
<!-- <script src="js/array.js"></script> -->
<!-- <script src="js/json.js"></script> -->
<script src="js/ajax.js"></script>