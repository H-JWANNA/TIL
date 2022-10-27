# 데이터 액세스 계층

데이터 액세스 계층은 서비스 계층에서 비즈니스 로직을 통해 처리된 데이터를 **데이터베이스에 저장**하고,  
비즈니스 로직을 처리하기 위해 데이터베이스에서 **데이터를 조회**하는 역할을 한다.

<br>

## JDBC 기반 데이터 액세스 계층

### 🔸 JDBC(Java DataBase Connectivity)

JDBC는 Java 기반 어플리케이션 코드 레벨에서 사용하는 데이터를 DB에 저장 및 업데이트 하거나 저장된 데이터를 Java 코드 레벨에서 사용할 수 있도록 Java에서 제공하는 표준 사양(Sepcification)이다.

즉, **자바에서 DB 프로그래밍을 하기 위해 사용되는 API**를 말한다.

> Spring에서 사용하는 데이터베이스 액세스 기술에는  
> mybatis, Spring JDBC, Spring Data JDBC, JPA, Spring Data JPA 등이 있다.

<br>

### JDBC 동작 흐름

<img src = "https://cf2.ppt-online.org/files2/slide/p/pze08XcAdjx12LCyl9rioJIwMFhBs5bfvHENkW/slide-10.jpg" width = "80%"/>

Java 애플리케이션 내에서 JDBC API를 사용하여 데이터베이스에 액세스하는 단순한 구조이다.

> **❓ JDBC 드라이버 (JDBC Driver)**
> 
> 데이터베이스와의 통신을 담당하는 인터페이스  
> 
> DBMS에 따라 DB를 다루는 방식이 다르다면, 사용자는 각 DBMS의 방식을 전부 알 수 없다.  
> 따라서, JDBC를 통해 추상화된 인터페이스를 제공하기만 하고,  
> DB의 종류에 상관없이 각 JDBC Driver를 통해 특정 DBMS를 사용할 수 있다.  

<br>

### JDBC API의 흐름

1. **JDBC 드라이버 로딩**  
   DriverManager 클래스를 통해서 사용하고자 하는 JDBC 드라이버 로딩

2. **Connection 객체 생성**  
   JDBC 드라이버가 정상적으로 로딩되면 DriverManager를 통해 DB와 연결되는 Connection 객체 생성

3. **Statement 객체 생성**  
   작성된 SQL 쿼리문을 실행하기 위한 Statement 객체 생성

4. **Query 실행**  
   생성된 Statement 객체를 이용해서 입력한 SQL 쿼리 실행

5. **ResultSet 객체로부터 데이터 조회**  
   실행된 SQL 쿼리문에 대한 결과 데이터 셋을 조회

6. **실행의 역순으로 Close**  
   ResultSet, Statement, Connection의 순으로 객체를 Close

<br>

### Connection Pool

<img src = "https://cf2.ppt-online.org/files2/slide/p/pze08XcAdjx12LCyl9rioJIwMFhBs5bfvHENkW/slide-37.jpg" width = "80%">

<br>

Connection Pool은 Connection 객체를 미리 만들어서 보관하고, 필요할 때 제공하는 **Connection Manager**이다.

어플리케이션 로딩 시점에 Connection 객체를 미리 생성해두고,  
DB 연결이 필요할 경우 미리 만들어준 Connection 객체를 사용함으로써 어플리케이션 성능을 향상 시킬 수 있다.

> Spring Boot에서 기존에는 Apache Commons DBCP를 사용했지만,  
> 최근에는 성능면에서 이점을 가진 **HikariCP**가 기본 DBCP로 사용된다.

<br>

***

<br>

### 🔸 객체(Object) 중심 기술

기존에 사용하던 방식은 SQL 중심 기술이다.

```java
Member member = this.jdbcTemplate.queryForObject(
			    "select * from member where member_id=?", 1, Member.class);

```
▲ _Spring JDBC의 JdbcTemplate_

<br>

최근 Java에서는 SQL 중심의 기술에서 객체(Object) 중심의 기술로 지속적으로 이전을 하는 추세이다.

<br>

**💡 ORM (Object-Relational Mappint)**

ORM은 객체 중심의 데이터 액세스 기술을 말하며,

객체 중심 기술은 DB에 접근하기 위해 위 코드처럼 SQL 쿼리문을 직접 작성하기 보다는  
Java 객체를 어플리케이션 내부에서 SQL 쿼리문으로 자동 변환한 후에 DB 테이블에 접근하는 방식이다.

> 대표적인 Java ORM 기술로는 JPA(Java Persistence API)가 있다.

<br>

***

<br>

### 🔸 Spring Data JDBC

Spring Data JDBC는 ORM 기술을 사용하거, JPA의 기술적 복잡도를 낮춘 기술이라 비교적 심플하다.

<br>

Spring Data JDBC를 사용하기 위해서는 의존 라이브러리를 추가해야한다.

```java
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
	runtimeOnly 'com.h2database:h2'
}
```

<br>

또한 인메모리(In-memory) DB인 H2를 사용하기 위해 해당 의존 라이브러리도 추가한다.

<br>

**💡 인메모리(In-memory) DB**

인메모리 DB는 메모리 안에 데이터를 저장하는 데이터베이스를 말한다.

어플리케이션이 실행 될 동안만 데이터를 저장하고 중지시키면 사라지는 휘발성 메모리이다.

주로 로컬 개발 환경에서 테스트를 진행할 때,  
테스트에 필요한 데이터 외의 쓸데없는 데이터가 없이 정확한 테스트를 하기 위해 사용한다.

<br><br>

***

_2022.10.27. Update_