
import promptSync from "prompt-sync";
const prompt = promptSync();

let dia: string = prompt("Ingresa un día de la semana: ").toLowerCase();

if (
    dia === "lunes" ||
    dia === "martes" ||
    dia === "miercoles" ||
    dia === "miércoles" ||
    dia === "jueves" ||
    dia === "viernes"
) {
    console.log("Es un día laboral.");
} 
else if (
    dia === "sabado" ||
    dia === "sábado" ||
    dia === "domingo"
) {
    console.log("No es un día laboral.");
} 
else {
    console.log("Día no válido.");
}