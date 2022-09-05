# 내부 클래스

내부 클래스는 클래스 내에 선언된 클래스로 외부 클래스와 내부 클래스가 서로 연관되어 있을 때 사용

외부 클래스 멤버들에 쉽게 접근할 수 있고, 코드의 복잡성을 줄일 수 있다.

또한, 외부적으로 불필요한 데이터를 감출 수 있어 캡슐화(encapsulation)를 달성하는데 유용하다.

<br>

▼ _내부 클래스의 종류_

```java
// 외부 클래스
class Outer { 

    // 인스턴스 내부 클래스	
	class Inner {}
	
    // 정적 내부 클래스
	static class StaticInner {}

	void run() {
        // 지역 내부 클래스
		class LocalInner {}
	}
} 
```

<br>

| 종 류 |	선언 위치 |	사용 가능한 변수 |
|---|---|---|
|인스턴스 내부 클래스<br>(instance inner class)	|외부 클래스의 멤버변수 선언위치에 선언<br>(멤버 내부 클래스) |	외부 인스턴스 변수, <br> 외부 전역 변수|
|정적 내부 클래스<br>(static inner class)|	외부 클래스의 멤버변수 선언위치에 선언<br>(멤버 내부 클래스)	|외부 전역 변수|
|지역 내부 클래스<br>(local inner class)|	외부 클래스의 메서드나 초기화블럭 안에 선언	|외부 인스턴스 변수,<br> 외부 전역 변수|
|익명 내부 클래스<br>(anonymous inner class)|	클래스의 선언과 객체의 생성을 동시에 하는 <br>일회용 익명 클래스	|외부 인스턴스 변수,<br> 외부 전역 변수|

<br>

***

<br>

## 인스턴스 내부 클래스

<br>

```java
class OuterClass {
    private int num1 = 1;        // 외부 클래스 인스턴스 변수
    private static int num2 = 2; // 외부 클래스 정적 변수

    class InnerClass {
    // static 메소드를 사용할 수 없음
        void innerTest() {}
    }

    public void outerTest() {
        InnerClass.innerTest();
    }
}

// 외부 클래스 인스턴스 생성
OuterClass outer = new OuterClass();

// 내부 클래스 기능 호출
outer.outerClass();
```

<br>

## 정적 내부 클래스

<br>

```java
class OuterClass {
    private int num1 = 1;         // 외부 클래스 인스턴스 변수
    private static int num2 = 2;  // 외부 클래스 정적 변수

    void instanceMethod() {}      // 외부 클래스 인스턴스 메소드
    static void staticMethod() {} // 외부 클래스 정적 메소드

    static class StaticInnerClass {
        void innerTest() {
            System.out.print(num2);
            staticMethod();
            // 인스턴스 변수와 메소드는 사용할 수 없음
        }
    }
}

// 정적 내부 클래스 인스턴스 생성
OuterClass.StaticInnerClass static = new OuterClass.StaticInnerClass();

// 정적 내부 클래스 기능 호출
static.innerTest();
```

<br>

## 지역 내부 클래스

<br>

```java
class OuterClass {
    int num1 = 1;

    void test() {
        int num2 = 2;
        class LocalInnerClass {
            void innerTest() {}
        }

        // 메소드 내에서 지역 내부 클래스 인스턴스 생성
        LocalInnerClass local = new LocalInnerClass();

        // 메소드 내에서 지역 내부 클래스 실행
        local.innerTest();
    }
}

// 외부 클래스 인스턴스 생성
OuterClass outer = new OuterClass();

// 기능 호출
outer.test();
```

<br>

***

_Update 2022.09.05._