import axios from "axios";
import { useRef } from "react";
import { SERVER_URL } from "../api/getUrl";
import { useNavigate } from "react-router-dom";
import useFetch from "../hooks/useFetch";

export default function CreateDay() {
  const days = useFetch(`${SERVER_URL}/days`);
  const navigate = useNavigate();

  async function addDay() {
    const res = await axios.post(`${SERVER_URL}/days`, {
      day: days.length + 1,
    });

    if (res.status === 201) {
      alert("생성이 완료되었습니다.");
      navigate("/");
    }
  }

  return (
    <div>
      <h3>현재 일수 : {days.length}</h3>
      <button onClick={addDay}>Day 추가</button>
    </div>
  );
}
