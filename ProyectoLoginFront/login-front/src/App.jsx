import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

// Login
import Login from "./pages/Login";

// Layouts
import AdminLayout from "./layouts/AdminLayout";
import UserLayout from "./layouts/UserLayout";

// Admin pages
import AdminPanel from "./pages/AdminPanel";
import AdminDevices from "./pages/AdminDevices";
import AdminEquipos from "./pages/AdminEquipos";
import AdminUsuarios from "./pages/AdminUsuarios";   

// User pages
import UserPanel from "./pages/UserPanel";
import UserDevices from "./pages/UserDevices";
import UserPerfil from "./pages/UserPerfil";        

// Seguridad
import ProtectedRoute from "./components/ProtectedRoute";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>

        {/* ---------------- LOGIN ---------------- */}
        <Route path="/login" element={<Login />} />

        {/* ---------------- ADMIN ROUTES ---------------- */}
        <Route element={<ProtectedRoute role="ADMIN" />}>
          <Route path="/admin" element={<AdminLayout />}>
            <Route index element={<AdminPanel />} />
            <Route path="devices" element={<AdminDevices />} />
            <Route path="equipos" element={<AdminEquipos />} />
            <Route path="usuarios" element={<AdminUsuarios />} />  {/* AGREGADO */}
          </Route>
        </Route>

        {/* ---------------- USER ROUTES ---------------- */}
        <Route element={<ProtectedRoute role="USER" />}>
          <Route path="/user" element={<UserLayout />}>
            <Route index element={<UserPanel />} />
            <Route path="devices" element={<UserDevices />} />
            <Route path="perfil" element={<UserPerfil />} /> {/*  AGREGADO */}
          </Route>
        </Route>

        {/* DEFAULT */}
        <Route path="*" element={<Navigate to="/login" />} />

      </Routes>
    </BrowserRouter>
  );
}
