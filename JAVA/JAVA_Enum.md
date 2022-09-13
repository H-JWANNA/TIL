# Java 컬렉션

## 열거형 (Enum; enumerated type)

열거형은 ```final``` 키워드를 사용하여 선언한 상수들을 간편하게 관리하기 위한 문법 요소이며,  
주로 몇 가지로 한정된 변하지 않는 데이터를 다루는데 사용한다.

<br>

```enum```을 사용하지 않고 전역변수로 상수를 선언하면 아래와 같다.  
<span style = "color : gray"> (참고 : 상수는 관례적으로 대문자로 작성) </span>

▼ _상수명의 중복 발생 (컴파일 에러)_

```java
public static final int SPRING = 1;
public static final int SUMMER = 2;
public static final int FALL   = 3;
public static final int WINTER = 4;

public static final int DJANGO  = 1;
public static final int SPRING  = 2; // SPRING 상수의 중복
public static final int NEST    = 3;
public static final int EXPRESS = 4;
```

<br>

▼ _인터페이스를 통한 상수의 구분_

```java
interface Seasons {
	int SPRING = 1, SUMMER = 2, FALL = 3, WINTER = 4;
}

interface Frameworks {
	int DJANGO = 1, SPRING = 2, NEST = 3, EXPRESS = 4;
}

if (Seasons.SPRING == Frameworks.SPRING) {...}  // 타입 안정성 문제
```

<br>

▼ _enum을 통한 상수의 선언_

```java
enum Seasons { SPRING, SUMMER, FALL, WINTER }
enum Frameworks { DJANGO, SPRING, NEST, EXPRESS }
```

<br>

```enum```을 사용하면 앞에서 발생한 문제들을 해결할 수 있을 뿐 아니라  
코드 자체도 간결하고 가독성이 좋아지고, ```switch```문에서도 사용할 수 있다는 장점이 있다.

```java
enum Seasons { SPRING, SUMMER, FALL, WINTER }

public class Example {
    public static void main(String[] args) {
        Seasons seasons = Seasons.SPRING;
        switch (seasons) {
            case SPRING:
                System.out.println("봄");
                break;
        }
    }
}

// 봄
```

<br>

### 🔸 enum Method

| 리턴 타입 | 메서드<br>(매개변수) | 설명 |
|:---------:|:------------------:|:----:|
|String     | name() | 열거 객체가 가지고 있는 문자열 리턴<br>리턴되는 문자열은 상수 이름과 동일|
|int | ordinal() | 객체의 순번 리턴 (0부터 시작) |
|int | compareTo() | 주어진 매개값과 비교해서 순번 차이를 리턴 |
|열거 타입 | valueOf(String name) | 주어진 문자열의 열거 객체 리턴 |
|열거 배열 | values() | 열거 객체들을 배열로 리턴 |

<br>

```java
enum Seasons { SPRING, SUMMER, FALL, WINTER }

public class Main {
    public static void main(String[] args) {

        // values(), name(), ordinal()
        System.out.println("i.name() = i.ordinal()");
        for(Seasons i : Seasons.values()) {
            System.out.println(i.name() + " = " + i.ordinal());
        }

        // valueOf()
        System.out.println(Seasons.FALL == Seasons.valueOf("FALL"));
    }
}

// 출력
i.name() = i.ordinal()
Spring = 0
Summer = 1
Fall = 2
Winter = 3
true
```

<br>

***

_Update 2022.09.13._
