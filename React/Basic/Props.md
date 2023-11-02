# Props (Properties)

Props는 컴포넌트의 **속성값**을 말한다.

<br>

### 🔸 Props 전달

컴포넌트에 다음과 같이 props를 넘겨줄 수 있다.

```js
function App() {
  const name = "World";
  return (
    <div className="App">
      <State age={10}></State>
      <State age={20}></State>
      <State age={30}></State>
    </div>
  );
}
```

<br>

그리고 props를 넘겨받은 컴포넌트는 다음과 같이 넘겨받은 props를 사용할 수 있다.

```js
function State(props) {
  return (
    <div>
        <h2>{props.age}</h2>
    </div>
  );
}
```

<br>

### 🔸 Props 수정

넘겨받은 props는 `read only`이므로 변경이 불가능하다.

해당 props를 사용하기 위해서는 새로운 State를 생성해야한다.

<br>

```js
function State(props) {
  const [age, setAge] = useState(props.age);

  function plusAge() {
    setAge(age + 1);
    console.log(age);
  }

  return (
    <div>
      <h2>나이 : {age}</h2>
      <button onClick={plusAge}>plus</button>
    </div>
  );
}
```

<br>

또한, 한 컴포넌트가 가지고 있는 State를 Props로 넘길 수도 있다.

새로운 컴포넌트를 만들었다.

```js
export default function CheckAge({ age }) {
  const msg = age > 19 ? "성인" : "미성년자";
  return <p>{msg}입니다.</p>;
}
```

<br>

변수명이 겹치는 것을 고려해서 아래와 같이 사용할 수도 있다.

```js
export default function CheckAge({ age: a }) {
  ...
}
```

age라는 이름 대신 a라는 이름으로 사용할 수 있다.

<br>

그리고 기존 컴포넌트에서 CheckAge 컴포넌트를 import해서 사용할 수 있다.

```js
function State(props) {
  const [age, setAge] = useState(props.age);

  function plusAge() {
    setAge(age + 1);
    console.log(age);
  }

  return (
    <div>
      <h2>나이 : {age}</h2>
      <CheckAge age={age} />
      <button onClick={plusAge}>plus</button>
    </div>
  );
}
```

<br>

위의 `<CheckAge age={age} />`에서 `{age}`는 State 컴포넌트에서는 State지만,  

CheckAge 컴포넌트 입장에서는 props이다.

State가 변경되면 하위 컴포넌트도 새로 렌더링을해서 보여주는 값을 변경한다.

<br><br>

---

_2023.11.02. Update_

_2023.10.27. Update_