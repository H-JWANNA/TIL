# Event

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