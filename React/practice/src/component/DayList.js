import { useState } from "react";
import dummy from "../dummy/data.json";
import Day from "./Day";

export default function DayList() {
  console.log(dummy);
  const [today, setToday] = useState(1);

  return (
    <div>
      <ul className="list_day">
        {dummy.days.map((day) => (
          <li
            key={day.id}
            onClick={() => {
              setToday(day.day);
            }}
          >
            Day {day.day}
          </li>
        ))}
      </ul>
      <Day today={today} />
    </div>
  );
}
