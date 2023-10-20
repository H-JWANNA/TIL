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

---

_2023.10.17. Update_