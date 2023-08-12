<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=6a0ea7566fd277ba692466d2e5949356&libraries=services"></script>
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
  <div class="container">
    <h3></h3>
    <div class="row">
      <%-- 이미지 5개 --%>
      <table class="table">
        <tr>
          <td class="text-center" v-for="img in poster">
           <img :src="img" style="width:100%">
          </td>
        </tr>
      </table>
    </div>
    <div style="height:10px"></div>
    <div class="row">
      <div class="col-sm-7">
        <%-- 상세정보 --%>
        <table class="table">
          <tr>
            <td colspan="2">
              <h4> <span id="name">{{food_detail.name}}</span>&nbsp;<span style="color:orange">{{food_detail.score}}</span></h4>
            </td>
          </tr>
          <tr>
            <td width=20% class="success">주소</td>
            <td width=80%>{{food_detail.address}}</td>
          </tr>
          <tr>
            <td width=20% class="success">음식종류</td>
            <td width=80%>{{food_detail.type}}</td>
          </tr>
          <tr>
            <td width=20% class="success">가격대</td>
            <td width=80%>{{food_detail.price}}</td>
          </tr>
          <tr>
            <td width=20% class="success">주차</td>
            <td width=80%>{{food_detail.parking}}</td>
          </tr>
          <tr>
            <td width=20% class="success">영업시간</td>
            <td width=80%>{{food_detail.time}}</td>
          </tr>
          <tr>
            <td width=20% class="success">메뉴</td>
            <td width=80%>
              <ul>
                <li v-for="m in menu">{{m}}원</li>
              </ul>
            </td>
          </tr>
          <tr>
            <td width=20% class="success">전화번호</td>
            <td width=80%>{{food_detail.phone}}</td>
          </tr>
        </table>
        <%-- 댓글 Vue=CURD --%>
        <div style="height:20px"></div>
        <table class="table">
			<tr>
				<td>
					<table class="table" v-for="re in reply_data">
						<tr>
							<td class="text-left">
							▣<span style="color:blue">{{re.name}} </span>&nbsp;{{re.dbday}}
							</td>
							<td class="text-right">
								<span v-if="re.id==food_detail.sessionId">
									<input type="button" class="btn btn-xs btn-success" 
									value="수정" @click="replyUpdateForm(re.no)" :id="'up'+re.no" >
									<input type="button" class="btn btn-xs btn-info"
									value="삭제" @click="replyDelete(re.no)" >
								</span>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="text-left" valing="top">
								<pre style="white-space:pre-wrap; background-color: white; border:none;">{{re.msg}}</pre>
							</td>
						</tr>
						<tr style="display:none" :id="'reply'+re.no" class="updates">
							<td colspan="2">
								<textarea rows="3" cols="55" style="float:left" ref="upmsg" v-model="msg">{{re.msg}}</textarea>
			        			<input type=button value="수정하기"  
			        			style="height:67px; background-color:blue; color:white;" 
			        			@click="replyUpdate(re.no)">
							</td>
						</tr>
					</table>
				</td>
			</tr>
        </table>
        <table class="table" v-if="food_detail.sessionId!=null">
        	<tr>
        		<td>
        			<textarea rows="3" cols="55" style="float:left" ref="msg" v-model="msg"></textarea>
        			<input type=button value="댓글 쓰기" style="height:67px; 
        			background-color:blue; color:white;" @click="replyWrite()">
        		</td>
        	</tr>
        </table>
      </div>
      <div class="col-sm-5">
        <%-- 지도 출력 위치 --%>
        <div id="map" style="width:100%;height:350px;"></div>
        <%-- 분석한 내용을 그래프로 출력할 예정: 데이터 분석/데이터 마이닝- 꼬꼬마 --%>
      </div>
    </div>
  </div>
  <script>
   new Vue({
	   el:'.container',
	   data:{
		   fno:${fno},
		   food_detail:{},
		   reply_data:[],
		   msg:'',
		   menu:[],
		   poster:[],
		   no:0
	   },
	   mounted:function(){
		   // 1. 상세정보 읽기
		   axios.get('../food/food_detail_vue.do',{
			   params:{
				   // ?물음표 뒤에 fno값을 지정해주는 역할: 변수아님
				   fno:this.fno
			   }
		   }).then(response=>{
			   console.log(response.data)
			   this.food_detail=response.data
			   
			   if(this.food_detail.menu!=null)
				{
				   this.menu=this.food_detail.menu.split("원")
				}
			   this.poster=this.food_detail.poster.split("^")
			   
			   if(window.kakao && window.kakao.maps)
				{
				   this.initMap();
				}
			   else
				{
				   this.addScript();
				}
		   }).catch(error=>{
			   console.log(error.response)
		   })
		   
		   axios.get('../food/reply_list_vue.do',{
			   params:{
				   fno:this.fno
			   }
		   }).then(response=>{
			   console.log(response.data)
			   this.reply_data=response.data
		   }).catch(error=>{
			   console.log(error.response)
		   })
		   // 2. 지도 출력
		   
		   // 3. Jquery 연결
		   $('.updateBtn').click(function(){
			   let no=$(this).attr("data-no");
			   if(this.selectNo==0){
				   $('#reply'+no).show();
				   $(this).val('취소')
				   this.selectNo=1;
			   }else{
				   
				   $('#reply'+no).hide();
				   $(this).val('수정')
				   this.selectNo=0;
			   }
			   
		   })
		   
	   },
	   methods:{
		   // 댓글 작성
		   initMap:function(){
			   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = {
			        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };  

			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 

			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();

			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(this.food_detail.address, function(result, status) {

			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {

			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });

			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+$('#name').text()+'</div>'
			        });
			        infowindow.open(map, marker);

			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } 
			});   
		   },
		   addScript:function(){
			   const script=document.createElement("script")
			   /* global kakao */
			   script.onload=()=>kakao.maps.load(this.initMap)
			   script.src='https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6a1ffbe66e5a8ba9af15d82e0b41ceac&libraries=services'
			   document.head.appendChild(script)
		   },
		   replyWrite:function(){
			   if(this.msg.trim===""){
				   this.$refs.msg.focus();
				   return;
			   }
			   axios.get('../food/reply_insert_vue.do',{
				   params:{
					   fno:this.fno,
					   msg:this.msg,
					   star:5
				   }
			   }).then(response=>{
				   console.log(response.data)
				   this.reply_data=response.data
				   this.msg='';
			   }).catch(error=>{
				   console.log(error.response)
			   })
		   },
		   replyDelete:function(no){
			   axios.get('../food/reply_delete_vue.do',{
				   params:{
					   no:no,
					   fno:this.fno
				   }
			   }).then(response=>{
				   console.log(response.data)
				   this.reply_data=response.data
			   }).catch(error=>{
				   console.log(error.response)
			   })
		   },
		   replyUpdateForm:function(no){
			   $('.updates').hide();
			   if(this.no==0){
				   $('#reply'+no).show();
				   $('#up'+no).val("취소");
				   this.no=1;
			   }else{
				   $('#reply'+no).hide();
				   $('#up'+no).val("수정");
				   this.no=0;
				   
			   }
		   },
		   replyUpdate:function(no){
			   let msg=this.$refs.upmsg.value;
			   if(msg===""){
				   this.$refs.upmsg.focus()
				   return;
			   }
			   alert(msg=$refs.upmsg)
			   axios.post('../food/reply_update_vue.do',null,{
				   params:{
					   no:no,
					   msg:msg,
					   fno:this.fno
				   }
			   }).then(response=>{
				   console.log(response.data)
				   this.reply_data=response.data //update => 변경된 데이터 값으로 출력
				   $('#reply'+no).hide();
				   $('#up'+no).val("수정");
				   this.no=0;
				   
			   }).catch(error=>{
				   console.log(error.response)
			   })
		   }
		   
	   }
   
   })
  </script>
</body>
</html>