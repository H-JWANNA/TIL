# HTTP

HTTP는 HTML과 같은 문서를 전송하기 위한 Application Layer 프로토콜이며,  
웹 브라우저와 웹 서버의 소통을 위해 디자인되었다.

HTTP는 특정 상태를 유지하지 않는 **무상태성(Stateless)** 이라는 특징이 있다.

## HTTP Messages

클라이언트와 서버 사이에서 데이터가 교환되는 방식을 말하며 2가지 유형이 존재한다.

<br>

### 요청(Requests)

<br>

<img src = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdM2OHy%2FbtqV2betcva%2F87Fk9IlSlQ6vFdCik3pKW0%2Fimg.png" width = "90%" />

▲ _HTTP Request Message Format_

<br>

**🔸 Request Line**

Start Line이라고도 하며, 3가지 요소가 포함된다.

➣ HTTP Method  
수행할 작업(GET, PUT, POST 등)이나 방식(HEAD, OPTIONS)을 설명

|요청|Method|
|:-:|:-:|
|Create (추가)|```POST```|
|Read (조회)| ```GET``` |
|Update (갱신)| ```PUT```, ```PATCH``` |
|Delete (삭제)| ```DELETE``` |

### 📋 [***HTTP Request Method***](https://developer.mozilla.org/ko/docs/Web/HTTP/Methods)

<br>

➣ Web Pages (요청 컨텍스트)  
URL이나 URI 또는 프로토콜, 포트, 도메인의 절대 경로가 작성된다.  
해당 요청 형식은 HTTP Method마다 다르다.
- origin 형식   
```?```와 쿼리 문자열이 붙는 절대 경로. ```POST```, ```GET```, ```HEAD```, ```OPTION``` 등의 method와 함께 사용된다.  

  > ```POST / HTTP 1.1GET /background.png HTTP/1.0HEAD /test.html?query=alibaba HTTP/ 1.1OPTIONS /anypage.html HTTP/1.0```


- absolute 형식  
완전한 URL형식으로, 프록시에 연결하는 경우 대부분 ```GET```과 함께 사용된다.  
  > ```GET http://developer.mozilla.org/en-US/docs/Web/HTTP/Messages HTTP/1.1```

- authority 형식  
  도메인 이름과 포트번호로 이루어진 URL의 authority component  
  HTTP 터널을 구축하는 경우 ```CONNECT```와 함께 사용  
  > ```CONNECT developer.mozilla.org:80 HTTP/1.1```

- asterisk 형식  
  ```OPTIONS```와 함께 별표(```*```)하나로 서버 전체를 표현  
  > ```OPTIONS * HTTP/1.1```

<br>

➣ HTTP Version  
HTTP 버전에 따라 HTTP Message 구조가 달라지므로 함께 입력한다.

<br>

**🔸 Header**

Request Header에는 헤더 이름, 콜론(:), 값이 입력된다.

- ```Host``` : 클라이언트가 요청한 도메인 정보
- ```Accept``` : 웹 서버로부터 수신되는 데이터 중 웹 브라우저가 처리할 수 있는 데이터의 형식 정의
- ```User-Agent``` : 사용자 웹 브라우저 종류 및 버전 정보
- ```Cookie``` : 클라이언트 로컬에 저장되는 key-value쌍의 데이터 파일
- ```Referer``` : 경유한 웹 사이트에 대한 정보

<br>

<img src = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages/http_request_headers3.png" width ="90%"/>

<br>

또한, 헤더는 위와 같이 그룹을 나눌 수 있다.

- Request headers  
  fetch를 통해 가져올 리소스나 클라이언트의 정보를 포함하는 헤더  
  > ```User-Agent```, ```Accept-Type```, ```Accept-Language```와 같은 헤더는 요청을 보다 구체화한다.   
  > ```Referer```처럼 컨텍스트를 제공하거나 ```If-None```과 같이 조건에 따라 제약을 추가할 수 있다.

- General headers  
  Message 전체에 적용되는 헤더로, body를 통해 전송되는 데이터와는 관련이 없는 헤더

- Entity headers (Representation headers)  
  body에 담긴 리소스의 정보(콘텐츠 길이, MIME 타입 등)를 포함하는 헤더

  📋 [**_MIME 타입이란?_**](https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types)

<br>

**🔸 Body**

요청의 본문은 Message 마지막에 위치한다.  
```GET```, ```HEAD```, ```DELETE```, ```OPTIONS```처럼 서버에 리소스를 요청하는 경우에는 본문이 필요하지 않다.  
```POST```, ```PUT```과 같은 일부 요청은 업데이트를 위해 사용한다.  

Body는 2가지 종류로 나눌 수 있다.

- Single-resource bodies (단일-리소스 본문)  
  헤더 2개(Content-Type, Content-Length)로 정의된 단일 파일로 구성

- Multiple-resource bodies(다중-리소스 본문)  
  각 파트마다 다른 정보를 지닌 여러 파트의 본문으로 구성  
  보통 [HTML form](https://developer.mozilla.org/en-US/docs/Learn/Forms)과 관련이 있다.

<br>

### 응답(Responses)

<br>

<img src = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbkBRZH%2FbtqVV2JEnFj%2FpFjfpwMOjGI2lCLIyf6dkk%2Fimg.png" width = "90%" />

▲ HTTP Response Message Format

<br>

**🔸 Status Line**

응답의 첫 줄은 Status Line이라고 하며 몇 가지 정보를 포함하고 있다.
- 현재 프로토콜의 버전(```HTTP/1.1```)
- 상태 코드 : 요청의 결과를 나타냄 (```200```, ```302```, ```404``` 등)
- 상태 텍스트 : 상태 코드에 대한 설명

이들이 합쳐저 ```HTTP/1.1 404 Not Found.```와 같은 Status Line이 생성된다.

<br>

> 💡 Status Code
> - 2xx : 성공
> - 3xx : 리다이렉션 필요 (요청을 완수하기 위해 추가적인 방법이 필요)
> - 4xx : 클라이언트 요청 오류 (잘못된 요청 / 문법 에러)
> - 5xx : 서버 내부 오류

### 📋 [**_HTTP Status Code_**](https://developer.mozilla.org/ko/docs/Web/HTTP/Status)

<br>

**🔸 Header**

Response Header도 Request Header와 동일한 구조를 가진다.

<br>

<img src = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages/http_response_headers3.png" width = "90%" />

<br>

Response Header도 위와 같이 그룹을 나눌 수 있다.

- Response headers  
  위치 또는 서버 자체에 대한 정보(이름, 버전 등)와 같이 응답에 대한 부가적인 정보를 갖는 헤더  
  ```Vary```, ```Accept-Ranges```와 같이 Status Line에 넣기에 공간이 부족했던 추가 정보 제공
- General headers  
  Message 전체에 적용되는 헤더로, body를 통해 전송되는 데이터와는 관련이 없는 헤더  
- Entity headers (Representation headers)  
  body에 담긴 리소스의 정보(콘텐츠 길이, MIME 타입 등)를 포함하는 헤더

  _<span style = "color : gray">(General Headers, Representation Headers는 Request Header와 동일)</span>_

<br>

**🔸 Body**

응답의 본문은 Message 마지막에 위치한다.  
```201```, ```204```와 같은 상태코드를 가지는 응답은 본문이 필요하지 않다.   

Body는 2가지 종류로 나눌 수 있다.

- Single-resource bodies (단일-리소스 본문)  
  1. 길이가 알려진 단일-리소스 본문은 헤더 2개(Content-Type, Content-Length)로 정의  
  2. 길이를 모르는 단일 파일로 구성된 단일-리소스 본문은 Transfer-Encoding이 ```chunked```로 설정되어 있으며, 파일은 chunk로 나뉘어 인코딩되어 있다.

- Multiple-resource bodies(다중-리소스 본문)  
  서로 다른 정보를 담고있는 body

<br>

### 💡 Request와 Response의 구조 요약

1. Start line   
   첫번째 줄에는 항상 요청이나 응답의 상태를 나타냅니다.  
   응답에서는 status line이라고 부른다.

2. HTTP headers  
   요청을 지정하거나, 메시지에 포함된 본문을 설명하는 헤더의 집합

3. Empty line   
   헤더와 본문을 구분하는 공백

4. Body   
   요청과 관련된 데이터나 응답과 관련된 데이터 또는 문서를 포함한다.  
   요청과 응답의 유형에 따라 선택적으로 사용

### 📋 [**_HTTP Message_**](https://developer.mozilla.org/ko/docs/Web/HTTP/Messages)

### 📋 [**_HTTP API Design_**](https://koreanjson.com/)

<br>

***

<br>

## REST API

REST API는 Respresentational State Transfer Application Programming Interface의 약자로  
로이 필딩의 박사학위 논문에서 웹(HTTP)의 장점을 최대한 활용할 수 있는 아키텍처로써 처음 소개되었다.   

REST API는 웹에서 사용되는 **데이터나 자원(Resource)을 HTTP URI로 표현**하고,  
HTTP 프로토콜을 통해 요청과 응답을 정의하는 방식을 말한다.

<br>

<img src = "https://devopedia.org/images/article/252/1821.1579540894.jpg" width = "90%" />

▲ _Richardson 성숙도 모델_

> 로이 필딩은 모든 단계를 충족해야 REST API라고 부를 수 있다고 했지만,  
> 실제로 3단계까지 지키기 어렵기 때문에 2단계까지만 적용해도 좋은 API Design이라고 볼 수 있고,  
> 이러한 경우에는 HTTP API라고도 부른다.

<br>

### 0단계

0단계에서는 단순히 **HTTP 프로토콜을 사용**만 해도 된다.

```json
// (Request) 예약 가능 시간 확인 
POST /appointment HTTP/1.1
~Skip Header~

{
  "date":"2022-10-04",
  "doctor":"Schweitzer"
}
```

```json
// (Request) 예약 
POST /appointment HTTP/1.1
~Skip Header~

{
  "doctor":"Schweitzer",
  "start":"14:00",
  "end":"15:00",
  "patient":"H-JWANNA"
}
```

<br>

### 1단계

1단계에서는 **개별 리소스와의 통신을 준수**해야한다.

모든 자원은 개별 리소스에 맞는 엔드포인트(Endpoint)를 사용해야 하고,  
요청하고 받은 자원에 대한 정보를 응답으로 전달해야 한다.

```json
// (Request) 예약 가능 시간 확인 
POST /doctor/Schweitzer HTTP/1.1  // 개별 엔드포인트
~Skip Header~

{
  "date":"2022-10-04",
}
```

```json
// (Response) 예약 가능 시간 확인 
HTTP/1.1 200 OK
~Skip Header~

{
  "slots": [
    { "id":123,"doctor":"Schweitzer","start":"09:00","end":"12:00"},
    { "id":124,"doctor":"Schweitzer","start":"14:00","end":"16:00"},
  ]
}
```

```json
// (Request) 예약 
POST /slots/123 HTTP/1.1
~Skip Header~

{
  "patient":"H-JWANNA"
}
```

```json
// (Response) 예약 
HTTP/1.1 409 Conflict
~Skip Header~

{
  "appointmentFailure": {
    { "id":123, … },
    "patient":"H-JWANNA",
    "reason":"해당 시간은 예약이 마감되었습니다."
  }
}
```

▲ _응답을 전달할 때에도 성공, 실패 여부를 포함한 응답을 전달한다._

<br>

**💡 Endpoint 작성 요령**

- 행위를 나타내는 동사형으로 작성하기보다 명사형(목적어)으로 작성한다.  
  <span style = "color:gray">(리소스를 어떻게 할지에 대한 동사는 HTTP Method에서 선언)</span>

- 리소스는 소문자로만 작성하고, 길어지는 경우에 하이픈(-)으로 단어를 구분한다.
  
- 위 예시처럼 바디에 적는 것 보다 쿼리파라미터에 적는 것이 좋다.

<br>

### 2단계

2단계에서는 CRUD에 맞는 적절한 HTTP Method를 사용하는 것에 중점을 둔다.

추가로, ```GET``` 메서드는 바디를 가지지 않기 때문에 쿼리파라미터를 통해 필요한 리소스를 전달한다.

```json
// (Request) 예약 가능 시간 확인 
GET /doctor/Schweitzer/slots?date=2022-10-04 HTTP/1.1  // 개별 엔드포인트
~Skip Header~

```

```json
// (Request) 예약 
POST /slots/123 HTTP/1.1
~Skip Header~

{
  "patient":"H-JWANNA"
}
```

<br>

**💡 HTTP Method 사용 시 주의사항**

- ```GET``` 메서드는 서버의 데이터를 변화시키지 않는 요청에 사용한다.
- ```PUT```과 ```PATCH``` 둘 다 UPDATE와 관련된 메서드이지만  
  ```PUT```은 교체. 즉 전체를 수정할 때 주로 사용하고,  
  ```PATCH```는 수정. 즉 일부분을 수정할 때 주로 사용한다.

<br>

### 3단계

3단계는 HATEOAS(Hypertext As The Engine Of Application State)라고 하는 하이퍼미디어 컨트롤을 적용한다.

요청은 2단계와 동일하지만, 응답에는 리소스의 URI를 포함한 링크 요소를 삽입하여 작성한다.

이때, 링크 요소는 응답을 받은 이후에 할 수 있는 다양한 액션들을 위해 많은 하이퍼미디어 컨트롤을 포함한다.

```json
// (Response) 예약 가능 시간 확인 
HTTP/1.1 200 OK
~Skip Header~

{
  "slots": [
    { "id":123,"doctor":"Schweitzer","start":"09:00", … }, …
  ],
  "links": {
    "appointment": {
      "href":"http://localhost:8080/slots/123",
      "method":"POST"
    }
  }
}
```

```json
// (Response) 예약
HTTP/1.1 201 Created
Location: slots/123/appointment
~Skip Header~

{
  "appointment": {
    "slot": { "id":123, … },
    "patient":"H-JWANNA",
  },
  "links": {
    "self": {
      "href":"http://localhost:8080/slots/123",
      "method":"GET"
    },
    "cancel": {
      "href": "http://localhost:8080/slots/123/cancel",
      "method":"DELETE"
    }
  }
}
```

<br>

위처럼 예약 가능 시간을 확인한 이후에는 예약을 할 수 있는 링크를 삽입하고,  
예약을 완료한 후에는 확인 또는 취소할 수 있도록 하여 **새로운 기능에 접근할 수 있도록 하는 것**이 3단계의 포인트이다.

<br>

### 📋 [**_5 Basic REST API Design Guidelines_**](https://blog.restcase.com/5-basic-rest-api-design-guidelines/)
### 📋 [**_Microsoft REST API Guidelines_**](https://github.com/Microsoft/api-guidelines/blob/master/Guidelines.md)
### 📋 [**_Google API Design Guide_**](https://cloud.google.com/apis/design?hl=ko)

<br><br>

***

_2022.10.02. Update_