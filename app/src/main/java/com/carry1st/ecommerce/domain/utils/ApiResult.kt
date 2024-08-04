package com.carry1st.ecommerce.domain.utils

sealed interface ApiResult<out D> {
    data class Success<out D>(val data: D) : ApiResult<D>

    data class NetworkError(val error: Throwable) : ApiResult<Nothing>

    data class ClientError(val error: Throwable) : ApiResult<Nothing>

    data class ServerError(val error: Throwable) : ApiResult<Nothing>
}

