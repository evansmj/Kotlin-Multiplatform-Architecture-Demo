package com.oldgoat5.udemo.ui.portfolio

sealed interface PortfolioItem {
    val id: String

    data class PortfolioCardData(val bitcoinPrice: Double, val bitcoin24HChange: Double, val bitcoinHoldingsBtc: Double, override val id: String = "portfolio") : PortfolioItem
    data class CashCardData(val dollarBalance: Double, override val id: String = "cash") : PortfolioItem
    data class VaultCardData(override val id: String = "vault") : PortfolioItem
}
