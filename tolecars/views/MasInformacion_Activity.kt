package com.example.ruben.tolecars.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ruben.tolecars.R
import com.example.ruben.tolecars.model.Coches
import kotlinx.android.synthetic.main.activity_mas_informacion.*

class MasInformacion_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mas_informacion)

        val coche = intent.getSerializableExtra("coche") as Coches

        wv.loadUrl(coche.urlFabricante)


    }
}
