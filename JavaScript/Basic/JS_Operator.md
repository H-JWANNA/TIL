# 연산자

JavaScript와 Java의 기본 연산자, 비교 연산자는 약간의 차이가 있다.

### 🔸 기본 연산자

Java와 기본 연산자(+, -, *, /, %), 증감 연산자(++, --), 연산자 줄여 쓰기(+=, -= 등) 대부분이 같다.

다른 점은 거듭제곱(**)을 사용할 수 있다.

```js
console.log(2 ** 3); // 8
```

<br>

### 🔸 비교 연산자

JavaScript의 비교 연산자에는 `===`, `!==`가 추가로 존재한다.

`==`는 형변환 후 값이 같은지 확인하는 연산자이며,   
`===`는 값 뿐만 아니라 자료형도 같은지 확인하는 연산자이다.

```js
console.log(0 == '');   // true
console.log(0 == '0');  // true
console.log('' == '0'); // false

console.log(0 === '');   // false
console.log(0 === '0');  // false
console.log('' === '0'); // false
```

<br>

### 🔸 논리 연산자

논리 연산자는 Java와 같다.

- `&&` : AND  
- `||` : OR  
- `!` : NOT
- `??` : null 병합 연산자 (앞의 값이 **null이나 undefined**이면 뒤의 값. 0이나 false는 병합하지 않음)

<br>

**💡 논리 할당 연산자(Logical assignment Operators)**

특정 함수에 기본값을 세팅할 때, 아래와 같이 작성할 수 있다.

```js
function add(num1, num2) {
    num1 = num1 || 0;
    num2 = num2 || 0;
    console.log(num1 + num2);
}
```

기본값을 설정하지 않으면, `add();` 출력 시 `NaN`이 출력되므로 기본값을 설정해주었다.

이를 논리 할당 연산자를 통해 축약하면 아래와 같이 축약할 수 있다.

```js
function add(num1, num2) {
    num1 ||= 0;
    num2 ||= 0;
    console.log(num1 + num2);
}
```

<br><br>

---

_2023.10.12. Update_