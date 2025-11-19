
import promptSync from "prompt-sync"
const prompt = promptSync();

let valor = prompt("Ingrese un número: ");
let numero = Number(valor);
numero % 2
if(numero % 2 === 0){
    console.log("Es divisible");
} else{
    console.log("No es divisible");
}