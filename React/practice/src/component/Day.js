import { useParams } from "react-router-dom";
import Word from "./Word";
import { useEffect, useState } from "react";
import axios from "axios";
import { SERVER_URL } from "../api/getUrl";

export default function Day() {
  const day = useParams().day;
  const [words, setWords] = useState([]);

  const fetchWords = async () => {
    const res = await axios.get(`${SERVER_URL}/words?day=${day}`);
    setWords(res.data);
  };

  useEffect(() => {
    fetchWords();
  }, []);

  return (
    <div>
      <h2>Day {day}</h2>
      <table>
        <tbody>
          {words.map((word) => (
            <Word word={word} key={word.id} />
          ))}
        </tbody>
      </table>
    </div>
  );
}
