# 스트림 (Stream)

스트림은 배열, 컬렉션의 저장 요소를 **하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자이다.**

List, Set, Map, 배열 등 다양한 데이터 소스로부터 스트림을 만들 수 있고,  
풍부한 메서드를 제공하여 **표준화된 방법으로 다룰 수 있다.**

<br>

## 스트림의 특징

### 1. 선언형으로 데이터 소스를 처리한다.

<br>

어떻게 수행하는지보다는 무엇을 수행하는지에 관심을 두고 데이터 소스를 처리한다.

<br>

▼ _기존의 방식_
```java
// 4보다 큰 짝수의 합 구하기
List<Integer> numbers = List.of(1, 3, 4, 6, 7, 8, 11);

int sum = 0;

for(int number : numbers) {
    if(number > 4 && (number % 2 == 0)) {
        sum += number;
    }
}

return sum;
```

<br>

▼ _Stream을 이용한 방식_
```java
// 4보다 큰 짝수의 합 구하기
List<Integer> numbers = List.of(1, 3, 4, 6, 7, 8, 11);

int sum = 0;

int sum =
    numbers.stream()
            .filter(number -> number > 4 && (number % 2 == 0))
            .mapToInt(number -> number)
            .sum();

return sum;
```

<br>

### 2. 람다식으로 요소 처리 코드를 제공한다.

<br>

Stream이 제공하는 대부분의 요소 처리 메서드는 함수형 인터페이스 타입이기 때문에  
**람다식 또는 메서드 참조**를 이용해서 요소 처리 내용을 매개값으로 전달할 수 있다.

<br>

```java
public class Student {
    // Field

    public Student(String name, int score){
        this.name = name;
        this.score = score;
    }
    // getter and setter
}

---------------------------

List<Student> list = Arrays.asList(
    new Student("짱구", 23),
    new Student("철수", 98)
);

Stream<Studnet> stream = list.stream();
stream.forEach( s -> {
    String name = s.getName();
    int score = s.getScore();
    System.out.println(name + score);
})

// 짱구23
// 철수98
```

<br>

### 3. 내부 반복자를 사용하므로 병렬 처리가 쉽다.

<br>

기존에 사용하던 ```for```문과 ```while```문은 개발자가 코드로 직접 컬렉션의 요소를 반복해서 가져오는 **외부반복자(external iterator)** 형태이다.

반면에 스트림에서 사용하는 **내부반복자(internal iterator)** 형태는 컬렉션 내부에서 요소들을 반복시키고, 개발자는 처리할 코드만 제공하는 패턴을 말한다.

```Iterator```는 컬렉션의 요소를 가져오는 것 부터 처리까지 모두 개발자가 작성해야 하지만  
```Stream```은 람다식으로 요소 처리 내용만 전달할 뿐, 반복은 컬렉션 내부에서 일어난다.

<br>

<img src = "https://i.stack.imgur.com/556uD.jpg"/>

▲ _External iterator & Interal iterator_

<br>

> from Stack Overflow :
> 
> When you get an iterator and step over it, that is an **external iterator**  
>When you pass a function object to a method to run over a list, that is an **internal iterator**

<br>

### 4. 중간 연산과 최종 연산을 할 수 있다.

<br>

스트림은 중간연산에서 매핑, 필터링, 정렬 등을 수행하고  
최종 연산에서 반복, 카운팅, 평균, 총합 등의 집계를 수행할 수 있다.

<br>

***

<br>

## 파이프라인 구성(.)

컬렉션의 요소를 합계, 평균값 등의 최종 연산으로 바로 집계할 수 없을 때,  
필터, 매핑 등의 중간 연산이 필요하다.

<br>

### 파이프라인

<br>

파이프라인은 여러개의 스트림이 연결되어 있는 구조를 말한다.  
파이프라인에서 최종 연산을 제외하고는 모두 중간 연산 스트림으로 구성된다.

<br>

<img src = "https://3553248446-files.gitbook.io/~/files/v0/b/gitbook-legacy-files/o/assets%2F-M5HOStxvx-Jr0fqZhyW%2F-MKF1Sut1hTzupHeYfpr%2F-MKF1wzqIYnyF-Pzpzit%2Fucarecdn.png?alt=media&token=d85e4606-8584-4b2c-906d-cb51391612a0">

▲ _Filter의 리턴을 Sorted가 호출, Sorted의 리턴을 Map이 호출하는 식으로 파이프라인 형성_

<br>

```java
// 남녀 멤버
Stream<Member> maleFemaleStream = list.stream();
// 남자만 나누기
Stream<Member> maleStream = maleFemaleSTream.filter(m -> m.getGender() == Member.MALE);
// 나이 모으기
IntStream ageStream = maleStream.mapToInt(Member::getAge);
// 평균값 저장
OptionalDouble opd = ageStream.average();
// 저장된 값 읽기
double ageAve = opd.getAsDouble();

----------( 아래는 로컬 변수를 생략한 같은 코드 )----------

double ageAve = list.stream()   // 남녀 멤버 (오리지널 스트림)
.filter(m-> m.getGender() == Member.MALE)   // 남자만 나누기 (중간 연산)
.mapToInt(Member::getAge)   // 나이 모으기 (중간 연산)
.average()      // 평균값 저장 (최종 연산)
.getAsDouble(); // 저장된 값 읽기
```
▲ _파이프라인을 코드로 구현하면 위와 같다._

<br>

***

<br>

## 스트림 생성, 중간·최종 연산

<br>

### 스트림 생성

java의 ```Collection``` 인터페이스에 정의된 ```stream()``` 메서드를 활용해 스트림을 생성할 수 있다.

<br>

```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> listStream = list.stream();
listStream.forEach(System.out::println);    // 요소 출력
```
▲ _List로부터 Stream 생성_

<br>

```java
Stream<String> stream = Stream.of("a", "b", "c"); //가변인자
Stream<String> stream = Stream.of(new String[] {"a", "b", "c"});
Stream<String> stream = Arrays.stream(new String[] {"a", "b", "c"});
Stream<String> stream = Arrays.stream(new String[] {"a", "b", "c"}, 0, 3); //end 범위 미포함
```
▲ _배열로부터 Stream 생성_

<br>

객체를 위한 ```Stream``` 외에도 ```IntStream```, ```LongStream```, ```DoubleStream``` 등이 존재하며,  
```IntStream```은 ```range()``` 함수를 사용하여 ```for```문을 대체할 수 있다.

```java
// 5이상 100미만의 숫자를 갖는 IntStream
IntStream stream = IntStream.range(5, 100);
```

> 💡   
> 스트림은 데이터를 읽기만 할 뿐 변경하지 않고(Read-only),  
> 람다식과 비슷하게 일회용으로, 한 번 사용하면 닫히게 되니 새로운 스트림을 만들어야 한다.

<br>

### 중간 연산

<br>

중간 연산은 연산 결과를 스트림으로 반환하므로 여러번 수행 가능하다.

🔸 중간 연산의 종류

- **필터링** : filter() / distinct()  
  - ```filter()``` : 매개 값으로 조건이 주어지고, 조건이 참이 되는 요소만을 필터링한다.  
  - ```distinct()``` : 중복된 데이터를 제거하기 위해 사용한다.
  
  <br>

  ```java
  List<String> names = Arrays.asList("짱구", "철수", "유리", "맹구", "짱구");

  names.stream()
        .distinct() // 중복 제거
        .filter(n -> n.endsWith("구"))  // 필터링
        .forEach(n -> System.out.print(n + " "));   
        // 출력 : 짱구 맹구
  ```

<br>

- **매핑** : map() / flatMap()   
  - ```map()``` : 기존의 Stream 요소들을 대체하는 요소로 구성된 **새로운 Stream을 형성**  
    ```map()``` 이외에도 ```mapToInt()```, ```mapToLong()```, ```mapToDouble()``` 등이 있다.

    <br>

  ```java
  List<String> countryCodeAlpha2 = Arrays.asList("kr", "us", "gb", "jp");

  countryCodeAlpha2.stream()
                    .map(s -> s.toUpperCase())
                    .forEach(s -> System.out.print(s + " "));
                    // 출력 : KR US GB JP

  // 아래는 같은 코드이다.  
  countryCodeAlpha2.stream()
                    .map(String::toUpperCase)   // 람다를 메서드 참조로 변환
                    .forEach(s -> System.out.print(s + " "));
  ```

    <br>

  - ```flatMap()``` : 기존의 요소들을 대체하는 복수 개의 요소들로 구성된 새로운 Stream을 형성

    > ```flatMap()```의 리턴 타입은 ```Stream```,  
    > ```Map()```의 리턴 타입은 ```Stream<Stream>```이라는 차이점이 있다.

    <br>

  ```java
  Stream<String[]> stringArraysStream = Stream.of(
                new String[]{"hello", "world", "java"},
                new String[]{"spring", "framework"});

  stringArraysStream.map(Arrays::stream).forEach(System.out::print);
  // 출력 : java.util.stream.ReferencePipeline$Head@3d075dc0java.util.stream.ReferencePipeline$Head@214c265e
  stringArraysStream.flatmap(Arrays::stream).forEach(System.out::print);
  // 출력 : helloworldjavaspringframework
  ```

<br>

- **정렬** : sorted()  
  - ```sorted()``` : Stream의 요소들을 정렬하기 위해 사용하며, 인자가 없을 경우 오름차순으로 정렬  
  내림차순으로 정렬하기 위해서는 ```Comparator```의 ```reverseOrder()```를 이용한다.  

  <br>

  ```java
  List<String> list = Arrays.asList("KR", "US", "GB", "JP");

  list.stream()
        .sorted()
        .forEach(n -> System.out.print(n + " "));
        // 출력 : GB JP KR US

  list.stream()
        .sorted(Compatator.reverseOrder())
        .forEach(n -> System.out.print(n + " "));
        // 출력 : US KR JP GB
  ```

<br>

- **연산 결과 확인** : peek()  
  - ```peek()``` : 요소를 하나씩 돌면서 출력한다. 중간 연산이기 때문에 여러번 출력 가능  
   ```forEach()```는 최종 연산이기 때문에 한 번만 호출가능하다. 호출 후 스트림 종료  
    <br>

   > 주로 연산 중간에 결과를 확인하여 디버깅할 때 사용한다.

   <br>

  ```java
  IntStream
	.filter(a -> a % 2 ==0)
	.peek(n-> System.out.println(n))
	.sum();
  ```

<br>

### 최종 연산

<br>

- **연산 결과 확인** : forEach()  
  : 위의 ```peak()```에서 함께 설명했다.

<br>

- **매칭** : match()  
  Stream의 요소들이 특정한 조건을 충족하는지 검사하고 싶은 경우에 사용  
  함수형 인터페이스 ```Predicate```를 받아서 검사한 후, ```boolean``` 타입으로 반환

  - ```allMatch()``` : 모든 요소들이 조건을 만족하는지 검사  
  - ```anyMatch()``` : 최소한 한 개의 요소가 조건을 만족하는지 검사
  - ```noneMatch()``` : 모든 요소들이 조건을 만족하지 않는지 검사

  <br>

  ```java
  int[] intArr = {2, 4, 6};
      boolean result = Arrays.stream(intArr).allMatch(a -> a % 2 == 0);
      System.out.println("모두 짝수인가? " + result);
      // 출력 : 모두 짝수인가? true
  ```

<br>

- **기본 집계** : sum(), count(), average(), max(), min()  
  요소들을 연산하여 하나의 값으로 산출할 경우에 사용

  <br>

  ```java
  int[] intArr = {1, 2, 3, 4, 5};

  long count = Arrays.stream(intArr).count();
    // 개수
  long sum = Arrays.stream(intArr).sum();
    // 합
  double avg = Arrays.stream(intArr).average().getAsDouble();
    // 평균
  int max = Arrays.stream(intArr).max().getAsInt();
    // 최대값
  int min = Arrays.stream(intArr).min().getAsInt();
    // 최소값
  int first = Arrays.stream(intArr).findFirst().getAsInt();
    // 배열의 첫번째 값
  ```

<br>

- **reduce()**
  - ```reduce(Identity, Accumulator, Combiner)``` : 최대 3개의 매개변수를 받을 수 있다.  
    Identity : 계산을 수행하기 위한 초기값 (생략 가능)  
    Accumulator : 각 요소를 계산한 중간 결과를 생성하기 위해 사용  
    Combiner : 병렬 스트림에서 나누어 계산된 결과를 하나로 합치기 위한 로직 (생략 가능)

    <br>

  ```java
  int[] intArr = {1, 2, 3, 4, 5};

  int sum1 = Arrays.stream(intArr)
                    .map(el -> el * 2)
                    .reduce((a, b) -> a + b)
                    .getAsInt();
  System.out.println("초기값 없는 reduce " + sum1);

  int sum2= Arrays.stream(intArr)
                    .map(el -> el * 2)
                    .reduce(10, Integer::sum);
  System.out.println("초기값 존재하는 reduce " + sum2);

  // 출력
  초기값 없는 reduce 30
  초기값 존재하는 reduce 40
  ```

<br>

- **collect()**  
  Stream의 요소들을 ```List```, ```Set```, ```Map``` 등 다른 종류의 결과로 수집하고 싶은 경우에 사용

  > 일반적으로 ```List```로 Stream의 요소들을 수집하는 경우가 많다.
  
  <br>

  ```java
  List<Student> maleList = totalList.stream()
                .filter(s -> s.getGender() == Student.Gender.Male)
                .collect(Collectors.toList())
                // 마지막 줄을 그냥 .toList()로 바꿀 수 있음

  maleList.stream().forEach(n->System.out.println(n.getName()));

  Set<Student> femaleSet = totalList.stream()
                .filter(s -> s.getGender() == Student.Gender.Female)
                .collect(Collectors.toCollection(HashSet :: new));

  femaleSet.stream().forEach(n->System.out.println(n.getName()));
  ```

<br>

### 📋 [**_Stream 공식 문서_**](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)


<br>

***

<br>

## Optional\<T>

Optional은 NullPointerException(NPE),  
즉 **null 값으로 인해 에러가 발생하는 현상을 객체 차원에서 효율적으로 방지하고자 도입되었다.**

연산 결과를 Optional에 담아서 반환하면, NPE가 발생하지 않도록 코드를 작성할 수 있다.

<br>

### Optional

<br>

```Optional``` 클래스는 모든 타입의 객체를 담을 수 있는 래퍼(Wrapper) 클래스이다.

```java
public final class Optional<T> {    
    private final T value;
}
```

<br>

🔸 **Optional 클래스의 메서드**
- ```of(Object o)``` : Optional 객체를 생성하는 메서드
  
- ```ofNullable(Object o)``` : Optional 객체를 생성하는 메서드  
  \> ```null```일 가능성이 있으면 해당 메서드를 사용

  ```java
  Optional<String> opt = Optional.ofNullable(null);
  System.out.println(opt.isPresent()); 
  //Optional 객체의 값이 null인지 여부를 리턴
  ```

- ```empty()``` : Optional 타입의 참조변수를 기본값으로 초기화
  
  ```java
  Optional<String> opt = Optional.<String>empty();
  ```

- ```get()``` : Optional 객체에 저장된 값 가져오기

- ```orElse(Default)``` : 참조 변수의 값이 ```null```일 가능성이 있다면 디폴트 값 지정해주기

  ```java
  Optional<String> optString = Optional.of("짱구");
  System.out.println(optString);        // Optional[짱구]
  System.out.println(optString.get());  // 짱구

  String nullName = null;
  String name = Optional.ofNullable(nullName).orElse("짱아");
  System.out.println(name); // 짱아
  ```

<br>

### 📋 [**_Optional 공식 문서_**](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)

<br>

***

_Update 2022.09.15._