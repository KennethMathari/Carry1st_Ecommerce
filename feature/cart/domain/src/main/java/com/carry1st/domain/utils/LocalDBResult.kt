package com.carry1st.domain.utils

sealed interface LocalDBResult<out D>{
    data class Success<out D>(val data: D): LocalDBResult<D>

    data class Error(val error: Throwable): LocalDBResult<Nothing>
}