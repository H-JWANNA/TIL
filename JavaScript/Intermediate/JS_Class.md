# 클래스 (Class)

ES6에 추가된 스펙으로 Java의 클래스처럼 객체를 만들 수 있다.

<br>

### 🔸 생성자

```js
const User = function(name) {
    this.name = name;
    this.showName = function() {
        console.log(this.name);
    }
}
```
▲ _기존 생성자 함수_


```js
class User {
    constructor(name) {
        this.name = name;
    }

    showName() {
        console.log(this.name);
    }
}
```
▲ _클래스_

<br>

클래스로 인스턴스를 생성하게 되면 생성자 외부의 메소드들은 프로토타입으로 들어가게 된다.

단, `for in` 문을 통해서도 프로토타입의 메소드를 출력하지 않는다.

또한, `new` 키워드를 사용하지 않은 생성자 함수는 `undefined`를 출력하지만, 클래스 생성자는 **에러를 발생**시킨다.

<br>

### 🔸 상속

클래스의 상속에는 `extends` 키워드를 사용한다.

부모의 프로퍼티에 접근하기 위해서는 `super` 키워드를 사용할 수 있으며, 메소드 오버라이딩을 통해 재정의가 가능하다.

```js
class Car {
    constructor(color) {
        this.color = color;
        this.wheels = 4;
    }

    drive() {
        console.log('drive');
    }
}

class Bmw extends Car {
    constructor(color) {
        super(color);
        this.navigation = 1;
    }

    park() {
        console.log('park');
    }
}

const x5 = new Bmw('black');

console.log(x5);
// Bmw { color: 'black', wheels: 4, navigation: 1 }
```

<br><br>

---

_2023.10.17. Update_