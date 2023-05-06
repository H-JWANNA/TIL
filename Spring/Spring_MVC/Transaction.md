# 트랜잭션 (Transaction)

여러 개의 작업을 그룹으로 묶어서 처리하는 실행 유닛을 말하며,

그룹 내의 작업이 **전부 성공하거나 전부 실패하는 (All or Nothing)** 경우로 나뉜다.

이로 인해 어플리케이션의 신뢰성과 데이터의 무결성을 보장하는 역할을 하게 된다.

<br>

### ACID 원칙

ACID는 트랜잭션의 안전성을 보장하기 위해 필요한 4가지 성질이다.

<br>

**🔸 Atomicity (원자성)**

작업을 더 이상 쪼갤 수 없음을 의미한다.

하나의 트랜잭션 내에서는 모든 연산이 성공하거나 모두 실패해야 한다는 성질을 말한다. (All or Nothing)

> A가 B에게 계좌이체를 하는 상황에서  
> A 계좌에서는 출금이 되었지만(성공) B 계좌에는 입금이 되지 않았을 때(실패) **모든 연산이 실패**로 돌아가게 된다.
> 
> 출금과 입금은 서로 쪼개어 처리할 수 없는 작업이다.

<br>

**🔸 Consistency (일관성)**

트랜잭션이 에러없이 성공적으로 종료될 경우, 비즈니스 로직에서 의도하는대로 일관성있게 저장되거나 변경되는 것을 의미한다.

즉, 하나의 트랜잭션 전후에 데이터베이스의 일관된 상태가 유지되어야 하는 성질이며,  
트랜잭션이 일어난 이후에도 데이터베이스의 제약이나 규칙을 만족해야한다는 의미이다.

> 새로운 고객 정보를 입력할 때,  
> ```name```이 필수로 입력되어야 하는 제약이 있으면  
> ```name```이 없는 고객을 입력하거나 기존 고객의 ```name```을 삭제하는 쿼리는 실패하게 된다.
>
> 또는 3잔의 커피를 주문하면 스탬프 값이 3 증가해야 하는데, 다른 숫자만큼 증가하면 일관성에 위배되는 것이다.

<br>

**🔸 Isolation (고립성)**

모든 트랜잭션은 다른 트랜잭션으로부터 독립적이여야 한다는 성질이다.

각각의 트랜잭션은 독립적이기 때문에, 다른 트랜잭션을 확인하거나 영향을 줄 수 없다.

> 1만원이 있는 계좌에서 B와 C에 6천원씩 동시에 송금할 경우  
> B에 송금을 한 후 이어서 C에 송금을 해야한다.  
> 
> 동시에 송금되어 마이너스 통장이 되어서는 안된다.

<br>

**🔸 Durability (지속성)**

성공한 트랜잭션에 대한 로그는 영구적으로 기록되어 남아야한다는 성질이다.

> 계좌 이체를 성공적으로 실행한 뒤, DB에 오류가 발생해 종료되었더라도  
> 
> 해당 이체 내역은 물리적인 저장소에 저장되어 기록으로 남아야한다.

<br><br>

### 트랜잭션 커밋(Commit) & 롤백(Rollback)

<br>

**🔸 커밋(commit)**

커밋은 변경된 모든 작업 내용을 최종적으로 DB에 반영하고 영구적으로 저장하는 명령어이다.

커밋 명령을 수행하게 된다면, 하나의 트랜잭션 과정은 종료된다.

<br>

**🔸 롤백(rollback)**

롤백은 작업 중 문제가 발생했을 때, 트랜잭션 내에서 수행된 작업들을 취소하는 명령어이다.

롤백 명령을 수행하게 된다면, 트랜잭션 시작 이전의 상태로 돌아간다.

<br>

***

<br>

## 선언형 트랜잭션

Spring에서 선언형 방식의 트랜잭션을 적용하는 방법은 크게 2가지로 나눌 수 있다.  
- 작성된 비즈니스 로직에 어노테이션을 추가하는 방식
- AOP 방식을 이용해 트랜잭션 코드를 비즈니스 로직에서 분리하는 방식

<br>

### 1. 어노테이션 방식의 트랜잭션 적용

어노테이션 방식의 트랜잭션을 적용하기 위해서는 ```@Transactional```을 명시해주면 된다.

<br>

**🔸 클래스 레벨에 적용**

```java
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService { ... }
```

<br>

위와 같이 클래스 레벨에 어노테이션을 추가하면,  

해당 클래스에서 Repository의 기능을 이용하는 모든 메서드에 트랜잭션이 적용된다.

<br>

> 체크 예외(Checked Exception)은 ```@Transactional```만 추가해서는 rollback이 일어나지 않는다.
> 
> 말 그대로 체크를 해야하는 예외이기 때문에, 아래와 같이 체크 예외를 직접 지정하거나 언체크 예외로 감싸야한다.  
> ```@Transactional(rollbackFor = {SQLException.class, DataFormatException.class})```
> 
> 아니라면 catch한 후에 적절한 예외 전략을 고민해야한다.

<br>

**🔸 메서드 레벨에 적용**

```java
@Service
@Transactional
public class MemberService {

    @Transactional(readOnly = true)
    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }
}
```

<br>

commit 호출이 일어나면, 영속성 컨텍스트가 flush되는데,  

위와 같이 메서드 레벨에 ```@Transactional(readOnly = true)``` 어노테이션을 추가하면,  

JPA 내부적으로 영속성 컨텍스트를 flush하지 않고, 읽기 전용일 경우 변경 감지를 위한 스냅샷 생성도 진행하지 않는다.

**즉, 불필요한 추가 동작을 줄일 수 있어서 조회 메서드에는 ```readOnly = true``` 속성을 지정하여 성능 최적화를 하는 것이 좋다.**

<br>

**💡 트랜잭션 전파 (Transaction Propagation)**

```@Transactional```이 적용된 메서드에서 내부에 다른 메서드를 호출할 경우  

호출된 메서드에 ```@Transactional(propagation = Propagation.REQUIRED)```처럼 속성을 적용할 수 있다.  

- ```Propagation.REQUIRED```  
  진행중인 트랜잭션이 존재하면 참여하고, 아니라면 새 트랜잭션 생성

- ```Propagation.REQUIRES_NEW```  
  진행중인 트랜잭션과 무관하게 새로운 트랜잭션 생성  
  기존에 진행중이던 트랜잭션은 새로운 트랜잭션이 종료할 때까지 중지

- ```Propagation.MANDATORY```  
  진행중인 트랜잭션이 존재하지 않으면 예외를 발생시킨다.

- ```Propagation.NOT_SUPPORTED```
  트랜잭션을 필요로 하지 않음을 의미한다.  
  진행중인 트랜잭션이 존재하면 진행중인 트랜잭션은 중지되며, 메서드 실행이 종료되면 트랜잭션을 계속 진행

- ```Propagation.NEVER```  
  트랜잭션을 필요로 하지 않음을 의미한다. 진행 중인 트랜잭션이 존재할 경우에는 예외를 발생시킨다.

<br>

**💡 트랜잭션 격리 레벨 (Isolation Level)**

ACID 원칙에 따라 트랜잭션은 독립적으로 실행돼야한다는 격리성이 보장되어야 한다.

Spring에서는 트랜잭션에 ```isolation``` 속성을 적용해 격리성을 조정할 수 있다.

- ```Isolation.READ_UNCOMMITTED```  
  다른 트랜잭션에서 커밋하지 않은 데이터를 읽는 것을 허용

- ```Isolation.READ_COMMITTED```  
  다른 트랜잭션에 의해 커밋된 데이터를 읽는 것을 허용 (Oracle의 Default)

- ```Isolation.REPEATABLE_READ```  
  트랜잭션 내에서 한 번 조회한 데이터를 반복해서 조회해도 같은 데이터가 조회되도록 한다. (MySQL의 Default)  
  트랜잭션 조회 시점에 이미 커밋되어 있던 내용에 대해서만 조회한다.

- ```Isolation.SERIALIZABLE```  
  동일한 데이터에 대해서 동시에 2개 이상의 트랜잭션이 수행되지 못하도록 한다. (InnoDB의 Default)

<br>

### 📋 [***트랜잭션 격리 레벨에 대한 자세한 정보***](../../Database/Transaction_Isolation_Level.md)

<br><br>

### 2. AOP 방식의 트랜잭션 적용

```java
@Configuration
public class TxConfig {
    private final TransactionManager transactionManager;

    public TxConfig(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource txAttributeSource =
                                    new NameMatchTransactionAttributeSource();

        RuleBasedTransactionAttribute txAttribute =
                                        new RuleBasedTransactionAttribute();
        txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        RuleBasedTransactionAttribute txFindAttribute =
                                        new RuleBasedTransactionAttribute();
        txFindAttribute.setPropagationBehavior(
                                        TransactionDefinition.PROPAGATION_REQUIRED);
        txFindAttribute.setReadOnly(true);

        Map<String, TransactionAttribute> txMethods = new HashMap<>();
        txMethods.put("find*", txFindAttribute);
        txMethods.put("*", txAttribute);

        txAttributeSource.setNameMap(txMethods);

        return new TransactionInterceptor(transactionManager, txAttributeSource);
    }

    @Bean
    public Advisor txAdvisor() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* package.coffee.service." +
                "CoffeeService.*(..))");

        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
```

<br>

**💡 AOP 방식의 트랜잭션 적용 순서**

1. AOP 방식으로 트랜잭션을 적용하기 위한 Configuration 클래스 정의

2. ```TransactionManager``` DI  
   - TransactionManager 객체를 DI 받는다.
   
```java
private final TransactionManager transactionManager;

public TxConfig(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
}
```

<br>

3. 트랜잭션 어드바이스용 ```TransactionInterceptor``` Bean 등록  
   - 위 클래스를 통해 대상 클래스 또는 인터페이스에 트랜잭션 경계를 설정하고 적용할 수 있다.  

- **트랜잭션 Attribute 지정**   
  트랜잭션 Attribute는 메서드 이름 패턴에 따라 구분해서 적용하기 때문에 아래와 같이 설정할 수 있다.

```java
// 조회 메서드를 제외한 공통 트랜잭션 Attribute
RuleBasedTransactionAttribute txAttribute =
                                new RuleBasedTransactionAttribute();

txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);


// 조회 메서드에 적용하기 위한 트랜잭션 Attribute (ReadOnly = true)
RuleBasedTransactionAttribute txFindAttribute =
                                new RuleBasedTransactionAttribute();

txFindAttribute.setPropagationBehavior(
                                TransactionDefinition.PROPAGATION_REQUIRED);

txFindAttribute.setReadOnly(true);
```

<br>

- 트랜잭션을 적용할 메서드에 트랜잭션 Attribute 매핑  
  
```java
Map<String, TransactionAttribute> txMethods = new HashMap<>();
txMethods.put("find*", txFindAttribute);
txMethods.put("*", txAttribute);

txAttributeSource.setNameMap(txMethods);
```

설정한 트랜잭션 Attribute를 Map에 추가하는데, Map의 key를 ```메서드 이름 패턴```으로 지정해서 추가한다.  

그 후, Map 객체를 ```txAttributeSource.setNameMap(txMethods);```과 같이 넘겨준다.


<br>

4. Advisor Bean 등록  
   
- 포인트컷 지정

```java
AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

pointcut.setExpression("execution(* package.coffee.service." +
        "CoffeeService.*(..))");
```

트랜잭션 어드바이스인 ```TransactionInterceptor```를 타겟 클래스에 적용하기 위해 포인트컷을 지정한다.

```AspectJExpressionPointcut``` 객체를 생성한 후, 포인트컷 표현식으로 타겟 클래스를 지정하면 된다.

<br>

- Advisor 객체 생성

```java
@Bean
public Advisor txAdvisor() {
    ...
    return new DefaultPointcutAdvisor(pointcut, txAdvice());
}
```

```DefaultPointcutAdvisor```의 생성자 파라미터로 포인트컷과 어드바이스를 전달해준다.

<br><br>

***

_2023.04.03. Modified_

_2022.11.06. Update_