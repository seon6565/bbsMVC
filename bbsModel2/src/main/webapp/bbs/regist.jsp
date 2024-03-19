<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model2.bbs.BbsDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model2.bbs.BbsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bbs detail</title>
<style>
	table, tr, td{
		border: 1px solid black;
	}
	table{
		width:90%;
	}
</style>
</head>
<body>
<h2>게시판 등록 페이지</h2>
<form name="frm_regist" id="frm_regist" action="regist" enctype="multipart/form-data" method="post">
	작성자<input type="text" name="user_id" id="user_id" value="${UserId}" readonly><br>
	제목<input type="text" name="title" id="title" value="" maxlength="100"><br>
	<textarea name="content" id="content" rows="10" cols="200" style="width: 200px; height:200px;"></textarea><br>
	<input type="file" name="file" id="file" multiple ><br>
	<input type="submit" name="btn_submit" id="btn_submit" value="글등록">
	<input type="reset" name="btn_cancel" id="btn_cancel" value="삭제">
	<input type="button" name="btn_list" id="btn_list" value="이전페이지로">
</form>

<script>
document.querySelector("#btn_list").addEventListener("click",()=>{
	window.location.href="list";
})
document.querySelector("#btn_submit").addEventListener("click",()=>{
	let a = document.getElementById("user_id");
	let b = document.getElementById("title");
	let c = document.getElementById("content");
	if(b.value ==""){
		alert("제목이 비어있습니다.");
		event.preventDefault();
	}
	if(c.value == ""){
		alert("내용이 비어있습니다.");
		event.preventDefault();
	}
})
document.querySelector("#btn_cancel").addEventListener("click",()=>{
	if(confirm("이전페이지로 돌아가시겠습니까?")){
		window.location.href="list";
	}
})
</script>
</body>
</html>