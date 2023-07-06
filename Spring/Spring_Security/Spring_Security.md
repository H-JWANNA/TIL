# Spring Security

Spring Security는 Spring MVC 기반 어플리케이션의 인증(Authentication)과 권한 부여(Authorization) 기능을 지원하는 보안 프레임워크로써,  
Spring MVC 기반 어플리케이션에 보안을 적용하기 위한 표준이다.

<br>

**💡 Spring Securiy로 할 수 있는 보안 강화 기능**

- 다양한 유형(폼 로그인 인증, 토큰 기반 인증, OAuth2 기반 인증, LDAP 인증)의 사용자 인증 기능 적용

- 어플리케이션 사용자의 역할(Role)에 따른 권한 레벨 적용

- 어플리케이션에서 제공하는 리소스에 대한 접근 제어

- 민감한 정보에 대한 데이터 암호화

- SSL 적용

- 일반적으로 알려진 웹 보안 공격 차단

- SSO, 클라이언트 인증서 기반 인증, 메서드 보안, 접근 제어 목록(Access Control List) 등

<br>

**💡 Spring Securiy에서 사용하는 용어**

- **Principal (주체)**  
  어플리케이션에서 작업을 수행할 수 있는 사용자, 디바이스 또는 시스템을 의미하며,  
  일반적으로는 인증 프로세스가 성공적으로 수행된 사용자의 계정 정보를 의미한다.

- **Authentication (인증)**  
  어플리케이션을 사용하는 사용자가 본인이 맞음을 증명하는 절차를 의미한다.
  
- **Cridential (신원 증명 정보)**  
  Authentication을 정상적으로 수행하기 위해 필요한 사용자 식별 정보  
  주로 특정 사이트에서 로그인을 위해 입력하는 패스워드를 의미한다.

- **Authorization (인가, 권한 부여)**  
  인증이 정상적으로 수행된 사용자에게 하나 이상의 권한을 부여하여 특정 어플리케이션의 특정 리소스에 접근할 수 있게 허가하는 과정  
  반드시 Authentication 과정 이후 수행되어야 하며, 권한은 일반적으로 **역할(Role)** 형태로 부여된다.

- **Access Control (접근 제어)**  
  사용자가 어플리케이션의 리소스에 접근하는 행위를 제어하는 것을 의미한다.

<br>

***

<br>

## Spring Security의 기본 구조

Spring Security의 기본 구조를 이해하기 쉬운 방식은 SSR 방식이다.

> SSR 방식은 세션 기반의 폼 로그인 방식을 적용하기 가장 적합한 어플리케이션이며,  
> 
> 폼 로그인 방식은 Spring Security를 이해하기 가장 적합한 인증 방식이다.

<br>

### SSR 방식 어플리케이션에 Spring Security 적용 (InMemory)

<br>

**1. Spring Security를 사용하기 위해 의존 라이브러리를 추가**

<br>

```java
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-security'

	implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
	implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.1.0'
}
```

SSR 방식의 어플리케이션은 클라이언트에게 전송하는 HTML 코드까지 포함하고 있으며,  
HTML 뷰를 구성하기 위해 타임리프(Thymeleaf)라는 템플릿 엔진을 사용한다.

<br><br>

**2. Spring Security Configuration 적용**

- 원하는 인증 방식과 웹 페이지에 대한 접근 권한을 설정할 수 있다.

<br>

```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailManager userDetailsService() {
        UserDetails userDetails =
            User.withDefaultPasswordEncoder()
                .username("hongjjwan@gmail.com")
                .password("1111")
                .roles("USER")
                .build();
    }

    return new InMemoryUserDetailsManager(userDetails);
}
```
▲ _InMemory User를 통해 인증_

<br>

- ```UserDetailsManager```  
  Spring Security에서 제공하는 UserDetails 관리 인터페이스

- ```UserDetails```  
  인증된 사용자의 핵심 정보를 포함한 인터페이스  
  ```User``` 클래스를 이용해서 사용자 인증 정보를 생성한다.

- ```withDefaultPasswordEncoder()```  
  디폴트 패스워드 인코더를 이용해 사용자 패스워드를 암호화

- ```username()```  
  고유한 사용자를 식별할 수 있는 값 (이름 X)

- ```password()```  
  사용자의 패스워드를 설정

- ```roles()```  
  사용자의 역할(권한)을 지정

<br><br>

**3. HTTP 보안을 구성한 커스텀 로그인 페이지 지정**

- Spring Security를 이용한 보안 설정은 ```HttpSecurity```를 파라미터로 가지고,  
  ```SecurityFilterChain```을 리턴하는 Bean을 생성하면 된다.

<br>

```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .formLogin()
            .loginPage("/auths/login-form")
            .loginProcessingUrl("/process_login")
            .failureUrl("/auths/login-form?error")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .and()
            .authorizeHttpRequests()
            .anyRequest()
            .permitAll();
        
        return http.build();
    }
    ...
}
```

- ```csrf().disable()```  
  CSRF 공격에 대한 Spring Security 설정 비활성화  
  기본적으로 아무 설정도 하지 않으면 CSRF 공격을 방지한다.

- ```formLogin()```  
  기본적인 인증 방법을 폼 로그인 방식으로 지정

- ```loginPage("/auths/login-form")```  
  커스텀 로그인 페이지를 사용  
  파라미터는 AuthController의 ```loginForm()``` 핸들러 메서드에 요청을 전송하는 요청 URL이다.

- ```longinProcessingUrl("/process_login")```  
  로그인 인증 요청을 수행할 요청 URL을 지정  
  해당 URL은 HTML 파일 form 태그의 action 속성에 지정한 값과 같다.

  ```html
  <form action="/process_login" method="post">
  ```

- ```failureUrl("/auths/login-form?error")```  
  로그인 인증에 실패할 경우, 리다이렉트 할 화면을 지정  
  HTML 태그의 ```${param.error}``` 값을 통해 로그인 인증 실패 메시지 표시 여부를 결정

  ```html
  <div class="row alert alert-danger center" role="alert" th:if="${param.error != null}">
    <div>로그인 인증에 실패했습니다.</div>
  </div>
  ```

- ```and()```  
  Spring Security 보안 설정을 메서드 체인 형태로 구성

- ```logout()```  
  로그아웃 설정을 위한 ```LogoutConfigurer```를 리턴

- ```logoutUrl("/logout")```  
  사용자가 로그아웃을 수행하기 위한 request URL 지정  
  해당 URL은 HTML 파일의 href 속성에 지정한 값과 같다.

  ```html
  <a href="/logout" class="text-decoration-none">로그아웃</a>
  ```

- ```logoutSuccessUrl("/")```  
  로그아웃이 완료되면 리다이렉트 할 URL 지정

- ```authorizeHttpRequests()```  
  클라이언트 요청이 들어오면 접근 권한 확인

- ```anyRequest().permitAll()```  
  클라이언트 모든 요청에 대해 접근 허용

<br><br>

**4. Request URI에 접근 권한 부여**

<br>

```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .formLogin()
            .loginPage("/auths/login-form")
            .loginProcessingUrl("/process_login")
            .failureUrl("/auths/login-form?error")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .and()
            .exceptionHandling()
            .accessDeniedPage("/auths/access-denied")
            .and()
            .authorizeHttpRequests(
                authorize -> authorize
                    .antMatchers("/orders/**").hasRole("ADMIN")
                    .antMatchers("/members/my-page").hasRole("USER")
                    .antMatchers("/**").permitAll()
            );
        
        return http.build();
    }
    ...
}
```

- ```exceptionHandling().accessDeniedPage("/auths/access-denied")```
  권한이 없는 사용자가 특정 Request URI에 접근할 경우 발생하는 **403(Forbidden)** 에러를 처리하기 위한 페이지 설정

- ```athorizeHttpRequest(...)```
  람다 표현식을 통해 Request URI에 대한 접근 권한 부여
  - ```antMatchers("/orders/**").hasRole("ADMIN")```  
    **ADMIN** 역할을 부여 받은 사용자만 ```/orders```로 시작하는 모든 URL에 접근할 수 있다는 의미

<br>

  > ```/orders/*```을 지정했다면 ```/orders/1```과 같이 하위 URL의 depth가 1인 URL만 포함  
  > 
  > ```/orders/**```을 지정했다면 ```/orders/1```이나 ```/orders/1/coffees/1```과 같이 모든 하위 URL을 포함

<br>

  > **💡 antMathcers()를 이용한 접근 권한 부여 시 주의사항**
  >
  > ```antMatchers()```는 ant라는 빌드 툴에서 사용되는 Path Pattern을 이용해서 매치되는 URL을 표현한다.
  >
  > 더 구체적인 URL 경로부터 접근 권한을 부여한 다음  
  > 덜 구체적인 URL 경로에 접근 권한을 부여해야한다.
  >  
  > ```permitAll()```을 가장 앞에 부여할 경우,  
  > 뒤에 오는 ```hasRole()```에 부여된 접근 권한은 제 기능을 하지 못하게 된다.

<br>

***

<br>

### SSR 방식 어플리케이션에 Spring Security 적용 (DB)

<br>

Spring Security에서는 User의 인증 정보를 테이블에 저장하고,   
테이블에 저장된 인증 정보를 이용해 인증 프로세스를 진행할 수 있는 몇 가지 방법이 존재하는데   

그 중 한 가지 방법이 바로 **Custom UserDetailsService**를 이용하는 방법이다.

<br>

**1. SecurityConfiguration 설정 변경 및 PasswordEncoder Bean 등록**

<br>

```java
@Configuration
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().sameOrigin()
            .and()
            .csrf().disable()
            ...
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
```

- ```frameOptions().sameOrigin()```  
  동일 출처로부터 들어오는 Request 페이지만 렌더링을 허용  

  > ```frameOptions()```는 HTML 태그 중에서 ```<frame>```, ```<iframe>```, ```<object>``` 태그에서 페이지 렌더링 여부를 결정  
  >
  > 기본적으로 Clickjacking 공격을 막기 위해 활성화 되어 있으며, 기본값은 ```DENY```이다.  
  >  
  > 위에서 언급한 HTML 태그를 이용한 페이지 렌더링을 허용하지 않겠다는 의미이다.

<br>

- ```DelegatingPasswordEncoder```  
  PasswordEncoder 구현 객체를 생성해준다.

  > 인메모리 User는 내부적으로 디폴트 PasswordEncoder를 통해 암호화 된다.

<br><br>

**2. DBMemberService 구현**

<br>

```java
@Transactional
public class DBMemberService implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public DBMemberService(MemberRepository memberRepository,
                           PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        Member savedMember = memberRepository.save(member);

        return savedMember;
    }
}
```

- ```passwordEncoder.encode()```  
  패스워드를 암호화 하는 메서드  
  암호화 한 패스워드를 ```setPassword()```를 통해서 다시 할당한다.

  > Password와 같이 민감한 정보는 반드시 암호화 되어 저장되어야 하며,  
  >  
  > 패스워드는 암호화 된 상태에서 복호화 할 필요가 없기 때문에 **단방향 암호화 방식**으로 암호화한다.

<br><br>

**3. MemberService Bean 등록을 위한 JavaConfiguration 구성**

<br>

```java
@Configuration
public class JavaConfiguration {

    @Bean
    public MemberService dbMemberService(MemberRepository memberRepository,
                                         PasswordEncoder passwordEncoder) {
        return new DBMemberService(memberRepository, passwordEncoder);               
    }
}
```

<br><br>

**4. Custom UserDetails 및 Custom UserDetailsService 구현**

Custom UserDetailsService를 통해 **DB에서 조회한 User의 인증 정보를 기반으로 인증을 처리**한다.

<br>

```java
@Component
public class HelloAuthorityUtils {

    @Value("${mail.address.admin}")
    private String adminMailAddress;

    private final List<GrantedAuthority> ADMIN_ROLES = 
            AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    private final List<GrantedAuthority> USER_ROLES =
            AuthorityUtils.createAuthorityList("ROLE_USER");
    
    public List<GrantedAuthority> createAuthorities(String email) {

        if (email.equals(adminMailAddress)) return ADMIN_ROLES;

        return USER_ROLES;
    }
}
```
▲ _HelloAuthorityUtils_

<br>

- ```@Value("${경로}")```  
  application.yml에 추가한 프로퍼티를 가져오는 표현식

  ```yml
  mail:
    address:
        admin: admin@gmail.com
  ```

- ```AuthorityUtils.createAuthorityList(...)```   
  Spring Security에서 지원하는 AuthorityUtils 클래스를 이용해 관리자용 또는 일반 사용자용 권한 목록을 생성한다.

<br>

```java
@Component
public class HelloUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final HelloAuthorityUtils authorityUtils;

    public HelloUserDetailsService(...) {
        // 생성자
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findByEmail(username);

        Member findMember = optionalMember.orElseThrow(
            () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return new HelloUserDetails(findMember);
    }

    private final class HelloUserDetails extends Member implements UserDetails {

        HelloUserDetails(Member member) {
            setMemberId(member.getMemberId());
            setFullName(member.getFullName());
            setEmail(member.getEmail());
            setPassword(member.getPassword());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getEmail());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
```
▲ _Custom UserDetailsService_

<br>

- ```UserDetailsService```  
  Custom UserDetailsService를 구현하기 위한 인터페이스

- ```UserDetails```  
  UserDetailsService에 의해 로드되어 인증을 위해 사용되는 핵심 User 정보를 표현하는 인터페이스  

  > 해당 인터페이스의 구현체는 직접 사용되지는 않고, Authentication 객체로 캡슐화되어 제공된다.

- ```loadUserByUsername()```  
  UserDetailsService 인터페이스를 구현하는 클래스는 해당 추상 메서드를 구현해야한다.

- ```Collection<? extends GrantedAuthority>```  
  authorityUtils를 통해 DB에서 조회한 회원의 이메일 정보를 이용하여 Role 기반의 권한 정보(GrantedAuthority) 컬렉션을 생성한다. 

- ```HelloUserDetails```  
  UserDetails 인터페이스를 구현하고, Member Entity 클래스를 상속한다.  

  이렇게 구성하면 **DB에서 조회한 회원 정보를 Spring Security의 User 정보로 변환하는 과정**과  
  **User의 권한 정보를 생성하는 과정을 캡슐화** 할 수 있다.

  또한, Member Entity 클래스를 상속하고 있기 때문에  
  HelloUserDetails를 리턴받아 사용하는 측에서는 두 클래스의 객체를 모두 손쉽게 캐스팅해서 사용 가능하다는 장점이 있다.

- ```getUsername()```  
  Spring Security에서 Username은 고유한 사용자를 식별할 수 있는 값이기 때문에 **리턴 값은 null일 수 없다.**

<br><br>

**5. DB에서 User의 Role 관리**

<br>

1. **User의 권한 정보를 저장하기 위한 테이블 생성**

JPA를 통해 User와 User의 권한 정보 간의 연관 관계를 맺을 수 있다.

```java
public class Member extends Auditable implements Principal {
    ...

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();
}
```

- ```@ElementCollection```  
  해당 어노테이션을 작성하면, User 권한 정보와 관련된 별도의 Entity 클래스를 생성하지 않아도 간단하게 매핑 처리가 된다.

<br>

2. **회원 가입 시, User의 권한 정보(Role)를 DB에 저장**

```java
@Component
public class HelloAuthorityUtils {
    ...

    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");

    public List<String> createRoles(String email) {

        if(email.equals(adminMailAddress)) return ADMIN_ROLES_STRING;

        return USER_ROLES_STRING;
    }
}
```
▲ _사용자 역할을 String 형식으로 저장하는 코드가 추가된 HelloAuthorityUtils_

<br>

```java
@Transactional
public class DBMemberService implements MemberService {
    ...

    public Member createMember(Member member) {
        ...

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        ...

        return savedMember;
    }
}
```
▲ _회원의 권한 정보를 DB에 저장하는 코드가 추가된 DBMemberService_

<br>

3. **로그인 인증 시, User의 권한 정보를 DB에서 조회하는 작업**

```java
@Component
public class HelloAuthorityUtils {
    ...

    public List<GrantedAuthority> createAuthorities(List<String> roles) {

        List<GrantedAuthority> authorities = role.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());
        
        return authorities
    }
}
```
▲ _DB에서 조회한 ROLE 정보를 기반으로 User의 권한 목록 생성_

<br>

- ```SimpleGrantedAuthority()```  
  해당 객체를 생성할 때, 생성자 파라미터로 넘겨주는 값이 ```USER```나 ```ADMIN```이 아닌 ```ROLE_USER```, ```ROLE_ADMIN``` 형태로 넘겨주어야 한다.

<br>

```java
@Component
public class HelloUserDetailsService implements UserDetailsService {
    ...

    private final class HelloUserDetails extends Member implements UserDetails {
        HelloUserDetails(Member member) {
            setMemberId(member.getMemberId());
            ...
            setRoles(member.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }
    }
}
```
▲ _HelloAuthorityUtils_

<br>

- HelloUserDetails가 상속하고 있는 Member에 DB에서 조회한 ```List<String> roles```를 전달

- Member에 전달한 Role 정보를 ```authorityUtils.createAuthorities()```의 파라미터로 전달해서 권한 목록을 생성

<br>

***

<br>

### Custom AuthenticationProvider

<br>

Custom UserDetailsService를 사용해 로그인 인증을 처리하는 방식은 Spring Security가 내부적으로 인증을 대신 처리해주는 방식이다.

Custom AuthenticationProvider를 사용하면 직접 로그인 인증을 처리할 수 있다.

<br>

```java
@Component
public class HelloUserAuthenticationProvider implements AuthenticationProvider {
    private final MemberService memberService;
    private final HelloAuthorityUtils authorityUtils;
    private final PasswordEncoder passwordEncoder;

    public HelloUserAuthenticationProvider(...) {
        // 생성자
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;

        String username = authToken.getName();
        Optional.ofNullable(username).orElseThrow(
                    () -> new UsernameNotFoundException("Invalid User name or User Password"));

        Member member = memberService.findMember(username);

        String password = member.getPassword();
        verifyCredentials(authToken.getCredentials(), password);

        Collection<? extends GrantedAuthority> authorities = authorityUtils

        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

    private void verifyCredentials(Object credentials, String password) {
        if (!passwordEncoder.matches((String)credentials, password)) {
            throw new BadCredentialsException("Invalid User Name or User Password");
        }
    }
}
```

- Spring Security는 **AuthenticationProvider를 구현한 구현 클래스가 Spring Bean으로 등록되어 있다면**  
  **해당 Custom AuthenticationProvider를 이용해서 인증을 진행**한다.

- ```AuthenticationProvider```  
  authenticate 메서드와 supports 메서드를 구현해야 한다.

- ```supports()```  
  직접 구현한 AuthenticationProvider가 Username/Password 방식의 인증을 지원한다는 것을 Spring Security에게 알려주는 역할  

  해당 값이 true일 경우, authenticate() 메서드를 호출해서 인증을 진행한다.

- ```Authentication authenticate()```   
  해당 구현 메서드를 통해 인증 처리 로직을 직접 작성하여 사용자의 인증 여부를 결정한다.

  - ```UsernamePasswordAuthenticationToken```  
 
    해당 객체에서 사용자의 Username을 얻은 후, 존재하는지 체크한다.  
    ```java
    String username = authToken.getName();
    Optional.ofNullable(username).orElseThrow(...);
    ```  

    <br>

    Username이 존재한다면 DB에서 해당 사용자를 조회한다.  
    ```java
    Member member = memberService.findMember(username);
    ```

    <br>

    로그인 정보에 포함된 패스워드와 DB에 저장된 사용자의 패스워드 정보가 일치하는지 검증한다.
    ```java
    verifyCredentials(authToken.getCredentials(), password);
    ```

    <br>

    검증에 성공하면 사용자 권한을 생성한다.
    ```java
    authorityUtils.createAuthorities(member.getRoles());
    ```

    <br>

    최종적으로 인증된 사용자의 인증 정보를 리턴값으로 전달한다. (해당 인증 정보는 내부적으로 Spring Security에서 관리한다.)
    ```java
    return new UsernamePasswordAuthenticationToken
    ```

<br>

***

<br>

## 접근 제어 표현식

Spring Security에서는 웹 및 메서드 보안을 위해 표현식(Spring EL)을 사용할 수 있다.

<br>

|표현식|설명|
|:-|:-|
| hasRole(String role) | 현재 보안 주체(Principal)가 지정된 역할을 가지고 있다면 true를 리턴한다. <br><br> hasRole("ADMIN")처럼 파라미터로 넘긴 Role이 ```ROLE_```로 시작하지 않으면 기본적으로 추가한다. <br> (DefaultWebSecurityExpressionHanlder의 ```defaultRolePrefix```를 수정하여 커스텀 가능) |
| hasAnyRole(String... roles) | 현재 보안 주체가 지정한 역할 중 1개라도 가지고 있으면 true를 리턴한다. <br> (문자열 리스트를 콤마(,)로 구분해서 전달)  <br><br> ex) hasAnyRole("ADMIN", "USER") | 
| hasAuthority(String authority) | 현재 보안 주체가 지정한 권한을 가지고 있다면 true를 리턴한다.<br><br> ex) hasAuthority("Read") |
| hasAnyAuthority(String... authorities) | 현재 보안 주체가 지정한 권한 중 1개라도 가지고 있으면 true를 리턴한다. <br><br>ex) hasAnyAuthority("Read", "Write") |
| principal | 현재 사용자를 나타내는 principal 객체에 직접 접근할 수 있다. |
| authentication | SecurityContext로 조회할 수 있는 현재 Authentication 객체에 직접 접근할 수 있다. |
| permitAll | 항상 true로 평가한다. |
| denyAll | 항상 false로 평가한다. |
| isAnonymous() | 현재 보안 주체가 익명 사용자라면 true를 리턴한다. |
| isRememberMe() | 현재 보안 주체가 remember-me 사용자면 true를 리턴한다. |
| isAuthenticated() | 사용자가 익명이 아닌 경우 true를 리턴한다. |
| isFullyAuthenticated() | 사용자가 익명 사용자나 remember-me 사용자가 아니라면 true를 리턴한다. |
| hasPermission(Object target, Object permission) | 사용자가 target에 해당 permission 권한이 있으면 true를 리턴한다. <br><br> ex) hasPermission(domainObject, "Read") |
| hasPermission(Object targetId, String targetType, Object permission) | 사용자가 target에 해당 permission 권한이 있으면 true를 리턴한다. <br><br> ex) hasPermission(1, "com.example.domain.Message", "Read") |

<br><br>

***

_2023.07.06. Modified_

_2022.12.05. Update_

_2022.11.27. Update_