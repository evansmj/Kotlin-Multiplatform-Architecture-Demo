package com.oldgoat5.udemo.network.user

sealed interface UserDataState {
    data object None: UserDataState
    data object Loading: UserDataState
    class Error(val cachedUserData: UserDataResponse?) : UserDataState
    class Success(val userData: UserDataResponse) : UserDataState
}
