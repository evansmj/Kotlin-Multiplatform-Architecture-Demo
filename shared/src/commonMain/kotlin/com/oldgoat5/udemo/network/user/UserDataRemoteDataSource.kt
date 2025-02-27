package com.oldgoat5.udemo.network.user

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

class UserDataRemoteDataSource: IUserDataRemoteDataSource {

    @NativeCoroutines
    override suspend fun fetchUserData(): UserDataResponse {
        return UserDataResponse()
    }
}
