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

---

_2023.10.13. Update_