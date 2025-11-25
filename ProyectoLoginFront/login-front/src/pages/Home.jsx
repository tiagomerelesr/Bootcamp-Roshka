import { getUsuario, getRol } from "../utils/auth";

export default function AdminPanel() {
  const usuario = getUsuario();
  const rol = getRol();

  return (
    <div className="min-h-screen bg-gray-100">
      <header className="bg-white shadow px-6 py-4 flex justify-between items-center">
        <h1 className="text-2xl font-bold text-purple-600">
          Panel de Administración
        </h1>
        <div className="text-sm text-gray-600">
          {usuario && (
            <>
              <span className="font-semibold">{usuario}</span> · Rol: {rol}
            </>
          )}
        </div>
      </header>

      <main className="p-8 space-y-6">
        <p className="text-gray-700">
          Aquí después conectamos el CRUD de usuarios y devices.
        </p>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div className="bg-white rounded-xl shadow p-6">
            <h2 className="font-semibold mb-2">Usuarios</h2>
            <p className="text-sm text-gray-500">
              Listar, crear, editar y eliminar usuarios.
            </p>
          </div>

          <div className="bg-white rounded-xl shadow p-6">
            <h2 className="font-semibold mb-2">Dispositivos</h2>
            <p className="text-sm text-gray-500">
              Ver historial de dispositivos que iniciaron sesión.
            </p>
          </div>

          <div className="bg-white rounded-xl shadow p-6">
            <h2 className="font-semibold mb-2">Reportes</h2>
            <p className="text-sm text-gray-500">
              (Idea futura) métricas de accesos y seguridad.
            </p>
          </div>
        </div>
      </main>
    </div>
  );
}
