import { useEffect, useState } from "react";
import { apiFetch } from "../utils/api";
import DeviceTable from "../components/DeviceTable";

export default function AdminPanel() {
  const [devices, setDevices] = useState([]);

  const loadDevices = async () => {
    const res = await apiFetch("/api/device");
    const data = await res.json();
    setDevices(data);
  };

  useEffect(() => {
    loadDevices();
  }, []);

  const handleDelete = async (id) => {
    await apiFetch(`/api/device/${id}`, { method: "DELETE" });
    loadDevices();
  };

  return (
    <div className="p-6">
      <h2 className="text-2xl font-semibold mb-6">Todos los dispositivos</h2>
      <DeviceTable devices={devices} onDelete={handleDelete} />
    </div>
  );
}
