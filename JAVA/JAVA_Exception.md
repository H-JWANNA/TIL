# Java 컬렉션

## 예외 처리 (Exception Handling)

예외 처리란 프로그램의 비정상적인 종료를 방지하고, 정상적인 실행 상태를 유지하기 위한 사전 작업이다.

쉽게 말하면 에러에 대응할 수 있는 코드를 미리 사전에 작성해서 에러를 방지하는 것을 말하고,  
여기서 에러는 크게 **컴파일 에러(Compile Time Error)** / **런타임 에러(Run Time Error)** 로 나눌 수 있다.

<span style = "color : gray"> 논리 에러(Logic Error)도 있지만 깊게 다루진 않는다. </span>

<br>

> 프로그램 실행 시 발생하는 모든 문제를 에러(Error)라고 부르지만,  
> 
> 엄밀히 말하면 **에러는** 메모리 부족(OutOfMemoryError)이나 스택오버플로우(StackOverflowError) 등 발생하면 복구하기 어려운 수준의 **심각한 오류**를 말하고,
>
> 아래에서 다룰 코드 수정 등을 통해 수습이 가능한 오류는 **예외(Exceoption)** 라고 한다.

<br>

### 컴파일 에러

<br>

말 그대로 컴파일 할 때 발생하는 오류를 말한다.

> 컴파일(Compile) : **인간의 언어로 작성된 소스 코드**(고수준 언어 : Java, C …)를 **CPU가 이해할 수 있는 언어**(저수준 언어 : 기계어)로 **변환**하는 작업을 말한다.

<br>

주로 세미콜론 생략, 잘못된 자료형이나 포맷 등 문법적인 문제에서 자주 발생한다.

```java
java: ')' expected
```

<br>


### 런타임 에러

<br>

코드를 실행하는 런타임 과정에서 발생하는 오류를 말한다.

- ```NullPointException``` : ```null``` 값을 참조했을 때 발생하는 오류
  
- ```ArithmeticException``` : 특정 숫자를 0으로 나눌 때 발생하는 오류

- ```FileNotFoundException``` : 존재하지 않는 파일을 불러올 때 발생하는 오류

- ```ArrayIndexOutOfBoundsException``` : 배열의 범위를 벗어난 값을 불러올 때 발생하는 오류

<br>

***

<br>

## 예외 클래스

<br>

<img src = "https://user-images.githubusercontent.com/51476083/125185092-9126b100-e25d-11eb-91b9-7100753a8b2f.png"/>

▲ _예외 클래스의 상속 계층도_

<br>

위의 사진에서 볼 수 있는 ```Throwable``` 클래스는 ```java.lang.Object``` 하위의 클래스이고, ```Exception```과 ```Error```로 나뉜다.

<br>

### Runtime Exception (실행 예외)

<br>

런타임 시 발생하는 오류들을 하위 클래스로 포함하고 있다.

컴파일러가 예외 처리를 강제하지 않는다는 의미에서 **unchecked exception**이라고도 한다.

<br>

### Other Exception (일반 예외)

<br>

```RuntimeException```과 하위 클래스를 제외한 모든 ```Exception``` 클래스와 하위 클래스를 말한다.

위에서 ```RuntimeException```을 상속한 클래스는 unchecked exception이라고 했다.  

반대로 ```RuntimeException```을 상속하지 않는 클래스는 **checked exception**이라고 하며, 코드 실행 전에 예외 처리 코드 여부를 반드시 검사한다.

<br>

***

<br>

## 예외 처리

<br>

### try-catch문

<br>

오류 상황을 대비해서 정상 실행 상태를 유지할 수 있도록 하는 방법

```java
try {
    // 예외가 발생할 수 있는 코드
    // 정상 실행되면 catch문을 실행하지 않음
}
catch (ExceptionType e) {
    // ExceptionType의 예외가 발생할 시 실행할 코드
    // catch 블럭은 여러개 생성할 수 있음
}
finally {
    // finally는 옵션이다. 꼭 추가하지 않아도 됨
    // 예외 발생 여부와 상관없이 항상 실행한다.
}
```
<br>

▼ _예제 코드_

```java
public void divide() {
    try {
        System.out.printf("계산결과는 %.1f입니다.\n", this.num1 / this.num2);
    } 
    catch (ArithmeticException e) {
        System.out.println(e.getMessage());    
        // / by zero (가장 간단한 정보)

        System.out.println(e.toString());          
        //  java.lang.ArithmeticException: / by zero (조금 더 상세한 정보)

        e.printStackTrace();
        /*
        java.lang.ArithmeticException: / by zero
        at package.Calculator.divide(Main.java:13)
        at package.Main.main(Main.java:28)
        (가장 상세한 정보)
         */
    }
    System.out.println("Divide Error");
}
```

<br>

### 예외 전가

<br>

오류를 바로 해결하지 않고 호출한 곳으로 떠넘기는 방법

```java
public static void main(String[] args) {
    try {
        throwException();
    }
    catch (ClassNotFoundException e) {
        System.out.println(e.toString());
    }
}

static void throwException() throws ClassNotFoundException, NullPointerException {...}
```

위 코드를 보면 ```throws``` 키워드를 이용해 ```throwException``` 메서드에서 발생할 수 있는 예외들을 호출한 ```main``` 메서드로 떠넘겼다.

원래라면 ```throwException``` 메서드에서 처리할 예외를 이제 ```main``` 메서드에서 처리하게 된다.

<br>

### 예외 발생 시키기

```throw``` 키워드를 사용해서 예외를 의도적으로 발생시키는 방법



```java
public void divide() {
    if (num2 == 0) throw new ArithmeticException("Can't divide 0");
    // 예외 생성과 동시에 처리

    System.out.printf("계산결과는 %.1f입니다.\n", this.num1 / this.num2);
}
```



```java
public static void main(String[] args) {
    try {
        Exception zeroException = new Exception("의도된 예외 생성");
        throw zeroException;
    }
    catch (Exception e) {
        System.out.println("의도된 예외 발생");
    }
}

// 의도된 예외 발생
```

<br>

***

_Update 2022.09.14._