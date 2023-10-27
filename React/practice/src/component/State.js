import { useState } from "react";
import CheckAge from "./CheckAge";

function State(props) {
  const [name, setName] = useState("JWANNA");
  const [age, setAge] = useState(props.age);

  function changeName() {
    setName(name === "JWANNA" ? "CHANGED JWANNA" : "JWANNA");
    setAge(age + 1);
    console.log(name, age);
  }

  return (
    <div>
      <h2 id="name">
        {name}({age})
      </h2>
      <CheckAge age={age} />
      <button onClick={changeName}>Change</button>
    </div>
  );
}

export default State;
