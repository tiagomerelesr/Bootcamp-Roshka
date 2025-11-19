
import promptSync from "prompt-sync"
const prompt = promptSync();

let nombre: string = prompt("Ingresa tu nombre:");


console.log(`Bienvenido ${nombre}`);