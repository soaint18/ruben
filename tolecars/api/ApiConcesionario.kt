package com.example.ruben.tolecars.api

import com.example.ruben.tolecars.model.Respuesta
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import rx.Observable

/**
 * Created by Ruben on 21/05/2018.
 */
interface ApiConcesionario {
    //--------------------------USERS-------------------------------
    @GET("usuarios")
    fun getUsuarios(): Observable<Respuesta>

    @GET("usuarios/{id_user}")
    fun getUsuario(@Path("id_user") id_user: Int): Observable<Respuesta>

    @FormUrlEncoded
    @POST("usuario")
    fun addUsuario(
            @Field("nick") username: String,
            @Field("password") password: String
    ): Observable<Respuesta>

    //--------------------------COCHES-------------------------------

    @GET("coches")
    fun getCoches(): Observable<Respuesta>

    @GET("marcas")
    fun getMarcas(): Observable<Respuesta>

    @GET("coches/{marca}")
    fun getCochesPorMarca(@Path("marca") marca: String): Observable<Respuesta>





    //--------------------------COMPANION OBJECT-------------------------------

    companion object {
        fun create(): ApiConcesionario {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://192.168.1.39:8282/ApiConcesionario/") //rubenPC
                   // .baseUrl("http://172.16.0.128:8282/ApiConcesionario/") //rubenPC
                    .build()
            return retrofit.create(ApiConcesionario::class.java)
        }
    }
}