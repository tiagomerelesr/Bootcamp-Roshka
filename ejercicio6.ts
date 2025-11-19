
import promptSync from "prompt-sync"
const prompt = promptSync();

console.log("Bienvenido a nuestro tienda!")
let producto: string = prompt("Ingrese el producto: ")
let valorIngresado = prompt("Ingrese el precio del producto: $");
let precio = parseFloat(valorIngresado);
const IVA=0.10;
let precioFinal = precio + (precio * IVA);
console.log("Precio final del producto con IVA: $"+ precioFinal);
console.log("Gracias por su compra!");