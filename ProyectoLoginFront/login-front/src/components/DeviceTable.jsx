
export default function DeviceTable({ devices, onDelete }) {
  return (
    <div className="bg-white shadow-xl rounded-xl overflow-hidden">
      <table className="w-full text-left border-collapse">
        <thead className="bg-gray-200">
          <tr>
            <th className="p-3 font-semibold text-gray-700">Browser</th>
            <th className="p-3 font-semibold text-gray-700">OS</th>
            <th className="p-3 font-semibold text-gray-700">Tipo</th>
            <th className="p-3 font-semibold text-gray-700">IP</th>
            <th className="p-3 font-semibold text-gray-700">Último Login</th>
            <th className="p-3 font-semibold text-gray-700"></th>
          </tr>
        </thead>

        <tbody>
          {devices.length === 0 ? (
            <tr>
              <td colSpan="6" className="p-4 text-center text-gray-500">
                No hay dispositivos registrados.
              </td>
            </tr>
          ) : (
            devices.map((d) => (
              <tr
                key={d.id}
                className="border-t hover:bg-gray-100 transition"
              >
                <td className="p-3">{d.browser}</td>
                <td className="p-3">{d.os}</td>
                <td className="p-3">{d.deviceType}</td>
                <td className="p-3">{d.ipAddress}</td>
                <td className="p-3 text-sm text-gray-600">
                  {new Date(d.lastLogin).toLocaleString()}
                </td>
                <td className="p-3">
                  <button
                    onClick={() => onDelete(d.id)}
                    className="bg-red-600 text-white px-3 py-1 rounded-md hover:bg-red-700"
                  >
                    Borrar
                  </button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}
