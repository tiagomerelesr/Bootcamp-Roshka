export default function DeviceTable({ devices }) {
  return (
    <table className="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
      <thead>
        <tr className="bg-gray-200 text-left">
          <th className="p-3">Browser</th>
          <th className="p-3">OS</th>
          <th className="p-3">Tipo</th>
          <th className="p-3">IP</th>
          <th className="p-3">Activo</th>
        </tr>
      </thead>

      <tbody>
        {devices.map((d) => (
          <tr key={d.id} className="border-b">
            <td className="p-3">{d.browser}</td>
            <td className="p-3">{d.os}</td>
            <td className="p-3">{d.deviceType}</td>
            <td className="p-3">{d.ipAddress}</td>

            {/* 🟢 Badge Activo / 🔴 Inactivo */}
            <td className="p-3">
              {d.active ? (
                <span className="px-3 py-1 rounded-full bg-green-100 text-green-700 text-sm">
                  Activo
                </span>
              ) : (
                <span className="px-3 py-1 rounded-full bg-red-100 text-red-700 text-sm">
                  Inactivo
                </span>
              )}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
