<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

#topMenu {
	height: 30px;
	width: 820px;
}

#topMenu ul li {
	list-style: none;
	color: white;
	background-color: #2d2d2d;
	float: left;
	line-height: 30px;
	vertical-align: middle;
	text-align: center;
}

#topMenu .menuLink {
	text-decoration: none;
	color: white;
	display: block;
	width: 150px;
	font-size: 12px;
	font-weight: bold;
	font-family: "Trebuchet MS";
}

#topMenu .menuLink:hover {
	color: red;
	background-color: #4d4d4d;
}
</style>

</head>
<body>
	<div align="center">
		<nav id="topMenu">
			<ul>
				<!-- 경로 모르겠으면 그냥 절대경로로 하세요 -->
				<li><a class="menuLink" href="main.do">Home</a></li>
				<li>|</li>
				<c:if test="${mid eq null }">
					<li><a class="menuLink" href="loginForm.do">Login</a></li>
					<li>|</li>
					<li><a class="menuLink" href="memberJoinForm.do">회원가입</a></li>
					<li>|</li>
				</c:if>
				<li><a class="menuLink" href="#">자유게시판</a></li>
				<li>|</li>
				<li><a class="menuLink" href="#">공지사항</a></li>
				<c:if test="${mid ne null }">
					<li>|</li>
					<li><a class="menuLink" href="logout.do">Logout</a></li>
				</c:if>

			</ul>
		</nav>
	</div>

</body>
</html>