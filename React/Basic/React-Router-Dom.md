# React-router-dom

리액트에 있는 라우팅 관련 라이브러리 중 가장 많이 쓰이는 라이브러리이다.

> **💡 라우팅**
>
> 사용자가 요청한 URL에 따라 매칭되는 페이지를 보여주는 것을 말한다.

<br>

```js
function App() {
  return (
    <div className="App">
      <Header />
      <DayList />
      <Day />
      <EmptyPage />
    </div>
  );
}
```

리액트에서 위와 같이 작성할 경우 모든 컴포넌트가 한 화면에 모두 보이게 된다.

이러한 상황에서 특정 URL마다 매칭되는 컴포넌트를 보여주기 위해 라우터를 사용한다.

<br>

### 설치 방법

```bash
$ npm i react-router-dom
```

<br>

## 리액트 라우터 (React Router)

사용자가 입력한 주소를 감지하는 역할을 하며, 여러 환경에서 동작하도록 여러 종류의 라우터 컴포넌트를 제공한다.

주로 사용하는 라우터 컴포넌트는 아래와 같다.

<br>

### 🔸 &lt;BrowserRouter&gt;

HTML5를 지원하는 브라우저의 주소를 감지한다.

<br>

```js
import { BrowserRouter } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Header />
        <DayList />
        <Day />
        <EmptyPage />
      </div>
    </BrowserRouter>
  );
}
```

위의 코드처럼 import를 해서 사용하며, 모든 코드를 감싸준다.

<br><br>

### 🔸 &lt;Routes&gt;, &lt;Route&gt;

v5 버전에는 `Switch`, `Route`를 사용했지만, v6부터는 `Routes`, `Route`를 사용한다.

<br>

#### v5

```js
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Day from "./Day";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Header />
        <Switch>
          <Route exact path="/">
            <DayList />
          </Route>
          <Route path="/day/:day" component={ Day }>
          <Route 
            path="/note" 
            render={ routeProps => (
              <Note routeProps={routeProps} isLogin={true} /> 
            )} 
          />
          <Route>
            <EmptyPage />
          </Route>
        </Switch>
      </div>
    </BrowserRouter>
  );
}
```

- `<Switch>` : Route들을 구성하는 부모 요소

- `<Route>` : 특정 경로로 특정 컴포넌트 라우팅

  - `path` : 특정 경로 지정

  - `component` : 경로가 일치할 때 호출할 컴포넌트 전달

  - `render` : 경로가 일치할 때 호출할 함수 전달

  - `exact` : React Router 디폴트 매칭 규칙으로 인해 경로의 앞 부분만 일치해도 전부 매칭되므로  
    **명확히 일치하는 경로의 컴포넌트만 불러오기 위한 속성**

  > 💡 **children prop** vs **component** vs **render**
  >
  > 먼저 children prop을 사용하면 route관련 props인 location, history, match를 받아올 수 없다.
  >
  > 그리고 component 키워드를 통해 인라인으로 사용하면, 렌더링을 할 때마다 새로운 컴포넌트를 만들게 된다.  
  > 그래서 render를 사용하거나 자식으로 사용하는 것이 권장된다.

<br>

#### v6

```js
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Header />
        <Routes>
          <Route exact path="/" element={<DayList />} />
          <Route path="/day/:day" element={<Day />} />
          <Route path="/note" element={<Note isLogin={true} />} />
          <Route path="/*" element={<EmptyPage />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}
```

- `<Routes>` : 기존의 `Switch` 네이밍이 변경되었다.

- `<Route>` : exact 속성이 삭제되고, 항상 정확히 일치하는 path에만 매칭된다.  
  또한 component, render 속성이 element 속성으로 통합되었다.

<br>

### 📋 [***react-router v6에서는 어떤것들이 변했을까??***](https://blog.woolta.com/categories/1/posts/211)

<br><br>

### 🔸 &lt;Link&gt;

HTML의 `<a>` 태그를 사용하면 클릭 시 페이지를 새로 불러오기 때문에 SPA에 어울리지 않는다.

필요한 컴포넌트만 재렌더링하기 위해서는 `<Link>` 컴포넌트를 사용할 수 있다.

<br>

```js
import { Link } from "react-router-dom";

export default function Header() {
  return (
    <div className="header">
      <h1>
        <Link to="/">홈으로</Link>
      </h1>
    </div>
  );
}
```

<br><br>

### 🔸 useParam()

`<Route path="/day/:day ...>`와 같이 `:`를 통해 지정한 경로의 변수를 받아온다.

`localhost:3000/day/80`으로 접속하여 day를 출력하면 아래와 같은 결과를 출력한다.

```js
export default function Day() {
  const day = useParams();
  console.log(day);

  return ...
}

출력
{ day : '80' }
```

<br>

이렇게 받아온 결과는 문자열이므로 `Number()`와 같이 형변환을 하여 사용할 수 있다.

또는 아래와 같이 데이터를 받으면 숫자로 전달받는다.

```js
export default function Day() {
  const { day } = useParams();
  console.log(day);

  return ...
}

출력
80
```

<br><br>

### 🔸 History API

v5까지는 Route에 할당된 컴포넌트는 자동으로 match, location, history 값이 props로 전달되었으나, v6부터는 해당 값들이 전달되지 않는다.

<br>

💡 **`useNavigate()`**

- `useHistory()`가 v6에서 변경

- 페이지 이동을 할 수 있게 해주는 함수를 반환

- 첫번째 인자로 경로를 넣어주면 해당 경로로 이동

- 주로 조건이 필요한 곳에서 navigate 함수를 호출 (ex. 로그인 후 다른 페이지 리다이렉트 시)

<br>

💡 **`useMatch()`**

- `useRouterMatch()`가 v6에서 변경

- 인자로 url을 넘기면 해당 url과 일치할 경우 정보를 반환, 아니라면 null 반환

```json
{
    "params": {
        "day": "1"
    },
    "pathname": "/day/1",
    "pathnameBase": "/day/1",
    "pattern": {
        "path": "/day/:day",
        "caseSensitive": false,
        "end": true
    }
}
```

> Match 객체의 값
> 
> - params : [object] url path로 전달된 파라미터 객체 
> - pathname : [string] 라우터에 정의된 path
> - pathnameBase : [string] 실제 클라이언트로부터 요청된 url path
> - pattern : [object] caseSensitive, end, path로 구성

<br>

💡 **`useLocation()`**

- 현재 페이지의 정보를 가진 객체를 반환

```json
{
    "pathname": "/day/1",
    "search": "",
    "hash": "",
    "state": null,
    "key": "qgvimb9g"
}
```

> Location 객체의 값
>
> - pathname : [string] 현재 페이지의 경로명
> - search : [string] 현재 페이지의 query string
> - hash : [string] 현재 페이지의 hash
> - state : [string?] 페이지로 이동 시 임의로 넣을 수 있는 상태값
> - key : [string] location 객체의 고유 값

<br><br>

---

_2023.11.01. Update_

_2023.10.31. Update_