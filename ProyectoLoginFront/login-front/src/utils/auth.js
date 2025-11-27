// =========================
// AUTH — GESTIÓN DE LOGIN
// =========================

// Guarda el token
export function saveToken(token) {
  sessionStorage.setItem("token", token);
}

// Obtiene token
export function getToken() {
  return sessionStorage.getItem("token");
}

// Guarda datos del usuario
export function saveUserInfo({ rol, usuario, id }) {
  sessionStorage.setItem("rol", rol);
  sessionStorage.setItem("usuario", usuario);
  sessionStorage.setItem("userId", id);
}

// RETORNA UN SOLO OBJETO CON TODA LA INFO
export function getUserInfo() {
  return {
    rol: sessionStorage.getItem("rol"),
    usuario: sessionStorage.getItem("usuario"),
    id: sessionStorage.getItem("userId"),
  };
}

// Limpia todo
export function clearAuth() {
  sessionStorage.removeItem("token");
  sessionStorage.removeItem("rol");
  sessionStorage.removeItem("usuario");
  sessionStorage.removeItem("userId");
}
