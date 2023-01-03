# 상속

기존의 클래스를 재활용하여 새로운 클래스를 작성하는 자바의 문법요소로  
```extends``` 키워드를 사용하여 **상위 클래스의 멤버를 전달받는 것**을 **상속**이라고 한다.

<br>

```java
class Example1 {
    static void testExtends(){}
}

// Example1을 상속받음
class Example2 extends Example1 {
    public static void main(String[] args) {

        // Example1의 멤버 상속받아 호출 가능
        Example2.testExtends();
    }
}
```

<br>

## 포함 관계

포함(composite)은 클래스의 멤버로 다른 클래스 타입의 참조변수를 선언하는 것을 의미한다.

<br>

```java
// 다른 클래스
class CompositeExample {
    String lst1, lst2;

    public CompositeExample(String lst1, String lst2) {
        this.lst1 = lst1;
        this.lst2 = lst2;
    }
}

// 현재 클래스
public class Example {
    int num;
    String name;
    // 다른 클래스 타입
    CompositeExample lst;

    // 현재 클래스 생성자
    public Example(int num, String name, CompositeExample lst) {
        this.num = num;
        this.name = name;
        this.lst = lst;
    }

    // 출력 메서드
    void printInfo() {
        System.out.println("num : " + num);
        System.out.println("name : " + name);
        System.out.println("list : [" + lst.lst1 + ", " + lst.lst2 + "]");
    }

    public static void main(String[] args) {
        
        // 다른 클래스 인스턴스 생성
        CompositeExample list1 = new CompositeExample("1번", "2번");

        // 현재 클래스 인스턴스 생성
        Example ex1 = new Example(1, "김이름", list1);

        ex1.printInfo();
    }
}

// 출력
num : 1
name : 김이름
list : [1번, 2번]
```

<br>

### 💡  
### 클래스 간의 관계가 <span style = "color : cyan">'**~은 ~이다 (Is-A)**'</span> 이면 상속 관계,  
### <span style = "color : cyan">'**~은 ~을 가지고 있다 (Has-A)**'</span> 이면 포함 관계가 적합하다.

<br>

***

<br>

## 메서드 오버라이딩 (Method Overriding)

메서드 오버라이딩은 상위 클래스로부터 상속받은 메서드와 **동일한 이름의 메서드를 재정의** 하는 것을 말한다.

<br>

```java
class Bicycle {
    void run() {
        System.out.println("페달 밟기");
    }
}

public class Motorcycle extends Bicycle {   // Bicycle 상속
    @Override
    void run() {
        System.out.println("엑셀 밟기");    // run 메서드 재정의
    }

    public static void main(String[] args) {
        Motorcycle mc = new Motorcycle();

        mc.run();   // "엑셀 밟기"
    }
}
```

<br>

### 💡 메서드 오버라이딩의 조건
1. 메서드의 선언부(이름, 매개변수, 반환타입)이 상위 클래스와 완전히 일치해야한다.
2. 접근 제어자의 범위가 상위 클래스의 메서드보다 같거나 넓어야한다. (public, praivate, …)
3. 에외는 상위 클래스의 메서드보다 많이 선언할 수 있다.

<br>

▼ _Method Overriding 활용법_
```java
class Vehicle {
    void run() {
        System.out.println("Vehicle is running");
    }
}

class Bike extends Vehicle {
    @Override
    void run() {
        System.out.println("Bike is running");
    }
}

class Car extends Vehicle {
    @Override
    void run() {
        System.out.println("Car is running");
    }
}

class MotorBike extends Vehicle {
    @Override
    void run() {
        System.out.println("MotorBike is running");
    }
}

// 배열로 한번에 관리하기
Vehicle[] vehicles = new Vehicle[] { new Bike(), new Car(), new MotorBike()};

for (Vehicle vehicle : vehicles) {
		vehicle.run();
}

// 출력 결과
Bike is running
Car is running
MotorBike is running
```

<br>

***

<br>

## super와 super()

```super와``` ```super()```의 차이는 ```this```와 ```this()```의 차이와 비슷하다.

```this```는 메서드 내에서 인스턴스 변수에 접근하기 위해 사용하고,  
```this()```는 같은 클래스 내의 다른 생성자를 호출하는데 사용한다.

```super```는 하위 클래스애서 상위 클래스의 객체에 접근하는데 사용하고,  
```super()```는 상위 클래스의 생성자를 호출하는데 사용한다.

```java
class Upper {
    int num = 100;  // super.count
}
class Lower extends Upper {
    int num = 20;   // this.count

    void upperLower() {
        System.out.println(count);        // 20
        System.out.println(this.count);   // 20
        System.out.println(super.count);  // 100
    }
}
```

이처럼 같은 이름의 ```num``` 변수를 구분하기 위해 ```super``` 키워드를 사용한다.

<br>

```java
class Upper {
    Upper() {
        System.out.println("상위 클래스 생성자");
    }
}
class Lower extends Upper {
    Lower() {
        super();  // "상위 클래스 생성자"
        System.out.println("하위 클래스 생성자");
    }
}
```

```super()``` 키워드 또한 ```this()``` 키워드와 마찬가지로 생성자 안에서 첫 줄에만 사용 가능하다.

<br>

***

<br>

## Object 클래스

Object 클래스는 자바의 클래스 상속 계층도에서 **최상위에 위치한 상위 클래스**이다.

```java
class Upper {   // 컴파일러가 자동으로 "extends Object" 추가
}
```

<br>

### Object 클래스의 메서드

| 메서드명 |	반환 타입 |	주요 내용 |
|:---:|:---:|:---:|
|toString() |	String |	객체 정보를 문자열로 출력 |
|equals(Object obj) |	boolean	 |등가 비교 연산(==)과 동일하게 스택 메모리값을 비교 |
|hashCode() |	int |	객체의 위치정보 관련. Hashtable 또는 HashMap에서 동일 객체여부 판단 |
| wait() |	void	 | 현재 쓰레드 일시정지 |
|notify()|	void|	일시정지 중인 쓰레드 재동작|


<br>

***

_Update 2022.09.06._