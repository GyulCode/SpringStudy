<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${bCheck==true }">
	<script type="text/javascript">
		location.href="list.do"
	</script>
</c:if>
<c:if test="${bCheck==false }">
	<script type="text/javascript">
		alert("비밀번호가 틀립니다")
		history.back();
	</script>
</c:if>