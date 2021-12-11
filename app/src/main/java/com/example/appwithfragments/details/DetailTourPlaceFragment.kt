package com.example.appwithfragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appwithfragments.R
import com.example.appwithfragments.databinding.FragmentDetailTourPlaceBinding
import com.squareup.picasso.Picasso

class DetailTourPlaceFragment : Fragment() {

    private lateinit var detailTourPlaceBinding: FragmentDetailTourPlaceBinding
    private val args: DetailTourPlaceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailTourPlaceBinding= FragmentDetailTourPlaceBinding.inflate(inflater, container, false)
        return detailTourPlaceBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tourPlace = args.detailPlace

        with(detailTourPlaceBinding){
            namePlace.text= tourPlace.name
            Picasso.get().load(tourPlace.img).into(imagePlace)
            description.text= tourPlace.description
            location.text = tourPlace.location
            ratingPlace.rating=tourPlace.rating.toString().toFloat()
            temperature.text = tourPlace.temperature
            recommendPlace.text = tourPlace.recommendedPlaces
        }

        detailTourPlaceBinding.btnMaps.setOnClickListener {
            findNavController().navigate(R.id.mapsFragment)
        }

    }
}