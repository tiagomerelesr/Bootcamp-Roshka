
import promptSync from "prompt-sync"
const prompt = promptSync();

let contraseñaReal: string = "12345";
let intentos = 0;

while(intentos < 3){
    let contraseña: string = prompt("Ingresa la contraseña: ");
    if(contraseñaReal === contraseña){
        console.log("¡Correcto! Adivinaste la contraseña.");
        break;
    } else {
        console.log("Contraseña incorrecta.");
        intentos++;
    }
}

if (intentos === 3) {
    console.log("FALLASTE!!!");
}
