# Spring Security

### Spring Security 6.0 이후의 변경점을 주로 다루고 있으며, 이전 버전에 대한 내용은 [여기](./Spring_Security_Legacy.md)에서 확인할 수 있다.

<br>

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

## SSR 방식을 통한 Spring Security 구성

### 🔸 Spring Security 의존성 라이브러리 추가

<br>

```java
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-security'
}
```

<br>

### 🔸 Spring Security Configuration 적용

원하는 인증 방식과 웹 페이지에 대한 접근 권한을 설정할 수 있다.

<br>

```java
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
}
```

<br>

- `@EnableWebSecurity`  
  Spring Security 사용을 위한 어노테이션  
  `WebSecurityConfiguration.class`, `SpringWebMvcImportSelector.class`, `OAuth2ImportSelector.class`, `HttpSecurityConfiguration.class`를 활성화 시켜준다.

<br>

### 🔸 정적 자원에 대한 Security Ignore 처리

`WebSecurityCustomizer` 클래스를 Bean으로 등록하여 정적 자원에 대해 Security를 무시할 수 있다.

```java
public class SecurityConfiguration {
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
			.requestMatchers(HttpMethod.POST, "/registers");
	}
}
```

<br>

- `ignoring().requestMatchers()`  
  HttpMethod, Path Pattern을 파라미터로 구성하여 특정 URI에 대한 Security 적용을 무시할 수 있다.

- `PathRequest.toStaticResources().atCommonLocations()`  
  `src/main/resources/static`(Default 경로) 하위의 패키지에 대해 Security 적용을 무시할 수 있다.

  ```java
  public enum StaticResourceLocation {

    CSS("/css/**"),

    JAVA_SCRIPT("/js/**"),

    IMAGES("/images/**"),

    WEB_JARS("/webjars/**"),

    FAVICON("/favicon.*", "/*/icon-*");

    ...
  }
  ```

  ▲ _atCommonLocations 메소드에 기본으로 설정된 경로는 위와 같다._

<br>

### 🔸 SecurityFilterChain 구성

Spring Security 6.0 이상에서는 메소드 체이닝 방식이 Deprecated 되어 각각의 설정에 대해 람다식으로 표현하는 것을 권장한다.

<br>

**🖥️ 기존 코드**

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
      .sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			.maximumSessions(1)
			.maxSessionsPreventsLogin(false)
			.expiredUrl("/session-expired")
			.and()
      .authorizeHttpRequests(
        authorize -> authorize
          .antMatchers("/admins/**").hasRole("ADMIN")
          .antMatchers("/managements/**").hasRole("OWNER")
          .antMatchers("/members/**").hasRole("USER")
          .antMatchers("/auths/**").permitAll()
          .antMatchers("/**").permitAll()
      );

    return http.build();
  }
}
```

<br>

**🖥️ 변경된 코드**

```java
@Configuration
public class SecurityConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable);

		http
			.formLogin(form -> form
				.usernameParameter("username")
				.passwordParameter("password")
				.loginPage("/auths/login-form")
				.loginProcessingUrl("/process_login")
				.failureUrl("/auths/login-form?error"));

		http
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/"));

		http
			.exceptionHandling(handle -> handle
				.accessDeniedPage("/auths/access-denied"));

		http.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.maximumSessions(1)
				.maxSessionsPreventsLogin(false)
				.expiredUrl("/session-expired"));

    http
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers("/admins/**").hasRole("ADMIN");
				auth.requestMatchers("/managements/**").hasRole("OWNER");
				auth.requestMatchers("/members/**").hasRole("USER");
				auth.requestMatchers("/auths/**").permitAll();
				auth.requestMatchers("/**").permitAll();
			});

    return http.build();
  }
}
```

<br>

`csrf()` : 기본적으로 아무 설정도 하지 않으면 CSRF 공격을 방지한다.

- `AbstractHttpConfigurer::disable`  
  CSRF 공격에 대한 Spring Security 설정 비활성화

<br>

`formLogin()` : 기본적인 인증 방법을 폼 로그인 방식으로 지정

- `withDefaults()`  
  기본 설정 사용

- `usernameParameter()`  
  인증을 수행할 때 사용자 이름을 찾는 HTTP 매개 변수 (기본값 : username)

- `passwordParameter()`  
  인증을 수행할 때 암호를 찾는 HTTP 매개 변수 (기본값 : password)

- `loginPage("/auths/login-form")`  
  Controller의 엔드 포인트를 통한 커스텀 로그인 페이지 사용

- `longinProcessingUrl("/process_login")`  
  로그인 인증 요청을 수행할 요청 URL을 지정  
  해당 URL은 HTML 파일 form 태그의 action 속성에 지정한 값과 같다.

  ```html
  <form action="/process_login" method="post"></form>
  ```

- `failureUrl("/auths/login-form?error")`  
  로그인 인증에 실패할 경우, 리다이렉트 할 화면을 지정  
  HTML 태그의 `${param.error}` 값을 통해 로그인 인증 실패 메시지 표시 여부를 결정

  ```html
  <div role="alert" th:if="${param.error != null}">
    <div>로그인 인증에 실패했습니다.</div>
  </div>
  ```

<br>

`logout()` : 로그아웃 설정을 위한 `LogoutConfigurer`를 리턴

- `logoutUrl("/logout")`  
  사용자가 로그아웃을 수행하기 위한 request URL 지정  
  해당 URL은 HTML 파일의 href 속성에 지정한 값과 같다.

  ```html
  <a href="/logout">로그아웃</a>
  ```

- `logoutSuccessUrl("/")`
  로그아웃이 완료되면 리다이렉트 할 URL 지정

<br>

`exceptionHandling()` : 웹 보안 사용 시 자동으로 적용되는 예외 처리를 구성

- `accessDeniedPage("/auths/access-denied")`  
  권한이 없는 사용자가 특정 Request URI에 접근할 경우 발생하는 **403(Forbidden)** 에러를 처리하기 위한 페이지 설정

<br>

`sessionManagement()` : 세션 관리 구성

- `sessionCreationPolicy()`  
  세션 생성 정책을 관리  
  ALWAYS, NEVER, IF_REQUIRED, STATELESS 4가지 속성이 있으며 ALWAYS와 NEVER를 주로 사용

- `maximumSessions(1)`  
  한 계정에 최대 허용 가능한 세션 수를 설정

- `maxSessionsPreventsLogin(false)`  
  최대 허용 세션에서 추가로 인증이 들어올 경우  
  true : 인증 실패 / false : 기존 세션 만료

- `expiredUrl("/session-expired)`  
  세션이 만료될 경우 이동할 페이지 지정

<br>

`authorizeHttpRequests()` : Request URI에 대한 접근 권한 부여

- `anyRequest().permitAll()`  
  클라이언트 모든 요청에 대해 접근 허용

- `requestMatchers()`
  Path Pattern을 통해 특정 URI 지정

- `hasRole("ADMIN")`  
  **ADMIN** 역할을 부여 받은 사용자만 requestMatchers의 URI에 접근할 수 있다는 의미

- `permitAll()`  
  모든 사용자가 접근할 수 있다는 의미

<br>

> **💡 Path Pattern**
>
> `?` : 한 문자 일치
>
> - /pages/t?st.html
>   - **YES**: /pages/test.html, /pages/tXst.html
>   - NO : /pages/toast.html
>
> <br>
>
> `*` : 경로(/) 안의 모든 문자 일치
>
> - /resources/\*.png
>   - YES: /resources/photo.png
>   - NO : /resources/favority.ico
>
> <br>
>
> `**` : 하위 경로 모든 문자 일치
>
> - /resources/\*\*
>   - /resources/image.png, /resources/css/spring.css
>
> <br>
>
> `{spring}` : spring 이라는 변수로 캡처
>
> - /resources/{path}
>   - /resources/robot.txt -> path변수에 "robot.txt" 할당
>   - @PathVariable("path")로 접근 가능
>
> <br>
>
> `{*spring}` : 하위 경로 끝까지 spring변수에 캡쳐
>
> - /items/{\*path}
>   - /items/1/add -> path변수에 "/1/add" 할당
>
> <br>
>
> `{spring:[a-z]+}` : 정규식 이용
>
> - /items/{path:[a-z]+}
>   - YES: /items/robots
>   - NO : /items/123

<br>

> **💡 접근 권한 부여 시 주의사항**
>
> 더 구체적인 URL 경로부터 접근 권한을 부여한 다음  
> 덜 구체적인 URL 경로에 접근 권한을 부여해야한다.
>
> `permitAll()`을 가장 앞에 부여할 경우,  
> 뒤에 오는 `hasRole()`에 부여된 접근 권한은 제 기능을 하지 못하게 된다.

<br>

### 🔸 PasswordEncoder Bean 등록

```java
@Configuration
public class SecurityConfiguration {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
```

<br>

### 🔸 만료된 세션을 정리하기 위한 설정 등록

```java
@Configuration
public class SecurityConfiguration {
	@Bean
	public static ServletListenerRegistrationBean httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
	}
}
```

<br>

---

<br>

## 인증 객체 Customize

### 🔸 Custom AuthorityUtils 구현

```java
@Component
public class CustomAuthorityUtils {
  // @Value 어노테이션을 사용하여 감추는 것도 좋은 방법이다.
	private final String ADMIN_USERNAME = "admin";

	private final List<GrantedAuthority> ADMIN_ROLES =
		AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_OWNER", "ROLE_USER");

	private final List<GrantedAuthority> OWNER_ROLES =
		AuthorityUtils.createAuthorityList("ROLE_OWNER", "ROLE_USER");

	private final List<GrantedAuthority> USER_ROLES =
		AuthorityUtils.createAuthorityList("ROLE_USER");

	private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "OWNER", "USER");
	private final List<String> USER_ROLES_STRING = List.of("USER");

	// DB에 저장된 Role 기반으로 권한 정보 생성
	public List<GrantedAuthority> createAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = roles.stream()
			.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
			.collect(Collectors.toList());

		return authorities;
	}

	public List<String> createRoles(String username) {
		if (username.equals(ADMIN_USERNAME)) {
			return ADMIN_ROLES_STRING;
		}

		return USER_ROLES_STRING;
	}
}
```

<br>

- `AuthorityUtils.createAuthorityList(...)`  
  Spring Security에서 지원하는 AuthorityUtils 클래스를 이용해 관리자용 또는 일반 사용자용 권한 목록을 생성한다.

- `SimpleGrantedAuthority()`  
  해당 객체를 생성할 때, 생성자 파라미터로 넘겨주는 값이 `USER`나 `ADMIN`이 아닌 `ROLE_USER`, `ROLE_ADMIN` 형태로 넘겨주어야 한다.

<br>

JPA를 통해 User와 User의 권한 정보 간의 연관 관계를 맺을 수 있다.

```java
@Entity
public class Member {
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();
}
```

- `@ElementCollection`  
  해당 어노테이션을 작성하면, 별도의 Entity 클래스를 생성하지 않아도 간단하게 매핑 처리가 되며 테이블을 생성한다.

<br>

### 🔸 Custom UserDetails 구현

기본으로 제공되는 UserDetails에는 username, password, role 3가지 정보만 담기기 때문에  
커스텀을 통해 추가적으로 필요한 정보를 담을 수 있다.

<br>

```java
@Getter
public class CustomUserDetails extends Member implements UserDetails {
	private String username;
	private String password;
	private String name;
	private String email;
	private String phoneNumber;
	private Integer point;
	private Collection<GrantedAuthority> authorities;

	public CustomUserDetails(Member member, CustomAuthorityUtils authorityUtils) {
		this.username = member.getUsername();
		this.password = member.getEncryptedPassword();
		this.name = member.getName();
		this.email = member.getEmail();
		this.phoneNumber = member.getPhoneNumber();
		this.point = member.getPoint();
		this.authorities = authorityUtils.createAuthorities(member.getRoles());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
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
```

<br>

- `UserDetails`  
  UserDetailsService에 의해 로드되어 인증을 위해 사용되는 핵심 User 정보를 표현하는 인터페이스

  > 해당 인터페이스의 구현체는 직접 사용되지는 않고, Authentication 객체로 캡슐화되어 제공된다.

- `CustomUserDetails`  
  UserDetails 인터페이스를 구현하고, Member Entity를 상속한다.

  이렇게 구성하면 **DB에서 조회한 회원 정보를 Spring Security의 User 정보로 변환하는 과정**과  
  **User의 권한 정보를 생성하는 과정을 캡슐화** 할 수 있다.

  또한, Member Entity를 상속하고 있기 때문에 CustomUserDetails를 리턴받아 사용하는 측에서는  
  두 클래스의 객체를 모두 손쉽게 캐스팅해서 사용 가능하다는 장점이 있다.

<br>

### 🔸 Custom UserDetailsService 구현

```java
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberService memberService;
	private final CustomAuthorityUtils authorityUtils;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberService.findMemberByUsername(username);

		return new CustomUserDetails(member, authorityUtils);
	}
}
```

<br>

- `UserDetailsService`  
  Custom UserDetailsService를 구현하기 위한 인터페이스

- `loadUserByUsername()`  
  UserDetailsService 인터페이스를 구현하는 클래스는 해당 추상 메서드를 구현해야한다.  
  username을 통해 Member를 찾아 CustomUserDetails를 생성한다.

<br>

### 🔸 Custom AuthenticationProvider

<br>

Custom UserDetailsService를 사용해 로그인 인증을 처리하는 방식은 Spring Security가 내부적으로 인증을 대신 처리해주는 방식이다.

Custom AuthenticationProvider를 사용하면 직접 로그인 인증을 처리할 수 있다.

<br>

```java
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private final MemberService memberService;
	private final CustomAuthorityUtils authorityUtils;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;

		String username = token.getName();
		Optional.ofNullable(username).orElseThrow(
			() -> new UsernameNotFoundException("Invalid Username or Password"));

		Member member = memberService.findMemberByUsername(username);

		String password = member.getEncryptedPassword();
		verifyCredentials(authentication.getCredentials(), password);

		Collection<GrantedAuthority> authorities = authorityUtils.createAuthorities(member.getRoles());

		log.info("### Authenticate : {}", username);

		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

	private void verifyCredentials(Object credentials, String password) {
		if (!passwordEncoder.matches((String)credentials, password)) {
			throw new BadCredentialsException("Invalid Username or Password");
		}
	}
}
```

<br>

Spring Security는 **AuthenticationProvider를 구현한 구현 클래스가 Spring Bean으로 등록되어 있다면 해당 Custom AuthenticationProvider를 이용해서 인증을 진행**한다.

- `AuthenticationProvider`  
  authenticate 메서드와 supports 메서드를 구현해야 한다.

- `supports()`  
  직접 구현한 AuthenticationProvider가 Username/Password 방식의 인증을 지원한다는 것을 Spring Security에게 알려주는 역할

  해당 값이 true일 경우, authenticate() 메서드를 호출해서 인증을 진행한다.

- `Authentication authenticate()`  
  해당 구현 메서드를 통해 인증 처리 로직을 직접 작성하여 사용자의 인증 여부를 결정한다.

  - `UsernamePasswordAuthenticationToken`

    해당 객체에서 사용자의 Username을 얻은 후, 존재하는지 체크한다.

    ```java
    String username = token.getName();
    Optional.ofNullable(username).orElseThrow(...);
    ```

    <br>

    Username이 존재한다면 DB에서 해당 사용자를 조회한다.

    ```java
    Member member = memberService.findMemberByUsername(username);
    ```

    <br>

    로그인 정보에 포함된 패스워드와 DB에 저장된 사용자의 패스워드 정보가 일치하는지 검증한다.

    ```java
    verifyCredentials(authentication.getCredentials(), password);
    ```

    <br>

    검증에 성공하면 사용자 권한을 생성한다.

    ```java
    Collection<GrantedAuthority> authorities = authorityUtils.createAuthorities(member.getRoles());
    ```

    <br>

    최종적으로 인증된 사용자의 인증 정보를 리턴값으로 전달한다.  
    (해당 인증 정보는 내부적으로 Spring Security에서 관리한다.)

    ```java
    return new UsernamePasswordAuthenticationToken(username, password, authorities);
    ```

<br><br>

---

_2023.08.29. Update_
