# map

리액트에서도 map 함수를 통해 컴포넌트를 표현할 수 있다.

<br>

먼저 아래와 같은 더미 데이터가 있다고 하자

```json
{
  "days": [
    { "id": 1, "day": 1 },
    { "id": 2, "day": 2 },
    { "id": 3, "day": 3 }
  ],
  "words": [
    {
      "id": 1,
      "day": 1,
      "eng": "book",
      "kor": "책"
    },
    {
      "id": 2,
      "day": 1,
      "eng": "apple",
      "kor": "사과"
    },
    {
      "id": 3,
      "day": 2,
      "eng": "pencil",
      "kor": "연필"
    },
    {
      "id": 4,
      "day": 2,
      "eng": "car",
      "kor": "자동차"
    }
  ]
}
```

<br>

해당 더미 데이터는 import를 통해 리액트에서 사용할 수 있다.

```js
import dummy from "../db/data.json";
```

<br>

```jsx
import dummy from "../db/data.json";

export default function DayList() {
  return (
    <div>
      <ul className="list_day">
        {dummy.days.map((day) => (
          <li key={day.id}>
            Day {day.day}
          </li>
        ))}
      </ul>
    </div>
  );
}
```

dummy 파일은 days 배열과 words 배열로 이루어져 있다.

`dummy.days`를 통해 days 배열에 접근하여 map 함수를 통해 각각의 데이터에 특정 함수를 실행할 수 있다.

위에서는 days 배열의 값에서 day 데이터를 표현하도록 했다.

<br><br>

```jsx
import dummy from "../db/data.json";

export default function Day() {
  const day = 1;
  const wordList = dummy.words.filter((word) => word.day === day);

  return (
    <div>
      <table>
        <tbody>
          {wordList.map((word) => (
            <tr key={word.id}>
              <td>{word.eng}</td>
              <td>{word.kor}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
```

또한 위와 같이 `filter` 메소드를 통해 특정 데이터만 선택해서 추출할 수 있다.


<br><br>

---

_2023.10.28. Update_