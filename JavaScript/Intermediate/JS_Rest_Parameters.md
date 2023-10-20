# 나머지 매개변수 (Rest parameters)

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

---

_2023.10.13. Update_