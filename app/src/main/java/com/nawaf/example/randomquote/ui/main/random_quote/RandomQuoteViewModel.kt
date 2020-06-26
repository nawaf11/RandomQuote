package com.nawaf.example.randomquote.ui.main.random_quote

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    /**
     * To avoid calling load while, there's a request already executing ..
     */
    private var _isLoading = MutableLiveData(false)
    var isLoading : LiveData<Boolean> = _isLoading

    init {
        refreshQuote()
    }

    fun refreshQuote() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = quoteRepository.loadRandomQuote()
            _isLoading.value = false
            if(result is DataResult.Error)
                showMessage(result.niceMessage(context))
        }
    }

}