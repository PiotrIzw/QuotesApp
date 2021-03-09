package com.example.quotesapp.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quotesapp.R
import com.example.quotesapp.data.Quote
import com.example.quotesapp.databinding.ActivityQuotesBinding
import com.example.quotesapp.utilities.InjectorUtils

class QuotesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityQuotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeUi()
    }

    private fun initializeUi(){
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach{ quote ->
                stringBuilder.append("$quote\n\n")
            }

            binding.textViewQuotes.text = stringBuilder.toString()
            binding.buttonAddQuote.setOnClickListener{
                val quote = Quote(binding.editTextQuote.text.toString(), binding.editTextAuthor.text.toString())
                viewModel.addQuote(quote)
                binding.editTextQuote.setText("")
                binding.editTextAuthor.setText("")
            }
        })

    }
}