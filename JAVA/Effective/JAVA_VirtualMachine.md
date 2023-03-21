# 자바 가상 머신 (Java Virtual Machine)

자바 이전에는 C++이 프로그래밍 언어로 많이 사용되었지만,  
Windows를 위해 만든 프로그램은 Windows만 작동이 가능한 운영체제 독립성이 없었다.

이러한 문제를 자바는 **JVM**이라는 별도의 프로그램을 통해 해결하고, 운영체제의 독립성을 갖췄다.

<br>

## JVM

JVM은 자바 프로그램을 실행시키는 도구로써, 자바로 작성한 소스 코드를 해석해 실행하는 별도의 프로그램이다.

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

    <br>  

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

Thread마다 생성되는 Stack 영역, PC Register, Native Method Stack과  
모든 스레드가 공유하는 Heap 영역과 Method 영역이 있다.

<img src = "https://tecoble.techcourse.co.kr/static/a0b18cc999920474a1852901e1e46ebf/6f641/2021-08-09-jvm-runtime-data-area-structure.png"/>

▲ _JVM Runtime Data Area_

<br>

### Stack

스택은 자료구조의 일종으로 _Last In First Out_ 이라는 키워드로 설명할 수 있다.

<img src = "https://res.cloudinary.com/practicaldev/image/fetch/s--s1Qbl8Gf--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_auto%2Cw_880/https://thepracticaldev.s3.amazonaws.com/i/mwcwre09s12vqa3gvl7a.png"/>

<br>

JVM에서도 이와 같은 원리로 작동한다.  

메서드를 호출하면 생성되는 Method Frame에는 변수, 리턴 값 등 연산 과정에서 발생하는 값들이 임시로 저장되는데,  
이러한 Method Frame이 Stack 영역에 순서대로 쌓이고, 동작이 완료되면 역순으로 제거된다.

<br>

### PC Register

<br>

PC(Program Counter) 레지스터는 현재 수행 중인 명령의 주소를 가지며, 스레드가 시작될 때 생성된다.

<br>

### Native Method Stack

Java 외의 언어로 작성된 네이티브 코드를 위한 스택이다.

JNI(Java Native Interface)를 통해 호출하는 C/C++ 등의 코드를 수행하기 위한 스택으로  
언어에 맞게 스택이 생성된다.

<br>

### Heap

객체 또는 인스턴스를 저장하는 공간으로 GC 대상이다.

```java
Example example = new Example();
```

위 코드처럼 새로운 인스턴스를 생성하면 Heap 영역에 인스턴스가 생성되며  
```example``` 변수는 Stack 영역에서 Heap 영역에 존재하는 실제 데이터의 주소값을 할당받는다.

> Stack 영역에 저장되어 있는 참조 변수를 통해 Heap 영역에 존재하는 객체를 다룬다.

<br>

<img src = "https://miro.medium.com/max/700/1*CuZtwu4B_k6T8gKY2vZmMA.jpeg"/>

<br>

### Method Area

JVM이 시작될 때 생성되며, JVM이 읽어들인 각 클래스와 인터페이스에 대한 런타임 상수 풀, 필드와 메서드에 대한 정보, Static 변수, 메서드의 바이트 코드 등을 저장한다.

**💡 런타임 상수 풀 (Runtime Constant Pool)**  

- JVM 동작에서 핵심적인 역할을 수행하는 곳
- 각 클래스와 인터페이스의 상수 뿐만 아니라, 메서드와 필드에 대한 레퍼런스까지 담고 있는 테이블
- 어떤 메서드나 필드를 참조할 때, JVM은 런타임 상수 풀을 통해 해당 메서드나 필드의 실제 메모리상 주소를 찾아서 참조한다.

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

**💡 Eden 영역** 

- 새롭게 생성되는 객체가 위치하는 공간
- Eden 영역이 가득차면 아직 사용중인 객체를 Mark해서 Survivor 영역에 복사하고, Eden 영역을 비운다.

<br>

**💡 Survivor 영역** 

- Survivor 영역도 마찬가지로 가득차게 되면 아직 사용중인 객체를 Mark해서 다음 Survivor 영역 혹은 Old Generation으로 복사한 후 해당 영역을 비우게 된다.

<br>

Old 영역에서는 Young 영역에서 살아남은 객체들이 복사되는 곳으로 Young 영역보다 크기가 크기 때문에 GC는 적게 발생한다.  
또한, Old 영역에서 발생하는 GC를 **Major GC**라고 한다.

> 크기가 크기 때문에 GC가 적게 발생하지만, 그만큼 제거하는데 시간이 오래 걸린다.

<br>

Old 영역은 Young 영역과 반대로 현재 사용중인 객체가 아닌 참조가 끝난 객체를 찾아서 제거한다.

<br>

### GC 실행의 단계

1. **Stop The World**  
   가비지 컬렉션을 실행시키기 위해 App의 실행을 멈추는 작업으로,  
   가비지 컬렉션을 실행하는 스레드를 제외한 모든 스레드의 작업은 중단되고, 정리가 완료되면 재개된다.

   Stop The World가 발생하는 시간을 줄이기 위해 JVM 튜닝을 하기도 하는데, 이를 GC 튜닝이라고 한다.

2. **Mark & Sweep**  
  Mark는 사용되는 메모리와 그렇지 않은 메모리를 식별하는 작업이고,  
  Sweep은 Mark 단계에서 사용되지 않음으로 식별된 메모리를 해제하는 작업이다.

<br>

### 📋 [**_Garbage Collector에 대한 정보_**](https://memostack.tistory.com/228)

<br>

***

_2023.03.21. Update_

_2022.09.19. Update_