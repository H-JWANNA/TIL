# 제어문

<br>

## 조건문

조건문은 Java와 같다.

```js
const age = 20;
if (age > 20) {
    ...
} else if (age === 20) {
    ...
} else {
    ...
}
```

<br>

## 반복문

반복문은 Java와 비슷하다.

```js
for(let i = 0; i < 10; i++) {
    console.log(i);
}
```

```js
let i = 0;

while(i < 10) {
    if (i % 2) {
        continue;
    }
    console.log(i);
    i++;
}
```

<br>

## Switch문

Switch문은 Java와 같다.

```js
let fruit = prompt('과일 입력');

switch(fruit) {
    case '사과':
        cosole.log('100원');
        break;
    case '바나나':
        cosole.log('200원');
        break;
    case '수박':
        cosole.log('300원');
        break;
    default :
        console.log('없는 상품입니다.');
}
```

<br><br>

---

_2023.10.12. Update_