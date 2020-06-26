package com.nawaf.example.randomquote.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.nawaf.example.randomquote.data.DataResult
import com.nawaf.example.randomquote.data.Quote
import com.nawaf.example.randomquote.data.local.dao.QuoteDao
import com.nawaf.example.randomquote.data.remote.QuoteService
import com.nawaf.example.randomquote.data.remote.SafeApiCall

class QuoteRepository constructor(private val appService: QuoteService, private val quoteDao: QuoteDao) {

    /**
     * This will delete all items, then insert this quote
     * Since in current requirement we need only a single quote.
     */
    private suspend fun makeThisOnlyItemInTheTable(quote: Quote) {
        quoteDao.deleteAll()
        quoteDao.insert(quote)
    }

    /**
     * This will load a random quote, then cache it in the local Database.
     */
    suspend fun loadRandomQuote(): DataResult<Quote> {
        val result = SafeApiCall.start { appService.randomQuote() }

        when(result) {
            is DataResult.Success -> {
                makeThisOnlyItemInTheTable(result.data)
            }
        }
        return result
    }

    fun observeRandomQuote(): LiveData<Quote?> {
        return quoteDao.observeSingleQuote()
    }

}