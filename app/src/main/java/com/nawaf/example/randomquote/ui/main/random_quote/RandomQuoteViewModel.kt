package com.nawaf.example.randomquote.ui.main.random_quote

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nawaf.example.randomquote.data.DataResult
import com.nawaf.example.randomquote.data.repository.QuoteRepository
import com.nawaf.example.randomquote.ui.BaseViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

class RandomQuoteViewModel @ViewModelInject constructor(
    @ApplicationContext val context: Context,
    private val quoteRepository : QuoteRepository) : BaseViewModel() {

    var quote = quoteRepository.observeRandomQuote()

    init {
        refreshQuote()
    }

    fun refreshQuote() {
        viewModelScope.launch {
            val result = quoteRepository.loadRandomQuote()
            if(result is DataResult.Error)
                showMessage(result.niceMessage(context))
        }
    }

}