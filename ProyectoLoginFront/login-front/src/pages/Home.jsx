import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getToken, removeToken } from "../utils/auth";

export default function Home() {
  const navigate = useNavigate();

  // Proteger ruta (si no hay token → redirigir al login)
  useEffect(() => {
    const token = getToken();
    if (!token) navigate("/login");
  }, []);

  // Logout correcto
  const handleLogout = () => {
    removeToken();
    navigate("/login");
  };

  return (
    <div className="min-h-screen bg-gray-100">

      {/* NAVBAR */}
      <nav className="bg-white shadow px-8 py-4 flex justify-between items-center sticky top-0 z-50">
        <h1 className="text-2xl font-bold text-blue-600">
          Inicio
        </h1>

        <button
          onClick={handleLogout}
          className="bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded-lg transition shadow-sm"
        >
          Cerrar sesión
        </button>
      </nav>

      {/* CONTENIDO */}
      <div className="p-10">
        <h2 className="text-3xl font-semibold mb-8">Menú Inicial</h2>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">

          <div className="bg-white rounded-xl shadow-md p-6 hover:shadow-xl transition transform hover:-translate-y-1 cursor-pointer">
            <h3 className="text-xl font-semibold mb-2">Usuarios</h3>
            <p className="text-gray-600">Administrar usuarios del sistema.</p>
          </div>

          <div className="bg-white rounded-xl shadow-md p-6 hover:shadow-xl transition transform hover:-translate-y-1 cursor-pointer">
            <h3 className="text-xl font-semibold mb-2">Operaciones</h3>
            <p className="text-gray-600">Ver tareas asignadas.</p>
          </div>

          <div className="bg-white rounded-xl shadow-md p-6 hover:shadow-xl transition transform hover:-translate-y-1 cursor-pointer">
            <h3 className="text-xl font-semibold mb-2">Mi Perfil</h3>
            <p className="text-gray-600">Ver y actualizar tu información personal.</p>
          </div>

        </div>
      </div>
    </div>
  );
}
