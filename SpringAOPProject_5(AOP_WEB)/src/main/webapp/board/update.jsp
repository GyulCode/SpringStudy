<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    Spring
    XML(설정파일) : 4버전 이하에서 주로 사용하는 방법(유지보수에 사용됨) 
    XML을 자바로 세팅 5,6버전에서 사용함 (6버전은 전부 자바임)
    --------------------------------------------
    스프링의 역할
    객체 생성 ~ 소멸 관리(생며주기 관리) -> 놓침
    
    --아래 내용은 더 공부--
    1.
    DI(의존성 주입) : 스프링에서 동작을 위해 필요한 데이터를 전송
    클래스와 클래스간의 연관관계를 설정 -> 클래스와 클래스간의 연관관계를 설정(IoC)
    설정 방법에는
    setter Di : setXXX()
    생성자 constructor DI : 생성자의 매개변수에 값을 설정
    필드 method DI : 객체 생성시(init-method), 객체 소멸시(destroy-method)
    나머지 메소드는 프로그램에 맞게 개발자 호출
    2.
    AOP(공통 모듈) : 프로그램에서 반복적으로 수행하는 에들을 묶어서 특정 위치에 넣어줌
    주로 사용은 1) 트랜잭션 2) 보안 3) 로그파일 
    -> 자동호출
    -> 지정 -> 어떤 메소드(PointCut), 위치 설정(JoinPoint) - Advice (Advice를 여러개 묶어서 Aspect)라 한다
    기존의 소스에 지정된 위치의 메소드를 묶어주는 역할 위빙(java -> class될때 설정함)
    *예시 코드는 강사님 파일 참조
    
    3.
    MVC : 웹 라이브러리
    DispatcherServlet : Front Controller(요청 -> 응답)
    -> 설정 (동작을 위한 설정이 필요) 그걸 아래 파일에 설정
    -> web.xml에등록 (서블릿은 개발자가 호출하는게 아님, 톰캣에 의해 호출) -> 이파일을 톰캣이 읽어감
    -> 사용자 정의 클래스를 등록한 파일로 전송 application-*.xml
    Model (Controller) : 요청에 대한 처리 -> 결과값을 모아서 DispatcherServlet 으로 전송
    -> 모델찾기 : HandlerMapping
    View(JSP) : request, session 전송을 받아서 화면 출력
    -> ViewResolver : MVC동작을 위한 기본 틀이 만들어져 있다.
    -> 기본틀에 설정은 1) 클래스 등록(XML, Java) 2) 메소드: @RequestMapping, @getMapping...
    단 클래스 등록에서 모든 클래스가 등록되는건 아니다. -> 따로 추가적인 설정이 필요
    XML
    	<bean class="" id="">
    	<context:component-scan base-package="">
    어노테이션
    	기능별 구분 (일종의 인덱스와 비슷 찾기가 빠르다) Component아래로 다 Compnent 상속받음
    	@Component : 일반 클래스 (MainClass, 크롤링, OpenAPI...)
    	@Repository : DAO(저장소)
    	@Service : DAO+DAO, 의존성이 낮게 관리히가 위함 (Service, Impl 이거같음)
    	@Controller : Model (페이지 관리) -> 화면 이동 관리
    	@RestController : Model(데이터 관리 Rest방식으로 하는듯, 화면에 데이터 출력할 데이터를 전송);
    	 	-> 다른 언어와 연동(JavaScript, Kotlin)
    	 		1)일반 문자열 
    	 		2)JSON Ajax, VueJS, React -> 자동 파싱
    	 		3)XML(파싱에 시간이 많이 걸림 -> 주로 JSON사용)
    	@ControllerAdvice : Controller클래스의 공통으로 사용되는 예외처리
    
    소스분석 순석
    web.xml(서블릿) / server.xml(DB) / application.xml(
    클래스(Model -> DAO (Service) -> JSP)순으로 보면됨
    React/Vue/**MSA(Spring Cloud)
    CI 지속적통합 / CD 지속적배포(잰킨스)
    	
    동작 순서
    (인터셉터 메소드)
    사용자(request) .do요청 -> dispatcher -> 1)prehandler(자동로그인 .do가 넘어 가기전) -> controller(사용자,
    							|		  2)posthandler(Jsp로 전송 전까지)
    							| afterCompletion(JSON)
    response			     Vies(JSP)
    -> layout : tiles
    -> security : 암호화 / 복호화, 권한 부여
    -> 인터셉트  : 자동 로그인, 페이스북, 네이버, 로그인 방법
       데이터 마이닝 : 추천
    -> spring-Boot(3차 HTML, JPA, MySQL)
    
    
    
    
    
    
    	
    
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	  			<h2 class="sectiontitle">수정하기</h2>
	  			<form method="post" action="../board/update_ok.do">
			      <table class="table">
			        <tr>
			          <th width=20% class="text-right">이름</th>
			          <td width=80%>
			           <input type=text name=name size=20 class="input-sm" value="${vo.name }">
			           <input type=hidden name="no" value="${vo.no }">
			          </td>
			        </tr>
			        <tr>
			          <th width=20% class="text-right">제목</th>
			          <td width=80%>
			           <input type=text name=subject size=50 class="input-sm" value="${vo.subject }">
			          </td>
			        </tr>
			        <tr>
			          <th width=20% class="text-right">내용</th>
			          <td width=80%>
			           <textarea rows="10" cols="52" name="content" >  ${vo.content}  </textarea>
			          </td>
			        </tr>
			        <tr>
			          <th width=20% class="text-right">비밀번호</th>
			          <td width=80%>
			           <input type=password name=pwd size=10 class="input-sm">
			          </td>
			        </tr>
			        <tr>
			          <td colspan="2" class="text-center">
			           <input type=submit value="글쓰기" class="btn btn-sm btn-primary">
			           <input type=button value="취소" class="btn btn-sm btn-info"
			            onclick="javascript:history.back()">
			          </td>
			        </tr>
			      </table>
		      </form>
	  		</div>
	  	</main>
  	</div>
	  		

</body>
</html>