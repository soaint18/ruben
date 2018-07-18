package com.example.ruben.tolecars.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ruben.tolecars.R
import kotlinx.android.synthetic.main.activity_quienessomos.*
import kotlinx.android.synthetic.main.content_menu.*

class Quienes_somos_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quienessomos)

        tvPresentacion.text="Somos un concesionario multimarca con mas de 10 años de experiencia en el sector. Contamos con mas de 5.000 clientes atendidos durante todos estos años."

        tvCoches.text="Durante estos años años hemos vendido mas de 3.000 vehiculos seminuevos y de segunda mano, situandonos como un referente a la hora de comprar un vehiculo de segundamano."

        tvInstalacion.text="Gracias al exito que conseguido durante todos estos años, nos ha permitido poder seguir mejorando nuestras instalaciones y servicio, a la misma vez que creciamos como empresa."


    }
}
