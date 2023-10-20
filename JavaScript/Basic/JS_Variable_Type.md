# 변수 & 자료형

<br>

## 변수

변수를 선언하는 방법에는 `var`, `let`, `const`가 있다.

### 🔸 var

`var`를 사용해 변수를 선언하면, 해당 변수를 **다시 선언 할 수 있다.**

```js
var a = 10; // 10
a = 20;     // 20
a += 10;    // 30

var a = 10; // 10
```

<br>

### 🔸 let

`let`을 사용해 변수를 선언하면, 해당 변수를 **다시 선언 할 수 없다.**

```js
let a = 10; // 10
a = 20;     // 20
a += 10     // 30

let a = 10; // 불가능
```

<br>

### 🔸 const

`const`를 사용해 변수를 선언하면, 해당 변수를 **수정할 수 없다.**

```js
const a = 10;   // 10
a = 20;         // 불가능
a += 10;        // 불가능
```

<br>

### 🔸 선언, 초기화, 할당

JavaScript의 변수는 JS 실행 시 호이스팅되어 다음과 같이 사용할 수 있다.

```js
console.log(name);
var name = 'JWANNA';
```

이는 아래와 같은 의미이다.

```js
var name;
console.log(name);  // undefined
name = 'JWANNA';
```

변수 **선언은 호이스팅되어** 변수 자체는 사용이 가능하지만, 변수의 **할당은 호이스팅되지 않아** `undefined`를 출력한다.

> **💡 호이스팅 (Hoisting)**
>
> 스코프 내부 어디에서든 최상위에 선언된 것처럼 동작하는 것을 말한다.

<br>

`var`는 선언과 동시에 초기화가 발생하므로 `undefined`를 출력한다.

`let`은 선언, 초기화, 할당 순서로 동작하기 떄문에 선언만 되고, 초기화가 되지 않은 경우에는 사용할 수 없다.

```js
console.log(name);  // ReferenceError
let name = 'JWANNA';
```

`const`는 선언, 초기화, 할당이 동시에 일어나야하는 상수이므로 선언만 할 수 없다.

```js
const name;
name = 'male';  // Uncaught SyntaxError
```

<br>

### 🔸 스코프

스코프의 종류에는 **함수 스코프**와 **블록 스코프**가 있다.

`let`, `const`는 블록 스코프(block-scoped)이며, `var`는 함수 스코프(function-scoped)이다.

<br>

- **블록 스코프**  

  함수, if문, for문, while문, try/catch문 등 코드블럭 내부에서 사용한 변수는 해당 코드블럭의 지역변수로 설정된다.

  ```js
  if (age > 19) {
    let txt = 'Adult';
    console.log(txt); // 가능
  }

  console.log(txt); // 불가능
  ```

- **함수 스코프** 

  함수 내에서 선언한 변수만 해당 함수의 지역 변수로 설정된다.

  ```js
  if (age > 19) {
    var txt = 'Adult';
  }

  console.log(txt); // 가능
  ```

<br><br>

## 자료형

변수 선언 시 JavaScript는 **타입**을 자동으로 지정한다.

```js
const a = 10;
const b = 'JWANNA';
const c = false;
```

<br>

또한 형변환을 할 때는 `Number()`, `Boolean()`, `String()` 등의 함수를 사용할 수 있다.

<br>

### 🔸 Number

숫자형에서 주의할 점

```js
console.log(1 / 0);
// Infinify

console.log('name' / 2);
// NaN (Not a Number)

Number(null);
// 0

Number(undefined);
// NaN
```

<br>

### 🔸 Boolean

Boolean에서 주의점

형변환 시 `false`를 나타내는 결과는 아래와 같다.

```js
Boolean(0);
Boolean('');
Boolean(null);
Boolean(undefined);
Boolean(NaN);
```

이 외에는 모두 true로 형변환된다.

<br>

**💡 null & undefined**

`null`은 값이 없음을 나타내고,  
`undefined`는 값이 대입되지 않음을 나타낸다.

```js
let foo;
console.log(foo);   // undefined
```

<br>

### 🔸 String

String 표현 방법은 큰 따옴표`("")`, 작은 따옴표`('')`, 백틱`(``)`이 있다.

큰 따옴표와 작은 따옴표는 차이가 없으며, 백틱을 사용하면 `${}` 표현식을 통해 변수를 삽입할 수 있다.

예를 들어, html을 작성할 때는 작은 따옴표가 편하고, `let html = '<div class="box_title">'`  
영어 문장을 작성할 때는 큰 따옴표가 편하다. `let eng = "It's 3 o'clock"`  

또한 백틱은 여러 줄을 동시에 작업할 수 있지만, 따옴표를 통해 줄바꿈을 할 때는 `\n`을 포함시켜야한다.

```js
const firstName = "Hong";
const lastName = 'JeongWan';

const myName = `My Name is ${firstName + lastName}`;
console.log(myName);
// My Name is HongJeongWan

const a = "나는 ";
const b = '살 입니다.';
const age = 20;

const myAge = `${a + (age + 7) + b}`;
console.log(myAge);
// 나는 27살 입니다.

console.log(a + age + b);
// 나는 20살 입니다.
```
▲ _자동 형변환 가능_

<br><br>

## 문자열 메소드 (String Method)

- `length` : 문자열의 길이를 나타낸다.

- `str[n]` : 문자열의 특정 위치에 있는 문자를 나타낸다.

- `str.toUpperCase()`, `str.toLowerCase()` : 모두 대문자, 모두 소문자로 바꾼다.

- `str.indexOf(text)` : str에서 text가 위치한 인덱스를 반환한다.

- `str.include(text)` : 문자가 포함되어 있으면 true, 아니면 false

- `str.slice(n, m)` : n부터 m - 1까지 문자열을 슬라이스한다. (m을 생략하면 끝까지, m이 음수이면 뒤에서부터 센다.)

- `str.substring(n, m)` : n과 m 사이 문자열 반환. n과 m의 위치가 바뀌어도 동일하게 동작 (음수 인식 X)

- `str.substr(n, m)` : n부터 시작해서 m개를 가져온다.

- `str.trim()` : 앞 뒤 공백을 제거한다.

- `str.repeat(n)` : 문자열을 n번 반복한다.

- `str.split(text)` : 문자열을 text 기준으로 구분하여 배열로 만든다.

- `str.replace(regex, text)` : 정규표현식을 통해 문자를 찾은뒤 text로 교체한다.

- `str.replaceAll(a, b)` : 문자 내 모든 a를 찾아 b로 교체한다.

<br><br>

---

_2023.10.13. Update_