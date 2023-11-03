import { createContext } from "react";
import "./App.css";
import CreateDay from "./component/CreateDay";
import CreateWord from "./component/CreateWord";
import Day from "./component/Day";
import DayList from "./component/DayList";
import EmptyPage from "./component/EmptyPage";
import Header from "./component/Header";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Children from "./component/Children";

export const Context = createContext();

function App() {
  const user = {
    name: "JWANNA",
    age: 27,
  };

  return (
    <BrowserRouter>
      <div className="App">
        <Header />
        <Routes>
          <Route
            path="/"
            element={
              <Context.Provider value={user}>
                <Children />
              </Context.Provider>
            }
          />
          <Route path="/day/:day" element={<Day />} />
          <Route path="/create-word" element={<CreateWord />} />
          <Route path="/create-day" element={<CreateDay />} />
          <Route path="/*" element={<EmptyPage />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
