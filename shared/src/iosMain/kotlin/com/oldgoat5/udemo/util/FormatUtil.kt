package com.oldgoat5.udemo.util

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle
import platform.Foundation.currentLocale

actual fun formatDollars(amount: Double): String {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterDecimalStyle
        locale = NSLocale.currentLocale
        minimumFractionDigits = 2u
        maximumFractionDigits = 2u
    }
    val formatted = formatter.stringFromNumber(NSNumber(kotlin.math.abs(amount))) ?: amount.toString()
    return if (amount < 0) "-$$formatted" else "$$formatted"
}
