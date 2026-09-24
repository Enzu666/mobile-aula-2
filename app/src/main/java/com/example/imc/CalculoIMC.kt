package com.example.imc

import androidx.compose.ui.graphics.Color
import kotlin.math.pow

fun calcularIMC(altura: Double, peso: Double): Double {
    return peso / (altura / 100).pow(x = 2.0)
}

fun determinarCategoriaIMC(imc: Double): String {
    return if (imc < 18.5) {
        "Abaixo do peso"
    } else if (imc >= 18.5 && imc < 25.0) {
        "Peso ideal"
    } else if (imc >= 25.0 && imc < 30.0) {
        "Levemente acima do peso"
    } else if (imc >= 30.0 && imc < 35.0) {
        "Obesidade grau I"
    } else if (imc >= 35.0 && imc < 40.0) {
        "Obesidade grau II"
    } else {
        "Obesidade grau III"
    }
}

fun determinarCorIMC(imc: Double): Color {
    return if (imc < 18.5) {
        Color(0xFF2196F3) // azul - abaixo do peso
    } else if (imc >= 18.5 && imc < 25.0) {
        Color(0xFF4CAF50) // verde - peso ideal
    } else if (imc >= 25.0 && imc < 30.0) {
        Color(0xFFFFC107) // amarelo - levemente acima
    } else if (imc >= 30.0 && imc < 35.0) {
        Color(0xFFFF9800) // laranja - obesidade grau I
    } else if (imc >= 35.0 && imc < 40.0) {
        Color(0xFFFF5722) // laranja escuro - obesidade grau II
    } else {
        Color(0xFFF44336) // vermelho - obesidade grau III
    }
}