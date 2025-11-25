import { useState } from "react";

export default function DeviceForm({ onCreate }) {
  const [browser, setBrowser] = useState("");
  const [os, setOs] = useState("");
  const [deviceType, setDeviceType] = useState("");
  const [ipAddress, setIp] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    onCreate({ browser, os, deviceType, ipAddress });

    // limpiar inputs
    setBrowser("");
    setOs("");
    setDeviceType("");
    setIp("");
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="bg-white shadow-xl rounded-xl p-6 mb-6"
    >
      <h2 className="text-xl font-bold mb-4 text-gray-800">
        Registrar nuevo dispositivo
      </h2>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">

        <input
          type="text"
          value={browser}
          onChange={(e) => setBrowser(e.target.value)}
          placeholder="Browser"
          className="p-3 border rounded-lg"
          required
        />

        <input
          type="text"
          value={os}
          onChange={(e) => setOs(e.target.value)}
          placeholder="Sistema Operativo"
          className="p-3 border rounded-lg"
          required
        />

        <input
          type="text"
          value={deviceType}
          onChange={(e) => setDeviceType(e.target.value)}
          placeholder="Tipo de dispositivo"
          className="p-3 border rounded-lg"
          required
        />

        <input
          type="text"
          value={ipAddress}
          onChange={(e) => setIp(e.target.value)}
          placeholder="Dirección IP"
          className="p-3 border rounded-lg"
          required
        />

      </div>

      <button
        type="submit"
        className="mt-4 bg-blue-600 hover:bg-blue-700 text-white py-2 px-5 rounded-lg"
      >
        Crear dispositivo
      </button>
    </form>
  );
}
