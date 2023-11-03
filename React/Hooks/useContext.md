# useContext

`useContext`는 컴포넌트에서 **Context**를 Publish/Subscribe 할 수 있도록 해주는 React Hook이다.

> `useContext` is a React Hook that lets you read and subscribe to **context** from your component.

```js
const value = useContext(SomeContext)
```

<br>

### 💡 Context란?

`context`를 이용하면 단계마다 일일이 props를 넘겨주지 않고도 컴포넌트 트리 전체에 데이터를 제공할 수 있다.

context API를 사용하기 위해서는 `Provider`, `Consumer`, `createContext`를 알아야한다.

- `createContext` : context 객체 생성

- `Provider` : 생성한 context를 하위 컴포넌트에게 전달

- `Consumer` : context의 변화를 감지

<br><br>

## Context 사용 예제

```js
// App.js

export const CustomContext = createContext();

function App() {
  const user = {
    name: "JWANNA",
    age: 27,
  };

  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route
            path="/"
            element={
              <CustomContext.Provider value={user}>
                <Children />
              </CustomContext.Provider>
            }
          />
        </Routes>
      </div>
    </BrowserRouter>
  );
}
```

`createContext`를 통해 Context를 생성하고,  

`CustomContext.Provider`를 통해 context를 props와 비슷한 형식으로 전달한다.

<br>

```js
// Children.js

export default function Children() {
  return (
    <CustomContext.Consumer>
      {(user) => (
        <div>
          <h3>name : {user.name}</h3>
          <h3>age : {user.age}</h3>
        </div>
      )}
    </CustomContext.Consumer>
  );
}
```

이후 `CustomContext.Consumer`를 통해 전달받은 Context를 사용할 수 있다.

<br><br>

## useContext 사용 예제

```js
// Children.js

export default function Children() {
  const user = useContext(CustomContext);

  return (
    <div>
      <h3>name : {user.name}</h3>
      <h3>age : {user.age}</h3>
    </div>
  );
}
```

<br>

`App.js`에서 Context를 생성하고, Provider를 통해 전달하는 과정은 그대로지만,  

`Children.js`에서 Context를 사용하는 과정에서 `useContext`를 사용해 깔끔하게 사용이 가능하다.

<br><br>

#### 🔗 참고 자료

- [Hooks API Reference - useContext](https://react.dev/reference/react/useContext)

- [useContext란?](https://velog.io/@jminkyoung/TIL-6-React-Hooks-useContext-%EB%9E%80)

<br>

---

_2023.11.03. Update_