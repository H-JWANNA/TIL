# 배열

## 1차원 배열

JAVA의 배열은 기본적으로 참조 타입이며, 가변배열이 아니다.  
배열을 선언할 때, 배열의 범위(주소값)를 지정하고 해당 범위에 값을 할당한다.

<br>

▼  _JAVA 1차원 배열 선언 및 할당(초기화) 방법_
```java
String[] arr;           
arr = new String[5];    

String[] arr = new String[5];

String[] arr = {"1", "2", "3", "4", "5"};
```

```arr[n]```를 사용해 배열의 index에 해당하는 값을 찾을 수 있고,  
```arr.length```를 사용해 배열의 크기를 알아낼 수 있다.

```Arrays.toString(arr)``` 메소드를 이용해 배열의 요소를 확인할 수 있다.  
해당 메소드를 사용하지 않으면 배열의 주소값들이 출력된다.
> ```import java.util.Arrays```를 통해 호출할 수 있다.

<br>

***

<br>

## 2차원 배열

2차원 배열의 선언 및 할당(초기화) 방법은 1차원 배열과 유사하다.

<br>

▼  _JAVA 2차원 배열 선언 및 할당(초기화) 방법_
```java
int[][] arr;
arr = new int[2][3];

int[][] arr = new int[2][3];

int[][] arr = {{1,2,3}, {1,2,3}};
```

<br>

1차원 배열과 다차원 배열의 차이점으로는 마지막 배열에 배열의 길이를 고려하지 않는 **가변 배열**의 형태로 만들 수 있다는 점이다.

```java
int[][] ages = {
	{ 30, 32, 39, 59, 23 },
	{ 31, 41, 52, 56, 72, 13 },
	{ 45, 32, 84, 23, 13, 42, 55 },
	{ 23, 41, 62, 64, 23, 51, 67, 98 },
	{ 13, 14, 17, 84, 52, 37, 68, 66, 33 }
};
```

<br>

***

<br>

## 추가로 공부한 메소드

<br>

### ```isEmpty()```

- ```str.isEmpty()```의 형식으로 사용하며, 객체가 비어있을 경우 ```boolean``` 값인 ```true```를 리턴한다.
- 비슷한 느낌으로 ```== null```과 ```.isBlank();```가 있다.  

| str | str == null | str.isEmpty() | str.isBlank() |
|:----:|:----:|:--------------------:|:---:|
| null | true | NullPointerException | NullPointerException |
| "" | false | true | true |
| " " | false | false | true |
| "hi" | false | false | false |

<br>

### ```System.arraycopy()```

- ```System.arraycopy(arr1, arr1-index, arr2, arr2-index, length)```의 형식으로 사용하며,  
arr2의 arr2-index에 arr1의 arr1-index값을 length만큼 복사한다.

▼  _예제 코드_

```java
char[] arr1 = {'A', 'B', 'C', 'D', 'E'};    
char[] arr2 = {'가', '나', '다', '라', '마'};

System.arraycopy(arr1, 1, arr2, 3, 2);
System.out.println(Arrays.toString(arr2));  // [가, 나, 다, B, C]
```

<br>

### ```Arrays.copyOf()```

- ```Arrays.copyOf(arr, length)``` 형식으로 사용하며, 배열의 크기를 줄이거나 늘릴 때 사용한다.
- 새로운 배열에 사용하면 원본 배열의 값은 바뀌지 않는다는 특징이 있다.

▼  _예제 코드_

```java
int[] arr = { 10, 20, 30 };
System.out.println(Arrays.toString(arr));   //  [10, 20, 30]

arr = Arrays.copyOf(arr, arr.length + 1);
arr[arr.length - 1] = 40;
System.out.println(Arrays.toString(arr));   //  [10, 20, 30, 40]
```

<br>

### ```Arrays.copyOfRange()```

- ```Arrays.copyOf(arr, start-index, end-index)``` 형식으로 사용하며, ```Array.copyOf()```에서 복사할 위치의 시작 인덱스와 끝 인덱스를 지정해줌으로써, 배열의 원하는 위치를 복사할 수 있다.

▼  _예제 코드_

```java
int[] arr = { 10, 20, 30, 40, 50, 60 };
System.out.println(Arrays.toString(arr));   //  [10, 20, 30, 40, 50, 60]

arr = Arrays.copyOfRange(arr, 2, 5);
System.out.println(Arrays.toString(arr));   //  [30, 40, 50]
```

<br>

### ```replace()```

- ```a.replace(기존문자, 바꿀문자)``` 형식으로 사용하며, 문자열에서 바꾸고 싶은 문자를 치환시켜준다.



<br>

***

<span style="color: gray ; display: inline-block; width: 95%; text-align: right;">_2022.09.01._</span>