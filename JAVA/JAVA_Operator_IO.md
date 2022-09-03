# 연산자와 I/O

## 연산자

<br>

연산자의 종류 및 우선 순위

| 우선순위 | 연산자 | 내용 |
|:------:|:---:|:---:|
| 1 | (), [] | 괄호 / 대괄호 |
| 2 | !, ~, ++, -- | 부정 / 증감 연산자 |
| 3 | *, /, % | 곰셈 / 나눗셈 연산자 |
| 4 | <, <=, >, >= | 대소 비교 연산자 |
| 5 | && | AND 연산자 |
| 6 | \|\| | OR 연산자 |
| 7 | ? : | 조건 연산자 |
| 8 | =, +=, -=, /=, %= | 대입 / 할당 연산자 |


- 삼항 연산자 : ```int a = (조건문) ? 참일때 : 거짓일때;```

<br>

## 콘솔 입출력(I/O)

<br>

### 출력

<br>

```System.out.print();``` : 일반 출력

```System.out.println();``` : 출력 후 줄 바꿈

```System.out.printf();``` : 형식대로 출력

| 지시자 | 출력 포맷 |
|:------:|:---:|
| %b | boolean |
| %d | 10진수 |
| %o | 8진수 |
| %x, %X | 16진수 |
| %c | 문자 |
| %s | 문자열 |
| %n | 줄바꿈 |


<br>

### 입력

<br>

**Scanner**

변수의 타입을 설정해 줄 수 있어서 추가 가공 작업이 불필요해 편리하다.

개행문자(```\n```)나 띄어쓰기(공백)를 경계로 하여 입력 값을 인식한다.

버퍼 사이즈가 1024char이기 때문에 많은 입력을 필요로 할 경우에는 성능상 단점이 있다.

```java
// Scanner 클래스를 import
import java.util.Scanner;            

// Scanner 클래스의 sc 인스턴스를 생성
Scanner sc = new Scanner(System.in); 

// 입력한 내용을 변수에 저장
String str = sc.nextLine();          

// 입력한 내용 출력
System.out.println(str); 
```

<br>

**BufferedReader**

키보드 입력을 Buffer에 전송한 뒤, 버퍼의 내용을 한 번에 묶어서 보낸다.  

개행문자(```\n```)만을 경계로 하여 입력 값을 인식한다.

```Scanner```보다 속도가 빠르다는 장점이 있다.

하지만 입력받은 데이터가 ```String```으로 고정되므로 가공하는 작업이 필요하고,  
예외처리를 반드시 필요로 하기 때문에 ```readLine()```시 마다 ```try/catch```문으로 감싸거나  
```throws IOException```을 통한 예외처리를 해줘야 한다.


```java
// 클래스들을 import
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public static void main(String[] args) throws IOException {

    // 사용자 입력을 받을 수 있는 reader 인스턴스 생성
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // 입력한 내용을 변수에 저장
    String str = reader.readLine();

    // 입력한 내용 출력
    System.out.println(str);
}
```

<br>

공백을 기준으로 데이터를 가공하고자 할 때는  
```StringTokenizer의 nextToken()``` 메서드나 ```String.split()``` 메서드를 사용한다.

```java
// StringTokenizer .nextToken()
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
StringTokenizer str = new StringTokenizer(br.readLine());
int num1 = Integer.parseInt(str.nextToken());
int num2 = Integer.parseInt(str.nextToken());

// String.split()
String arr[] = str.split(" ");
```

***

_Modified 2022.09.03._


_Update 2022.08.30._