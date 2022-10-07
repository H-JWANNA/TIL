# 스키마 (Schema)

스키마는 DB에서 데이터가 구성되는 방식과 서로 다른 **엔티티 간의 관계**에 대한 설명을 말한다.

<br>

## 관계의 종류

**🔸 1:1 관계**

하나의 레코드가 다른 테이블의 레코드 한 개와 연결된 관계

<img src = "https://vertabelo.com/blog/one-to-one-relationship-in-database/one-to-one-img1.png" width = "90%" />

▲ _One-to-One Relationship_

<br>

1:1 관계는 자주 사용되지는 않는다.  

1:1 관계로 나타낼 수 있다면, 해당 테이블에 직접 데이터를 저장하는 게 나을 수도 있다.

<br>

**🔸 1:N 관계**

하나의 레코드가 서로 다른 여러 개의 레코드와 연결된 관계

<img src = "https://www.sqlshack.com/wp-content/uploads/2020/01/one-to-many-relation.png" width = "90%" />

▲ _One-to-Many Relationship_

<br>

데이터베이스에서 가장 많이 사용되는 관계이다.  

위 관게에서 한 개의 ```city_id```는 여러 ```customer```를 가질 수 있지만,  
```customer```가 여러 ```city_id```를 갖는 것은 불가능하다.

<br>

**🔸 N:N 관계**

여러 개의 레코드가 다른 테이블의 여러 개의 레코드와 관계가 있는 관계  

<img src = "https://www.sqlshack.com/wp-content/uploads/2020/03/many-to-many-relationship-example.png" width = "70%" />

▲ _Many-to-Many Relationship_

<br>

N:N 관계의 스키마 디자인에서는 Join 테이블을 만들어 관리한다.

위 관계에서 ```Students```는 여러 ```Class```를 가질 수 있고,  
한 ```Class```도 여러 ```Students```를 가질 수 있다.

이러한 관계를 표현하기 위해 1:N 관계를 형성하는 ```Enrollment```라는 새로운 조인 테이블을 생성한다.

<br>

**🔸 자기참조 관계**

테이블 내의 레코드를 참조하는 관계

<img src = "https://user-images.githubusercontent.com/79494088/132781434-bf0d6b53-7a1f-447e-9779-00da94269432.png" width = "50%" />

▲ _Self Referencing Relationship_

<br>

1:N 관계와 비슷한 관계이다.  

위 관계에서 ```recommend_id```는 추천인이며,  
한 유저는 한 명만 추천인으로 등록할 수 있지만 추천 받는 것은 여러 명에게 할 수 있다.

주로 추천인이나 조직의 상하관계를 표현할 때 사용한다.

<br><br>

***

_2022.10.07. Update_