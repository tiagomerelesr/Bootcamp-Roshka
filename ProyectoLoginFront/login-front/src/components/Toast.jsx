
import { useEffect } from "react";

export default function Toast({ message, type = "success", onClose }) {

  useEffect(() => {
    const timer = setTimeout(onClose, 2500);
    return () => clearTimeout(timer);
  }, [onClose]);

  return (
    <div className={`fixed bottom-6 right-6 px-4 py-3 rounded shadow-lg text-white flex items-center gap-3
        ${type === "success" ? "bg-green-600" : "bg-red-600"}
      `}
    >
      <span>{message}</span>

      {/* Botón de cierre */}
      <button
        onClick={onClose}
        className="text-white font-bold ml-3 hover:text-gray-200"
      >
        ✕
      </button>
    </div>
  );
}
