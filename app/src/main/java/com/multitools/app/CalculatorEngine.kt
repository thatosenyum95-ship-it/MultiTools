package com.multitools.app

import kotlin.math.roundToInt

object CalculatorEngine {
    fun evaluate(input: String): String {
        val clean = input.replace(" ", "")
        if (clean.isBlank() || !clean.matches(Regex("[0-9.+*/-]+"))) return "Input tidak valid"
        return try {
            val values = mutableListOf<Double>()
            val ops = mutableListOf<String>()
            val tokens = Regex("[0-9.]+|[+*/-]").findAll(clean).map { it.value }.toList()
            if (tokens.joinToString("") != clean) return "Input tidak valid"
            fun precedence(op: String) = if (op == "+" || op == "-") 1 else 2
            fun apply() {
                if (values.size < 2 || ops.isEmpty()) return
                val b = values.removeAt(values.lastIndex)
                val a = values.removeAt(values.lastIndex)
                val op = ops.removeAt(ops.lastIndex)
                values.add(when (op) {
                    "+" -> a + b
                    "-" -> a - b
                    "*" -> a * b
                    "/" -> if (b == 0.0) Double.NaN else a / b
                    else -> Double.NaN
                })
            }
            tokens.forEach { token ->
                if (token[0].isDigit() || token[0] == '.') values.add(token.toDouble())
                else {
                    while (ops.isNotEmpty() && precedence(ops.last()) >= precedence(token)) apply()
                    ops.add(token)
                }
            }
            while (ops.isNotEmpty()) apply()
            if (values.size != 1 || values[0].isNaN()) "Tidak dapat dihitung"
            else if (values[0] % 1.0 == 0.0) values[0].roundToInt().toString() else values[0].toString()
        } catch (_: Exception) {
            "Input tidak valid"
        }
    }
}
