package com.carry1st.ecommerce.data.network.utils

import android.util.Log
import com.carry1st.ecommerce.domain.utils.ApiResult
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.Success(apiCall())
    } catch (e: IOException) {
        Log.e("IOException:", e.toString())
        e.printStackTrace()
        ApiResult.NetworkError(e)
    } catch (e: HttpException) {
        Log.e("Http Error:", e.toString())
        e.printStackTrace()
        extractHttpException(e)
    } catch (e: ConnectException) {
        Log.e("ConnectException:", e.toString())
        e.printStackTrace()
        ApiResult.NetworkError(e)
    }catch (e: Exception) {
        Log.e("Exception:", e.toString())
        e.printStackTrace()
        ApiResult.NetworkError(e)
    }
}
fun extractHttpException(exception: HttpException) = when (exception.code()) {
    in 400..499 -> {
        // Client errors (4xx)
        exception.printStackTrace()
        ApiResult.ClientError(exception)
    }

    in 500..599 -> {
        // Server errors (5xx)
        exception.printStackTrace()
        ApiResult.ServerError(exception)
    }

    else -> {
        exception.printStackTrace()
        ApiResult.NetworkError(exception)
    }
}