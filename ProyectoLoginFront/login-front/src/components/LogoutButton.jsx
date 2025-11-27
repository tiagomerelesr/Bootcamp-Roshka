import { clearAuth, getUserInfo } from "../utils/auth";
import { useNavigate } from "react-router-dom";
import { apiFetch } from "../utils/api";

export default function LogoutButton() {
  const navigate = useNavigate();
  const usuario = getUserInfo();

  const logout = async () => {
    await apiFetch(`/api/auth/logout/${usuario.id}`, { method: "POST" });

    clearAuth();
    navigate("/login");
  };

  return (
    <button
      onClick={logout}
      className="bg-red-600 text-white px-3 py-1 rounded hover:bg-red-700"
    >
      Cerrar sesión
    </button>
  );
}
