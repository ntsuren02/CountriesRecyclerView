package com.example.countriesrecyclerview.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryService {
    private val BASE_URL = "https://gist.githubusercontent.com/"

    fun getCountryService(): CountriesAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CountriesAPI::class.java)
    }
}