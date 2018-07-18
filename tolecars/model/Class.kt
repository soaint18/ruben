package com.example.ruben.tolecars.model

import java.io.Serializable

/**
 * Created by Ruben on 23/05/2018.
 */
data class Usuarios(var id_user:Int=-1, var nick:String="", var imguser:String="", var password:String="")

data class Coches (var id:Int=-1, var marca:String="", var modelo:String="", var combustible:String="", var kilometros:String="",
                   var cambio:String="", var caballos:String="", var color:String="", var anioMatriculacion:String="",
                   var garantia:String="", var descripcion:String="", var precio:String="", var imgcar:String="", var urlFabricante:String=" "):Serializable

data class Respuesta(var usuario:Usuarios, var usuarios:ArrayList<Usuarios>,
                     var coche:Coches, var coches:ArrayList<Coches>)
