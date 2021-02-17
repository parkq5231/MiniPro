<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../common/menu.jsp" />
<body>
	<h1>로그인 실패</h1>
	<h3>${param.mId }님 존재하지 않거나, 패스워드가 틀렸습니다.</h3>
</body>
</html>