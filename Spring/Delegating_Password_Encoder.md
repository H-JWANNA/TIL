# Delegating Password Encoder

```DelegatingPasswordEncoder```는 SpringSecurity에서 지원하는 ```PasswordEncoder``` 구현 객체를 생성해주는 컴포넌트이다.

해당 컴포넌트를 통해 어플리케이션에서 사용할 PasswordEncoder를 결정하고,  
결정된 PasswordEncoder로 사용자가 입력한 패스워드를 단방향 암호화한다.

<br>

**💡 Delegating Password Encoder의 장점**

- ```DelegatingPasswordEncoder```를 사용해 다양한 방식의 암호화 알고리즘을 적용할 수 있는데,  
  사용하고자 하는 암호화 알고리즘을 특별히 지정하지 않는다면 Spring Security에서 권장하는 최신 암호화 알고리즘을 사용하여 패스워드를 암호화 할 수 있도록 한다.

- 패스워드 검증에 있어서도 레거시 방식의 암호화 알고리즘으로 암호화 된 패스워드의 검증을 지원한다.

- Delegating은 위임이라는 의미로,  
  나중에 암호화 방식을 변경하고 싶다면 언제든지 암호화 방식을 변경할 수 있다.

  - 단, 기존에 암호화 되어 저장된 패스워드에 대한 마이그레이션 작업이 진행되어야 한다.

<br>

***

<br>

## DelegatingPasswordEncdoer를 통한 PasswordEncoder 생성

```java
PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
```

<br>

```PasswordEncoderFactories```의 메서드를 통해 ```DelegatingPasswordEncoder``` 객체를 생성하고,  

내부적으로 ```DelegatingPasswordEncdoer```가 다시 적절한 ```PasswordEncoder``` 객체를 생성한다.

<br><br>

```PasswordEncoderFactories```를 사용하면 기본적으로 Spring Security에서 권장하는 PasswordEncoder를 사용할 수 있지만,  

필요한 경우 DelegatingPasswordEncoder로 직접 PasswordEncoder를 지정해서 Custom 할 수 있다.

<br>

```java
String idForEncode = "bcrypt";
Map ecndoers = new HashMap<>();
encoders.put(idForEncode, new BCryptPasswordEncdoer());
encoders.put("noop", NoOpPasswordEncoder.getInstance());
encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
encoders.put("scrypt", new SCryptPasswordEncoder());
encoders.put("sha256", new StandardPasswordEncoder());

PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
```

▲ _Custom DelegatingPasswordEncoder_

<br>

***

<br>

## 암호화 된 Password Format

Spring Security 5에서는 패스워드를 암호화할 때, 암호화 알고리즘 유형을 prefix로 추가한다.

- ```{id}encodedPassword``` : 암호화 된 패스워드 포맷

<br>

> ex) ```BCryptPasswordEncoder```를 통한 암호화를 할 경우  
>
> - ```{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG```
> 
>   - PasswordEncoder id : "bcrypt"  
>  
>   - encodedPassword : "$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG"

<br>

***

<br>

## 패스워드 암호화 기술의 성장

<br>

**1. Plain Text 저장**

Plain Text는 암호화 되지 않은 텍스트 그 자체를 의미한다.

<br>

**2. Hash 알고리즘**

해시 알고리즘은 단방향 암호화를 위한 핵심 알고리즘이다.

DB에 암호화되어 저장되는 패스워드 자체는 검증하는 용도이므로 복호화 될 필요가 없다.

<br>

**3. MD5 (Message Digest 5)**

MD5도 단방향 알고리즘이지만 복호화가 된 사례가 있어, 지금은 거의 사용하지 않는다.

> ❓ **Digest**
>
> 다이제스트는 원본 메세지를 암호화한 메세지를 의미한다.

<br>

**4. SHA(Secure Hash Algorithm)**

SHA 알고리즘은 해시된 문자열을 만들어내기 위해 비트 회전 연산이 추가된 방식이다.

<br>

**5. Rainbow Attack에 대한 대응책**

사용자가 패스워드로 사용할만한 문자열들을 미리 목록으로 만들어 놓은 것을 **Rainbow Table**이라고 하며,  

해당 목록을 동일한 알고리즘으로 암호화 한 후, 탈취한 암호화 된 문자열과 서로 비교하는 작업을 통해 패스워드의 원본 문자열을 알 수 있게 되는데

이러한 공격을 **Rainbow Attack**이라고 한다.

<br>

**💡 Rainbow Attack에 대한 대응책**

1. Digest를 비교하는 작업의 횟수를 줄인다.

   - 해시된 Digest를 반복적으로 해시하는 알고리즘을 만든다. (키 스트레칭)

<br>

2. 솔트(Salt)를 이용한다.  
   
   - 패스워드로 입력하는 원본 메시지에 임의의 문자열을 추가해서 해시처리한다.

<br>

**6. Work Factor를 추가한 Hash 알고리즘**

Work Factor는 **공격자가 해시된 메세지를 알아내는데 더 느리고, 많은 비용이 들게 해주는 요소**를 의미한다.

- **PBKDF2**와 **bcrypt**는 Work Factor로 솔트와 키 스트레칭을 기본적으로 사용하고,  
  내부적으로 훨씬 복잡한 알고리즘을 이용해서 공격자의 공격을 느리게 만든다.

- **scrypt**는 다이제스트 생성 시, **메모리 오버헤드를 갖도록 설계**되어 있어서,  
  무차별 대입 공격(Brute Force Attack)을 시도하기 위한 병렬화 처리가 매우 어렵다는 특징이 있다.

<br><br>

***

_2022.12.04. Update_