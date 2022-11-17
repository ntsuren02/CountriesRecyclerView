package com.example.countriesrecyclerview.model

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("capital")
    val capital: String?
)
