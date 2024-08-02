package com.carry1st.ecommerce.data.network.utils

import android.util.Log
import com.carry1st.ecommerce.domain.utils.NetworkResult
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        NetworkResult.Success(apiCall())
    } catch (e: IOException) {
        Log.e("IOException:", e.toString())
        e.printStackTrace()
        NetworkResult.NetworkError(e.toString()) // Handle IO exceptions
    } catch (e: HttpException) {
        Log.e("Http Error:", e.toString())
        e.printStackTrace()
        extractHttpException(e)
    } catch (e: ConnectException) {
        Log.e("ConnectException:", e.toString())
        e.printStackTrace()
        NetworkResult.NetworkError(e.toString())
    }catch (e: Exception) {
        Log.e("Exception:", e.toString())
        e.printStackTrace()
        NetworkResult.NetworkError(e.toString())
    }
}
fun extractHttpException(exception: HttpException) = when (exception.code()) {
    in 400..499 -> {
        // Client errors (4xx)
        exception.printStackTrace()
        NetworkResult.ClientError(exception.toString())
    }

    in 500..599 -> {
        // Server errors (5xx)
        exception.printStackTrace()
        NetworkResult.ServerError(exception.toString())
    }

    else -> {
        exception.printStackTrace()
        NetworkResult.NetworkError(exception.toString())
    }
}