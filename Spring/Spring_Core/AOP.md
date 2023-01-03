# AOP (Aspect-Oriented Programming)

관점 지향 프로그래밍(AOP)은 관점(Aspect)에 따라 핵심 기능과 부가 기능을 분리한다.

AOP는 기존에 사용하던 **OOP를 대체하기 위한 것이 아닌** 횡단 관심사를 깔끔하게 처리하기 위해   
**OOP의 부족한 부분을 보조하는 목적으로 개발**되었다.

> 관점 (Aspect)  
>
> 부가 기능을 정의한 코드인 Advice와 Advice를 어디에 적용할지 결정하는 PointCut을 합친 개념  
>
> 분리한 부가 기능과 그 기능들을 어디에 적용할지 선택하는 기능을 합해서 하나의 모듈로 만든 것

<br>

### 기존 객체 지향 프로그래밍

- OOP의 핵심은 공통된 목적을 띈 데이터와 동작을 묶어 하나의 객체로 정의하는 것이다.

- 객체를 적극적으로 활용함으로써, 기능을 재사용 할 수 있는 것이 장점이다.

- 객체를 잘 활용하기 위해선 관심사 분리(Separation of Concerns, SoC)의 디자인 원칙을 준수해야한다.

> **관심사 분리는 모듈화의 핵심이다.**  
> Spring에서는 ```@Controller```, ```@Service```, ```@Repository```처럼 관심사 별로 계층을 나눠 객체를 관리한다.

<br>

### 기존 객체 지향 프로그래밍의 문제점

특정 관심사 업무 관련 코드에 트랜잭션, 보안, 로깅 등의 코드가 존재한다.  
즉, **비즈니스 클래스에 횡단 관심사와 핵심 관리사가 공존하게 된다**는 뜻이다.

- 이로 인해, 메서드의 복잡도가 증가하여 비즈니스 코드 파악이 어렵게 된다.

- 부가 기능의 불특정 다수 메서드가 반복적으로 구현되어 횡단 관심사의 모듈화가 어렵다.

<br>

### AOP의 등장

기존 OOP가 클래스를 단위로 모듈화를 했다면, AOP는 관점을 기준으로 모듈화를 한다.

AOP에서는 어플리케이션 로직을 비즈니스 로직과 같은 **핵심 기능**과  
트랜잭션, 보안, 로깅 등의 핵심 기능을 보조하기 위한 **부가 기능**으로 나눈다.

> 소프트웨어 개발에서 변경 지점은 **하나**가 될 수 있도록 잘 모듈화 되어야 한다.  
> 
> AOP는 OOP 방식의 불필요한 반복을 해결하기 위해 등장했다.

<br>

***

<br>

## AOP의 용어

### 🔸 Aspect

여러 객체에 공통으로 적용되는 공통 기능을 뜻한다.

Advice + PointCut을 모듈화하여 어플리케이션에 포함되는 횡단 기능이다.

<br>

### 🔸 Join Point

클래스 초기화, 객체 인스턴스화, 메서드 호출, 필드 접근, 예외 발생과 같은 **어플리케이션 실행 흐름에서의 특정 포인트**를 뜻한다.

- 추상적인 개념으로, AOP를 적용할 수 있는 모든 지점을 말한다.

어플리케이션에 새로운 동작을 추가하기 위해 조인 포인트에 관심 코드(Aspect Code)를 추가할 수 있다.

> Spring AOP는 프록시 방식을 사용하므로, 조인 포인트는 항상 **메서드 실행 지점으로 제한**된다.

<br>

### 🔸 Advice

Join Point에서 수행되는 코드를 뜻하며, Aspect를 **언제** 핵심 코드에 적용할 지를 정의한다.

시스템 전체 Aspect에 API 호출을 제공한다.

<br>

### 🔸 PointCut

Join Point 중에서 Advice가 적용될 **위치**를 선별하는 기능이다.

AspectJ 표현식을 사용해서 지정한다.

> Spring AOP는 프록시 방식을 사용하므로, 메서드 실행 지점만 포인트컷으로 선별 가능하다.

<br>

### 🔸 Weaving

PointCut으로 결정한 Target의 Join Point에 Advice를 적용하는 것을 뜻한다.  
쉽게 말해, Advice를 핵심 코드에 적용하는 것을 의미한다.

Weaving을 통해 핵심 기능 코드에 영향을 주지 않고 부가 기능을 추가할 수 있다.

<br>

### 🔸 AOP Proxy

AOP 기능을 구현하기 위해 만든 프록시 객체이다.

프록시는 어떤 객체를 사용하고자 할 때, 해당 객체에 직접 요청하는 것이 아닌 중간에 프록시 객체를 두어 프록시 객체가 대신해서 요청을 받아 실제 객체를 호출해 주도록 한다.

프록시 객체 내부에는 실제 Bean을 요청하는 로직이 들어있어, 클라이언트의 요청이 들어오면 그 때 실제 Bean을 호출한다.

Spring에서 AOP Proxy는 JDK 동적 프록시 또는 CGLIB 프록시이다.

<br>

### 🔸 Target

부가 기능을 부여할 대상을 말한다.

Advice를 받는 객체이며 PointCut으로 결정된다.

<br>

### 🔸 Adviser

하나의 Advice와 하나의 PointCut으로 구성된다.

- Spring AOP에서만 사용되는 특별한 용어이다.

<br>

***

<br>

## 타입별 Advice

> **💡 Advice**
>
> Join Point에서 수행되는 코드를 뜻하며, Aspect의 공통 기능을 **언제** 핵심 코드에 적용할 지를 정의한다.

<br>

Advice는 기본적으로 순서를 보장하지 않는다.

순서를 지정하기 위해서는 ```@Aspect``` 적용 단위로 org.```springframework.core.annotation.@Order``` 어노테이션을 적용해야한다.
- Advice 단위가 아닌 클래스 단위로 적용 가능하다.
- 하나의 Aspect에 여러 Advice가 존재하면 순서를 보장받을 수 없다.
- Aspect를 별도의 클래스로 분리해아 한다.

<br>

### Advice의 종류

**🔸 Before**

Join Point **실행 이전**에 실행하도록 한다.

Before Advice를 구현한 메서드는 일반적으로 **리턴 타입이 void**이지만,  
리턴 값을 갖더라도 실제 Advice 적용 과정에는 영향을 주지 않는다.

메서드에서 예외를 발생시킬 경우 대상 객체의 메서드가 호출되지 않는다는 주의 사항이 있다.

```java
@Before("hello.aop.order.aop.Pointcuts.orderAndService()")
public void doBefore(JoinPoint joinPoint) {
    log.info("[before] {}", joinPoint.getSignature());
}
```

- 작업 흐름을 변경할 수 없으며, 메서드 종료 시 자동으로 다음 타겟이 호출된다.

<br>

**🔸 After returning**

Join Point가 **정상적으로 완료된 후** 실행하도록 한다.

```java
@AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
public void doReturn(JoinPoint joinPoint, Object result) {
    log.info("[return] {} return={}", joinPoint.getSignature(), result);
}
```

- returning 속성에 사용된 이름은 Advice 메서드의 매개변수 이름과 동일해야한다.
- returning 절에 지정된 타입의 값을 반환하는 메서드만 대상을 실행한다.

<br>

**🔸 After throwing**

메서드가 **예외를 발생시키는 경우**에 실행하도록 한다.

```java
@AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
public void doThrowing(JoinPoint joinPoint, Exception ex) {
    log.info("[ex] {} message={}", joinPoint.getSignature(), ex.getMessage());
}
```

- throwing 속성에 사용된 이름은 Advice 메서드의 매개변수 이름과 동일해야한다.
- throwing 절에 지정된 타입과 일치하는 예외를 대상으로 실행한다.

<br>

**🔸 After**

Join Point의 동작과 상관없이 실행하도록 한다.
- try-catch 문의 ```finally```와 비슷한 동작을 한다.

메서드 실행 후 ```@After```의 공통 기능을 실행한다.

- 일반적으로 리소스를 해제하는데 사용

<br>

**🔸 Around**

메서드 호출 전・후에 수행하며, 가장 강력한 Advice이다.

메서드 실행 전 & 후, 예외 발생 시점에 공통 기능을 실행한다.

Advice의 첫 번째 매개변수는 ```ProceedingJoinPoint```를 사용한다.

```proceed()```를 통해 대상을 실행하며, 여러번 실행할 수 있다.

<br>

***

<br>

## PointCut 표현식

> **💡 PointCut**
>
> Join Point 중에서 Advice가 적용될 **위치**를 선별하는 기능이다.
>
> AspectJ 표현식을 사용해서 지정한다.

<br>

### 포인트컷 지시자 (PointCut Designator, PCD)

PointCut 표현식은 ```execution```과 같은 포인트컷 지시자로 시작한다.

```execution```을 가장 많이 사용하며, 나머지는 자주 사용하지 않는다.

|종류	|설명|
|:-:|:-|
|execution	|메서드 실행 조인 포인트를 매칭한다.<br>스프링 AOP에서 가장 많이 사용하며, 기능도 복잡하다.|
|within|	특정 타입 내의 조인 포인트를 매칭한다.|
|args|	인자가 주어진 타입의 인스턴스인 조인 포인트|
|this|	스프링 빈 객체(스프링 AOP 프록시)를 대상으로 하는 조인 포인트|
|target|	Target 객체(스프링 AOP 프록시가 가르키는 실제 대상)를 대상으로 하는 조인 포인트|
|@target|	실행 객체의 클래스에 주어진 타입의 어노테이션이 있는 조인 포인트|
|@within|	주어진 어노테이션이 있는 타입 내 조인 포인트|
|@annotation|	메서드가 주어니 어노테이션을 가지고 있는 조인 포인트를 매칭|
|@args|	전달된 실제 인수의 런타임 타입이 주어진 타입의 어노테이션을 갖는 조인 포인트|
|bean	|스프링 전용 포인트컷 지시자이고 빈의 이름으로 포인트컷을 지정한다.|

<br>

### 일반적인 PointCut 표현식

포인트컷 표현식은 ```@Pointcut``` 어노테이션을 사용하여 표시된다.

포인트컷 선언은 이름과 매개변수를 포함하는 서명과  
메소드 실행을 정확히 결정하는 Pointcut 표현식의 두 부분으로 구성된다.

🔸 ```execution(public * *(..))```  
&emsp; : 모든 공개 메서드 실행
  
🔸 ```execution(* set*(..))```  
&emsp; : ```set``` 다음 이름으로 시작하는 모든 메서드 실행

🔸 ```execution(* com.xyz.service.AccountService.*(..))```  
&emsp; : ```AccountService``` 인터페이스에 의해 정의된 모든 메서드의 실행

🔸 ```execution(* com.xyz.service.*.*(..))```  
&emsp; : ```service``` 패키지에 정의된 메서드 실행

🔸 ```execution(* com.xyz.service..*.*(..))```  
&emsp; : ```service``` 패키지 또는 해당 하위 패키지 중 하나에 정의된 메서드 실행

🔸 ```within(com.xyz.service.*)```  
&emsp; : ```service``` 패키지 내의 모든 조인 포인트  
<span style = "color : gray">&emsp; &nbsp; *(Spring AOP에서만 메서드 실행)*</span>

🔸 ```within(com.xyz.service..*)```  
&emsp; : ```service``` 패키지 또는 하위 패키지 중 하나 내의 모든 조인 포인트  
<span style = "color : gray">&emsp; &nbsp; *(Spring AOP에서만 메서드 실행)*</span>

🔸 ```this(com.xyz.service.AccountService)```  
&emsp; : ```AccountService``` 프록시가 인터페이스를 구현하는 모든 조인 포인트  
<span style = "color : gray">&emsp; &nbsp; *(Spring AOP에서만 메서드 실행)*</span>

🔸 ```target(com.xyz.service.AccountService)```  
&emsp; : ```AccountService``` 대상 객체가 인터페이스를 구현하는 모든 조인 포인트  
<span style = "color : gray">&emsp; &nbsp; *(Spring AOP에서만 메서드 실행)*</span>

🔸 ```args(java.io.Serializable)```  
&emsp; : 단일 매개변수를 사용하고 런타임에 전달된 인수가 ```Serializable```과 같은 모든 조인 포인트  
<span style = "color : gray">&emsp; &nbsp; *(Spring AOP에서만 메서드 실행)*</span>

🔸 ```@target(org.springframework.transaction.annotation.Transactional)```  
&emsp; : 대상 객체에 ```@Transactional``` 어노테이션이 있는 모든 조인 포인트  
<span style = "color : gray">&emsp; &nbsp; *(Spring AOP에서만 메서드 실행)*</span>

🔸 ```@annotation(org.springframework.transaction.annotation.Transactional)```  
&emsp; : 실행 메서드에 ```@Transactional``` 어노테이션이 있는 조인 포인트  
<span style = "color : gray">&emsp; &nbsp; *(Spring AOP에서만 메서드 실행)*</span>

🔸 ```@args(com.xyz.security.Classified)```  
&emsp; : 단일 매개 변수를 사용하고 전달된 인수의 런타임 유형이 ```@Classified``` 어노테이션을 갖는 조인 포인트  
<span style = "color : gray">&emsp; &nbsp; *(Spring AOP에서만 메서드 실행)*</span>

🔸 ```bean(tradeService)```  
&emsp; : ```tradeService``` 라는 이름을 가진 스프링 빈의 모든 조인 포인트  
<span style = "color : gray">&emsp; &nbsp; *(Spring AOP에서만 메서드 실행)*</span>

🔸 ```bean(*Service)```  
&emsp; : 와일드 표현식 ```*Service``` 라는 이름을 가진 스프링 빈의 모든 조인 포인트

<br>

### PointCut 표현식 결합

```&&```, ```||```, ```!```를 사용하여 포인트컷 표현식을 결합할 수 있다.

또한, 이름으로 PointCut 표현식을 참조할 수도 있다.

```java
@Pointcut("execution(public * *(..))")
private void anyPublicOperation() {}

@Pointcut("anyPublicOperation() && inTrading()")
private void tradingOperation() {}
```

<br>

### @AspectJ

@AspectJ는 어노테이션이 있는 일반 Java 클래스로 관점을 선언하는 스타일을 말한다.

- Java에서 @AspectJ 활성화 방법

```java
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

}
```
▲ _```@Configuration```으로 @AspectJ 활성화를 위해서 ```@EnableAspectAutoProxy``` 어노테이션을 추가한다._

<br>

- XML에서 @AspectJ 활성화 방법

```xml
<aop:aspectj-autoproxy/>
```
▲ _@AspectJ 활성화를 위해 ```aop:aspectj-autoproxy```를 사용한다._

<br>

@AspectJ 지원이 활성화되면, @AspectJ 관점이 있는 클래스(```@Aspect```)로 어플리케이션 컨텍스트에 정의된 모든 Bean이 Spring에서 자동으로 감지되고, Spring AOP를 구성하는데 사용된다.

```java
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class NotVeryUsefulAspect {}
```
▲ _Java AspectJ_

<br>

```xml
<bean id="myAspect" class="org.xyz.NotVeryUsefulAspect">
    <!-- configure properties of the aspect here -->
</bean>
```
▲ _xml AspectJ_

<br>

***

<br>

## Join Point

> **💡 Join Point**
>
> AOP를 적용할 수 있는 지점을 말한다.  
> → Advice 적용 위치, 메서드 실행 위치, 생성자 호출, 필드 값 접근, static 메서드 접근 등의 프로그램 실행 중 지점 

<br>

AspectJ를 사용해서 컴파일 시점과 클래스 로딩 시점에 적용하는 AOP는  
실제 바이트코드를 조작하므로, 해당 기능을 모든 지점에 전부 적용 가능하다.

프록시 방식을 사용하는 스프링 AOP는  
- **메서드 실행 지점에만 AOP를 적용**할 수 있으며, 메서드 오버라이딩 개념으로 동작한다.
  - 생성자, static 메서드, 필드값 접근에는 프록시 개념이 적용될 수 없다.
- 스프링 컨테이너가 관리할 수 있는 **스프링 Bean에만 AOP**를 적용 가능하다.

JoinPoint 메서드는 Advice 종류에 따라 사용 방법이 다르지만,  
기본적으로 Advice 메서드에 매개변수로 선언만 하면 된다.

<br>

### JoinPoint 인터페이스의 주요 기능

🔸 ```JoinPoint.toString()```  
&emsp; : 조인되는 방법에 대한 설명을 인쇄  

🔸 ```JoinPoint.getArgs()```  
&emsp; : JoinPoint에 전달된 인자를 배열로 반환

🔸 ```JoinPoint.getThis()```  
&emsp; : AOP 프록시 객체를 반환

🔸 ```JoinPoint.getTarget()```  
&emsp; : AOP가 적용된 대상 객체를 반환
- 클라이언트가 호출한 비즈니스 메서드를 포함하는 비즈니스 객체를 반환

🔸 ```JoinPoint.getSignature()```  
&emsp; : 조인되는 메서드에 대한 설명을 반환
- 클라이언트가 호출한 메서드의 시그니처(리턴타입, 이름, 매개변수) 정보가 저장된 ```Signature``` 객체를 반환

> **💡 Signature**  
> 
> 객체가 선언하는 모든 연산을 연산의 이름, 매개변수로 받아들이는 객체들을 의미한다.
> 
> **💡 Signature가 제공하는 메서드**  
> 
> ```String getName()```  
> : 클라이언트가 호출한 메서드의 이름을 반환  
> ```String toLongString()```  
> : 클라이언트가 호출한 메서드의 리턴타입, 이름, 매개변수를 패키지 경로까지 포함해서 반환  
> ```String toShortString()```  
> : 클라이언트가 호출한 메서드 시그니처를 축약한 문자열로 반환  

<br>

### ProceedingJoinPoint 인터페이스의 주요 기능

🔸 ```proceed()```  
&emsp; : 다음 Advice나 Target을 호출

<br><br>

***

_2022.10.18. Modified_

_2022.10.17. Update_