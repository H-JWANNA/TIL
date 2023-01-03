# 스레드 (Thread)

메모리를 할당 받아 실행 중인 어플리케이션을 **프로세스**라고 하고,  
프로세스 내에서 실행되는 소스코드의 실행 흐름을 **스레드**라고 한다.

> 싱글 스레드 프로세스 : 하나의 스레드를 가지는 프로세스  
> 멀티 스레드 프로세스 : 여러개의 스레드를 가지는 프로세스로 동시 작업이 가능하다.  
> 
> (대부분은 멀티 스레드 프로세스이다.)

<br>

### 메인 스레드 (Main Thread)

<br>

메인 스레드는 자바 실행시 가장 먼저 실행되는 ```main``` 메서드를 실행시키고,  
코드의 끝을 만나거나 return문을 만나면 실행을 종료한다.

> 메인 스레드만 가진다는 것은 싱글 스레드 프로세스라는 의미이고,  
> 메인 스레드에서 또 다른 스레드를 생성하여 실행하면 멀티 스레드 프로세스로 동작하게 된다.

<br>

### 멀티 스레드 (Multi-Thread)

<br>

하나의 프로세스는 여러 개의 스레드를 가질 수 있으며, 이를 멀티 스레드 프로세스라 한다.

하나의 애플리케이션 내에서 여러 작업을 동시에 수행하는 **멀티 태스킹**을 구현하는 역할을 한다.

<br>

***

<br>

## 스레드의 생성 및 실행

<br>

### 작업 스레드 생성 및 실행

<br>

작업 스레드는 메인 스레드 이외에 별도의 작업을 위한 멀티 스레드를 말하며,  
작업 스레드를 활용한다는 것은 **작업 스레드가 수행할 코드를 작성**하고,  
**작업 스레드를 생성하여 실행**시키는 것을 의미한다.

스레드가 수행할 코드는 클래스 내부에 작성해주어야 하며,  
```run()```이라는 메서드 내에 스레드가 처리할 작업을 작성해야한다.

> ```run()``` 메서드는 ```Runnable``` 인터페이스와 ```Thread``` 클래스에 정의되어져 있다.   

<br>

🔸 **작업 스레드의 생성 및 실행 방법**

1. ```Runnable``` 인터페이스를 구현한 객체에서 ```run()```을 구현하여 스레드를 생성하고 실행

```java
public class ThreadExample1 {
    public static void main(String[] args) {
        // Runnable 인터페이스를 구현한 객체 생성
        Runnable task1 = new ThreadTask1();
        // 구현 객체를 인자로 전달하면서 Thread 클래스를 인스턴스화한 스레드생성
        Thread thread1 = new Thread(task1);

        // Thread thread1 = new Thread(new ThreadTask1());


        // 작업 스레드 실행 방법 start() 메서드
        thread1.start();

        // 메인 스레드 반복문 작성
        for (int i = 0; i < 100; i++) {
            System.out.print("@");
        }
    }
}

// Runnable 인터페이스를 구현하는 클래스
class ThreadTask1 implements Runnable {
    // ru() 메서드 바디 내에 작업 스레드 반복문 작성
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.print("#");
        }
    }
}

// 출력
@@@@@@@@@@@######@@@@@############################
@#########@@@@@@@@@@@@@@@@############@@@@@@@@@@@@
@@@@@@@@@@@@@@@@@@@@@@@@##@@@@@@@@@@@@@@@@@@@@@@@@
@@@@@@@###########################################
```

<br>

2. ```Thread``` 클래스를 상속 받은 하위 클래스에서 ```run()```을 구현하여 스레드를 생성하고 실행

```java
public class ThreadExample2 {
    public static void main(String[] args) {

        // Thread 클래스를 상속받은 클래스를 인스턴스화
        ThreadTask2 thread2 = new ThreadTask2();

        // 작업 스레드 실행
        thread2.start();

        // 메인 스레드 반복문 작성
        for (int i = 0; i < 100; i++) {
            System.out.print("@");
        }
    }
}

// Thread 클래스를 상속받는 클래스 작성
class ThreadTask2 extends Thread {
    // run() 메서드 바디 내에 작업 스레드 반복문 작성
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.print("#");
        }
    }
}
```

<br>

### 익명객체를 사용하여 스레드 생성하고 실행

- ```Runnable``` 익명 구현 객체를 활용한 스레드 생성 및 실행

```java
public class ThreadExample1 {
    public static void main(String[] args) {
				
        // 익명 Runnable 구현 객체를 활용하여 스레드 생성
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.print("#");
                }
            }
        });

        thread1.start();

        for (int i = 0; i < 100; i++) {
            System.out.print("@");
        }
    }
}
```

<br>

- ```Thread``` 익명 하위 객체를 활용한 스레드 생성 및 실행

```java
public class ThreadExample2 {
    public static void main(String[] args) {

        // 익명 Thread 하위 객체를 활용한 스레드 생성
        Thread thread2 = new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.print("#");
                }
            }
        };

        thread2.start();

        for (int i = 0; i < 100; i++) {
            System.out.print("@");
        }
    }
}
```

<br>

***

<br>

## 스레드 이름

메인스레드는 ```main```이라는 이름을 가지며,  
그 외에 추가적으로 생성한 스레드는 기본적으로 ```Thread-n```이라는 이름을 가지고 있다.

<br>

### 스레드 이름 조회

```java
// 임의의 스레드
Thread thread = new Thread(new Runnable() {
    public void run() {
        System.out.println("Get Thread Name");
    }
});

System.out.println(thread.getName());

// 출력
Get Thread Name
Thread-0
```

<br>

### 스레드 이름 설정

```java
// 임의의 스레드
Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Set And Get Thread Name");
            }
        });

thread.setName("Modified Thread Name");

System.out.println(thread.getName());

// 출력
Set And Get Thread Name
Modified Thread Name
```

<br>

### 스레드 인스턴스의 주소값 얻기

<br>

실행 중인 스레드의 주소값을 사용해야 하는 상황이 발생한다면  
```Thread``` 클래스의 정적 메서드인 ```currentThread()```를 사용하면 된다.

```java
Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName());   // Thread-0
            }
        });

thread.start();
System.out.println(Thread.currentThread().getName());   // main
```

<br>

***

<br>

## 스레드 동기화

싱글 스레드 프로세스는 데이터에 단 하나의 스레드만 접근하므로 문제될 사항이 없지만  
멀티 스레드 프로세스의 경우, 두 스레드가 동일한 데이터를 공유하게 되어 문제가 발생할 수 있다.

```java
try { Thread.sleep(1000); } catch (Exception error) {}
```

```java
Thread.sleep(1000);
```

▲ _스레드를 일시 정지시키는 메서드_

<br>

**어떤 스레드가 일시 정지되면, 대기열에서 기다리고 있던 다른 스레드가 실행된다.**

또한, ```Thread.sleep()```은 반드시 try-catch문의 ```try``` 블럭 내에 작성해야 한다.

<br>

```java
public class ThreadExample {
    public static void main(String[] args) {

        Runnable threadTask3 = new ThreadTask3();
        Thread thread3_1 = new Thread(threadTask3);
        Thread thread3_2 = new Thread(threadTask3);

        thread3_1.setName("김코딩");
        thread3_2.setName("박자바");

        thread3_1.start();
        thread3_2.start();
    }
}

class Account {

    // 잔액을 나타내는 변수
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }
		
    // 인출 성공 시 true, 실패 시 false 반환
    public boolean withdraw(int money) {

        // 인출 가능 여부 판단
        if (balance >= money) {

            // if문의 실행부에 진입하자마자 해당 스레드를 일시 정지 시키고, 
            // 다른 스레드에게 제어권을 강제로 넘긴다.
            // 일부러 문제 상황을 발생시키기 위해 추가한 코드
            try { Thread.sleep(1000); } catch (Exception error) {}

            // 잔액에서 인출금을 깎아 새로운 잔액을 기록
            balance -= money;

            return true;
        }
        return false;
    }
}

class ThreadTask3 implements Runnable {
    Account account = new Account();

    public void run() {
        while (account.getBalance() > 0) {

            // 100 ~ 300원의 인출금을 랜덤으로 정함
            int money = (int)(Math.random() * 3 + 1) * 100;

            // withdraw를 실행시키는 동시에 인출 성공 여부를 변수에 할당
            boolean denied = !account.withdraw(money);

            // 인출 결과 확인
            // 만약, withraw가 false를 리턴하였다면, 해당 내역에 -> DENIED를 출력
            System.out.println(String.format("Withdraw %d₩ By %s. Balance : %d %s",
                    money, Thread.currentThread().getName(), account.getBalance(), denied ? "-> DENIED" : "")
            );
        }
    }
}

// 출력
Withdraw 100₩ By 김코딩. Balance : 600 
Withdraw 300₩ By 박자바. Balance : 600 
Withdraw 200₩ By 김코딩. Balance : 400 
Withdraw 200₩ By 박자바. Balance : 200 
Withdraw 200₩ By 김코딩. Balance : -100 
Withdraw 100₩ By 박자바. Balance : -100 

Process finished with exit code 0
```

<br>

> 💡 음수의 잔액이 발생하는 이유 :  
> 
> 두 스레드가 하나의 ```Account``` 객체를 공유하는 상황에서,  
> 한 스레드가 if 문의 조건식을 ```true```로 평가하여 if문의 실행부로 코드의 흐름이 이동하는 시점에 **다른 스레드가 끼어들어** balance를 인출했기 때문이다.
> 
> 추가로 알 수 없는 원인에 의해 인출에 실패한 경우에 -> DENIED가 제대로 출력되지 않는 문제도 발생

<br>

### 임계 영역(Critical section)과 락(Lock)

<br>

- 임계 영역 : **오로지 하나의 스레드만 코드를 실행할 수 있는 코드 영역**  
  특정 코드 구간을 임계 영역으로 설정할 때에는 ```synchronized```라는 키워드를 사용  

- 락 : 임계 영역을 포함하고 있는 객체에 접근할 수 있는 권한

<br>


🔸 **```synchronized``` 키워드를 사용하는 방법**

<br>

1. 메서드 전체를 임계 영역으로 지정

```java
class Account {
	...
    // synchronized 키워드를 작성하여 메서드 전체를 임계 영역으로 만듬
	public synchronized boolean withdraw(int money) {
	    if (balance >= money) {
	        try { Thread.sleep(1000); } catch (Exception error) {}
	        balance -= money;
	        return true;
	    }
	    return false;
	}
}
```


2. 특정한 영역을 임계 영역으로 지정

특정 영역을 임계 영역으로 지정하려면 ```synchronized``` 키워드와 함께  
소괄호(```()```) 안에 해당 영역이 포함된 객체의 참조를 넣고,  
중괄호(```{}```)로 블럭을 열어, 블럭 내에 코드 작성

```java
class Account {
	...
	public boolean withdraw(int money) {
        // 참조는 보통 this
        synchronized (this) {
            // 블럭 내에 코드 작성
            if (balance >= money) {
                try { Thread.sleep(1000); } catch (Exception error) {}
                balance -= money;
                return true;
            }
            return false;
        }
	}
}
```

<br>

***

<br>

## 스레드의 상태와 실행 제어

위에서 스레드를 실행하기 위해 ```start()``` 메서드를 사용했지만,  
```start()```는 스레드를 실행시키는 메서드는 아니다.

```start()```는 스레드의 상태를 **실행 대기 상태**로 만들어주는 메서드이며,  
어떤 스레드가 실행 대기 상태가 되면 운영체제가 적절한 때에 스레드를 실행시킨다.


> 즉, 스레드에는 상태라는 것이 존재한다.  
> 
> 또한, 스레드의 상태를 바꿀 수 있는 메서드가 존재한다.


<br>

<img src = "https://miro.medium.com/max/700/1*ZB4kgrJkAN-eVv1OIwMrpw.jpeg" />

▲ _Thread State Diagram_

<br>

### 스레드 실행 제어 메서드

<br>

**```sleep(long milliSecond)``` : milliSecond 동안 스레드를 잠시 멈춤**

<br>

```sleep()```은 ```Thread```의 클래스 메서드이기 때문에  
```Thread.sleep(1000);```과 같이 클래스를 통해서 호출하는 것이 권장된다.

```sleep()```에 의해 일시 정지된 스레드는 다음의 경우에 실행 대기 상태로 복귀한다.

- 인자로 전달한 시간 만큼의 시간이 경과한 경우

- ```interrupt()```를 호출한 경우 : 반드시 try-catch문을 사용해서 예외 처리를 해주어야 한다. 

따라서 ```sleep()```을 사용할 때에는 try-catch 문으로 ```sleep()```을 감싸주어야 한다.

```java
try { Thread.sleep(1000); } catch (Exception error) {}
```

<br>

**```interrupt()``` : 일시 중지 상태인 스레드를 실행 대기 상태로 복귀시킨다.**

<br>

```interrupt()```는 ```sleep()```, ```wait()```, ```join()```에 의해 일시 정지 상태에 있는 스레드들을 실행 대기 상태로 복귀시키는 역할을 한다.

멈춰있는 스레드가 아닌 다른 스레드에서 ```멈춰 있는 스레드.interrupt()```를 호출하면,  
기존에 호출되어 스레드를 멈추게 했던 ```sleep()```, ```wait()```, ```join()``` 메서드에서 예외가 발생되며,  
그에 따라 일시 정지가 풀리게 된다.

<br>

**```yield()``` : 다른 스레드에게 실행을 양보한다.**

<br>

```yield()```는 다른 스레드에게 자신의 실행 시간을 양보한다.  
예를 들어, 운영 체제의 스케줄러에 의해 3초를 할당 받은 스레드 A가 1초 동안 작업을 수행하다가 ```yield()```를 호출하면 남은 실행 시간 2초는 다음 스레드에게 양보된다.

```java
public void run() {
    while (true) {
        if (example) { ... }

        // example이 false이면 while문을 멈추고,
        // 우선 순위가 높은 스레드에게 실행시간 양보
        else Thread.yield();
    }
}
```
<br>

**```join()``` : 다른 스레드의 작업이 끝날 때까지 기다린다.**

<br>

```join()``` : 특정 스레드가 작업하는 동안에 자신을 일시 중지 상태로 만드는 상태 제어 메서드

💡 ```join()```과 ```sleep()```의 유사점

- ```join()```을 호출한 스레드는 일시 중지 상태가 된다.

- try-catch문으로 감싸서 사용해야 한다.

- ```interrupt()```에 의해 실행 대기 상태로 복귀할 수 있다.


💡 ```join()```과 ```sleep()```의 차이점

- ```sleep()``` : ```Thread``` 클래스의 static 메서드

- ```join()``` : 특정 스레드에 대해 동작하는 인스턴스 메서드

> ```Thread.sleep(1000);```
> 
> ```thread1.join();```

<br>

**```wait()```, ```notify()``` : 스레드 간 협업에 사용**

<br>

두 스레드가 교대로 작업을 처리해야할 때 사용하는 메서드

1. 스레드A가 공유 객체에 자신의 작업을 완료한다.  
  이 때, 스레드B와 교대하기 위해 ```notify()```를 호출한다.  

2. ```notify()```가 호출되면 스레드B가 실행 대기 상태가 되며, 곧 실행된다.  

3. 이어서 스레드A는 ```wait()```을 호출하며 자기 자신을 일시 정지 상태로 만든다.

4. 이후 스레드B가 작업을 완료하면 ```notify()```를 호출하여 작업을 중단하고 있던 스레드A를 다시 실행 대기 상태로 복귀시킨 후, ```wait()```을 호출하여 자기 자신의 상태를 일시 정지 상태로 전환한다.

```java
public class ThreadExample5 {
    public static void main(String[] args) {
        WorkObject sharedObject = new WorkObject();

        ThreadA threadA = new ThreadA(sharedObject);
        ThreadB threadB = new ThreadB(sharedObject);

        threadA.start();
        threadB.start();
    }
}

class WorkObject {
    public synchronized void methodA() {
        System.out.println("ThreadA의 methodA Working");
        notify();
        try { wait(); } catch(Exception e) {}
    }

    public synchronized void methodB() {
        System.out.println("ThreadB의 methodB Working");
        notify();
        try { wait(); } catch(Exception e) {}
    }
}

class ThreadA extends Thread {
    private WorkObject workObject;

    public ThreadA(WorkObject workObject) {
        this.workObject = workObject;
    }

    public void run() {
        for(int i = 0; i < 10; i++) {
            workObject.methodA();
        }
    }
}

class ThreadB extends Thread {
    private WorkObject workObject;

    public ThreadB(WorkObject workObject) {
        this.workObject = workObject;
    }

    public void run() {
        for(int i = 0; i < 10; i++) {
            workObject.methodB();
        }
    }
}
```

<br>

***

_2022.09.18. Update_