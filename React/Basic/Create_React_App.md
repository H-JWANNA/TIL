# Create-React-App

리액트는 `create-react-app` 명령어를 통해서 웹팩(Webpack), 바벨(Babel) 등의 개발에 필요한 패키지 설치 및 설정을 간편하게 할 수 있도록 도와준다.

```bash
$ npx create-react-app [디렉토리명]
```

> `create-react-app`을 사용할 경우 개발에 필요한 수많은 패키지들이 딸려오기 때문에  
> 규모가 큰 프로젝트에서는 웹팩, 바벨 등을 통해 수동으로 패키지를 설치하는 것이 좋다고 한다.
>
> 📋 [**_Webpack & Babel_**](./Webpack_Babel.md)

<br><br>

## 앱 실행

리액트 앱을 실행하기 위해서는 터미널에서 `npm start` 명령어를 통해 실행할 수 있다.

실행하면 `http://localhost:3000`으로 접속하게 된다.

<br><br>

## React 구조

`create-react-app`을 사용해서 생성한 리액트 구조는 아래와 같다.

```bash
$ tree -L 2
.
├── README.md
├── node_modules
│   ├── @aashutoshrathi
│   ├── @adobe
│   └── ...
├── package-lock.json
├── package.json
├── public
│   ├── favicon.ico
│   ├── index.html
│   ├── logo192.png
│   ├── logo512.png
│   ├── manifest.json
│   └── robots.txt
└── src
    ├── App.css
    ├── App.js
    ├── App.test.js
    ├── index.css
    ├── index.js
    ├── logo.svg
    ├── reportWebVitals.js
    └── setupTests.js
```

<br>

### 🔸 node_modules/

외부 모듈을 설치하면 해당 폴더에 다운로드 된다.

설치된 외부 모듈을 import하게 되면 Node.js는 `node_modules/` 하위에서 해당 모듈을 찾는다.

npm으로 새로운 패키지를 설치하게 되면, `package.json`과 `node_modules`에 추가된다.

<br>

`node_modules`는 `.gitignore`에 추가되는 대상인데 `package.json`에 설치한 패키지들이 모두 있기 때문에,

`npm install`로 `node_modules`를 언제든지 설치가 가능하기 때문이다.

<br>

### 🔸 package.json

프로젝트의 종속성을 처리하고, 패키지 매니저가 프로젝트를 식별할 수 있는 정보와 프로젝트 설명, 버전, 라이센스 정보 등 기타 메타데이터를 제공한다.

프로젝트에서 사용하는 외부 모듈이 많아지면 관리하기가 어렵기 때문에 필요한 패키지 목록을 저장해놓고 한 번에 모두 설치할 수 있도록 패키지를 정의해놓은 파일이다.

<br>

### 🔸 package-lock.json

협업 개발자 간에 서로 같은 버전의 패키지를 설치할 수 있게하는 패키지 잠금 파일이다.

프로젝트에 패키지가 최초로 추가될 시에 어떤 버전이 설치되었는지를 기록한다.

> 새 패키지 설치 또는 기존 패키지 업데이트 및 삭제 시에  
> 반드시 `package.json`과 `package-lock.json` 파일을 함께 커밋하여 버전을 일치시켜야한다.

<br>

### 🔸 public/

`index.html`과 해당 html 파일에서 사용될 **정적 파일**들이 위치한다.

> `public`에 `src`에서 사용될 파일을 두고 `process.env.PUBLIC_URL` 등으로 접근하여 사용할 수도 있지만,
>
> `src`에서 사용되는 `public` 내 파일들은 **post-processed**나 **minified** 되지 않는 점,
>
> 유실된 파일이 있을 경우 컴파일 시점에 호출되지 않고 프로덕션에서 404 에러를 유발하는 점 등의 단점이 있으므로
>
> 공식문서에서 권장하는대로 js 파일에서 에셋을 `import` 하는 방식을 사용하는 것이 좋다.

<br>

#### index.html

서버에서 읽고, 브라우저가 표출하는 파일이다.

기타 다른 파일들은 삭제하거나 이름을 변경해도 상관없지만 `public/index.html` 과 `src/index.js` 는 해당 이름으로만 사용해야 한다.

> 변경을 못하는 건 아니지만 서버 설정 변경이 필요하다. 굳이 바꿀 필요가 없음

public 폴더 내의 파일들은 `public/index.html` 에서만 사용될 수 있다.

<br>

```html
<div id="root"></div>
```

index.html 내의 해당 태그를 통해 리액트 코드가 실행돼서 만들어진 돔이 구현된다.

<br>

#### manifest.json

웹앱 메타에디터이다.

홈 화면에서 보여지는 앱 이름, 아이콘, 디스플레이 유형 등을 지정할 수 있다.

<br>

#### robots.txt

웹 크롤러를 위한 정보이다.

크롤러에게 색인화할 수 있는 페이지와 허용되지 않는 페이지를 알려준다.

Disallow 옵션에 특정 URL 을 추가하면 공개적으로 사용할 수 있지만 검색 엔진에 의해 인덱싱되지는 않는다.

<br>

#### favicon.ico

favorites + icon 의 합성어로, 브라우저의 주소창에 표시되는 웹 페이지 대표 아이콘이다.

<br><br>

### 🔸 src/

빠른 재빌드를 위해 `src` 내부에 위치한 파일만 webpack에 의해 processed 된다.

src 외부에 위치하는 JS, CSS 파일은 webpack이 찾을 수 없다.

<br>

<br>

#### App.js

root가 되는 리액트 컴포넌트이다.

해당 파일에서 코드를 작성하거나 수정하고 저장하면 브라우저에 바로 반영이 된다.

이를 `HMR(Hot Module Replacement)`이라고 한다.

<br>

#### index.js

```js
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
```

ReactDOM이 `App` 컴포넌트를 document 내 id 값이 root인 태그 안에 렌더링한다.

<br><br>

#### 🔗 참고 사이트

- [Create React App 의 기본 구조](https://velog.io/@suzypark/Create-React-App-%EC%9D%98-%EA%B8%B0%EB%B3%B8-%EA%B5%AC%EC%A1%B0)

- [package.json, node_modules, 그리고 package-lock.json간의 관계](https://cheonmro.github.io/2018/12/23/package-json/)

- [Youtube 코딩앙마](https://www.youtube.com/watch?v=dvQMbg7n6mY&list=PLZKTXPmaJk8J_fHAzPLH8CJ_HO_M33e7-&index=2)

<br><br>

---

_2023.10.25. Update_
