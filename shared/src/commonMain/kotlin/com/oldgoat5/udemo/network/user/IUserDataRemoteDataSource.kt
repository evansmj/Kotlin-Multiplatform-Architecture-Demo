package com.oldgoat5.udemo.network.user

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

interface IUserDataRemoteDataSource {

    @NativeCoroutines
    suspend fun fetchUserData(): UserDataResponse
}
