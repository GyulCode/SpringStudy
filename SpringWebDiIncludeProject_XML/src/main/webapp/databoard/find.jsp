<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<h3 class="text-center">검색 결과</h3>
		<c:if test="${count==0 }">
			<table class="table">
				<tr>
					<td class=text-left>
						<h3>검색된 결과가 없습니다.</h3>
					</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${count!=0 }">
			<table class="table">
				<tr>
					<th width=10% class="text-center">번호</th>
					<th width=45% class="text-center"> 제목</th>
					<th width=15% class="text-center">이름</th>
					<th width=20% class="text-center">작성일</th>
					<th width=10% class="text-center">조회수</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<th width=10% class="text-center">${vo.no }</th>
						<th width=45% class="text-center"><a href="../databoard/detail.do?no=${vo.no }">${vo.subject }</a></th>
						<th width=15% class="text-center">${vo.name }</th>
						<th width=20% class="text-center">${vo.dbday }</th>
						<th width=10% class="text-center">${vo.hit }</th>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<table>
			<tr>
				<td>
					<a href="../databoard/list.do" class="btn btn-sm btn-info">목록</a>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>