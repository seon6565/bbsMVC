<%@page import="java.util.List"%>
<%@page import="model2.bbs.BbsDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model2.bbs.BbsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bbs list</title>
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
<h2><a href="list">게시판 목록</a></h2>

<form name="frmSearch" id="frmSearch" method="get">
<table>
<tr>
	<td>
		<select name="search_category" id="search_category">
			<option value="" selected>선택</option>
			<option value="title" >제목</option>
			<option value="content" >글내용</option>
			<option value="user_id" >작성자</option>
		</select>
		
		&nbsp; &nbsp;
		<input type="text" name="search_word" id="search_word" value="" maxlength="20">
		&nbsp;
		<input type="button" name="btn_search" id="btn_search" value="검색">
		<%
		   if(session.getAttribute("UserId") == null){
			   out.print("<a href=./login> 로그인</a>");
			}
			else{
		         out.print("<a href=../logout> 로그아웃</a>");
			}
		%>
	</td>
</tr>
</table>
</form>

<table>
	<thead>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>조회수</td>
		<td>작성일</td>
		<td>다운로드</td>
	</tr>
	</thead>
	<tbody>
<c:set var="row" value="${params.total_count - (params.page_no-1)*params.page_size }" />
<c:choose>
	<c:when test="${not empty bbsList }">
		<c:forEach var="list" items="${bbsList }" varStatus="loop">
		<tr>
		<td>${row}</td>
		<td><a href="view?idx=${list.idx}">${list.title} </a></td>
		<td>${list.user_id}</td>
		<td>${list.read_cnt}</td>
		<td>${list.reg_date}</td>
		<td><a href="filelist?idx=${list.idx}">첨부파일리스트</a></td>
		</tr>
		${row = row-1;''}
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
		<td colspan="6">등록된 게시물이 없습니다.</td>
		</tr>
	</c:otherwise>
</c:choose>
	</tbody>
</table>
<form>

<table>
	<tr>
		<td>
		<% if(session.getAttribute("UserId")!= null){
		out.print("<input type=\"button\" name=\"btn_regist\" id=\"btn_regist\" value=\"글등록\">");
		}
		%>
		</td>
	</tr>
</table>

<table>
	<tr>
		<td>
			${params.paging }
		</td>
	</tr>
</table>
</form>
<script>
if(document.querySelector("#btn_regist")!=null){
document.querySelector("#btn_regist").addEventListener("click",()=>{
	window.location.href="./Registpage";
})
}
document.querySelector("#btn_search").addEventListener("click",()=>{
	let search_category = document.querySelector("#search_category");
	if(search_category.value.length<1 || search_category.value == "" || search_category == null){
		alert("검색 구분을 선택하세요.");
		return false;
	}
	let search_word = document.querySelector("#search_word");
	if(search_word.value.length<1 || search_word.value == "" || search_word == null){
		alert("검색어를 입력하세요.");
		return false;
	}
	let frmSearch = document.querySelector("#frmSearch");
	frmSearch.submit();

})
</script>
</body>
</html>