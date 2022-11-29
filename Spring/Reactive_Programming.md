# 리액티브 프로그래밍 (Reactive Programming)

리액티브 프로그래밍은 리액티브 시스템(Reactive System)에서 사용되는 프로그래밍 모델을 말한다.

리액티브 시스템에서의 메시지 기반 통신(Message Driven)은 Non-Blocking 통신과 유기적인 관게를 맺고 있으며,  
리액티브 프로그래밍은 Non-Blocking 통신을 위한 프로그래밍 모델이다.

<br>

**💡 리액티브 프로그래밍 특징**

- declarative programming paradigm  
  
  리액티브 프로그래밍은 선언형 프로그래밍 방식을 사용하는 대표적인 프로그래밍 모델이다.  

- data streams and the propagation of change  

  Data Streams는 지속적으로 데이터가 입력으로 들어올 수 있음을 의미하며,  
  리액티브 프로그래밍에서는 데이터가 지속적으로 발생하는 것 자체를 데이터에 어떠한 변경이 발생함을 의미한다.  

  또한, 이 변경 자체를 이벤트로 간주하고, 이벤트가 발생할 때마다 데이터를 계속해서 전달한다.

- automatic propagation of the changed data flow  

  지속적으로 발생하는 데이터를 하나의 데이터 플로우로 보고 데이터를 자동으로 전달한다.

<br>

## 리액티브 시스템 (Reactive System)

리액티브 시스템은 클라이언트의 요청에 반응을 잘하는 시스템을 의미한다.

<br>

자세히 말하면 클라이언트의 요청에 대한 응답 대기 시간을 최소화 할 수 있도록  

요청 쓰레드가 차단되지 않게 함으로써(Non-Blocking) 클라이언트에게 즉각적으로 반응하도록 구성된 시스템이다.

<br>

### 💡 리액티브 시스템의 특징

<img src = "https://www.reactivemanifesto.org/images/reactive-traits.svg" width = "500" />

▲ _리액티브 시스템의 설계 원칙_

<br>

리액티브 시스템의 설계 원칙을 기반으로 리액티브 시스템의 특징을 확인할 수 있다.

<br>

- **MEANS** : 리액티브 시스템에서 사용하는 커뮤니케이션 수단을 의미  
  
  - Message Driven : 리액티브 시스템에서는 메시지 기반 통신을 통해 여러 시스템 간에 느슨한 결합을 유지한다.

<br>

- **FORM** : 메시지 기반 통신을 통해 리액티브 시스템이 어떤 특정을 가지는 구조로 형성되는지를 의미  
  
  - Elastic : 시스템으로 들어오는 요청량에 상관없이 일정한 응답성을 유지하는 것을 말한다.
  
  - Resillient : 시스템의 일부분에 장애가 발생하더라도 응답성을 유지하는 것을 말한다.

<br>

- **VALUE** : 리액티브 시스템의 핵심 가치가 무엇인지를 표현하는 영역  

  - Responsive : 리액티브 시스템은 클라이언트 요청에 즉각적으로 응답할 수 있어야 함을 말한다.

  - Maintainable : 클라이언트 요청에 대한 즉각적인 응답이 지속 가능해야 함을 말한다.

  - Extensible : 클라이언트 요청에 대한 처리량을 자동으로 확장하고 축소할 수 있어야 함을 말한다.
  
<br>

***

<br>

## 리액티브 스트림즈 (Reactive Streams)

리액티브 프로그래밍을 위한 표존 사양(Specification)을 의미한다.

<br>

### Reactive Streams Component (구성 요소)

- **Publisher**  
  
  Publisher 인터페이스는 데이터 소스로부터 데이터를 내보내는(emit) 역할을 한다.

  ```java
  public interface Publisher<T> {
    public void subscribe(Subscriber<? super T> s);
  }
  ```

  - ```subscribe()```  
    Publisher가 내보내는 데이터를 수신할지 여부를 결정하는 **구독**의 의미를 가지고 있으며,  
    일반적으로 subscribe()가 호출되지 않으면 Publisher가 데이터를 내보내는 프로세스는 시작되지 않는다.

<br>

- **Subscriber**  
  
  Subscriber 인터페이스는 Publisher로부터 내보내진 데이터를 소비하는 역할을 한다.  

  ```java
  public interface Subscriber<T> {
    public void onSubscribe(Subscription s);
    public void onNext(T t);
    public void onError(Throwable t);
    public void onComplete();
  }
  ```

  - ```onSubscribe(Subscription s)```  
    구독이 시작되는 시점에 호출되며, 메서드 내에서 Publisher에게 요청할 데이터 개수를 지정하거나 구독 해지 처리를 할 수 있다.

  - ```onNext(T t)```  
    Publisher가 데이터를 내보낼 때 (emit) 호출되며, emit된 데이터를 전달 받아서 소비할 수 있다.

  - ```onError(Throwable t)```  
    Publisher로부터 emit된 데이터가 Subscriber에게 전달되는 과정에서 에러가 발생될 경우에 호출된다.

  - ```onComplete()```  
    Publisher가 데이터를 emit하는 과정이 종료될 경우 호출되며,  
    데이터의 emit이 정상적으로 완료된 후 처리할 작업이 있다면 onComplete() 내에서 수행할 수 있다.

<br>

- **Subscription**  

  Subscription 인터페이스는 Subscriber의 구독 자체를 표현한 인터페이스이다.  

  ```java
  public interface Subscription {
    public void request(long n);
    public void cancel();
  }
  ```

  - ```request(long n)```  
    Publisher가 emit하는 데이터의 개수를 요청한다.

  - ```cancel()```  
    구독을 해지하는 역할을 한다.  
    구독 해지가 발생하면 Publisher는 더이상 데이터를 emit하지 않는다.

<br>

- **Processor**

  Processor 인터페이스는 Subscriber와 Publisher의 역할을 동시에 할 수 있다는 특징을 가지고 있다.

  ```java
  public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
  }
  ```

<br>

### Reactive Streams의 구현체

- **Project Reactor**

  리액티브 스트림즈를 구현한 대표적인 구현체로, Spring과 가장 궁합이 잘 맞는다.

<br>

- **RxJava**

  .NET 기반의 리액티브 라이브러리를 넷플릭스에서 Java 언어로 포팅한 JVM 기반의 리액티브 확장 라이브러리이다.

  RxJava의 경우 2.0부터 리액티브 스트림즈 표준 사양을 준수하며, 이전 버전의 컴포넌트와 함께 혼용되어 사용되고 있다.

<br>

- **Java Flow API**

  Java Flow API는 리액티브 스트림즈를 구현한 구현체가 아닌, 리액티브 스트림즈 표준 사양을 Java 안에 포함시킨 구조이다.

  즉, 리액티브 스트림즈 사양을 구현한 여러 구현체들에 대해 SPI(Service Provider Interface) 역할을 한다.

<br>

- **Reactive Extension (기타 리액티브 확장)**

  특정 언어에서 리액티브 스트림즈를 구현한 별도의 구현체가 존재한다는 의미로,  
  실제로 다양한 프로그래밍 언어에서 리액티브 스트림즈를 구현한 리액티브 확장 라이브러리를 제공한다.

  > 대표적인 리액티브 확장 라이브러리는 RxJava, RxJS, RxAndroid, RxKotlin, RxPython, RxScala 등이 있다.
  > 
  > 해당 확장 라이브러리들의 Rx가 Reactive Extension의 줄임말이다.

<br><br>

***

_2022.11.29. Update_