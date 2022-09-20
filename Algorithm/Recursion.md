# 재귀

재귀란 작은 문제를 해결함으로써 전체 문제를 해결하는 방법을 말하고,  
CS에서 재귀는 **자신을 정의할 때 자기 자신을 재참조** 하는 방법을 뜻한다.

<br>

### 재귀 함수의 장점

- 불필요한 반복문의 사용을 줄여 코드가 간결해지고, 수정이 용이하다.
- 변수의 사용이 줄어든다

### 재귀 함수의 단점

- 반복문에 비해 코드의 흐름을 직관적으로 파악하기 어렵다.
- 메서드를 반복하여 호출하기 때문에 지역변수, 매개변수, 반환값을 모두 process stack에 저장하게 되고, 결과적으로 반복문에 비해 많은 메모리를 사용한다.
- 메서드가 종료된 이후에 복귀를 위한 컨텍스트 스위칭 비용이 발생한다.
  
  > 컨텍스트 스위칭이란?
  > 
  > 여러 프로세스가 실행되고 있을 때, 실행되던 프로세스를 중단하고 다른 프로세스를 실행하는 것  
  > 즉, CPU에 실행할 프로세스를 교체하는 기술

<br>

💡 **1부터 10까지의 합을 구하는 코드**

```java
int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
int sum = 0;

for(int i = 0; i < arr.length; i++) {
    sum += arr[i];
}

return sum;
```
▲ _일반적인 for문_

<br>

```java
int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
int sum = 0;

for(int i : arr) {
    sum += i;
}

return sum;
```
▲ _향상된 for문_

<br>

```java
int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

public int arrSum(int[] array) {
    if (array.length == 0) return 0;

    int[] sumArr = Arrays.copyOfRange(array, 1, array.length);

    return array[0] + arrSum(sumArr);
}
```
▲ _재귀함수_

<br>

## 재귀적 사고

### 1. 재귀 함수의 입력값과 반환값 정의

- 위의 예제에서는 인자로 ```int[]```를 입력받아 최종적으로 ```int``` 형식을 반환한다.

### 2. 문제를 쪼개고 경우의 수를 나누기

- 일반적으로 입력값을 기준으로 문제를 쪼개고 경우의 수를 나눈다.
- 주어진 입력 값 또는 문제 상황을 **크기**로 구분하거나 **순서**를 명확히 한다.
- 구분된 문제를 푸는 방식이 순서나 크기에 상관없이 같으면 문제를 해결한다.

- 위의 예제에서는 배열의 크기에 따라 문제를 나눌 수 있고,  
  ```1 + arrSum(new int[]{2, 3, 4})``` >> ```1 + 2 + arrSum(new int[]{3, 4})```와 같이  
  문제를 푸는 방식이 동일하므로 문제 해결이 가능하다.

### 3. 단순한 문제 해결하기

- 가장 해결하기 쉬운 재귀의 탈출 조건을 구성한다.

- 위의 예제에서는 ```if (array.length == 0) return 0;``` 부분에 해당한다.

### 4. 복잡한 문제 해결하기

- 남아있는 복잡한 경우를 해결한다.

- 고정되는 값과 변화하는 값만 제대로 구분하여 재귀 함수를 작성한다.

<br>

***

<br>

## StringifyJSON

<br>

### JSON

<br>

JavaScript Objcet Notation의 줄임말로, **데이터 교환을 위해 만들어진 객체 형태의 포맷**이다.

네트워크를 통해 어떠한 객체 내용을 다른 프로그램에게 전송할 때,  
데이터를 보내는 발신자와 수신자가 **같은 프로그램을 사용**하거나 **범용적으로 읽을 수 있는 형태**여야 한다.

> Java 객체에 ```toString()```을 써도 Java를 사용하지 않는 프로그램에서는 데이터를 정확히 파악할 수 없다.

<br>

이러한 문제를 해결하기 위해 객체를 JSON의 형태로 변환하거나 JSON을 객체의 형태로 변환해야 한다.

<br>

### jackson

<br>

```jackson```라이브러리에서 제공하는 ```ObjectMapper``` 클래스를 사용하여 JSON의 형태로 변환할 수 있다.

```java
Map<String, String> jsonExample = new HashMap<>(){{
      put("key1", "value1");
      put("key2", "value2");
      put("key3", "value3");
    }};

ObjectMapper mapper = new ObjectMapper();
String json = mapper.writeValueAsString(jsonExample);

System.out.println(json);

// {"key3":"value3","key2":"value2","key1":"value1"}
```
▲ _Java Object to JSON_

위의 코드에서 ```writeValueAsString()```을 사용하는 과정을 직렬화(serialize)한다고 한다.

반대로 JSON으로 변환된 문자열 타입의 객체를 다시 Java의 객체로 변환하는 방법은 아래와 같다.

```java
ObjectMapper mapper = new ObjectMapper();
String json = "{\"key3\":\"value3\",\"key2\":\"value2\",\"key1\":\"value1\"}";

Map<String, String> deserializedData = mapper.readValue(json, Map.class);
System.out.println(deserializedData);

// {key3=value3, key2=value2, key1=value1}
```
▲ _JSON to Java Object_

```readValue()```를 사용해서 다시 Java 객체의 형태로 변환하는 것을 역직렬화(deserialize)한다고 한다.

<br>

<img src = "https://symfony.com/doc/2.6/_images/serializer_workflow.png"/>

▲ _Serializer Component Schema_

<br>

### JSON의 기본 규칙

| | JavaScript Object | JSON |
|:-:|:-|:-|
|키|키는 따옴표 없이 쓸 수 있음| 반드시 쌍따옴표를 붙여야함 |
|문자열 값|문자열 값은 어떠한 형태의 따옴표로도 사용 가능 | 반드시 쌍따옴표로 감싸야함|

<br>

또한 JSON은 키와 키 값 사이 / 키-값 쌍 사이에 공백이 존재해서는 안된다.

<br>

### 📋 [_**JSON 공식 문서**_](https://www.json.org/json-en.html)

<br>

***

_2022.09.20. Update_