package com.multitools.app

import kotlin.random.Random

object PasswordGenerator {
    private const val CHARACTERS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789!@#$%&*"

    fun generate(length: Int): String {
        val safeLength = length.coerceIn(8, 64)
        return buildString {
            repeat(safeLength) { append(CHARACTERS[Random.nextInt(CHARACTERS.length)]) }
        }
    }
}
