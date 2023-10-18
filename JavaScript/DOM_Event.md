# DOM & Event

JavaScript를 활용하는 방법

<br>

## DOM

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

## Event

키보드 입력, 마우스 클릭, 인풋창에 포커스가 되거나 문서의 로드가 완료되는 등의 행위로 **이벤트**가 발생한다.

이때 특정 함수를 할당해서 실행할 수 있는데 이를 **이벤트 핸들러(Event Handler)** 라고 한다.

<br>

### 🔸 이벤트 핸들러 (Event Hanlder)

이벤트 핸들러는 다음과 같이 사용할 수 있다.

<br>

#### **1. html 태그에 직접 로직 작성**

```html
<button onclick="alert('hello')">클릭</button>
```

<br>

#### **2. 함수 로직 분리**

```html
<button onclick="sayHello()">클릭</button>
<script>
    function sayHello() {
        alert('hello');
    }
</script>
```

<br>

#### **3. id 값으로 핸들러 할당**

```html
<button id="btn">클릭</button>
<script>
    function sayHello() {
        alert('hello');
    }

    const btn = document.getElementById('btn');
    btn.onclick = sayHello;
</script>
```

- `btn.onclick = sayHello()`와 같이 작성하면 함수의 반환값이 할당되므로 소괄호는 작성하지 않는다.

<br>

#### **4. addEventListener**

```html
<button id="btn">클릭</button>
<script>
    function sayHello() {
        alert('hello');
    }

    const btn = document.getElementById('btn');
    btn.addEventListener("click", sayHello);
</script>
```

- `addEventListener(event, fn)`는 특정 이벤트가 발생하면, 전달된 핸들러를 실행한다.

    > `DOMContentLoaded`라는 이벤트는 문서 로딩이 완료되었을 때 발생하는 이벤트이다.  
    >
    > 해당 이벤트는 반드시 `addEventListener`를 사용해야 동작한다.

<br>

- 할당된 이벤트를 삭제하기 위해서는 `removeEventListener`를 사용할 수 있다.

<br>

**💡 인자를 통해 해당 이벤트에 대한 정보를 얻을 수 있다.**

```js
input.addEventListener("keyup", event => {
    console.log(event); // KeyboardEvent {...}
})
```

<br><br>

### 🔸 자주 사용되는 이벤트

#### **1. 마우스**

- `click` : 마우스 클릭 시 이벤트 발생
- `dblclick` : 마우스 더블 클릭 시 이벤트 발생
- `mousemove` : 마우스를 움직일 때 이벤트 발생

<br>

#### **2. 키보드**

- `keydown` : 키보드 누를 때 이벤트 발생
- `keyup` : 키보드 뗄 때 이벤트 발생

<br>

#### **3. 인풋 텍스트**

- `focus` : 인풋 창에 포커스가 왔을 때 (인풋창에 작성 중일 때) 이벤트 발생 (버블링 X)
- `blur` : 인풋 창에서 포커스가 사라졌을 때 이벤트 발생 (버블링 X)
- `focusin` : focus와 같음 (버블링 O)
- `focusout` : blur와 같음 (버블링 O)

<br>

#### **4. 윈도우**

- `resize` : 창 크키가 바뀔 때마다 이벤트 발생

<br>

### 📋 [***이벤트 타입***](https://www.w3schools.com/jsref/dom_obj_event.asp)

<br><br>

### 🔸 이벤트 버블링 (Event Bubbling)

한 요소에서 이벤트가 발생할 때, 부모 요소가 없을 때까지 DOM 상위로 전파되는 현상을 말한다.

```html
<body>
    <div id="box">
        <ul id="ul">
            <li id="color">Red</li>
        </ul>
    </div>
</body>

<script>
    const box = document.getElementById('box');
    const ul = document.getElementById('ul');
    const li = document.getElementById('li');

    document.body.addEventListener("click", () => {
        console.log('1. body');
    })
    box.addEventListener("click", () => {
        console.log('2. box');
    })
    ul.addEventListener("click", () => {
        console.log('3. ul');
    })
    li.addEventListener("click", () => {
        console.log('4. li');
    })
</script>
```

위와 같은 html이 있을 때, 가장 안쪽 태그를 클릭하면 상위 요소까지 전파된다.

```js
/*
    출력
    1. li
    2. ul
    3. box
    4. body
*/
```

<br>

이벤트 버블링은 `stopPropagetion`을 사용하여 인위적으로 막을 수 있다.

```js
li.addEventListener("click", event => {
    event.stopPropagation();
    console.log('4. li');
})
```

<br><br>

### 🔸 이벤트 위임

자신에게 발생하는 이벤트를 다른 요소에서 처리하는 것을 말한다.

<br>

```js
const ul = document.getElementById('ul');
const li = ul.children;

function clickHandler(event) {
    for (l of li) {
        l.classList.remove("on");
    }
    event.target.classList.add("on");
}

document.getElementById("li1").addEventListener("click", clickHandler);
document.getElementById("li2").addEventListener("click", clickHandler);
document.getElementById("li3").addEventListener("click", clickHandler);
```

위와 같은 코드가 있을 때, 리스트의 요소가 늘어난다면 코드 또한 늘어나게 되고 재사용성이나 유지보수 측면에서 좋지 않다.

<br>

이럴 때, 이벤트 버블링을 사용해서 상위 요소에 이벤트를 위임하여 사용할 수 있다.

```js
// document.getElementById("li1").addEventListener("click", clickHandler);
// document.getElementById("li2").addEventListener("click", clickHandler);
// document.getElementById("li3").addEventListener("click", clickHandler);
document.getElementById("ul").addEventListener("click", clickHandler);
```

<br>

또한, 해당 리스트 내부에 `<a>` 태그로 텍스트가 감싸져있다면 문제가 발생하는데, 이는 타겟을 설정해줌으로써 해결할 수 있다.

```js
function clickHandler(event) {
    let target = event.target;

    // li 태그 내부의 a 태그 클릭 시 발생되는 문제
    if (target.tagName === "A") {
        target = target.parentElement;
    } 
    // ul 태그를 클릭할 때, 발생되는 문제
    else if (target === event.currentTarget) {
        return;
    }

    for (l of li) {
        l.classList.remove("on");
    }
    target.classList.add("on");
}
```

<br>

- `event.target` : 이벤트를 발생시키는 요소 (위의 예제에서는 a, li, ul 중 클릭한 것)
- `event.currentTarget` : 이벤트 핸들러가 등록된 요소 (위의 예제에서는 항상 ul)

<br><br>

---

_2023.10.18. Update_