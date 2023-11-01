# useEffect

어떠한 상태값이 바뀌었을 때 동작하는 함수를 작성할 수 있는 리액트 훅이다.

<br>

리액트는 기본적으로 props나 state에 변화가 생기면 리렌더링을 한다.

그렇기 때문에 `useEffect`를 사용해서 특정 이벤트에 변화가 있을 때만 리렌더링을 하도록 할 수 있다.

<br>

아래와 같은 코드가 있다고 하자.

```js
function App() {
  const [todoList, setTodoList] = useState([]);

  fetch(`${SERVER_URL}/todo`)
    .then((res) => res.json())
    .then((date) => setTodoList(data));

  return (
    <div className="App">
      <h1>TODO LIST</h1>
    </div>
  );
}
```

<br>

위 코드는 계속해서 리렌더링이 발생하며 에러가 날 것이다.

`fetch`를 통해 데이터를 불러오고, `setTodoList`를 통해 state를 변경했다.

state가 변경되었기 때문에 리액트는 다시 리렌더링을 할 것이고, 위의 상황이 무한 루프가 될 것이다.

<br>

이를 위해 사용하는 것이 `useEffect`이다.

<br>

아래와 같이 `useEffect`를 사용하면 초기 렌더링 시에만 함수를 실행하므로, 무한 루프를 방지할 수 있다.

```js
function App() {
  const [todoList, setTodoList] = useState([]);

  useEffect(() => {
    fetch(`${SERVER_URL}/todo`)
      .then((res) => res.json())
      .then((date) => setTodoList(data));
  }, []);

  return (
    <div className="App">
      <h1>TODO LIST</h1>
    </div>
  );
}
```

<br>

### 🔸 1번째 인자

`useEffect()`의 첫번째 인자는 특정 이벤트가 발생했을 때 작동할 함수를 의미한다.

<br>

### 🔸 2번째 인자

`useEffect()`의 두번째 인자는 함수가 작동할 이벤트를 의미한다.

<br><br>

## 예제

```js
export default function Example() {
  const [count, setCount] = useState(0);
  const [count2, setCount2] = useState(1);

  function onClick() {
    setCount(count + 1);
  }

  function onClick2() {
    setCount2(count2 + 1);
  }

  return (
    <div>
      <button onClick={onClick}>{count}</button>
      <button onClick={onClick2}>{count2}</button>
    </div>
  );
}
```

위와 같이 state 2개를 생성하고 각각의 state를 변경하는 버튼 2개를 생성했다.

<br>

위 코드에 useEffect를 적용해 count를 로그로 찍어보려고 한다.

```js
export default function Example() {
  const [count, setCount] = useState(0);
  const [count2, setCount2] = useState(1);

  function onClick() {
    setCount(count + 1);
  }

  function onClick2() {
    setCount2(count2 + 1);
  }

  // useEffect 추가
  useEffect(() => {
    console.log("count: ", count);
  }, []);

  return (
    <div>
      <button onClick={onClick}>{count}</button>
      <button onClick={onClick2}>{count2}</button>
    </div>
  );
}
```

<br>

위와 같이 작성했을 때, 두 버튼 중 어느 버튼을 눌러도 로그가 출력이 되는 것을 볼 수 있다.

만약 여기서 count에 변경이 일어났을 때만 로그를 찍고 싶다면 아래와 같이 코드를 변경할 수 있다.

```js
useEffect(() => {
  console.log("count: ", count);
}, [count]);
```

<br>

2번째 인자 값으로 count를 추가해주면, count에 변경이 일어났을 때만 로그를 찍게 된다.

<br>

이처럼 1번째 인자로는 실행될 함수를 작성할 수 있고,  

2번째 인자로는 어떠한 state나 props가 변경되었을 때 함수가 실행될 지를 정해줄 수 있다.

<br><br>

---

_2023.11.01. Update_