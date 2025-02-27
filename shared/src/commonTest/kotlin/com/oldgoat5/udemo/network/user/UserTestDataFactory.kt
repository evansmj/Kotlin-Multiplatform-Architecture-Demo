package com.oldgoat5.udemo.network.user

object UserTestDataFactory {
    fun createUserDataResponse(
        name: String = "Michael",
        bitcoinHoldingsSats: Int = 314151337
    ): UserDataResponse {
        return UserDataResponse(name, bitcoinHoldingsSats)
    }
}
