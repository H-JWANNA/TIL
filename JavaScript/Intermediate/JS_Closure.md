# 클로저 (Closure)

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

---

_2023.10.13. Update_