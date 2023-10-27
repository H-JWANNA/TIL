import { useState } from "react";

function State() {
  const [name, setName] = useState("JWANNA");

  function changeName() {
    setName(name === "JWANNA" ? "CHANGED JWANNA" : "JWANNA");
    console.log(name);
  }

  return (
    <div>
      <h2 id="name">{name}</h2>
      <button onClick={changeName}>Change</button>
    </div>
  );
}

export default State;
