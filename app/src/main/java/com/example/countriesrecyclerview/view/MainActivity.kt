package com.example.countriesrecyclerview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesrecyclerview.R
import com.example.countriesrecyclerview.viewModel.CountryViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var countryViewModel: CountryViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        countryViewModel.refresh()
        countryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        countryViewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                countryList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        })
        countryViewModel.countryLoadError.observe(this, Observer { isError ->
        })
        countryViewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                if (it) {
                    countryList.visibility = View.GONE
                }
            }
        })
    }

}