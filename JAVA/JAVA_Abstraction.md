# 추상화

자바에서 추상은 **객체의 공통적인 속성과 기능을 추출하여 정의하는 것**을 의미한다.

<br>

## abstract 제어자

```abstract``` 제어자는 메서드나 클래스에 붙을 수 있으며, 이를 각각 **추상 메서드(abstract method)**, **추상 클래스(abstract class)** 라고 한다.

추상 메서드가 포함된 클래스는 반드시 추상 클래스여야 한다.

```java
abstract class AbstractExample {        // 추상 메서드가 포함된 추상 클래스
    abstract void printInfo();  // 추상 메서드는 메서드 바디가 없다.
}
```

위 코드처럼 추상 메서드는 메서드 시그니처만 존재하고 바디는 존재하지 않는다.

즉, 추상 메서드가 포함된 추상 클래스는 미완성된 클래스이기 때문에 **객체 생성이 불가능하다.**

```java
AbstractExample abstractExample = new AbstractExample();    
// 에러 발생 : AbstractExample is abstract; cannot be instantiated
```

<br>

## 추상 클래스

추상 클래스의 하위 클래스는 오버라이딩을 사용해 추상 메서드의 내용을 구현하며 완성시킬 수 있다.

여러 사람이 함께 개발하는 경우에 추상 클래스를 통해 틀을 잡고,  
상속 계층도 상층부는 추상의 정도가 높게, 하층부는 구체화되게 한다.

```java
abstract class Vehicle {
    public String name;
    public abstract void sound();
}

class Bicycle extends Vehicle { // Vehicle 클래스 상속
    public Bicycle() {
        this.name = "자전거";
    }
    @Override
    public void sound() {       // 메서드 오버라이딩으로 구현부 완성
        System.out.println("따르릉");
    }
}

class Motercycle extends Vehicle {
    public Motercycle() {
        this.name = "오토바이";
    }
    @Override
    public void sound() {
        System.out.println("부릉부릉");
    }
}

public class Example {
    public static void main(String[] args) {
        Vehicle bicycle = new Bicycle();
        bicycle.sound();    // 따르릉

        Vehicle motorcycle = new Motorcycle();
        motorcycle.sound(); // 부릉부릉
    }
}
```
<br>

***

<br>

## final

```final``` 키워드는 필드, 지역 변수, 클래스 앞에 위치할 수 있으며  
- 클래스 앞에 위치한 경우에 해당 클래스는 변경, 확장, 상속이 불가능하다.
- 메서드 앞에 위치한 경우에 해당 메서드를 오버라이딩하는 것은 불가능하다.
- 변수 앞에 위치한 경우에 해당 변수는 값 변경이 불가능한 상수가 된다.  

> ❔ 참조형 변수는 가리키는 객체의 주소를 변경할 수 없으므로, 객체 내부의 값은 변경할 수 있다?
>
> 메서드 내부의 참조 변수인 경우에는 Stack 메모리에 객체의 주소가 담기고,  
> Heap 메모리에 값이 저장되므로 final 값을 변경 할 수 있다는 것 같음..?
> 
> getter와 setter를 사용하면 변경할 수 있는 것 같다.  
> 
> 📋 [**참고한 블로그**](https://sabarada.tistory.com/148)

<br>

***

<br>

## 인터페이스 (interface)

인터페이스는 추상클래스와 비슷하지만, 추상 클래스보다 더 높은 추상성을 가진다.

추상 클래스는 추상 메서드를 하나 이상 포함하고,  
인터페이스는 기본적으로 **추상 메서드와 상수만을 멤버로 가질 수 있다.**

<br>

### 인터페이스의 구조

<br>

인터페이스를 작성할 때는 ```class``` 키워드 대신 ```interface``` 키워드를 사용한다.

또한 내부의 모든 필드가 ```public static final```로, 모든 메서드가 ```public abstract```로 정의된다.

<br>

```java
public interface InterfaceExample {
    public static final int num1 = 1;   // interface에서 상수 정의
    final int num2 = 2;                 // public & static 생략
    static int num3 = 3;                // public & final 생략

    public abstract String printNum();  // interface에서 메서드 정의
    void call();                        // public & abstract 생략
}
```

위처럼 생략한 키워드는 컴파일러가 자동으로 추가해준다.

<br>

### 인터페이스의 구현

<br>

클래스의 상속에서 ```extends``` 키워드를 사용했다면, 인터페이스의 구현에는 ```implements``` 키워드를 사용하고,  
인터페이스를 구현할 때는 인터페이스에 구현된 **모든 추상메서드를 구현해야한다.**  
<span style = "color : gray">(이건 abstract도 똑같다..)</span>

추가로 클래스의 상속에서 다중 상속은 허용되지 않지만, 인터페이스는 다중 구현이 가능하다.

```java
class ClassName implements InterfaceName {...}  // 인터페이스 구현

class Dog implements Animal, Pet {...}  // 인터페이스 다중 구현
```
<br>

특정 클래스는 클래스의 상속과 인터페이스 구현을 동시에 할 수도 있다.

```java
abstract class Animal { // 추상 클래스
	public abstract void cry();
} 
interface Pet { // 인터페이스
	public abstract void play();
}

class Dog extends Animal implements Pet { // Animal 클래스 상속 & Pet 인터페이스 구현
    public void cry(){
        System.out.println("멍멍!");
    }

    public void play(){
        System.out.println("원반 던지기");
    }
}
```
<br>

### **인터페이스의 장점**

<br>

인터페이스의 가장 큰 장점은 **역할과 구현을 분리**시켜 복잡한 구현의 내용 또는 변경에 상관없이 해당 기능을 사용할 수 있다는 점이다.

```java
public class NonInterfaceExample {
    public static void main(String[] args) {
        Example ex = new Example();        // Example 객체 생성
        ex.callNon(new NonInterface());    // NonInterface 객체 생성 후 매개변수로 전달
    }
}

class Example {
    // NonInterface 객체를 매개변수로 받는 메서드
    public void callNon(NonInterface nonInterface) {
        nonInterface.call();
    }
}

class NonInterface {
    public void call() {
        System.out.println("텍스트 출력");
    }
}
```

만약 위와 같은 코드에 변경 사항이 생겨 ```NonInterface``` 클래스를 ```NonInterface2```로, 
출력 내용을 ```"텍스트 출력```에서 ```"문자열 출력"```으로 변경해야한다면 
수정할 부분이 꽤 많고 귀찮은 작업이 될 것이다.

<br>

이러한 경우에 인터페이스를 사용하면 아래 코드와 같이 변경할 수 있다.

```java
interface Cover {
    public abstract void call();
}

public class InterfaceExample {
    public static void main(String[] args) {
        Example ex = new Example();
        // ex.callNon(new NonInterface());  // 기존 출력 주석처리
        ex.callNon(new NonInterface2());    // 새로운 출력 생성
    }
}

class Example {
    public void callNon(Cover cover) {  // 매개변수의 다형성
        cover.call();
    }
}

class NonInterface implements Cover {
    public void call() {
        System.out.println("텍스트 출력");
    }
}

class NonInterface2 implements Cover {
    public void call() {
        System.out.println("문자열 출력");
    }
}
```

짧은 코드에서는 크게 어려움이 느껴지지 않아도 코드가 많아질 수록 이러한 기초 작업이 중요하다.

정리하자면 인터페이스의 장점은 아래와 같다.
- 코드 변경의 번거로움 최소화
- 선언과 구현을 분리시켜 개발시간 단축
- 독립적인 프로그래밍을 통해 클래스의 변경이 다른 클래스에 미치는 영향 최소화

<br>

## abstract class vs interface

### 💡 공통점
- 상속받거나 구현하는 클래스에게 추상 메소드를 구현하도록 강제한다.


### 💡 차이점
- 추상 클래스는 상속받아서 기능을 이용하고 확장하는데 목적이 있다면,  
  인터페이스는 껍데기만 존재하며 해당 함수의 구현을 강제화하는데 목적이 있다.

- 추상 클래스는 다중 상속이 불가능하지만, 인터페이스는 다중 구현이 가능하다.
  

  
<br>

***

_Update 2022.09.07._