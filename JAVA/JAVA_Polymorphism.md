# 다형성

다형성은 **하나의 객체가 여러 가지 형태를 가질 수 있는 성질**을 말한다.

자바에서 다형성은 **상위 클래스 타입의 참조변수를 통해서 하위 클래스 객체를 참조**할 수 있도록 허용한 것이다.

<br>

▼ _Overloading과 다형성_

```java
class O {
    public void testNum(int param) {
        System.out.println("숫자 출력" + param);
    }
    public void testNum(String param) {
        System.out.println("문자 출력" + param);
    }
}

public class Test {
    public static void main(String[] args) {
        O o = new O();
        o.a(20);        // 숫자 출력 : 20
        o.a("test");    // 문자 출력 : test
    }
}
```

> 참고 : Overloading이 다형성인지 아닌지에 대해서는 이견이 존재한다.

<br>

▼ _Overriding과 다형성_

```java
class Friend {
    public void friendInfo() {
        System.out.println("친구");
    }
}

class BoyFriend extends Friend {
   @Override
    public void friendInfo() {
        System.out.println("남자친구");
    }
}

class GirlFriend extends Friend {
    @Override
    public void friendInfo() {
        System.out.println("여자친구");
    }
}

public class FriendTest {
    public static void main(String[] args) {
        Friend friend = new Friend();  // 객체 타입 == 참조변수 타입
        BoyFriend boyfriend = new BoyFriend();
        Friend girlfriend = new GirlFriend();  // 객체 타입 != 참조변수 타입

        friend.friendInfo();        // 친구
        boyfriend.friendInfo();     // 남자친구
        girlfriend.friendInfo();    // 여자친구
    }
}
```

위 코드에서는 ```GirlFriend``` 클래스의 인스턴스를 생성하고,  
생성한 인스턴스를 ```Friend``` 타입의 참조변수 ```girlfriend```에 할당하고 있다.

즉, 상위 클래스 타입의 참조변수로 하위 클래스의 인스턴스를 참조하고 있다.

또한, 위와 같이 ```Override```를 통한 재정의가 되었을 때는  
상위 클래스 타입의 참조변수로 하위 클래스인 ```GirlFriend``` 멤버에 접근한 것을 볼 수 있다.
> ```java
> Friend girlfriend = new GirlFriend();
> girlfriend.friendInfo();  // 여자친구
> ```

<br>

```java
class Friend {
    public void friendInfo() {
        System.out.println("친구");
    }
}

class GirlFriend extends Friend {
    @Override
    public void friendInfo() {
        System.out.println("여자친구");
    }

    public void friendDate(int num) {   // 새로운 메서드
        System.out.println("만난지 " + num + "일 째");
    }
}

public class FriendTest {
    public static void main(String[] args) {
        Friend friend = new Friend();
        Friend girlfriend = new GirlFriend();
        GirlFriend girlFriend2 = new GirlFriend();  // 새로운 인스턴스 생성

        friend.friendInfo();          // 친구
        girlfriend.friendInfo();      // 여자친구
        girlFriend2.friendDate(100);  // 만난지 100일 째

        girlfriend.friendDate(200);   // java: cannot find symbol
    }
}
```

하지만 위 코드와 같이 상위 클래스 타입의 참조변수로 하위 클래스의 ```Override```되지 않은 멤버에 접근하게 되면 ```java: cannot find symbol``` 에러가 발생한다.

<br>

***

<br>

## 참조변수의 타입 변환

참조변수의 타입 변환이란, **사용할 수 있는 멤버의 개수를 조절하는 것**을 말한다.

<br>

💡 타입 변환을 위한 조건
1. **서로 상속관계에 있는 상위 클래스 - 하위 클래스 사이에만 타입 변환이 가능**
2. 하위 클래스 타입에서 상위 클래스 타입 변환(업캐스팅)은 형변환 연산자(괄호)를 생략 가능
3. 상위 클래스 타입에서 하위 클래스 타입 변환(다운캐스팅)은 형변환 연산자(괄호) 생략 불가능

<br>

```java
public class FriendTest {
    public static void main(String[] args) {
        BoyFriend boyfriend = new BoyFriend();

        // 상위 클래스 Friend 타입으로 변환 (생략 가능)
        Friend friend = (Friend) boyfriend;

        // 하위 클래스 GirlFriend 타입으로 변환 (생략 불가능)
        GirlFriend girlfriend = (GirlFriend) friend;

        // 상속 관계가 아닌 경우 에러 발생
        GirlFriend gf2 = (GirlFriend) boyfriend;  // java: incompatible types
    }
}

class Friend {...}
class BoyFriend extends Friend {...}
class GirlFriend extends Friend {...}
```

<br>

***

<br>

## instanceof 연산자

```참조변수(Object) instanceof 타입(Type)```의 형태로 사용하며,  
참조변수의 타입 변환(캐스팅) 가능 여부를 ```boolean``` 타입으로 리턴해준다.

> 쉽게 A는 B의 인스턴스가 맞나요? 라고 생각하면 쉽다.  
> 
> \+ 부모는 자식 집에 들어갈 수 없다.

```java
class Animal {};
class Bat extends Animal{};
class Cat extends Animal{};

public class InstanceOfExample {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal instanceof Object); //true
        System.out.println(animal instanceof Animal); //true
        System.out.println(animal instanceof Bat);    //false

        Animal cat = new Cat();
        System.out.println(cat instanceof Object);    //true
        System.out.println(cat instanceof Animal);    //true
        System.out.println(cat instanceof Cat);       //true
        System.out.println(cat instanceof Bat);       //false
    }
}
```

<br>

***

<br>

## 다형성의 활용

<br>

```java
public class PolymorphismEx {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.buyCoffee(new Americano());
        customer.buyCoffee(new CaffeLatte());

        System.out.println("현재 잔액은 " + customer.money + "원 입니다.");
    }
}

class Coffee {
    int price;

    public Coffee(int price) {
        this.price = price;
    }
}

class Americano extends Coffee {
    public Americano() {
        super(4000);            // 상위 클래스 Coffee 의 생성자 호출
    }

    public String toString() {  // Object 클래스 toString() 메서드 오버라이딩
        return "아메리카노";
    }
}

class CaffeLatte extends Coffee {
    public CaffeLatte() {
        super(5000);
    }

    public String toString() {
        return "카페라떼";
    }
}

class Customer {
    int money = 50000;

    void buyCoffee(Coffee coffee) {   // 커피 구입 (다형성)
        if (money < coffee.price) {
            System.out.println("잔액이 부족합니다.");
            return;
        }
        money -= coffee.price;
        System.out.println(coffee + "를 구입했습니다.");

    }
}
```

<br>

다형성을 활용해 ```void buyCoffee(Coffee coffee)```와 같이 메서드를 작성하였다.  
다형성을 활용하지 않았다면 ```void buyCoffee(Americano americano)```처럼 하나씩 작성해야 했을 것이다.

<br>

### toString()

```toString()``` 메서드 오버라이딩을 하지 않으면,  
```(coffee + "를 구입했습니다.")```가 ```Americano@3d075dc0를 구입했습니다.```와 같이 작동할 것이다.

```toString()``` 메서드는 ```System.out.println```에 참조변수를 넣으면 자동으로 호출된다.  
따라서, 재정의를 통해 읽을 수 있는 문자열로 바꾸는 작업을 통해 원하는 문자열을 출력할 수 있다.

> 💡 참고 : 자주 쓰이는 ```String```이나 ```Integer```와 같은 클래스에는 ```toString()```이 이미 재정의 되어 있다.

<br>

***

_Update 2022.09.07._