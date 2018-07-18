package com.example.ruben.tolecars.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ruben.tolecars.R
import com.example.ruben.tolecars.api.ApiConcesionario
import com.example.ruben.tolecars.model.Coches
import com.example.ruben.tolecars.views.Coches_Activity
import com.example.ruben.tolecars.views.Detail_Coche_Activity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_coches.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Ruben on 24/05/2018.
 */


    class CustomAdapterCoches(val context: Context,
                                  val layout: Int,
                                  val dataList: List<Coches>) : RecyclerView.Adapter<CustomAdapterCoches.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val viewlayout = layoutInflater.inflate(layout, parent, false)
            return ViewHolder(viewlayout, context)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = dataList[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        inner class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {

            fun bind(dataItem: Coches) {

                Picasso.with(context).load(dataItem.imgcar).into(itemView.ivCoche)
                itemView.tvMarca.text = dataItem.marca
                itemView.tvModelo.text = dataItem.modelo
                itemView.tvKilometros.text = dataItem.kilometros
                itemView.tvCombustible.text = dataItem.combustible
                itemView.tvPrecio.text = dataItem.precio
                itemView.setOnClickListener({
                    onItemClick(dataItem)
                })
            }

            private fun onItemClick(dataItem: Coches) {
                val intent = Intent(context as Activity, Detail_Coche_Activity::class.java)
                intent.putExtra("coche", dataItem)
                context.startActivity(intent)
            }

        }

    }





