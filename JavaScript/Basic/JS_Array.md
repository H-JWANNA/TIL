# 배열 (Array)

JavaScript의 배열은 대괄호(`[]`)로 선언한다.

배열은 문자 뿐만 아니라 숫자, 객체, 함수 등도 포함할 수 있다.

<br>

### 🔸 배열의 기본 메소드

- `.length` : 배열의 길이를 나타낸다.

- `.push()` : 배열 맨 뒤에 값을 삽입한다.

- `.pop()` : 배열 맨 뒤의 값을 삭제한다.

- `unshift()` : 배열 맨 앞에 값을 삽입한다.

- `shift()` : 배열 맨 앞의 값을 삭제한다.

<br>

### 🔸 반복문을 통한 배열 탐색

- 인덱스로 탐색
  
```js
let days = ['월', '화', '수'];

for(let i = 0; i < days.length; i++) {
    console.log(days[i]);
}
```

- Iterator로 탐색

```js
let days = ['월', '화', '수'];

for(let day of days) {
    console.log(day);
}
```

Java에서는 `:`를 사용하지만 JavaScript에서는 `of`를 사용한다.

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

---

_2023.10.12. Update_