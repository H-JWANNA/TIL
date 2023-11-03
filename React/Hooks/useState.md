# useState

`useState`는 컴포넌트에 **상태 변수**를 추가할 수 있는 React Hook이다.

> `useState` is a React Hook that lets you add a **state variable** to your component

```js
const [state, setState] = useState(초기값);
```

<br>

최초 렌더링 시, `state`에는 초기값이 전달된다. 이후 `setState` 함수를 통해 state 값을 갱신할 수 있다.

<br>

### 💡 state를 사용하는 이유

일반 변수는 값이 변해도 화면이 리렌더링되지 않는다.

하지만 state는 값이 변함에 따라 화면이 **리렌더링**된다.

<br>

### 💡 setState

`setState` 함수는 **비동기**적으로 동작한다.

<br>

```js
const [count, setCount] = useState(0);

const plus = () => {
  setCount(count + 1);
  console.log(count);
}

return (
  <button onClick={plus}>+</button>
);
```

먼저 위와 같은 코드에서 버튼을 눌렀을 때, 화면에는 1로 출력되지만 콘솔에는 0으로 출력되는 등의 현상이 발생할 수 있다.

이는 setCount가 비동기로 동작하기 때문에 **실행 순서를 보장하지 않기 때문이다.**

<br>

또한, 리액트는 이벤트 핸들러가 닫히는 시점에 setState를 종합하여 한 번에 처리하는데,  

이 때 같은 키값을 가진 객체의 경우라면 **가장 마지막 실행 값으로 덮어쓰게 된다.**

따라서 아래와 같은 상황이 발생할 수 있다.

```js
const [count, setCount] = useState(0);

const plus = () => {
  setCount(count + 1);
  setCount(count + 2);
  setCount(count + 3);
}

return (
  <button onClick={plus}>+</button>
);
```

위와 같은 코드에서 버튼을 눌렀을 때, count에 대한 값이 덮어씌워져서 count는 3씩 증가하게 된다.

<br>

이를 방지하려면 아래와 같이 작성할 수 있다.

```js
const plus = () => {
  setCount(count => count + 1);
  setCount(count => count + 2);
  setCount(count => count + 3);
}
```

<br><br>

#### 🔗 참고 자료

- [Hooks API Reference - useState](https://react.dev/reference/react/useState)

- [state를 사용하는 이유](https://velog.io/@hamham/%EB%A6%AC%EC%95%A1%ED%8A%B8%EC%97%90%EC%84%9C-state%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0)

<br>

---

_2023.11.03. Update_