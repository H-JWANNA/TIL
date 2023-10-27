import "./App.css";
import Day from "./component/Day";
import DayList from "./component/DayList";
import Header from "./component/Header";

function App() {
  const name = "World";
  return (
    <div className="App">
      <Header />
      <DayList />
    </div>
  );
}

export default App;
