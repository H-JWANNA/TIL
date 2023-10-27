# CSS 작성법

`create-react-app`을 통해 프로젝트를 생성했다면, `index.css` 파일과 `App.css` 파일이 있다.

`index.css` 파일에는 전체 프로젝트에 영향을 주는 스타일이 작성되어 있고,

`App.css` 파일에는 App 컴포넌트에 영향을 주는 스타일이 작성되어 있다.

<br>

## 1. 인라인 작성

일반적으로 마크업 할 때도 마찬가지이지만, 특별한 이유가 없으면 인라인으로 작성하는 것은 선호되지 않는다.

<br>

```html
const Hello = () => {
  return (
    <div>
      <h1
        style={{
          color: "#f00",
          borderRight: "2px solid #fff",
          marginBottom: "30px",
          opacity: 0.7,
        }}
      >
        Hello
      </h1>
      <World />
    </div>
  );
};
```

- 인라인에 스타일을 작성할 때는 객체 형식으로 작성한다.

- 객체는 key-value 형태로 나타내며, key는 카멜케이스로 작성한다.

- 30px처럼 문자가 포함되면 문자열 형식으로, 그렇지 않으면 숫자로 작성한다.

<br><br>

## 2. CSS 파일로 작성

일반적인 형태로 CSS를 작성할 수 있다.

```css
.box {
    width: 30px;
    height: 30px;
    backgrond-color: blue;
}
```

<br>

해당 CSS를 컴포넌트에 사용하기 위해서는 import를 해주면 된다.

```js
import "./Hello.css";
```

<br>

하지만 여러 컴포넌트에서 box라는 클래스를 사용하고 있고,  

box 클래스에 대한 CSS가 여러개 작성되었을 경우에 CSS가 오버라이딩 되는 문제가 발생하기도 한다.

특정 컴포넌트에 종속되는 것이 아닌 head의 style에 작성이 되기 떄문에 전 페이지에 영향을 미치는 것이다.

<br><br>

## 3. 모듈화된 CSS

컴포넌트에 종속된 CSS를 만들 수 있다.

파일명을 `컴포넌트명.module.css`로 작성하고, 일반적인 CSS 작성법과 같이 스타일을 추가한다.

```css
.box {
    width: 30px;
    height: 30px;
    backgrond-color: blue;
}
```

<br>

이후 해당 파일을 아래와 같이 import 한다.

```js
import styles from "./Hello.module.css";
```

<br>

해당 CSS를 `styles`라는 이름으로 가져왔으니 태그에서 아래와 같이 사용한다.

```html
<div className={styles.box}></div>
```

<br>

위와 같이 사용하면 컴포넌트 단위로 스타일을 지정할 수 있어서  

사이즈가 큰 프로젝트의 CSS 오버라이딩과 같은 문제를 방지할 수 있다.

<br><br>

---

_2023.10.27. Update_