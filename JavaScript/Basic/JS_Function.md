# 함수 (Function)

JavaScript의 함수는 **함수 선언문** 또는 **함수 표현식**의 방식으로 작성할 수 있다.

- 함수 선언문은 JS 실행 시 선언된 함수들을 로딩해서 저장하기 때문에 **어디서든 사용 가능**하다.

- 함수 표현식은 인터프리터가 해당 라인을 읽을 때 저장하므로 해당 함수 표현식 **아래에서만 사용 가능**하다.

<br>

### 🔸 함수 선언문

```js
function sayHello(name) {
    return `Hello, ${name}!`;
}

console.log(sayHello());
// Hello, undefined!

console.log(sayHello('JWANNA'));
// Hello, JWANNA!
```

위처럼 파라미터가 전달되지 않았을 때 undefined가 출력되는 것을 방지하기 위해 기본 값을 설정할 수 있다.

<br>

```js
function sayHello(name) {
    let newName = name || 'friend';
    return `Hello, ${newName}!`;
}

function sayHello(name = 'friend') {
    return `Hello, ${name}!`;
}
```
▲ _둘 다 name의 기본 값으로 friend를 입력받는다._

<br>

### 🔸 함수 표현식

함수 표현식은 변수에 함수를 지정하는 방식으로 화살표 표현식으로도 표현할 수 있다.

```js
const add = function(num1, num2) {
    return num1 + num2;
}

// 화살표 표현식 (Java의 람다식과 유사함)
const add = (num1, num2) => {
    return num1 + num2;
}

// 코드가 한 줄이면 return 생략 가능
const add = (num1, num2) => num1 + num2;
```

<br><br>

---

_2023.10.12. Update_