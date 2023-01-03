# Java 컬렉션

## 컬렉션 프레임워크 (Collection Framework)

컬렉션 프레임워크는 널리 알려져있는 자료 구조를 바탕으로 컬렉션을 만들고, 관련된 인터페이스와 클래스를 포함시켜둔 것을 말한다.

<br>

▼ _Java Collection Framework_

<img src = "https://hashnets.com/wp-content/uploads/2020/11/20-copy.jpeg" />

<br>

### 🔸 Collection 인터페이스의 메서드

|기능	|리턴<br>타입	|메서드	|설명|
|:-:|:-:|:-----|:-|
|객체<br>추가|	boolean	|add(Object o) <br>addAll(Collection c)|	주어진 객체 및 컬렉션의 객체들을 컬렉션에 추가|
|객체<br>검색|	boolean	|contains(Object o)<br>containsAll(Collection c)|	주어진 객체 및 컬렉션이 저장되어 있는지 여부를 리턴|
||Iterator|	iterator()|	컬렉션의 iterator를 리턴|
||boolean|	equals(Object o)|	컬렉션이 동일한지 여부를 확인|
||boolean|	isEmpty()|	컬렉션이 비어있는지 여부를 확인|
||int|	size()|	저장되어 있는 전체 객체 수를 리턴|
|객체<br>삭제|	void|	clear()|	컬렉션에 저장된 모든 객체를 삭제|
||boolean|	remove(Object o) <br> removeAll(Collection c)|	주어진 객체 및 컬렉션을 삭제하고 성공 여부를 리턴|
||boolean	|retainAll(Collection c)|	주어진 컬렉션을 제외한 모든 객체를 컬렉션에서 삭제하고,<br>컬렉션에 변화가 있는지의 여부를 리턴|
|객체<br>변환|	Object[]|	toArray()|	컬렉션에 저장된 객체를 객체배열(Object [])로 반환|
||Object[]|	toArray(Object[] a)|	주어진 배열에 컬렉션의 객체를 저장해서 반환|

<br>

***

<br>

## Iterator

컬렉션에 저장된 요소들을 순차적으로 읽어오는 역할을 하는 반복자이다.

```.iterator()``` 메서드를 활용하여 Iterator 타입의 인스턴스를 생성할 수 있다.

<br>

### 🔸 Iterator 인터페이스의 메서드

|메서드	|설명|
|:-|:-|
|hasNext()|	읽어올 객체가 남아 있으면 true를 리턴하고, 없으면 false를 리턴한다.|
|next()|	컬렉션에서 하나의 객체를 읽어옵니다.<br>이 때, next()를 호출하기 전에 hasNext()를 통해 읽어올 다음 요소가 있는지 먼저 확인해야 한다.|
|remove()|	next()를 통해 읽어온 객체를 삭제 <br>next()를 호출한 다음에 remove()를 호출해야 한다.|

<br>

```java
ArrayList<String> list = ...;
Iterator<String> iterator = list.iterator();  // Iterator 객체 생성

while(iterator.hasNext()) {     // 읽어올 다음 객체가 있다면 
  String str = iterator.next(); // next()를 통해 다음 객체를 읽어온다.
  System.out.println(str);
}
```

<br>

***

<br>

## List\<E>

배열과 유사하게 인덱스가 부여된 객체를 일렬로 늘어놓은 구조이다.

> 중복 혀용 O / 저장 순서 O

<br>

```List``` 인터페이스를 구현한 클래스로는 ```ArrayList```, ```LinkedList```, ```Vector```, ```Stack``` 등이 있다.

<br>

### 🔸 List 인터페이스의 메서드

|기능|	리턴 타입|	메서드	|설명|
|:-:|:-----------:|:--------|:---|
|객체<br>추가|	void|	add(int index, Object element)	|주어진 인덱스에 객체를 추가|
||boolean|	addAll(int index, Collection c)|	주어진 인덱스에 컬렉션을 추가|
||Object	|set(int index, Object element)|	주어진 위치에 객체를 저장|
|객체<br>검색|	Object|	get(int index)|	주어진 인덱스에 저장된 객체를 반환|
||int|	indexOf(Object o)<br>lastIndexOf(Object o)	|정·역방향으로 탐색하여 주어진 객체의 위치를 반환|
||ListIterator	|listIterator()<br>listIterator(int index)|	List의 객체를 탐색할 수 있는ListIterator 반환<br>주어진 index부터 탐색할 수 있는 ListIterator 반환|
||List|	subList(int fromIndex, int toIndex)|	fromIndex부터 toIndex에 있는 객체를 반환|
|객체<br>삭제|	Object|	remove(int index)|	인덱스에 저장된 객체 삭제 후 삭제된 객체를 반환|
||boolean|	remove(Object o)|	주어진 객체를 삭제|
|객체<br>정렬|	void	|sort(Comparator c)|	주어진 비교자(comparator)로 List를 정렬|

<br>

### **ArrayList**

```ArrayList```는 컬렉션 프레임 워크에서 가장 많이 사용되는 클래스이며,  
배열과 유사하지만 저장용량이 자동으로 늘어나는 동적 배열이라는 차이점이 있다.

```ArrayList```는 기능적으로 ```Vector```와 동일하지만 아래와 같은 차이점이 있다.

- 동기화  
  ```Vector```는 동기화가 되어있어 한번에 하나의 Thread만 액세스 가능하지만,  
  ```ArrayList```는 동기화가 되어있지 않아 동시에 여러 Thread가 작업이 가능하다.

- 스레드 안전(Thread Safe)  
  ```Vector```는 동기화 되어있어 멀티 스레드 환경에서 안전하게 프로그램 실행이 가능하다.

- 성능  
  ```ArrayList```는 동기화 되지 않았기 때문에 동기화 된 ```Vector```보다 **빠르다**.

- 크기 증가  
  둘 다 동적 배열 클래스인 것은 동일하나 최대 인덱스를 초과할 때 추가되는 인덱스 수가 다르다.  
  ```Vector```는 현재 배열 크기의 100%, ```ArrayList```는 현재 배열 크기의 50%가 증가한다.

<br>

```java
ArrayList<T> name = new ArrayList<T>(초기 저장 용량);   // 초기 저장 용량 Default : 10

// List 클래스의 메서드를 사용 가능하다.
name.add("str1");
name.size();    // 1
name.get(0);    // str1
name.set(0, "str2")
name.remove(0); // str2
```

### 📋 [**_ArrayList의 주요 Method_**](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)

<br>

### **LinkedList**

```LinkedList```는 데이터를 효율적으로 추가, 삭제, 변경하기 위해 사용한다.

<br>

<img src = "https://media.geeksforgeeks.org/wp-content/uploads/20210409184741/HowtoImplementGenericLinkedListinJava.jpg" />

▲ _LinkedList의 각 요소는 자신과 연결된 요소의 주소값과 자신의 데이터로 구성되어있다._

<br>

### 💡 ArrayList vs LinkedList

<br>

1. 검색  
  **ArrayList > LinkedList**  
  ```ArrayList```는 index 기반의 자료구조이기 때문에 O(1)의 시간 복잡도를 가진다.  
  ```LinkedList```는 모든 요소를 탐색해야하기 때문에 O(N)의 시간 복잡도를 가진다.

2. 삽입, 삭제  
   **LinkedList > ArrayList**  
   ```LinkedList```는 이전, 다음 노드를 참조하는 상태만 변경하면 되기 떄문에 O(1)의 시간 복잡도를 가진다.  
   ```ArrayList```는 삽입, 삭제 이후 다른 데이터를 복사해야하기 때문에 O(N)의 시간 복잡도를 가진다.  
   다만 순차적으로 데이터를 추가, 삭제하는 경우에는 ```ArrayList```가 유리하다.

<br>

> 결론 : 데이터의 잦은 변경이 예상된다면 ```LinkedList``` 데이터의 개수가 변하지 않는다면 ```ArrayList```

<br>

***

<br>

## Set\<E>

중복된 값을 허용하지 않는 집합을 의미한다.

> 중복 혀용 X / 저장 순서 X

<br>

```Set``` 인터페이스를 구현한 클래스로는 ```HashSet```, ```TreeSet``` 등이 있다.

<br>

### 🔸 Set 인터페이스의 메서드

|기능|	리턴 타입|	메서드	|설명|
|:-:|:-:|:-|:-|
|객체<br>추가|	boolean|	add(Object o)|	주어진 객체를 추가하고, 성공하면 true<br>중복 객체면 false를 반환|
|객체<br>검색|	boolean|	contains(Object o)|	주어진 객체가 Set에 존재하는지 확인|
||boolean|	isEmpty()|	Set이 비어있는지 확인|
||Iterator|	Iterator()|	저장된 객체를 하나씩 읽어오는 반복자를 리턴|
||int|	size()|	저장되어 있는 전체 객체의 수를 리턴|
|객체<br>삭제|	void|	clear()|	Set에 저장되어져 있는 모든 객체를 삭제|
||boolean|	remove(Object o)|	주어진 객체를 삭제|

<br>

### **HashSet**

```Set``` 인터페이스를 구현한 가장 대표적인 컬렉션 클래스이다.

💡 HashSet 중복 값 판단 과정

1. ```add(Object o)```를 통해 객체를 저장
2. 저장하고자 하는 객체의 해시코드를 ```hashcode()``` 메서드를 통해 얻어냄
   
   > 해시코드 : hash 알고리즘에 의해 생성된 정수 값
   >
   > ```java
   > @Override
   > public int hashCode() {
   >     int hash = 7;
   >     hash = 31 * hash + (int) id;
   >     hash = 31 * hash + (name == null ? 0 : name.hashCode());
   >     hash = 31 * hash + (email == null ? 0 : email.hashCode());
   >     return hash;
   > }
   >```

3. Set이 저장하고 있는 모든 객체의 해시코드를 ```hashcode()``` 메서드를 통해 얻어냄
   
4. (2)에서 얻은 해시코드와 (3)에서 얻은 해시코드를 비교
   - 같은 해시코드가 존재하지 않으면 객체가 추가되며 ```add``` 메서드가 ```true```를 리턴
  
   - 같은 해시코드가 존재하면 ```equals()``` 메서드를 통해 객체를 비교
     - ```true```가 리턴된다면 중복으로 간주되어 추가되지 않으며 ```add``` 메서드가 ```false```를 리턴
  
     - ```false```가 리턴된다면 객체가 추가되며 ```add``` 메서드가 ```true```를 리턴

<br>

```java
HashSet<String> languages = new HashSet<>();

languages.add("Korean");
languages.add("English");
languages.add("Japaness");
languages.add("Korean");    // 중복

// 반복자 생성
Iterator<String> it = languages.iterator(); 

// 반복자를 통해 HashSet을 순회하며 요소 출력
while(it.hasNext()) {   
    System.out.println(it.next());
}
```

### 📋 [**_HashSet의 주요 Method_**](https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html)

<br>

### **TreeSet**

```TreeSet```은 이진 탐색 트리(Binary Search Tree) 형태로 데이터를 저장하며  
중복 허용 X 저장 순서X 라는 ```Set``` 인터페이스의 특징은 유지된다.

> 이진탐색 트리 :  
> 
> 하나의 부모 노드가 최대 2개의 자식 노드와 연결되는 이진 트리의 일종  
> 모든 왼쪽 자식이 루트(최상위 노드)나 부모보다 작고, 오른쪽 자식이 큰 값을 가짐  
> 정렬과 검색에 특화된 자료 구조

<br>

<img src = "https://media.geeksforgeeks.org/wp-content/uploads/BSTSearch.png"/>

▲ _이진 탐색 트리_

<br>

```java
TreeSet<String> workers = new TreeSet<>();

// TreeSet에 요소 추가
workers.add("Lee Java");
workers.add("Park Hacker");
workers.add("Kim Coding");

System.out.println(workers);
System.out.println(workers.first());
System.out.println(workers.last());
System.out.println(workers.higher("Lee"));
System.out.println(workers.subSet("Kim", "Park"));

// 출력
[Kim Coding, Lee Java, Park Hacker]
Kim Coding
Park Hacker
Lee Java
[Kim Coding, Lee Java]
```

▲ _TreeSet의 기본 정렬 방식이 오름차순이기 때문에 요소를 추가하면 자동으로 오름차순 정렬이 된다._

<br>

### 📋 [**_TreeSet의 주요 Method_**](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html)

<br>

***

<br>

## Map\<K,V>

**키(key)와 값(value)으로 구성된 Entry객체**를 저장한 구조이다.

> 키는 중복 허용 X / 값은 중복 허용 O  
>
> 만약 기존에 저장된 동일한 키에 값을 저장하면 **기존의 값이 새로운 값**으로 대치된다.

<br>

```Map``` 인터페이스를 구현한 클래스로는 ```HashMap```, ```HashTable```, ```TreeMap```, ```SortedMap``` 등이 있다.

<br>

### 🔸 Map 인터페이스의 메서드

|기능	|리턴 타입	|메서드|	설명|
|:-:|:-|:-|:-|
|객체<br>추가	|Object|	put(Object key,<br>Object value)|	주어진 키로 값을 저장한다. <br>해당 키가 새로운 키일 경우 null을 리턴하지만, <br>동일한 키가 있을 경우에는 기존의 값을 대체하고 대체되기 이전의 값을 리턴|
|객체<br>검색|	boolean	|containsKey(Object key)|	주어진 키가 있으면 true, 없으면 false를 리턴|
||boolean|	containsValue(Object value)|	주어진 값이 있으면 true, 없으면 false를 리턴|
||Set|	entrySet()|	키와 값의 쌍으로 구성된 모든 Map.Entry 객체를 Set에 담아서 리턴|
||Object|	get(Object key)|	주어진 키에 해당하는 값을 리턴|
||boolean|	isEmpty()|	컬렉션이 비어 있는지 확인|
||Set|	keySet()|	모든 키를 Set 객체에 담아서 리턴|
||int|	size()|	저장된 Entry 객체의 총 갯수를 리턴|
||Collection|	values()|	저장된 모든 값을 Collection에 담아서 리턴|
|객체<br>삭제|	void	|clear()|	모든 Map.Entry(키와 값)을 삭제|
||Object|	remove(Object key)|	주어진 키와 일치하는 Map.Entry를 삭제하고 값을 리턴|

<br>

### **HashMap**

```Map``` 인터페이스를 구현한 가장 대표적인 컬렉션 클래스이다.

이름 그대로 해싱(Hashing)을 사용하기 때문에 많은 양의 데이터를 검색하는데 있어서 뛰어난 성능을 보인다.

또한, ```HashMap```의 요소인 Entry 객체는 ```Map``` 인터페이스의 내부 인터페이스인 ```Entry``` 인터페이스를 구현한다.

<br>

🔸 **```Map.Entry``` 인터페이스의 메서드**

|리턴 타입|	메서드	|설명|
|:-|:-|:-|
|boolean|	equals(Object o)|	동일한 Entry 객체인지 비교|
|Object	|getKey()|	Entry 객체의 Key 객체를 반환|
|Object|	getValue()|	Entry 객체의 Value 객체를 반환|
|int|	hashCode()|	Entry 객체의 해시코드를 반환|
|Object	|setValue(Object value)|	Entry 객체의 Value 객체를 인자로 전달한 value 객체로 바꾼다.|

<br>

```java
// HashMap 생성
HashMap<String, Integer> hashMap = new HashMap<>();

// Entry 객체 저장
hashMap.put("짱구", 25);
hashMap.put("유리", 55);
hashMap.put("철수", 95);
hashMap.put("맹구", 75);

// 저장된 총 Entry 수 얻기
System.out.println(hashMap.size()); // 4

// 객체 찾기hash
System.out.println(hashMap.get("유리"));  // 55

// KeySet을 순회하면서 value 읽기 ==========
Iterator<String> keyIterator = keySet.iterator(); // Iterator 객체 생성

while(keyIterator.hasNext()) {
    String key = keyIterator.next();
    Integer value = hashMap.get(key);
    System.out.printf("key : %s, value : %d", key, value);
}

// values를 통해 평균 구하기 ==========
int avg = 0;

Collection values = hashMap.values();
Iterator<Integer> iterator = values.iterator(); // Iterator 객체 생성

while(iterator.hasNext()) {
    Integer num = iterator.next();
    avg += num.intValue();
}

System.out.println("평균 : " + (float) avg / values.size());

// EntrySet을 순회하면서 value 읽기 ==========
Set<Map.Entry<String,Integer>> entrySet = hashMap.entrySet(); // entrySet 객체 생성

Iterator<Map.Entry<String, Integer>> entryIterator = entrySet.iterator(); // Iterator 객체 생성

while(entryIterator.hasNext()) {
    Map.Entry<String, Integer> entry = entryIterator.next();
    String key = entry.getKey();  
    Integer value = entry.getValue();
    System.out.printf("key : %s, value : %d\n", key, value);
}

// 최대값 구하기 ==========
// 위에서 이미 한바퀴 돌아서 Iterator 재할당
Iterator<Map.Entry<String, Integer>> iterator1 = entrySet.iterator(); 

while(iterator1.hasNext()) {
    Map.Entry<String, Integer> entry = iterator1.next();
    if(entry.getValue() == Collection.max(values)) {
      System.out.println(entry.getKey()); // 철수
    }
}

// 객체 전체 삭제
hashMap.clear();
```

<br>

### 📋 [**_HashMap의 주요 Method_**](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html)

<br>

### **HashTable**

```HashTable```은 ```HashMap```과 내부 구조가 동일하며 ```HashMap```이 신버전이라고 생각하면 편하다.

<br>

위에서 본 ```ArrayList```와 ```Vector```의 차이처럼  
- ```HashMap```은 멀티스레드 환경에서 안전하지 않지만, 멀티스레드 환경이 아니라면 더 빠르다
- ```HashTable```은 반대로 멀티스레드 환경에서 안전하지만, 멀티스레드 환경이 아니라면 성능이 떨어진다.

<br>

***

_Update 2022.09.15._