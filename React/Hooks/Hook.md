# Hooks

Hook은 React 16.8에서 새로 추가된 기능이다.

리액트 훅은 **함수형 컴포넌트**가 **클래스형 컴포넌트**의 기능을 사용 할 수 있도록 해준다.

Hook을 통해 class를 작성하지 않고도 State와 같은 React 기능을 사용할 수 있다.

<br>

### 📍 규칙

1. React Hook은 반복문, 조건문 혹은 중첩된 함수 내에서 호출하면 안되며, **반드시 최상위 레벨에서만 Hook을 호출**해야 한다.  
   또한 Hook은 렌더링 시 항상 동일한 순서로 호출되어야 한다.

2. Hook은 **React 함수 내에서만 호출**해야한다.  
   즉, React Hook은 함수형 컴포넌트에서 호출해야 하며, 추가적으로 Custom Hooks에서 또한 호출이 가능하다.

<br><br>

## 기본 Hooks

#### 🔸 [useState](./useState.md)

#### 🔸 [useEffect](./useEffect.md)

#### 🔸 [useContext](./useContext.md)

<br>

## 추가 Hooks (deprecated 확인 필요)

#### 🔸 [useReducer](./)

#### 🔸 [useCallback](./)

#### 🔸 [useMemo](./)

#### 🔸 [useRef](./useRef.md)

#### 🔸 [useImperativeHandle](./)

#### 🔸 [useLayoutEffect](./)

#### 🔸 [useDebugValue](./)

#### 🔸 [useDeferredValue](./)

#### 🔸 [useTransition](./)

#### 🔸 [useId](./)

#### 🔸 [Library Hooks](./)

#### 🔸 [useSyncExternalStore](./)

#### 🔸 [useInsertionEffect](./)

<br>

#### 🔗 참고 자료

- [React Hook(리액트 훅) 이란?](https://well-made-codestory.tistory.com/44)

- [Hooks API Reference (Legacy)](https://ko.legacy.reactjs.org/docs/hooks-reference.html#usestate)

<br>

---

_2023.11.03. Update_