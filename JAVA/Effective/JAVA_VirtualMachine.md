# 자바 가상 머신 (Java Virtual Machine)

자바 이전에는 C++이 프로그래밍 언어로 많이 사용되었지만,  
Windows를 위해 만든 프로그램은 Windows만 작동이 가능한 운영체제 독립성이 없었다.

이러한 문제를 자바는 **JVM**이라는 별도의 프로그램을 통해 해결하고, 운영체제의 독립성을 갖췄다.

<br>

## JVM

JVM은 자바 프로그램을 실행시키는 도구로써,  
자바로 작성한 소스 코드를 해석해 실행하는 별도의 프로그램이다.

JVM은 자바 프로그램과 운영체제 사이의 **매개 역할**을 하며,  
Windows용 JVM, Mac OS용 JVM, Linux용 JVM이 따로 존재한다.

<img src = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99E947465A3BC25206"/>

▲ _JVM의 운영체제 독립성_

<br><br>

## JVM의 구조

<br>

<img src = "https://user-images.githubusercontent.com/51476083/113502632-7959d580-9568-11eb-8457-edc8b60b0bac.png"/>

▲ _JVM의 구조_

<br>

### 💡 **자바로 소스코드를 작성하고 실행하는 과정**

1. 컴파일러가 실행되면서 자바 소스 코드 파일(```.java```)이 바이트 코드 파일(```.class```)로 변환된다.

2. JVM이 운영체제로부터 소스 코드 실행에 필요한 메모리를 할당 받는다. (Runtime Data Area)

3. 클래스 로더(Class Loader)가 바이트 코드 파일을 런타임 데이터 영역에 적재시킨다. (메모리에 로드)

4. 실행 엔진(Excution Engine)이 런타임 영역의 바이트 코드를 실행시킨다.
    - Interpreter를 통해 코드를 한 줄씩 기계어로 번역하고 실행
    - JIT Compiler(Just-In-Time Compiler)를 통해 바이트 코드 전체를 기계어로 번역하고 실행
  
    > 기본적으로는 Interpreter를 통해 바이트 코드를 실행한다.
    > 
    > 특정 바이트 코드가 자주 실행되면  
    > JIT Compiler를 통해 해당 코드를 실행한다.

<br>

### 📋 [**_JVM에 대한 더 많은 정보_**](https://deepu.tech/memory-management-in-jvm/)

<br>

***

<br>

## Runtime Data Area

<br>

런타임 데이터 영역은 JVM에 프로그램에 로드되어 실행될 때  
특정 값, 바이트 코드, 객체, 변수 등과 같은 데이터을 담는 메모리 영역이다.

<img src = "https://tecoble.techcourse.co.kr/static/a0b18cc999920474a1852901e1e46ebf/6f641/2021-08-09-jvm-runtime-data-area-structure.png"/>

▲ _JVM Runtime Data Area_

<br>

### Stack

<br>

스택은 자료구조의 일종으로 _Last In First Out_ 이라는 키워드로 설명할 수 있다.

<img src = "https://res.cloudinary.com/practicaldev/image/fetch/s--s1Qbl8Gf--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/mwcwre09s12vqa3gvl7a.png"/>

<br>

JVM에서도 이와 같은 원리로 작동한다.  

메서드를 호출하면 생성되는 Method Frame에는 변수, 리턴 값 등 연산 과정에서 발생하는 값들이 임시로 저장되는데,  
이러한 Method Frame이 Stack 영역에 순서대로 쌓이고, 동작이 완료되면 역순으로 제거된다.

<br>

### Heap

<br>

위의 사진에서 본 것 처럼 JVM에 Heap 영역은 단 하나만 존재하고,  
Heap 영역에는 객체, 인스턴스 변수, 배열이 저장된다.

```java
Example example = new Example();
```

위 코드처럼 새로운 인스턴스를 생성하면 Heap 영역에 인스턴스가 생성되며  
```example``` 변수는 Stack 영역에서 Heap 영역에 존재하는 실제 데이터의 주소값을 할당받는다.

> Stack 영역에 저장되어 있는 참조 변수를 통해 Heap 영역에 존재하는 객체를 다룬다.

<br>

<img src = "https://miro.medium.com/max/700/1*CuZtwu4B_k6T8gKY2vZmMA.jpeg"/>

<br>

***

<br>

## Garbage Collector

JVM의 구조를 살펴보면 크게 **Runtime Data Area, Excution Engine, Garbage Collector**로 나눌 수 있다.

그 중 Garbage Collector(GC)는 프로그램에서 더 이상 사용하지 않는 객체를 삭제·제거하여 **메모리를 자동으로 관리**하는 일을 한다.

```java
Example example = new Example();
example.setName("예제1");
example = null; // 인스턴스와 참조변수간의 연결 끊기
// Garbage 발생
```

<br>

### 동작 방식

<br>

<img src = "https://miro.medium.com/max/700/1*bfaBln8nWqy5dCE4Dtqq0Q.png"/>

▲ _Heap Memory Area_

Heap 영역은 객체가 얼마나 살아있냐에 따라 Young Generation과 Old Generation으로 나눌 수 있다.

<br>

Young 영역에서는 새롭게 생성되는 객체들이 할당되는 곳이라 많은 객체가 생성되고 사라지며,  
Young 영역에서 발생하는 GC를 **Minor GC**라고 한다.

Old 영역에서는 Young 영역에서 살아남은 객체들이 복사되는 곳으로  
Young 영역보다 크기가 크기 때문에 GC는 적게 발생한다.  
또한, Old 영역에서 발생하는 GC를 **Major GC**라고 한다.

<br>

GC 실행의 단계

1. **Stop The World**  
   가비지 컬렉션을 실행시키기 위해 App의 실행을 멈추는 작업으로,  
   가비지 컬렉션을 실행하는 스레드를 제외한 모든 스레드의 작업은 중단되고, 정리가 완료되면 재개된다.

2. **Mark & Sweep**  
  Mark는 사용되는 메모리와 그렇지 않은 메모리를 식별하는 작업이고,  
  Sweep은 Mark 단계에서 사용되지 않음으로 식별된 메모리를 해제하는 작업이다.

<br>

### 📋 [**_Garbage Collector에 대한 정보_**](https://memostack.tistory.com/228)

<br>

***

_2022.09.19. Update_