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
	<div align="center">
		<h1>${vo.mName }님 ${vo.mId }로 회원가입 완료되었습니다.</h1><br/>
		<p/>
		<button type="button" onclick="location.href='login.do'">로그인</button>
	</div>
</body>
</html>