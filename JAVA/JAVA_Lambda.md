# 람다 (Lambda)

람다식(Lambda Expression)은 함수형 프로그래밍 기법을 지원하는 자바의 문법요소이며,  
메서드를 하나의 식으로 표현한 것으로, **코드를 매우 간결하고 명확하게 표현할 수 있다**는 장점이 있다.

<br>

## 기본 문법

람다식은 기본적으로 **반환 타입과 메서드명을 생략**할 수 있다.  
그래서 람다식을 익명 함수(anonymous function)라고 부르기도 한다.

또한 객체의 선언과 생성을 동시에 하여 하나의 객체를 생성하고, 한 번만 사용되는 일회용이다.

```java
// 기존 방식
void example1() {
    System.out.println(5);
}

// 람다식
() -> { System.out.println(5); }
```

```java
// 기존 방식
void example2(String str) {
    System.out.println(str);
}

// 람다식
(String str) -> System.out.println(str) // 한 줄인 경우 중관호 생략 가능
```

```java
// 기존 방식
int example3(int x, int y) {
    return x + y;
}

// 람다식
(x, y) -> x + y // 매개변수 타입을 쉽게 유추할 수 있을 경우 생략 가능

// 람다식을 객체로 표현
new Object() {
    int sum(int x, int y) {
        return x + y;
    }
}
```

<br>

## 함수형 인터페이스 (Functional Interface)

함수형 인터페이스에는 단 하나의 추상 메서드만 선언될 수 있고,  
```@FunctionalInterface``` 어노테이션을 가지고 있다.

위에서 언급한 람다식은 객체이지만 람다식 메서드를 사용할 수 있는 방법이 없는데,  
이러한 문제를 해결하는데 함수형 인터페이스가 사용된다.

함수형 인터페이스는 단 하나의 추상 메서드만 선언될 수 있는 이유도  
**람다식과 인터페이스의 메서드가 서로 1:1 매칭이 되어야 하기 때문이다.**

<br>

```java
@FunctionalInterface
interface Example {
    public abstract int sum(int x, int y);
}

public static void main(String[] args) {
    Example example = (x, y) -> x + y;
    System.out.println(example.sum(10, 20));    // 30
}
```
▲ _참조형 변수의 타입으로 함수형 인터페이스를 사용하여 원하는 메서드에 접근가능_


<br>

### 매개변수와 리턴값이 없는 람다식

```java
@FunctionalInterface
public interface Example {
    public void run();
}

// 람다식
Example example = () -> { System.out.println("run"); };
example.run();  // run
```

<br>

### 매개변수가 있는 람다식

```java
@FunctionalInterface
public interface Example {
    public void run(String name);
}

// 람다식
Example example = (str) -> {
    String polite = "입니다.";
    System.out.println( str.concat(polite) );
};

example.run("짱구");  // 짱구입니다.
```

<br>

### 리턴 값이 있는 람다식

```java
@FunctionalInterface
public interface Example {
    public int sum(int x, int y);
}

// 람다식
Example example;
example = (x, y) -> { return x + y; };
example.sum(10, 20);    // 30

// 한 줄인 경우, 중괄호와 return 생략 가능
example = (x, y) -> x + y; 
example.sum(20, 30);    // 50
```

### 📋 [**_함수형 인터페이스 공식문서_**](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

<br>

## 메서드 참조 (Method Reference)

메서드 참조는 람다식에서 불필요한 매개변수를 제거할 때 주로 사용한다.

```java
@FunctionalInterface
public interface Example {
    public int numbers(int x, int y);
}

Example example = (x, y) -> Math.max(x, y); // 단순 호출

// 클래스이름 :: 메서드이름
Example example = Math :: max;  // 메서드 참조 (위 결과와 같음)
```

람다식과 동일하게 인터페이스의 추상 메서드가 가진 매개변수, 반환 타입 등을 유추하여 객체가 생성된다.

<br>

### 정적 메서드와 인스턴스 메서드 참조

정적 메서드는 위와 같이 ```클래스 :: 메서드``` 방식으로 참조한다.

인스턴스 메서드의 경우에는 먼저 객체를 생성한 뒤 참조 변수에 ```참조변수 :: 메서드``` 형식으로 참조한다.

```java
public class Calculator {
    public static int staticMethod(int x, int y) {
        return x + y;
    }

    public int instanceMethod(int x, int y) {
        return x * y;
    }
}

Example example;

example = Calculator :: staticMethod;   // 정적 메서드 참조

Calculator calculator = new Calculator();
example = calculator :: instanceMethod; // 인스턴스 메서드 참조
```

<br>

### 생성자 참조

생성자를 참조한다는 것은 객체 생성을 의미한다.

```java
(a, b) -> { return new 클래스(a,b ); };

클래스 :: new   // 생성자 참조
```

<br>

```java
public class Member {
    // Field

    public Member(String id) {
        this.id = id;
    }

    public Member(String name, String id) {
        this.id = id;
        this.name = name;
    }
    // getter and setter
}

--------------------------------------

import java.util.function.BiFunction;
import java.util.function.Function;

public static void main(String[] args) throws Exception {

    Function<String, Member> func1 = Member::new;
    Member member1 = func1.apply("짱구");   // Member(String id)

    BiFunction<String, String, Member> func2 = Member::new;
    Member member2 = func2.apply("신짱구", "짱구"); // Member(String name, String id)
}
```

<br>

***

_Update 2022.09.15._