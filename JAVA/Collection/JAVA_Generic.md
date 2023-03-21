# Java 컬렉션

## 제네릭 (Generic)

제네릭은 데이터 타입을 일반화한다는 것을 의미하며,  
클래스나 메서드에서 사용할 내부 데이터 타입을 컴파일 시에 미리 지정하는 방법이다.

객체의 타입을 컴파일 시에 체크하기 때문에 객체의 타입 안정성을 높이고, 형변환의 번거로움을 줄여준다.

<br>

▼ _String 타입만 저장할 수 있는 클래스_

```java
class Example {
    private String ex;

    Example(String ex) {
        this.ex = ex;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }
}
```

<br>

▼ _제네릭을 활용한 모든 타입을 저장할 수 있는 클래스 (제네릭 클래스)_

```java
class Example<T> {
    private T ex;

    Example(T ex) {
        this.ex = ex;
    }

    public T getEx() {
        return ex;
    }

    public void setEx(T ex) {
        this.ex = ex;
    }
}

// String 타입의 인스턴스 생성
Example<String> ex1 = new Example<String>("예제");
System.out.println(ex1);    // Example@4eec7777

// int 타입의 인스턴스 생성
Example<Integer> ex2 = new Example<Integer>(100);

// Boolean 타입의 인스턴스 생성
Example<Boolean> ex3 = new Example<>(true); // 생성자 부분 타입 생략 가능

// Double 타입의 인스턴스 생성
Example<Double> ex4 = new Example<>(3.141592);
```

<br>

만약 타입 매개변수를 여러개 사용할 경우에는 클래스 선언 시 ```<T>``` 자리에 ```<T, K, V>```와 같이 사용할 수 있다.

자주 사용되는 타입 매개변수 문자 
<span style = "color : gray"> (임의의 값이지만 통상적으로 사용되는 문자) </span>

- ```<T>``` : Type
- ```<K>``` : Key
- ```<V>``` : Value
- ```<E>``` : Element
- ```<N>``` : Number
- ```<R>``` : Result

또한 클래스 변수(```static```)에는 타입 매개변수를 사용할 수 없다.

```Example<String>```의 인스턴스와 ```Example<Integer>```의 인스턴스가 공유하는 클래스 변수의 타입이 서로 달라져서, 같은 변수를 공유하는게 아니기 때문이다.

추가로, 제네릭 타입 매개변수에는 기본형(int, boolean) 타입은 작성할 수 없고, Wrapper 클래스를 사용해야 한다.

<br>

### 🔸 제네릭 클래스의 다형성

<br>

```java
class Framework {...}
class Spring extends Framework {...}
class JavaScript {...}

class Example<T> {
    private T item;

    public T getItem() {
        return item;
    }

    public T setItem() {
        this.item = item;
    }
}

public static void main(String[] args) {
    // 인스턴스 생성
    Example<Framework> frameworkExample = new Example<>();
    frameworkExample.setItem(new Spring());     // 다형성 적용
    frameworkExample.setItem(new JavaScript()); // 에러 발생 : incompatible types
}
```

```Spring``` 클래스는 ```Framework``` 클래스를 상속받고 있기 떄문에 다형성 적용이 가능하다.

<br>

### 🔸 제네릭 클래스의 제한

<br>

```java
interface Programming {...}
class Framework implements Programming{...}
class Spring extends Framework implements Programming{...}
class JavaScript {...}

class Example<T extends Framework & Programming> {  // 상속 및 구현
    private T item;

    @getter
    @setter
}

public static void main(String[] args) {
    // 인스턴스 생성
    Example<Spring> springExample = new Example<>(); // 가능

    Example<JavaScript> javascriptExample = new Example();  
    // 에러 발생 : type argument package.JavaScript is not within bounds of type-variable T
}
```

- ```<T extends Framework>```처럼 타입 매개변수에 클래스를 상속받으면, 해당 클래스의 하위 클래스만 타입으로 지정할 수 있도록 제한한다.

- ```<T extends Programming>```처럼 타입 매개변수에 인터페이스를 상속받으면, 해당 인터페이스를 구현한 클래스만 타입으로 지정할 수 있도록 제한한다.  
이 경우에는 인터페이스도 ```extends``` 키워드를 사용한다.

- ```<T extends Framework & Programming>```처럼 ```&```를 이용하여 클래스 상속과 인터페이스를 구현한 클래스만 지정할 수도 있다.

<br>

### 🔸 제네릭 메서드

<br>

```java
class Example<T> {
    public <T> void add(T element) {...}
}

// 클래스 <T> 타입 지정
Example<String> example = new Example<>();

// 메서드 <T> 타입 지정
example.<Integer>add(100);
exaple.add(100); // 타입 지정 생략 가능
```

위의 클래스에서 선언된 타입 매개변수 ```<T>```와 메서드에서 선언된 타입 매개변수 ```<T>```는 서로 다른 타입 매개변수이다.

클래스에서 선언된 ```<T>```는 클래스가 인스턴스화 될 때 타입이 지정되고,  
메서드에서 선언된 ```<T>```는 메서드가 호출될 때 타입이 지정된다.

추가로 
- 메서드 타입 매개변수는 ```static```메서드에서도 사용할 수 있다.
- ```length()```와 같은 특정(```String```) 클래스의 메서드는 사용할 수 없다.
- ```equals()```나 ```toString()```처럼 ```Object``` 클래스의 메서드는 사용할 수 있다.

<br>

***

<br>

### 🔸 와일드카드

<br>

제네릭에서 와일드카드는 **어떠한 타입으로든 대체될 수 있는** 타입 파라미터를 의미한다.

▼ _일반적으로 extends와 super를 조합하여 사용_

```java
<? extends T>   // 하위 클래스 타입만 타입 파라미터로 가능
<? super T>     // 상위 클래스 타입만 타입 파라미터로 가능
```

<br>

▼ _사용 예제_

```java
public static void samsungPay(User<? extends Galaxy> user) {
    System.out.println("user.phone = " + user.phone.getClass().getSimpleName());
    System.out.println("Galaxy만 삼성 페이를 사용할 수 있습니다. ");
}

public static void recordVoice(User<? super Galaxy> user) {
    System.out.println("user.phone = " + user.phone.getClass().getSimpleName());
    System.out.println("안드로이드 폰에서만 통화 녹음이 가능합니다. ");
}
```
```.getClass().getName()```은 패키지명과 클래스 이름까지 모두 호출하고,  
```.getClass().getSimpleName()```은 클래스 이름만 호출한다.


<br>

***

_2023.03.21. Modified_

_2022.09.13. Update_
