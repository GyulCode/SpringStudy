<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
  margin: 0px auto;
  width:800px;
}
</style>
</head>
<body>
	<div class="wrapper row3">
  		<main class="container clear">
	  		<div class=row>
	  			<h2>내용 상세보기</h2>
	  			 <table class="table">
        <tr>
          <th width=20% class="text-center danger">번호</th>
          <td width=30% class="text-center">${vo.no }</td>
          <th width=20% class="text-center danger">작성일</th>
          <td width=30% class="text-center">${vo.dbday}</td>
        </tr>
        <tr>
          <th width=20% class="text-center danger">이름</th>
          <td width=30% class="text-center">${vo.name }</td>
          <th width=20% class="text-center danger">조회수</th>
          <td width=30% class="text-center">${vo.hit }</td>
        </tr>
        <tr>
          <th width=20% class="text-center danger">제목</th>
          <td colspan="3">${vo.subject }</td>
        </tr>
        <tr>
          <td colspan="4" class="text-left" valign="top" height="200">
           <pre style="white-space: pre-wrap;border: none;background-color: white">${vo.content }</pre>
          </td>
        </tr>
        <tr>
          <td colspan="4" class="text-right">
            <a href="../board/reply.do?pno=${vo.no }" class="btn btn-xs btn-warning">답변</a>
            <a href="../board/update.do?no=${vo.no }" class="btn btn-xs btn-info">수정</a>
            <a href="../board/delete.do?no=${vo.no }" class="btn btn-xs btn-success">삭제</a>
            <a href="../board/list.do" class="btn btn-xs btn-danger">목록</a>
          </td>
        </tr>
      </table>
	  		</div>
  		</main>
	</div>

</body>
</html>