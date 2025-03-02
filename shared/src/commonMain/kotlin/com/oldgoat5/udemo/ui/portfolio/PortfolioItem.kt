package com.oldgoat5.udemo.ui.portfolio

sealed interface PortfolioItem {
    val id: String

    data class PortfolioCardData(val bitcoinPrice: String, val bitcoin24HChange: String, val bitcoinHoldingsBtc: String, override val id: String = "portfolio") : PortfolioItem
    data class CashCardData(val dollarBalance: String, override val id: String = "cash") : PortfolioItem
    data class VaultCardData(override val id: String = "vault") : PortfolioItem
}
