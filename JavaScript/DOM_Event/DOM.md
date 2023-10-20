# DOM

DOM은 Document Object Model의 약자로 HTML 문서의 각 요소를 트리 형식으로 표현해준다.

<img src = "https://www.w3schools.com/js/pic_htmltree.gif" width = 550>

<br>

### 🔸 접근 방법

#### **1. 태그로 접근**

- `document.documentElement` : html 태그에 접근
- `document.body` : body 태그에 접근
- `document.head` : head 태그에 접근

    > ex) `document.body.style.padding = '100px'`과 같이 해당 태그의 속성을 변경할 수 있다.

<br>

#### **2. ID로 접근**

- `document.getElementById('id')` : 태그의 id가 `'id'`인 태그에 접근 (id는 유일한 값)

<br>

#### **3. 태그명으로 접근**

- `document.getElementsByTagName('tag')` : `tag` 태그를 가진 속성들에 접근

    ```js
    const pList = document.getElementsByTagName('p'); // HTMLCollection

    for(p of pList) {
        p.style.opacity = String(Math.random());
    }
    ```

<br>

#### **4. 이름으로 접근**

- `document.getElementsByClassName('class')` : `class` 클래스를 가진 요소들에 접근 (HTMLCollection)

<br>

#### **5. 셀렉터(선택자)로 접근**

📋 [***셀렉터란?***](../CSS/CSS_Basics.md#css-selector-css-선택자)

- `document.querySelectorAll('.class')` : 셀렉터를 사용해 특정 요소들에 접근 (NodeList)

- `document.querySelector('#id')` : 셀렉터를 사용해 탐색한 요소 중 첫번째 요소에 접근

    ```js
    document.querySelector('h3:nth-of-type(2)');
    // h3 태그 중 2번째 선택

    document.querySelectorAll('p:nth-of-type(2n)');
    // 짝수번째 p들을 선택 (NodeList)
    ```

<br>

**💡 HTMLCollection & NodeList**

- 공통점  
  Iterable 컬렉션으로 `for of` 혹은 인덱스, length 등을 사용 가능  
  Array는 아니기 떄문에 push, pop, join 등은 사용 불가능

- 차이점  
  NodeList는 해당 함수를 실행해서 **값을 가져온 순간의 값으로 저장** (추가되는 태그에 의한 변화가 없음)  
  HTMLCollection은 함수 실행 후 태그가 추가된다면 **추가된 태그도 저장**

<br><br>

### 🔸 부모 ・ 자식 ・ 형제 노드에 접근

#### **1. 부모 노드에 접근**

- 부모 노드에는 `parentNode`, `parentElement` 메소드로 접근할 수 있다.

    ```html
    <ul id="color">
        <li id="red">red</li>
        <li id="blue">blue</li>
        <li id="green">green</li>
    </ul>
    ```

    ```js
    const red = document.getElementById('red');

    red.parentNode;    // <ul id="color">...</ul>
    red.parentElement; // <ul id="color">...</ul>

    const htmlTag = document.documentElement; // <html> 태그
    htmlTag.parentNode;    // #document
    htmlTag.parentElement; // null
    ```

- `parentNode`는 부모 중 모든 노드를 반환한다. 그러므로 html태그의 상위 노드는 document이다.
- `parentElement`는 부모 중 요소 노드를 반환한다. html 태그로 이루어진 요소만 반환한다.

<br>

> **💡 Node의 타입**
>
> **1. Node.ELEMENT_NODE**
> - `<p>`, `<div>`와 같은 html 요소
>
> **2. Node.ATTRIBUTE_NODE**
> - `<img>` 태그의 alt 값 같은 속성
>
> **3. Node.TEXT_NODE**
> - 텍스트
>
> **8. Node.COMMENT_NODE**
> - 주석
>
> **9. Node.DOCUMENT_NODE**
> - document

<br>

#### **2. 자식 노드에 접근**

- 자식 노드에는 `childNodes`, `children` 메소드로 접근할 수 있다.

    ```html
    <ul id="color">
        <li id="red">red</li>
        <!-- comment -->
        <li id="blue">blue</li>
        <li id="green">green</li>
    </ul>
    ```

    ```js
    const ul = document.getElementById('color');

    ul.childNodes; // NodeList(9) [text, li#red, text, comment, text ...]
    ul.children;   // HTMLCollection(3) [li#red, li#blue, li#green]
    ```

- `childNodes`는 자식 중 모든 노드를 반환한다. 줄바꿈이나 공백도 text로 인식된다.
- `children`은 자식 중 요소 노드를 반환한다. html 태그로 이루어진 요소만 반환한다.

    > `childNodes`는 예외적으로 NodeList를 반환하지만 HTMLCollection과 같이 실시간 변화를 반영한다.

<br>

- 추가로 `firstChild`, `lastChild`, `firstElementChild`, `lastElementChild` 메소드도 존재한다.

<br>

#### **3. 형제 노드에 접근**

- 형제 노드에는 `previousSibling`, `nextSibling`, `previousElementSibling`, `nextElementSibling` 함수로 접근할 수 있다.

    ```html
    <ul id="color">
        <li id="red">red</li>
        <li id="blue">blue</li>
        <li id="green">green</li>
    </ul>
    ```

    ```js
    const blue = document.getElementById('blue');

    blue.previousSibling; // #text
    blue.nextSibling;     // #text

    blue.previousElementSibling; // <li id="red">red</li>
    blue.nextElementSibling;     // <li id="green">green</li>
    ```

<br>

#### **🟢 종합**

|   | 모든 노드 | 요소 노드만 |
|:-:|:-------:|:--------:|
|**부모**|parentNode|parentElement|
|**자식**|childNodes<br>firstChild<br>lastChild|children<br>firstElementChild<br>lastElementChild|
|**형제**|previousSibling<br>nextSibling|previousElementSibling<br>nextElementSibling

<br><br>

### 🔸 노드 생성 ・ 추가 ・ 복제 ・ 삭제

```html
<ul id="color">
    <li id="red">Red</li>
    <li id="blue">Blue</li>
    <li id="green">Green</li>
</ul>
```

위와 같은 html에서 노드 정보를 확인하는 것은 아래와 같이 할 수 있다.

<br>

```js
const blue = document.getElementById('blue'); // <li id="blue">Blue</li>
const blueText = blue.firstChild; // "Blue"

blueText.nodeName;  // '#text'
blueText.nodeType;  // 3
blueText.nodeValue; // 'Blue'

const ul = document.getElementById('color');
ul.nodeName;    // 'UL'
ul.nodeType;    // 1
ul.nodeValue;   // null
ul.textConetnt; // '\n    Red\n    \n    Blue\n    Green\n    '
```

text에서 `nodeValue`나 요소에서 `textContent`를 수정하면 내용을 바꿀 수 있다.

다만, 마크업이 지원되지 않아 `<li>Red</li>`를 넣으면 HTML 형식으로 들어가는 것이 아닌 문자 그대로 들어가게 된다.

<br>

이러한 경우에 `innerHTML`을 사용해서 값을 대입하면 HTML 마크업 형식으로 데이터를 삽입할 수 있다.

```js
ul.innerHTML = '<li id="red">Red</li>';
```

다만, 모든 HTML을 String으로 작성해야하는 단점이 있어서 자식을 추가할 경우 기존의 요소까지 재작성해야한다.

<br>

#### **1. 노드 생성**

- 노드 생성은 `createElement`, `createTextNode` 메소드를 통해서 할 수 있다.

    ```js
    const li = document.createElement('li'); // <li></li>
    const text = document.createTextNode('pink');
    ```

<br>

#### **2. 노드 추가**

- 자식 노드를 추가하기 위해서는 `innerHTML`, `appendChild` 메소드를 사용할 수 있다.

    ```js
    li.append(text);
    ul.append(li);
    ```

<br>

- `appendChild`를 사용하면 부모 노드의 가장 마지막 자식으로 추가된다.  
  특정 위치에 삽입하기 위해서는 `insertBefore`를 사용할 수 있다.

    ```js
    const red = document.getElementById('red');
    ul.insertBefore(li, red);
    ```

<br>

- 새로운 노드가 아닌 기존 노드에 `appendChild` 또는 `insertBefore`를 사용하면 노드의 위치가 변경된다.

<br>

#### **3. 노드 복제**

- 노드 복제는 `cloneNode(boolean)` 메소드를 사용할 수 있다.  
  boolean값이 true이면 자식 노드까지 모두 복제되고, false이면 노드 자신만 복제된다. (default : false)

    ```js
    const newRed = red.cloneNode(true);
    ```

<br>

#### **4. 노드 삭제**

- 노드 삭제는 `removeChild()` 메소드를 사용할 수 있다.

    ```js
    ul.removeChild(red);
    ul.removeChild(ul.firstElementChild);
    ```

<br><br>

### 🔸 CSS Style 제어

```html
<div id="box">BOX</div>
```

위와 같은 html에서 CSS 정보를 확인하는 방법은 아래와 같다.

<br>

```js
const box = document.getElementById('box');
box.style;  // CSSStyleDeclaration {...}
```

<br>

CSS 변경도 가능하다.

```js
box.style.backgroundColor = "red";
box.style["margin-left"] = "30px";
box.style.border = "10px solid #000";
```

<br><br>

### 🔸 Class 제어

```html
<head>
    <style>
        .bg-red {
            background-color: #f00;
        }
        .txt-pink {
            color: pink;
        }
    </style>
</head>
<body>
    <div id="box">BOX</div>
</body>
```

위와 같은 html에서 box에 스타일을 적용하기 위해 클래스를 적용할 수 있다.

<br>

```js
const box = document.getElementById('box'); 
box.className = 'bg-red';
box.className = 'bg-red txt-pink';
```

클래스를 하나 이상 적용하기 위해서는 위와같이 적어줘야한다. 이를 위해 **Class List**를 사용한다.

<br>

```html
<div id="box" class="box bg-red">BOX<div>
```

위와 같은 html은 클래스 리스트를 통해 클래스를 전달한 것이다.

<br>

```js
box.classList;  // DOMTokenList(2) ['box', 'bg-red']

box.classList.add('txt-pink');
box.classList.add('bg-green', 'txt-yellow');
box.classList.remove('txt-pink');
box.classList.replace('bg-red', 'bg-blue');
box.classList.toggle('bg-red');
```

- `add(...)` : 클래스 리스트에 클래스 삽입
- `remove(...)` : 클래스 리스트에서 클래스 삭제
- `replace(a, b)` : 클래스 리스트의 a 클래스를 b 클래스로 교체
- `toggle(...)` : 클래스 리스트에 없다면 삽입, 있다면 삭제

<br><br>

---

_2023.10.18. Update_