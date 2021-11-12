package com.example.primerapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_sitio.view.*
import android.net.Uri
import com.squareup.picasso.Picasso

class SitiosAdapter(private  val mcontext:Context, private val listaSitios: List<Sitio>) : ArrayAdapter<Sitio>(mcontext,0, listaSitios) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mcontext).inflate(R.layout.item_sitio,parent,false)

        val sitio = listaSitios[position]

        layout.txtNombre.text = sitio.nombre
        layout.txtDescripcion.text = sitio.informacion_general

        Picasso.get().load(sitio.image).resize(300,300).centerCrop().into(layout.img)
        //layout.img.setImageURI(Uri.parse(sitio.image))

        return layout
    }
}