import { Outlet, Link } from "react-router-dom";
import LogoutButton from "../components/LogoutButton";
import { getUserInfo } from "../utils/auth";

export default function AdminLayout() {
  const usuario = getUserInfo();

  return (
    <div className="flex min-h-screen">

      <aside className="w-64 bg-gray-900 text-white p-6">
        <h2 className="text-2xl font-bold mb-10">Hola, {usuario.usuario}</h2>

        <nav className="flex flex-col gap-4">
          <Link to="/admin">Inicio</Link>
          <Link to="/admin/usuarios">Usuarios</Link>
          <Link to="/admin/equipos">Equipos</Link>
          <Link to="/admin/devices">Dispositivos</Link>
          <LogoutButton />
        </nav>
      </aside>

      <main className="flex-1 bg-gray-100 p-8">
        <Outlet />
      </main>

    </div>
  );
}
