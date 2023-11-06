# useCallback

`useCallback`은 리렌더링 간에 함수를 캐시할 수 있는 React Hook이다.

> `useCallback` is a React Hook that lets you cache a function definition between re-renders.

```js
const cachedFn = useCallback(fn, dependencies)
```

<br>

### 💡 useMemo vs useCallback

`useMemo`는 계산한 값을 memoization해서 재사용한다.

즉, 함수가 리턴한 값을 저장하는 것이다.

<br>

`useCallback`은 계산한 값을 저장하는 것이 아닌 **콜백함수 그 자체를 저장**하는 것이다.

<br>

사실 JavaScript에서 함수는 객체의 한 종류이다.

```js
const calculate = (num) => {
  return num + 1;
}
```

위의 함수도 `calculate`라는 변수에 함수 객체가 할당된 것이다.

따라서 컴포넌트가 리렌더링될 때 컴포넌트가 가진 함수 객체도 다시 호출 된다.

<br>

따라서 `useCallback`을 사용하면, 해당 함수 객체가 초기화 되는 것을 방지할 수 있다.

<br><br>

## 사용 방법

`useMemo`와 비슷하게 1번째 인자는 **콜백 함수**를 작성하고, 2번째 인자로는 **의존성 값**을 배열로 작성한다.

`useCallBack`도 주로 state나 props 변경으로 인한 리렌더링 시 특정 함수가 다시 호출되는 현상을 막기 위해 사용한다.

<br><br>

#### 🔗 참고 자료

- [Hooks API Reference - useCallback](https://react.dev/reference/react/useCallback)

- [다시 한번 useCallback을 파헤쳐보자](https://velog.io/@hjthgus777/React-%EB%8B%A4%EC%8B%9C-%ED%95%9C%EB%B2%88-useCallback%EC%9D%84-%ED%8C%8C%ED%97%A4%EC%B3%90%EB%B3%B4%EC%9E%90)

<br>

---

_2023.11.06. Update_