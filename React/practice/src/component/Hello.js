import World from "./World";
import asd from "./Hello.module.css";

const Hello = () => {
  function showName() {
    console.log("JWANNA");
  }

  function showAge(age) {
    console.log(age);
  }

  function showText(e) {
    console.log(e.target.value);
  }

  return (
    <div>
      <h1
        style={{
          color: "#f00",
          borderRight: "2px solid #fff",
          marginBottom: "30px",
          opacity: 0.7,
        }}
      >
        Hello
      </h1>
      <button onClick={showName}>Show name</button>
      <button
        onClick={() => {
          showAge(27);
        }}
      >
        Show age
      </button>
      <input
        type="text"
        onChange={(e) => {
          showText(e);
        }}
      ></input>
      <World />
      <div className={asd.box}>Box</div>
    </div>
  );
};

export default Hello;
