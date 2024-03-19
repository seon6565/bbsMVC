<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>로그인</h2>
<form name="frmLogin" id="frmLogin" action="./login" method="post">
아이디: <input type="text" name="id" id="id" value="" maxlength="20" /> <br>
비밀번호: <input type="text" name="pwd" id="pwd" value="" maxlength="20" /> <br>
<input type="submit" value="로그인">
<input type="button" value="이전페이지로이동" onClick=window.location.href="../bbs/list">
</form>

</body>
</html>