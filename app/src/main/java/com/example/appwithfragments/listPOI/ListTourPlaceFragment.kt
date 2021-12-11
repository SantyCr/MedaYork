package com.example.appwithfragments.listPOI

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appwithfragments.databinding.FragmentListTourPlaceBinding
import com.example.appwithfragments.main.MainActivity
import com.example.appwithfragments.models.TourPlace
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset


class ListTourPlaceFragment : Fragment() {

    private lateinit var listTourPlaceBinding: FragmentListTourPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity?)?.showIcon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listTourPlaceBinding = FragmentListTourPlaceBinding.inflate(inflater, container, false)
        return listTourPlaceBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initRecycler()
        cargarDatos()
    }

    private fun initRecycler(listPoi : ArrayList<TourPlace>){
        val recycler: RecyclerView = listTourPlaceBinding.listTourPlace
        val listAdapter= TourPlaceAdapter(listPoi, onClick={openDetailPlace(it)})
        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            setHasFixedSize(false)
        }
    }
    private fun openDetailPlace(tourPlace: TourPlace) {
        findNavController().navigate(
            ListTourPlaceFragmentDirections
                .actionListTourPlaceFragmentToDetailTourPlaceFragment(tourPlace))
    }
    private fun getJSONFromAssets() : String?{
        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myListTourPlace = context?.assets?.open("list.json")
            val size = myListTourPlace?.available()
            val buffer = size?.let { ByteArray(it) }
            myListTourPlace?.read(buffer)
            myListTourPlace?.close()
            json= buffer?.let { String(it,charset) }
        }catch (ex: IOException){
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun createListTourPLace() : ArrayList<TourPlace> {
        val listTourPlace : ArrayList<TourPlace> = ArrayList()
        try {
            val placesArray = JSONArray(getJSONFromAssets()!!)
            for (i in 0 until placesArray.length()){
                val placeInfo = placesArray.getJSONObject(i)
                val name = placeInfo.getString("name")
                val description = placeInfo.getString("description")
                val description_short = ""//placeInfo.getString("descriptionShort")
                val rating = placeInfo.getDouble("rating")
                val img = placeInfo.getString("img")
                val temperature = placeInfo.getString("temperature")
                val location = placeInfo.getString("location")
                val recommendedPlaces = placeInfo.getString("recommendedPlaces")
                val placeDetails= TourPlace(id,img,name,description,rating,temperature,location,recommendedPlaces, description_short )
                listTourPlace.add(placeDetails)
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return listTourPlace
    }

    private fun cargarDatos() {

        val listTourPlace: ArrayList<TourPlace> = ArrayList()
        try {

            val queue = Volley.newRequestQueue(activity)

            //URL
            val url ="http://181.49.136.167:15778/MisionTic/webresources/misionTic/list_all"

           

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener { response ->
                    try {

                        var id : Int
                        var name : String
                        var img : String
                        var description : String
                        var description_short : String
                        var rating : Double
                        var temperature : String
                        var location : String
                        var recommendedPlaces : String

                        //val lstGerencia = mutableListOf<Sitio>()

                        val dataFormatPreference = JSONArray(response)
                        Log.d("error","antes de cargar")
                        if (dataFormatPreference.length() < 1) {
                            //progressDialog.dismiss()

                            //val builder = AlertDialog.Builder(this@Recaudos_gerencia)

                            /* builder.setIcon(R.drawable.alert).setTitle("No hay informacion para esta consulta")
                                 .setPositiveButton("Aceptar",
                                     DialogInterface.OnClickListener { dialog, which -> Actividad_anterior() })

                             val alertDialog = builder.create()
                             alertDialog.show()*/

                        }

                        for (i in 0 until dataFormatPreference.length()) {

                            id = dataFormatPreference.getJSONObject(i).getInt("id")
                            name = dataFormatPreference.getJSONObject(i).getString("name")
                            img = dataFormatPreference.getJSONObject(i).getString("image")
                            description = dataFormatPreference.getJSONObject(i).getString("description")
                            description_short = dataFormatPreference.getJSONObject(i).getString("descriptionShort")
                            rating = dataFormatPreference.getJSONObject(i).getDouble("rating")
                            temperature = dataFormatPreference.getJSONObject(i).getString("temperature")
                            location = dataFormatPreference.getJSONObject(i).getString("location")
                            recommendedPlaces = dataFormatPreference.getJSONObject(i).getString("recommendedPlaces")



                            var poi = TourPlace(id,img,name,description,rating,temperature,location,recommendedPlaces,description_short)
                            listTourPlace.add(poi)
                            Log.d("error", poi.img)

                        }
                        initRecycler(listTourPlace)

                    } catch (e: JSONException) {
                        Log.d("error", e.toString())
                        e.printStackTrace()
                    }


                }, { error ->
                    Log.d("error", error.toString())
                })

            queue.add(stringRequest)
        } catch (e: Exception) {
            Log.d("error", e.toString())
            e.printStackTrace()

        }
    //return listTourPlace
    }
}