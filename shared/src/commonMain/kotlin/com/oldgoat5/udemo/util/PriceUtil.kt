package com.oldgoat5.udemo.util

fun calculateDollarBalance(bitcoinPriceUsd: Double, holdingsSats: Int): Double {
    val bitcoin = holdingsSats / 100_000_000.0
    val dollarBalance = bitcoinPriceUsd * bitcoin
    return dollarBalance
}

fun satsToBtc(holdingsSats: Int): Double {
    return holdingsSats / 100_000_000.0
}
