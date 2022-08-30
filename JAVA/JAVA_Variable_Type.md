# JAVA 변수와 타입

## 변수

변수(Variable) : 값이 변할 수 있는 데이터를 임시적으로 저장하기 위한 수단

변수선언 : 메모리 공간을 확보하고, 그 메모리 공간에 내가 정한 이름을 붙인다.

```java
int num = 1;
```

<br>

### 변수의 선언과 할당


<br>

- 변수를 선언할 때에는 저장하고자 하는 값의 데이터 타입과 함께 변수 이름을 작성

- 변수를 **선언**한다는 것은 어떤 값을 저장할 메모리 공간을 확보하고, 해당 메모리 공간을 식별할 수 있는 이름을 붙이는 것을 의미

- 변수에 값을 저장하는 것을 **할당**, 변수를 선언하고 나서 처음으로 값을 할당하는 것을 **초기화**라고한다.

### [📋 **JAVA Naming Conventions**](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)


<br>

***

<br>

## 상수

상수(Constant) : 변하지 말아야 할 데이터를 임시적으로 저장하기 위한 수단

```java
final double PI = 3.141592;
```

<br>

상수를 사용하는 이유
- 프로그램이 실행되면서 값이 변하면 안되는 경우
- 코드 가독성을 높이고 싶은 경우
- 코드 유지관리를 손쉽게 하고자 하는 경우

<br>

***

<br>

## 타입

타입은 어떤 값의 유형 및 종류를 의미하며,  
타입에 따라 **값이 차지하는 메모리 공간의 크기**와, **값이 저장되는 방식**이 결정

### 값이 저장되는 방식  
- 실제 값을 의미하는 기본 타입(primitive type)  
- 어떤 값이 저장된 주소를 값으로 갖는 참조 타입(reference type)

```java
int primitive = 1; // 기본 타입 변수 = 1;
Object reference = new Object(); // 참조 타입 변수 = 객체의 주소값 저장;
```


<br>

### 🔸 정수형 데이터 타입

|데이터 타입|메모리의 크기|표현 가능 범위|
|:------:|:---:|:---:|
| byte | 1 byte | -128 ~ 127 ( -2<sup>7</sup>  ~  2<sup>7</sup>-1 ) |
| short | 2 byte |-32,768 ~ 32,767 ( -2<sup>15</sup>  ~  2<sup>15</sup>-1 ) |
| int | 4 byte |-2,147,483,648~2,147,483,647 ( -2<sup>31</sup>  ~  2<sup>31</sup>-1 ) |
| long | 8 byte | -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807 ( -2<sup>63</sup>  ~  2<sup>63</sup>-1 ) |
|||

일반적으로 ```int```형을 사용

- 오버플로우 : 표현 범위 최대값 이상을 넘어가면 넘어간 만큼 최소값으로 순환
- 언더플로우 : 표현 범위 최소값 이하를 넘어가면 넘어간 만큼 최대값으로 순환

<br>

### 🔸 실수형 데이터 타입

|데이터 타입|메모리의 크기|표현 가능 범위|
|:------:|:---:|:---:|
| float | 4 byte | ±(1.40129846432481707e-45 ~ 3.40282346638528860e+38)|
| double | 8 byte | ±(4.94065645841246544e-324d ~ 1.79769313486231570e+308d) |
|||

일반적으로 ```double```형을 사용

- 오버플로우 : 값이 음 또는 양의 범위를 벗어날 경우 값은 무한대가 된다.
- 언더플로우 : 값이 음 또는 양의 범위 안일 경우 값은 0이 된다.

<img src = "https://teach-ict.com/2016/images/diagrams/overflow.gif" />

<span style = "color: gray"> ▲ _실수형 데이터 타입의 Overflow & Underflow_ </span>

<br>


### 🔸 논리형 데이터 타입

|데이터 타입|메모리의 크기|표현 가능 범위|
|:------:|:---:|:---:|
| boolean | 1 byte | True, False |

<br>

### 🔸 문자형 데이터 타입

|데이터 타입|메모리의 크기|표현 가능 범위|
|:------:|:---:|:---:|
| char | 2 byte | 모든 유니코드 문자 |

문자형 리터럴을 작성할 때에는 큰 따옴표(" ")가 아닌 **작은 따옴표(' ')** 를 사용해야 한다.


<br>

***

<br>

## 타입 변환

<br>

### 🔸 자동 타입 변환

바이트 크기가 작은 타입에서 큰 타입으로 변환할 때 (예 : byte → int)

덜 정밀한 타입에서 더 정밀한 타입으로 변환할 때 (예 : 정수 → 실수)

```byte(1) -> short(2)/char(2) -> int(4) -> long(8) -> float(4) -> double(8)```

<br>

### 🔸 수동 타입 변환


<br>

 ▼ JAVA int to String Example using <span style = "color: skyblue">Integer.toString() </span>
```java
public class IntToStringExample2 {  
    public static void main(String args[]) {  
        int i = 200;  
        String s = Integer.toString(i);  
        System.out.println(i + 100); //300 because + is binary plus operator  
        System.out.println(s + 100); //200100 because + is string concatenation operator  
    }
}  
```

<br>


▼ JAVA String to int Example using <span style = "color: skyblue"> Integer.parseInt() </span>

```java
public class StringToInt {    
    public static void main(String[] args) {        
        String str1 = "123";        
        String str2 = "-123";         

        int intValue1 = Integer.parseInt(str1);        
        int intValue2 = Integer.parseInt(str2);   

        System.out.println(intValue1); // 123        
        System.out.println(intValue2); // -123    
    }
}
```

<br>

▼ JAVA String to int Example using  <span style = "color: skyblue"> Integer.valueOf() </span>


```java
public class StringToInt {    
    public static void main(String[] args) {        
        String str1 = "123";        
        String str2 = "-123";         
        int intValue1 = Integer.valueOf(str1).intValue();        
        int intValue2 = Integer.valueOf(str2).intValue();         
        System.out.println(intValue1); // 123        
        System.out.println(intValue2); // -123    
    }
}
```
출처 : [어제 오늘 내일:Tistory](https://hianna.tistory.com/524)


<br>

***

<br>

## 리터럴

리터럴(Literal) : 문자가 가리키는 값. 데이터 타입

```java
int A = 10; // 정수형 변수 A, 정수형 리터럴 10

double PI = 3.141592; // 실수형 변수 PI, 실수형 리터럴 3.141592

boolean isRight = true; // 논리형 변수 isRight, 논리형 리터럴 true

char grade = 'A'; // 문자형 변수 grade, 문자형 리터럴 'A'

String myFirstName = "Hong"; // 문자열 변수 myFirstName, 문자열 리터럴 "Hong"
```

> float 타입의 변수에 실수형 리터럴을 할당할 때, 리터럴 뒤에 접미사 F를 붙여주어야 한다.  
> long 타입의 변수에 정수형 리터럴을 할당할 때, 리터럴 뒤에 접미사 L을 붙여주어야 한다.

<br>

***

<span style="color: gray ; display: inline-block; width: 95%; text-align: right;">_2022.08.30._</span>