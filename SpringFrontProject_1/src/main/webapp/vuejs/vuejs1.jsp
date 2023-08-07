<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
margin-top: 50px;
width:960px;
}
</style>
<!-- <script type="text/javascript">
/* 일반적 Jquery */
$(function(){
	$('#msg').keyup(function(){
		let m=$('#msg').val();
		$('#print').text(m);
	})
})
</script> -->
</head>
<body>
<%--
1. VueJS의 생명주기 -> vue3(React), vuex(MVC)
beforeCreate
created
--------------Vue객세 생성
beforeMount
mounted
--------------메모리에 저장
beforeUpdate
updated
--------------수정
beforeDestroy
destroy
--------------메모리 해제(면접 : 가상돔 빈출)
가상돔 : 게임(더블버퍼링?) -> 메모리를 두개 생성(가상메모리 -> 실제 메모리)
String -> 메모리와연결, StringBuffer
2. 디렉티브
	-> 제어문
		v-for : 반복문 / 태그 <div v-for="">
		v-if/ v-else
		v-show
	-> 이벤트
		v-on:click -> <button v-on:click="aaa()">
		v-on:change
	-> 양방향 통신(Vue)
		AngularJS -> GO(제이스 고슬링), C#, Java
	-> 단방향 통신(React)
3. 컴포넌트 : 재사용
	Vue.component('modal',template:'<html>...')
4. 데이터를 읽어서 출력(클라이언트 <=> 서버 통신) -> axios
	axios.get('url',{params:{데이터 전송}}.then(){결과값읽기}
	일반문자열 / JSON -> RESTController
	id나 class를 사용하지 않는다 ref=""
5. 라우터 (화면 변경) -> Spring에서 화면 변경
	-> 검색, 로그인 처리, 
6. Vue 생성
	<script>
	-> 여러개 사용
	new Vue({
		el:'.container' -> 제어영역 -> class=".container" id="#row"
		data:{
			-> 멤버변수 (Vue에서 제어하는 변수)
			a:[] : 스프링에서 전송 -> Arraylist(JSONArray)
			b:{} : VO (JSONObject)
			c:true / boolean
			d:'' / string
			e:0 / 정수실수
		}
		생명주기 함수 이용
		mounted:function(){}
		updated:function(){}
		methods:{
			aaa:function(){}
		}
		filters:변환(날짜, 숫자) fliter..? filter..?
		-----------complete
	})
	</script>
	
	데이터 출력
	{{data에 설정된 변수 사용}}
	한번에 데이터 출력{{$data}}
	-> <img src="{{poster}}"> -> 오류임
	-> <img :src="poster"> -> : 붙여줘야함
	-> : -> <input type="text" :value="id"> / v-bind 생략가능 -> 사용법 : v-bind: 
 --%>
 
 <!-- 양방향 통신 : 입력값을 받아서 출력 -->
 <div class="container">
 	<div class="row">
 		입력 : <input type="text" v-model="msg" size=30 class="input-smd">
 		<div id="print"> <!-- msg에 저장된 내용 출력 -->
 		{{msg}}
 		</div>
 	</div>
 </div>
 
 <script type="text/javascript">
 new Vue({
	 el:'.container',
	 data:{
		 msg:''
	 }
 })
 </script>

</body>
</html>