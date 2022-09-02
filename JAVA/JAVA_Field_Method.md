# 필드와 메서드

> 필드는 클래스에 포함된 변수,  
> 
> 메서드는 특정 작업을 수행하는 일련의 명령문들의 집합을 뜻힌다.

<br>

## 필드 (Field)

<br>

JAVA에서 변수는 
- 클래스 변수(class variable)
- 인스턴스 변수(instance variable)
- 지역 변수(local variable)

로 구분할 수 있다.

<br>

```java
class Example { // => 클래스 영역
	int instanceVariable; // 인스턴스 변수
	static int classVariable; // 클래스 변수(static 변수, 공유변수)

	void method() { // => 메서드 영역
		int localVariable = 0; // 지역 변수. 메서드 블록 안에서만 유효
	}
}
```

<br>

**인스턴스 변수**
- ```static``` 키워드가 선언되지 않은 클래스에 포함된 변수(필드), 멤버 변수에 포함된다.  
- 인스턴스 각각의 고유한 속성을 저장하기 위한 변수, **독립적인 공간을 가진다.**  
- ```new 생성자()```를 통해 인스턴스가 생성될 때 만들어진다.

**클래스 변수**
- ```static``` 키워드가 함께 선언된 필드, 멤버 변수에 포함된다.
- 독립적인 공간을 가지는 인스턴스 변수와 다르게 **공통된 저장공간을 공유**
- 인스턴스를 따로 생성하지 않아도 ```클래스명.클래스변수명```을 통해 사용이 가능하다.

**지역 변수**
- 필드의 범위에 들어가지 않으며, 메서드 내에 포함된 모든 변수, 멤버 변수에 포함되지 않는다.


<br>

▼ _위의 예제를 통한 정리_

```java
Example.classVariable = 0;      // 인스턴스 없이 호출 가능
Example.instanceVariable = 0;   // 인스턴스 없이 호출 불가능
Example.method();               // 인스턴스 없이 호출 불가능

Example ex = new Example();     // 인스턴스 생성

ex.classVariable = 0;           // 인스턴스를 통해 호출 가능
ex.instanceVariable = 0;        // 인스턴스를 통해 호출 가능
ex.method();                    // 인스턴스를 통해 호출 가능
```

<br>

▼ _클래스 변수는 공통된 저장공간을 공유한다._

```java
Example ex1 = new Example();
Example ex2 = new Example();

Example.classVariable = 1;
ex1.classVariable = 2;
ex2.classVariable = 3;

System.out.println(Example.classVariable);  // 3
System.out.println(ex1.classVariable);      // 3
System.out.println(ex2.classVariable);      // 3
```

<span style = "color: gray"> 인텔리제이에서 ex1.classVariable과 같이 적으면 Example.classVariable로 바꿔준다. </span>

<br>

▼ _반면 인스턴스 변수는 독립적인 저장공간을 사용한다._

```java
Example ex1 = new Example(); // 인스턴스 생성
Example ex2 = new Example(); // 인스턴스 생성

ex1.instanceVariable = 1;
ex2.instanceVariable = 2;

System.out.println(ex1.instanceVariable);   // 1
System.out.println(ex2.instanceVariable);   // 2
```

<br>

지역 변수는 메서드 내에 선언되며 메서드 내에서만 사용이 가능하다.

멤버 변수가 힙 메모리에 저장되는 것과 다르게 지역변수는 스택 메모리에 저장이 되어 메서드가 종료되는 것과 동시에 더 이상 사용 할 수 없게 된다.

또한, 힙 메모리에는 빈 공간이 저장될 수 없어 멤버 변수는 강제로 초기화가 되지만, 스택 메모리는 강제로 초기화가 되지 않아 반드시 초기화를 실행해주어야 한다.

<br>

<img src = "https://user-images.githubusercontent.com/33229855/109814411-d0bb0c00-7c71-11eb-90b8-a9c6158d53e9.png" />

▲ _이해를 돕기위한 각 변수들의 메모리 적재 위치_

<br>

### Static
 
```static```은 클래스의 멤버(필드, 메서드, 이너 클래스)에 사용되는 키워드이며,  
```static``` 키워드가 붙은 멤버를 **정적 멤버(static member)** 라고 부른다.

위에서 ```static```이 붙은 변수를 클래스 변수, 그렇지 않은 변수를 인스턴스 변수로 나누었고,  
클래스 변수의 특징으로는 인스턴스를 생성하지 않고도 호출할 수 있다는 점이 있었다.

이는 메서드에서도 동일하게 ```static void method()```와 같이 생성된 정적 메서드는 ```Class.method()```로 인스턴스 생성없이 호출이 가능하다.

다만, 정적 메서드에서는 인스턴스 변수, 혹은 인스턴스 메서드를 사용할 수 없다.

<br>

***

<br>

## 메서드

<br>

▼ _메서드의 구조_

```java
// 자바제어자 반환타입 메서드명(매개 변수)  
public void example(String str){  // ->  메서드 시그니처
	메서드 내용                   // -> 메서드 바디
}
```

메서드 호출 시 괄호() 안에 입력하는 값을 인자(argument)라고 하는데,  
**인자의 개수와 순서는 반드시 메서드를 정의할 때 선언된 매개 변수와 일치해야한다.**

<br>

### 메서드 오버로딩 (Method Overloading)

직역하면 **재정의**, 즉 하나의 클래스 안에 같은 이름의 메서드 여러개를 정의하는 것  
상속을 받은 자식 클래스에서도 오버로딩이 가능하다.

<br>

▼ _Method Overloading_

```java
// 인자를 받지 않았을 때
void A () {
    System.out.println("void A()");
}

// int 타입의 인자를 받았을 때
void A (int arg1) {
    System.out.println("void A(int arg1)");
}

// double 타입의 인자를 받았을 때
void A (double arg1) { 
    System.out.println("void A(double arg1)"); 
}

// String 타입의 인자를 받았을 때
void A (String arg1) {
    System.out.println("void A (String arg1)");
}
```

<br>

**오버로딩의 조건**

- 같은 메서드명을 사용해야한다.
- 매개변수의 개수나 타입이 달라야 한다.
- 반환 타입은 오버로딩이 성립하는데에 영향을 주지 못한다.

<br>

▼ _많은 자바 Method들은 오버로딩이 되어있다._

[![Overloading](/JAVA/src/overloading.PNG)](https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html)



<br>

***

_2022.09.02. Update_

