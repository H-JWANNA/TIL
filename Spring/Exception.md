# 예외 처리

## Presentation 예외 처리

### 🔸 @ExceptionHandler

```@ExceptionHandler```이 추가된 예외 처리 메서드가 있을 경우 예외를 해당 메서드가 전달받아 처리한다.

<br>

```java
@RestController
@RequestMapping("/v1/members")
@Validated
@Slf4j
public class MemberController {
    ...

    @ExceptionHandler
    public ResponseEntity handleException(MethodArgumentNotValidException e) {

        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
    }
}
```
▲ _Controller 내부에 예외 처리 메서드 작성_

<br>

위와 같이 클래스 내부에 예외 처리 메서드를 작성한 경우 2가지 문제점이 있다.

- 해당 클래스 레벨에서 발생하는 예외만 처리 가능  
- ```getBindingResult().getFieldErrors()```를 통해 구체적인 에러 메시지를 전송받지만,  
  특정 정보만 받기 위해서는 추가적인 클래스를 작성해야한다.

<br>

### 🔸 @RestControllerAdvice

```@ControllerAdvice``` + ```@RequestBody```를 나타내는 어노테이션으로  
Rest API 기반의 ```@Controller``` 전역에서 발생할 수 있는 예외를 처리해주는 어노테이션이다.

```@RestControllerAdvice```을 사용하면 ```@ResponseBody```의 기능을 포함하므로  
JSON 형식의 데이터를 Response Body로 전송하기 위해 ResponseEntity로 래핑할 필요가 없다.

<br>

특정 클래스에 ```@RestControllerAdvice``` 어노테이션을 추가하면 여러개의 Controller 클래스에서  
```@ExceptionHandler```, ```@InitBinder```, ```@ModelAttribute```가 작성된 메서드를 공유해서 사용할 수 있다.

> ❓ **```@InitBinder```, ```@ModelAttribute```**
> 
> 두 어노테이션은 JSP, Thymeleaf와 같은 SSR 방식에서 주로 사용된다.

<br>

아래는 다양한 예외에 대해 특정한 정보를 받기 위한 클래스와 Controller 전역에서 발생하는 예외를 처리하기 위한 클래스이다.

<span style = "color : gray">_(아래 요약 있음)_</span>

<br>

```java
@Getter
public class ErrorResponse {

    // fieldErrors : 'DTO 멤버 변수 필드의 유효성 검증 실패'로 발생한 에러 정보를 담는 멤버 변수
    // violationErrors : 'URI 변수 값의 유효성 검증에 실패'로 발생한 에러 정보를 담는 멤버 변수
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;

    // private를 사용한 생성자 → new 키워드를 통해 객체를 생성할 수 없음 → of() 메서드로 생성
    private ErrorResponse(final List<FieldError> fieldErrors,
                         final List<ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    // MethodArgumentNotValidException에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult), null);
    }

    // ConstraintViolationException에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationError.of(violations));
    }

    // 필드(DTO 클래스의 멤버 변수)의 유효성 검증에서 발생하는 에러 정보 생성
    @Getter
    public static class FieldError {
        private String field;
        private Object rejectedValue;
        private String reason;

        public FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors =
                    bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                    error.getField(),
                    error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                    error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

    // URI 변수 값에 대한 에러 정보 생성
    @Getter
    public static class ConstraintViolationError {
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        public ConstraintViolationError(String propertyPath, Object rejectedValue, String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<ConstraintViolationError> of(
                Set<ConstraintViolation<?>> constraintViolations) {

            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()))
                    .collect(Collectors.toList());
        }
    }
}
```
▲ _ErrorResponse Class_

<br>

### **💡 요약 정리**

위의 ErrorResponse 클래스에서 처리하는 Exception은 2가지이다.
- ```MethodArgumentNotValidException``` : **DTO 멤버 변수 필드의 유효성 검증 실패**로 발생한 에러
- ```ConstraintViolationException``` : **URI 변수 값의 유효성 검증에 실패**로 발생한 에러

그리고 위에서 발생한 예외 정보를 담는 객체는 아래와 같다.

- ```BindingResult``` : 검증 오류가 발생할 경우 오류 내용을 보관하는 스프링 프레임워크에서 제공하는 객체
- ```ConstraintViolation``` : ConstraintViolationException 발생 시 오류 내용을 보관하는 객체

<br>

1. 위 2가지 예외 정보를 담는 변수를 아래와 같이 선언하고 생성자를 만든다.

```java
private List<FieldError> fieldErrors;
private List<ConstraintViolationError> violationErrors;

private ErrorResponse(final List<FieldError> fieldErrors,
                      final List<ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
}
```

생성자는 ```private``` 접근 제어자를 사용했기 때문에,  
```new``` 키워드를 사용해 객체를 생성하는 것이 아닌 ```of()``` 메서드를 사용해 생성할 수 있다. 

> ❓ ```of()```
>
> of() 메서드는 Java 8의 API에서도 흔히 볼 수 있는 네이밍 컨벤션(Naming Convention)이다.
>
> 주로 객체 생성시 어떤 값들의(of~) 객체를 생성한다는 의미에서 of() 메서드를 사용한다.

<br><br>

2. 다음으로 각각의 에러가 어떠한 정보를 담을지 정해서 static 중첩 클래스를 작성한다.

```java
@Getter
public static class ConstraintViolationError {
    private String propertyPath;
    private Object rejectedValue;
    private String reason;

    // AllArgsConstructor

    public static List<ConstraintViolationError> of(
            Set<ConstraintViolation<?>> constraintViolations) {

        return constraintViolations.stream()
                .map(constraintViolation -> new ConstraintViolationError(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getInvalidValue().toString(),
                        constraintViolation.getMessage()))
                .collect(Collectors.toList());
    }
}
```

모든 에러 정보가 담긴 ```constraintViolation``` 객체의  

```getPropertyPath()```, ```getInvalidValue()``` 등의 메서드를 사용해서  

원하는 에러 정보(propertyPath, rejectiveValue …)를 담은 ```ConstraintViolationError``` 객체를 만들고,  

```of()``` 메서드를 통해 위에서 만든 객체를 담은 List를 생성한다.

<br>

**즉, 원하는 에러 정보를 담은 List를 생성한다.**

<br>

> **💡 원하는 정보만 담지 않으면**  
> 
> 해당 객체가 가진 defaultMessage, objectName, field, rejectedValue, bindingFailure 등  
> 모든 정보를 리턴하기 때문에 원하는 정보만 골라서 담는다.

<br>

> **💡 static 중첩 클래스를 사용하는 이유**
>
> 상위 클래스에서만 사용하는 정보이므로 내부 클래스로 선언하고,  
> 외부 인스턴스 멤버의 직접 참조가 필요하지 않은 경우라면 static 클래스로 만들어 메모리를 확보한다.
> 
> **결론 : 성능의 효율을 위해서 사용한다.**  
> 
> 📋 [***참고 사이트***](https://velog.io/@agugu95/%EC%99%9C-Inner-class%EC%97%90-Static%EC%9D%84-%EB%B6%99%EC%9D%B4%EB%8A%94%EA%B1%B0%EC%A7%80)

<br><br>

3. ```@RestContollerAdvice```를 적용한 클래스에 사용하기 위한 객체를 생성한다.

```java
public static ErrorResponse of(BindingResult bindingResult) {
    return new ErrorResponse(FieldError.of(bindingResult), null);
}
```

위 코드에서는 ```FieldError```의 예외 정보가 ```BindingResult```에 담겨있으므로 해당 클래스를 입력받은 뒤,  
2번에서 만든 클래스를 적용해 원하는 정보만 가져온 ErrorResponse 생성자를 만든다.

이때, 뒤의 값이 ```null```인 것은  
처음에 생성한 ```private``` 생성자가 2가지 예외를 입력받지만,  
위 코드에서 반환하려는 예외 정보는 ```MethodArgumentNotValidException```이므로 1번째만 입력한다.

<br><br>

4. ```@RestControllerAdvice```를 적용한 클래스에 직접 적용

```java
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());

        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(
            ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());

        return response;
    }
}
```
▲ _GlobalExceptionAdvice Class_

<br>

```@ExceptionHandler```를 사용해 예외를 전달받도록 하고,  
```@ResponseStatus```를 사용해 HTTP Status를 HTTP Response에 포함시킨다.

그 후, 생성한 ```ErrorResponse``` 클래스를 반환하는 메서드를 생성하여 적용한다.

메서드는 **예외 클래스를 입력** 받아서 **ErrorResponse를 이용해 변환한 결과를 리턴**한다.

<br>

***

<br>

## Service Layer 예외 처리

Service Layer의 비즈니스 로직은 Presentation Layer의 Controller가 처리하므로  
비즈니스 로직에서 발생한 예외도 Controller의 핸들러 메서드에서 잡아서 처리할 수 있다.

위에서 Controller에서 발생하는 예외를 Exception Advice에서 처리하도록 공통화 해두었으니  
서비스 계층에서 발생한 예외도 Exception Advice에서 처리할 수 있다.

<br>

1. 먼저, 예외를 처리하기 위해서는 ErrorResponse 클래스에 출력하고자하는 필드 변수와 생성자를 생성한다.

```java
@Getter
public class ErrorResponse {

    private int status;
    private String message;
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
    ...
}
```

기존의 fieldErrors, violationErrors 외에 ```status```, ```message``` 정보를 출력하기 위해 변수와 생성자를 만든다.

<br><br>

2. 그 후, HTTP 상태에 따른 status와 reasonPhrase를 생성자에 담는다.

```java
public static ErrorResponse of(HttpStatus httpStatus) {
    return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase());
}
```

<br><br>

3. 위에서 정의한 생성자를 통해 Exception Advice 클래스에 핸들러 메서드를 정의한다.

```java
@RestControllerAdvice
public class GlobalExceptionAdvice {
    ...

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(RuntimeException e) {

        System.out.println(e.getMessage());

        final ErrorResponse response = 
                            ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR)

        return response;
    }
}
```
▲ _500, Internal Server Error가 발생하면 예외를 클라이언트에 전송하기 위한 메서드_

<br>

위와 같이 기존 Controller에서 사용하던 예외 처리 클래스에 **서비스 계층에서 발생한 예외**를 던지면 된다.

<br><br>

### **🔸 사용자 정의 예외 (Custom Exception)**

사용자 정의 예외는 사용자가 원하는 에러 값을 Response Body로 전송하기 위해 만드는 예외이다.

<br>

1. 에러 코드 작성

```java
public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member Not Found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
```

```enum```을 통해 정의할 수 있으며 여러가지 에러를 생성할 수 있다.

> 가장 예시로 보기 좋은 enum을 통한 Exception은 HttpStatus이다.

<br><br>

2. 서비스 계층에서 사용할 Custom Exception을 정의

```java
public class BusinessLogicException extends RuntimeException {
    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
```

BusinessLogicException은 ```RuntimeException```을 상속하고 있으며,  
ExceptionCode를 멤버 변수로 지정하여 생성자를 통해 위에서 **정의한 예외 정보를 제공**할 수 있다.

- 상위 클래스인 RuntimeException의 생성자(super)로 예외 메시지를 전달한다.

- BusinessLogicException은 서비스 계층에서 개발자가 의도적으로 예외를 던져야 하는 다양한 상황에서  
  ExceptionCode 정보만 바꿔가며 던질 수 있다.

<br><br>

3. ErrorResponse 클래스에 ExceptionCode의 getter를 생성자 파라미터로 전달

```java
public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }
```

<br><br>

4. Exception Advice 클래스에 CustomException을 처리할 핸들러 메서드를 작성
   
```java
@ExceptionHandler
public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
   System.out.println(e.getExceptionCode().getStatus());
   System.out.println(e.getMessage());

   final ErrorResponse response = ErrorResponse.of(e.getExceptionCode());

   return new ResponseEntity<>(response, 
                        HttpStatus.valueOf(e.getExceptionCode().getStatus()));
}
```

```ErrorResponse.of()```의 파라미터로 ```ExceptionCode```를 받았기 때문에  

위에서 정의한 ```new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage())```를 반환한다.

<br><br>

### **🔸 의도적으로 예외 발생 시키기 (throw/catch)**

위에서 작성한 BusinessLogicException은 catch를 위한 부분이고,  

서비스 계층에서 의도적으로 예외를 발생시켜 에러 핸들링을 테스트할 수 있다.

```java
@Service
public class MemberService {
    ...

    public Member findMember(long memberId) {

        throw new RuntimeException("Not found member");
    }
}
```

<br>

서비스 로직에서 ```throw```를 통해 새로운 Exception 객체를 생성해서 의도적으로 예외를 발생시킬 수 있다.

<br><br>

***

_2022.10.25. Update_