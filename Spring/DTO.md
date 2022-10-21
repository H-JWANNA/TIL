# DTO (Data Trasfer Object)

DTO는 계층간 데이터 교환을 위해 사용하는 객체(Java Bean)이다.

**💡 DTO 클래스를 사용하는 목적**
- 비용이 많이 드는 작업인 HTTP 요청의 수를 줄인다.
- 도메인 객체와의 분리
- 데이터 유효성 검증의 단순화
- Request Body를 하나의 객체로 받으므로, 코드가 간결해진다.

<br>

```java
public ResponseEntity postMember(@RequestParam("email") String email,
                                 @RequestParam("name") String name,
                                 @RequestParam("phone") String phone) {
    Map<String, String> map = new HashMap<>();
    map.put("email", email);
    map.put("name", name);
    map.put("phone", phone);

    return new ResponseEntity<Map>(map, HttpStatus.CREATED);
}
```

위의 코드처럼 여러가지 파라미터를 전달받는 경우, DTO 클래스를 사용하여 요청 데이터를 하나의 객체로 전달 받도록 할 수 있다.

<br>

```java
public ResponseEntity postMember(MemberDto memberDto) {
    return new ResponseEntity<MemberDto>(memberDto, HttpStatus.CREATED);
}
```
▲ _DTO 클래스 적용_

<br>

### 🛠 DTO 클래스 적용

1. DTO 클래스 생성

```java
@Getter
public class MemberPostDto {
    private String email;
    private String name;
    private String phone;
}
```

```java
@Getter
@Setter
public class MemberPatchDto {
    private long memberId;
    private String name;
    private String phone;
}
```

> DTO 클래스를 만들 때, 멤버 변수에 해당하는 ```getter``` 메서드가 있어야한다.
>
> getter가 포함되지 않으면 Response Body에 해당 멤버 변수의 값이 포함되지 않는다.

<br>

2. DTO 클래스 적용

```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {

    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberPostDto memberPostDto) {
        return new ResponseEntity<>(memberPostDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);
        memberPatchDto.setName("홍길동");
        // No need Business logic
        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {
        // No need business logic
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
```

```@RequestParam``` 대신에 DTO 클래스 객체를 통해 Request Body를 한 번에 전달 받을 수 있다.

**🔸 @RequestBody**

클라이언트 측에서 Json 형식의 Request Body를 전송하면,  
```@RequestBody``` 어노테이션을 통해 ```MemberPostDto```의 객체로 변환된다.
> Json이 아닌 다른 형식의 데이터를 전송하면, Spring 내부에서 ‘Unsupported Media Type’ 에러 메시지를 포함한 응답을 전달

<br>

**🔸 @ResponseBody**

반대로 DTO 클래스의 객체를 Json 형식으로 변환하는 어노테이션이다.

Spring MVC에서 Handler Method에 ```@ResponseBody```가 붙거나 리턴 값이 ```ResponseEntity```인 경우,  
내부적으로 ```HttpMessageConverter```가 동작해서 응답 객체를 Json 형식으로 변환한다.

> **❓ JSON 직렬화(Serialization)와 역직렬화 (Deserialization)**
> 
> JSON 직렬화 : Java 객체 → JSON (@ResponseBody)  
> JSON 역직렬화 : JSON → Java 객체 (@RequestBody)

<br>

***

<br>

## 유효성 검증 (Validation)

1차적으로 FrontEnd 영역에서 유효성 검사를 하지만,  
JavaScript로 전송되는 데이터는 브라우저의 개발자 도구를 사용해서 Breakpoint를 추가한 뒤에 얼마든지 값을 조작할 수 있기 때문에, 서버 쪽에서 한번 더 유효성 검사를 진행해야한다.

DTO 클래스에 유효성 검증을 위해서는 Spring Boot에서 지원하는 Starter가 필요하다.

```java
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-validation'
}
```

<br>

### DTO 유효성 검증

DTO 클래스의 필드 변수에 어노테이션을 작성하여 유효성 검증을 한다.

이후 Controller 클래스에서 DTO 클래스 객체를 사용하는 부분에 ```@Valid``` 어노테이션을 사용하여 적용한다.

```java
@Getter
public class MemberPostDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String name;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "올바르지 않은 형식입니다.")
    private String phone;
```
▲ _MemberPostDto.java_

<br>

```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
        return new ResponseEntity<>(memberDto, HttpStatus.CREATED);
    }
	...
}
```
▲ _MemberController.java_

<br>

### @PathVariable 및 쿼리 파라미터 유효성 검증

DTO 클래스에서 사용되는 값이 아닌,  
URI path에서 사용되는 ```@PathVariable() long id```와 같은 변수는 **변수 앞에 어노테이션을 사용**해 데이터를 검증한다.

또한, 클래스에 ```@Validated``` 어노테이션을 사용해야한다.

<br>

```java
@RestController
@RequestMapping("/v1/members")
@Validated
public class MemberController {

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Min(1) long memberId,
                                    @Valid @RequestBody MemberPatchDto memberPatchDto) {

        memberPatchDto.setMemberId(memberId);

        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
    }
    ...
}
```
▲ _MemberController.java_

<br>

### Jakarta Bean Validation

Jakarta Bean Validation은 라이브러리처럼 사용할 수 있는 API가 아닌 **유효성 검증을 위한 표준 스펙**이다.

Java Bean 스펙을 준수하는 Java 클래스라면 Jakarta Bean Validation의 어노테이션을 사용해 유효성 검증을 할 수 있다.

<br>

**🔸 @NotNull**

```null```값을 허용하지 않는다.

<br>

**🔸 @NotEmpty**

```null```값이나 공백("")값을 허용하지 않는다.

<br>

**🔸 @NotBlank**

```null```값이나 공백(""), 스페이스(" ") 같은 값들을 허용하지 않는다.

<br>

**🔸 @Email**

유효한 이메일 주소인지 검증한다.

- javax에서 지원하는 표준 Email 어노테이션은 ```javax.validation.constraints.Email```이다.

- 때로는 _hongjjwan@gmail_ 처럼 최상위 도메인(```.com```)이 없는 경우에도 유효성 검증에 통과한다.  
  최상위 도메인까지 포함한 이메일 주소를 판단하고자 하면 정규 표현식을 이용해야한다.

<br>

**🔸 @Pattern**

정규 표현식 (Regular Expression)에 매치되는 유효한 문자열인지 검증한다.

<br>

**🔸 @Min(num), @Max(num)**

숫자형 타입에 사용 가능하며, ```num``` 이상의 값을 가지는지 / ```num``` 이하의 값을 가지는지 검증한다.

<br>

**🔸 @Range**

숫자형 타입에 사용 가능하며, 조건을 통해 범위 내의 값을 가지는지 검증한다.

<br>

**🔸 @Positive, @Negative**

숫자형 타입에 사용 가능하며, 양수 / 음수의 값을 가지는지 검증한다.

<br>

### Custom Validator

Jakarta Bean Validation에 내장된 어노테이션 중 적절한 것이 없다면,  
목적에 맞는 어노테이션을 직접 만들어 유효성 검증에 적용할 수 있다.

<br>

1. Custom Annotation 정의

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotSpaceValidator.class}) // (1)
public @interface NotSpace {
    String message() default "공백이 아니어야 합니다"; // (2)
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

(1) : ```@NotSpace``` 어노테이션이 추가되었을 때, 동작할 Custom Validator 추가

(2) : 유효성 검증에 실패했을 때 표시되는 기본 메시지

<br>

2. Custom Validator 구현

```java
public class NotSpaceValidator implements ConstraintValidator<NotSpace, String> {

    @Override
    public void initialize(NotSpace constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || StringUtils.hasText(value);
    }
}
```

```CustomValidator```를 구현하기 위해서는 ```ConstraintValidator``` 인터페이스를 구현해야 한다.


```ConstraintValidator<NotSpace, String>```에서  
- ```NotSpace```는 CustomValidator와 매핑된 ```Custom Annotation```을 의미  
- ```String```은 Custom Annotation으로 **검증할 대상 멤버 변수의 타입**을 의미

<br>

3. 유효성 검증이 필요한 DTO 클래스의 멤버 변수에 Custom Annotation 추가

```java
public class MemberPatchDto {
    private long memberId;

    @NotSpace(message = "회원 이름은 공백이 아니어야 합니다")
    private String name;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
    private String phone;
```

직접 어노테이션을 추가하여 유효성 검증이 되면 완료

<br><br>

***

_2022.10.20. Update_