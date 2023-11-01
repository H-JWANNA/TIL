import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { SERVER_URL } from "../api/getUrl";

export default function DayList() {
  const [days, setDays] = useState([]);

  const fetchDay = async () => {
    const res = await axios.get(`${SERVER_URL}/days`);
    setDays(res.data);
  };

  useEffect(() => {
    fetchDay();
  }, []);

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
