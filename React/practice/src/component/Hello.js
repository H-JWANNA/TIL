import World from "./World";
import asd from "./Hello.module.css";

const Hello = () => {
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
      <World />
      <div className={asd.box}>Box</div>
    </div>
  );
};

export default Hello;
