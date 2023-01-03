# API 문서화

❓ API 문서화란  

클라이언트가 REST API 백엔드 어플리케이션에 요청을 전송하기 위해 알아야하는 요청 정보를 문서로 정리하는 것을 말한다.

요청 정보에는 요청 URL(URI), request body, query parameter 등이 포함된다.

<br>

Java 기반의 어플리케이션에서 API 문서 자동화 방식은 **Spring Rest Docs**를 통한 방식과 **Swagger**를 통한 방식이 있다.

> 수기로 작성하는 방법도 존재한다.

<br>

***

<br>

## Spring Rest Docs

Spring Rest Docs는 테스트 코드 기반의 API 문서화 방식이다.

따라서, Controller에 대한 슬라이스 테스트가 함께 진행되어야 하며, 테스트 결과가 'passed'일 경우에 API 문서가 생성된다.

<br>

<image src = "../src/Spring_Rest_Docs_Flow.png" height = "500">

▲ _Spring Rest Docs의 API 문서 생성 흐름_

<br>

**1. 테스트 코드 작성**

- 슬라이스 테스트 코드 작성

- API 스펙 정보 코드 작성 (Request Body, Response Body, Query Parameter 등)

<br>

**2. test 태스크 실행**

- 작성된 슬라이스 테스트 코드 실행 : test task를 실행하여 API 문서 스니핏을 일괄 생성

- 테스트 실행 결과가 'failed'일 경우 테스트 케이스 수정

<br>

**3. API 문서 스니핏(.adoc 파일) 생성**

- 테스트에 성공하면 API 스펙 정보 코드를 기반으로 API 문서 스니핏인 ```.aodc``` 파일 자동 생성

> **💡 스니핏(Snippet)**
>
> 코드 또는 문서의 일부 조각을 의미한다.  
> 
> 테스트 케이스 하나 당 하나의 스니핏이 생성되며, 여러개의 스니핏이 모여 하나의 API 문서가 완성된다.

<br>

**4. API 문서 생성**

- 생성된 API 문서 스니핏을 모아 API 문서를 생성한다.

<br>

**5. API 문서를 HTML로 변환**

- 생성된 API 문서를 HTML 파일로 변환한다.

<br><br>

### 🔸 Spring Rest Docs 설정

<br>

**1. build.gradle 설정**

```java
plugins {
    id "org.asciidoctor.jvm.convert" version "3.3.2"
}
```

- ```.adoc``` 확장자를 가지는 AsciiDoc 문서를 생성해주는 AsciiDoctor를 사용하기 위한 플러그인 추가

<br>

```java
ext {
    set('snippetsDir', file("build/generated-snippets"))
}
```

- ```ext``` 변수의 ```set()``` 메서드를 이용해 API 문서 스니핏이 생성될 **경로 지정**

<br>

```java
configurations {
    asciidoctorExtensions
}
```

- AsciiDoctor에서 사용되는 의존 그룹을 지정
- ```:asciidoctor``` task가 실행되면 내부적으로 해당 그룹을 지정

<br>

```java
dependencies {
    testImplementation 'org.springframework.restdocs:spring-restdocs-mockMvc'
    asciidoctorExtensions 'org.springframework.restdocs:spring-restdocs-asciidoctor'
}
```

- 의존 라이브러리 추가

<br>

```java
tasks.named('test') {
	outputs.dir snippetsDir
	useJUnitPlatform()
}
```

- ```:test``` task 실행 시, API 문서 생성 스니핏 디렉토리 경로 설정

<br>

```java
tasks.named('asciidoctor') {
	configurations "asciidoctorExtensions"
	inputs.dir snippetsDir
	dependsOn test
}
```

- ```:asciidoctor``` task 실행 시, AsciiDoctor 기능을 사용하기 위해 task에 asciidoctorExtensions 설정

<br>

```java
task copyDocument(type: Copy) {
	dependsOn asciidoctor
	println "asciidoctor output: ${asciidoctor.outputDir}"
	from file("build/docs/asciidoc/")
	into file("src/main/resources/static/docs")
}
```

- ```:build``` task 전에 실행되는 ```:copyDocument``` task
  
- ```dependsOn``` : asciidoctor task가 실행 된 후에 해당 task가 실행되도록 의존성 설정
  
- ```from file()``` : 해당 경로에 생성되는 index.html을 복사

- ```into file()``` : 해당 경로에 index.html 파일을 붙여넣기 (외부에 제공하기 위한 용도)

<br>

```java
build {
	dependsOn copyDocument
}
```

- copyDocument task가 실행 된 후에 build task가 실행되도록 의존성 설정

<br>

```java
bootJar {
	enabled = true
	dependsOn copyDocument
	from ("${asciidoctor.outputDir}") {
		into 'static/docs'
	}
}
```

- ```:bootJar``` task는 어플리케이션 실행 파일을 생성한다.

- ```from () { into }``` : index.html 파일을 jar 파일 안에 추가한다. 
  - ```http:/localhost:8080/docs/index.html```을 통해 API 문서를 확인할 수 있도록 설정

<br>

**2. 템플릿 API 문서 생성**

Gradle 기반 프로젝트에서는 ```src/docs/asciidoc/``` 하위에 ```index.adoc```을 생성하여  

API 문서 스니핏을 사용해서 최종 API 문서로 만들 수 있다.

<br><br>

### 🔸 Spring Rest Docs 적용

<br>

기본적으로 Spring Rest Docs는 테스트 기반의 API 문서화 방식이므로,  

API 문서 생성을 위한 슬라이스 테스트 케이스를 작성해야한다.

<br>

```java
@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void postMemberTest() throws Exception {
        // given
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com", "홍길동", "010-1234-5678");
        String content = gson.toJson(post);

        MemberDto.response responseDto =
                new MemberDto.response(1L,
                        "hgd@gmail.com",
                        "홍길동",
                        "010-1234-5678",
                        Member.MemberStatus.MEMBER_ACTIVE,
                        new Stamp());

        given(mapper.memberPostToMember(Mockito.any(MemberDto.Post.class)))
                                .willReturn(new Member());

        given(memberService.createMember(Mockito.any(Member.class)))
                                .willReturn(new Member());

        given(mapper.memberToMemberResponse(Mockito.any(Member.class)))
                                .willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/v11/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                );

        // then
        actions
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.email").value(post.getEmail()))
            .andExpect(jsonPath("$.data.name").value(post.getName()))
            .andExpect(jsonPath("$.data.phone").value(post.getPhone()))
            .andDo(document( 
                "post-member",   
                getRequestPreProcessor(),  
                getResponsePreProcessor(),   
                requestFields(       
                    fieldWithPath("email").type(JsonFieldType.STRING)
                    .description("이메일"),
                    fieldWithPath("name").type(JsonFieldType.STRING)
                    .description("이름"),
                    fieldWithPath("phone").type(JsonFieldType.STRING)
                    .description("휴대폰 번호")
                ),
                responseFields(   
                    List.of(
                        fieldWithPath("data").type(JsonFieldType.OBJECT)
                        .description("결과 데이터"),
                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER)
                        .description("회원 식별자"),
                        fieldWithPath("data.email").type(JsonFieldType.STRING)
                        .description("이메일"),
                        fieldWithPath("data.name").type(JsonFieldType.STRING)
                        .description("이름"),
                        fieldWithPath("data.phone").type(JsonFieldType.STRING)
                        .description("휴대폰 번호"),
                        fieldWithPath("data.memberStatus").type(JsonFieldType.STRING)
                        .description("회원 상태"),
                        fieldWithPath("data.stamp").type(JsonFieldType.NUMBER)
                        .description("스탬프 갯수")
                    )
                )
            ));
    }
}
```

<br>

```@AutoConfigureRestDocs``` : Spring Rest Docs 자동 구성을 위한 어노테이션

```@WebMvcTest``` : Controller 테스트를 위한 어노테이션. 괄호 안에는 대상 테스트 클래스를 지정

```@MockBean``` : JPA에서 사용하는 Bean들을 Mock 객체로 주입해주는 어노테이션

> 메인 실행파일에 ```@EnableJpaAudting```이 포함되어 JPA 관련 Bean을 필요로 하기 때문에,  
>  ```@WebMvcTest```를 사용해 테스트를 진행할 때는  
> JpaMetamodelMappingContext.class를 Mock 객체로 주입해주어야 한다.

<br>

```post, patch, get, delete```

> import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;  

기존 슬라이스 테스트에서 위 경로의 HTTP Request 메서드를 호출했지만,

Rest Docs에서는 아래의 HTTP Request 메서드를 호출한다.

> import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;

<br>

```andDo()``` : andExpect처럼 검증 작업을 하는 것이 아닌 일반적인 동작을 정의

```document()``` : API 문서를 생성하기 위해 Spring Rest Docs에서 지원하는 메서드  

- 실질적인 문서화 작업을 수행하는 ```RestDocumentationResultHandler``` 클래스에서 가장 핵심 기능을 한다.

- ```"post-member"``` : 첫번째 파라미터는 API 문서 스니핏의 식별자 역할을 한다.  
  (post-member 하위 디렉토리에 문서 스니핏이 생성된다.)

- ```getRequestPreProcesser()``` , ```getResponsePreProcessor()``` :   
  스니핏을 생성하기 전에 request와 response에 해당하는 문서 영역을 전처리하는 역할을 한다.

  ```java
  public interface ApiDocumentUtils {
     static OperationRequestPreprocessor getRequestPreProcessor() {
         return preprocessRequest(prettyPrint());   // JSON 포맷을 예쁘게 표현
      }

      static OperationResponsePreprocessor getResponsePreProcessor() {
          return preprocessResponse(prettyPrint());
      }  
  }
  ```
   &nbsp; ▲ _해당 기능 공통화하여 모든 테스트 케이스에서 재사용 가능_

<br>

- ```requestfield()```, ```responsefield()``` :   
  문서로 표현될 Request Body, Response Body를 의미한다.  
  ```List<FieldDescriptor>```를 인자로 받으며, List로 묶어주지 않아도 자동으로 List형으로 변환한다.

  > List형으로 묶기 위해서는 ```List.of()``` 또는 ```Arrays.asList()```를 사용한다.

- ```fieldWithPath()``` : JSON 포맷으로 변환했을 때 하나의 프로퍼티를 의미하는 FieldDescriptor
  - ```.type(JsonFieldType.STRING)```과 같이 지정하여 프로퍼티 값의 타입을 지정하고,  
      ```.description()```을 통해 해당 프로퍼티에 대한 설명을 추가할 수 있다.  

      또한 patch를 사용하는 경우 ```.ignored()```를 추가하여 API 스펙 정보에서 제외할 수 있고,  
      ```.optional()```을 사용하여 필수가 아닌 선택 정보로 설정할 수 있다.

<br>

위 코드 외에도 URL의 Path Variable 정보나 Query Parameter 정보처럼  
request body 내에 매핑되지 않는 정보를 API 문서에 명시할 수도 있다.

```java
//  /v11/members/{member-id}
pathParameters(
    parameterWithName("member-id").description("회원 식별자")
)
```
▲ _요청 URL에 Path Variable을 받는 경우_

<br>

```java
//  /v11/members?page={}&size={}
requestParameters(
    parameterWithName("page").description("페이지 번호"),
    parameterWithName("size").description("페이지당 출력")
)
```

▲ _요청 URI에 Query Parameter를 받는 경우_

<br>

또한, 중복되는 request body 인자가 많을 경우 공통화하여 재사용성을 높일 수 있다.

```java
public static FieldDescriptor[] memberResponseFields = new FieldDescriptor[] {

    fieldWithPath("memberId").type(JsonFieldType.NUMBER)
            .description("회원 식별자"),
    fieldWithPath("email").type(JsonFieldType.STRING)
            .description("이메일"),
    fieldWithPath("name").type(JsonFieldType.STRING)
            .description("이름"),
    fieldWithPath("phone").type(JsonFieldType.STRING)
            .description("휴대폰 번호"),
    fieldWithPath("memberStatus").type(JsonFieldType.STRING)
            .description("회원 상태: 활동중 / 휴면 상태 / 탈퇴 상태"),
    fieldWithPath("stamp").type(JsonFieldType.NUMBER)
            .description("스탬프 갯수")
};
```

▲ _재사용성을 높이기 위한 공통화 메서드_

<br>

```java
responseFields(
    fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터")
).andWithPrefix("data.", memberResponseFields)
```

▲ _andWithPrefix를 이용한 responseField 구성_

<br>

```andWithPrefix```를 사용하면 ```fieldWithPath("data.memberId")...```처럼 작성한 것과 동일한 결과를 리턴하여 기존 코드와 같은 결과를 낼 수 있다.

<br><br>

### 🔸 스니핏을 이용한 API 문서화

Gradle 프로젝트는 템플릿 문서가 위치하는 Default 경로가 ```src/docs/asciidoc```이므로  

해당 폴더 하위에 ```index.adoc``` 파일을 생성하여 스니핏을 이용한 API 문서를 작성한다.

<br>

```adoc
= 커피 주문 어플리케이션
:sectnums:
:toc: left                
:toclevels: 4
:toc-title: Table of Contents
:source-highlighter: prettify

Hong Jeong Wan <hongjjwan@gmail.com>

v1.0.0, 2022.11.16.

***

== MemberController
=== 회원 등록
.curl-request
include::{snippets}/post-member/curl-request.adoc[]     

.http-request
include::{snippets}/post-member/http-request.adoc[]

.request-fields
include::{snippets}/post-member/request-fields.adoc[]

.http-response
include::{snippets}/post-member/http-response.adoc[]

.response-fields
include::{snippets}/post-member/response-fields.adoc[]

...
```
▲ _Asciidoc 문법으로 작성된 템플릿 문서_

<br>

```=```, ```==```, ```===``` : 마크다운에서 ```#```, ```##```, ```###```과 같은 의미로 제목을 나타낸다.  

```:sectnums:``` : 목차의 각 섹션에 넘버링을 작성한다.

```:toc: left``` : left로 지정되었으므로, 목차를 문서의 좌측에 위치한다.

```:toclevels: 4``` : 4로 지정되었으므로, 최대 ```====```로 지정된 제목까지만 목차에 표시한다.

```:toc-title:``` : 목차의 제목을 지정한다.

```:source-highlighter:``` : 문서에 표시되는 소스 코드 하이라이터를 지정한다.

```.curl-request``` : ```.```은 하나의 스니핏 섹션 제목을 표현하기 위해 사용한다.

```include::``` : include는 Asciidocter의 매크로 중 하나이며, ```::```는 매크로를 사용하기 위한 표기법이다.

```{snippets}``` : 해당 스니핏이 생성되는 디폴트 경로를 의미하며, build.gradle에서 ext 설정을 통해 변경할 수 있다.

```include::{snippets}/스니핏 문서의 디렉토리/스니핏 문서 파일명.adoc[]``` : 해당 경로의 스니핏을 사용한다.

<br>

### 📋 [***Asciidoc 기본 사용법***](https://narusas.github.io/2018/03/21/Asciidoc-basic.html)

<br><br>

### 🔸 API 문서 확인

<br>

**템플릿 문서를 HTML 파일로 변환**

- Gradle 탭의 ```:bootJar``` 또는 ```:build``` task 명령을 실행하면,  

  ```src/main/resources/static/docs``` 경로에 ```index.html``` 파일이 생성된다.

<br>

**웹 브라우저를 통한 템플릿 문서 확인**

- ```src/main/resources/static/docs``` 경로에 ```index.html``` 파일이 생성되면,  

  IntelliJ에서 어플리케이션을 실행한 후 ```http://localhost:8080/docs/index.html```를 통해 확인할 수 있다.

<br><br>

### 🔸 API 문서 커스터마이징

<br>

.snippet 파일을 변경하여 테이블 이름이나 내부 값에 대한 Custom이 가능하다.

```adoc
// request-fields.snippet

|===
|필드명|타입|필수값 여부|설명|제약 조건

{{#fields}}

|{{#tableCellContent}}`+{{path}}+`{{/tableCellContent}}
|{{#tableCellContent}}`+{{type}}+`{{/tableCellContent}}
|{{#tableCellContent}}{{^optional}}true{{/optional}}{{/tableCellContent}}
|{{#tableCellContent}}{{description}}{{/tableCellContent}}
|{{#tableCellContent}}{{#constraints}}{{.}} + 
{{/constraints}}{{/tableCellContent}}

{{/fields}}
|===
```

<br>

***

<br>

## Swagger

Swagger는 어노테이션 기반의 API 문서화 방식이다.

<br>

```java
@ApiOperation(value = "회원 정보 API", tags = {"Member Controller"})
@RestController
@RequestMapping("/v11/swagger/members")
@Validated
@Slf4j
public class MemberControllerSwaggerExample {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberControllerSwaggerExample(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "회원 정보 등록", notes = "회원 정보 등록")

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "회원 등록 완료"),
            @ApiResponse(code = 404, message = "Member not found")
    })
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post memberDto) {
        Member member = mapper.memberPostToMember(memberDto);
        member.setStamp(new Stamp()); // homework solution 추가

        Member createdMember = memberService.createMember(member);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponse(createdMember)),
                HttpStatus.CREATED);
    }

    ...

    @ApiOperation(value = "회원 정보 조회", notes = "회원 식별자(memberId)에 해당하는 회원을 조회")
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @ApiParam(name = "member-id", value = "회원 식별자", example = "1")
            @PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponse(member))
                                    , HttpStatus.OK);
    }

    ...
}
```

<br>

```@ApiOperation```, ```@ApiResponses``` 어노테이션 등을 통해 API 문서를 생성하는 방식으로,  

기능 구현과 관련 없는 어노테이션이 추가되어 기능 구현 코드가 눈에 잘 들어오지 않게 된다.

> Controller뿐만 아니라 Request Body, Response Body 같은 DTO 클래스도 어노테이션을 적용해야 한다.

<br>

Swagger 기반의 API 문서는 Postman에서 HTTP 요청을 전송하듯 API 요청 툴로의 기능을 사용할 수 있다는 장점이 있다.

<br><br>

***

_2022.11.16. Update_

_2022.11.15. Update_