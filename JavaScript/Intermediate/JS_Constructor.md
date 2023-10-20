# 생성자 함수

비슷한 객체 리터럴을 생성자를 통해 만들 수 있다.

생성자를 통해 객체를 만들때 해당 객체를 인스턴스라고 한다.

```js
function User(name, age) {
    this.name = name;
    this.age = age;
    this.sayName = function() {
        console.log(this.name);
    }
}

let user1 = new User('JWANNA', 27);
user1.sayName(); // JWANNA
```

`new` 키워드를 사용하지 않으면 return이 없는 함수를 실행하기 때문에 `undefined`를 할당받는다.

<br>

```js
console.log(user1 instanceof User); // true
console.log(user1.constructor === User); // true
```

생성자를 통해 만들어진 객체가 해당 생성자의 인스턴스인지, 해당 인스턴스의 생성자가 맞는지 확인할 수 있다.

<br><br>

---

_2023.10.13. Update_