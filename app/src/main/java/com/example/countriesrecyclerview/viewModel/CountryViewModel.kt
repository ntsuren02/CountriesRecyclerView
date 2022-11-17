package com.example.countriesrecyclerview.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesrecyclerview.model.CountryResponse
import com.example.countriesrecyclerview.model.CountryService
import kotlinx.coroutines.*

class CountryViewModel: ViewModel() {

    val countriesService = CountryService.getCountryService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler {coroutineContext, throwable ->
        onError("Exception : ${throwable.localizedMessage}")
    }
    val countries = MutableLiveData<List<CountryResponse>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    fun refresh() {
        fetchCountries()
    }
    private fun fetchCountries() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = countriesService.getCountries()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    countries.value = response.body()
                    countryLoadError.value = null
                    loading.value = false
                }else {
                    onError("Error:${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        countryLoadError.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}