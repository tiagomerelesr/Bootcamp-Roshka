const API_URL = "http://localhost:8080";

export async function apiFetch(endpoint, options = {}) {
  console.log("➡️ Llamando a:", API_URL + endpoint); // DEBUG

  try {
    let headers = {
      "Content-Type": "application/json",
      ...(options.headers || {}),
    };

    // NO agregar token en endpoints públicos
    const isPublicEndpoint =
      endpoint.startsWith("/api/auth/login") ||
      endpoint.startsWith("/api/auth/register") ||
      endpoint.startsWith("/api/auth/refresh");

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

    console.log("⬅ Respuesta recibida:", response); 

    return response;
  } catch (error) {
    console.error("ERROR en apiFetch:", error);
    throw error;
  }
}
