package com.oldgoat5.udemo.network.user

import com.oldgoat5.udemo.network.UDemoException
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserDataRepository(
    private val userDataRemoteDataSource: IUserDataRemoteDataSource
) : IUserDataRepository {
    private val userDataMutex = Mutex()
    private var cachedUserData: UserDataResponse? = null

    @NativeCoroutines
    override suspend fun getUserData(): UserDataResponse {
        return try {
            if (cachedUserData == null) {
                val networkResult = userDataRemoteDataSource.fetchUserData()
                userDataMutex.withLock {
                    this.cachedUserData = networkResult
                }
            }
            userDataMutex.withLock { this.cachedUserData!! }
        } catch (e: Exception) {
            throw UDemoException(this.cachedUserData, e)
        }
    }
}
