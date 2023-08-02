<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class=row>
		<h1 class="text-center">자료실</h1>
		<table class="table">
			<tr>
				<td> <a href="../databoard/insert.do" class="btn btn-sm btn-warning">새글</a>
			</tr>
		</table>
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
		<table class="table">
			<tr>
				<td class="text-left">
				<form action="../databoard/find.do" method="post">
					search:<select name=fs class="input-sm">
						<option value="name">이름</option>
						<option value="subject">제목</option>
						<option value="content">내용</option>
					</select>
					<input type=text name=ss class=input-sm size=15>
					<button class="btn btn-sm btn-success">검색</button>
				</form>
				</td>
				<td class="text-right">
				<a href="#" class="btn btn-sm btn-danger">이전</a>
					${curpage } page/ ${totalpage } pages
				<a href="#" class="btn btn-sm btn-primary">다음</a>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>