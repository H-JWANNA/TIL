## 객체 (Object)

JavaScript의 객체는 중괄호(`{}`)를 사용하여 JSON 형식으로 표현된다.

```js
const superman = {
    name: 'clark',
    age: 33
}
```

<br>

### 🔸 접근

```js
console.log(superman.name);
console.log(superman['name']);
```

<br>

### 🔸 추가

```js
superman.gender = 'male';
superman['hairColor'] = 'black';
```

<br>

### 🔸 삭제

```js
delete superman.hairColor;
```

<br>

### 🔸 없는 프로퍼티 접근

`in`을 사용해서 특정 객체에 해당 이름을 가진 프로퍼티가 있는지 확인할 수 있다.

```js
superman.birthDay;
// undefined

'birthDay' in superman;
// false;

'age' in superman;
// true;
```

<br>

### 🔸 단축 프로퍼티

```js
const makeObject = (name, age) => {
    return {
        name: name,
        age: age,
        hobby: 'football'
    }
}
```

아래 코드는 위 코드와 같은 의미이다.

```js
const makeObject = (name, age) => {
    return {
        name,
        age,
        hobby: 'football'
    }
}
```

<br>

### 🔸 객체 반복문 in

```js
const me = {
    name: 'JWANNA',
    age: 27,
    hobby: 'programming'
}

for (x in me) {
    console.log(x);
}

/*
    name
    age
    hobby
*/

for (x in me) {
    console.log(me[x]);
}

/*
    JWANNA
    27
    programming
*/
```

<br>

### 🔸 메소드 (Method)

객체 내에서 함수를 사용할 수 있는데, 이를 메소드라고 한다.

```js
const superman = {
    name: 'clark',
    age: 30,

    // 표현 방법 1
    fly: function() {
        console.log('Fly');
    },

    // 표현 방법 2
    punch() {
        console.log('Punch');
    }
}
```

<br>

### 🔸 this

JavaScript에서 this의 객체는 실행되는 런타임에 결정된다.

> ❗️ 단, 화살표 함수는 일반 함수와는 달리 자신만의 this를 가지지않는다.  
> 
> 화살표 함수 내부에서 this를 사용할 경우, this는 전역객체를 가리킨다.  
> 브라우저 환경의 전역객체는 **window**, Node.js에서는 **global**이다.

```js
function sayHello() {
    console.log(`Hello, ${this.name}`);
}

const boy = {
    name: 'boy',
    sayHello
}

const girl = {
    name: 'girl',
    sayHello
}

boy.sayHello();  // Hello, boy
girl.sayHello(); // Hello, girl
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

---

_2023.10.13. Update_

_2023.10.12. Update_