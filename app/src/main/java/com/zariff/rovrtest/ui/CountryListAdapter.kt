package com.zariff.rovrtest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zariff.rovrtest.R
import com.zariff.rovrtest.model.CountryData
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(private val countryData: ArrayList<CountryData>) :
        RecyclerView.Adapter<CountryListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_country,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return countryData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val country = countryData[position]

        country.let {
            holder.countryName.text = it.country
            holder.txtNewCase.text = it.newConfirmed.let { b ->
                when (b) {
                    null -> it.searchActive.toString()
                    else -> b.toString()
                }
            }

            if (it.newConfirmed == null) {
                holder.lblTotalCases.text = "Active Cases"
            }
            if (it.newDeaths == null) {
                holder.lblTotalDeath.text = "Total Death"
            }

            holder.txtNewDeath.text = it.newDeaths.let { b ->
                when (b) {
                    null -> it.searchDeaths.toString()
                    else -> b.toString()
                }
            }
            holder.txtTotalCase.text = it.totalConfirmed.let { b ->
                when (b) {
                    null -> it.searchConfirm.toString()
                    else -> b.toString()
                }
            }
            holder.txtTotalRecovered.text = it.newRecovered.let { b ->
                when (b) {
                    null -> it.searchRecovered.toString()
                    else -> b.toString()
                }
            }
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName = view.country_name!!
        val txtNewCase = view.txtNewCase!!
        val txtNewDeath = view.txtNewDeath!!
        val txtTotalCase = view.txtTotalCase!!
        val txtTotalRecovered = view.txtTotalRecovered!!
        val lblTotalCases = view.lblNewCase!!
        val lblTotalDeath = view.lblNewDeath!!


    }

}