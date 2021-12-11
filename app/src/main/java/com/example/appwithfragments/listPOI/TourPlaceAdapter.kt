package com.example.appwithfragments.listPOI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appwithfragments.R
import com.example.appwithfragments.models.TourPlace
import com.squareup.picasso.Picasso

class TourPlaceAdapter(
    private val tourPlace : ArrayList<TourPlace>,
    private val onClick: (TourPlace) -> Unit,


) : RecyclerView.Adapter<TourPlaceAdapter.PlaceHolder>() {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): PlaceHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_tour_place_design,parent,false)
        return PlaceHolder(view)
    }

    override fun getItemCount(): Int = tourPlace.size

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.render(tourPlace[position])
    }

    inner class PlaceHolder( view : View) : RecyclerView.ViewHolder(view){
        private val name : TextView = view.findViewById(R.id.namePOI)
        private val description: TextView = view.findViewById(R.id.descriptionPOI)
        private val rating: RatingBar = view.findViewById(R.id.ratingPOI)
        private val image: ImageView = view.findViewById(R.id.imagePOI)
        private var dataPlace: TourPlace? = null

        init{
            view.setOnClickListener{
                dataPlace?.let{
                    onClick(it)
            }
            }
        }

        fun render(tourPlace : TourPlace){
            dataPlace=tourPlace
            name.text=tourPlace.name
            description.text=tourPlace.description_short
            rating.rating= tourPlace.rating.toFloat()
            Picasso.get().load(tourPlace.img).resize(300, 300).into(image)
        }
    }

}