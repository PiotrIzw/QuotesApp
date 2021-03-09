package com.example.quotesapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeQuoteDAO(){
    private val quotesList = mutableListOf<Quote>()
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.value = quotesList
    }

    fun addQuote(quote: Quote){
        quotesList.add(quote)
        quotes.value = quotesList
    }

    fun getQuotes() = quotes as LiveData<List<Quote>>
}