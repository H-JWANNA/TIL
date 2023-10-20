# WeakRef

WeakRef는 Weak Reference의 줄임말로 **약한 참조**를 말한다.

JavaScript는 가비지 컬렉터가 존재하므로 사용하지 않는 객체를 메모리에서 해제하는 작업을 자동으로 해준다.

떄문에 참조하고 있는 객체는 가비지 컬렉터의 대상이 아니다.

하지만, **약한 참조는 가비지 컬렉터 대상이므로 메모리를 해제할 수 있다.**

<br>

```js
let user = { name: 'Hong', age: 27 }
const weakUser = new WeakRef(user);
```

위와 같이 약한 참조 객체를 만들 수 있다.

참조에 접근하기 위해서는 `weakUser.deref()`와 같이 사용해 참조가 해제되었는지 확인할 수 있다.

<br>

**_MDN 문서에서는 가능한 사용을 피하라고 말하고 있다._**

<br><br>

---

_2023.10.17. Update_