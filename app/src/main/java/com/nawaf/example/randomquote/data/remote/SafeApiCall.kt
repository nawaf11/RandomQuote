package com.nawaf.example.randomquote.data.remote

import com.nawaf.example.randomquote.data.DataResult
import com.nawaf.example.randomquote.data.ErrorType
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 *  To handle exceptions in Retrofit call and Categorize the error types -> **[ErrorType]**
 */
object SafeApiCall {
    suspend fun <T : Any> start(apiCall : suspend () -> T) : DataResult<T> {

        try {

            val result = apiCall()
            return DataResult.Success(result)

        }

        catch (t : Throwable) {
            when(t) {

                is IOException ->
                    return DataResult.Error(ErrorType.NETWORK, t.message ?: t.toString())

                is SocketTimeoutException ->
                    return DataResult.Error(ErrorType.TIMEOUT, t.message ?: t.toString())

                is HttpException -> {
                    val httpCode = t.code() // this is http code (ex: 500 internal server error, 404 ...)
                    return DataResult.Error(ErrorType.HTTP,
                        t.message() +", http code $httpCode")
                }
                else ->
                    return DataResult.Error(ErrorType.UNKNOWN, t.message ?:t.toString())
            }

        }
    }

}