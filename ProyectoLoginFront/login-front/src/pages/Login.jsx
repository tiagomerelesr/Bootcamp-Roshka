import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { apiFetch } from "../utils/api";
import { saveToken, saveUserInfo } from "../utils/auth";

export default function Login() {
  const [usuario, setUsuario] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {

      const response = await apiFetch("/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          usuario,
          password
        }),
      });

      if (!response.ok) {
        setError("Credenciales incorrectas.");
        return;
      }

      const data = await response.json();

      saveToken(data.token);
      saveUserInfo({ rol: data.rol, usuario: data.usuario });

      if (data.rol === "ADMIN") {
          navigate("/admin");
      } else {
          navigate("/user");
      }

    } catch (err) {
      console.log(err);
      setError("Error de conexión con el servidor.");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-600 to-blue-900 px-4">
      <div className="bg-white w-full max-w-md rounded-2xl shadow-2xl p-8">
        
        <h2 className="text-3xl font-bold text-center text-blue-700 mb-1">
          Bienvenido
        </h2>
        <p className="text-center text-gray-500 mb-6">
          Inicia sesión con tu cuenta
        </p>

        {error && (
          <div className="bg-red-100 text-red-700 px-4 py-2 rounded-lg mb-4 text-center">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} autoComplete="off">

          <label className="block mb-3">
            <span className="text-gray-700 font-medium">Usuario</span>
            <input
              type="text"
              autoComplete="username"
              className="w-full mt-1 px-4 py-2 border rounded-lg"
              value={usuario}
              onChange={(e) => setUsuario(e.target.value)}
              required
            />
          </label>

          <label className="block mb-4">
            <span className="text-gray-700 font-medium">Contraseña</span>
            <input
              type="password"
              autoComplete="new-password"
              className="w-full mt-1 px-4 py-2 border rounded-lg"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </label>

          <button
            type="submit"
            className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 rounded-lg text-lg"
          >
            Ingresar
          </button>

        </form>
      </div>
    </div>
  );
}
