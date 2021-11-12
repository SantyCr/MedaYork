package com.example.primerapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject
import java.nio.file.Files.size
import com.google.gson.JsonParser
import java.nio.file.Files.list


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayView : ArrayAdapter<*>





        val sitios = leerDatos()

        val lv_datos = findViewById<ListView>(R.id.lv_datos)

        arrayView = SitiosAdapter(this,sitios)

        lv_datos.adapter = arrayView


        lv_datos.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(this, detalle::class.java)
            startActivity(intent)
        }
    }



    fun  leerDatos():List<Sitio>{

        val gson = Gson()
        val parser = JsonParser()

        val sitiosJson = "{sitios:[{id:1,nombre:'Malpelo', " +
                "informacion_general:'La isla fue declarada Santuario de Fauna y Flora en 1995 y en 2005 fue ampliada su área a 9.584 kilómetros cuadrados'" +
                ",ubicacion:'Costa Pacifica'," +
                "temperatura=34,sitios_recomendados='otros'," +
                "image:'https://turismoi.pe/uploads/region/image/1/home_460_Amazonas.jpg'," +
                "pais:'colombia' }," +
                "{id:2,nombre:'Guatavita'," +
                "informacion_general:'Guatavita es un pueblo con estilo colonial y " +
                "uno de los lugares turísticos de Colombia de influencia española, construido" +
                " para reemplazar el antiguo pueblo que se hundió para dar paso al embalse de Tominé'" +
                ",ubicacion:'Cundinamarca'," +
                "temperatura=15,sitios_recomendados='otros'," +
                "image:'https://hansatours.com/images/Guatavita-tour1-864wX360h-v3.webp'," +
                "pais:'colombia' }]}"

        val jsonElement = parser.parse(sitiosJson)
        val jsonObject =  jsonElement.getAsJsonObject();
        val sitios = jsonObject.getAsJsonArray("sitios")
        val listaElementos = mutableListOf<Sitio>()

        for (x in 0 until sitios.size()) {
            val elemento = gson.fromJson(sitios.get(x),Sitio::class.java)
           listaElementos.add(x,elemento)
        }

        return listaElementos

       // var testModel = gson.fromJson(jsonObject, Sitio::class.java)
/*
        StringRequest getResquest = new StringRequest(Request.Method.GET,url, new Response.Listener < String >(){

*/



    }
}
