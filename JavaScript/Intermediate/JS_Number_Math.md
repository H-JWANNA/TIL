# Number & Math

<br>

## Number

### 🔸 진수 변환법

`toString()`을 사용하여 숫자의 진수를 변환하여 나타낼 수 있다.

```js
let num = 10;
num.toString();     // "10"
num.toString(2);    // "1010"
```

<br>

### 🔸 Number

- `num.toFixed(n)` : 소수점 자리 설정 (단, 리턴이 String)

- `isNaN(num)` : NaN인지 판단 (`NaN === NaN`도 false이므로 `isNaN`을 사용해야한다.)

- `parseInt()` : 문자열을 10진수로 바꿔준다. 시작하는 부분부터 정수 이외의 문자가 나올 때까지 진행한다.

  ```js
  let margin = '10px';

  parseInt(margin); // 10
  Number(margin);   // NaN

  let redColor = 'f3';
  parseInt(redColor);       // NaN
  parseInt(redColor, 16);   // 243

  parseInt('11', 2) // 3
  ```

- `parseFloat()` : parseInt와 다르게 소수점 이하도 반환

<br><br>

## Math

- `Math.PI` : 원주율 출력

- `Math.ceil(n)` : 소수점 올림

- `Math.floor(n)` : 소수점 내림

- `Math.round(n)` : 반올림

- `Math.random()` : 0 ~ 1 사이의 무작위 수 생성

- `Math.max(n, m, o)`, `Math.min()` : 괄호 안의 수 중 최소, 최대값

- `Math.abs(n)` : 절대값

- `Math.pow(n, m)` : n의 m 거듭 제곱

- `Math.sqrt(n)` : 제곱근

<br><br>

---

_2023.10.13. Update_