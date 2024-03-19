<%@page import="java.net.URLEncoder"%>
<%@page import="fileupload.FileDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fileupload.FileDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
	if(session.getAttribute("UserId") == null){
		response.sendRedirect("./login");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 목록</title>
</head>
<body>
<h2>파일 목록 보기</h2>

<table border="1">
	<tr>
		<th>NO</th>
		<th>글번호</th>
		<th>제목</th>
		<th>카테고리</th>
		<th>원본 파일</th>
		<th>저장 파일</th>
		<th>작성일</th>
		<th>파일 다운로드</th>
	</tr>
	<c:choose>
	<c:when test="${not empty fileLists }">
		<c:forEach var="list" items="${fileLists }" varStatus="loop">
		<tr>
			<td>${list.getIdx()}</td>
			<td>${list.getBbs_idx()}</td>
			<td>${list.getTitle()}</td>
			<td>${list.getCategory()}</td>
			<td>${list.getOrgFile()}</td>
			<td>${list.getSaveFile()}</td>
			<td>${list.getReg_date()}</td>
			<td><a href="./download?orgFile=${list.getOrgFile()}&saveFile=${list.getSaveFile()}&bbs_idx=${list.getBbs_idx()}">다운로드</a></td>
		</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
		<td colspan="8">등록된 게시물이 없습니다.</td>
		</tr>
	</c:otherwise>
</c:choose>
</table>

</body>
</html>