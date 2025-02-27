package com.oldgoat5.udemo.network.user

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.StateFlow

interface IUserDataInteractor {
    @NativeCoroutinesState
    val state: StateFlow<UserDataState>
    @NativeCoroutines
    suspend fun getUserData()
}
