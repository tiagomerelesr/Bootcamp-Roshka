
// auth.js

export function saveToken(token) {
  sessionStorage.setItem("token", token);
}

export function getToken() {
  return sessionStorage.getItem("token");
}

export function removeToken() {
  sessionStorage.removeItem("token");
  sessionStorage.removeItem("rol");
  sessionStorage.removeItem("usuario");
}

export function saveUserInfo({ rol, usuario }) {
  if (rol) sessionStorage.setItem("rol", rol);
  if (usuario) sessionStorage.setItem("usuario", usuario);
}

export function getRol() {
  return sessionStorage.getItem("rol");
}

export function getUsuario() {
  return sessionStorage.getItem("usuario");
}

export function clearAuth() {
  sessionStorage.removeItem("token");
  sessionStorage.removeItem("rol");
  sessionStorage.removeItem("usuario");
}

export function getUserInfo() {
  return {
    rol: sessionStorage.getItem("rol"),
    usuario: sessionStorage.getItem("usuario"),
  };
}

