# 클래스와 객체

> 클래스를 통해 생성된 객체를 해당 클래스의 인스턴스라고 부른다.  
> 
> 클래스는 설계도이며, 인스턴스는 설계도를 통해 만든 결과물이다.

<br>

## 클래스 (Class)

<br>

### 클래스의 구성

<br>

```java
public class ExampleClass {
	int x = 10;                 // (1)필드
	void printX() {...}         // (2)메서드
	ExampleClass {...}          // (3)생성자
	class ExampleClass2 {...}   // (4)이너 클래스
} 
```

1. 필드 : 클래스의 속성(state)을 나타내는 변수
2. 메서드 : 클래스의 기능(behavior)을 나타내는 변수
3. 생성자 : 클래스의 객체를 생성하는 역할
4. 이너 클래스 : 클래스 내부의 클래스

> 클래스의 멤버에는 필드, 메서드, 이너 클래스가 포함된다.

<br>

### 클래스 심화

<br>

**중첩 클래스**

```static```만 선언 가능하며, 클래스와 연관관계가 밀접한 경우 사용

클래스 내부에 또 다른 클래스가 내포되어 있는 상태로, 클래스의 관리 효율을 높인다.

```java
class ClassName
{
  static class 중첩클래스이름
  {
    // 내용
  }
}
```
<br>

**내부 클래스**

주로 이벤트를 처리하거나 데이터 구조선언을 하기 위해 사용

하나의 클래스 또는 메소드에서만 사용되는 클래스가 필요할 때 사용한다.

```java
class ClassName
{
  class 내부클래스이름
  {
    //내용
    //static 메소드를 사용하지 않음
  }
}
```

<br>

**지역 클래스**

메소드 내부에서 선언하여 메소드 내에서만 사용되는 클래스. 

지역변수를 ```final```로 생성하는 특징이 있다.

```java
class ClassName
{
   Method
   {
      static class 지역클래스이름
      {
         //내용
         //지역변수는 final로 선언
      }
   }
 }
```

<br>

**익명 클래스**

메소드 내부에서 선언하여 **단 한번만** 정의하여 사용하려고 할 때 사용

지역 클래스와 같으나 클래스 선언 후에 ```;```를 붙이는 특징이 있다.

단독 생성이 불가능하며 클래스를 상속하거나 인터페이스를 구현해야 한다.

익명 객체에 새롭게 정의된 필드나 메소드는 익명 객체 내부에서만 사용이 가능한다.


```java
class ClassName
{
  Method
  {
     new class 익명클래스이름()
     {
        //내용
     }
  }
}
```




<br>

***

<br>

## 객체 (Object)

<br>

객체는 크게 속성(필드)과 기능(메서드) 두 가지 구성요소로 이루어져 있다.

<br>

▼ _Create Class & Object_

```java
// 클래스 생성
class Building {

    // 필드 생성
    private String name;    // 건물 이름
    private int beam;       // 보 개수
    private int column;     // 기둥 개수
    private int window;     // 창문 개수
    ...

    // 메서드 생성
    void heating() {...};   // 난방 기능
    void security() {...};  // 보안 기능
    ...
}

class BuildingList {
    public static void main(String[] args) {

        // 객체의 생성
        Building opera_house = new Building();
        Building burj_khalifa = new Building();
        Building lotte_world_tower = new Building();
    }
}
```

위와 같이 ```클래스명 참조변수명 = new 생성자();``` 키워드를 통해 객체를 생성할 수 있다.  

```new``` 키워드와 생성자를 통해 객체를 생성하는 것은 **해당 객체를 힙 메모리에 넣고 그 주소값을 참조변수에 저장하는 것과 같다.**

객체를 생성한 후에는 ```.```(Point)를 통해 객체의 멤버에 접근이 가능하다.





<br>

***

_2022.09.02. Update_

