<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css"/>

<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>


<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <table class="table">
    	<tr>
    		<td width=30% class="text-center" rowspan=2></td>
    		<td width=70% ></td>
    	</tr>
    	<tr>
    		<td width=70% ></td>
    	</tr>
    </table>
    <div class="content"> 
      <div id="gallery">
        <figure>
          <header class="heading">쉐프 목록</span> </header>
          <ul class="nospace clear">
            <li v-for="vo, index in chef_list" :class="index%4==0?'one_quarter first':'one_quarter'">
            	<a :href="'../recipe/chef_find.do?chef='+vo.chef"><img :src="vo.poster" alt=""></a>
            </li>
          </ul>
          <figcaption>Gallery Description Goes Here</figcaption>
        </figure>
      </div>
      
      <nav class="pagination">
        <ul>
          <li v-if="startPage>1"><a href="#" v-on:click="prev()">&laquo; Previous</a></li>
          <li v-for="i in range(startPage, endPage)" :class="i==curpage?'current':''">
          	<a href="#" @click="pageChange(i)">{{i}}</a>
          </li>
          <li v-if="endPage<totalpage"><a href="#"  @click="next()">Next &raquo;</a></li>
        </ul>
      </nav>
      
    </div>
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
<script type="text/javascript">
	 new Vue({
		el:'.container',
		data:{
			chef_info:{},
			food_list:[],
			
	 		curpage:1,
			totalpage:0,
			startPage:0,
			endPage:0,
			chef:'${chef}'
		},
		//EL,JSTL = 자바스크립트에서 사용이 가능
		mounted:function(){
			axios.get('http://localhost/web/recipe/chef_info_vue.do',{
				params:{
					chef:this.chef
				}
			}).then(response=>{
				console.log(response.data)
				this.chef_info=response.data
			}).catch(error=>{
				console.log(response.data)
				
			})
			
		},
		methods:{
			
		}
	 })
		
</script>

</body>
</html>