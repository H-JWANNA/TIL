# useEffect

`useEffect`는 컴포넌트를 외부 시스템과 동기화할 수 있는 React Hook이다.

> `useEffect` is a React Hook that lets you **synchronize a component with an external system**

```js
useEffect(setup, dependencies?)
```

<br>

## 사용하는 이유

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

1번째 인자는 특정 이벤트가 발생했을 때 **작동할 함수**를 의미하며, 2번째 인자는 **함수가 작동할 이벤트**를 의미한다.

<br><br>

## 사용 예제

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

## 세부 동작

useEffect는 함수형 컴포넌트에서도 클래스 컴포넌트의 **생명주기 메서드**를 사용할 수 있도록 해주는 Hook이며,  

클래스 생명주기 메서드 중 다음 3가지를 합한 것과 같다.

- `componentDidMount` (생성 될 때)

- `componentDidUpdate` (업데이트 할 때)

- `componentWillUnmount` (제거 할 때)

<br>

<img src = "https://velog.velcdn.com/images/sjoleee_/post/9cdbdb8a-fce9-474a-b8fb-e56306af60fa/image.png" width = 600 />

<br><br>

### 1. 생성될 때

useEffect의 **1번째 인자**로 작성된 함수는 **초기 렌더링 시에 실행**된다.

더 정확히 말하면 useEffect 내부의 함수는 **렌더링 후**에 작동한다.

<br>

```js
useEffect(() => {
  console.log('componentDidMount');
}, []);
```

위의 경우에는 리렌더링 시마다 해당 문구가 출력된다.

<br>

### 2. 업데이트 할 때

useEffect의 **2번째 인자**에 입력된 특정 값이 업데이트 되면, 입력한 함수가 실행된다.

만약 2번째 인자인 `dependency`에 빈 배열(`[]`)을 입력하면, 초기 렌더링 시에만 실행된다.

<br>

```js
useEffect(() => {
  console.log(name);
  console.log('componentDidUpdate');
}, [name]);
```

위의 경우에는 초기 렌더링 시, 의존성 값 변경 시 해당 문구가 출력된다.

<br>

### 3. 제거 할 때 (Optional)

dependency의 값이 변경되어 **리렌더링되거나** DOM에서 **컴포넌트가 제거될 때,** React는 `CleanUp` 함수를 실행한다.

CleanUp 함수는 useEffect 내에서 `return`되는 함수이다.

> After every re-render with changed dependencies, React will first run the cleanup function (if you provided it) with the old values, and then run your setup function with the new values. After your component is removed from the DOM, React will run your cleanup function.

<br>

```js
useEffect(() => {
  console.log(count);
  console.log('Update');

  return () => {
    console.log(count);
    console.log('CleanUp');
  }
}, [count]);
```

위의 경우에는 **초기 렌더링 시**에 Update 문구가 출력되고,  

**count 값 변경 시,** CleanUp이 먼저 출력된 후, Update가 출력된다.

이후 unmount 시, CleanUp이 출력된다.

<br><br>

#### 🔗 참고 자료

- [Hooks API Reference - useEffect](https://react.dev/reference/react/useEffect)

- [useEffect의 clean-up 함수](https://velog.io/@sjoleee_/useEffect%EC%9D%98-cleanup%ED%95%A8%EC%88%98)

<br>

---

_2023.11.03. Update_

_2023.11.01. Update_