import { useParams } from "react-router-dom";
import Word from "./Word";
import { SERVER_URL } from "../api/getUrl";
import useFetch from "../hooks/useFetch";

export default function Day() {
  const day = useParams().day;
  const words = useFetch(`${SERVER_URL}/words?day=${day}`);

  return (
    <div>
      <h2>Day {day}</h2>
      {words.length === 0 && <span>Loading...</span>}
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
