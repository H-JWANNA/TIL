# JavaScript 중급

<br>

## 변수

변수를 선언하는 방법에는 `var`, `let`, `const`가 있다.

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

<br>

## 생성자 함수

비슷한 객체 리터럴을 생성자를 통해 만들 수 있다.

```js
function User(name, age) {
    this.name = name;
    this.age = age;
    this.sayName = function() {
        console.log(this.name);
    }
}

let user1 = new User('JWANNA', 27);
user1.sayName(); // JWANNA
```

`new` 키워드를 사용하지 않으면 return이 없는 함수를 실행하기 때문에 `undefined`를 할당받는다.

<br>

## 계산된 프로퍼티 (Computed Property)

미리 할당된 변수나 직접 식을 입력해서 객체 변수를 할당하는 방식

```js
let a = 'age';

const user = {
    name: 'JWANNA',
    [a]: 27
}
// {name: "JWANNA", age: 27}

const user2 = {
    [1 + 4]: 5,
    ["안녕"+"하세요"]: "Hello"
}
// {5: 5, 안녕하세요: "Hello"}
```

<br>

응용하면 함수에도 사용할 수 있다.

```js
function makeObj(key, val) {
    return {
        [key]: val
    }
}

const obj = makeObj("이름", "JWANNA");
```

<br>

## 객체 메소드 (Object Method)

객체 메소드는 여러가지가 있다.

<br>

### 🔸 Object.assign()

객체를 복제하는 메소드

<br>

```js
const user = {
    name: 'JWANNA',
    age: 27
}

const newUser = user;
```

위와 같이 객체를 복제할 경우, 얕은 복사가 일어나서 newUser의 name 프로퍼티를 변경하는 경우에 user 객체의 프로퍼티 값에도 변화가 일어난다.

<br>

```js
const user = {
    name: 'JWANNA',
    age: 27
}

const newUser = Object.assign({}, user);
```

위와 같이 작성하면 빈 객체에 user 객체를 병합시킨다.

빈 객체에 user 객체가 가지고 있는 **프로퍼티가 있는 경우 덮어쓰기**가 된다.

**프로퍼티가 없는 경우**에는 해당 프로퍼티가 **추가된 객체**가 생성된다.

<br>

추가로, 2개 이상의 객체로 합칠 수 있다.

```js
const user = {
    name: 'JWANNA'
}

const info1 = {
    age: 27
}

const info2 = {
    gender: 'Male'
}

Object.assign(user, info1, info2);

console.log(user);
// { name: 'JWANNA', age: 27, gender: 'Male' }
```

<br>

### 🔸 Object.keys()

객체 프로퍼티의 키들을 배열로 만들어 반환한다.

```js
const user = {
    name: "JWANNA",
    age: 27
}

Object.keys(user);
// ["name", "age"]
```

<br>

### 🔸 Object.values()

객체 프로퍼티의 값들을 배열로 만들어 반환한다.

```js
const user = {
    name: "JWANNA",
    age: 27
}

Object.values(user);
// ["JWANNA", 27]
```

<br>

### 🔸 Object.entries()

객체 프로퍼티의 키, 값 쌍을 배열로 만들어 반환한다.

```js
const user = {
    name: "JWANNA",
    age: 27
}

Object.entries(user);
/*
    [
        ["name", "JWANNA"],
        ["age", 27]
    ]
*/
```

<br>

### 🔸 Object.fromEntires()

키, 값 배열을 객체로 만들어 반환한다.

```js
const arr = [
    ["name", "JWANNA"],
    ["age", 27]
]

Object.fromEntries(arr);
/*
    {
        name: "JWANNA",
        age: 27
    }
*/
```

<br>

## 심볼 (Symbol)

유일한 식별자를 만들 때 사용 (유일성 보장)

```js
const a = Symbol();
const b = Symbol();

console.log(a);
// Symbol()
console.log(b);
// Symbol()

console.log(a === b);
// false
```

<br>

### 🔸 Symbol

심볼에는 이름을 부여할 수 있다.

```js
const symbolId = Symbol('id');

const user = {
    name: 'JWANNA',
    age: 27,
    [symbolId]: 'myId'
}

console.log(user);
// { name: 'JWANNA', age: 27, [Symbol(id)]: 'myId' }

console.log(user[symbolId]);
// myId
```

위처럼 Symbol 프로퍼티가 잘 표현되는 것으로 보이지만,  
Object 메소드인 `keys`, `values`, `entries`, `for … in` 등을 사용할 때 Symbol이 나타나지 않는다.

**이러한 특성 때문에 기존의 작업에 영향이 가지 않게 하면서 프로퍼티를 추가하고 싶을 때 주로 사용한다.**

> Symbol만 보기 위해서는 `Object.getOwnPropertySymbols()` 메소드를 사용할 수 있다.
> 
> 또한 `Reflect.ownKeys()` 메소드를 통해 심볼형 키를 포함한 모든 키를 배열로 나타낼 수 있다.

<br>

### 🔸 Symbol.for()

전역 심볼을 나타낼 때 사용한다.

해당 심볼이 없으면 만들고 있으면 가져오기 떄문에 하나의 심볼만 보장받을 수 있다.

```js
const id1 = Symbol.for('id');
const id2 = Symbol.for('id');

console.log(id1 === id2);
// true
```

<br>

전역 심볼의 이름을 알고 싶다면 `keyFor()`를 사용할 수 있다.  

만약 전역 심볼이 아니라면 `description`을 사용하면 된다.

```js
const id1 = Symbol.for('id');
Symbol.keyFor(id1); // id

const id2 = Symbol('id2');
id2.description;    // id2
```

<br>

## Number, Math

### 🔸 진수 변환법

`toString()`을 사용하여 숫자의 진수를 변환하여 나타낼 수 있다.

```js
let num = 10;
num.toString();     // "10"
num.toString(2);    // "1010"
```

<br>

### 🔸 Math

- `Math.PI` : 원주율 출력

- `Math.ceil(n)` : 소수점 올림

- `Math.floor(n)` : 소수점 내림

- `Math.round(n)` : 반올림

- `Math.random()` : 0 ~ 1 사이의 무작위 수 생성

- `Math.max(n, m, o)`, `Math.min()` : 괄호 안의 수 중 최소, 최대값

- `Math.abs(n)` : 절대값

- `Math.pow(n, m)` : n의 m 거듭 제곱

- `Math.sqrt(n)` : 제곱근

<br>

### 🔸 Number

- `num.toFixed(n)` : 소수점 자리 설정 (단, 리턴이 String)

- `isNaN(num)` : NaN인지 판단 (`NaN === NaN`도 false이므로 `isNaN`을 사용해야한다.)

- `parseInt()` : 문자열을 10진수로 바꿔준다. 시작하는 부분부터 정수 이외의 문자가 나올 때까지 진행한다.

  ```js
  let margin = '10px';

  parseInt(margin); // 10
  Number(margin);   // NaN

  let redColor = 'f3';
  parseInt(redColor);       // NaN
  parseInt(redColor, 16);   // 243

  parseInt('11', 2) // 3
  ```

- `parseFloat()` : parseInt와 다르게 소수점 이하도 반환

<br>

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

<br>

## 배열 메소드 (Array Method)

- `arr.splice(n, m)` : 배열의 n번째 요소부터 m개를 지우고 삭제된 요소를 반환한다.

- `arr.splice(n, m, x, ...)` : 배열의 n번째 요소부터 m개를 지우고, 그 자리에 x부터 차례대로 넣는다.

- `arr.slice(n, m)` : n부터 m - 1까지 반환한다.

- `arr.concat(arr2, arr3, ...)` : 배열을 합쳐서 새 배열을 반환한다.

- `arr.forEach(fn)` : 배열을 반복한다. (파이썬의 enumerate와 비슷한 느낌?)

  ```js
  let arr = ['Hong', 'Kim', 'Lee'];

  arr.forEach((name, index) => {
    console.log(`${index + 1}. ${name}`);
  });
  /*
    1. Hong
    2. Kim
    3. Lee
  */
  ```

- `arr.indexOf(n, m)`, `arr.lastIndexOf(n)` : n을 m번째 위치부터 탐색한다. (m 생략 가능)

- `arr.includes()` : 배열 내에 인수를 포함하고 있는지 확인

- `arr.find(fn)` : include처럼 찾는다는 의미는 비슷하지만 복잡한 연산 가능 (단, true가 나오면 멈추고 없으면 undefined)

  ```js
  let userList = [
    { name: "Hong", age: 27 },
    { name: "Kim", age: 15 },
    { name: "Lee", age: 17 },
  ]

  userList.find((user) => {
    if(user.age < 19) {
        return true;
    }
    return false;
  })

  console.log(result);  // { name: 'Kim', age: 15 }
  ```

- `arr.findIndex(fn)` : indexOf처럼 찾는다는 의미는 비슷하지만 복잡한 연산 가능 (있으면 index, 없으면 -1)

  ```js
  let userList = [
    { name: "Hong", age: 27 },
    { name: "Kim", age: 15 },
    { name: "Lee", age: 17 },
  ]

  userList.findIndex((user) => {
    if(user.age < 19) {
        return true;
    }
    return false;
  })

  console.log(result);  // 1
  ```

- `arr.filter(fn)` : 조건에 만족하는 모든 요소를 배열로 반환

  ```js
  let userList = [
    { name: "Hong", age: 27 },
    { name: "Kim", age: 15 },
    { name: "Lee", age: 17 },
  ]

  userList.filter((user) => {
    if(user.age < 19) {
        return true;
    }
    return false;
  })

  console.log(result);  // [ { name: 'Kim', age: 15 }, { name: 'Lee', age: 17 } ]
  ```

- `arr.reverse()` : 배열을 역순으로 재정렬

- `arr.map(fn)` : 함수를 받아 특정 기능을 실행하고, 새로운 배열을 반환

  ```js
  let userList = [
    { name: "Hong", age: 27 },
    { name: "Kim", age: 15 },
    { name: "Lee", age: 17 },
  ]

  let newUserList = userList.map((user, index) => {
    return Object.assign({}, user, {isAdult: user.age > 19})
  })

  console.log(newUserList);
  /*
  [
    { name: 'Hong', age: 27, isAdult: true },
    { name: 'Kim', age: 15, isAdult: false },
    { name: 'Lee', age: 17, isAdult: false }
  ]
  */
  ```

- `arr.join(text)` : 배열을 합성해서 문자열 생성. text가 없을 시 기본으로 쉼표(,)를 사용해 조인

- `Array.isArray()` : JS에서 배열은 객체이므로 `typeof` 사용 시 객체로 판단되어 isArray를 사용해 구분한다.

- `arr.sort(fn)` : 배열을 문자 기준으로 정렬한다. 정렬 기준을 함수로 전달할 수 있다. 

  > `Lodash` 라이브러리를 사용하면 `_.sortBy(arr);`를 사용하여 쉽게 정렬할 수 있다.

- `arr.reduce()` : 인수로 함수를 받아서 계산한다. `(누적 계산값, 현재값) => { return 계산값 }`

  ```js
  let userList = [
    { name: "Hong", age: 27 },
    { name: "Kim", age: 15 },
    { name: "Lee", age: 17 },
    { name: "Park", age: 30 },
    { name: "Choi", age: 42 }
  ]

  let result = userList.reduce((prev, cur) => {
    if (cur.age > 19) {
        prev.push(cur.name);
    }
    return prev;
  }, []);

  console.log(prev);    // [ 'Hong', 'Park', 'Choi' ]
  ```

<br>

## 구조 분해 할당 (Destructuring assignment)

구조 분해 할당 구문은 배열이나 객체의 속성을 분해해서 그 값을 변수에 담을 수 있게하는 표현식을 말한다.

<br>

### 🔸 배열 구조 분해

```js
let users = ['Hong', 'Kim'];
let [user1, user2, user3] = users;

console.log(user1); // Hong
console.log(user2); // Kim
console.log(user3); // undefined
```

위처럼 할당되지 않은 변수에는 undefined가 들어간다.

이를 방지하기 위해 기본 값을 설정할 수 있다.

```js
let [a=3, ,b=4, c=5] = [1, 2, 3];

console.log(a); // 1
console.log(b); // 3
console.log(c); // 5
```

중간에 빈 값을 주면 할당되는 변수가 없으므로 건너뛸 수도 있다.

<br>

### 🔸 객체 구조 분해

```js
let user = {name: 'Hong', age: 27};
let {age, name, gender = 'Male'} = user;

console.log(name);  // Hong
console.log(age);   // 27
console.log(gender);   // Male
```

프로퍼티의 순서를 바꿔도 사용할 수 있다.

<br>

## 나머지 매개변수 (Rest parameters)

JavaScript에서 함수에 넘겨주는 인수의 갯수는 제약이 없다.

```js
function showName(name) {
    console.log(name);
}

showName('Hong'); // Hong
showName('Hong', 'Kim'); // Hong
showName(); // undefined
```

함수의 인자에 접근하는 방법에는 **arguments**를 통한 방식과 **나머지 매개변수**를 통한 방식이 있다.

<br>

### 🔸 arguments

arguments는 함수로 넘어온 모든 인수에 접근할 수 있는 함수 내에서 이용 가능한 지역변수이다.

`length`와 `index` 등의 메소드를 사용할 수 있는 Array 형태의 객체이지만, 배열의 내장 메소드를 사용할 수 없다.

```js
function showName(name) {
    console.log(arguments.length);
    console.log(arguments[0]);
    console.log(arguments[1]);
}

showName('Hong', 'Kim');
/*
    2
    Hong
    Kim
*/
```

<br>

### 🔸 나머지 매개변수

나머지 매개변수는 정해지지 않은 갯수의 인수를 배열로 나타낼 수 있게 한다.

```js
function showName(...names) {
    console.log(names);
}

showName(); // []
showName('Hong'); // [ 'Hong' ]
showName('Hong', 'Kim'); // [ 'Hong', 'Kim' ]
```

<br>

```js
function User(name, age, ...skills) {
    this.name = name;
    this.age = age;
    this.skills = skills;
}

const user1 = new User('Hong', 27, 'java', 'spring');
const user2 = new User('Park', 19, 'html', 'css');
const user3 = new User('Choi', 30, 'English');
```

<br>

## 전개 구문 (Spread syntax)

나머지 매개변수를 반대로 풀어서 작성하는 구문이다.

### 🔸 배열

```js
let arr1 = [1, 2, 3];
let arr2 = [4, 5, 6];

let result = [0, ...arr1, ...arr2, 7];

console.log(result);    // [0, 1, 2, 3, 4, 5, 6, 7]
```

<br>

### 🔸 객체

```js
let user = { name: 'Hong' };
let info = { age: 27 };
let be = ['java', 'spring'];
let lang = ['ko', 'en'];

const user = {
    ...user,
    ...info,
    skills: [...be, ...lang]
}

console.log(user);
// { name: 'Hong', age: 27, skill: ['java', 'spring', 'ko', 'en'] }
```

<br>

## 클로저 (Closure)

JavaScript는 어휘적 환경(Lexical Environment)을 갖는다.

함수와 렉시컬 환경의 조합으로 함수가 생성될 당시의 외부 변수를 기억하고, 생성 이후에도 계속 접근 가능하게 한다.

```js
function makeAdd(x) {
    return function(y) {
        return x + y;
    }
}

const add3 = makeAdd(3);
console.log(add3(2));   // 5
```

<br>

```js
function makeCounter() {
    // 은닉화
    let num = 0;

    return function() {
        return num++;
    }
}

let counter = makeCounter();

console.log(counter()); // 0
console.log(counter()); // 1
console.log(counter()); // 2
```

<br>

## setTimeout / setInterval

`setTimeout`은 일정 시간이 지난 후 함수를 실행하고, `setInterval`은 일정 시간 간격으로 함수를 반복한다.

<br>

### 🔸 setTimeout

`setTimeout(함수, 시간, 인수)`로 나타낸다. 리턴값은 timeId

`clearTimeout(tId)`을 사용해서 지정된 스케쥴링을 취소할 수 있다.

```js
function showName(name) {
    console.log(name);
}

count timeId = setTimeout(showName, 3000, 'Hong');
// 3초 뒤 Hong 출력

clearTimeout(timeId);   // 스케쥴링 취소
```

<br>

### 🔸 setInterval

`setInterval(함수, 시간, 인수)`로 나타낸다. 리턴값은 timeId

`clearInterval(tId)`을 사용해서 지정된 스케쥴링을 취소할 수 있다.

```js
function showName(name) {
    console.log(name);
}

count timeId = setInterval(showName, 3000, 'Hong');
// 3초마다 Hong 출력

clearInterval(timeId);   // 스케쥴링 취소
```

<br>

> **❗️ 주의사항**
> 
> delay를 0으로 설정해도 바로 실행되지 않는다.
> 
> 현재 실행중인 스크립트가 종료된 이후 스케쥴링 함수를 실행하기 때문이다.
> 
> 또한 브라우저는 기본적으로 4ms 정도의 딜레이 시간이 있어 그 이상의 시간이 걸릴 수 있다.

<br>

## call, apply, bind

<br>

## 상속, 프로토타입 (Prototype)

<br>

## 클래스 (Class)

<br>

## 프로미스 (Promise)

<br>

## async, await

<br>

## Generator

<br><br>

---

_2023.10.13. Update_