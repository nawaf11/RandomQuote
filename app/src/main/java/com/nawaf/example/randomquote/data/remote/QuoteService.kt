package com.nawaf.example.randomquote.data.remote

import com.nawaf.example.randomquote.data.Quote
import retrofit2.http.GET


interface QuoteService {

    companion object {
        const val BASE_URL = "https://programming-quotes-api.herokuapp.com/"
    }

    @GET("quotes/random")
    suspend fun randomQuote() : Quote

}