const API_URL = "http://localhost:8080";

export async function apiFetch(endpoint, options = {}) {

  let headers = {
    "Content-Type": "application/json",
    "User-Agent": navigator.userAgent,
    ...(options.headers || {}),
  };

  const isPublicEndpoint =
    endpoint.startsWith("/api/auth/login");

  if (!isPublicEndpoint) {
    const token = sessionStorage.getItem("token");
    if (token) {
      headers.Authorization = `Bearer ${token}`;
    }
  }

  const response = await fetch(API_URL + endpoint, {
    ...options,
    headers,
  });

  // NO HACEMOS LOGOUT AUTOMÁTICO
  if (response.status === 401) {
    console.warn("401 – Token ausente o inválido PERO no hacemos logout");
    return response;
  }

  if (response.status === 403) {
    console.warn("403 – Rol sin permisos PERO no hacemos logout");
    return response;
  }

  return response;
}
