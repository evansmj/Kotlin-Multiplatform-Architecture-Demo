package com.oldgoat5.udemo.network.user

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

interface IUserDataRepository {
    @NativeCoroutines
    suspend fun getUserData(): UserDataResponse
}
