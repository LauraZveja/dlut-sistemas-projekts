import {Routes, Route, Navigate } from "react-router-dom";
import ViewAllEmployees from "./components/Employee/ViewAllEmployees";

function App() {
  return (
    <Routes>
        <Route path="/" element={<Navigate to="/showAll" replace />} />
        <Route path="/showAll" element={<ViewAllEmployees />} />
      </Routes>
  );
}

export default App;
