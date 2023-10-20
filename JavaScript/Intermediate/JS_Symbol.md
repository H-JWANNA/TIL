# 심볼 (Symbol)

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

---

_2023.10.13. Update_