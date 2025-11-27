import { useEffect, useState } from "react";
import { apiFetch } from "../utils/api";
import { getUserInfo } from "../utils/auth";

export default function UserPerfil() {

  const usuario = getUserInfo();
  const [equipos, setEquipos] = useState([]);
  const [loading, setLoading] = useState(true);   // ⬅ NUEVO

  useEffect(() => {
    // ⛔ Si usuario.id todavía no existe, NO llames al backend
    if (!usuario || !usuario.id) return;

    const load = async () => {
      try {
        const res = await apiFetch(`/api/equipos/user/${usuario.id}`);
        const data = await res.json();
        setEquipos(data);
      } catch (err) {
        console.error("Error cargando equipos:", err);
      } finally {
        setLoading(false);
      }
    };

    load();
  }, [usuario.id]); // ⬅ Espera que id esté disponible

  if (loading) {
    return <p className="text-gray-500">Cargando...</p>;
  }

  return (
    <div>
      <h1 className="text-3xl font-bold mb-6">Mi Perfil</h1>

      <div className="bg-white p-6 shadow rounded mb-8">
        <h2 className="text-xl font-semibold mb-3">Datos personales</h2>

        <p><strong>Usuario:</strong> {usuario.usuario}</p>
        <p><strong>Email:</strong> —</p>
        <p><strong>Rol:</strong> {usuario.rol}</p>
      </div>

      <h2 className="text-xl font-semibold mb-3">Mis Equipos</h2>

      <table className="min-w-full bg-white shadow rounded text-center">
        <thead className="bg-gray-200">
          <tr>
            <th className="p-3">Categoría</th>
            <th className="p-3">Marca</th>
            <th className="p-3">Modelo</th>
            <th className="p-3">Serie</th>
          </tr>
        </thead>

        <tbody>
          {equipos.length === 0 ? (
            <tr>
              <td colSpan="4" className="p-3 text-gray-500">
                No tienes equipos asignados.
              </td>
            </tr>
          ) : (
            equipos.map(eq => (
              <tr key={eq.id} className="border-b">
                <td className="p-3">{eq.categoria}</td>
                <td className="p-3">{eq.marca}</td>
                <td className="p-3">{eq.modelo}</td>
                <td className="p-3">{eq.serie}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}
