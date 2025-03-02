package com.oldgoat5.udemo.util

actual fun formatDollars(amount: Double): String {
    val formatted = "%,.2f".format(kotlin.math.abs(amount))
    return if (amount < 0) "-$$formatted" else "$$formatted"
}
