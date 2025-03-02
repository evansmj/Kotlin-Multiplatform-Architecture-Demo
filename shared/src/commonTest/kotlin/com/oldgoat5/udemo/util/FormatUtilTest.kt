package com.oldgoat5.udemo.util

import kotlin.test.Test
import kotlin.test.assertEquals

class FormatUtilTest {
    @Test
    fun `formatDollars formats whole numbers correctly`() {
        assertEquals("$1,000.00", formatDollars(1000.0))
        assertEquals("$123,456.00", formatDollars(123456.0))
    }

    @Test
    fun `formatDollars formats decimal numbers correctly`() {
        assertEquals("$1,234.56", formatDollars(1234.56))
        assertEquals("$99,999.99", formatDollars(99999.99))
    }

    @Test
    fun `formatDollars handles single digit and small values`() {
        assertEquals("$0.99", formatDollars(0.99))
        assertEquals("$5.00", formatDollars(5.0))
    }

    @Test
    fun `formatDollars handles large numbers`() {
        assertEquals("$1,000,000.00", formatDollars(1_000_000.0))
        assertEquals("$10,000,000.75", formatDollars(10_000_000.75))
    }

    @Test
    fun `formatDollars rounds numbers up`() {
        assertEquals("$123.46", formatDollars(123.456))
    }

    @Test
    fun `formatDollars rounds numbers down`() {
        assertEquals("$789.44", formatDollars(789.444))
    }

    @Test
    fun `formatDollars handles negative numbers`() {
        assertEquals("-$1,234.56", formatDollars(-1234.56))
        assertEquals("-$10.00", formatDollars(-10.0))
    }

    @Test
    fun `formatDollars handles zero`() {
        assertEquals("$0.00", formatDollars(0.0))
    }
}
