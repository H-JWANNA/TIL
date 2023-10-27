import "./App.css";
import Hello from "./component/Hello";
import State from "./component/State";

function App() {
  const name = "World";
  return (
    <div className="App">
      <State age={10}></State>
      <State age={20}></State>
      <State age={30}></State>
    </div>
  );
}

export default App;
