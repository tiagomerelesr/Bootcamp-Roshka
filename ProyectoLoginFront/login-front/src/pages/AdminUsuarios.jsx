
import { useEffect, useState } from "react";
import { apiFetch } from "../utils/api";
import Toast from "../components/Toast";

export default function AdminUsuarios() {
  const [usuarios, setUsuarios] = useState([]);
  const [toast, setToast] = useState(null);

  const loadUsuarios = async () => {
    const res = await apiFetch("/api/users");
    setUsuarios(await res.json());
  };

  useEffect(() => {
    loadUsuarios();
  }, []);

  const borrarUsuario = async (id) => {
    if (!confirm("¿Eliminar este usuario?")) return;

    const res = await apiFetch(`/api/users/${id}`, { method: "DELETE" });
    if (res.ok) {
      setToast({ mensaje: "Usuario eliminado", tipo: "success" });
      loadUsuarios();
    } else {
      setToast({ mensaje: "Error al eliminar", tipo: "error" });
    }
  };

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">Gestión de Usuarios</h1>

      <table className="min-w-full bg-white shadow rounded text-center">
        <thead className="bg-gray-200">
          <tr>
            <th className="p-3">Usuario</th>
            <th className="p-3">Email</th>
            <th className="p-3">Rol</th>
            <th className="p-3">Acciones</th>
          </tr>
        </thead>

        <tbody>
          {usuarios.map(u => (
            <tr key={u.id} className="border-b">
              <td className="p-3">{u.usuario}</td>
              <td className="p-3">{u.email}</td>
              <td className="p-3">{u.rol}</td>

              <td className="p-3 flex justify-center gap-2">
                <button className="bg-yellow-500 text-white px-3 py-1 rounded">
                  Editar
                </button>

                <button
                  className="bg-red-600 text-white px-3 py-1 rounded"
                  onClick={() => borrarUsuario(u.id)}
                >
                  Borrar
                </button>

                <button className="bg-blue-600 text-white px-3 py-1 rounded">
                  Equipos
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {toast && <Toast mensaje={toast.mensaje} tipo={toast.tipo} />}
    </div>
  );
}
