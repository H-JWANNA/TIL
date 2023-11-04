# useRef

`useRef`는 렌더링에 필요하지 않은 값을 참조할 수 있는 하는 React Hook이다.

> `useRef` is a React Hook that lets you reference a value that’s not needed for rendering.

```js
const ref = useRef(initialValue)
```

<br>

## 사용 예시

JavaScript에서 특정 DOM을 선택하기 위해서는 `querySelector` 등의 함수를 사용한다.

마찬가지로 React에서 **저장공간 혹은 DOM 요소에 접근**하기 위해서 `useRef`를 사용한다.

주로 스크롤 위치를 확인하거나 포커스를 주는 동작에서 사용할 수 있다.

<br>

```js
export default function CreateWord() {
  const engRef = useRef(null);
  const korRef = useRef(null);
  const dayRef = useRef(null);

  async function onSubmit(e) {
    e.preventDefault();

    const res = await axios.post(`${SERVER_URL}/words`, {
      day: dayRef.current.value,
      eng: engRef.current.value,
      kor: korRef.current.value,
      isDone: false,
    });

    if (res.status === 201) {
      alert("생성이 완료되었습니다.");
    }
  }

  return (
    <form onSubmit={onSubmit}>
      <div className="input_area">
        <label>Eng</label>
        <input type="text" placeholder="computer" ref={engRef} />
      </div>
      <div className="input_area">
        <label>Kor</label>
        <input type="text" placeholder="컴퓨터" ref={korRef} />
      </div>
      <button>저장</button>
    </form>
  );
}
```

<br>

### 🔸 Parameter

파라미터로는 초기 값을 전달받는다.

<br>

### 🔸 Return

처음에는 전달받은 `initialValue`로 초기화된 변경 가능한 Ref 객체를 리턴한다.

`ref` 속성을 통해 특정 JSX 노드에 접근하여 값을 설정할 수 있다.

<br>

전달받은 객체를 `ref.current`를 통해 사용할 수 있다.

```js
const korRef = useRef(null);

<input type="text" ref={korRef} />

console.log(korRef.current);
// <input type="text">

console.log(korRef.current.value);
// 입력한 값
```

<br><br>

## 주의 사항

`ref.current`는 State와 달리 변경 가능한 객체이다.

그러나 렌더링에 사용되는 객체라면 해당 객체를 변경하지 않아야 한다.

<br>

`ref` 객체는 일반 자바스크립트 객체이기 때문에,  

해당 값이 변경 되어도 React는 인식하지 못해 component를 리렌더링하지 않는다.

<br><br>

#### 🔗 참고 자료

- [Hooks API Reference - useRef](https://react.dev/reference/react/useRef)

<br>

---

_2023.11.04. Update_