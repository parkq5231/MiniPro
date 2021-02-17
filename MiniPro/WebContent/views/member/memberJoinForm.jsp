<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 Form</title>

<style>

	#mId, #mPassword, #mPasswordc, #mName {
		cursor: pointer;
	}

	#id, #password, #passwordc, #name {
		background-color: rgb(255, 255, 153);
	}
</style>

<script>
	function formCheck(){
		
		if(frm.mPassword.value==""){
			alert("비밀번호을 반드시 입력해야 합니다.");
			frm.mPassword.focus();
			return false;
		}
		
		// 패스워드 합/불 여부 확인
		if(frm.mPassword.value != frm.mPasswordc.value){
			alert("패스워드가 일치하지 않습니다.");
			frm.mPassword.value = null;
			frm.mPasswordc.value = null;
			frm.mPassword.focus();
			return false;
		}
		
		// 이름 입력 여부 확인
		if(frm.mName.value==""){
			alert("이름을 반드시 입력해야 합니다.");
			frm.mName.focus();
			return false;
		}
		
		return true;
	}
	
	function idCheck(str){
		var url = "idCheck.do?mid="+str;
		if(str == ""){
			alert("아이디를 입력하세요.");
			frm.mId.focus();
		}else{
		window.open(url, "아이디중복체크","width=600, top=400, left=100, right=100");
		}
	}
</script>

</head>
<body>
	<jsp:include page="../common/menu.jsp" />
	<div align="center">
		<h1>회 원 가 입</h1>
		<form id="frm" name="frm" onsubmit="return formCheck()" action="memberJoin.do" method="post">
			<div>
				<table border="1" id="table">
					<tr>
						<th width="100" id="id">아이디</th>
						<td><input type="text" id="mId" name="mId">
							<button type="button" onclick="idCheck(mId.value)" required="required">중복체크</button></td>
					</tr>
					<tr>
						<th width="100" id="password">Password</th>
						<td><input type="password" id="mPassword" name="mPassword"></td>
					</tr>
					<tr>
						<th width="150" id="passwordc">Password 확인</th>
						<td><input type="password" id="mPasswordc" name="mPasswordc" required="required"></td>
					</tr>
					<tr>
						<th width="100" id="name">이름</th>
						<td><input type="text" id="mName" name="mName" required="required"></td>
					</tr>
				</table>
				<p />
				<button type="submit">회원가입</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset">취소</button>
			</div>
		</form>
	</div>
</body>
</html>