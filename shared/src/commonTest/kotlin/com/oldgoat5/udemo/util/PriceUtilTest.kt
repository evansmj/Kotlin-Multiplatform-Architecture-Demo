package com.oldgoat5.udemo.util

import com.oldgoat5.udemo.util.calculateDollarBalance
import com.oldgoat5.udemo.util.satsToBtc
import kotlin.test.Test
import kotlin.test.assertEquals

class UtilFunctionsTest {

    @Test
    fun `calculateDollarBalance should return correct balance`() {
        val bitcoinPriceUsd = 50_000.0
        val holdingsSats = 2_000_000

        val result = calculateDollarBalance(bitcoinPriceUsd, holdingsSats)

        val expected = (holdingsSats / 100_000_000.0) * bitcoinPriceUsd
        assertEquals(expected, result, 0.0001)
    }

    @Test
    fun `satsToBtc should correctly convert satoshis to BTC`() {
        val holdingsSats = 2_000_000
        val result = satsToBtc(holdingsSats)
        val expected = holdingsSats / 100_000_000.0
        assertEquals(expected, result, 0.0001)
    }
}
