<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 960px;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center">디렉티브(v-show="true/false")</h1>
		<div class=row>
			<button class="btn btn-lg btn-danger" @click="showClick()">보이기</button>
			<button class="btn btn-lg btn-success" @click="hideClick()">감추기</button>
			<div v-show="isShow">
				Hello Vue!!
				<img alt="" :src="'https://www.kobis.or.kr//common/mast/movie/2023/06/thumb/thn_a68cc44d22d84acf9d861d5c931a4254.jpg'" style="width:30px; hieght:30px;">
			</div>
		</div>
	</div>
	<script type="text/javascript">
		const myApp={
				data(){
					return{
						isShow:false
					}
				},
				methods:{
					showClick:function(){
						this.isShow=true
					},
					hideClick:function(){
						this.isShow=false;
					}
				}
		}
		Vue.createApp(myApp).mount('.container')
		//vue3으로 구현해본거임 문법이확 바뀌니 유의!! -> vuex도 공부
	</script>

</body>
</html>