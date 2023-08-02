<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
  margin: 0px auto;
  width:450px;
}
</style>
</head>
<body>
	<div class="container">
	<h1 style="text-center">사원 정보</h1>
		<div class="row">
			<table class="table">
				<tr style="text-strenght:300px">
				 	<td width=20%>사번</td>
				 	<td width=20%>이름</td>
				 	<td width=20%>직위</td>
				 	<td width=20%>입사일</td>
				 	<td width=20%>급여</td>
				</tr>
				<c:forEach var="name" items="${list }">
				<tr>
					<td class="text-center">${empno}</td>
					<td class="text-center">${ename }</td>
					<td class="text-center">${job }</td>
					<td class="text-center">${dbday }</td>
					<td class="text-center">${sal }</td>
				</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>