# State

State는 컴포넌트가 가지고 있는 **상태값**이며, 해당 상태가 변하면 리액트는 자동으로 UI를 업데이트한다.

따라서 개발자는 State를 잘 관리하면 화면을 다시 그려주어야하는 번거로움을 없앨 수 있다.

<br>

## State를 사용하지 않았을 때

```jsx
function State() {
  let name = "JWANNA";

  function changeName() {
    name = name === "JWANNA" ? "CHANGED JWANNA" : "JWANNA";
    console.log(name);
  }

  return (
    <div>
      <h1>State</h1>
      <h2 id="name">{name}</h2>
      <button onClick={changeName}>Change</button>
    </div>
  );
}

export default State;
```

위와 같은 State 컴포넌트가 있을 때,  
Change 버튼을 누르면 name 변수의 값은 변경되고 콘솔에도 변경된 이름이 출력된다.

하지만, 화면을 변화시키는 로직은 없기 때문에 화면에 보이는 이름은 변하지 않고 초기 상태로 유지된다.

<br>

```js
  function changeName() {
    name = name === "JWANNA" ? "CHANGED JWANNA" : "JWANNA";
    console.log(name);
    document.getElementById("name").innerHTML(name);
  }
```

위처럼 Element를 가져와서 innerHTML 메소드로 변환시켜주면 클릭할 때마다 변경이 된다.

<br><br>

## State를 사용할 때

초기 리액트는 State와 LifeCycle을 관리하기 위해서는 클래스형 컴포넌트를 만들어야 했고,  

단순히 UI만 표현하는 컴포넌트만 함수형으로 제작했다.

<br>

이후 리액트 16.8 버전부터 Hook을 사용할 수 있게 되었고,  

함수형 컴포넌트 내에서도 State와 LifeCycle을 관리할 수 있게 되면서  

모든 컴포넌트를 함수형으로 만들 수 있게 되었다.

### 📋 [***React 공식 문서 Hooks***](https://react.dev/reference/react)

<br>

### 🔸 useState

```js
import { useState } from "react";

function State() {
  const [name, setName] = useState("JWANNA");

  function changeName() {
    setName(name === "JWANNA" ? "CHANGED JWANNA" : "JWANNA");
    console.log(name);
  }

  return (
    <div>
      <h1>State</h1>
      <h2>{name}</h2>
      <button onClick={changeName}>Change</button>
    </div>
  );
}

export default State;
```

- `useState` Hook을 사용하기 위해서는 `import`가 필요하다.

- `useState`는 구조 분해 할당을 사용해 표현한다. [0]은 변수, [1]은 setter이다.

- `useState`의 파라미터로는 초기값을 받는다.

- 상태(State)의 변경이 일어나면 리액트는 UI를 업데이트한다.

- 동일한 컴포넌트가 여러개 있더라도 State는 각각 따로 관리되며 **다른 State에 영향을 미치지 않는다.**

<br><br>

---

_2023.10.27. Update_