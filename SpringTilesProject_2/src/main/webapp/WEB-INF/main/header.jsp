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
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">Tiles & Session</a></h1>
    </div>
    <div class="fl_right">
    <c:if test="${sessionScope.id==null }">
      <ul class="inline">
        <li> ID: <input type=text name="id" size=10 ref="login_id" v-model="login_id"></li>
        <li> PWD: <input type=password name="pwd" size=10 ref="login_pwd" v-model="login_pwd"></li>
        <li>
	        <input type=button value="로그인" @click="login()">
        </li>
      </ul>
     </c:if>
     <c:if test="${sessionScope.id!=null }">
      <ul class="inline" v-if="sessionId!=null">
        <li> {{sessionId}}님 안녕하세요</li>
        <li> <input type=button value="로그아웃" class="btn-sm" @click="logout()"></li>
        <li>
	        <input type=button value="로그인" @click="login()">
        </li>
      </ul>
      </c:if>
    </div>
  </header>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <ul class="clear">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li><a class="drop" href="#">회원</a>
        <ul>
          <li><a href="../member/join.do">회원가입</a></li>
          <li><a href="pages/full-width.html">아이디찾기</a></li>
          <li><a href="pages/sidebar-left.html">비밀번호찾기</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">Mat.ZIP</a>
        <ul>
          <li><a href="../food/recommand.do">MatZip recommand</a></li>
          <li><a href="../food/food_find.do">MatZip Search</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">서울</a>
        <ul>
          <li><a href="../seoul/list.do">명소</a></li>
          <li><a href="../seoul/list.do?no=2">자연</a></li>
          <li><a href="../seoul/list.do?no=3">쇼핑</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">스토어</a>
        <ul>
          <li><a href="pages/gallery.html">상품</a></li>
          <li><a href="pages/full-width.html">장바구니</a></li>
        </ul>
      </li>
      <li><a href="#">커뮤니티</a></li>
      <li><a href="#">마이페이지</a></li>
      <li><a href="../movie/movie_rank.do">영화순위</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </nav>
</div>
<script>
	new Vue({
		el:'.header',
		data:{//v-model과 이름을 맞춰줘야함
			login_id:'',
			login_pwd:'',
			/* sessionId:null */
			
		},
		methods:{
			login:function(){
				if(this.login_id===''){
					this.$refs.login_id.focus();
					return;
				}
				else if(this.login_pwd===''){
					alert('비밀번호르 입력하세요.')
					this.$refs.login_pwd.focus();
					return;
				}
				else{
					axios.post('http://localhost/web/member/login_ok_vue.do',null,{
						params:{
							id:this.login_id,
							pwd:this.login_pwd
						}
					}).then(res=>{
						if(res.data==='NOID'){
							alert('아이디가 존재하지 않습니다.')
							this.login_id=''
							this.login_pwd=''
							this.$refs.login_id.focus()
							
						}else if(res.data==='NOPWD'){
							alert('비밀 번호가 일치하지 않스빈다.')
							this.login_pwd='';
							this.$refs.login_pwd.focus()
						}else{
							/* this.sessionId=res.data */
							location.href="../main/main.do";
						}
					})
				}
			}
		}
	})
</script>

</body>
</html>