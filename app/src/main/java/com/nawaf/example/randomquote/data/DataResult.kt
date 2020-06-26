package com.nawaf.example.randomquote.data

import android.content.Context
import com.nawaf.example.randomquote.R
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


sealed class DataResult<out T : Any> {

    data class Success<out T : Any>(val data : T) : DataResult<T>()

    data class Error(var errorType : ErrorType, val exceptionMessage : String) : DataResult<Nothing>() {

        /**
         * @return:
         * TRUE: if the error is one of the connection error types (timeout, network/io)
         */
        fun isConnectionError() : Boolean {
            return errorType == ErrorType.NETWORK ||
                    errorType == ErrorType.TIMEOUT
        }

        fun niceMessage(context : Context) : String {
            return when(errorType) {
                ErrorType.NETWORK -> context.getString(R.string.network_error)
                ErrorType.TIMEOUT -> context.getString(R.string.cant_connect_the_server)
                ErrorType.HTTP -> context.getString(R.string.server_response_error)
                ErrorType.UNKNOWN -> context.getString(R.string.unknown_error)
            }
        }
    }

}

enum class ErrorType {
    NETWORK, // IO (IOException)
    TIMEOUT, // Socket (SocketTimeoutException)
    HTTP, // HttpException (http code != 200)
    UNKNOWN //Anything else
}