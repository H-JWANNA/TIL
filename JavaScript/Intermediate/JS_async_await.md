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

---

_2023.10.17. Update_