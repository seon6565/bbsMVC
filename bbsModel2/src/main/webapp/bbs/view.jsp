<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model2.bbs.BbsDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model2.bbs.BbsDAO"%>
<%@page import="common.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>

<%
	if(session.getAttribute("UserId") == null){
		response.sendRedirect("./login");
	}
%>
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
<h2>게시판 목록</h2>

<table>
	<tr>
		<td>번호 : ${ idx }</td>
		<td>작성자 : ${ user_id }</td>
		<td>조회수 :${ read_cnt } </td>
		<td>작성일 : ${ reg_date }</td>
	</tr>
		<tr>
		<td colspan="4">${ title }</td>
	</tr>
		<tr>
		<td colspan="4">${ content }</td>
	</tr>
</table>
<table>
	<tr>
	<td>
	<input type="submit" name="btn_submit" id="btn_submit" value="글등록">
	<% if(session.getAttribute("UserId")!= null && session.getAttribute("UserId").toString().equals(request.getAttribute("user_id"))){
		out.print("<input type=\"button\" name='btn_modify' id='btn_modify' value='글수정'>");
	}
	%>
	<%
	if(session.getAttribute("UserId")!= null && session.getAttribute("UserId").toString().equals(request.getAttribute("user_id"))){
		out.print("<input type='button' name='btn_delete' id='btn_delete' value='글삭제'>");
	}
	%>
	<input type="button" name="btn_list" id="btn_list" value="이전페이지로">
	</td>
	</tr>
</table>
<script>
document.querySelector("#btn_submit").addEventListener("click",()=>{
	window.location.href="Registpage";
})
if(document.querySelector("#btn_modify")!=null){
document.querySelector("#btn_modify").addEventListener("click",()=>{
	window.location.href="Modifypage?idx=${idx}";
})
}
if(document.querySelector("#btn_delete")!=null){
document.querySelector("#btn_delete").addEventListener("click",()=>{
	if(confirm("정말로 삭제하시겠습니까?")){
	window.location.href="delete?idx=${idx}";
	}
})
}
document.querySelector("#btn_list").addEventListener("click",()=>{
	window.location.href="list";
})
</script>
</body>
</html>