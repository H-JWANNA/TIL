import axios from "axios";
import { SERVER_URL } from "../api/getUrl";
import useFetch from "../hooks/useFetch";
import { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function CreateWord() {
  const days = useFetch(`${SERVER_URL}/days`);
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);

  const engRef = useRef(null);
  const korRef = useRef(null);
  const dayRef = useRef(null);

  async function onSubmit(e) {
    e.preventDefault();

    if (!isLoading) {
      setIsLoading(true);

      const res = await axios.post(`${SERVER_URL}/words`, {
        day: dayRef.current.value,
        eng: engRef.current.value,
        kor: korRef.current.value,
        isDone: false,
      });

      console.log(res);

      if (res.status === 201) {
        alert("생성이 완료되었습니다.");
        navigate(`/day/${dayRef.current.value}`);
        setIsLoading(false);
      }
    }
  }

  return (
    <form onSubmit={onSubmit}>
      <div className="input_area">
        <label>Eng</label>
        <input type="text" placeholder="computer" ref={engRef} />
      </div>
      <div className="input_area">
        <label>Kor</label>
        <input type="text" placeholder="컴퓨터" ref={korRef} />
      </div>
      <div className="input_area">
        <label>Day</label>
        <select ref={dayRef}>
          {days.map((day) => (
            <option key={day.id} value={day.day}>
              {day.day}
            </option>
          ))}
        </select>
      </div>
      <button
        style={{
          opacity: isLoading ? 0.3 : 1,
        }}
      >
        {isLoading ? "저장 중.." : "저장"}
      </button>
    </form>
  );
}
