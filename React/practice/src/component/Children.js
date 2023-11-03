import { useContext } from "react";
import { Context } from "../App";

export default function Children() {
  const user = useContext(Context);

  return (
    <div>
      <h3>name : {user.name}</h3>
      <h3>age : {user.age}</h3>
    </div>
  );
}
