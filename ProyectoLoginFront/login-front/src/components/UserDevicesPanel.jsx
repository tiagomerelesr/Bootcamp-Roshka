import { useState, useEffect } from "react";
import { apiFetch } from "../utils/api";
import { getUsuario } from "../utils/auth";
import LogoutButton from "./LogoutButton";
import DeviceFolder from "./DeviceFolder";
import DeviceTable from "./DeviceTable";

export default function UserDevicesPanel() {
  const usuario = getUsuario();
  const [devices, setDevices] = useState([]);

  // cargar dispositivos
  const loadDevices = async () => {
    try {
      const res = await apiFetch(`/api/device/user/${usuario}`);
      const data = await res.json();
      setDevices(data);
    } catch (err) {
      console.error("Error cargando dispositivos:", err);
    }
  };

  useEffect(() => {
    loadDevices();
  }, []);

  // crear device
  const handleCreate = async (device) => {
    try {
      await apiFetch(`/api/device/user/${usuario}`, {
        method: "POST",
        body: JSON.stringify(device),
      });
      loadDevices();
    } catch (err) {
      console.error("Error creando dispositivo:", err);
    }
  };

  // borrar device
  const handleDelete = async (id) => {
    try {
      await apiFetch(`/api/device/${id}`, {
        method: "DELETE",
      });
      loadDevices();
    } catch (err) {
      console.error("Error borrando dispositivo:", err);
    }
  };

  return (
    <div className="flex">

      {/* SIDEBAR */}
      <aside className="w-64 bg-gray-900 text-white min-h-screen p-6">
        <h2 className="text-xl font-bold mb-8">Usuario</h2>

        <nav className="flex flex-col space-y-4">
          <a href="/user" className="text-gray-300 hover:text-white">Inicio</a>
          <a href="/user/devices" className="text-gray-300 hover:text-white">Mis dispositivos</a>

          <LogoutButton />
        </nav>
      </aside>

      {/* CONTENIDO */}
      <main className="flex-1 p-10 bg-gray-100 min-h-screen">
        <h1 className="text-3xl font-bold mb-6">Dispositivos de {usuario}</h1>

        <DeviceFolder onCreate={handleCreate} />
        <DeviceTable devices={devices} onDelete={handleDelete} />
      </main>
    </div>
  );
}
