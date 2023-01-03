# 생성자

생성자는 ```new```를 사용하여 **인스턴스를 생성할 때**,  
생성되는 **인스턴스의 변수들을 초기화시키는데 사용되는 특수한 메서드**이다.

> 생성자의 특징
> 
> 1. 생성자의 이름은 반드시 클래스명과 같아야한다.
> 2. 생성자는 리턴 타입 자체가 존재하지 않는다.
> 3. 생성자도 오버로딩이 가능하다.

<br>

***

<br>

## 기본 생성자 (Default Constructor)

<br>

```java
DefaultConst(){}    // DefaultConst 클래스의 기본 생성자
```

위와 같이 매개 변수가 없는 생성자를 기본 생성자라고 하며,  
만약 클래스 내에 생성자가 없을 경우 자바 컴파일러는 기본 생성자를 자동으로 생성해 준다.

<br>

## 매개변수가 있는 생성자

<br>

```java
class Calculator {
    // 매개 변수가 있는 생성자
    public Calculator (int num1, int num2, char operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }
}

// 매개변수를 통해 인스턴스 초기화
Calculator c1 = new Calculator(10, 20, '+');
```

위와 같이 매개 변수를 통해 인스턴스의 필드값을 하나씩 설정해 줄 필요 없이 생성과 동시에 설정이 된다.

<br>

***

<br>

## this()와 this.

<br>

### this()

```this()``` 메서드는 자신이 속한 클래스에서 다른 생성자를 호출하는 경우에 사용한다.

💡 ```this()``` 메서드는 **반드시** 생성자의 내부에서만 사용할 수 있고, 생성자의 첫 줄에 위치해야한다.

<br>

▼ _this() Example_

```java
public class Test {
    public static void main(String[] args) {
        Example ex1 = new Example();
        Example ex2 = new Example(10);
    }
}

class Example  {
    public Example() {
        System.out.println("기본 생성자 호출");
    };

    public Example(int x) {
        this();
        System.out.println("두 번째 생성자 호출");
    }
}

// 출력
기본 생성자 호출
기본 생성자 호출
두 번째 생성자 호출
```

<br>

### this.

```this``` 키워드는 주로 인스턴스의 필드명과 지역변수를 구분하기 위한 용도로 사용한다.

<br>

▼ _this. Example_

```java
class Car {
    // 인스턴스 변수
    String color;
    String gearType;
    int door; 
    
    Car(String color, String gearType, int door){
        this.color = color; 
        this.gearType = gearType;
        this.door = door;
    }
}
```

위의 예제에서 ```this.color```는 인스턴스 변수이고, ```color```는 매개변수로 정의된 지역변수이다.

<br>

***

_Update 2022.09.05._