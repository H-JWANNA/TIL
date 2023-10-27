# 이벤트 처리

리액트에서 이벤트 핸들링을 하는 방법은 여러가지가 있다.

<br>

## 1. 함수 호출

```js
function showName() {
console.log("JWANNA");
}

<button onClick={showName}>Show name</button>
```

함수를 호출할 때 `showName()`과 같이 작성하면 함수의 리턴값을 반환하기 때문에

위와 같은 코드에서는 작동을 하지 않게 된다.

<br>

반면에 showName 함수에 다음과 같이 작성한다면

```js
function showName() {
    return "JWANNA";
}
```

`showName`으로 호출해도, `showName()`으로 호출해도 동일하게 리턴값을 반환한다.

<br><br>

## 2. 함수 직접 작성

```js
<button onClick={() => {
    console.log(27);
}}>
Show age
</button>
```

함수를 직접 작성하면 함수 인자에 대한 처리를 편하게 할 수 있다.

<br>

```js
function showAge(age) {
    return age;
}

<button onClick={() => {
    showAge(27);
}}>
Show age
</button>
```

<br>

```js
function showText(txt) {
    return txt;
}

<input 
  type="text"
  onChange={e => {
    const txt = e.target.value;
    showText(txt);
  }} 
/>
```

<br><br>

---

_2023.10.27. Update_