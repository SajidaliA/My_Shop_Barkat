package com.peacetechsolution.myshopbarkat.data.source.network

import android.content.Context
import com.peacetechsolution.myshopbarkat.util.Constant
import retrofit2.Response

/**
 * Created by Sajid.
 */

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : NetworkResult<T>(data)

    class Error<T>(
        message: String,
        data: T? = null
    ) : NetworkResult<T>(
        data,
        message
    )

    class Loading<T> : NetworkResult<T>()
}

abstract class BaseApiResponse(val context: Context) {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            return when {
                response.isSuccessful -> {
                    val body = response.body()
                    NetworkResult.Success(body)
                }
                response.code() == Constant.UNAUTHORISED -> {
                    error("Unauthorised request")
                }
                else -> error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return error(
                e.message
                    ?: e.toString()
            )
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")
}
