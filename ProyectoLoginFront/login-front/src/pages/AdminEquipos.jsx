import { useEffect, useState } from "react";
import { apiFetch } from "../utils/api";

export default function AdminEquipos() {
  const [usuarios, setUsuarios] = useState([]);
  const [equipos, setEquipos] = useState([]);

  // Formulario creación
  const [categoria, setCategoria] = useState("");
  const [marca, setMarca] = useState("");
  const [modelo, setModelo] = useState("");
  const [serie, setSerie] = useState("");
  const [userId, setUserId] = useState("");

  // Modal edición
  const [modalOpen, setModalOpen] = useState(false);
  const [equipoEditando, setEquipoEditando] = useState(null);

  // Toast
  const [toast, setToast] = useState(null);

  const showToast = (mensaje, tipo = "success") => {
    setToast({ mensaje, tipo });
    setTimeout(() => setToast(null), 2500);
  };

  // Cargar datos
  const loadUsuarios = async () => {
    const res = await apiFetch("/api/users");
    setUsuarios(await res.json());
  };

  const loadEquipos = async () => {
    const res = await apiFetch("/api/equipos");
    setEquipos(await res.json());
  };

  useEffect(() => {
    loadUsuarios();
    loadEquipos();
  }, []);

  // Crear
  const crearEquipo = async (e) => {
    e.preventDefault();

    const body = { categoria, marca, modelo, serie };

    const res = await apiFetch(`/api/equipos/${userId}`, {
      method: "POST",
      body: JSON.stringify(body),
    });

    if (res.ok) {
      showToast("Equipo creado correctamente");
      loadEquipos();
      setCategoria("");
      setMarca("");
      setModelo("");
      setSerie("");
      setUserId("");
    } else showToast("Error al crear equipo", "error");
  };

  // Borrar
  const borrarEquipo = async (id) => {
    const res = await apiFetch(`/api/equipos/${id}`, { method: "DELETE" });
    if (res.ok) {
      showToast("Equipo eliminado");
      loadEquipos();
    } else showToast("Error al eliminar", "error");
  };

  // Editar
  const abrirModalEditar = (eq) => {
    setEquipoEditando(eq);
    setCategoria(eq.categoria);
    setMarca(eq.marca);
    setModelo(eq.modelo);
    setSerie(eq.serie);
    setUserId(eq.user.id);
    setModalOpen(true);
  };

  const guardarEdicion = async (e) => {
    e.preventDefault();

    const body = { categoria, marca, modelo, serie, user: { id: userId } };

    const res = await apiFetch(`/api/equipos/${equipoEditando.id}`, {
      method: "PUT",
      body: JSON.stringify(body),
    });

    if (res.ok) {
      showToast("Equipo actualizado correctamente");
      setModalOpen(false);
      loadEquipos();
    } else showToast("Error al actualizar", "error");
  };

  return (
    <div>
      <h1 className="text-3xl font-bold mb-2">Equipos</h1>

      <p className="text-gray-600 mb-6">
        Total de equipos:{" "}
        <span className="font-semibold">{equipos.length}</span>
      </p>

      {/* FORMULARIO */}
      <div className="bg-white p-6 shadow-md rounded mb-10">
        <h2 className="text-xl font-semibold mb-4">Crear nuevo equipo</h2>

        <form onSubmit={crearEquipo} className="grid grid-cols-2 gap-4">

          <input className="border p-2 rounded" placeholder="Categoría"
            value={categoria} onChange={(e) => setCategoria(e.target.value)} required />

          <input className="border p-2 rounded" placeholder="Marca"
            value={marca} onChange={(e) => setMarca(e.target.value)} required />

          <input className="border p-2 rounded" placeholder="Modelo"
            value={modelo} onChange={(e) => setModelo(e.target.value)} required />

          <input className="border p-2 rounded" placeholder="Serie"
            value={serie} onChange={(e) => setSerie(e.target.value)} required />

          <select className="border p-2 rounded"
            value={userId} onChange={(e) => setUserId(e.target.value)} required>
            <option value="">Seleccionar usuario</option>
            {usuarios.map(u => (
              <option key={u.id} value={u.id}>
                {u.usuario} ({u.email})
              </option>
            ))}
          </select>

          {/* BOTÓN MÁS CHICO Y CENTRADO */}
          <button
            type="submit"
            className="col-span-2 mx-auto w-48 bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition"
          >
            Crear Equipo
          </button>
        </form>
      </div>

      {/* TABLA CENTRADA Y CON SEPARADORES */}
      <table className="min-w-full bg-white shadow-md rounded overflow-hidden text-center">
        <thead className="bg-gray-200 text-center">
          <tr>
            <th className="p-3 border-r">Categoría</th>
            <th className="p-3 border-r">Marca</th>
            <th className="p-3 border-r">Modelo</th>
            <th className="p-3 border-r">Serie</th>
            <th className="p-3 border-r">Asignado a</th>
            <th className="p-3">Acciones</th>
          </tr>
        </thead>

        <tbody>
          {equipos.map((eq) => (
            <tr key={eq.id} className="border-b">
              <td className="p-3 border-r">{eq.categoria}</td>
              <td className="p-3 border-r">{eq.marca}</td>
              <td className="p-3 border-r">{eq.modelo}</td>
              <td className="p-3 border-r">{eq.serie}</td>
              <td className="p-3 border-r">{eq.user?.usuario}</td>

              <td className="p-3 flex gap-2 justify-center">
                <button
                  className="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600"
                  onClick={() => abrirModalEditar(eq)}
                >
                  Editar
                </button>

                <button
                  className="bg-red-600 text-white px-3 py-1 rounded hover:bg-red-700"
                  onClick={() => borrarEquipo(eq.id)}
                >
                  Borrar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* MODAL */}
      {modalOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center">
          <div className="bg-white p-6 rounded shadow-xl w-96">
            <h2 className="text-xl font-bold mb-4">Editar Equipo</h2>

            <form onSubmit={guardarEdicion}>
              <input className="border p-2 rounded w-full mb-3"
                value={categoria} onChange={(e) => setCategoria(e.target.value)} />

              <input className="border p-2 rounded w-full mb-3"
                value={marca} onChange={(e) => setMarca(e.target.value)} />

              <input className="border p-2 rounded w-full mb-3"
                value={modelo} onChange={(e) => setModelo(e.target.value)} />

              <input className="border p-2 rounded w-full mb-3"
                value={serie} onChange={(e) => setSerie(e.target.value)} />

              <select className="border p-2 rounded w-full mb-3"
                value={userId} onChange={(e) => setUserId(e.target.value)}>
                {usuarios.map(u => (
                  <option key={u.id} value={u.id}>{u.usuario}</option>
                ))}
              </select>

              <div className="flex justify-end gap-4 mt-4">
                <button type="button"
                  className="px-4 py-2 bg-gray-300 rounded"
                  onClick={() => setModalOpen(false)}>
                  Cancelar
                </button>

                <button type="submit"
                  className="px-4 py-2 bg-blue-600 text-white rounded">
                  Guardar cambios
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* TOAST */}
      {toast && (
        <div className="fixed bottom-6 right-6 px-5 py-3 bg-white shadow-xl rounded-lg border flex items-center gap-3 animate-fade">
          <div
            className={
              toast.tipo === "success"
                ? "bg-green-600 text-white px-2 py-1 rounded"
                : "bg-red-600 text-white px-2 py-1 rounded"
            }
          >
            {toast.tipo === "success" ? "✔" : "⚠"}
          </div>

          <span className="font-medium">{toast.mensaje}</span>

          <button
            className="ml-3 text-gray-600 hover:text-black"
            onClick={() => setToast(null)}
          >
            ✖
          </button>
        </div>
      )}

    </div>
  );
}
