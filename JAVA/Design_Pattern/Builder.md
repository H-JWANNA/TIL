# Builder Pattern (빌더 패턴)

빌더 패턴은 생성 패턴(Creational Pattern) 중 하나이며,  

선택적 매개변수가 많은 상황에서 생성자 혹은 정적 팩토리 메서드보다 유용하게 사용할 수 있다.

<br>

특정 필드가 Optional할 경우 생성자 방식으로 객체를 생성하기 위해서는 특정 필드의 존재 유무에 따른 생성자를 모두 만들어줘야한다.

반면에, 빌더 패턴을 사용할 경우 **필요한 데이터만 설정하여 객체를 생성**할 수 있다.

<br>

***

<br>

## 🛠 빌더 패턴의 기본 구조

<br>

```java
public class Member {
    private final String email;
    private final String password;
    private final String name;
    private final Profile profile;

    private Member(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.name = builder.name;
        this.profile = builder.profile;
    }

    public static class Builder {
        // 필수 매개변수
        private final String email;
        private final String password;

        // 선택적 매개변수
        private String name;
        private Profile profile;

        public Builder(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder profile(Profile profile) {
            this.profile = profile;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }
}
```

<br>

- 빌더 패턴의 기본 구조는 Entity 클래스 내부에 정적 클래스인 ```Builder```를 정의하여 사용한다.

- 정적 내부 클래스에서 필수 매개변수는 ```final```을 선언하고, 선택적 매개변수는 기본값을 사용한다.

- Builder 생성자에는 필수 매개변수가 파라미터로 들어가고,  
  선택적 매개변수는 메서드 이름으로 하나씩 정의하며 자기 자신을 반환한다.

- 마지막으로 클래스를 리턴하는 ```build()``` 메서드를 정의한다.

- 이후 Builder를 파라미터로 받는 클래스 생성자를 작성한다.

<br><br>

### 빌더 패턴 사용

<br>

```java
Member member = 
    new Member.Builder("hongjjwan@gmail.com", "1234")
              .name("홍정완")
              .build();
```

<br>

- ```Builder()```의 생성자에 필수 값을 입력한다.

- 선택적 변수는 Optional이므로 입력하지 않아도 된다.

- 최종적으로 ```build()``` 메서드를 사용하여 객체를 생성한다.

<br>

***

<br>

## 🛠 @Builder 어노테이션을 사용한 빌더 패턴

<br>

```java
@Builder
public class Member {
    private String email;
    private String password;
    private String name;
    private Profile profile;
}
```

<br>

- ```@Builder``` 어노테이션을 사용하면 기본적으로 ```Builder()```의 생성자는 NoArgsConstructor로 생성되며,  
  각 필드에 대응하는 Builder 메서드가 생기게 된다.

  _(final을 선언해도 마찬가지)_

<br><br>

### @Builder 어노테이션에 필수 매개변수 입력

<br>

```java
@Builder(builderMethodName = "MemberBuilder")
public class Member {
    private final String email;
    private final String password;
    private String name;
    private Profile profile;

    public static MemberBuilder builder(String email, String password) {
        return MemberBuilder().email(email).password(password);
    }
}
```

<br>

- ```@Builder``` 어노테이션의 속셩 값으로 ```builderMethodName```을 지정해준다.  

  : 지정된 ```MemberBuilder()```를 통해 객체를 만든 후에 Builder 패턴을 적용하겠다는 의미이다.

<br><br>

### 필드 초기값 설정

<br>

```java
@Builder
public class Member {
    private final String email;
    private final String password;
    private String name;
    private Profile profile;

    @Builder.Default private int follow = 0;
    @Builder.Default private int following = 0;
}
```

- 특정 필드에 ```@Builder.Default```를 선언하면,  
  해당 필드에 값이 할당되지 않은 경우 설정된 초기값을 사용하겠다는 의미이다.

- 해당 어노테이션을 적용하지 않은 경우 ```@Builder```에서는 기본값으로 **0 / null / false**를 사용한다.

<br>

***

<br>

## 🔸 빌더 패턴의 장・단점

<br>

### 💡 장점

<br>

**1. 필요한 데이터만 설정할 수 있다.**

- 위에서 말했듯이 필드를 Optional하게 가져갈 수 있다.

<br>

**2. 유연성을 확보할 수 있다.**

- 새롭게 추가되는 변수가 기존의 코드에 크게 영향을 주지 않는다.

<br>

**3. 가독성을 높일 수 있다.**

- 빌더 패턴을 통해 생성하는 객체는 직관적으로 데이터와 값이 연결되기 때문에 가독성을 높일 수 있다.

<br>

**4. 변경 가능성을 최소화 할 수 있다.**

- 수정자 패턴(Setter)을 사용하지 않으므로, 불필요한 변경 가능성을 최소화 할 수 있다.

- 만약 객체에 잘못된 값이 들어왔을 때, 해당 값을 할당하는 시점이 객체의 생성뿐이라면 해당 지점을 찾기 쉬우므로 유지보수성이 높아질 것이다.

<br><br>

### 💡 단점

**1. 필드가 적은 경우 빌더 패턴이 더 난잡할 경우가 있다.**

- 빌더 패턴은 기본적으로 코드가 길기 때문에 필드가 적은 경우에는 생성자 또는 정적 팩토리 메서드를 통한 객체 생성 방식이 더 유리할 수 있다.

<br>

**2. 객체 생성을 라이브러리로 위임하는 경우 사용이 어렵다.**

- Entity 객체로부터 DTO를 생성하는 경우라면 직접 빌더를 만드는 과정이 번거로우므로, MapStruct와 같은 라이브러리를 통해 생성을 위임할 수 있다.

<br>

### 📋 [***Builder 사용 시 주의 사항***](https://johngrib.github.io/wiki/pattern/builder/)

<br><br>

***

_2023.01.04. Update_