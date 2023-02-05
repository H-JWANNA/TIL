# 스케쥴러

일정한 시간 간격 혹은 특정 시간에 로직을 돌리기 위해서 사용한다.

Spring에서 Scheduler는 **Spring Scheduler**, **Spring Quartz** 2가지 방식이 존재한다.

<br>

## 🔸 Spring Scheduler

<br>

### 💡 의존성

<br>

Spring Scheduler는 SpringBoot Starter에 기본적인 의존성으로 제공되므로, 별도의 의존성이 필요하지 않다.

다만, 실행 파일에 ```@EnableScheduling``` 어노테이션을 붙여줘야 한다.

```java
@EnableScheduling
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```

<br><br>

### 💡 사용 조건

<br>

이후 ```@Scheduled``` 어노테이션을 사용하여 스케쥴러를 사용할 수 있으며, 아래와 같은 조건이 필요하다.

- 메서드의 리턴 타입은 ```void```여야 한다.

- 메서드는 파라미터를 가질 수 없다.

<br><br>

### 💡 사용 방법

<br>

사용 방법은 **cron 표현식**을 이용한 방법과 **fixedDelay**를 이용한 방법이 있다.

<br>

#### **1. cron 표현식**

```java
@Scheduled(cron = "0 0/30 * * * *")
public void schedule() {
    ...
}
```

<br>

cron 표현식의 각 부분은 왼쪽부터 초, 분, 시, 일, 월, 요일, 년도를 나타낸다.

<br>

|필드명|값 허용 범위|허용된 특수 문자|
|:-:|:-:|:-:|
|초 (Seconds)|0 ~ 59|, - * /|
|분 (Minutes)|0 ~ 59|, - * /|
|시 (Hours)|0 ~ 23|, - * /|
|일 (Day)|1 ~ 31|, - * ? / L W|
|월 (Month)|1 ~ 12<br>JAN ~ DEC|, - * /|
|요일 (Week)|0 ~ 6<br>SUN ~ SAT|	, - * ? / L #|
|연도 (Year)|Empty<br>1970 ~ 2099|, - * /|

<br>

**📌 특수문자별 의미**

- ```*``` : 모든 값을 의미
- ```?``` : 해당 항목을 사용하지 않음
- ```-``` : 범위 (ex. 10-30)
- ```,``` : 특별한 값일 때만 동작 (ex. 10,20,30)
- ```/``` : 시작시간/단위 (ex. 0/5는 0분부터 매 5분마다)
- ```L``` : Day에서는 마지막 날짜, Week에서는 마지막 요일 (토요일)
- ```W``` : 가장 가까운 평일 (ex. 15W는 15일에서 가장 가까운 평일을 찾음)
- ```#``` : 몇 째 주의 무슨 요일 (ex. 3#2는 2번째 주 수요일)

<br>

cron 표현식은 서버 현지의 시간대를 사용하며, ```zone``` 속성을 사용하여 시간대를 변경할 수도 있다.

```java
@Scheduled(cron = "0 0/30 * * * *", zone = "Europe/Paris")
```

<br>

#### **2. 고정 지연 / 고정 속도**

```java
@Scheduled(fixedDelay = 1000)
public void schedule() {
    ...
}
```

- 마지막 **실행 종료**와 다음 **실행 시작** 사이의 시간이 1000ms

<br>

```java
@Scheduled(fixedRate = 1000)
public void schedule() {
    ...
}
```

- 해당 메서드가 **실행된 직후**부터 1000ms 후에 실행

  <br>

  > 예약된 작업은 병렬로 실행되지 않으므로,  
  > 이전 작업이 완료되지 않은 경우에는 다음 작업이 호출되지 않는다.
  >
  > 따라서 병렬된 동작을 지원하기 위해서는 ```@Async```를 추가해야한다.

<br>

#### **3. 초기 지연**

```java
@Scheduled(initialDelay = 1000)
public void schedule() {
    ...
}
```

- 처음 실행될 때 initialDelay 값 이후에 실행된다.

<br>

#### **4. 매개변수화**

```java
@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")

@Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")

@Scheduled(cron = "${cron.expression}")
```

속성 파일에 해당 설정을 저장하여 매개변수화 할 수 있다.

<br><br>

### 💡 스레드 설정

<br>

Scheduler는 기본적으로 Spring에서 생성된 1개의 스레드 풀에서 실행된다.

그렇기 때문에 하나의 Schedule이 진행중이라면 해당 스케쥴이 끝나야 다음 스케쥴이 실행되는 문제가 있다.

Spring Boot에서는 설정을 통해 Schedule에 대한 스레드 풀을 생성하고,  
해당 스레드 풀을 사용하여 모든 스케쥴된 작업을 실행하도록 할 수 있다.

<br>

#### **1. Configuration을 통한 설정**

```java
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
    private final int POOL_SIZE = 10;

    @Override
    public void configureTasks(ScheduledTaskRegistrar shceduledTaskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("my-scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();

        shceduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }
}
```

<br>

#### **2. 속성에 설정을 추가**

```yml
spring:
    task:
        scheduling:
            pool:
                size: 5
```

<br>

***

<br>

## 🔸 Spring Quartz

<br>

### 💡 의존성

```gradle
implementation 'org.springframework.boot:spring-boot-starter-quartz'
```

<br><br>

### 💡 사용 방법

<br>

Quartz에는 필수적으로 구현해야할 요소와, 옵셔널하게 선택적으로 구현할 수 있는 요소가 있다.

Quartz Job의 필수 요소는 ```Job```, ```JobDetail```, ```Trigger``` 3가지가 있다.

<br>

#### **📌 Job**

실제로 실행되는 로직이 있는 곳이다.

Quartz에서 인터페이스로 제공하며, 해당 인터페이스를 구현하면 된다.

```java
@Configuration
public class CollectJob implements Job {

    private final CollectService collectService;

    public CollectJob(CollectService collectService) {
        this.collectService = collectService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("[Collect] collect Job Start...");
    }
}
```

<br>

#### **📌 JobDetail**

Job을 실행시키기 위한 구체적인 정보를 가지고 있는 인스턴스이다.

JobBuilder API를 통해 만들 수 있고, Job에 대한 설명, Job의 ID 등을 설정할 수 있다.

```java
@Bean
public JobDetail exampleJobDetail() {
    return JobBuilder.newJob().ofType(CollectJob.class)
        .storeDurably()
        .withIdentity("job_detail")
        .withDescription("설명...")
        .build();
}
```

<br>

#### **📌 Trigger**

Job이 실행되는 실행 조건을 가지고 있는 인스턴스이다.

TriggerBuilder API를 통해 만들 수 있고,  

조건으로는 단순히 특정 시간 간격으로 할 수 있으며, cron 표현식으로도 작성할 수 있다.

<br>

```java
@Bean
public Trigger tistoryTrigger(@Qualifier("exampleJobDetail") JobDetail job) {
return TriggerBuilder.newTrigger().forJob(job)
    .withIdentity("example_job_trigger")
    .withSchedule(cronSchedule("0 0 9 * * ?")
    .inTimeZone(TimeZone.getTimeZone("Asia/Seoul")))
    .build();
}
```

```yml
spring:
  quartz:
    job-store-type: memory
```

<br><br>

### 💡 Quartz 사용의 장점

<br>

Spring Scheduler에 비해 Quartz가 구현하기 어렵고 복잡하지만 아래와 같은 장점이 있다.

- **Scheduler 간의 Clustering 기능**  
  
  여러 Service 노드가 있을 때, 해당 노드들의 Scheduler 간의 Clustering을 책임져 줄 수 있다.  

  JobStore를 이용하며, Memory 방식, DB 방식을 지원한다.

- **Scheduler 실패에 대한 후처리 기능**  

  Misfire Instructions 기능을 제공한다.  

  만약 스케쥴러가 실패한 경우나 Thread Pool에서 사용할 수 있는 스레드가 없을 경우에 발생할 수 있다.

- **JVM 종료 이벤트를 캐치하여 스케쥴러에게 종료를 알려주는 기능 제공**

- **여러가지 기본 플러그인 제공**

- **Job, Trigger 실행에 대해 이벤트 처리 가능**

<br><br>

## 📋 참고 자료

- [*Spring의 @Scheduled 어노테이션*](https://recordsoflife.tistory.com/1164)

- [*[Spring] Scheduler 어떤걸 사용해야 할까 ? - Spring Scheduler와 Spring Quartz*](https://sabarada.tistory.com/113)

- [*[Spring-boot] Scheduler Thread Pool 구성*](https://m.blog.naver.com/dg110/221589812687)

<br><br>

***

_2023.02.05. Update_