
import promptSync from "prompt-sync"
const prompt = promptSync();

let numero;
do{
    numero = prompt("Ingrese un valor: ");
} while(numero < 0){
    console.log("Fin!");
}

