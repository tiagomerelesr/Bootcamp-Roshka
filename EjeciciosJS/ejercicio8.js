"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var prompt_sync_1 = require("prompt-sync");
var prompt = (0, prompt_sync_1.default)();
var numero;
do {
    numero = prompt("Ingrese un valor: ");
} while (numero < 0);
{
    console.log("Fin!");
}
