package com.sist.main;
/*
 1. �����ӿ�ũ
 	���̺귯�� : ����ǰ
 	�����ӿ�ũ : ���� ����
 		������ (�������� �����ӿ�ũ(�����), �ִ������ӿ�ũ(����))
 		���߿��� �⺻�� �Ǵ� Ʋ�� ����(����� �߰��ؼ� ���)
 		�������� ��ǥ (�ε� ����) -> ���¼ҽ�
 			1. Ŭ������ Ŭ�������� ������ ����=���� ���յ� ���� / ����� �ٸ� Ŭ������ ������ ������(POJO)
 			2. �޼ҵ帶�� �ݺ��ϴ� �ҽ��ڵ�� ���� ���� �и�(�������� ���� ����) / AOP : OOP�� ����
 		���
 			1. Ŭ������ ��Ƽ� ����(����~�Ҹ�) -> *�����̳�* / ������ �ʿ��� ������(��, ��ü(�ּ�))�� �־��ش� -> DI
 			DI : ��ü ����, Ŭ������ �������� 
 			AOP : ������(AOP��� -> �α�����, Ʈ�����, ����)
 			���� �ι���� �������̽� ����̴�.
 		
 		Spring �Թ����� �����ؾ��Ұ�
	 		1. �����̳��� ����
	 			BeanFactory : ���� �ܼ��� �����̳ʷ� ����ڰ� ���� Ŭ������ �����Ѵ�->DI //CORE
	 			ApplicationContext : AOP, �޼��� ����(JMS)
	 			ClassPathApplication
	 			FileSystemXmlApplication
	 			WebApplicationContext : MVC(��)
	 			
	 			Container�� ����
	 			1) DL DataLookup : Ŭ������ ã���ִ� ���� getBean() //app.xml�� ����Ȱ�?
	 			2) DI DataInjection : �ʿ��� �����͸� �޾Ƽ� �ʱ�ȭ(����)
	 				= setter Di(����)
	 				= constructor(������) �������� �Ű����� �̿�
	 			
	 		2. DI (���� �������� �����ϴ� ���) // ������, setter, xml�� �Ƹ� ��ǥ����
	 			������ �� ����, ��ü�� �ּ� �Ҵ� ���� ���⿡ �ش�ȴ�.
	 			������ private�̶� ������ ����� �׷��� setter, ������ ����Ѱ���
	 			p:������(setXX()), c:������(�Ű�������)
	 			
	 			+++ ������ ���� ���̺귯��
	 			1) Spring Core : DI(��ü ����, �Ҹ�) -> Container
	 			2) Spring AOP : ������ -> �ڵ�ȣ��
	 				a)Before
	 				b)After
	 				c)After-Returning
	 				d)After-Throwing
	 				e)Around
	 				f)JoinPoint, PointCut, ����
	 				g)Advice
	 			3) Spring ORM : �����ͺ��̽� ����(MyBatis, Hibernate, JPA(Hibernate���))
	 			4) Spring WEB : JSP ����
	 			5) Spring MVC : View/Model/Controller

	 		***XML ���
	 			���������� ��ü�� �����ϱ� ���� ����Ѵ�.
	 			Ŭ�������� ����(ID) �̰ɷ� �޸��Ҵ��� ����
	 			----------
	 			XML
	 			Annotation			��Ÿ���� -> ������ �����̳�
	 			�ڹ��ڵ�
	 			----------
	 			xml �̿��
	 				1.Ŭ���� �Ѱ��� ����
	 					<bean id="a" class="A">
	 					<bean id="b" class="B">
	 					Map
	 					key		Ŭ���� ��ü
	 					a		new A()
	 					b		new B()
	 					**���α׷� ���� ���� -> Ŭ������ ��Ƽ� ���� : �ּҰ��� ������ �ȵȴ�
	 					  -> �̱������� �ϳ��� �����ϰ� �������� ����
	 				2.��Ű�� ������ ���� ���
	 					<context:component-scan base-package="com.sist.*">
	 					base-package ������� ��θ� �а� �ȿ� ��ü���� bean���� �ٲ���
	 				3.����
	 					����� ���� / ���̺귯�� Ŭ����(MyBatis,ViewResolver...) => ������̼��� ���� -> �������̸� XML ���
	 				
	 			Annotation
	 				�޸� �Ҵ�
	 				@Component : �Ϲ�Ŭ����(MainClass, ũ�Ѹ�, OpenAPI)
	 				@Repository : DAO(�����)
	 				@Service : DAO�������� �ѹ��� ����(BI)
	 				@Controller : MVC������ Model(������ ����)
	 				@RestController :  MVC������ Model -> JSON
	 				@ControllerAdvice : ���� ����ó��
	 				@RestControllerAdvice : ���� ����ó��
	 				----------------------Ŭ������ ������ �ϸ� �����̳ʰ� �޸𸮸� �Ҵ��Ѵ�.
	 			***��(��ü) �����ֱ�
	 				����� : DI = init-method = ��ü���(������) = destroy-method = �Ҹ�
	 			�ڹ��ڵ�
	 			
	 		3. AOP�� ����
	 		4. MVC���� �ľ�
	 		5. ����
 			
 			
 			

  
  
 */
public class MainClass {
	public static void main(String[] args) {
		String path="C:\\springDev\\springStudy\\SpringDiProject_8\\src\\main\\java\\com\\sist\\main\\app.xml";
		ApplicationContext app=new ClassPathApplicationContext(path);
		Sawon sa=(Sawon)app.getBean("sa");
		System.out.println("���:"+sa.getSabun());
		System.out.println("�̸�:"+sa.getName());
		System.out.println("�μ�:"+sa.getDept());
		System.out.println("����:"+sa.getJob());
		 
		
	}

}
