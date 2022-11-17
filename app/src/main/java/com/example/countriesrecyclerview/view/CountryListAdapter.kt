package com.example.countriesrecyclerview.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesrecyclerview.R
import com.example.countriesrecyclerview.model.CountryResponse
import kotlinx.android.synthetic.main.item_list.view.*

class CountryListAdapter(var countries: ArrayList<CountryResponse>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<CountryResponse>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val countryName = view.name
        private val countryRegion = view.region
        private val countryCode = view.code
        private val countryCapital = view.capital

        fun bind(countryResponse: CountryResponse) {
            countryName.text = "| " + countryResponse.name + ","
            countryRegion.text = countryResponse.region
            countryCode.text = countryResponse.code + "|"
            countryCapital.text = "| " + countryResponse.capital + "\t\t\t\t\t\t\t\t\t\t|"
        }
    }
}