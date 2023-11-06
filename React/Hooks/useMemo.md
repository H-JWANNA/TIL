# useMemo

`useMemo`는 리렌더링 간의 결과를 캐시할 수 있는 React Hook이다.

> `useMemo` is a React Hook that lets you cache the result of a calculation between re-renders.

```js
const cachedValue = useMemo(calculateValue, dependencies)
```

<br>

## 사용 목적

`useMemo`는 React에서 component의 성능을 최적화하는데 사용된다.

Memo는 Memoization를 뜻하며, 이전에 계산한 값을 메모리에 저장하여 프로그램 실행 속도를 빠르게 한다.

<br><br>

## 사용 예시

```js
const shortCal = (num) => {
  return num + 1;
}

const longCal = (num) => {
  for (let i = 0; i < 99_999_999; i++) {
    // 의도적으로 딜레이 발생
  }
  return number + 1000;
}

function Memo() {
  const [short, setShort] = useState(1);
  const [long, setLong] = useState(1);

  const shortSum = shortCal(short);
  const longSum = longCal(long);

  return (
    <div>
      <h3>Short</h3>
      <input 
        type="number" 
        value={short} 
        onChange={(e) => setShort(parseInt(e.target.value))} />
      <span>{shortSum}</span>

      <h3>Long</h3>
      <input 
        type="number" 
        value={long} 
        onChange={(e) => setLong(parseInt(e.target.value))} />
      <span>{longSum}</span>
    </div>
  );
}

export default Memo;
```

<br>

위와 같은 코드에서 short와 long은 한 컴포넌트에서 사용하고 있기 때문에,

만약 short의 값이 변경되더라도 state가 변경됨에 따라 컴포넌트에 리렌더링이 발생하게 된다.

그에 따라 `longCal` 함수도 재실행되며 렌더링이 오래 걸리게 된다.

<br>

이러한 현상을 방지하기 위해 `useMemo`를 사용할 수 있다.

<br>

```js
// 기존 코드
const longSum = longCal(long);

// useMemo 사용 코드
const longSum = useMemo(() => {
  return longCal(long);
}, [long]);
```

useMemo의 1번째 인자는 **콜백 함수**를 작성한다. 캐시할 값을 계산하는 함수이다.

초기 렌더링 시 함수를 실행한 결과를 반환하고, 해당 값을 의존성 배열에 저장한다.

<br>

2번째 인자로는 **의존성 값**을 배열로 작성한다. 해당 값에 변경이 없다면 캐시된 값을 그대로 가져온다.

<br><br>

#### 🔗 참고 자료

- [Hooks API Reference - useMemo](https://react.dev/reference/react/useMemo)

- [useMemo란?](https://velog.io/@jinyoung985/React-useMemo%EB%9E%80)

<br>

---

_2023.11.06. Update_