# 어노테이션 (Annotation)

어노테이션은 지금까지 사용했던 주석과 기능적으로 같은 역할을 한다.

다만, 정보를 전달하는 대상이 소스코드를 읽는 **사람**일 경우 주석(```//```)을 사용하고,  
특정 코드를 사용하는 **프로그램**에게 정보를 전달할 경우에는 어노테이션(```@```)을 사용한다.

어노테이션의 주 역할은 아래와 같다
- 컴파일러에게 문법 에러를 체크하도록 정보 제공
  
- 프로그램을 빌드할 때 코드를 자동으로 생성할 수 있도록 정보 제공
- 런타임에 특정 기능을 실행하도록 정보 제공

<br>

```java
@Test
public void run() {...}

public void stop() {...}
```
▲ _테스팅을 수행하는 프로그램(JUnit)에게 특정 메서드만 테스트 하도록 정보 전달_

<br>

## 어노테이션의 종류

어노테이션의 종류는 크게 2가지로 분류된다.
- 어노테이션은 자바에서 기본으로 제공하는 **표준 어노테이션**  
- 어노테이션을 정의하는데 사용되는 **메타 어노테이션**  
  <span style = "color : gray"> (사용자가 직접 정의하는 사용자 정의 어노테이션도 있음) </span>

<br>

### 표준 어노테이션

<br>

- ```@Overide``` : 컴파일러에게 메소드를 오버라이딩한 것이라고 알림
  
  ```java
  class Parent {
	void study() {...}
  }
  class Child extends Parent {
	@Override
	void studt() {...}  // 컴파일 에러 발생 - 오타가 난 것을 발견할 수 있음
  }  
  ```


- ```@Deprecated``` : 앞으로 사용하지 않을 대상을 알릴 때 사용  
  새로운 것으로 대체되었으니 기존의 것을 사용하지 않기를 권장
  
  ```java
  class OldClass {
	@Deprecated
	int oldField;

	@Deprecated
	int getOldField() { return oldField; };
  }

  // 컴파일 메시지
  Note: 파일명.java uses or overrides a deprecated API.
  Note: Recomplie with -Xlint:deprecation for details.
  ```

- ```@FunctionalInterface``` : 함수형 인터페이스라는 것을 알릴 때 사용 (실수 방지)  
  > 💡 참고 : 함수형 인터페이스는 단 하나의 추상 메서드만을 가져야 한다.
  ```java
  @FunctionalInterface
  public interface Runnable {
    public abstract void run (); // 하나의 추상 메서드
  }
  ```

- ```@SuppressWarning``` : 컴파일러가 경고 메세지를 나타내지 않도록 할 때 사용

  | 어노테이션 | 설명 |
  |:-|:-|
  |@SuppressWarnings("all")| 모든 경고 억제 |
  |@SuppressWarnings("deprecation")| Deprecated 메서드를 사용한 경우 나오는 경고 억제|
  |@SuppressWarnings("fallthrough")|switch문에서 break 구문이 없을 때 나오는 경고 억제|
  |@SuppressWarnings("finally")|finally 관련 경고 억제|
  |@SuppressWarnings("null")|null 관련 경고 억제|
  |@SuppressWarnings("unchecked")|검증되지 않은 연산자 관련 경고 억제|
  |@SuppressWarnings("unused")|사용하지 않는 코드 관련 경고 억제|

  ```java
  @SuppressWarnings({"deprecation", "unused"})  // 여러개 한 번에 사용
  ```


<br>

### 메타 어노테이션

<br>

- ```@Target``` : 어노테이션을 정의할 때 적용대상을 지정하는데 사용
  
  |대상 타입	|적용범위|
  |:-|:-|
  |ANNOTATION_TYPE	|어노테이션|
  |CONSTRUCTOR	|생성자|
  |FIELD	|필드(멤버변수, 열거형 상수)|
  |LOCAL_VARIABLE	|지역변수|
  |METHOD	|메서드|
  |PACKAGE|	패키지|
  |PARAMETER|	매개변수|
  |TYPE	|타입(클래스, 인터페이스, 열거형)|
  |TYPE_PARAMETER|	타입 매개변수|
  |TYPE_USE	|타입이 사용되는 모든 대상|

  ▲ _```@Target``` 어노테이션을 사용하여 지정할 수 있는 대상의 타입_
  > 💡 참고 : ```java.lang.annotation.ElementType``` 열거형에 모두 정의되어 있음

  <br>

  ```java
  import static java.lang.annotation.ElementType.*; 
  //import문을 이용하여 ElementType.TYPE 대신 TYPE과 같이 간단히 작성할 수 있음

  @Target({FIELD, TYPE, TYPE_USE})	// 적용대상이 FIELD, TYPE
  public @interface CustomAnnotation { }	// 사용자 정의 어노테이션 CustomAnnotation

  @CustomAnnotation	// 적용대상이 TYPE인 경우
  class Main {
    
    @CustomAnnotation	// 적용대상이 FIELD인 경우
    int i;
  }
  ```
<br>

- ```@Documented``` : 어노테이션 정보를 javadoc으로 작성된 문서에 포함
  ```java
  @Documented
  @Target(ElementType.Type)
  public @interface CustomAnnotation { }
  ```

- ```@Inherited``` : 어노테이션이 하위 클래스에 상속되도록 함

  ```java
  @Inherited // @SuperAnnotation이 하위 클래스까지 적용
  @interface SuperAnnotation{ }

  @SuperAnnotation
  class Super { }

  class Sub extends Super{ } // Sub에 어노테이션이 붙은 것으로 인식
  ```

- ```@Retention``` : 어노테이션이 유지되는 기간을 정하는데 사용

  |유지 정책	|설명|
  |:-:|:-|
  |SOURCE|	소스 파일에 존재, 클래스파일에는 존재하지 않음|
  |CLASS	|클래스 파일에 존재, 실행시에 사용불가, 기본값|
  |RUNTIME|	클래스 파일에 존재, 실행시에 사용가능|

  > 유지 정책 : 어노테이션이 유지되는 기간을 지정하는 속성
  
  <br>

  ```java
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.SOURCE) 
  //오버라이딩이 제대로 되었는지 컴파일러가 확인하는 용도 
  //클래스 파일에 남길 필요 없이 컴파일시에만 확인하고 사라짐
  public @interface Override(){ }
  ```

- ```@Repeatable``` : 어노테이션을 반복해서 적용할 수 있게 함

  ```java
  // 여러개의 ToDo애너테이션을 담을 컨테이너 어노테이션 Works
  @interface Works {  
    Work[] value(); 
  }

  // 컨테이너 애너테이션 지정 
  @Repeatable(Works.class) // Work 어노테이션을 여러 번 반복해서 쓸 수 있게 한다.  
  @interface Work{  
    String value();  
  }

  @Work("코드 업데이트")  
  @Work("메서드 오버라이딩")  
  class Main{...}
  ```

<br>

### 사용자 정의 어노테이션

<br>

사용자가 직접 어노테이션을 정의해서 사용하는 것

```java.lang.annotation``` 인터페이스를 상속받기 때문에 다른 클래스나 인터페이스를 상속 받을 수 없음

```java
@interface AnnotationName {
    타입 요소명();  // 어노테이션 요소 선언
}
```

<br>

***

_Update 2022.09.15._