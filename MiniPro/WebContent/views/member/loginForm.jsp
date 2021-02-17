<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>
	<jsp:include page="../common/menu.jsp" />
	<div align="center">
		<div><h1>Login</h1></div>
		<form id="frm" name="frm" action="login.do" method="post">
			<div>

				<table border="1">
					<tr>
						<th width="100">아이디</th>
						<td><input type="text" id="mId" name="mId"></td>
					</tr>

					<tr>
						<th width="100">비밀번호</th>
						<td><input type="password" id="mPassword" name="mPassword"></td>
					</tr>
				</table>

				<p />

				<div align="center">
					<button type="submit">로그인</button>
					&nbsp;&nbsp;&nbsp;
					<button type="reset">취소</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>