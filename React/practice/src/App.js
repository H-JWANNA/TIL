import "./App.css";
import Hello from "./component/Hello";
import State from "./component/State";

function App() {
  const name = "World";
  return (
    <div className="App">
      <State></State>
      <State></State>
      <State></State>
    </div>
  );
}

export default App;
