package com.oldgoat5.udemo.network.user

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesIgnore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeUserDataInteractor : IUserDataInteractor {
    private val _state = MutableStateFlow<UserDataState>(UserDataState.Loading)
    override val state: StateFlow<UserDataState> = _state

    @NativeCoroutinesIgnore
    suspend fun emitSuccess(data: UserDataResponse) {
        _state.emit(UserDataState.Success(data))
    }

    @NativeCoroutinesIgnore
    suspend fun emitError() {
        _state.emit(UserDataState.Error(null))
    }

    @NativeCoroutinesIgnore
    suspend fun emitLoading() {
        _state.emit((UserDataState.Loading))
    }

    override suspend fun getUserData() {
        // no-op
    }
}
