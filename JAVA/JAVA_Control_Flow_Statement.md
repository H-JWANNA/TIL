# 제어문

<br>

## 조건문

### if 문

<br>

```java
if (조건식) {
	// 조건식이 ture이면 실행되는 코드 Block
}
```

- 소괄호 안에는 ```boolean``` 값으로 평가될 수 있는 조건식을 넣어준다.
- 중괄호 안에는 조건식이 참일 때 실행하고자 하는 코드를 넣어준다.

<br>


### if ~ else 문

<br>

```java
if(조건식1) {
	// 조건식1이 true이면 실행되는 코드 Block
} 
else if (조건식2) {
	// 조건식1이 false, 조건식2가 true일 때 실행되는 코드 Block
	// else if 문은 반복 사용 가능
} 
else {
	// 조건식1과 2가 모두 false 일때 실행되는 코드 Block
	// else문은 생략 가능
}
```

- ```if``` 문이 참이 아니면 ```else if```를 실행한다.
- ```else if```도 참이 아니면 ```else```를 실행한다.
- ```else if```는 여러번 사용 될 수 있다.

<br>

### switch 문

<br>

```java
switch (num) {
    case "1":
        System.out.println("1번");
        break; // 다음 case를 실행하지 않고, switch문 탈출
			   // break; 가 없으면 다음 break; 가 나올때 까지 실행
    case "2":
        System.out.println("2번");
        break;

    default: // switch문의 값과 같은 값이 없으면, default 실행
        System.out.println("default");
        break;
}
```

- ```switch``` 문은 변수가 어떤 값을 갖느냐에 따라 실행문이 선택된다.
- ```default```는 생략 가능하다.
- ```int, char, String``` 타입의 변수를 입력받을 수 있다.

<br>

### [📋 **JAVA 14에서 향상된 Switch문 사용법**](https://docs.oracle.com/en/java/javase/14/language/switch-expressions.html)

<br>

***

<br>


## 반복문

<br>

### for 문

<br>

```for(초기화 ; 조건식 ; 증감식)``` 의 형태로 조건식이 참이면 반복 실행한다.

```java
for(int num = 0; num < 10; num++) {	
	System.out.print(num + " ");
}
// 출력 결과 : 0 1 2 3 4 5 6 7 8 9 
```

<br>

### for each 문

<br>

동일한 ```for```를 사용하지만 조건식 부분의 문법이 조금 다르다.

```java
String[] numbers = {"one", "two", "three"};
for(String number: numbers) {
    System.out.print(number + " ");
}
// 출력 결과 : one two three
```

```numbers``` 배열의 값을 ```number``` 변수에 저장하며 반복한다.

<br>

### While 문

<br>

```java
int num = 0; 		// 초기화
while(num < 10) {	// 조건식
	System.out.print(num + " ");
	num ++;			// 증감식
}
// 출력 결과 : 0 1 2 3 4 5 6 7 8 9
```

위의 ```for``` 문과 동일한 반복문이다.


<br>

### do - While 문

<br>

```java
do {
  반복문				// 처음 한 번은 무조건 실행
} while(조건식) {		// 조건식이 true이면 do를 계속 실행
	종료문				// 조건식이 false 이면 종료문 실행
}
```

<br>

### break, continue 문

<br>

```break``` 문은 대개 ```if``` 문과 같이 사용된다.  
조건식에 따라 반복문을 종료할 때 사용한다.

```continue``` 문은 ```break``` 문과는 다르게 반복문을 종료하지 않고,   
해당 코드를 넘기고 다음 반복문으로 넘어간다.

<br>

***

<span style="color: gray ; display: inline-block; width: 95%; text-align: right;">_2022.08.31._</span>