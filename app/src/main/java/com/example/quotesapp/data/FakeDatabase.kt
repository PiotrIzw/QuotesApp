package com.example.quotesapp.data

class FakeDatabase private constructor(){
    var quoteDAO = FakeQuoteDAO()
        private set

    companion object{
        @Volatile private var instance : FakeDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: FakeDatabase().also { instance = it }
            }
    }
}