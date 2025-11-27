import { Navigate, Outlet } from "react-router-dom";
import { getUserInfo, getToken } from "../utils/auth";

export default function ProtectedRoute({ role }) {

  const token = getToken();
  const user = getUserInfo();

  // Si NO hay token → login
  if (!token) return <Navigate to="/login" replace />;

  // Si el usuario aún no está cargado → NO redirigir todavía
  if (!user || !user.rol) {
    return <div className="text-center p-4">Cargando...</div>;
  }

  // Si el rol no coincide → login
  if (role && user.rol !== role) {
    return <Navigate to="/login" replace />;
  }

  return <Outlet />;
}
