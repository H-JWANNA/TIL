# JWT (Json Web Token)

Spring Security에서 지원하는 폼 로그인 방식은 서버 측 **세션**을 사용해 상태(Stateful)를 유지하는 방식으로 SSR 방식에서 주로 사용한다.

반면에 JWT는 REST API 통신을 사용하는 CSR 방식에서 활용도가 높은 자격 증명 방식이다.

<br>

***

<br>

## JWT 개요

<br>

### 🔸 세션 기반 자격 증명

HTTP 프로토콜의 특징은 다음과 같다.

- 비연결성 (Connectionless)  
  request 전송 후, response를 수신하게 되면 연결을 끊는다.

- 비상태성 (Stateless)  
  request와 response에 대한 상태를 저장하지 않는다.

<br>

위와 같은 특징으로 인해 성공적으로 인증된 사용자의 상태를 저장하고 유지하기 위한 수단이 필요한데,  

세션은 이러한 사용자 request 상태를 유지하기 위한 대표적인 수단이다.

<br>

**💡 세션 기반 자격 증명의 특징**

- 인증된 사용자 정보를 **서버 측 세션 저장소에서 관리**한다.

- 생성된 세션 ID는 클라이언트의 쿠키에 저장되어 request 전송 시, 인증된 사용자인지 증명하는 수단으로 사용된다.

- 세션 ID만 클라이언트 쪽에서 사용하므로 **상대적으로 적은 네트워크 트래픽**을 사용한다.

- 서버 측에서 세션 정보를 관리하므로 **보안성 측면에서 조금 더 유리**하다.

- 서버 확장성 면에서 **세션 불일치 문제가 발생**할 가능성이 높다.  
   → Sticky Session, Session Clustering, Session 저장소 외부 분리 등을 통해 보완


- 세션 데이터가 많아질수록 **서버 부담이 가중**될 수 있다.

- **SSR 방식의 어플리케이션에 적합**한 방식이다.

<br>

### 🔸 토큰 기반 자격 증명

토큰은 인증된 사용자의 **자격을 증명**하는 동시에 **접근 권한을 부여**해 특정 리소스에만 접근 가능하게 하는 역할을 한다.

<br>

**💡 토큰 기반 자격 증명의 특징**

- 토큰에 포함된 사용자 인증 정보는 서버 측에서 별도의 관리를 하지 않는다.

- 생성된 토큰을 헤더에 포함시켜 request 전송 시, 인증된 사용자인지 증명하는 수단으로 사용된다.

- 토큰 내에 인증된 사용자 정보 등을 포함하므로 **세션에 비해 상대적으로 많은 네트워크 트래픽을 사용**한다.

- 서버 측에서 토큰을 관리하지 않으므로 보안성 측면에서 불리하다.

- 인증된 사용자 request의 상태를 유지할 필요가 없기 때문에 **서버 확장성 면에서 유리하고, 세션 불일치와 같은 문제가 발생하지 않는다.**

- 토큰에 포함되는 사용자 정보는 토큰의 특성상 암호화가 되지 않기 때문에, **민감한 정보는 토큰에 포함시키지 않아야 한다.**

- 기본적으로 토큰이 만료되기 전까지는 **토큰을 무효화 시킬 수 없다.**  
  → 무효화 시키고자 하는 토큰의 만료 시간을 짧게 주는 방식을 사용해 토큰 무효화 문제를 보완

- **CSR 방식의 어플리케이션에 적합**한 방식이다.

<br>

### 🔸 JWT란?

JWT는 데이터를 안전하고 간결하게 전송하기 위한 인터넷 표준 인증 방식으로 토큰 인증 방식에서 가장 범용적으로 사용되며,  

**JSON 포맷의 토큰 정보를 인코딩** 후, **해당 토큰 정보를 Secret Key로 서명(Sign)한 메시지를 Web Token으로써 인증 과정에 사용**한다.

<br>

#### **💡 JWT의 종류**

JWT는 **액세스 토큰(Access Token)과 리프레시 토큰(Refresh Token)** 2가지 토큰을 사용자의 자격 증명에 이용한다.

- **액세스 토큰 (Access Token)**  
  보호된 정보들(사용자 이메일, 연락처 등)에 접근할 수 있는 권한 부여에 사용한다.  

  또한, 짧은 유효 기간을 주어 탈취 되더라도 오랫동안 사용할 수 없도록 하는 것이 좋다.

- **리프레시 토큰 (Refresh Token)**  
  액세스 토큰의 유효 기간이 만료되면 리프레시 토큰을 사용해 새로운 액세스 토큰을 발급 받는다.

<br>

#### **💡 JWT의 구조**

<img src = "https://velopert.com/wp-content/uploads/2016/12/jwt.png" width = "500"/>

▲ _JWT Structure_

<br>

**1. Header**

어떤 종류의 토큰인지 (ex. JWT), 어떤 알고리즘을 통해 서명(Sign)할지 JSON 포맷 형태로 정의한다.

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

<br>

**2. Payload**

서버에서 활용할 수 있는 사용자의 정보가 담겨있는 부분이다.

접근 권한, 이름이나 이메일 등 필요한 데이터 등을 담을 수 있다.  

```json
{
  "sub": "someInformation",
  "name": "phillip",
  "iat": 151623391
}
```

<br>

해당 JSON 객체를 Base64로 인코딩하면 JWT의 각 부분이 완성되지만,  

쉽게 디코딩이 가능하므로 민감한 정보는 포함하지 않는 것이 좋다.



<br>

**3. Signature**

원하는 비밀 키(Secret Key)와 Header에서 지정한 알고리즘을 사용하여 **Header와 Payload에 단방향 암호화를 수행**한다.

암호화 된 메시지는 토큰의 위변조 유무를 검증하는데 사용된다.

```json
HMACSHA256(base64UrlEncode(header) + '.' + base64UrlEncode(payload), secret);
```

<br><br>

#### **💡 토큰 기반 인증 절차**

<br>

<img src = "https://velog.velcdn.com/images%2Fjun7867%2Fpost%2F80a5ab68-8c14-4c4a-9031-af3385704bf7%2Fimage.png" width = "500"/>

<br>

1. 클라이언트가 서버에 ID/PW를 담아 로그인 요청을 보낸다.

2. ID/PW를 확인하고, 클라이언트에게 보낼 암호화된 토큰을 생성한다.  
   - Access Token과 Refresh Token을 모두 생성  
   - 토큰에 담길 정보(Payload)는 사용자 식별 정보, 사용자 권한 정보 등이 될 수 있다.  
   - Refresh Token을 통해 Access Token을 생성하므로 두 토큰이 같은 정보를 담을 필요는 없다.

<br>

3. 토큰을 클라이언트에 전송하면, 클라이언트는 토큰을 저장한다.
   - 저장 위치는 Local Storage, Session Storage, Cookie 등

<br>

4. 클라이언트가 HTTP Header(Authorization Header) 또는 쿠키에 토큰을 담아 Request를 전송
   - Bearer authentication 이용

<br>

5. 서버는 토큰을 검증한 후, 클라이언트 요청에 대한 응답을 보낸다.

<br><br>

### 🔸 JWT의 장・단점

<br>

#### **💡 장점**

- **상태를 유지하지 않고(Stateless), 확장에 용이한(Scalable) 어플리케이션 구현이 용이하다.**  
  
   → 서버는 클라이언트에 대한 정보를 저장할 필요가 없다.  

   → 클라이언트는 Request를 전송할 때 토큰을 헤더에 포함시키면 된다.

<br>

- **클라이언트가 Request를 전송할 때마다 자격 증명 정보를 전송할 필요가 없다.**  
  
   → 토큰이 만료되기 전까지는 한 번의 인증만 수행하면 된다.

<br>

- **인증을 담당하는 시스템을 다른 플랫폼으로 분리하는 것이 용이하다.**  
  
   → 자격 증명 정보를 다른 플랫폼(Google, GitHub)의 자격 증명 정보로 인증하는 것이 가능하다.  

   → 토큰 생성용 서버를 만들거나, 다른 회사에 토큰 관련 작업을 맡길 수도 있다.

<br>

- **권한 부여에 용이하다.**  
  
   → 토큰의 내용에 해당 사용자의 권한 정보를 포함하는 것이 용이하다.

<br>

#### **💡 단점**

- **Payload는 디코딩이 쉽다.**  
  
   → base64로 인코딩 되므로 디코딩이 쉬워, 해당 데이터를 탈취하면 쉽게 데이터를 확인할 수 있다.

<br>

- **토큰의 길이가 길어지면 네트워크에 부하를 줄 수 있다.**  
  
   → 저장하는 정보의 양이 많아지면 토큰의 길이는 길어진다.

<br>

- **토큰은 자동으로 삭제되지 않는다.**  
  
   → 토큰 만료 시간을 반드시 추가해야 한다.  

   → 토큰이 탈취될 경우를 대비해서 토큰 만료 시간을 너무 길게 설정하지 않는 것이 좋다.

<br><br>

### 🔸 JWT 생성 및 검증과 테스트

<br>

먼저 JWT 생성을 위한 JJWT 라이브러리를 추가해야한다.

(JJWT 외에도 JWT를 위한 라이브러리는 Java JWT가 존재한다.)

```java
dependencies {
	implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
	runtimeOnly	'io.jsonwebtoken:jjwt-jackson:0.11.5'
}
```

<br>

**🛠 JWT 생성**

<br>

```java
public class JwtTokenizer {
   public String encodeBase64SecretKey(String secretKey) {
      return Encoders.BASE64.encode(secretKey.getBytes(StandardCharSets.UTF_8));
   }

   public String generateAccessToken(Map<String, Object> claims,
                                     String subject,
                                     Date expiration,
                                     String base64EncodedSecretKey) {
      Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

      return Jwts.builder()
               .setClaims(claims)
               .setSubject(subject)
               .setIssuedAt(Calendar.getInstance().getTime())
               .setExpiration(expiration)
               .signWith(key)
               .compact();                                 
   } 

   public String generateRefreshToken(String subject,
                                      Date expiration,,
                                      String base64EncodedSecretKey) {
      Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

      return Jwts.builder()
               .setSubject(subject)
               .setIssuedAt(Calendar.getInstance().getTime())
               .signWith(key)
               .compact();
   }

   ...

   private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
      byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);

      Key key = Keys.hmacShaKeyFor(keyBytes);

      return key;
   }
}
```

<br>

```java
public String encodeBase64SecretKey(String secretKey) {
   return Encoders.BASE64.encode(secretKey.getBytes(StandardCharSets.UTF_8));
}
```
▲ _Plain Text 형태인 Secret Key의 byte[]를 Base64 형식의 문자열로 인코딩 해주는 메서드_

<br>

```java
private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
   byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);

   Key key = Keys.hmacShaKeyFor(keyBytes);

   return key;
}
```
▲ _JWT 서명에 사용할 Secret Key를 생성해주는 메서드_

- Base64 형식으로 인코딩 된 Secret Key를 디코딩 한 후, byte[]로 변환한다.

- byte[]를 기반으로 가장 적절한 HMAC 알고리즘을 적용한 Key 객체를 생성한다. (내부적으로 적절한 알고리즘 지정)

<br>

```java
public String generateAccessToken(...) {
   Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

   return Jwts.builder()
           .setClaims(claims)
           .setSubject(subject)
           .setIssuedAt(Calendar.getInstance().getTime())
           .setExpiration(expiration)
           .signWith(key)
           .compact();         
}
```
▲ _인증된 사용자에게 JWT를 최초로 발급해주기 위한 JWT 생성 메서드_

- 먼저 위에 정의한 ```getKeyFromBase64EncodedKey``` 메서드를 통해 Key 객체를 얻는다.

- ```claims```에는 주로 인증된 사용자와 관련된 정보를 추가한다.

- ```subject```에는 JWT에 대한 제목을 추가한다.

- ```Calendar.getInstance().getTime()```을 통해 JWT 발행 일자를 설정한다.

- ```expiration```에는 JWT의 만료 일시를 지정한다.

- ```signWith(key)```를 통해 서명을 위한 Key 객체를 지정한다.

- ```compact()```를 통해 JWT를 생성하고 직렬화 한다.

<br>

```java
public String generateRefreshToken(...) {
   ...
}
```
▲ _Access Token이 만료되었을 경우, 새로 생성할 수 있게 해주는 Refresh Token 생성을 위한 메서드_

<br><br>

**🛠 JWT 생성 기능 테스트**

<br>

```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtTokenizerTest {
   private static JwtTokenizer jwtTokenizer;
   private String secretKey;
   private String base64EncodedSecretKey;

   @BeforeAll
   public void init() {
      jwtTokenizer = new JwtTokenizer();
      secretKey = "jwanna1234123412341234123412341234"

      base64EncodedSecretKey = jwtTokenizer.encodedBase64SecretKey(secretKey);
   }

   @Test
   public void encodeBase64SecretKeyTest() {
      System.out.println(base64EncodedSecretKey);

      // Plain Text인 Secret Key가 Base64 형식으로 인코딩이 정상적으로 수행 되는지 테스트
      assertThat(secretKey, is(new String(Decoders.BASE64.decode(base64EncodedSecretKey))));
   }

   @Test
   public void generateAccessTokenTest() {
      Map<String, Object> claims = new HashMap<>();
      claims.put("memberId", 1);
      claims.put("roles", List.of("USER"));

      String subject = "test access token";
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.MINUTE, 10);
      Date expiration = calendar.getTime();

      String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

      System.out.println(accessToken);

      // JwtTokenizer가 Access Token을 정상적으로 생성하는지 테스트
      // JWT는 생성할 때마다 값이 바뀌므로 정확한 값 대신 null 여부만 체크
      assertThat(accessToken, notNullValue());
   }

   @Test
   public void generateRefreshTokenTest() {
      String subject = "test refresh token";
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.HOUR, 24);
      Date expiration = calendar.getTime();

      String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

      System.out.println(refreshToken);

      // JwtTokenizer가 Resfresh Token을 정상적으로 생성하는지 테스트
      assertThat(refreshToken, notNullValue());
   }
}
```

<br>

- ```@TestInstance()``` : 테스트 인스턴스의 라이프 사이클을 설정할 때 사용한다.
  
   - ```PER_METHOD``` : test 메서드 당 인스턴스가 생성된다.
  
   - ```PER_CLASS``` : test 클래스 당 인스턴스가 생성된다.
      라이프 사이클을 클래스 단위로 지정하면, ```@BeforeAll``` 어노테이션을 static 메서드가 아닌 곳에서도 사용 가능하다.

<br><br>

**🛠 JWT 검증**

```java
public class JwtTokenizer {
   ...
   
   public void verifySignature(String jws, String base64EncodedSecretKey) {
      Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

      Jwts.parserBuilder()
               .setSigningKey(key)
               .build()
               .parseClaimsJws(jws);
   }
}
```
▲ _JWT Signature 검증을 통한 위・변조 여부 확인 메서드_

- JJWT에서는 서명에 사용된 Secret Key를 이용해 Signature를 검증한 후, 성공하면 JWT를 파싱해서 Claims를 얻을 수 있다.

- ```setSigningKey()``` : 서명에 사용된 Secret Key 설정

- ```parseClaimsJws()``` : JWT를 파싱해서 Claims를 획득

<br>

> jws는 Signature가 포함된 JWT라는 의미로 사용한 파라미터이다.
> 
> 파싱(Parsing)은 데이터를 분해・분석하여 원하는 형태로 만들어 내는 것을 말한다.

<br><br>

**🛠 JWT 검증 기능 테스트**

```java
@TestInstance(TestInstance.LifeCycle.PER_CLASS)
public class JwtTokenizerTest {
   ...

   @Test
   public void verifySignatureTest() {
      String accessToken = getAccessToken(Calendar.MINUTE, 10);

      // Signature를 잘 검증하는지 테스트 -> Exception이 발생하지 않는다면 검증 성공
      assertDoesNotThrow(() -> jwtTokenizer.verifySignature(accessToken, base64EncodedSecretKey));
   }

   @Test
   public void verifyExpirationTest() throws InterruptedException {
      String accessToken = getAccessToken(Calendar.SECOND, 1);
      
      assertDoesNotThrow(() -> jwtTokenizer.verifySignature(accessToken, base64EncodedSecretKey));

      TimeUnit.MILLISECOND.sleep(1500);

      // 지정한 만료 일시가 지나면 JWT 만료되는지 테스트
      assertThrows(ExpiredJwtException.class, 
               () -> jwtTokenizer.verifySignature(accessToken, base64EncodedSecretKey));
   }

   private String getAccessToken(int timeUnit, int timeAmount) {
      Map<String, Object> claims = new HashMap<>();
      claims.put("memberId", 1);
      claims.put("roles", List.of("USER"));

      String subject = "test access token"
      Calendar calendar = Calendar.getInstance();
      calendar.add(timeUnit, timeAmount);
      Date expiration = calendar.getTime();

      String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

      return accessToken;
   }
}
```

<br>

***

<br>

## JWT 인증

<br>

### 🔸 로그인한 사용자 정보 조회

<br>

사용자가 서버에 요청을 보낼 때마다 DB에 접근해서 데이터를 가져오는 것은 비효율적인 일이기 때문에  

한 번 인증된 사용자 정보를 세션에 담아놓고, 세션이 유지되는 동안 유저 객체를 DB 접근 없이 가져다 쓸 수 있다.

<br>

Spring Security는 해당 정보를 SecurityContextHolder 내부의 SecurityContext의 ```Authentication``` 객체로 저장하며,  

해당 객체를 참조하기 위해서는 ```@AuthenticationPrincipal``` 어노테이션을 사용할 수 있다.

```UserDetails```를 구현한 ```CustomUserDetails``` 클래스를 매개변수로 받아 아래와 같이 사용할 수 있다.

<br>

```java
@RestController
@RequestMapping("/answers")
@Validated
public class AnswerController {
	private final AnswerService answerService;
	private final AnswerMapper answerMapper;

	public AnswerController(AnswerService answerService, AnswerMapper answerMapper) {
		this.answerService = answerService;
		this.answerMapper = answerMapper;
	}

	@PatchMapping("/{answer-id}")
	public ResponseEntity patchAnswer(
      @Valid @RequestBody AnswerDto.Patch requestBody,
		@PathVariable("answer-id") @Positive long answerId,
		@AuthenticationPrincipal MemberDetails memberDetails) {

		Answer answer =
			answerService.updateAnswer(
				answerMapper.patchToAnswer(requestBody), memberDetails);

		AnswerDto.Response response = answerMapper.answerToResponse(answer);

		return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
	}
}
```

<br><br>

***

_2023.01.09. Update_

_2022.11.25. Update_