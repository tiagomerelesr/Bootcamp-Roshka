import { useEffect, useState } from "react";
import { apiFetch } from "../utils/api";
import { getUserInfo } from "../utils/auth";
import DeviceTable from "../components/DeviceTable";

export default function UserDevices() {
  const { id } = getUserInfo();
  const [devices, setDevices] = useState([]);

  const loadDevices = async () => {
    const res = await apiFetch(`/api/device/user/id/${id}`);
    setDevices(await res.json());
  };

  useEffect(() => {
    loadDevices();
  }, []);

  return (
    <div>
      <h1 className="text-2xl font-bold mb-6">Dispositivos conectados</h1>
      <DeviceTable devices={devices} showDelete={false} />
    </div>
  );
}
