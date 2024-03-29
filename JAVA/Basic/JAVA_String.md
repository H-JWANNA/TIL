# 문자열

JAVA는 ```String``` 클래스를 통해 문자열을 다룬다.

<br>

문자열 선언 : ```String = "문자열";```  

문자열 할당
- 문자열 리터럴을 할당하는 방법 : ```변수 = “문자열”;```  
- String 클래스의 인스턴스를 생성하여 할당하는 방법 : ```변수 = new String(”문자열”);```

<br>

▼ Example

```java
String name1 = "Kim Coding";
String name2 = "Kim Coding";

String name3 = new String("Kim Coding");
String name4 = new String("Kim Coding");

boolean comparison1 = name1 == "Kim Coding";      // true
boolean comparison2 = name1 == name2;             // true
boolean comparison3 = name1 == name3;             // false
boolean comparison4 = name3 == name4;             // false
boolean comparison5 = name1.equals("Kim Coding"); // true
boolean comparison6 = name1.equals(name3);        // true
boolean comparison7 = name3.equals(name4);        // true
```

```name1``` ```name2```는 문자열 리터럴을 **직접 할당** 받은 변수이며  
```name3``` ```name4```는 ```String``` 클래스를 통해 **인스턴스**를 생성하여 할당받은 변수이다.

등가 비교 연산자 ```==```는 같은 **주소값**을 가지는지 확인하고, ```equals()``` 메서드는 같은 **결과값**을 가지는지 확인한다.

이를 **동일성(identity)과 동등성(equality)이라고 한다.**

<br>

String에서 리터럴을 사용해서 객체를 생성할 경우 Heap 메모리 내부의 String Pool에 저장이 되는데,  
해당 String Pool에 동일한 정보가 저장이 되어있을 경우 해당 값을 참조한다.  

따라서, name2는 name1에서 생성한 ```"Kim Coding"```을 참조하게 되고, name1과 name2는 **동일성을 가진다.**  

다만, ```new``` 키워드를 사용해서 생성한 name3, name4는 각각 Heap 영역에 새로운 객체를 생성하기 때문에 **동등성을 가지지만, 동일성은 가지지 않는다.**

<br>

***

<br>

## String 클래스의 Method

```charAt(index)``` : 해당 문자열의 특정 인덱스를 반환  
```compareTo("String")``` : 문자열을 비교하여 문자열이 같으면 0, 인수보다 작으면 음수, 크면 양수를 반환
```java
String str = new String("abcd");
System.out.println("문자열 : " + str); // 문자열 : abcd
System.out.println(str.compareTo("bcef")); // -1
System.out.println(str.compareTo("abcd") + "\n"); // 0
System.out.println(str.compareTo("Abcd")); // 32
System.out.println(str.compareToIgnoreCase("Abcd")); // 0
System.out.println("compareTo() 메서드 호출 후 문자열 : " + str); 
```

```concat("String")``` : concatenate의 약자로 문자열을 연결하여 새로운 문자열을 반환

```indexOf("String")``` : 특정 문자나 문자열이 처음으로 등장하는 위치의 인덱스를 반환

```trim()``` : 앞 뒤의 공백 문자 제거

```toLowerCase()``` / ```toUpperCase()``` : 문자열의 모든 문자를 소문자 / 대문자로 변환

<br>

### [📋 **JAVA toString() Method**](https://www.javatpoint.com/understanding-toString()-method)

<br>


### 추가 심화 내용

```StringTokenizer``` : 문자열을 지정한 구분자로 쪼개주는 클래스
> ```import java.util.StringTokenizer```를 통해 호출한다.
- ```.countTokens()``` : (int) 현재 남아있는 token의 개수를 반환
- ```.hasMoreElements(), .hasMoreTokens()``` : (boolean) 현재 위치 뒤에 있는 문자열에서 하나 이상의 토큰을 사용할 수 있는 경우 ```true```를 반환
- ```.nextElement(), .nextToken()``` : (Object, String) 다음 토큰을 반환

<br>

```StringBuilder``` : 문자열을 연결할 때 사용하는 클래스   
> ```StringBuilder name = new StringBuilder();```를 통해 인스턴스를 생성
- ```.append("text")``` : 문자열을 연결하는 메서드
- ```.toString()``` : 변수에 문자열을 할당할 때 사용하는 메서드
- ```.delete(num1, num2)``` : (num1) ~ (num2-1) 인덱스에 해당하는 문자열을 제거
- ```.deleteCharAt(num)``` : num 인덱스에 해당하는 문자 제거

```java
StringBuilder stringBuilder = new StringBuilder();
stringBuilder.append("문자열 ").append("연결");

String str = stringBuilder.toString();

// 아래 두 코드는 같은 값을 출력한다.
System.out.println(stringBuilder);  // 문자열 연결 (StringBuilder 타입)
System.out.println(str);            // 문자열 연결 (String 타입)
```

<br>

```StringBuffer``` : ```StringBuilder```와 비슷하게 문자열 연결에 사용하는 클래스
> ```StringBuffer name = new StringBuffer();```를 통해 인스턴스를 생성
- ```.append()``` : 문자열을 연결하는 메서드 (```concat()``` 메서드와 같은 결과지만 훨씬 빠름)
- ```.capacity()``` : 인스턴스의 현재 버퍼 크기를 반환 (문자열 크기 + 여유 버퍼 크기 16)
- ```.delete(num1, num2)``` : (num1) ~ (num2-1) 인덱스에 해당하는 문자열을 제거
- ```.deleteCharAt(num)``` : num 인덱스에 해당하는 문자 제거
- ```.insert(num, "text")``` : num 인덱스 위치에 "text" 문자열을 삽입

<br>

### 🔸 **String** vs **StringBuffer** vs **StringBuilder**

<br>

**String** 은 **불변**의 속성을 갖는다.  
변하지 않는 문자열을 자주 읽어들이는 경우 좋은 성능을 기대할 수 있다.  

```str + "text"```와 같은 문자열 추가, 수정, 삭제 등의 연산이 발생하면  
기존의 메모리 영역은 Garbage로 남아있다가 사라지고 새로운 메모리 영역을 가리키게 되므로  
힙 메모리에 많은 임시 가비지가 생성되어 성능에 영향을 끼치게 된다.

<br>

반면 **StringBuffer**와 **StringBuilder**의 경우에는 **가변**의 속성을 갖는다.  

StringBuffer와 StringBuilder의 차이로는  

StringBuffer는 동기화를 지원하기 때문에 멀티쓰레드 환경에서 안전하지만 속도 측면에서 불리하고,  

StringBuilder는 동기화를 지원하지 않기 때문에 멀티 쓰레드 환경에는 적합하지 않지만 단일 쓰레드에서의 성능은 더 뛰어나다.

<br>

> **💡 동기화 (synchronized)**
> 
> 멀티 쓰레드 환경에서 1번 쓰레드와 2번 쓰레드가 동시에 하나의 자원을 사용하고자 할 때,  
> 현재 데이터를 사용하고 있는 쓰레드를 제외한 나머지 쓰레드는 데이터에 접근할 수 없도록 막는 것을 말한다.

<br>

***

_2023.03.22. Update_

_2022.09.04. Modified_

_2022.08.30. Update_