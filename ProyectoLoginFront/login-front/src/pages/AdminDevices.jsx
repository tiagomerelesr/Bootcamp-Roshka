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

  const deleteDevice = async (id) => {
    await apiFetch(`/api/device/${id}`, { method: "DELETE" });
    loadDevices();
  };

  useEffect(() => {
    loadDevices();
  }, []);

  return (
    <>
      <h1 className="text-3xl font-bold mb-6">Dispositivos conectados</h1>
      <DeviceTable devices={devices} onDelete={deleteDevice} />
    </>
  );
}
