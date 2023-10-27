import "./App.css";
import Hello from "./component/Hello";
import Welcome from "./component/Welcome";
import styles from "./App.module.css";

function App() {
  const name = "World";
  return (
    <div className="App">
      <Hello />
      <Welcome />
      <div className={styles.box} />
    </div>
  );
}

export default App;
