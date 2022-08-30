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


- 몰랐던 내용 : ```int a = (조건문) ? 참일때 : 거짓일때;```

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

```java
import java.util.Scanner;                 // Scanner 클래스를 가져옵니다.

Scanner scanner = new Scanner(System.in); // Scanner 클래스의 인스턴스를 생성합니다.
String inputValue = scanner.nextLine();   // 입력한 내용이 inputValue에 저장됩니다.

System.out.println(inputValue);           // 입력한 문자열이 출력됩니다.
```

<br>

***

<span style="color: gray ; display: inline-block; width: 95%; text-align: right;">_2022.08.30._</span>