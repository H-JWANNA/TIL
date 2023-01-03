# Spring Boot

Spring Boot는 Spring Framework의 편리함에도 불구하고,  
Spring 설정의 복잡함으로 인한 어려움을 해결하기 위해 생겨난 Spring Project 중 하나이다.

<br>

## Sprint Boot를 사용하는 이유

### 🔸 XML 기반의 복잡한 설계 방식 지양

Spring의 복잡한 개발 설정을 해소해준다.

<br>

### 🔸 의존 라이브러리의 자동 관리

기존 Spring에서는 어플리케이션에 필요한 라이브러리의 이름과 버전을 전부 추가해야 했다.  
이로 인해 라이브러리 간의 버전 불일치로 인한 빌드 및 실행 오류 역시 빈번하게 발생하곤 했다.

하지만, Spring Boot는 Starter 모듈 구성 기능을 통해 의존 라이브러리를 자동으로 관리해준다.

```java
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-jdbc'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    implementation 'com.h2database:h2'
}
```

▲ _웹 애플리케이션 개발을 위한 Spring Boot 의존 라이브러리 설정_

<br>

### 🔸 어플리케이션 설정의 자동 구성

Spring Boot는 Starter 모듈을 통해 설치되는 의존 라이브러리를 기반으로 어플리케이션의 설정을 자동으로 구성한다.

```java
@SpringBootApplication
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
```

▲ _Spring Boot 자동 구성 활성화 예시_

<br>

예시를 들어 ```@SpringBootApplication``` 어노테이션을 추가하게 되면,  
```implementation 'org.springframework.boot:spring-boot-starter-web'```과 같은 Starter가 존재할 때,  
웹 어플리케이션이라고 추측한 뒤, 웹 애플리케이션을 띄울 서블릿 컨테이너(default: Tomcat) 설정을 자동으로 구성한다.

또한, ```implementation 'org.springframework.boot:spring-boot-starter-jdbc’```와 같은 Start가 존재할 때,  
데이터베이스 연결이 필요하다고 추측한 뒤, JDBC 설정을 자동으로 구성한다.

<br>

### 🔸 프로덕션급 어플리케이션의 손쉬운 빌드

개발한 빌드 결과물을 직접 ```WAR``` 파일 형태로 WAS에 올릴 필요가 없다.

> ❓WAS (Web Application Server)  
> 
> Java 기반의 웹 어플리케이션을 배포하는 일반적인 방식은 개발자가 구현한 코드를 WAR(Web application ARchive)파일 형태로 빌드한 후에 WAS라는 서버에 배포해서 해당 어플리케이션을 실행하는 것이다.  
> 
> 즉, WAS는 구현된 코드를 빌드해서 나온 결과물을 실제 웹 어플리케이션으로 실행되게 해주는 서버이다.  
> 
> Java에서는 WAS를 서블릿 컨테이너라고도 부르며, 대표적인 WAS에는 Tomcat이 있다.

<br>

```bootjar``` 명령을 실행하게 되면 ```jar``` 파일이 생성되며, 즉시 시작 가능한 어플리케이션 실행 파일로 사용된다.

<br>

### 🔸 내장된 WAS를 통한 손쉬운 배포

Spring Boot는 Apache Tomcat이라는 WAS를 내장하고 있어 별도의 WAS를 구축할 필요가 없으며,  
빌드된 ```jar```파일을 이용해 ```java -jar <파일명>.jar``` 명령어 한 줄로 서비스 가능한 웹 어플리케이션을 실행할 수 있다.

<br><br>

***

_2022.10.12. Update_