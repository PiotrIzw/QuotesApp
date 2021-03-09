package com.example.quotesapp.data

class QuoteRepository private constructor(private val quoteDAO: FakeQuoteDAO){

    fun addQuote(quote: Quote){
        quoteDAO.addQuote(quote)
    }

    fun getQuotes() = quoteDAO.getQuotes()

    companion object{
        @Volatile private var instance: QuoteRepository? = null

        fun getInstance(quoteDAO: FakeQuoteDAO) =
            instance ?: synchronized(this){
                instance ?: QuoteRepository(quoteDAO).also { instance = it }
            }
    }
}