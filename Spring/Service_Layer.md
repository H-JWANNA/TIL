# 서비스 계층

비즈니스 계층이라고도 부르며, 웹 어플리케이션의 비즈니스 로직을 처리하는 핵심 계층이다.

트랜젝션을 관리 및 Presentation Layer와 Data Access Layer 연결의 역할을 하며,  
Service 인터페이스와 ```@Service``` 어노테이션을 사용하여 작성된 Service 구현 클래스가 Service Layer에 속한다.

<br>

## 🛠 Service Layer 구현

아래 작성된 내용은 Presentation Layer와 Service Layer가 연결되는 과정이다.

<br>

### 🔸 Entity 클래스 작성

DTO가 Presentation Layer에서 클라이언트의 Request Body를 전달받고 되돌려줄 응답 데이터를 담는 역할이라면,  

Entity 클래스는 Presentation Layer에서 전달받은 요청 데이터를 기반으로  

**Service Layer에서 비즈니스 로직을 처리하기 위해 필요한 데이터를 전달 받고,**  

**비즈니스 로직을 처리한 후에 결과 값을 다시 Presentation Layer로 리턴해주는 역할**을 한다.

<br>

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
```
▲ _Entity Class_

위 코드에 사용된 lombok 어노테이션은 다음과 같다.

- ```@Getter```, ```@Setter``` : 클래스 내의 각 멤버 변수에 해당하는 getter, setter 메서드를 생성해준다.
  
- ```@NoArgsConstructor``` : 파라미터가 없는 기본 생성자를 자동으로 생성해준다.
  
- ```@AllArgsConstructor``` : 모든 멤버 변수를 파라미터로 가지는 생성자를 자동으로 생성해준다.

<br>

### 🔸 Mapper를 통한 DTO와 Entity 클래스 매핑

Mapper 클래스를 사용하여 DTO 클래스와 Entity 클래스의 역할 분리를 하는 이유는 다음과 같다.

- **계층별 관심사의 분리**  
  하나의 클래스나 메서드 내에서 여거 기능을 구현하는 것은 객체 지향 코드 관점에서 좋지 않다.

- **코드 구성의 단순화**  
  여러 기능이 섞이게 된다면 코드가 복잡해지며 유지보수가 어렵게 된다.

- **REST API 스펙의 독립성 확보**  
  패스워드와 같은 원하지 않는 데이터를 제외하고 전송할 수 있다.  
  API 문서로써의 역할이 가능해 유지보수에 장점이 있다.

<br>

Mapper가 하는 역할은 **전달받은 DTO 클래스 → Entity 클래스** / **Entity 클래스 → 응답 DTO 클래스**이다.

<br>

**💡 MapStruct를 활용한 Mapper 자동 생성**

```java
dependencies {
	implementation 'org.mapstruct:mapstruct:1.5.3.Final'
	annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.3.Final'
}
```
▲ _build.gradle에 의존 라이브러리 추가_

<br>

```java
@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
}
```
▲ _MapStruct 기반의 Mapper 인터페이스 정의_

<br>

- ```@Mapper``` : MapStruct의 Mapper Interface를 자동 생성해준다.  

> 빌드 후, build/classes/java/main/package-path 하위에 생긴  
> ```MapperImpl``` 파일을 통해 생성된 Mapper 인터페이스를 확인할 수 있다.
> 
> ```@Mapper```의 Attribute로 ```componentModel = "spring"```을 지정해주면 Spring Bean으로 등록된다.

<br>

> **💡 MapStruct가 매핑을 정상적으로 하기 위한 조건의 우선 순위**
>
> 1. ```@Builder``` 패턴이 적용되어 있는 경우
> 2. ```@AllArgsConstructor```가 있는 경우
>       - 단, 기본 생성자가 포함되어 있을 경우, 2의 생성자는 제 역할을 못한다.
> 3. ```@Setter```가 있는 경우

<br>

### 🔸 Service 클래스 작성

Service 클래스는 ```@Service```를 통해 Spring Bean으로 등록되며,  
Service 클래스의 메서드는 Controller 클래스의 핸들러 메서드와 **1:1 매칭**을 기본으로 한다.

```java
public class MemberService {
    public Member createMember(Member member) {
        // Not Implemented
        return member;
    }

    public Member updateMember(Member member) {
        // Not Implemented
        return member;
    }

    public Member findMember(long memberId) {
        Member member = 
                new Member(memberId, "abc@gmail.com", "김철수", "010-1234-5678");
        return member;
    }

    public List<Member> findMembers() {
        List<Member> members = List.of(
                new Member(1, "abc@gmail.com", "김철수", "010-1234-5678"),
                new Member(2, "poppy@naver.com", "박뽀삐", "010-1111-2222")
        );
        return members;
    }

    public void deleteMember(long memberId) {}
}
```
▲ _MemberServiceStub.java_

<br>

Service 클래스의 메서드는 Controller 클래스의 핸들러 메서드와 1:1 매칭이 된 것을 볼 수 있다.

| Controller || Service |
|:-:|:-:|:-:| 
|postMember|↔︎|createMember|
|patchMember|↔︎|updateMember|
|getMember|↔︎|findMember|
|getMembers|↔︎|findMembers|
|deleteMember|↔︎|deleteMember|

<br>

### 🔸 Controller 클래스에 적용

Spring의 DI를 이용해 Service 클래스와 Mapper 클래스를 Controller 클래스와 결합한다.

<span style = "color : gray"> _(아래 요약 있음)_</span>

```java
@RestController
@RequestMapping("/v1/members")
@Validated
public class MemberController {

    // DI
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, 
                            MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {

        // Request Body를 전달받은 Dto 클래스 → Entity 클래스
        Member member = mapper.memberPostDtoToMember(memberDto);

        // Entity 클래스를 통해 Service 클래스의 로직 호출
        Member response = memberService.createMember(member);

        // Entity 클래스 → 응답 Dto 클래스
        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(response);

        // 응답 Dto 클래스 반환
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(...) {
        ...
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {

        // HTTP GET Method는 Body를 가지지 않기 때문에 전달받은 Dto 클래스가 없다.
        // 따라서, 전달받은 member-id를 기준으로 Service 클래스의 로직 호출
        Member response = memberService.findMember(memberId);

        // Entity 클래스 → 응답 Dto 클래스
        MemberResponseDto responseDto = mapper.memberToMemberResponseDto(response);

        // 응답 Dto 클래스 반환
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        ...
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(...) {
        ...
    }
}
```
▲ _생성자 주입을 통한 DI_

<br>

### **💡 Presentation Layer ↔︎ Service Layer 요약**

1. 클라이언트의 요청을 DTO가 전달 받는다.

<br>

2. Mapper를 통해 DTO → Entity 변환하여 Service 클래스에서 사용하게끔 만든다.

```java
Member member = mapper.memberPostDtoToMember(memberDto);
```

<br>

3. Service 클래스에서 비즈니스 로직을 처리한다.

```java
Member response = memberService.createMember(member);
```

<br>

4. 비즈니스 로직 처리가 끝난 Entity → 응답 DTO 클래스로 변환한다.

```java
MemberResponseDto responseDto = mapper.memberToMemberResponseDto(response);
```

<br>

5. 클라이언트에게 응답 데이터를 전달한다.

```java
return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
```

<br><br>

***

_2022.10.24. Update_