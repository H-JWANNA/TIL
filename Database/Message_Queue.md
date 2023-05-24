# Message Queue (메시지 큐, MQ)

메시지 큐는 **메시지 지향 미들웨어(Message Oriented Middleware, MOM)** 를 구현한 시스템으로  

메시지를 이용하여 여러 어플리케이션 및 서비스를 연결해주는 역할을 한다.

<br>

## 메시지 지향 미들웨어 (MOM)

분산 응용 프로그램 간에 메시지를 보내고 받으면서 데이터를 전달하고 교환할 수 있게 해주는 미들웨어이다.

> **💡 미들웨어 (Middleware)**
>
> 운영체제(OS)와 응용 프로그램 또는 서버와 클라이언트 사이에서 다양한 서비스를 제공하는 소프트웨어
>
> 쉽게 말해, **어플리케이션의 통신에 사용되는 소프트웨어**를 말한다.

<br>

<img src = "https://docs.oracle.com/cd/E19148-01/820-0532/images/to_MOM.gif" width = 600>

▲ _MOM 기반 시스템_

- **클라이언트** : 메시지를 주고 받는 주체

- **메시지** : 전송되는 데이터

- **메시징 공급자 (Message Broker)** : 요청된 메시지를 대상(Destination)에 저장하고,  
   수신측 클라이언트의 요청이 발생할 때까지 보관한다.

<br>

메시지 지향 미들웨어는 **point-to-point messaging** 또는 **publish-subscribe messaging** 방식으로 통신한다.

<br>

### 🔸 Point-to-Point Messaging (지점 간 메시징)

<br>

메시지 생산자를 Sender, 수신자를 Receiver, 메시지 목적지를 Queue라고 한다.

큐에서 보관하던 메시지를 Receiver가 받아간다면 메시지는 즉시 삭제된다.

<br>

### 🔸 Publish-Subscribe Messaging (발행-구독 메시징)

<br>

메시지 생산자를 Publisher, 수신자를 Subscriber, 메시지 목적지를 topic라고 한다.

Subscriber는 Topic 메시지의 복사본을 가져가기 때문에 메시지가 즉시 삭제되지 않는다.

<br><br>

***

_2023.05.24. Update_