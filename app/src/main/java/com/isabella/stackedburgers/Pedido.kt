package com.isabella.stackedburgers

data class Pedido(
    val nome: String,
    val hamburguer: String,
    val pontoCarne: String,
    val observacao: String,
    val adicionais: List<String>
)