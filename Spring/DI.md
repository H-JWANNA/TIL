# DI (Dependency Indection)

DI는 IoC 원칙을 구현하기 위해서 사용되는 방법 중 하나로, 의존성 주입이라고 한다.

아래는 스프링에서 지원하는 DI를 위한 내용들이다.

<br>

## 🔸 스프링 컨테이너 (Spring Container)

스프링 컨테이너는 **스프링 프레임워크의 핵심 컴포넌트**로,  
내부에 존재하는 어플리케이션 빈의 생성, 관리, 제거 등 생명 주기를 관리하는 역할을 한다.

IoC Container, DI Container 등으로 불리기도 한다.

> ❓ 빈 (Bean) : 컨테이너에서 관리되는 인스턴스화된 객체를 의미한다.

<br>

> ❓ 컨테이너 (Container)  
> 소프트웨어 개발 관점에서 컨테이너란 내부에 또 다른 컴포넌트를 가지고 있는 어떠한 컴포넌트를 의미한다.
>
> 컨테이너는 객체를 생성하고 서로 연결하는 역할을 하며,  
> 객체를 설정하는 단계를 지나 마지막으로 생명주기 전반을 관리한다.
> 
> 또한, 객체의 의존성을 확인해 생성한 뒤 적절한 객체에 의존성을 주입하기도 한다.

<br>

### **💡 스프링 컨테이너의 종류**

Spring Container의 대표적인 구현체는 BeanFactory와 ApplicationContext 2가지로 나뉜다.

**1. BeanFactory**

- 스프링 컨테이너의 최상위 인터페이스
  
- 스프링 설정 파일에 등록된 Bean 객체를 생성하고 관리하는 컨테이너
  
- ```getBean()``` 메서드를 통해 빈을 인스턴스화 할 수 있다.
  
- ```@Bean```이 붙은 메서드명을 Spring Bean의 이름으로 사용하여 ```Bean 등록```을 한다.

**2. ApplicationContext**

- BeanFactory의 기능을 상속받아 제공한다.

- Bean을 관리하고 검색하는 기능 외의 부가기능을 제공한다.
  - MessageSource : 메세지 다국화를 위한 인터페이스
  - EnvironmentCapable : 개발, 운영, 환경변수 등으로 나눠 App 구동 시 필요한 정보들을 관리
  - ApplicationEventPublisher : 이벤트 관련 기능을 제공하는 인터페이스
  - ResourceLoader : 파일, 클래스 경로 등 리소스를 편리하게 조회하기 위한 인터페이스

<br>

```ApplicationContext```를 보통 스프링 컨테이너라고 하고, 인터페이스로 구성되어 있다.

```java
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory, MessageSource, ApplicationEventPublisher, ResourcePatternResolver {...}
```

<br>

### **💡 스프링 컨테이너를 사용하는 이유**

```new```를 사용해 객체를 생성하면 서로 참조하게 되고, 강하게 결합된다. (의존성이 높다)

스프링 컨테이너는 이러한 객체 간의 의존성을 낮추고, 캡슐화를 지향하여 객체지향적 프로그래밍을 하기 위해 사용된다.

<br>

### **💡 스프링 컨테이너 생성 과정**

<img src = "https://media.geeksforgeeks.org/wp-content/uploads/20210702120704/m2.png" width = "80%"/>

▲ _컨테이너가 Configuration Metadata 및 Java POJO classes를 사용하여 빈을 관리하는 방법_

<br>

스프링 컨테이너는 Configuration Metadata와 파라미터로 넘어온 설정 클래스 정보를 사용해서 스프링 빈을 등록한다.

```new AnnotationConfigApplicationContext(구성정보.class)```로 스프링에 있는 ```@Bean```의 메서드를 등록한다.

```java
// Annotation
ApplicationContext context = new AnnotationConfigApplicationContext(Example.class);

// XML
ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
```

- ```Example.class``` 등의 구성 정보를 지정하여 스프링 컨테이너를 생성해야한다.
- ```Example```에 있는 구성 정보를 통해 스프링 컨테이너는 필요한 객체들을 생성한다.
- 어플리케이션 클래스는 Configuration Metadata와 결합되어 ```ApplicationContext``` 생성 및 초기화 후 완전히 구성되고 실행 가능한 시스템 또는 어플리케이션을 갖게 된다.
  
<br>

> **컨테이너 인스턴스화**
> 
> ```ApplicationContext``` 생성자에 제공된 경로는 컨테이너가 로컬 파일 시스템, Java CLASSPATH 등  
> 다양한 외부 리소스로부터 Configuration Metadata를 로드할 수 있도록 하는 리소스 문자열이다.

<br>

> **Spring Bean 조회**
>
> ```ApplicationContext```의 메서드 중 ```getBeanDefinitionNames()``` 메서드를 호출하면  
> Spring Container에 관리되는 Bean 목록을 String배열 타입으로 리턴한다.
>
> Spring Bean 조회에서 상속 관계가 있을 경우 부모타입을 조회하면 자식타입도 함께 조회된다.  
> _(Object 타입으로 조회하면 모든 Spring Bean을 조회한다.)_

<br>

> **🔸 ApplicationContext 인터페이스 구현체 확인 방법**
>
> Mac에서 ```Cmd + O```, Windows에서 ```Ctrl + N```을 누르고,  
> Classes에서 ```ApplicationContext``` 검색
> 
> → ```ApplicationContext``` 인터페이스를 구현한 하위 클래스들이 나온다.

<br>

### 📋 [***Annotation-based Container Configuration***](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-annotation-config)

<br>

***

<br>

어노테이션 기반의 컨테이너가 아닌 XML 기반으로 만드는 컨테이너도 존재한다.  
아까 전의 코드처럼 ```ClassPathXmlApplicationContext```를 사용하면 XML 기반의 컨테이너 구성이 가능하다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
        
    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
     </bean>
        
     <bean id="..." class="...">
         <!-- collaborators and configuration for this bean go here -->
     </bean>
        
     <!-- more bean definitions go here -->
        
</beans>
```
▲ _XML 기반의 컨테이너_

<br>

- ```<beans />``` : 내부에 필요한 값들을 설정
- ```<bean id="...">``` : Bean 정의를 식별하는데 사용되는 문자열
- ```<bean class="...">``` : Bean의 유형을 정의하고 클래스 이름을 사용

<br>

***

<br>

## 🔸 빈 (Bean)

빈은 **스프링 컨테이너에 의해 관리되는 재사용 소프트웨어 컴포넌트**로, 인스턴스화된 객체를 의미한다.

설정 정보와 함께 스프링 컨테이너에 등록되고, 관리되는 객체를 **스프링 빈**이라고 한다.

Bean은 컨테이너에 사용되는 **Configuration Metadata**로 생성되며, 클래스의 등록정보, ```getter & setter``` 메서드를 포함한다.

> ❓ 설정 메타데이터 (Configuration Metadata)  
> 
> XML 또는 Java Annotation, Java Code로 표현한다.  
> 컨테이너의 명령과 인스턴스화, 설정, 조립할 객체를 정의한다.

<br>

### 💡 Bean 접근 방법

```ApplicaionContext```를 사용하여 Bean의 정의를 읽고 접근할 수 있다.

```java
// create and configure beans
ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");

// retrieve configured instance
PetStoreService service = context.getBean("memberRepository", memberRepository.class);

// use configured instance
List<String> userList = service.getUsernameList();
```

- ```getBean()``` 메서드를 사용하여 Bean의 인스턴스를 가져올 수 있다.  
  하지만, 실제 응용 프로그램 코드에서는 ```getBean()``` 메서드로 호출하여 사용하는 것은 좋은 방법이 아니다.  
  _(테스트 시에만 사용)_

<br>

### 💡 BeanDefinition (빈 설정 메타정보)  

```BeanDefinition``` 인터페이스 덕분에 스프링이 다양한 설정 형식을 지원할 수 있는 것이다.

- Spring Container는 ```BeanDefinition```이라는 추상화를 통해 스프링 빈을 생성한다.  
  즉, ```BeanDefinition```은 Bean을 어떻게 생성할지 알려주는 **레시피**와 같다.

- ```@Bean``` 또는 ```<bean>``` 당 1개씩 메타 정보가 생성되고,  
  스프링 컨테이너는 이 메타정보를 기반으로 스프링 Bean을 생성한다.

- Spring은 ```BeanDefinition``` 인터페이스를 통해 설정 메타정보를 관리므로 XML, Java로 컨테이너 설정을 할 수 있다.  
  <span style = "color : gray"> _스프링 컨테이터는 설정 형식이 XML인지 Java인지에 상관 없이 BeanDefinition만 알면 된다._ </span>

<br>

```java
ApplicationContext ac = AnnotationConfigApplicationContext(Example.class);
```

위와 같이 ```Example.class```를 매개변수로 ```ApplicationContext``` 객체를 생성하게 되면,  

```Example.class``` 정보를 바탕으로 ```BeanDefinition``` 인터페이스의 구현체 중 하나인 ```AnnotatedGenericBeanDefinition```을 만들게 되고,  

해당 객체에서 Bean 메타 정보를 가지고 Spring Container에서 Bean을 생성하게 된다.  

<br>

**🔸 BeanDefinition 정보**

- **BeanClassName**  
  생성할 Bean의 클래스 명 (자바 설정처럼 팩토리 역할의 Bean을 사용하면 없음)

- **factoryBeanName**  
  팩토리 역할의 빈을 사용할 경우 이름  
  ex) appConfig

- **factoryMethodName**  
  빈을 생성할 팩토리 메서드 지정  
  ex) memberService

- **Scope** : 싱글톤 (기본값)

- **lazyInit**  
  스프링 컨테이너를 생성할 때 Bean을 생성하는 것이 아니라,  
  실제 Bean을 사용할 때까지 최대한 생성을 지연 처리하는지 여부

- **initMethodName**  
  빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드명

- **DestroyMethodName**  
  빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드명

- **Constructor arguments, Properties**  
  의존관계 주입에서 사용 (자바 설정처럼 팩토리 역할의 Bean을 사용하면 없음)

<br>

***

<br>

## 🔸 빈 스코프 (Bean Scope)

빈 스코프는 말 그대로 빈이 사용되어지는 범위, 존재할 수 있는 범위를 뜻한다.

예를 들어, 앱이 구동되는 동안 1개의 빈만 만들어서 쓸 것인지 HTTP 요청마다 생성해서 쓸 것인지를 결정하는 것이 스코프이다.

보통 Spring Bean은 앱이 구동 될때 ```ApplicationContext```에서 한 번에 모두 생성해서  
한 클래스는 한 개의 Bean만 가지지만 → ```Singleton```  
때에 따라서 HTTP요청마다 다른 Bean을 생성해서 쓸건지 → ```Request```  
매번 사용될 때 마다 Bean을 생성해서 쓸건지 → ```Prototype```  
설정해서 쓸 수도 있다.

<br>

**🔸 Bean Scope의 특징**

- 특정 Bean 정의에서 생성된 개체에 연결할 다양한 의존성 및 구성 값뿐만 아니라 특정 Bean 정의에서 생성된 개체의 범위도 제어할 수 있다.

- Bean은 여러 범위 중 하나에 배치되도록 정의할 수 있다.

- 구성을 통해 생성하는 개체의 범위를 선택할 수 있기 때문에 강력하고 유연하다.

- 사용자 정의 범위를 생성할 수도 있다.

- Spring Famework는 기본적으로 6개의 Scope를 지원하며,  
  그 중 4개는 ```ApplicationContext```를 사용하는 경우에만 사용 가능하다.

<br>

**🔸 Bean Scope의 종류**

| Scope | Description |
|:-:|:-|
|singleton|	(Default) 각 Spring 컨테이너에 대한 단일 객체 인스턴스에 대한 단일 Bean Definition의 범위를 지정<br>→ _앱이 구동되는 동안 하나의 Bean만 사용한다._|
|prototype	|스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하는 매우 짧은 범위의 스코프<br>→ _매번 새로운 Bean을 정의해서 사용한다._|
|request	|웹 요청이 들어오고 나갈때 까지 유지되는 스코프<br>→ _HTTP 라이프 사이클마다 1개의 Bean을 사용한다._|
|session|	웹 세션이 생성되고 종료될 때 까지 유지되는 스코프<br>→ _HTTP 세션마다 1개의 Bean을 사용한다._|
|application	|웹의 서블릿 컨텍스와 같은 범위로 유지되는 스코프<br>→ _ServletContext 라이프사이클 동안 1개의 Bean만 사용한다._|
|websocket|	단일 bean definition 범위를 WebSocket의 라이프사이클까지 확장한다.<br>→ _WebSocket 라이프사이클 안에서 1개의 Bean만 사용한다._|

(```request```, ```session```, ```application```, ```websocket```은 ```ApplicationContext```의 컨텍스트에서만 유효)

<br>

### 💡 싱글톤 스코프 (Singleton Scope)

클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴

- 스프링 컨테이너의 시작과 함께 생성되어, 스프링 컨테이너가 종료될 때까지 유지된다.

- Singleton Bean의 하나의 공유 인스턴스만 관리하게 된다.  
  <span style = "color : gray">_(```private``` 생성자를 사용해 외부에서 임의로 ```new```를 사용하지 못하도록 막는다.)_</span>

- 단일 인스턴스는 Singleton Bean의 캐시에 저장되며, 이름이 정해진 Bean에 대한 모든 요청과 참조는 캐시된 개체를 반환한다.  
  <span style = "color : gray">_(Singleton Scope의 Spring Bean은 여러번 호출해도 항상 같은 인스턴스 참조 주소값을 가진다.)_</span>

- 해당 Bean Definition과 일치하는 ID를 가진 Bean에 대한 모든 요청은 스프링 컨테이너에서 해당 특정 Bean 인스턴스를 반환한다.

- 스프링 컨테이너 종료 시 소멸 메서드도 자동으로 실행된다.

<br>

**✔️ Singleton 패턴의 문제점**

- 의존 관계상 클라이언트가 구체 클래스에 의존한다.

- 지정해서 객체를 가져오기 때문에 테스트가 어렵다.

- ```private``` 생성자를 사용하여 자식 클래스를 만들기 어렵기 때문에 유연성이 떨어진다.

- Singleton Bean은 기본적으로 어플리케이션 구동 시 생성되므로 빈이 많을수록 구동 시간이 증가할 수 있다.

<br>

**✔️ Singleton 패턴 주의점**

싱글톤 패턴은 여러 클라이언트가 하나의 객체 인스턴스를 공유하기 때문에 **싱글톤 객체는 무상태로 설계**해야한다.

- 특정 클라이언트가 값을 변경할 수 있으면 안된다.
  
- 읽기만 가능해야한다.
  
- 스프링 빈의 공유 값을 설정하면 장애가 발생한다.

<br>

***

<br>

## 🔸 Java 기반의 컨테이너 설정 (Annotation 방식)

### _@Configuration_

설정 파일에 작성 (class에 작성)  
<span style = "color:gray">_(설정 파일 안에는 Bean들의 정보와 의존성 주입이 어떻게 이루어지는지 적혀있음)_</span>

내부에 있는 객체들이 Spring Container의 관리를 받기 위해서는 ```@Bean```을 작성해야한다.

<br>

### _@Bean_

생성자나 설정 파일 내의 메서드 위에 작성

<br>

### _@ComponentScan_

설정 정보 없이 자동으로 Spring Bean을 등록하는 기능

```@Component```가 붙은 모든 클래스를 Spring Bean으로 등록해준다.

> ```@SpringBootApplication```이 ```@ComponentScan```을 포함하고 있어 실제로는 많이 사용하지 않는다.  

<br>

```@ComponentScan(basePackages = "text")```와 같이 매개변수를 통해 탐색할 패키지의 시작 위치를 지정할 수 있다.

지정하지 않으면 ```@ComponentScan```이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.


<br>

```java
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
```

위 코드와 같이 필터 기능을 추가해 Bean 등록을 할 수 있다.

- ```includeFilters``` : 컴포넌트 스캔 대상을 추가로 지정

- ```excludeFilters``` : 컴포넌트 스캔에서 제외할 대상을 지정

<br>

**✔️ FilterType 옵션**

- ```ANNOTATION``` : 기본값, 애너테이션으로 인식해서 동작
- ```ASSIGNABLE_TYPE``` : 지정한 타입과 자식 타입을 인식해서 동작
- ```ASPECTJ``` : AspectJ 패턴을 사용
- ```REGEX``` : 정규 표현식을 나타낸다.
- ```CUSTOM``` : TypeFilter라는 인터페이스를 구현해서 처리

<br>

**💡 ComponentScan의 대상**

- ```@Component``` : 컴포넌트 스캔에서 사용되는 기본 대상

- ```@Controller``` & ```@RestController``` : Spring MVC 및 REST 전용 컨트롤러에서 사용

- ```@Service``` : Spring 비즈니스 로직에서 사용

- ```@Repository``` : Spring 데이터 접근 계층에서 사용

- ```@Configuration``` : Spring 설정 정보에서 사용

> 특별한 기능보다는 Spring이 해당 클래스를 어떤 로직으로 인식할지에 중점을 두고 사용한다.

<br>

### _@Component_

생성할 객체의 클래스 위에 작성

> 보통은 ```@Service```, ```@Repository```, ```@Controller``` 등을 많이 사용한다.

<br>

**💡 ```@Bean```과 ```@Component```는 어느 시점에 사용할까**

- 외부 라이브러리를 사용할 경우, 외부 라이브러리는 직접 사용하기 어렵기 때문에 사용되는 객체를 넘겨주려면 해당 객체위에 ```@Bean```과 ```@Configuration```을 주로 사용한다.

- 반면, ```@Component```는 외부 라이브러리에는 작성할 수 없으므로, 내부에서 작성한 코드에 주로 작성한다.

- 외부 라이브러리에서 지정한 타입을 리턴해야하는 경우가 있는데, 해당 경우 ```@Bean```이 유리하다.

### _@Import_

XML 파일 내에서 요소가 사용되는 것처럼 구성을 모듈화하는데 사용한다.

```java
@Configuration
public class ExampleA {
    @Bean
    public A a() {
        return new A();
    }
}

@Configuration
@Import(ExampleA.class)
public class ExampleB {
    @Bean
    public B b() {
        return new B();
    }
}
```

컨텍스트를 인스턴스화 할 때, ```ExampleA```와 ```ExampleB```를 모두 지정하는 대신 ```ExampleB```만 제공하면 된다.

```java
ApplicationContext ac = new AnnotationConfigApplicationContext(ExampleB.class);

A a = ac.getBean(A.class);
B b = ac.getBean(B.class);
```

<br>

***

<br>

## 🔸 의존 관계 주입 방법

주로 생성자 주입을 사용하며 필드 주입과 메서드 주입은 현재 거의 사용하지 않는다.

<br>

### 생성자 주입

생성자를 통해서 의존 관계를 주입하는 방법

생성자에 ```@Autowired```를 작성하면 Spring Container에 ```@Component```로 등록된 Bean에서 생성자에 필요한 Bean들을 주입한다.

- 생성자가 1개만 존재할 경우 ```@Autowired```를 생략 가능하다.
  > Spring에서 해당 클래스 객체를 생성할 때 생성자를 부르면서 의존 관계 주입이 발생한다.


- 주입받을 필드를 ```final```로 선언 가능하다.

- 생성자 호출 시점에 딱 1번만 호출된다.

```java
@Component
public class CoffeeService {
  private final MemberRepository memberRepository;
  private final CoffeeRepository coffeeRepository;

  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository, CoffeeRepository coffeeRepository) {
    this.memberRepository = memberRepository;
    this.coffeeRepository = coffeeRepository;
  }
}
```

▲ _예제 코드_

<br>

### Setter 주입 (수정자 주입)

필드의 값을 변경하는 ```setter``` 메서드를 사용해 의존 관계를 주입하는 방법

- 변경 가능성이 있는 의존 관계에 사용

- ```@Autowired```를 작성하지 않으면 실행이 되지 않는다.

```java
@Component
public class CoffeeService {
  private final MemberRepository memberRepository;
  private final CoffeeRepository coffeeRepository;

  @Autowired
  public void setMemberRepository(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Autowired
  public void setCoffeeRepository(CoffeeRepository coffeeRepository) {
    this.coffeeRepository = coffeeRepository;
  }
}
```

<br>

### 필드 주입

필드에 ```@Autowired```를 붙여 바로 주입하는 방법

- 코드가 간결하지만, 외부에서 변경이 불가능해 테스트가 어렵다.

- DI 프레임워크가 없으면 아무것도 할 수 없다. (종속적)

- 실제 코드와 상관 없는 특정 테스트를 하고 싶을 때 사용할 수 있다.

- 정상적으로 사용하기 위해서는 ```setter```가 필요해 결국 ```setter``` 주입을 하는 것이 더 편리하다.

```java
@Component
public class CoffeeService {
  @Autowired
  private final MemberRepository memberRepository;
  @Autowired
  private final CoffeeRepository coffeeRepository;
}
```

<br>

### 일반 메서드 주입

일반 메서드를 사용해 주입하는 방법

- 한 번에 여러 필드를 주입 받을 수 있다.

- 일반적으로 사용되지 않는다.

<br><br>

***

_2022.10.13. Update_