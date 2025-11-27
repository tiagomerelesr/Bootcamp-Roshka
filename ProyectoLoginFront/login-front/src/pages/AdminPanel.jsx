import { getUserInfo } from "../utils/auth";

export default function AdminPanel() {
  const user = getUserInfo();

  return (
    <div>
      <h1 className="text-3xl font-bold mb-4">Bienvenido {user.usuario}</h1>
      <p className="text-gray-700">Selecciona una opción del menú para continuar.</p>
    </div>
  );
}
