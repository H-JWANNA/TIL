# 관계형 데이터베이스 (Relational Database, RDB)

관계형 데이터 모델을 기초로 두고 모든 데이터를 2차원 테이블 형태로 표현하는 데이터베이스

구성된 테이블이 다른 테이블들과 관게를 맺고 모여있는 집합체이다.

<br>

### **💡 데이터베이스가 필요한 이유**
- In-Memory (Client)  
  종료하게 되면 데이터가 증발한다.
- File I/O (Server)  
  원하는 데이터만 가져올 수 없고 항상 모든 데이터를 가져온 뒤 서버에서 필터링을 해야한다.
- Database  
  필터링 외에도 File I/O로 구현이 힘든 관리를 위한 여러 기능을 가진 데이터에 특화된 서버

<br>

### **💡 관계형 데이터베이스의 구조**

- 엔티티(Entity)는 고유한 정보의 단위이다.  
  관계형 데이터베이스에서는 주로 테이블로 표시할 수 있다.
  
- 필드(Field)는 해당 엔티티의 특성을 설명한다.  
  테이블에서는 열(Column)이 필드에 해당된다.  

- 레코드(Record)는 테이블에 저장된 데이터이다.  
  테이블에서는 행(Row)이 레코드에 해당된다.

- 키(Key)는 테이블의 각 레코드를 구분할 수 있는 값이다.  
  레코드마다 고유한 값을 가지며 기본키(Primary Key), 외래키(Foreign Key) 등이 있다.

<br>

### **💡 관계형 DB를 사용하는 Case**

1. 데이터베이스의 ACID 성질을 준수해야하는 경우  
   SQL을 사용하면 DB와 상호 작용하는 방식을 규정할 수 있기 때문에  
   데이터 처리 시에 발생하는 예외 상황을 줄이고, 무결성을 보호할 수 있다.
   > 전자 상거래와 같은 금융 서비스를 위한 소프트웨어 개발은 반드시 관계형 DB를 사용한다.

2. 소프트웨어에 사용되는 데이터가 구조적이고 일관적인 경우  
   프로젝트의 규모가 많은 서버를 필요로 하지 않고 일관된 데이터를 사용하는 경우에 유리하다.

<br>

***

<br>

## ACID

ACID는 트랜잭션의 안전성을 보장하기 위해 필요한 4가지 성질이다.

<br>

### 트랜잭션
트랜잭션(transaction)은 여러 개의 작업을 하나로 묶은 실행 유닛이다.

만약 트랜잭션 내에 속한 여러 작업 중 하나라도 실패하면, 전체가 실패한 것으로 간주한다.

따라서, 트랜잭션은 미완료된 작업없이 모든 작업을 성공해야 한다.

<br>

### ACID의 성질

**🔸 Atomicity (원자성)**

하나의 트랜젝션 내에서는 모든 연산이 성공하거나 모두 실패해야 한다는 성질

> A가 B에게 계좌이체를 하는 상황에서  
> A 계좌에서는 출금이 되었지만(성공) B 계좌에는 입금이 되지 않았을 때(실패) **모든 연산이 실패**로 돌아가게 된다.

<br>

**🔸 Consistency (일관성)**

하나의 트랜젝션 전후에 데이터베이스의 일관된 상태가 유지되어야 하는 성질

트랜젝션이 일어난 이후에도 데이터베이스의 제약이나 규칙을 만족해야한다는 의미이다.

> 새로운 고객 정보를 입력할 때,  
> ```name```이 필수로 입력되어야 하는 제약이 있으면  
> ```name```이 없는 고객을 입력하거나 기존 고객의 ```name```을 삭제하는 쿼리는 실패하게 된다.

<br>

**🔸 Isolation (고립성)**

모든 트랜젝션은 다른 트랜젝션으로부터 독립적이여야 한다는 성질

각각의 트랜젝션은 독립적이기 때문에, 다른 트랜젝션을 확인하거나 영향을 줄 수 없다.

> 1만원이 있는 계좌에서 B와 C에 6천원씩 동시에 송금할 경우  
> B에 송금을 한 후 이어서 C에 송금을 해야한다.  
> 
> 동시에 송금되어 마이너스 통장이 되어서는 안된다.

<br>

**🔸 Durability (지속성)**

성공한 트랜젝션에 대한 로그는 영구적으로 기록되어 남아야한다는 성질

> 계좌 이체를 성공적으로 실행한 뒤, DB에 오류가 발생해 종료되었더라도  
> 해당 이체 내역은 기록으로 남아야한다.

<br><br>

***

_2022.10.07. Modified_

_2022.10.06. Update_