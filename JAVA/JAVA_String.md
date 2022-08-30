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

> ```name1``` ```name2```는 문자열 리터럴을 **직접 할당** 받은 변수이며  
> ```name3``` ```name4```는 ```String``` 클래스를 통해 **인스턴스**를 생성하여 할당받은 변수이다.
> 
> 등가 비교 연산자 ```==```는 같은 **주소값**을 가지는지 확인하고,  
> ```equals()``` 메서드는 같은 **결과값**을 가지는지 확인한다.
>
> <br>
>
> 나와 똑같이 생긴 사람이 있을 수 있지만, 그게 나는 아니듯이  
> ```==```는 진짜 나만을 true로 반환하고,   
> ```equals()```는 외형적으로 나와 똑같이 생긴 사람까지 true로 반환하는 느낌이다.

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

추가로 공부할 내용

> StringTokenizer, StringBuilder, StringBuffer

<br>

***

<span style="color: gray ; display: inline-block; width: 95%; text-align: right;">_2022.08.30._</span>