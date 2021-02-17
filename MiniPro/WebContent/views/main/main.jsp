<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
</head>
<body>
	<div align="center">
		<jsp:include page="../common/menu.jsp" />
		<h1>처음 접속 시 나오는 페이지</h1>
		<!-- From CursorType.java -->
		<h3>${vo}</h3>
	</div>
</body>
</html>