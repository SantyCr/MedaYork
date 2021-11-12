package com.example.primerapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Inicial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)
    }

    fun comenzar(v:View){

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
