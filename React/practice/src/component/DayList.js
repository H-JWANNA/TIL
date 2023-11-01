import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function DayList() {
  const [days, setDays] = useState([]);
  const [count, setCount] = useState(0);
  const [count2, setCount2] = useState(0);

  function onClick() {
    setCount(count + 1);
  }

  function onClick2() {
    setCount2(count2 + 1);
  }

  useEffect(() => {
    console.log("count: ", count);
  }, [count]);

  return (
    <div>
      <ul className="list_day">
        {days.map((day) => (
          <li key={day.id}>
            <Link to={`/day/${day.day}`}>Day {day.day}</Link>
          </li>
        ))}
      </ul>
      <button
        onClick={onClick}
        style={{
          marginRight: "10px",
        }}
      >
        {count}
      </button>
      <button onClick={onClick2}>{count2}</button>
    </div>
  );
}
