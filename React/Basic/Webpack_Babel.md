# Webpack & Babel

Webpack과 Babel을 따로 사용할 수 있지만,

웹팩과 바벨을 연결하면 바벨이 코드를 트랜스 파일링하면서 웹팩을 통해 모듈들을 번들링할 수 있기 때문에 함께 쓰는 것이 효율적이다.

<br>

## Webpack

<img src = "https://velog.velcdn.com/post-images%2Fjeff0720%2F64584460-6f94-11e9-862b-bdb4de70314b%2F1CNeQyaChrTh0H3ovOd9Dgg.png" width = 600>

<br>

Webpack은 JavaScript 어플리케이션을 위한 정적 모듈 번들러이다.

> **💡 모듈 번들러**
>
> 모듈 번들러란 의존성이 있는 모듈들을 하나의 파일로 통합시켜주는 도구이다.
>
> 모듈 번들러를 사용하면 여러 개의 자바 스크립트를 한 번에 불러올 수 있어 **네트워크의 부담**을 줄일 수 있다.
>
> 또한 모듈 단위로 개발하여 **유지 보수성**을 높일 수 있다.

<br>

번들러 기능 이외에도 css loader 기능, JSX 변환 작업 등의 기능도 한다.

<br>

### 🔸 Webpack 설치

```bash
$ npm i -D webpack webpack-cli webpack-dev-server
$ npm i -D babel-loader css-loader file-loader
$ npm i -D html-webpack-plugin mini-css-extract-plugin
$ npm i -D sass sass-loader
```

- `webpack` : 웹팩 모듈

- `webpack-cli` : 웹팩 명령어를 터미널에서 사용할 수 있게 도와준다.

- `webpack-dev-server` : `nodemon`과 같이 웹팩 환경에서 개발 서버를 생성

- `babel-loader` : 웹팩과 바벨을 연동

- `css-loader` : 웹팩이 CSS 파일을 읽을 수 있도록 도와준다.

- `file-loader` : 웹팩이 파일을 로딩할 수 있도록 도와준다. (이미지 로딩에 사용)

- `html-webpack-plugin` : 번들링된 html 파일에 CSS와 JS 파일을 추가 해준다.

- `mini-css-extract-plugin` : style-loader를 대체하며, html 내의 style 태그 대신 별도의 CSS 파일로 생성해준다.

- `sass-loader` : 웹팩이 sass 파일을 읽을 수 있도록 도와준다.

<br>

### 🔸 Webpack 사용법

> ❌ 추후에 실사용할 때 정리할 예정

<br><br>

## Babel

Babel은 트랜스파일러이다.

> **💡 트랜스 파일러**
>
> 트랜스 파일러는 컴파일러의 일종이며, 특정 언어로 작성된 코드를 비슷한 수준의 추상화를 가진 언어로 변환하는 것을 말한다.

<br>

JavaScript는 매우 다양한 환경에서 실행되는데, 모든 브라우저가 ES6의 최신 기능을 제공하지 않기 때문에 ES5 코드로 변환시키는 과정이 필요하다.

이를 위해서 Babel을 사용한다.

<br>

### 🔸 Babel 설치

```bash
$ npm i -D @babel/core @babel/cli @babel/preset-env
```

- `@babel/core` : 바벨의 핵심 기능을 포함한다.

- `@babel/cli` : 바벨 명령어를 터미널에서 사용할 수 있게 해준다.

- `@babel/preset-env` : 코드 구문 변환 설정을 도와준다. (자동으로 옛날 브라우저를 지원)

<br>

바벨 공식 문서의 플러그인 메뉴를 통해 필요한 플러그인을 추가로 설치할 수 있다.

### 📋 [**_바벨 공식 문서_**](https://babeljs.io/docs/plugins-list)

<br><br>

#### 🔗 참고 사이트

- [자바스크립트 개발 환경: 웹팩과 바벨](https://chanyeong.com/blog/post/27)

- [Webpack 과 Babel이란 무엇일까?](https://velog.io/@dbsbest10/Webpack-%EA%B3%BC-Babel%EC%9D%B4%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C)

<br><br>

---

_2023.10.25. Update_
