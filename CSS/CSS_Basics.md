# CSS

_**웹페이지에서 HTML, CSS, JS의 역할은?**_
> HTML은 구조(Structure)를 담당하는 Markup Language  
> CSS는 스타일(Style, Presentation)을 담당하는 Style Sheets Language  
> JavaSctipt는 상호작용(Interaction)을 담당하는 Programming Language

<br>

## CSS (Cascading Style Sheets)

### 💡 Cascading이란?

- '**폭포**, **상속하는**'의 의미로 트리 구조로 작성된 문서의 상위 요소에 Style을 적용하면 하위 요소까지 영향을 주는 CSS의 특징을 나타내는 의미이다.

<br>

CSS를 사용하면 스타일을 별도로 저장할 수 있게 해주므로 사이트의 전체 스타일을 손쉽게 제어할 수 있다.

<br><br>

## CSS Selector (CSS 선택자)

CSS에서 Style을 적용하고자하는 HTML 요소를 정확히 특정하기 위해 사용되는 것

<br>

**🔸 전체 셀렉터 (Universal Selector)**

- `*`을 통해 정의. HTML 문서 내의 모든 요소를 선택한다.

<br>

**🔸 태그 셀렉터 (Tag Selector)**

- `h1`, `p`와 같은 지정된 태그명을 선택한다. `,`을 통해 중복 선택 가능.

<br>

**🔸 ID 셀렉터 (ID Selector)**

- `#idname`을 통해 정의. id attribute 값이 일치하는 요소를 선택한다. **이 값은 중복될 수 없다.**  

&emsp;&emsp; <span style = "color :gray">*실제로는 잘 안 쓴다고도 하기도 하던데..*</span>

<br>

**🔸 클래스 셀렉터 (Class Selector)**

- `.classname`을 통해 정의. class attribute 값이 일치하는 요소를 모두 선택한다.

<br>

**🔸 어트리뷰트 셀렉터 (Attribute Selector)**

- `tag[attribute]`를 통해 정의. 지정된 Attribute를 갖는 모든 요소를 선택한다.

<br>

**🔸 자식 셀렉터 (Child Combinator)**
- `A > B`를 통해 정의. A 태그의 직계 자식 중 B를 모두 선택한다.

<br>

**🔸 인접 형제 셀렉터 (Adjacent Sibling Combinator)**
- `A + B`를 통해 정의. A와 같은 라인에 있는 B 중 A 뒤로 가장 가까운 B를 선택한다.

<br>

**🔸 일반 형제 셀렉터 (General Sibling Combinator)**

- `A ~ B`를 통해 정의. A와 같은 라있에 있는 B 중 A보다 뒤에 있는 있는 모든 B를 선택한다.

<br><br>

### 💡 가상 클래스 셀렉터 (Pseudo-Class Selector)

가상 클래스 셀렉터는 `:`를 통해 사용하며, 클래스 추가 없이 요소의 특정 상태에 따라 스타일을 정의할 때 사용된다.

<br>

**🔸 링크 셀렉터 (Link pseudo-classes)**

- `a:link` : 셀렉터 a가 방문하지 않은 링크일 때
- `a:visited` : 셀렉터 a가 방문한 링크일 때

<br>

**🔸 동적 셀렉터 (User action pseudo-classes)**

- `a:hover` : 셀렉터 a에 마우스가 올라와 있을 때
- `a:active` : 셀렉터 a가 클릭된 상태일 때
- `a:focus` : 셀렉터 a에 포커스가 들어와 있을 때

<br>

**🔸 구조 가상 클래스 셀렉터 (Structural pseudo-classes)**

- `a:first-child` : 셀렉터에 해당하는 모든 요소 a 중 첫번째 자식인 요소를 선택 (순서 상관 X)
- `a:last-child` : 셀렉터에 해당하는 모든 요소 a 중 마지막 자식인 요소를 선택 (순서 상관 X)

<br>

- `a:nth-child(n)` : 셀렉터에 해당하는 모든 요소 a 중 첫번째 자식인 요소를 선택 (순서 상관 O)
- `a:nth-last-child(n)` : 셀렉터에 해당하는 모든 요소 a 중 마지막 자식인 요소를 선택 (순서 상관 O)

<br>

- `a:first-of-type` : 셀렉터에 해당하는 부모 요소 a의 자식 중 첫번째가 a이면 True
- `a:last-of-type` : 셀렉터에 해당하는 부모 요소 a의 자식 중 마지막이 a이면 True
- `a:nth-of-type(n)` : 셀렉터에 해당하는 부모 요소 a의 자식 중 앞에서 n번째가 a이면 True
- `a:nth-last-of-type(n)` : 셀렉터에 해당하는 부모 요소 a의 자식 중 뒤에서 n번째가 a이면 True

<br>

**🔸 UI 요소 상태 셀렉터 (UI element states pseudo-classes)**

- `a:checked` : 셀렉터 a가 체크 상태일 때
- `a:enabled` : 셀렉터 a가 사용 가능한 상태일 때
- `a:disabled` : 셀렉터 a에 사용 불가능한 상태일 때

<br>

**🔸 부정 셀렉터 (Negation pseudo-class)**

- `a:not(셀렉터)` : a 요소 중 셀렉터 조건에 해당하지 않는 모든 요소를 선택

<br>

**🔸 정합성 체크 셀렉터(validity pseudo-class)**

- `:valid(셀렉터)` : 정합성 검증이 성공한 input 요소 또는 form 요소를 선택
- `:invalid(셀렉터)` : 정합성 검증이 실패한 input 요소 또는 form 요소를 선택

<br><br>

### 💡 가상 요소 (Pseudo-Element)

가상 요소는 `::`를 통해 사용하며, 존재하지 않는 요소를 만들어 스타일을 적용하거나 특정 부분에 스타일을 적용할 때 사용된다.

> CSS3 이상부터 `::` 표현식을 사용한다.

<br>

- `::before` : 콘텐츠의 앞에 위치하는 공간을 선택한다. 일반적으로 content 프로퍼티와 함께 사용된다.
- `::after` : 콘텐츠의 뒤에 위치하는 공간을 선택한다. 일반적으로 content 프로퍼티와 함께 사용된다.
- `::first-letter` : 콘텐츠의 첫글자를 선택한다.
- `::first-line` : 콘텐츠의 첫줄을 선택한다. 블록 요소에만 적용할 수 있다.
- `::selection` : 드래그한 콘텐츠를 선택한다. iOS Safari 등 일부 브라우저에서 동작하지 않는다.

<br>

### 📋 [***더 많은 셀렉터 관련 참조 문서***](https://poiemaweb.com/css3-selector)

<br><br>

## CSS Properties (CSS 속성)

<br>

[![ChromeStatus](./src/css.png)](https://chromestatus.com/metrics/css/popularity)

*시작에 앞서 웹페이지에서 많이 사용되는 속성을 알아보았다. (22.08.23. 기준)*

<br>

<img src = "https://www.hackerhero.com/blog/wp-content/uploads/2020/04/important-css-properties-1024x594.png" width = "100%">

*CSS Properties 너무 많다..*

<br>

### 🔸 Font 관련 속성
- color : 폰트 색상 변경
- font-family : 폰트 설정
- font-size : 폰트 크기 설정
- font-weight : 폰트 굵기 설정
- text-decoration : 폰트 밑줄, 가로줄 (종류 효과 색깔)
- text-shadow : 그림자 설정 (가로 세로 퍼짐 색깔)
- letter-spacing : 자간 설정
- line-height : 행간 설정
- text-align : left, right, center, justify를 통해 정렬

<br>

### 🔸 Box Model 관련 속성

<br>

🔔 보통 박스모델은 border를 기준으로 한다. 외부 여백은 margin 내부 여백은 padding이라고 한다.

```CSS
* {
  box-sizing: border-box;
}
```
&emsp; - *해당 코드를 통해 Box Size의 기본값을 Border를 기준으로 설정한다. 기본값은 `content-box`*

<br>

<img src="https://codinglead.github.io/images/box-model.png" witdh="300px" height = "220px">

<br>

- width : 폭 설정
- height : 높이 설정
- padding : border 안쪽 여백 설정 (top right bottom left - 시계방향)
- margin : border 바깥쪽 여백 설정 (100px 200px 이면 위아래 100px, 양 옆 200px)
- border : border 설정 (border-width border-style border-color)
- border-color : border 색상만 설정
- backgorund : background 설정 (background-color url("image url") no-repeat)
- background-color : background 색상만 설정

<br>

❌ `display`와 `flex`에 대한 개념은 아직 이해하지 못했다.

<br>

### 📋 **더욱 자세한 속성은 [_MDN_](https://developer.mozilla.org/ko/docs/Web/CSS/CSS_Values_and_Units), [_W3L_](https://www.w3schools.com/cssref/css_units.asp) 참조**

<br><br>

---

_2023.10.17. Update_

_2022.08.23. Update_