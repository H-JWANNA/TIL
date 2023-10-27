# Component & JSX

<br>

## Component

리액트로 만든 페이지는 컴포넌트(Component)들로 구성되어 있다.

컴포넌트로 구성하면 비슷한 코드를 재사용할 수 있고, 유지 보수가 용이하다는 장점이 있다.

<br>

`create-react-app`을 통해 리액트 프로젝트를 생성하면 초기에 `App.js`라는 함수형 컴포넌트가 있고,  

해당 컴포넌트를 `index.js`에서 import해서 사용하고 있다.

<br><br>

## JSX

`App.js` 컴포넌트가 리턴하는 html처럼 생긴 문법이 **JSX (JavaScript XML)** 이다.

```jsx
const name = "World!";

return (
    <div className="App">
        <h1 
            style={{ 
                color: "red", 
                backgroundColor: "green" 
            }}
        > 
            Hello {name}
        </h1>
    </div>
);
```

<br>

JSX는 몇 가지 규칙을 따른다.

- class는 JavaScript 예약어이므로 `className`으로 전달한다.

- style은 **객체**로 전달해야 적용된다.

- background-color와 같은 속성은 **카멜 케이스**로 작성한다.

- 변수는 **중괄호**를 통해서 전달한다.  
  (변수는 문자열이나 숫자는 가능하지만, Boolean 타입이나 객체는 포현하지 못한다.)

- 객체는 아래와 같이 변수로 전달할 수 있다.

- return은 하나의 태그만 가능하다. 그래서 `div`와 같은 태그로 결과를 감싸주어야한다.

```jsx
const naver = {
    name: "네이버",
    url: "https://www.naver.com,
};

return (
    <div className="App">
        <a href={naver.url}> {naver.name} </a>
    </div>
);
```

<br><br>

---

_2023.10.27._