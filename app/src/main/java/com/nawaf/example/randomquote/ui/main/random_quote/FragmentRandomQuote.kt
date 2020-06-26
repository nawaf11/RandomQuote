package com.nawaf.example.randomquote.ui.main.random_quote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nawaf.example.randomquote.data.Quote
import com.nawaf.example.randomquote.databinding.RandomQuoteFragmentBinding
import com.nawaf.example.randomquote.utility.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentRandomQuote : Fragment() {

    lateinit var binding: RandomQuoteFragmentBinding

    val viewModel by viewModels<RandomQuoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RandomQuoteFragmentBinding.inflate(inflater)

        setupUi()
        setupViewModel()

        return binding.root
    }

    private fun setupViewModel() {

        viewModel.showMsgEvent.observe(viewLifecycleOwner,
            Observer {
                it.getContentIfNotHandled()?.let { msg -> toast(msg) }
            })

        viewModel.quote.observe(viewLifecycleOwner,
            Observer { quote ->
                quote?.let { showQuote(it) }
            })

    }

    private fun setupUi() {
        binding.refreshQuote.setOnClickListener {
            viewModel.refreshQuote()
        }
    }

    private fun showQuote(quote: Quote) {
        binding.author.text = quote.author
        binding.quoteContent.text = quote.content

    }

}