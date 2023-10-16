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

<br><br>

## 생성자 함수

비슷한 객체 리터럴을 생성자를 통해 만들 수 있다.

생성자를 통해 객체를 만들때 해당 객체를 인스턴스라고 한다.

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

```js
console.log(user1 instanceof User); // true
console.log(user1.constructor === User); // true
```

생성자를 통해 만들어진 객체가 해당 생성자의 인스턴스인지, 해당 인스턴스의 생성자가 맞는지 확인할 수 있다.

<br><br>

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

<br><br>

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

<br><br>

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

<br><br>

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

<br><br>

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

<br><br>

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

<br><br>

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

<br><br>

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

이를 활용하면 Java의 Getter처럼 사용할 수 있다.

```js
const User = function(name) {
    const n = name;
    this.getName = function() {
        console.log(n);
    }
}

const hong = new User('Hong');
```

위처럼 작성하면 해당 객체의 프로퍼티를 수정할 수 없고, 가져오기만 가능하다.

<br><br>

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

<br><br>

## call, apply, bind

함수 호출 방식과 관계없이 this를 지정할 수 있다.

<br>

### 🔸 call

call 메소드는 모든 함수에서 사용할 수 있으며, **this를 특정 값으로 지정**할 수 있다.

첫번째 파라미터로 this를 지정할 객체를 받으며, 두번째부터 함수의 파라미터를 받는다.

```js
const hong = {
    name: 'Hong'
}

function showName() {
    console.log(this.name);
}

showName();
// undefined (this가 window)

showName.call(hong);
// Hong (this를 hong 객체로 지정)
```

<br>

call 메소드를 사용해서 객체 프로퍼티의 추가도 가능하다.

```js
const hong = {
    name: 'Hong'
}

function update(birthYear, occupation) {
    this.birthYear = birthYear;
    this.occupation = occupation;
}

update.call(hong, 1997, 'Programmer');

console.log(hong);
// { name: 'Hong', birthYear: 1997, occupation: 'Programmer' }
```

<br>

### 🔸 apply

apply 메소드는 함수 매개변수 처리 방법을 제외하면 call과 완전히 같다.

apply는 call과 다르게 **배열의 형태로 파라미터를 전달**받는다.

```js
const kim = {
    name: 'Kim'
}

function update(birthYear, occupation) {
    this.birthYear = birthYear;
    this.occupation = occupation;
}

update.apply(kim, [1995, 'Architecture']);

console.log(kim);
// { name: 'Kim', birthYear: 1995, occupation: 'Architecture' }
```

<br>

### 🔸 bind

bind 메소드는 함수의 **this 값을 영구히** 바꿀 수 있다.

```js
const user = {
    name: 'Hong',
    showName: function() {
        console.log(`hello, ${this.name}`);
    }
}

user.showName(); // hello, Hong

let fn = user.showName;

fn(); // hello, undefined
fn.call(user); // hello, Hong

let boundFn = fn.bind(user);
boundFn(); // hello, Hong
```

`fn` 변수에 showName을 할당하면서 this를 잃어버려 fn만 호출할 때에는 this를 찾지 못한다.

위와 같은 상황에 `bind`를 통해 this 값에 대한 객체를 할당해주면 this를 찾을 수 있다.

<br><br>

## 상속, 프로토타입 (Prototype)

프로토타입은 상속을 통해 상위 객체의 프로퍼티를 사용하거나 오버라이딩할 수 있게 한다.

```js
const car = {
    wheels: 4
}

const bmw = {
    navigation: 1,
    name: 'bmw'
}

const x5 = {
    color: 'white',
    name: 'x5'
}

bmw.__proto__ = car;
x5.__proto__ = bmw;
```

위처럼 `__proto__`를 사용해서 특정 객체의 프로토타입을 지정할 수 있다.

<br>

```js
console.log(x5);
// { color: 'white', name: 'x5' }

console.log(x5.wheels);
// 4
```

객체의 프로퍼티에서 찾을 수 없다면, 프로토타입을 통해 해당 프로퍼티가 존재하는지 확인한다.

<br>

```js
for(p in x5) {
    if(x5.hasOwnProperty(p)) {
        console.log('o', p);
    } else {
        console.log('x', p);
    }
}

/*
    o color
    o name
    x navigation
    x wheels
*/
```

`for in` 문을 사용하면 프로토타입의 프로퍼티도 모두 가져오게 된다.

이 때, `hasOwnProperty`를 사용해서 해당 객체의 프로퍼티만 선별할 수 있다.

<br>

### 🔸 생성자 프로토타입

생성자 함수에도 프로토타입을 사용해서 중복 코드를 줄일 수 있다.

```js
const Bmw = function(color) {
    this.color = color;
}

Bmw.prototype.wheels = 4;
Bmw.prototype.drive = function() {
    console.log('drive');
}

const x5 = new Bmw('red');
```

생성자 함수에는 `prototype` 메소드를 사용해서 프로토타입을 설정할 수 있다.

<br>

단, 아래와 같이 프로토타입을 작성할 경우에는 문제가 발생한다.

```js
const Bmw = function(color) {
    this.color = color;
}

Bmw.prototype = {
    wheels = 4,
    drive = function() {
        console.log('drive');
    }
}

const x5 = new Bmw('red');

console.log(x5 instanceof Bmw); // true
console.log(x5.constructor === Bmw); // false
```

해당 인스턴스의 constructor가 false가 나오는 문제점이 생기기 때문에 프로토타입을 하나씩 추가하거나,  
아래처럼 수동으로 constructor 프로퍼티를 추가해줄 수 있다.

```js
Bmw.prototype = {
    constructor: Bmw,
    wheels = 4,
    ...
}
```

<br><br>

## 클래스 (Class)

ES6에 추가된 스펙으로 Java의 클래스처럼 객체를 만들 수 있다.

<br>

### 🔸 생성자

```js
const User = function(name) {
    this.name = name;
    this.showName = function() {
        console.log(this.name);
    }
}
```
▲ _기존 생성자 함수_


```js
class User {
    constructor(name) {
        this.name = name;
    }

    showName() {
        console.log(this.name);
    }
}
```
▲ _클래스_

<br>

클래스로 인스턴스를 생성하게 되면 생성자 외부의 메소드들은 프로토타입으로 들어가게 된다.

단, `for in` 문을 통해서도 프로토타입의 메소드를 출력하지 않는다.

또한, `new` 키워드를 사용하지 않은 생성자 함수는 `undefined`를 출력하지만, 클래스 생성자는 **에러를 발생**시킨다.

<br>

### 🔸 상속

클래스의 상속에는 `extends` 키워드를 사용한다.

부모의 프로퍼티에 접근하기 위해서는 `super` 키워드를 사용할 수 있으며, 메소드 오버라이딩을 통해 재정의가 가능하다.

```js
class Car {
    constructor(color) {
        this.color = color;
        this.wheels = 4;
    }

    drive() {
        console.log('drive');
    }
}

class Bmw extends Car {
    constructor(color) {
        super(color);
        this.navigation = 1;
    }

    park() {
        console.log('park');
    }
}

const x5 = new Bmw('black');

console.log(x5);
// Bmw { color: 'black', wheels: 4, navigation: 1 }
```

<br><br>

## 프로미스 (Promise)

프로미스는 Javascript **비동기 처리에 사용되는 객체**이며, 주로 서버에서 받아온 데이터를 화면에 표시할 때 사용한다.

프로미스는 다음과 같이 나타낼 수 있다.

```js
const pr = new Promise((resolve, reject) => {
    setTimeout(() => {
        // resolve('OK')
        reject(new Error("error"))
    }, 1000)
})

console.log('---start---');

pr.then(result => {
    console.log(result)
}).catch(err => {
    console.log(err)
}).finally(() => {
    console.log('---finally---');
})
```

- `resolve` : 요청이 성공했을 때, 보내주는 값을 의미한다.

- `reject` : 요청이 실패했을 때, 보내주는 값을 의미한다.

- `then` : 요청이 성공했을 때, 실행되는 함수를 의미한다.

- `catch` : 요청이 실패했을 때, 실행되는 함수를 의미한다.

- `finally` : 요청의 성공 여부와 관계없이 마지막에 실행되는 함수를 의미한다.

<br>

### 🔸 프로미스 체이닝(Promises chaining)

기존의 콜백 함수를 사용하여 함수를 나타내면 아래와 같다.

```js
const f1 = (callback) => {
    setTimeout(function() {
        console.log('1번');
        callback();
    }, 1000)
}

const f2 = (callback) => {
    setTimeout(function() {
        console.log('2번');
        callback();
    }, 2000)
}

const f3 = (callback) => {
    setTimeout(function() {
        console.log('3번');
        callback();
    }, 3000)
}

console.log('시작');
f1(function() {
    f2(function() {
        f3(function(){
            console.log('끝');
        })
    })
})
```

이처럼 뎁스가 깊어지면서 콜백을 계속 호출하는 것을 콜백 지옥(Callback Hell)이라고 한다.

<br>

이를 프로미스를 통해 나타내면 아래와 같다.

```js
const f1 = () => {
    return new Promise((res, rej) => {
        setTimeout(() => {
            res('1번');
        }, 1000)
    })
}

const f2 = (msg) => {
    console.log(msg);

    return new Promise((res, rej) => {
        setTimeout(() => {
            res('2번');
        }, 2000)
    })
}

const f3 = (msg) => {
    console.log(msg);

    return new Promise((res, rej) => {
        setTimeout(() => {
            res('3번');
        }, 3000)
    })
}

console.log('시작');
f1()
    .then((res) => f2(res))
    .then((res) => f3(res))
    .then((res) => console.log(res))
    .catch((err) => console.log(err))
    .finally(() => console.log('끝'));
```

<br>

### 🔸 Promise.all()

위와 같이 나타낼 경우 1초, 2초, 3초를 기다려 총 6초의 시간이 걸리게 된다.

모든 작업이 비동기로 동시에 시작된다면 가장 오래 걸리는 작업의 시간인 3초에 작업이 완료될 것이다.

```js
Promise.all(
    [f1(), f2(), f3()]
).then((res) => {
    console.log(res);
})
// [ '1번', '2번', '3번' ]
```

`Promise.all()`을 사용하면 모든 작업을 동시에 시작해서, 모든 작업이 완료되는 순간 배열로 나타낸다.

단, 하나라도 `reject`를 반환할 경우에는 데이터를 하나도 반환하지 않는다. (All or Nothing)

<br>

### 🔸 Promise.race()

```js
Promise.race(
    [f1(), f2(), f3()]
).then((res) => {
    console.log(res);
})
/*
    1번
*/
```

`Promise.race()`를 사용하면 가장 먼저 완료된 작업만 보여준다.

만약 2번 작업이 `reject`를 반환할 경우에도 1번이 먼저 완료되었으므로 2번 작업은 무시된다.

<br>

### 🔸 Promise.any()

```js
const rejPromise = new Promise((res, rej) => {
    setTimeout(() => {
        rej("fail");
    }, 1000)
})

const resPromise = new Promise((res, rej) => {
    setTimeout(() => {
        res("success");
    }, 2000)
})

Promise.any(
    [rejPromise, resPromise]
).then((res) => {
    console.log(res);
}).catch(e => console.log(e));

// success
```

`Promise.race()`를 사용하면 위의 결과는 가장 먼저 완료된 작업의 결과만 보여주므로 **fail**이 나올 것이다.

반면에 `Promise.any()`를 사용하면 전달된 프로미스 중 가장 먼저 이행된 객체를 반환한다.

모두 거부가 된다면 모든 프로미스가 거부가 되었다는 메시지를 출력한다.

<br><br>

## async, await

`async`, `await`를 사용하면 프로미스를 더 편리하게 사용할 수 있다.

<br>

### 🔸 async

`async` 키워드를 함수 앞에 붙이면 함수는 항상 `Promise` 객체를 반환한다.

그러므로 `then`, `catch`와 같은 키워드를 사용할 수 있다.

```js
async function getName() {
    return 'Hong';
}

getName().then((name) => {
    console.log(name); // Hong
})
```

<br>

만약 반환값이 `Promise`라면 해당 값을 그대로 사용한다.

```js
async function getName() {
    return Promise.resolve('Kim');
}

getName().then((name) => {
    console.log(name); // Kim
})
```

<br>

함수 내부에서 예외가 발생하면 `reject` 상태의 프로미스가 반환된다.

```js
async function getName() {
    throw new Error('err');
}

getName().catch((err) => {
    console.log(err);
})
```

<br>

### 🔸 await

`await` 키워드는 `async` 함수 내에서만 사용할 수 있다.

```js
function getName(name) {
    return new Promise((res, rej) => {
        setTimeout(() => {
            res(name);
        }, 1000)
    })
}

async function showName() {
    const result = await getName('Hong');
    console.log(result);
}

console.log('시작');
showName();
```

`await` 키워드 오른쪽에는 프로미스가 오고, 해당 프로미스가 처리될 때까지 기다린다.

<br>

아래와 같이 `async`, `await`를 사용하면 가독성있는 코드를 작성할 수 있다.

```js
f1()
    .then((res) => f2(res))
    .then((res) => f3(res))
    .then((res) => console.log(res))
    .catch((err) => console.log(err))
    .finally(() => console.log('끝'));

// 위 아래는 동일의미의 코드이다.

async function order() {
    try {
        const result1 = await f1();
        const result2 = await f2(result1);
        const result3 = await f3(result2);
        console.log(result3);
    } catch(e) {
        console.log(e);
    }
    console.log('끝');
}

order();
```

<br><br>

## Generator

함수의 실행을 중간에 멈췄다가 재개할 수 있는 기능

```js
function* fn() {
    yield 1;
    yield 2;
    yield 3;
    return 'finish';
}

const a = fn();
```

Generator는 function 옆에 `*` 키워드를 붙여 선언하고, 내부에 `yield` 키워드를 사용하여 함수 실행을 멈출 수 있다.

또한 yield 키워드를 통해 값을 입력받아 변수로 할당할 수 있다.

```js
function* fn() {
    const num1 = yield '첫번째 숫자를 입력하세요.';
    console.log(num1);

    const num2 = yield '두번째 숫자를 입력하세요.';
    console.log(num2);

    return num1 + num2;
}
```

<br>

### 🔸 Generator Method

- `next()` : 다음 `yield`까지 함수 실행을 진행한다.

    ```js
    function* fn() {
        console.log('1번');
        yield 1;
        console.log('2번');
        yield 2;
        return 'finish';
    }

    const a = fn();

    console.log(a);
    // Object [Generator] {}

    console.log(a.next());
    // 1번
    // { value: 1, done: false }

    console.log(a.next());
    // 2번
    // { value: 2, done: false }

    console.log(a.next());
    // { value: 'finish', done: true }

    console.log(a.next());
    // { value: undefined, done: true }
    ```

    `value`는 yield의 값 또는 리턴 값을 의미하며, `done`은 함수가 완료되었는지를 반환한다.

<br>

- `return()` : 메소드를 호출하는 즉시 Generator 함수를 종료한다.

    ```js
    const a = fn();

    console.log(a.next());
    // 1번
    // { value: 1, done: false }

    console.log(a.return('End'));
    // { value: 'End', done: true }

    console.log(a.next());
    // { value: undefined, done: true }
    ```

<br>

- `throw()` : 메소드를 호출하는 즉시 에러를 반환하며 Generator 함수를 종료한다.

    ```js
    function* fn() {
        try {
            console.log('1번');
            yield 1;
            console.log('2번');
            yield 2;
            return 'finish';
        } catch(e) {
            console.log(e);
        }
    }

    const a = fn();

    console.log(a.next());
    // 1번
    // { value: 1, done: false }

    console.log(a.throw(new Error('err')));
    // 에러 발생

    console.log(a.next());
    // { value: undefined, done: true }
    ```

<br>

### 🔸 Iterator

Generator는 반복이 가능한 Iterable이다. 

그러므로 `for of` 문을 사용하여 done이 true가 될 때까지 반복할 수 있다.

```js
a[Symbol.iterator]() === a;
// true
```

<br>

`Iterable`

- `Symbol.iterator` 메소드가 있다.

- `Symbol.iterator`는 `iterator`를 반환해야한다.

`Iterator`

- `next` 메소드를 가진다.

- `next` 메소드는 `value`, `done` 속성을 가진 객체를 반환한다.

- 작업이 끝나면 `done`은 `true`가 된다.

<br>

Iterable 객체에는 **문자열, 배열, Generator** 등이 있다.

<br>

반복가능한 객체를 활용하면 아래와 같은 표현이 가능하다.

```js
function* gen1() {
    yield 'W';
    yield 'o';
    yield 'r';
    yield 'l';
    yield 'd';
}

function* gen2() {
    yield 'Hello,';
    yield* gen1();
    yield '!';
}

// 구조 분해 할당을 사용하면 done이 true가 될 때까지 값을 표현한다.
console.log(...gen2()); // Hello, W o r l d !
```

`gen1()` 자리는 현재는 Generator가 들어가있지만, 반복가능한 모든 객체가 들어갈 수 있다.

<br><br>

---

_2023.10.17. Update_

_2023.10.16. Update_

_2023.10.13. Update_