const API_URL = "http://localhost:8080";

export async function apiFetch(endpoint, options = {}) {
  console.log("➡️ Llamando a:", API_URL + endpoint); // DEBUG

  try {
    const token = sessionStorage.getItem("token");

    const response = await fetch(API_URL + endpoint, {
      ...options,
      headers: {
        "Content-Type": "application/json",
        ...(options.headers || {}),
        ...(token ? { Authorization: `Bearer ${token}` } : {}),
      },
    });

    console.log("⬅️ Respuesta recibida:", response); // DEBUG

    return response;
  } catch (error) {
    console.error("❌ ERROR en apiFetch:", error);
    throw error;
  }
}
