# setTimeout / setInterval

`setTimeout`은 일정 시간이 지난 후 함수를 실행하고, `setInterval`은 일정 시간 간격으로 함수를 반복한다.

<br>

### 🔸 setTimeout

`setTimeout(함수, 시간, 인수)`로 나타낸다. 리턴값은 timeId

`clearTimeout(tId)`을 사용해서 지정된 스케쥴링을 취소할 수 있다.

```js
function showName(name) {
    console.log(name);
}

count timeId = setTimeout(showName, 3000, 'Hong');
// 3초 뒤 Hong 출력

clearTimeout(timeId);   // 스케쥴링 취소
```

<br>

### 🔸 setInterval

`setInterval(함수, 시간, 인수)`로 나타낸다. 리턴값은 timeId

`clearInterval(tId)`을 사용해서 지정된 스케쥴링을 취소할 수 있다.

```js
function showName(name) {
    console.log(name);
}

count timeId = setInterval(showName, 3000, 'Hong');
// 3초마다 Hong 출력

clearInterval(timeId);   // 스케쥴링 취소
```

<br>

> **❗️ 주의사항**
> 
> delay를 0으로 설정해도 바로 실행되지 않는다.
> 
> 현재 실행중인 스크립트가 종료된 이후 스케쥴링 함수를 실행하기 때문이다.
> 
> 또한 브라우저는 기본적으로 4ms 정도의 딜레이 시간이 있어 그 이상의 시간이 걸릴 수 있다.

<br><br>

---

_2023.10.13. Update_