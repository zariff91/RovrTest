package com.zariff.rovrtest.ui


import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zariff.rovrtest.R
import com.zariff.rovrtest.local.GlobalDatabase
import com.zariff.rovrtest.local.GlobalLocalData
import com.zariff.rovrtest.service.ApiClient
import com.zariff.rovrtest.service.DataApi
import com.zariff.rovrtest.model.CountryData
import com.zariff.rovrtest.model.DataModel
import com.zariff.rovrtest.model.GlobalData
import kotlinx.android.synthetic.main.act_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private var dataApi: DataApi = ApiClient.createApi().create(DataApi::class.java)
    lateinit var countryDataa: List<CountryData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchData()

        btnViewData.setOnClickListener {
            val intent = Intent(activity, CountryActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.act_home, container, false)
    }

    private fun fetchData() {

        var dialog = context?.let { ProgresssDialog.progressDialog(it) }
        dialog?.show()

        dataApi.getSummary().enqueue(object : Callback<DataModel> {
            override fun onFailure(call: Call<DataModel>, t: Throwable) {

                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()
                dialog?.dismiss()

                val dataa = GlobalDatabase(activity!!).globalDao().readAllData()

                if(dataa == null)
                {
                    txtTotalCase.text = "No data available"
                    txtTotalDeath.text = "No data available"
                    txtTotalRecovered.text = "No data available"

                }

                else
                {
                    txtTotalCase.text = dataa.totalCases.toString()
                    txtTotalDeath.text = dataa.totalDeath.toString()
                    txtTotalRecovered.text = dataa.totalRecovered.toString()
                }

            }

            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                    val data: DataModel = response.body()!!
                    val global: GlobalData = data.global!!
//                    countryDataa = data.countries!!

                    val globalLocal =
                        global.totalConfirmed?.let { global.totalDeaths?.let { it1 ->
                            global.totalRecovered?.let { it2 ->
                                GlobalLocalData(it,
                                    it1, it2
                                )
                            }
                        } }

                    globalLocal?.let { GlobalDatabase(activity!!).globalDao().addGlobalData(it) }


                    global.let {
                        txtTotalCase.text = it.totalConfirmed.let { b ->
                            when (b) {
                                null -> "0"
                                else -> b.toString()
                            }
                        }
                        txtTotalRecovered.text = it.totalRecovered.let { b ->
                            when (b) {
                                null -> "0"
                                else -> b.toString()
                            }
                        }
                        txtTotalDeath.text = it.totalDeaths.let { b ->
                            when (b) {
                                null -> "0"
                                else -> b.toString()
                            }
                        }
                    }

                    dialog?.dismiss()

                } else {
                    Toast.makeText(context, "Error = " + response.errorBody(), Toast.LENGTH_LONG).show()
                    dialog?.dismiss()

                }
            }
        })
    }


}
