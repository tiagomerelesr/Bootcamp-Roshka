import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import ProtectedRoute from "./components/ProtectedRoute";

import UserPanel from "./pages/UserPanel";
import UserDevices from "./pages/UserDevices";

import AdminPanel from "./pages/AdminPanel";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Navigate to="/login" />} />

        <Route path="/login" element={<Login />} />

        {/* USER */}
        <Route
          path="/user"
          element={
            <ProtectedRoute requiredRole="USER">
              <UserPanel />
            </ProtectedRoute>
          }
        />

        <Route
          path="/user/devices"
          element={
            <ProtectedRoute requiredRole="USER">
              <UserDevices />
            </ProtectedRoute>
          }
        />

        {/* ADMIN */}
        <Route
          path="/admin"
          element={
            <ProtectedRoute requiredRole="ADMIN">
              <AdminPanel />
            </ProtectedRoute>
          }
        />

      </Routes>
    </BrowserRouter>
  );
}

export default App;
