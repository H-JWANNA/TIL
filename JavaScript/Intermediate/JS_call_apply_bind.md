# call, apply, bind

함수 호출 방식과 관계없이 this를 지정할 수 있다.

<br>

### 🔸 call

call 메소드는 모든 함수에서 사용할 수 있으며, **this를 특정 값으로 지정**할 수 있다.

첫번째 파라미터로 this를 지정할 객체를 받으며, 두번째부터 함수의 파라미터를 받는다.

```js
const hong = {
    name: 'Hong'
}

function showName() {
    console.log(this.name);
}

showName();
// undefined (this가 window)

showName.call(hong);
// Hong (this를 hong 객체로 지정)
```

<br>

call 메소드를 사용해서 객체 프로퍼티의 추가도 가능하다.

```js
const hong = {
    name: 'Hong'
}

function update(birthYear, occupation) {
    this.birthYear = birthYear;
    this.occupation = occupation;
}

update.call(hong, 1997, 'Programmer');

console.log(hong);
// { name: 'Hong', birthYear: 1997, occupation: 'Programmer' }
```

<br>

### 🔸 apply

apply 메소드는 함수 매개변수 처리 방법을 제외하면 call과 완전히 같다.

apply는 call과 다르게 **배열의 형태로 파라미터를 전달**받는다.

```js
const kim = {
    name: 'Kim'
}

function update(birthYear, occupation) {
    this.birthYear = birthYear;
    this.occupation = occupation;
}

update.apply(kim, [1995, 'Architecture']);

console.log(kim);
// { name: 'Kim', birthYear: 1995, occupation: 'Architecture' }
```

<br>

### 🔸 bind

bind 메소드는 함수의 **this 값을 영구히** 바꿀 수 있다.

```js
const user = {
    name: 'Hong',
    showName: function() {
        console.log(`hello, ${this.name}`);
    }
}

user.showName(); // hello, Hong

let fn = user.showName;

fn(); // hello, undefined
fn.call(user); // hello, Hong

let boundFn = fn.bind(user);
boundFn(); // hello, Hong
```

`fn` 변수에 showName을 할당하면서 this를 잃어버려 fn만 호출할 때에는 this를 찾지 못한다.

위와 같은 상황에 `bind`를 통해 this 값에 대한 객체를 할당해주면 this를 찾을 수 있다.

<br><br>

---

_2023.10.17. Update_