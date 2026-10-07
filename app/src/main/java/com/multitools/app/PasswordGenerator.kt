package com.multitools.app

import java.security.SecureRandom

object PasswordGenerator {
    private const val CHARACTERS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789!@#$%&*"
    private val random = SecureRandom()
    fun generate(length: Int): String {
        val safeLength = length.coerceIn(8, 64)
        return buildString { repeat(safeLength) { append(CHARACTERS[random.nextInt(CHARACTERS.length)]) } }
    }
}
