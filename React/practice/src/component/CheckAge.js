export default function CheckAge({ age }) {
  const msg = age > 19 ? "성인" : "미성년자";
  return <p>{msg}입니다.</p>;
}
