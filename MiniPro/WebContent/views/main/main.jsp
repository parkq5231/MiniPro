<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
<style>
.pagination {
  display: inline-block;
}
.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
  margin: 0 4px;
}
.pagination a.active {
  background-color: #4CAF50;
  color: white;
  border: 1px solid #4CAF50;
}
.pagination a:hover:not(.active) {background-color: #ddd;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function goPage(page){
	location.href="${pageContext.request.contextPath}/paging.do?pageNo="+page;	
}
</script>
</head>
<body>
	<div align="center">
		<jsp:include page="../common/menu.jsp" />
		<h1>처음 접속 시 나오는 페이지</h1>
		<!-- From CursorType.java -->
		<h3>${resultVo}</h3>
		<div id="show"></div>
		
		<c:forEach var="vo" items="${list }">
		<p>id:${vo.employeeId} / ${vo.firstName} / ${vo.lastName} / ${vo.salary }</p>
		</c:forEach>		
	</div>		
		<jsp:include page="../common/paging.jsp"/>
	
	<script>
	$.ajax({
		url: 'Json',
		dataType:'json',
		success:success,
		error:function(reject){
			console.log("오류...");
		}
	});
	function success(){
		let table = document.createElement('table');
		table.setAttribute('border','1')
		let tr = document.createElement('tr');
		let td = document.createElemente('td');
		table.setAttribute('border','1');
		for(let i=1;i<5;i++){
			td.innerHTML=i;
		}
		tr.append(td);
		table.append(tr);
		document.getElementById("show").append(table);
	}
	</script>
</body>
</html>