# JPA 기반 데이터 액세스 계층

## 🔸 JPA(Java Persistence API / Jakarta Persistence)

JPA는 Java에서 사용하는 ORM 기술의 표준 사양(Specification)이고,  

JPA 표준 사향을 구현한 구현체로는 Hibernate ORM, EclipseLink, DataNucleus 등이 있다.

> ORM은 객체와 데이터베이스 테이블의 매핑을 통해 Entity 클래스 객체 안에 포함된 정보를 테이블에 저장하는 기술이다.

<br>

### JPA의 위치

<img src = "https://velog.velcdn.com/images/rmswjdtn/post/a68ce1d0-d68f-4a64-82d8-ee08e113a09f/image.png" width = 500/>

<br>

JPA는 데이터 액세스 계층의 상단에 위치한다.

데이터 저장 ・ 조회 등의 작업은 JPA를 거쳐 Hibernate ORM을 통해 이루어지며,  
Hibernate ORM은 내부적으로 JDBC API를 이용하여 DB에 접근하게 된다.

<br>

### 영속성 컨텍스트 (Persistence Context)

JPA에서 테이블과 매핑되는 Entity 객체 정보를 보관하는 장소이며,  
**1차 캐시** 영역과 **쓰기 지연 SQL 저장소** 영역으로 구성되어 있다.  

JPA API 중 Entity 정보를 컨텍스트에 저장하는 API를 사용하면 1차 캐시에 Entity 정보가 저장된다.

<br>

```java
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
}
```
▲ _JPA API를 사용하기 위한 build.gradle 설정_

<br>

```yml
spring:
    jpa:
        hibernate:
            ddl-auto: create
        show-sql: true
```
▲ _JPA 사용을 위한 application.yml 설정_

- ```ddl-auto: create``` : Entity 클래스와 매핑되는 데이터베이스 테이블을 자동으로 생성해준다.
- ```show-sql: true``` : JPA API를 통해 실행되는 SQL 쿼리를 로그로 출력해준다.

<br>

**🛠 영속성 컨텍스트에 Entity 저장**

```java
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long memberId;

    private String email;

    public Member(String email) {
        this.email = email;
    }
}
```
▲ _영속성 컨텍스트에 저장할 Entity_

- ```@Entity```, ```@Id``` : 해당 어노테이션들을 통해 JPA에서 Entity 클래스로 인식하게 된다.
- ```@GeneratedValue``` : DB 테이블에서 기본키가 되는 식별자를 자동으로 설정해준다.

<br>

```java
@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            example();
        };
    }

    private void example() {
        tx.begin();
        Member member = new Member("hongjjwan@gmail.com");

        em.persist(member);
        tx.commit();

        Member resultMember1 = em.find(Member.class, 1L);
        System.out.println("Id: " + resultMember1.getMemberId() +
                    ", email: " + resultMember1.getEmail());

        Member resultMember2 = em.find(Member.class, 2L);
        System.out.println("Id: " + resultMember2.getMemberId() +
                    ", email: " + resultMember2.getEmail());

        System.out.println(resultMember2 == null);
    }
}
```
▲ _샘플 코드 실행을 위한 Configuration 클래스_

<br>

- ```@Configuration``` : 설정 파일에 작성   
  Bean을 등록할 때 싱글톤이 되도록 보장해준다. (만들어진 객체는 다시 생성하지 않는다.)  
  Spring Container에서 Bean을 관리할 수 있게 된다.

- ```EntityManager``` : 영속성 컨텍스트를 관리하는 클래스  
  ```EntityManagerFactory```의 ```createEntityManager()``` 메서드를 통해 EntityManager 객체를 얻을 수 있고,  
  해당 객체를 Spring으로부터 DI 받을 수 있다.

- ```EntityTransaction``` : EntityManager의 ```getTransaction()``` 메서드를 통해 객체를 얻을 수 있다.  
  JPA는 Transaction 객체를 기준으로 DB 테이블에 데이터를 저장한다.

<br>

**💡 EntityManager의 메서드**

- ```persist()``` : 영속성 컨텍스트의 **1차 캐시**에 객체 정보를 저장한다. (실제 테이블에는 X)

- ```find(클래스타입, 식별자)``` : 영속성 컨텍스트의 1차 캐시에 객체가 잘 저장되었는지 조회한다.  
  resultMember1에서는 처음에 ```persist()```를 통해 1차 캐시에 저장한 Entity 객체를 조회하고,  
  resultMember2에서는 1차 캐시에서 조회된 값이 없기 때문에 테이블에 직접 SELECT 쿼리를 전송한다.

- ```flush()``` : 영속성 컨텍스트의 변경 내용을 DB에 반영한다.

<br>

**💡 EntityTransaction의 메서드**

- ```begin()``` : Transaction을 시작하기 위해 호출하는 메서드

- ```commit()``` : 해당 메서드 호출 시점에 **영속성 컨텍스트에 저장된 Entity 객체를 DB 테이블에 저장**한다.  
  해당 메서드가 호출되면 JPA 내부적으로 ```em.flush()```를 호출한다.
  (쓰기 지연 SQL 저장소에 저장된 쿼리문을 실행한다.)

<br><br>

**🛠 영속성 컨텍스트와 DB 테이블의 Entity 정보 업데이트**

```java
private void example() {
   tx.begin();
   em.persist(new Member("hongjjwan@gmail.com"));
   tx.commit();


   tx.begin();
   Member member1 = em.find(Member.class, 1L);
   member1.setEmail("hongjwan@naver.com");
   tx.commit();
}
```

```persist```와 ```commit``` 메서드를 통해 영속성 컨텍스트와 DB에 정보를 저장한 후,  

```find``` 메서드를 통해 **영속성 컨텍스트의 1차 캐시**에 저장된 객체를 조회한다.

이후 setter로 정보를 업데이트한 뒤 ```commit```을 통해 쓰기 지연 SQL 저장소에 등록된 UPDATE 쿼리를 실행한다.

<br><br>

**🛠 영속성 컨텍스트와 DB 테이블의 Entity 정보 삭제**

```java
private void example() {
    tx.begin();
    em.persist(new Member("hongjwan@naver.com"));
    tx.commit();

    tx.begin();
    Member member = em.find(Member.class, 1L);
    em.remove(member);
    tx.commit();
}
```

```remove()``` 메서드를 통해 영속성 컨텍스트의 1차 캐시에 있는 Entity를 제거하고,  
```commit()``` 메서드를 통해 쓰기 지연 SQL 저장소에 등록된 DELETE 쿼리를 실행한다.

> **💡 쓰기 지연 SQL 저장소**  
> 
> 현재 변경된 1차 캐시의 Entity와 이전에 등록한 스냅샷을 비교한 후,  
> 변경된 값이 있으면 UPDATE, 값이 없으면 DELETE 쿼리를 실행한다.

<br><br>

## 🔸 JPA Entity 매핑 / Entity 간의 연관 관계 매핑




<br><br>

***

_2022.11.02. Update_