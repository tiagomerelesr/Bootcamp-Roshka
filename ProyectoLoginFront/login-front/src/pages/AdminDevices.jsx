
import { useEffect, useState } from "react";
import { apiFetch } from "../utils/api";
import DeviceTable from "../components/DeviceTable";

export default function AdminDevices() {
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
    <div>
      <h1 className="text-2xl font-bold mb-4">Todos los dispositivos</h1>
      <DeviceTable devices={devices} onDelete={handleDelete} />
    </div>
  );
}
