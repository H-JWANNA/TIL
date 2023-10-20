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