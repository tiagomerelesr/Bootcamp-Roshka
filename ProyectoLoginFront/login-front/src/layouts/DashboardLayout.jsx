
import { Link } from "react-router-dom";
import LogoutButton from "../components/LogoutButton";

export default function DashboardLayout({ children }) {
  return (
    <div className="flex min-h-screen">

      {/* SIDEBAR */}
      <aside className="w-64 bg-gray-900 text-white p-6 flex flex-col">
        <h1 className="text-2xl font-bold mb-6">Panel</h1>

        <nav className="flex flex-col gap-3">
          <Link to="/admin" className="hover:text-blue-400">Inicio</Link>
          <Link to="/admin/devices" className="hover:text-blue-400">Dispositivos</Link>

          <div className="mt-auto">
            <LogoutButton />
          </div>
        </nav>
      </aside>

      {/* CONTENIDO */}
      <main className="flex-1 p-8 bg-gray-100">
        {children}
      </main>
    </div>
  );
}
