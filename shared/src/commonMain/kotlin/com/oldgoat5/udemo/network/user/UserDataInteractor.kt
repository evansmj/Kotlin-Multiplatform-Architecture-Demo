package com.oldgoat5.udemo.network.user

import com.oldgoat5.udemo.network.UDemoException
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class UserDataInteractor(
    private val userDataRepository: IUserDataRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IUserDataInteractor {
    private val _state = MutableStateFlow<UserDataState>(UserDataState.None)

    @NativeCoroutinesState
    override val state = _state.asStateFlow()

    @NativeCoroutines
    override suspend fun getUserData() {
        withContext(coroutineDispatcher) {
            _state.update { UserDataState.Loading }
            try {
                val userData = userDataRepository.getUserData()
                _state.update { UserDataState.Success(userData) }
            } catch (e: Exception) {
                if (e is UDemoException) {
                    _state.update { UserDataState.Error(e.data as UserDataResponse) }
                } else {
                    _state.update { UserDataState.Error(null)}
                }
            }
        }
    }

}
