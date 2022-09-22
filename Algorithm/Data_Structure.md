# 자료 구조

자료 구조란 여러 데이터의 묶음을 저장하고, 사용하는 방법을 정의한 것으로

자료 구조의 종류로는 ```Stack```, ```Queue```, ```Graph```, ```Tree```, ```Binary Search Tree```, ```Search Algorithm```, ```Deque```, ```Linked List```, ```Hash Table```, ```Heap Tree``` 등 여러 종류가 존재한다.

<img src = "https://cheris8.github.io/assets/images/PY/datastructure-overview.png"/>

▲ _Type of Data Structure_

<br>

## Stack (스택)

Stack은 data를 순서대로 쌓는 자료 구조이다.

<img src = "https://images.velog.io/images/sbinha/post/17a3cf61-fb95-4970-b66c-92a71b99846b/Screenshot%202020-04-20%2019.07.55.png" width = "90%"/>

▲ _Stack_

<br>

### Stack의 특징

1. Last In First Out (LIFO)
   
2. 데이터는 하나씩 넣고 뺄 수 있다.  

3. 하나의 입출력 방향을 가지고 있다.  

<br>

### Stack 메서드

```java
import java.util.Stack;

Stack<T> stack = new Stack<>();
```
▲ _Stack 선언_

<br>

**Stack 메서드**

|기능	|리턴<br>타입	|메서드	|설명|
|:-:|:-:|:-----|:-|
|객체<br>추가|Element|```push(e)``` | Stack에 데이터 추가|
|객체<br>삭제|Element|```pop()``` | 가장 위쪽의 데이터 제거|
||void|```clear()``` | Stack의 데이터 모두 제거|
|객체<br>검색|Element|```peek()``` | Stack 가장 상단의 데이터 출력|
||int|```size()``` | Stack의 크기 출력|
||int|```search(Object o)``` | Object의 index 리턴 <br> index는 스택의 최상단이 1부터 시작 <br> Object를 찾을 수 없는 경우 ```-1``` 리턴|
||boolean|```empty()``` | Stack이 비어있는지 확인|
||boolean|```contains(e)``` | Stack에 Object가 있는지 확인|
||String|```show()```| Stack에 포함된 모든 데이터를 String 타입으로 반환하여 리턴|

<br>

## Queue (큐)

Queue는 data가 입력된 순서대로 처리하는 자료 구조이다.

<img src = "https://programmathically.com/wp-content/uploads/2021/06/queue.png" width = "90%"/>

▲ _Queue_

<br>

### Queue의 특징

1. First in First Out (FIFO)

2. 데이터는 하나씩 넣고 뺄 수 있다.

3. 두 개의 입출력 방향을 가지고 있다.  
   입력이 이루어지는 쪽을 ```rear```, 출력이 이루어지는 쪽을 ```front```라고 한다.  
   값을 입력할수록 rear 값이 증가하고, 값을 출력할수록 front 값이 증가한다.

<br>

### Queue 메서드

```java
import java.util.Queue;
import java.util.LinkedList;

Queue<T> queue = new LinkedList<>();
```
▲ _Queue 선언_

<br>

**Queue 메서드**

|기능	|리턴<br>타입	|메서드	|설명|
|:-:|:-:|:-----|:------------------|
|객체<br>추가| boolean | ```add(e)``` | Queue에 데이터 추가 <br> 큐가 꽉 찬 경우 ```IllegalStateException``` 발생 |
|| boolean | ```offer(e)``` | Queue에 데이터 추가 / 실패 시 ```false``` 리턴 |
|객체<br>삭제| Element | ```poll()``` | Queue 가장 앞쪽의 데이터 제거<br>큐가 비어있으면 ```null``` 리턴 |
|| Element | ```remove()``` | 인자가 없는 경우 Queue 가장 앞쪽의 데이터 제거 <br> 큐가 비어있는 경우 ```NoSuchElementException``` 발생  |
||boolean| ```remove(o)``` | 인자가 있는 경우 해당 데이터를 제거 / 실패 시 ```false``` 리턴 |
|| void | ```clear()``` | Queue의 데이터 모두 제거 |
|객체<br>검색| Element | ```peek()```  | Queue 가장 앞쪽의 데이터 출력 <br> 인자가 없는 경우 ```null``` 리턴|
|| Element | ```element()```  | Queue 가장 앞쪽의 데이터 출력 <br> 큐가 비어있는 경우 ```NoSuchElementException``` 발생 |

<br>

### 📋 [**_추가 : Circle Queue_**](https://haruhiism.tistory.com/144)


<br>

***

_2022.09.22. Update_