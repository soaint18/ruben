package com.example.ruben.tolecars.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.example.ruben.tolecars.R
import com.example.ruben.tolecars.adapter.CustomAdapterCoches
import com.example.ruben.tolecars.api.ApiConcesionario
import com.example.ruben.tolecars.model.Coches
import kotlinx.android.synthetic.main.activity_coches.*
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class Coches_Activity : AppCompatActivity() {

    private lateinit var cochesAll: ArrayList<Coches>
    private lateinit var cochesMarca: ArrayList<Coches>
    private lateinit var lista: String
    private lateinit var marcasCoches: String
    private lateinit var coche:Coches
    private lateinit var adapter: CustomAdapterCoches

    private lateinit var marcas:Spinner

    val TAG :String = "RUBEEEEEEENNNNNNNN"
    private val apiconcesionario by lazy {
        ApiConcesionario.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coches)

        loadCoches()

    }

    private fun loadCoches() {
        apiconcesionario.getCoches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { respuesta ->
                            Log.d(TAG,"Listado de coches -> "+respuesta.coches.toString())
                            cochesAll=respuesta.coches
                            showCoches()
                        },
                        { error ->
                            Log.e(TAG, "Error cargando categorias -> "+error.message)
                        }
                )
    }

    private fun showCoches() {
        adapter = CustomAdapterCoches(this,R.layout.row_coches, cochesAll)
        rvCoches.layoutManager = LinearLayoutManager(this)
        rvCoches.hasFixedSize()
        rvCoches.adapter= adapter

    }

//    private fun cargarSpinner(){
//        lista = loadMarcas().toString()
//
//    }
//
//    private fun loadMarcas() {
//        apiconcesionario.getMarcas()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { respuesta ->
//                            Log.d(TAG,"Listado de marcas -> "+respuesta.coche.marca)
//                            marcasCoches=respuesta.coche.marca
//                            showCoches()
//                        },
//                        { error ->
//                            Log.e(TAG, "Error cargando categorias -> "+error.message)
//                        }
//                )
//    }
//
//    private fun loadCochesPorMarca(marca:String) {
//        apiconcesionario.getCochesPorMarca(marca)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { respuesta ->
//                            Log.d(TAG,"Listado de coches por marca -> "+respuesta.coches.toString())
//                            cochesMarca=respuesta.coches
//                            showCoches()
//                        },
//                        { error ->
//                            Log.e(TAG, "Error cargando categorias -> "+error.message)
//                        }
//                )
//    }

}
