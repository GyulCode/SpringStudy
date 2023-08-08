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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
   margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 100%;
}
</style>
</head>
<body>
  <div class="container-fluid">
    <div class="row">
      <input type=text ref="fd" size=30 class="input-sm" v-model="fd">
      <input type=button class="btn btn-sm btn-primary" value="검색" v-on:click="findData()">
    </div>
    <div style="height:20px"></div>
    <div class="row">
      <div class="col-md-3" v-for="vo in food_list">
	      <div class="thumbnail">
	        <a href="#">
	          <img :src="vo.poster" alt="Lights" style="width:100%">
	          <div class="caption">
	            <p style="font-size:9px">{{vo.name}}&nbsp;<span style="color:orange">{{vo.score}}</span></p>
	          </div>
	        </a>
	      </div>
	    </div>
    </div>
    <div style="height:10px"></div>
    <div class="row">
     <%-- class="active"  --%>
      <div class="text-center">
        <ul class="pagination">
		  <li v-if="startPage>1"><a href="#">&lt;</a></li>
		  <li v-for="i in range(startPage,endPage)" :class="i==curpage?'active':''"><a href="#" v-on:click="selectPage(i)">{{i}}</a></li>
		  <li v-if="endPage<totalpage"><a href="#">&gt;</a></li>
	    </ul>
      </div>
    </div>  
	
	<script>
     new Vue({
    	el:'.container-fluid',
    	// react: state
    	// React VueJS 차이는?
        // React는 라이브러리 (완제품-수정불가능), VueJS는 JavaScript의 프레임워크(레고-수정 및 재사용 가능, 재조립 가능)입니다.
    	// data는 멤버변수 => 화면이동 전까지 유지
        data:{
    		fd:'마포',
    		food_list:[],
    		curpage:1,
    		totalpage:0,
    		startPage:0,
    		endPage:0
    	},
    	// 시작과 동시에 데이터를 가져와야 출력을 한다, 그러려면?
    	// Callback => 시스템에 의해 자동으로 호출되는 함수 => 생명주기
    	// window.onload => $(function(){}) => componentDidMount => useEffect()
    	mounted:function(){
    		// 스프링 서버를 연결해서 => 필요한 데이터를 전송 & 결과값을 동시에 받아온다
    		// 자바 스크립트에서는 리스트를 보낼 수 없다 instead Array[]
    		this.send()
    	},
    	// 멤버 method == 사용자 정의 함수
    	methods:{
    		send:function(){
    			axios.get("http://localhost/web/food/find_vue.do",{
        			params:{
        				page:this.curpage,
        				fd:this.fd
        			}
        		}).then(response=>{
        			console.log(response.data)
        			this.food_list=response.data
        			this.curpage=response.data[0].curpage;
        			this.totalpage=response.data[0].totalpage;
        			this.startPage=response.data[0].startPage;
        			this.endPage=response.data[0].endPage;
        			
        		})
    		},
    		findData:function(){
    			this.curpage=1;
    			this.send();
    		},
    		range:function(start,end){
    			let arr=[];
    			let length=end-start;
    			for(let i=0;i<=length;i++)
   				{
   					arr[i]=start;
   					start++;
   				}
    			return arr;
    		},
    		selectPage:function(page){
    			this.curpage=page;
    			this.send();
    		},
    		prev:function(){
    			this.curpage=this.startpage-1;
    			this.send();
    		},
    		next:function(){
    			this.curpage=this.endpage+1;
    			this.send();
    		}
    	}
     })
    </script>
	

</body>
</html>