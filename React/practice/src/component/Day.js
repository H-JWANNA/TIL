import dummy from "../dummy/data.json";

export default function Day({ today }) {
  const wordList = dummy.words.filter((word) => word.day === { today }.today);
  console.log(wordList);

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
