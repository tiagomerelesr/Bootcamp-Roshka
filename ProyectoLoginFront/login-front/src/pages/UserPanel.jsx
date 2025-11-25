import { useState, useEffect } from "react";
import { apiFetch } from "../utils/api";
import { getUsuario } from "../utils/auth";

import LogoutButton from "../components/LogoutButton";
import DeviceFolder from "../components/DeviceFolder";
import DeviceTable from "../components/DeviceTable";

export default function UserPanel() {
  const usuario = getUsuario();   // usamos solo el usuario
  const [devices, setDevices] = useState([]);

  // Cargar dispositivos del usuario
  const loadDevices = async () => {
    try {
      const res = await apiFetch(`/api/device/user/${usuario}`);
      const data = await res.json();
      setDevices(data);
    } catch (e) {
      console.error("Error cargando dispositivos", e);
    }
  };

  useEffect(() => {
    loadDevices();
  }, []);

  // Crear dispositivo
  const handleCreate = async (device) => {
    await apiFetch(`/api/device/user/${usuario}`, {
      method: "POST",
      body: JSON.stringify(device),
    });

    loadDevices();
  };

  // Borrar dispositivo
  const handleDelete = async (id) => {
    await apiFetch(`/api/device/${id}`, {
      method: "DELETE",
    });

    loadDevices();
  };

  return (
    <div className="p-6">

      {/* Header con Logout */}
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Dispositivos de {usuario}</h1>
        <LogoutButton />
      </div>

      {/* Formulario para crear */}
      <DeviceFolder onCreate={handleCreate} />

      {/* Tabla */}
      <DeviceTable devices={devices} onDelete={handleDelete} />
    </div>
  );
}
