# 상속, 프로토타입 (Prototype)

프로토타입은 상속을 통해 상위 객체의 프로퍼티를 사용하거나 오버라이딩할 수 있게 한다.

```js
const car = {
    wheels: 4
}

const bmw = {
    navigation: 1,
    name: 'bmw'
}

const x5 = {
    color: 'white',
    name: 'x5'
}

bmw.__proto__ = car;
x5.__proto__ = bmw;
```

위처럼 `__proto__`를 사용해서 특정 객체의 프로토타입을 지정할 수 있다.

<br>

```js
console.log(x5);
// { color: 'white', name: 'x5' }

console.log(x5.wheels);
// 4
```

객체의 프로퍼티에서 찾을 수 없다면, 프로토타입을 통해 해당 프로퍼티가 존재하는지 확인한다.

<br>

```js
for(p in x5) {
    if(x5.hasOwnProperty(p)) {
        console.log('o', p);
    } else {
        console.log('x', p);
    }
}

/*
    o color
    o name
    x navigation
    x wheels
*/
```

`for in` 문을 사용하면 프로토타입의 프로퍼티도 모두 가져오게 된다.

이 때, `hasOwnProperty`를 사용해서 해당 객체의 프로퍼티만 선별할 수 있다.

<br>

### 🔸 생성자 프로토타입

생성자 함수에도 프로토타입을 사용해서 중복 코드를 줄일 수 있다.

```js
const Bmw = function(color) {
    this.color = color;
}

Bmw.prototype.wheels = 4;
Bmw.prototype.drive = function() {
    console.log('drive');
}

const x5 = new Bmw('red');
```

생성자 함수에는 `prototype` 메소드를 사용해서 프로토타입을 설정할 수 있다.

<br>

단, 아래와 같이 프로토타입을 작성할 경우에는 문제가 발생한다.

```js
const Bmw = function(color) {
    this.color = color;
}

Bmw.prototype = {
    wheels = 4,
    drive = function() {
        console.log('drive');
    }
}

const x5 = new Bmw('red');

console.log(x5 instanceof Bmw); // true
console.log(x5.constructor === Bmw); // false
```

해당 인스턴스의 constructor가 false가 나오는 문제점이 생기기 때문에 프로토타입을 하나씩 추가하거나,  
아래처럼 수동으로 constructor 프로퍼티를 추가해줄 수 있다.

```js
Bmw.prototype = {
    constructor: Bmw,
    wheels = 4,
    ...
}
```

<br><br>

---

_2023.10.17. Update_