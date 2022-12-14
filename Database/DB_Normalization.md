# 데이터베이스 정규화

데이터베이스 정규화의 기본 목표는 테이블 간에 **중복된 데이터를 허용하지 않는다**는 것이다.  
중복된 데이터를 허용하지 않음으로써 **무결성**을 유지할 수 있으며, DB의 저장 용량 역시 줄일 수 있다.

이에 따라 중요한 부분은 아래와 같다.

<br>

### **데이터 중복 (Data Redundancy)**

데이저 중복은 실제 데이터의 동일한 복사본이나 부분적인 복사본을 뜻한다.  
중복성으로 인한 문제점은 아래와 같다.

- 일관된 자료 처리가 어렵다.
- 저장 공간을 낭비한다.
- 데이터의 효율성이 감소한다.

<br>

### **데이터 무결성 (Data Itegrity)**

데이터 무결성은 데이터의 수명 주기 동안 정확성과 일관성을 유지하는 것을 뜻한다.  

데이터 정규화는 이러한 데이터 무결성을 강화하기 위한 목적도 가지고 있다.

<br>

### **데이터 이상 현상 (Data Anomaly)**

데이터 이상 현상은 데이터에서 기대한 것과 다른 이상 현상을 말한다.

데이터 이상 현상은 3가지로 나뉜다.

<br>

**🔸 갱신 이상 (Update Anomaly)**

갱신 이상은 동일한 데이터가 여러 레코드에 걸쳐있을 때,  
어떤 데이터를 갱신해야 하는지에 대한 논리적 일관성이 없어 발생하게 된다.

```
+-------+-----------+--------+
| name  | dept_name | salary |
+-------+-----------+--------+
| John  | Comp.Sci. |  65000 |
| Mark  | Math      |  75000 |
| Tom   | Physics   |  90000 |
| Tom   | Economics |  70000 |
+-------+-----------+--------+
```

<br>

**🔸 삽입 이상 (Insertion Anomaly)**

삽입 이상은 데이터 삽입을 못하는 경우를 가리킨다.

```
+----+---------+----------+------+------+
| id | course  | semester | year | room |
+----+---------+----------+------+------+
|  1 | CS-101  | Fall     | 2021 | 402  |
|  2 | PHY-312 | Spring   | 2020 | 203  |
|  3 | CS-203  | Spring   | 2022 | 404  |
+----+---------+----------+------+------+
```

위와 같은 경우, 새로운 강의가 편성되었을 때 강의실이 정해지지 않은 경우 데이터에 추가하지 못하게 된다.

<br>

**🔸 삭제 이상 (Deletion Anomaly)**

삭제 이상은 데이터의 특정 부분을 지울 때 다른 부분도 의도치 않게 같이 지워지는 경우를 말한다.

<br>

***

<br>

## 정규화의 법칙 (Normalization Rule)

### **제1 정규화**

제1 정규화란, 테이블의 열이 **원자값(Atomic Value, 하나의 값)을 갖도록 테이블을 분해하는 것**이다.

```
+-------+-----------+
| name  | Language  |
+-------+-----------+
| John  | Java      |
| Mark  | Python    |
| Tom   | JS, Ruby  |
+-------+-----------+

▼   ▼   ▼   ▼   ▼   ▼

+-------+-----------+
| name  | Language  |
+-------+-----------+
| John  | Java      |
| Mark  | Python    |
| Tom   | JS        |
| Tom   | Ruby      |
+-------+-----------+
```

<br>

### **제2 정규화**

제2 정규화란, 제1 정규화를 진행한 테이블에 대해 **완전 함수 종속을 만족하도록 테이블을 분해하는 것**이다.

> 완전 함수 종속 : 기본키의 부분 집합이 결정자가 되어서는 안된다는 뜻

<br>

```
+--------+-----------+------+-------+
| Stu_no | dept_name | room | grade |
+--------+-----------+------+-------+
| 211232 | Comp.Sci. | 402  | 3.5   |
| 208941 | Comp.Sci. | 402  | 4.0   |
| 182206 | Math      | 203  | 3.5   |
| 190511 | Physics   | 106  | 4.5   |
| 211232 | Physics   | 106  | 3.0   |
+--------+-----------+------+-------+
```

위 테이블에서 기본키는 ```Stu_no```, ```dept_name``` 2가지의 복합키이다.  

하지만 여기서 ```room``` 필드는 기본키의 부분 집합인 ```dept_name```에 의해 결정될 수 있다.  
즉, 기본키의 부분키인 ```dept_name```이 결정자이기 때문에 별도의 테이블로 관리하여 제2 정규화를 시킬 수 있다.

<br>

```
+--------+-----------+-------+      +-----------+------+
| Stu_no | dept_name | grade |      | dept_name | room |
+--------+-----------+-------+      +-----------+------+
| 211232 | Comp.Sci. | 3.5   |      | Comp.Sci. | 402  |
| 208941 | Comp.Sci. | 4.0   |      | Comp.Sci. | 402  |
| 182206 | Math      | 3.5   |      | Math      | 203  |
| 190511 | Physics   | 4.5   |      | Physics   | 106  |
| 211232 | Physics   | 3.0   |      | Physics   | 106  |
+--------+-----------+-------+      +-----------+------+
```

<br>

### **제3 정규화**

제3 정규화란, 제2 정규화를 진행한 테이블에 대해  
**이행적 종속을 없애도록 테이블을 분해하는 것**이다.

> 이행적 종속 : A → B, B → C가 성립하면 A → C가 성립된다.

<br>

```
+--------+-----------+---------+
| Stu_no | dept_name | tuition |
+--------+-----------+---------+
| 211232 | Comp.Sci. |   80000 |
| 208941 | Comp.Sci. |   80000 |
| 182206 | Math      |   75000 |
| 190511 | Physics   |   60000 |
+--------+-----------+---------+
```

위 테이블에서 ```Stu_no``` 필드는 ```dept_name``` 필드를 결정하고,  
```dept_name``` 필드는 ```tuition``` 필드를 결정하고 있다.

즉, ```Stu_no```, ```dept_name```를 가진 테이블과 ```dept_name```, ```tuition```을 가진 테이블로 분해하여 제3 정규화를 시킬 수 있다.

<br>

```
+--------+-----------+      +-----------+---------+
| Stu_no | dept_name |      | dept_name | tuition |
+--------+-----------+      +-----------+---------+
| 211232 | Comp.Sci. |      | Comp.Sci. |   80000 |
| 208941 | Comp.Sci. |      | Math      |   75000 |
| 182206 | Math      |      | Physics   |   60000 |
| 190511 | Physics   |      +-----------+---------+
+--------+-----------+      
```

<br>

## **BCNF 정규화**

BCNF 정규화란, 제3 정규화를 진행한 테이블에 대해  
모든 결정자가 후보키가 되도록 테이블을 분해하는 것이다.

```
+--------+-----------+-------+-------+
| Stu_id | dept_name | prof  | grade |
+--------+-----------+-------+-------+
|      1 | Comp.Sci. | John  | 3.5   |
|      2 | Comp.Sci. | John  | 4.0   |
|      3 | Math      | Mark  | 3.5   |
|      4 | Physics   | Tom   | 4.5   |
|      5 | Physics   | Tom   | 3.0   |
+--------+-----------+-------+-------+
```

위 테이블에서 기본키는 ```Stu_id```, ```dept_name```이다. 그리고 기본키는 ```prof```를 결정하고 있다.  
또한, 여기서 ```prof```는 ```dept_name```을 결정하고 있다.

하지만 문제는 ```prof```가 ```dept_name```을 결정하는 결정자이지만, 후보키가 아니라는 점이다.

또한, John이나 Tom이 강의하는 과목명이 바뀌었다면 한 개 이상의 로우를 갱신해야하는 문제점이 있다.  

이를 해결하기 위해 테이블을 분리하면 BCNF 정규화를 시킬 수 있다.

<br>

```
+-------+-----------+       +--------+-----------+-------+
| prof  | dept_name |       | Stu_id | dept_name | grade |
+-------+-----------+       +--------+-----------+-------+
| John  | Comp.Sci. |       |      1 | Comp.Sci. | 3.5   |
| Mark  | Math      |       |      2 | Comp.Sci. | 4.0   |
| Tom   | Physics   |       |      3 | Math      | 3.5   |
+-------+-----------+       |      4 | Physics   | 4.5   |
                            |      5 | Physics   | 3.0   |
                            +--------+-----------+-------+
```

<br><br>

***

_2022.10.07. Update_