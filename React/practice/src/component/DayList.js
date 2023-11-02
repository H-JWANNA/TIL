import { Link } from "react-router-dom";
import { SERVER_URL } from "../api/getUrl";
import useFetch from "../hooks/useFetch";

export default function DayList() {
  const days = useFetch(`${SERVER_URL}/days`);

  if (days.length === 0) {
    return <span>Loading...</span>;
  }

  return (
    <div>
      <ul className="list_day">
        {days.map((day) => (
          <li key={day.id}>
            <Link to={`/day/${day.day}`}>Day {day.day}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
