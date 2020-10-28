package com.zariff.rovrtest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zariff.rovrtest.R
import com.zariff.rovrtest.model.CountryData
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(private val countryData: ArrayList<CountryData>) : RecyclerView.Adapter<CountryListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))
    }

    override fun getItemCount(): Int {
        return countryData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val country = countryData[position]

        country.let {
            holder.countryName.text = it.country
            holder.txtNewCase.text = it.newConfirmed.toString()
            holder.txtNewDeath.text = it.newDeaths.toString()
            holder.txtTotalCase.text = it.totalConfirmed.toString()
            holder.txtTotalRecovered.text = it.newRecovered.toString()
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName = view.country_name!!
        val txtNewCase = view.txtNewCase!!
        val txtNewDeath = view.txtNewDeath!!
        val txtTotalCase = view.txtTotalCase!!
        val txtTotalRecovered = view.txtTotalRecovered!!
    }

}