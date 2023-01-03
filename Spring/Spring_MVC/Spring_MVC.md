# Spring MVC

Spring MVC는 Spring 모듈 중 **서블릿(Servlet) API**를 기반으로 클라이언트 요청을 처리하는 모듈이며,  
MVC는 각각 Model, View, Controller를 뜻한다.

> ❓ 서블릿 (Servlet)
> 
> 클라이언트의 요청을 처리하도록 특정 규약에 맞추어 Java 코드로 작성하는 클래스파일
>
> 아파치 톰캣(Apache Tomcat)은 서블릿들이 웹 어플리케이션으로 실행되도록 해주는 서블릿 컨테이너(Servlet Container) 중 하나이다.

<br>

### 🔸 Model

Model은 클라이언트에게 응답으로 돌려주는 **작업의 처리 결과 데이터**를 말한다.

> 클라이언트의 요청 사항을 구체적으로 처리하는 영역을 **서비스 계층(Service Layer)** 이라고 하며,  
> 요청 사항을 처리하기 위해 Java 코드로 구현한 것을 **비즈니스 로직(Business Logic)** 이라고 한다.

<br>

### 🔸 View

View는 Model 데이터를 이용하여 웹브라우저와 같은 클라이언트 어플리케이션의 화면에 보여지는 리소스(Resource)를 제공하는 역할을 한다.

**💡 View의 형태**

- **HTML 페이지 출력**
  - 클라이언트 어플리케이션에 보여지는 HTML 페이지를 직접 렌더링하여 클라이언트 측에 전송하는 방식  
  
    기본적인 HTML 태그로 구성된 페이지에 Model 데이터를 채워 넣은 후,  
    최종적인 HTML 페이지를 만들어서 클라이언트 측에 전송해준다.  

    Spring MVC에서 지원하는 HTML 페이지 출력 기술에는 Thymeleaf, FreeMarker, JSP + JSTL, Tiles 등이 있다.  
    > HTML 페이지 출력 방식은 FrontEnd + BackEnd 통합 구조이다.

- **PDF, Excel 등 문서 형태의 출력**
  - Model 데이터를 가공해서 PDF나 Excel 문서로 만들어 클라이언트 측에 전송하는 방식  
  
    문서 내에서 데이터가 동적으로 변경되어야 하는 경우 사용할 수 있는 방식이다.

- **XML, JSON 등 특정 형식의 포맷으로 변환**
  - Model 데이터를 특정 프로토콜 형태로 변환해서, 변환된 데이터를 클라이언트 측에 전송하는 방식    
  
    특정 형식의 데이터만 전송하고, FrontEnd 측에서 해당 데이터를 기반으로 HTML 페이지를 만드는 방식

  - 장점  
    FrontEnd 영역과 BackEnd 영역이 명확하게 구분되어, 개발 및 유지보수가 상대적으로 용이하다.  
    FrontEnd 측에서 비동기 클라이언트 어플리케이션을 만드는 것이 가능하다.

  > ❓ JSON (JavaScript Object Notation)
  >
  > Spring MVC에서 클라이언트 어플리케이션과 서버 어플리케이션이 주고 받는 데이터 형식
  > 기본 포맷 : ```{"속성":"값"}```

<br>

▼ 입력
```java
public class JsonExample {
    public static void main(String[] args) {
        Coffee coffee = new Coffee("아메리카노", "Americano", 3000);
        Gson gson = new Gson();
        String jsonString = gson.toJson(coffee);

        System.out.println(jsonString);
    }
}
```

▼ 출력
```json
{"korName":"아메리카노","engName":"Americano","price":3000}
```

<br>

### 🔸 Controller

Controller는 클라이언트 측의 요청을 직접적으로 전달 받는 엔드포인트(Endpoint)로써  
Model과 View의 중간에서 상호 작용을 해주는 역할을 한다.

즉, 클라이언트 측 요청을 전달 받아 비즈니스 로직을 거친 후 Model 데이터가 만들어지면,  
Model 데이터를 View로 전달하는 역할을 한다.

```java
@RestController
@RequestMapping(path = "/v1/coffee")
public class CoffeeController {                 
    private final CoffeeService coffeeService;

    CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping("/{coffee-id}")  // 클라이언트 요청 수신
    public Coffee getCoffee(
            @PathVariable("coffee-id") long coffeeId) {

      return coffeeService.findCoffee(coffeeId);
      // 비즈니스 로직 처리 -> Model 데이터
    }
}
```

<br>

***

<br>

## Presentation Layer

Spring 웹 계층에서 MVC는 Presentation Layer를 담당한다.

프론트 컨트롤러(DispatcherServlet), 컨트롤러, 뷰, 모델이 포함된다.

<br>

<img src = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile21.uf.tistory.com%2Fimage%2F991CBB505C6BE2AC02F7CE" width = "90%"/>

▲ _Spring Layered Architecture_

<br>

> **💡 Spring MVC의 동작 방식**
>
> Client가 요청 데이터 전송  
> → Controller가 요청 데이터 수신  
> → 비즈니스 로직 처리  
> → Model 데이터 생성  
> → Controller에게 Model 데이터 전달  
> → Controller가 View에게 Model 데이터 전달  
> → View가 응답 데이터 생성  
> → Client에게 응답 데이터 전달

<br>

### 📋 [***Presentation Layer***](https://github.com/H-JWANNA/TIL/blob/main/Spring/Presentation_Layer.md "프레젠테이션 계층")

### 📋 [***Service Layer***](https://github.com/H-JWANNA/TIL/blob/main/Spring/Service_Layer.md "서비스 계층")

### 📋 [***Data Access Layer***](https://github.com/H-JWANNA/TIL/blob/main/Spring/DataAccess_Layer.md "데이터 액세스 계층")

<br><br>

***

_2022.10.20. Update_