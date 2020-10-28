package com.zariff.rovrtest.ui


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.zariff.rovrtest.R
import com.zariff.rovrtest.model.CountryData
import com.zariff.rovrtest.model.DataModel
import com.zariff.rovrtest.service.ApiClient
import com.zariff.rovrtest.service.DataApi
import kotlinx.android.synthetic.main.fragment_country.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class CountryActivity : AppCompatActivity() {

    private var dataApi: DataApi = ApiClient.createApi().create(DataApi::class.java)
    private var countryList: ArrayList<CountryData> = ArrayList()
    private var listAdapter: CountryListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_country)

        edStartDate.transformIntoDatePicker("yyyy-MM-dd", Date())
        edEndDate.transformIntoDatePicker("yyyy-MM-dd", Date())


        getCountryList()
        fetchCountryList()

        btnSearch.setOnClickListener {
            fetchByDate()
        }
    }

    private fun fetchCountryList() {

        val dialog = ProgresssDialog.progressDialog(this)
        dialog.show()

        val call: Call<DataModel> = dataApi.getSummary()
        call.enqueue(object : Callback<DataModel> {
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Toast.makeText(this@CountryActivity, "No Internet Connection", Toast.LENGTH_LONG)
                    .show()
                dialog.dismiss()
            }

            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    countryList = (data!!.countries as ArrayList<CountryData>?)!!
                    listAdapter = CountryListAdapter(countryList)
                    list_country.apply {
                        layoutManager = LinearLayoutManager(this@CountryActivity)
                        adapter = listAdapter
                        list_country.adapter = adapter
                    }

                    dialog.dismiss()
                } else {
                    Toast.makeText(this@CountryActivity, "Error" + response.errorBody(), Toast.LENGTH_LONG)
                        .show()                  }
            }
        })
    }

    private fun getCountryList() {
        val call = dataApi.getCountries()
        call.enqueue(object : Callback<Array<CountryData>> {
            override fun onFailure(call: Call<Array<CountryData>>, t: Throwable) {

                Toast.makeText(this@CountryActivity, "No Internet Connection", Toast.LENGTH_LONG)
                    .show()
                noDataText.visibility = View.VISIBLE

            }

            override fun onResponse(
                call: Call<Array<CountryData>>,
                response: Response<Array<CountryData>>
            ) {
                if (response.isSuccessful) {
                    val obj = response.body()
                    val countryName: ArrayList<String> = ArrayList()
                    obj!!.forEach {
                        countryName.add(it.country!!)
                    }
                    countrySpinner.adapter = ArrayAdapter<String>(
                        this@CountryActivity,
                        android.R.layout.simple_list_item_1,
                        countryName
                    )
                } else {
                    Toast.makeText(this@CountryActivity, "Error" + response.errorBody(), Toast.LENGTH_LONG)
                        .show()                }
            }

        })
    }

    private fun fetchByDate() {

        val dialog = ProgresssDialog.progressDialog(this)
        dialog.show()

        val call = dataApi.getCountryByDate(
            countrySpinner.selectedItem.toString(),
            edStartDate.text.toString(),
            edEndDate.text.toString()
        )

        call.enqueue(object : Callback<List<CountryData>> {
            override fun onFailure(call: Call<List<CountryData>>, t: Throwable) {
                Toast.makeText(this@CountryActivity, "No Internet Connection", Toast.LENGTH_LONG)
                    .show()
                dialog.dismiss()
            }

            override fun onResponse(
                call: Call<List<CountryData>>,
                response: Response<List<CountryData>>
            ) {

                if (response.isSuccessful) {

                    val objCountryList = response.body()

                    if (objCountryList.isNullOrEmpty()) {

                        noDataText.visibility = View.VISIBLE
                        list_country.visibility = View.GONE

                    } else {

                        countryList.clear()
                        objCountryList.let { countryList.addAll(it) }

                        listAdapter!!.notifyDataSetChanged()

                    }

                    dialog.dismiss()


                } else {
                    noDataText.visibility = View.VISIBLE
                    dialog.dismiss()

                }
            }

        })

    }

    fun TextInputEditText.transformIntoDatePicker(format: String, maxDate: Date? = null) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.UK)
                setText(sdf.format(myCalendar.time))
            }

        setOnClickListener {
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }
    }
}
