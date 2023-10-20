# 구조 분해 할당 (Destructuring assignment)

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

---

_2023.10.13. Update_