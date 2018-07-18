package com.example.ruben.tolecars.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ruben.tolecars.R
import com.example.ruben.tolecars.R.id.toolbar
import com.example.ruben.tolecars.api.ApiConcesionario
import com.example.ruben.tolecars.model.Coches
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_coche.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.row_coches.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Detail_Coche_Activity : AppCompatActivity() {

    companion object {
        val TAG = "**RUBEEEEEENNNNNNNNN**"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_coche)


        val coche = intent.getSerializableExtra("coche") as Coches

        btnURL.setOnClickListener({v -> onItemClick(coche)})

        Picasso.with(this).load(coche.imgcar).into(ivImgCar)

        tvPrecio.text = coche.precio
        tvMarca.text = coche.marca
        tvModelo.text = coche.modelo
        tvKilometros.text = coche.kilometros
        tvCombustible.text = coche.combustible
        tvCaballos.text = coche.caballos
        tvAnioMatriculacion.text = coche.anioMatriculacion
        tvCambio.text = coche.cambio
        tvColor.text = coche.color
        tvGarantia.text = coche.garantia
        tvDescripcion.text = coche.descripcion

    }


    private fun onItemClick(dataItem: Coches) {
        val intent = Intent(this, MasInformacion_Activity::class.java)
        intent.putExtra("coche", dataItem)
        this.startActivity(intent)
    }
}
