import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { apiFetch } from "../utils/api.js";
import { saveToken } from "../utils/auth.js";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

const handleSubmit = async (e) => {
  e.preventDefault();
  setError("");

  console.log("HANDLE SUBMIT EJECUTADO!");
  console.log("Email:", email);
  console.log("Password:", password);

  try {
    console.log("Enviando request al backend...");

    const response = await apiFetch("/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });

    console.log("Respuesta del backend:", response);

    if (!response.ok) {
      setError("Credenciales incorrectas.");
      return;
    }

    const data = await response.json();
    console.log("DATA RECIBIDA:", data);

    if (!data.token) {
      setError("Error: el backend no devolvió un token.");
      return;
    }

    saveToken(data.token);
    navigate("/home");

  } catch (err) {
    console.log("ERROR en Login:", err);
    setError("Error de conexión con el servidor.");
  }
};


  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-600 to-blue-900 px-4">
      <div className="bg-white w-full max-w-md rounded-2xl shadow-2xl p-8">

        <h2 className="text-3xl font-bold text-center text-blue-700 mb-1">ROSHKA</h2>
        <p className="text-center text-gray-500 mb-6">Inicia sesión</p>

        {error && (
          <div className="bg-red-100 text-red-700 px-4 py-2 rounded-lg mb-4 text-center">
            {error}
          </div>
        )}

        {/* FORMULARIO COMPLETO Y CORRECTO */}
        <form onSubmit={handleSubmit} autoComplete="off">

          <label className="block mb-3">
            <span className="text-gray-700 font-medium">Usuario</span>
            <input
              type="email"
              autoComplete="email"
              className="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </label>

          <label className="block mb-4">
            <span className="text-gray-700 font-medium">Contraseña</span>
            <input
              type="password"
              autoComplete="new-password"
              className="w-full mt-1 px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </label>

          <button
            type="submit"
            className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 rounded-lg text-lg shadow-lg transition-all"
          >
            Ingresar
          </button>

        </form>
      </div>
    </div>
  );
}
