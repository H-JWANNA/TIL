import axios from "axios";
import { useState } from "react";
import { SERVER_URL } from "../api/getUrl";

export default function Word(props) {
  const [word, setWord] = useState(props.word);
  const [isShow, setIsShow] = useState(false);
  const [isDone, setIsDone] = useState(word.isDone);

  function toggleShow() {
    setIsShow(!isShow);
  }

  async function toggleDone() {
    const res = await axios.put(`${SERVER_URL}/words/${word.id}`, {
      ...word,
      isDone: !isDone,
    });

    // console.log("put", res);

    if (res.statusText === "OK") {
      setIsDone(!isDone);
    }
  }

  async function del() {
    if (window.confirm("정말로 삭제하시겠습니까?")) {
      const res = await axios.delete(`${SERVER_URL}/words/${word.id}`);

      // console.log("del", res);

      if (res.statusText === "OK") {
        setWord({ id: 0 });
      }
    }
  }

  if (word.id === 0) {
    return null;
  }

  return (
    <tr className={isDone ? "off" : ""}>
      <td>
        <input type="checkbox" checked={isDone} onChange={toggleDone} />
      </td>
      <td>{word.eng}</td>
      <td>{isShow && word.kor}</td>
      <td>
        <button onClick={toggleShow}>뜻 {isShow ? "숨기기" : "보기"}</button>
        <button onClick={del} className="btn_del">
          삭제
        </button>
      </td>
    </tr>
  );
}
