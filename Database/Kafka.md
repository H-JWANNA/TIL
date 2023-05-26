# Apache Kafka

Kafka는 실시간 데이터 처리에 특화된 이벤트 스트리밍 플랫폼이다.

Kafka를 사용하면 아래와 같이 시스템 간의 직접적인 연결을 중재하여 의존성을 낮출 수 있고,  
변동이나 장애 상황으로부터 비교적 자유로워질 수 있다.

<br>

<img src = "./src/Before_Kafka.png" width = 600>

▲ _Point-to-Point 구조_

<br>

<img src = "./src/After_Kafka.png" width = 600>

▲ _Publish-Subscribe 구조_

<br><br>

또한 아래와 같이 동기 방식의 백엔드 어플리케이션에서는 

API 응답이나 시스템 처리량이 제한적이고, API 자체가 실패하는 경우가 발생하여 사용자의 불만이나 재처리 이슈가 발생할 수 있다.

<br>

<img src = "./src/Sync_Architecture.png" width = 600>

▲ _동기 방식의 상품 등록 시스템 프로세스_

<br>

이를 Kafka를 사용하여 비동기 방식으로 개선하면 

API 요청과 무관하게 독립적으로 로직을 실행할 수 있고, 필요에 따라 Consumer를 증설하여 독자적으로 처리량을 확대할 수 있다.


<img src = "./src/Async_Architecture_for_Kafka.png" width = 600>

▲ _비동기 방식의 상품 등록 시스템 프로세스_

<br>

***

<br>

## 🔸 Kafka 구조

<img src = "./src/Kafka_Structure.png" width = 600>

▲ _Kafka 구조_

<br>

특정 노드의 갑작스러운 장애 상황에서도 서비스 중단이나 데이터 유실 없이 안정적으로 시스템을 유지하기 위해 **Cluster 형태**로 구성한다.

데이터 복제(Replication)와 결합하여 흔히 **고가용성 구성**이라고 한다.

<br><br>

<img src = "./src/Kafka_Structure_of_Cluster.png" width = 600>

▲ _Kafka Cluster 구조_

- Leader Partition : Producer와 Consumer가 연결되어 쓰기와 읽기가 실행되는 파티션

- Follower Partition : Replication Factor에 의해 만들어진 복제본으로 Leader Partition에 문제가 생겼을 때 언제라도 Leader가 될 수 있도록 데이터를 지속적으로 체크하여 만약의 장애 상황을 대비하는 파티션

<br>

### 🚨 장애 상황 발생

1. Leader Partition에 장애가 발생하면 Kafka Cluster 내의 컨트롤러 모듈이 탐지한다.

2. Follow Partition을 Leader로 만들어 서비스를 지속하도록 한다.

3. Leader 교체 순간에는 일시적으로 쓰기・읽기 타임 아웃이 발생할 수 있는데,  
   Producer는 새로운 Leader Partition이 확정될 때까지 Retry를 통해 서비스 영향을 최소화한다.

4. 새로운 Leader 선정이 완료되면 Producer와 Consumer는 다시 정상적 발행을 시작한다.

<br><br>

<img src = "./src/Kafka_Structure_of_Partition.png" width = 600>

▲ _Kafka Partition 구조_

<br>

Partition을 여러 개 만들게 되면 Consumer Group 내의 Consumer 숫자를 증가시켜 병렬 처리 능력을 향상시킬 수 있다.

<br>

Topic이 여러 개의 Partition으로 나뉘게 되면 Producer의 메시지 발행 시 여러 메시지를 어떤 Partition에 기록할지 결정할 방법이 필요한데,  

일반적으로 발행 메시지 내에 키 값이 비어있으면 메시지들은 모든 Partition에 라운드 로빈 방식으로 기록된다.

<br>

단, Consumer의 메시지 처리 순서를 보장하기 위해서는 Partition을 하나만 사용하거나  

여러 개의 Partition 사용시에는 라운드 로빈 방식이 아닌  

발행 메시지의 키 설정을 통해 특정 키의 경우 동일한 Partition에 할당되도록 강제하여 하나의 Partition 내에서는 순서를 보장할 수 있다.

<br>

> **💡 라운드 로빈 (Round Robin)**
>
> 그룹 내에 있는 요소들을 합리적인 순서에 입각하여 뽑는 방법  
> 
> 보통 리스트의 **맨 위에서 아래로 가며 하나씩** 뽑는 방식으로 진행된다.

<br>

***

<br>

## 🔸 Kafka 용어

|용어|설명|
|:-:|:-|
|**Zookeeper**|Kafka 브로커를 하나의 클러스터로 코디네이트하는 분산 코디네이션 시스템|
|**Kafka Broker**|Kafka 어플리케이션이 설치되어 있는 서버 또는 노드|
|**Topic**|Kafka 데이터 저장소 (폴더와 비슷한 개념)|
|**Partition**|각 Topic 당 데이터를 분산 처리하는 단위|
|**Offset**|Partition 내의 각 레코드를 고유하게 식별하는 순차적인 ID|
|**Producer**|메시지를 Broker의 Topic에 전달하는 어플리케이션 (Publisher)|
|**Consumer**|Broker의 Topic에 저장된 메시지를 가져가는 어플리케이션 (Subscriber)|
|**Consumer Group Rebalancing**|Consumer 그룹 내의 각 컴슈머의 파티션 소유권 이관 작업|
|**Broker Partition Replication**|Partition 복제 기능|
|**ISR**<br>**(In Sync Replica)**|Leader, Follower Partition이 모두 동기화 된 상태|
|**Consumer Lag**|Topic의 최신 Offset과 Consumer Offset 간의 차이|

<br>

### ▫️ Zookeeper

Zookeeper는 분산 어플리케이션을 위한 코디네이션 시스템이다.

분산 어플리케이션이 안정적인 서비스를 할 수 있도록 분산된 어플리케이션 정보를 중앙에 집중하고 구성 관리, 동기화 등의 서비스를 제공한다.

<img src = "https://zookeeper.apache.org/doc/r3.5.1-alpha/images/zkservice.jpg" width = 600>

▲ _Zookeeper Service_

<br>

Zookeeper는 서버 여러 대를 Cluster로 구성하고, 서버들의 상태 정보들을 Znode에 저장한다.

> **💡 Znode**
>
> 데이터를 저장하기 위한 공간의 명칭
>
> Znode의 데이터가 변경될 때마다 Znode의 버전 정보가 증가한다.  
> 데이터는 메모리에 저장되므로 처리량이 매우 크고 속도가 빠르다.

<img src = "https://zookeeper.apache.org/doc/r3.5.1-alpha/images/zknamespace.jpg" width = 600>

▲ _Znode_

<br>

### ▫️ Kafka Broker

Kafka Broker는 실행된 Kafka 어플리케이션 서버 중 1대를 말하며, 클라이언트와 데이터를 주고 받기 위해 사용된다.  

Broker 여러 개가 모여 하나의 Cluster를 구성한다.

<br>

여러 Broker 중 1대는 컨트롤러(Controller) 기능을 수행한다.

> **💡 컨트롤러 (Controller)**
>
> 각 Broker에게 담당 Partition 할당을 수행하고, Broker 상태를 체크하고, 장애를 감지한다.

<br>

### ▫️ Topic

Kafka Cluster는 Topic에 데이터를 저장한다.

하나의 Topic에 여러 Producer가 데이터를 전송할 수 있고, 여러 Consumer가 데이터를 읽어올 수 있다.

<br>

### ▫️ Partition

Partition은 Topic을 분할한 것이다.

파티션이 여러개로 구성되면 병렬로 데이터 처리를 할 수 있다.

토픽 생성 시 파티션 갯수는 동시 처리량과 깊은 관계를 갖기 때문에 토픽 생성 시 주요 관심사이다.

해당 토픽에서 예상되는 메시지 인입량과 컨슈머 처리량등을 고려해서 초기값을 설정하고, 서비스 운영 과정에서 모니터링을 통해 파티션 확대를 하는 경우도 있다.

<br>

### ▫️ Offset

컨슈머 그룹이 이미 가져간 마지막 레코드에 대한 포인터  

> **💡 컨슈머 그룹 (Consumer Group)**
> 
> 동일한 그룹명을 사용하는 컨슈머들의 집합

<br>

***

<br>

## 🔸 CLI 환경에서 Kafka 사용하기

<span style = "color : gray">OpenJDK 1.8 및 Kafka-2.13-2.8.2 버전을 사용하여 Mac 환경에서 Kafka를 사용하는 방법이다.</span>

<br>

### ▫️ 사전 설정

로컬 환경에서 사용을 위해 설정파일을 수정해야한다.

```bash
$ vi config/server.properties
```

```advertised.listeners``` 주석 해제 후 ```localhost:9092```로 수정

<br><br>

### ▫️ Zookeeper 및 Kafka 실행

```bash
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```
▲ _Zookeeper 실행_

<br>

```bash
$ bin/kafka-server-start.sh config/server.properties
```
▲ _Kafka 실행_

<br><br>

### ▫️ 토픽 생성

```bash
$ bin/kafka-topic.sh --create --topic 토픽명 --bootstrap-server localhost:9092
```
▲ _Topic 생성_

> **💡 Topic 생성 시 사용 가능한 옵션**
>
> - ```--partitions 갯수``` : 파티션 갯수 설정  
> 
> - ```--replication-factor 갯수``` : 복제본 갯수(Leader&Follower) 설정

<br>

```bash
$ bin/kafka-topic.sh --describe --topic 토픽명 --bootstrap-server localhost:9092
```
▲ _Topic 정보 확인_

<br><br>

### ▫️ 토픽 수정

```bash
$ bin/kafka-topic.sh --alter --topic 토픽명 --partitions 3
```
▲ _Partition 갯수 수정_

<br>

📌 kafka-configs를 사용하면 몇가지 다이나믹 옵션의 설정 값을 수정할 수 있다.

```bash
$ bin/kafka-configs.sh --alter --entity-type topics --entity-name 토픽명 --add-config retention.ms=86400000
```
▲ _Topic의 레코드가 삭제되기까지의 유지시간 수정_

<br><br>

### ▫️ Message 발행

```bash
$ bin/kafka-console-producer.sh --topic 토픽명 --bootstrap-server localhost:9092
```
▲ _Producer Console을 통한 메시지 발행_

> **💡 Message 발행 시 사용 가능한 옵션**
>
> - ```--request-required-acks [0/1/all]``` :  
>   Producer가 브로커로 메시지를 보낸 후 브로커의 수신 응답 메시지를 어떤 식으로 처리할지 설정
> 
>   - 0 : 메시지를 보낸 후 Kafka의 응답 메시지를 듣지 않고 성공 처리  
>    가장 빠르지만 브로커 장애 발생 시 메시지 유실 가능성이 높다.
>
>   - 1 : Leader 파티션의 성공 응답 값만을 확인  
>     장애 발생 시 메시지 유실 가능성이 조금 있다.
>
>   - all : 하나 이상의 Follow 파티션의 성공 응답도 확인  
>     유실 가능성이 거의 없다.   
>     ```min-insync-replicas=2``` 옵션과 함께하여 성공적인 전송 여부를 확인할 수 있다.
>
> ```--message-send-max-retries 숫자``` : 브로커 장애와 같은 상황에서 Producer의 재전송 횟수

<br>

```bash
$ bin/kafka-verifiable-producer.sh --topic 토픽명 --max-messages 100
```
▲ _String 타입의 넘버를 반복적으로 발행해주는 툴을 사용해 100개의 메시지 발행 테스트_

<br><br>

### ▫️ Message 읽기

```bash
$ bin/kafka-console-consumer.sh --topic 토픽명 --from-beginning --bootstrap-server localhost:9092
```
▲ _Consumer Console을 통한 메시지 읽기_

<br>

> **💡 Message 읽기 시에 사용 가능한 옵션**
>
> - ```--from-beginning``` :   
>   Current Offset이 없을 때, Topic이 갖고 있는 첫 레코드부터 메시지를 읽어온다.   
>   해당 설정이 없으면 Consumer가 접속한 이후 신규로 생성된 메시지부터 읽어온다.
>
> - ```--group 그룹명``` : 그룹을 지정하여 메시지를 읽는다.  
>   Current Offset은 그룹 단위로 관리되므로 항상 그룹을 지정하는 것이 좋다.
>
> - ```--property print.key=true``` : 메시지의 키값을 출력한다.
>
> - ```--property key.separator="-"``` : 키와 밸류 사이에 ```-``` 구분자를 추가한다.

<br>

```bash
$ bin/kafka-consumer-group.sh --list --bootstrap-server localhost:9092
```
▲ _Consumer Group 리스트 확인_

<br>

```bash
$ bin/kafka-consumer-group.sh --describe --group 그룹명 --bootstrap-server localhost:9092
```
▲ _특정 Group의 상세 정보 확인_

<br><br>

### ▫️ Kafka Cluster 구성

먼저 ```config/server.properties``` 파일을 수정해야한다.

```bash
$ vi config/server.properties

broker.id=0
listeners=PLAINTEXT://localhost:9092
log.dirs=/tmp/kafka-logs
```

<br>

이후 Broker의 갯수만큼 ```server.properties```를 복제한다.

```bash
$ cp server.properties server1.properties
$ cp server.properties server2.properties
```

<br>

복제한 properties 파일을 수정한다.

```bash
$ vi config/server1.properties

broker.id=1
listeners=PLAINTEXT://localhost:9093
log.dirs=/tmp/kafka-logs1


$ vi config/server2.properties

broker.id=2
listeners=PLAINTEXT://localhost:9094
log.dirs=/tmp/kafka-logs2
```

<br>

Zookeeper 서버를 실행하고, Kafka 서버를 각 properties 별로 실행한다.

```bash
$ bin/zookeeper-server-start.sh config/zookeeper.properties

$ bin/kafka-server-start.sh config/server.properties&
$ bin/kafka-server-start.sh config/server1.properties&
$ bin/kafka-server-start.sh config/server2.properties&
```

<br>

Topic을 생성하고 메시지를 발행하고 읽는다.

```bash
$ bin/kafka-topic.sh --create --topic 토픽명 --partitions 3 --replication-factor 3 --bootstrap-server localhost:9093

$ bin/kafka-console-producer.sh --topic 토픽명 --bootstrap-server localhost:9093

$ bin/kafka-console-consumer.sh --topic 토픽명 --from-beginning --group 그룹명 --bootstrap-server localhost:9092,localhost:9093,localhost:9094
```

<br>

새로운 터미널에서 Broker 중 1개를 Shutdown 시킨다.

```bash
$ ps -ef|grep server2.properties
혹은
$ lsof -i :9094

$ kill -9 [위에서 확인한 PID]
```

<br>

Consumer console에서 Warning 메시지가 표시되며,  

토픽 정보를 확인하면 Leader 파티션이 변경되며 ISR에는 Replication 정보가 사라지게 된다.

이후 종료했던 Broker를 다시 실행하면 장애 상황이 해소되며 Leader 파티션은 다시 원래대로 돌아간다.

<br><br>

***

_2023.05.26. Update_

_2023.05.25. Update_

_2023.05.24. Update_