# SQL

SQL은 Structured Query Language의 약자로, 구조화된 Query 언어이다.

따라서, SQL을 사용하기 위해서는 데이터의 구조가 고정되어 있어야 한다.

SQL은 데이터베이스 언어로, MySQL, Oracle, SQLite, PostgreSQL 등 다양한 관계형 데이터베이스에서 사용할 수 있다.

<br>

### Query

Query는 '질의문'이라는 뜻을 가지고 있으며, 검색할 때 입력하는 검색어가 쿼리의 일종이다.

데이터베이스에 쿼리를 보내 저장되어 있는 데이터를 필터하여 가져오거나 삽입할 수 있다.

<br>

## SQL Basics

<br>

### 📌 Database Command

<br>

**🔸 데이터베이스 생성**

```sql
CREATE DATABASE database_name
```

<br>

**🔸 데이터베이스 삭제**

```sql
DROP DATABASE database_name
```

<br>

**🔸 데이터베이스 수정**

```sql
ALTER DATABASE database_name CHARACTER SET = charset_name
ALTER DATABASE database_name COLLATE = collation_name
```

> 대표적인 CHARACTER SET은 아래와 같다.  
> 1. utf8 : UTF-8 유니코드를 지원하는 문자셋 (1~3 byte)
> 2. euckr : 한글을 지원하는 문자셋 (1~2 byte)
> 
> 대표적인 COLLATE는 아래와 같다.  
> 1. utf8_bin
> 2. utf8_general_ci  →  기본 설정값
> 3. euckr_bin
> 4. euckr_korean_ci
> 
> 콜레이션(Collation)은 DB에서 검색이나 정렬 같은 작업을 할 때 사용하는 비교를 위한 규칙의 집합을 말한다.  
> 또한, 위의 COLLATE에서 ```ci```는 case-insensitive의 약자이며, 대소문자를 구분하지 않는다는 의미이다.

<br>

**🔸 데이터베이스 사용**

```sql
USE database_name
```

테이블 생성, 수정, 삭제 등의 작업을 위해 데이터베이스 사용 명령을 먼저 해야한다.

<br>

**🔸 테이블 생성**

```sql
CREATE TABLE table_name (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    email varchar(255)
)
```

필드 이름이 각각 ```id```, ```name```, ```email```이고,   
필드 타입이 숫자와 문자열(최대 255개 문자)이며,  
```id```의 속성은 Primary Key이면서 자동 증가되도록 설정한 테이블 생성

<br>

**🔸 테이블 삭제**

```sql
DELETE FROM table_name

-- 조건을 붙이면 해당 행만 삭제
WHERE sheet = 'D11'

-- 커밋을 하기 전이라면 롤백할 수 있다.
ROLLBACK
COMMIT
```
▲ _테이블의 데이터는 사라지지만, 해당 인덱스와 구조는 남아있다._

<br>

```sql
TRUNCATE TABLE table_name

-- 자동 커밋이 되기 때문에 롤백이 불가능하다.
```
▲ _테이블의 데이터와 해당 인덱스가 삭제되지만, 구조는 남아있다._

<br>

```sql
DROP TABLE table_name

-- 자동 커밋이 되기 때문에 롤백이 불가능하다.
```
▲ _테이블의 존재 자체를 지워버린다._

<img src = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile6.uf.tistory.com%2Fimage%2F99A5253F5DC1723107442D" width = "90%" />

▲ _Difference of DELETE / TRUNCATE / DROP_

<br>

**🔸 테이블 수정**

```sql
-- 새로운 필드 추가
ALTER TABLE table_name ADD field_name field_type

-- 기존 필드 삭제
ALTER TABLE table_name DROP field_name

-- 필드 타입 변경
ALTER TABLE table_name MODIFY COLUMN field_name field_type
```

<br>

**🔸 테이블 정보 확인**

```sql
DESCRIBE table_name
```

<br>

▼ _출력_

```sql 
mysql> describe user;
+-------+--------------+------+-----+---------+----------------+
| Field | Type         | Null | Key | Default | Extra          |
+-------+--------------+------+-----+---------+----------------+
| id    | int          | NO   | PRI | NULL    | auto_increment |
| name  | varchar(255) | YES  |     | NULL    |                |
| email | varchar(255) | YES  |     | NULL    |                |
+-------+--------------+------+-----+---------+----------------+
3 rows in set (0.00 sec)
```

<br>

### 📌 SQL Command

<br>

**🔸 SELECT**

데이터셋에 포함될 특성을 선택

```sql
SELECT column_name

-- 문자열
SELECT 'hello world'

-- 숫자
SELECT 2

-- 연산
SELECT 15 + 3
```

<br>

**🔸 FROM, AS**

FROM : 결과를 도출해낼 테이블을 선택  
AS : 별칭 설정

```sql
SELECT *
FROM table_name AS db_name
```

<br>

**🔸 WHERE**

필터 역할을 하는 쿼리문

```sql
SELECT *
FROM table_name

-- 비교 연산자 사용 가능
WHERE gender = 'Male'

-- 사이값 찾기
WHERE salary BETWEEN 300 AND 500

-- 비슷한 값을 찾을 때 (% 또는 _ 사용 /feat.정규식)
WHERE name LIKE "홍%"
WHERE name LIKE "홍_완"
WHERE name LIKE "[가-힇]%"

-- 일치하는 값 여러개를 찾을 때
WHERE salary IN (200, 300, 400)

-- 값이 없는 경우를 제외하고 찾을 때
WHERE company IS NOT NULL
```

<br>

> 💡 일반적으로 아래와 같이 사용한다.  
> ```sql
> SELECT 필드명 FROM 테이블명  
> WHERE 조건
> ```

<br>

**🔸 ORDER BY**

리턴하는 데이터의 정렬 기준

```sql
SELECT *
FROM table_name

-- 오름차순 (ASC 생략 가능)
ORDER BY column_name ASC

-- 내림차순
ORDER BY column_name DESC
```

<br>

**🔸 GROUP BY**

데이터들을 원하는 그룹으로 나누어 결과를 출력

```sql
SELECT *
FROM table_name
GROUP BY column_name
```

<br>

**🔸 HAVING**

```GROUP BY```절의 조건문으로 사용하며, ```WHERE```과 비슷한 기능을 한다.

```sql
SELECT *
FROM table_name
GROUP BY column_name
HAVING salary >= 300
```

<br>

**🔸 INSERT INTO - VALUES**

테이블에 새로운 레코드를 추가한다.

```sql
-- column_name은 생략 가능
INESRT INTO table_name (column_name_1, column_name_2, …)
VALUES (data_1, data_2, …)
```

<br>

**🔸 UPDATE - SET**

테이블 내의 레코드를 수정한다.

```sql
UPDATE table_name
SET column_1 = data_1, column_2 = data_2, …

-- 조건을 붙이지 않으면 모든 레코드 값이 변경된다.
WHERE column_3 = data_3
```

<br>

**🔸 LIMIT**

출력할 데이터 갯수 제한 (선택적 사용)  
단, 가장 마지막에 선언해야한다.

```sql
SELECT *
FROM table_name
LIMIT 10
```

<br>

**🔸 DISTINCT**

중복을 제거하기 위한 명령어  
특정 필드의 중복을 제거한 값을 출력하도록 한다.

```sql
SELECT DISTINCT column_name
FROM table_name
```

<br>

<br>

**🔸 INNER JOIN - ON**

둘 이상의 테이블의 서로 공통된 부분을 기준으로 연결하는 명령어  
```INNER JOIN``` 또는 ```JOIN```으로 실행한다.

```sql
SELECT *
FROM table_name_1
JOIN table_name_2 ON table_name_1.column_1 = table_name_2.column_A
```

<br>

<img src = "https://www.dofactory.com/img/sql/sql-join.png" width ="50%" />

<br>

**🔸 OUTER JOIN - ON**

둘 이상의 테이블에서 한 쪽만 데이터가 있어도 연결하는 명령어  
```OUTER```는 생략 가능하다.

```sql
SELECT *
FROM table_name

-- 왼쪽 테이블의 모든 값을 기준으로 조인
LEFT OUTER JOIN table_name_2 ON table_name_1.column_1 = table_name_2.column_A

-- 오른쪽 테이블의 모든 값을 기준으로 조인
RIGHT OUTER JOIN table_name_2 ON table_name_1.column_1 = table_name_2.column_A

-- 양쪽 테이블의 모든 값을 기준으로 조인
FULL OUTER JOIN table_name_2 ON table_name_1.column_1 = table_name_2.column_A
```

<br>

<img src = "https://www.dofactory.com/img/sql/sql-joins.png" width = "50%" />

<br>

### 📋 [***SQL Server Functions***](https://www.w3schools.com/sql/sql_ref_sqlserver.asp)

<br><br>

## SQL의 종류

**🔸 DDL (Data Definition Language)**

DDL은 데이터를 정의할 때 사용하는 언어이다. 

```CREATE```, ```DROP``` 등 DB의 테이블과 같은 오브젝트를 정의할 때 사용한다.

<br>

**🔸 DML (Data Manipulation Languate)**

DML은 DB에 데이터를 저장할 때 사용하는 언어이다.  

```INSERT INTO```, ```DELETE```, ```UPDATE``` 등 데이터를 추가, 삭제, 수정할 때 사용한다.

<br>

**🔸 DCL (Data Control Languate)**

DCL은 유저의 데이터베이스 접근 권한과 관련된 문법이다.  

권한을 주는 ```GRANT```, 권한을 뺏는 ```REVOKE``` 등이 DCL에 포함된다.

<br>

**🔸 DQL (Data Query Languate)**

DQL은 정해진 스키마 내에서 쿼리할 수 있는 언어이다.  

```SELECT```가 DQL에 해당하며, DQL은 DML의 일부로 취급되기도 한다.

<br>

**🔸 TCL (Transcation Control Languate)**

TCL은 DML을 거친 데이터의 변경사항을 수정할 수 있다.  

```COMMIT```, ```ROLLBACK``` 등 작업 내용을 커밋하거나 롤백할 때 사용한다.

<br><br>

***

_2022.10.07. Update_

_2022.10.06. Modified_

_2022.10.05. Update_