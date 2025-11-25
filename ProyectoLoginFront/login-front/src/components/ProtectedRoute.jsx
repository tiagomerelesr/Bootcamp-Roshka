import { Navigate } from "react-router-dom";
import { getToken, getRol } from "../utils/auth";

export default function ProtectedRoute({ children, requiredRole }) {

  const token = getToken();
  const rol = getRol(); // “ADMIN” o “USER”

  // Si no tiene token → login
  if (!token) {
    return <Navigate to="/login" replace />;
  }

  // Si se exige un rol y NO coincide → redirigir a SU dashboard, no al /home
  if (requiredRole && rol !== requiredRole) {
    return <Navigate to={`/${rol.toLowerCase()}`} replace />;
  }

  return children;
}
