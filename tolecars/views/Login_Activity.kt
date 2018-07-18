package com.example.ruben.tolecars.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ruben.tolecars.R
import com.example.ruben.tolecars.api.ApiConcesionario
import com.example.ruben.tolecars.model.Respuesta
import com.example.ruben.tolecars.model.Usuarios
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar_menu.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class Login_Activity : AppCompatActivity() {

    private lateinit var unUsuario: Usuarios
    private lateinit var usuarioLogin: Usuarios
    private lateinit var todosUsuarios:ArrayList<Usuarios>
    private lateinit var respuesta: Respuesta


    val TAG :String = "RUBEEEEEENNNNNNNNN"

    private val apiconcesionario by lazy {
        ApiConcesionario.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        setSupportActionBar(toolbar)

        loadUsers()

        btnLogIn.setOnClickListener({v -> logIn()})
        btnRegistrar.setOnClickListener({v -> signIn()})
        loadUsers()
        usuarioLogin = Usuarios(0,"","")
    }

    private fun loadUsers() {
        apiconcesionario.getUsuarios()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { respuesta ->
                            todosUsuarios=respuesta.usuarios
                            Log.d(TAG, "List of Users-> "+respuesta.usuarios.toString())
                        },
                        { error ->
                            Toast.makeText(this,"Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show()
                            Log.e(TAG, "Error loading users-> " +error.message)
                        }
                )
    }

    private fun logIn(){
        var correcto=false

        for (i in 0..todosUsuarios.size - 1) {

            unUsuario = todosUsuarios.get(i)

            usuarioLogin.nick = etNick.text.toString()
            usuarioLogin.password = etPassword.text.toString()

            if (unUsuario.nick.equals(usuarioLogin.nick) && unUsuario.password.equals(usuarioLogin.password)) {

                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Usuario correcto. Accediendo..", Toast.LENGTH_SHORT).show()
                correcto=true
                Log.d(TAG, "Log in...")
            }
        }
        if (correcto==false){
            Toast.makeText(this, "La contraseña o nick son incorrectos", Toast.LENGTH_SHORT).show()
        }

    }

    private fun signIn(){
        usuarioLogin.nick = etNick.text.toString()
        usuarioLogin.password = etPassword.text.toString()

        addUser(usuarioLogin)
    }

    private fun addUser(user:Usuarios){
        apiconcesionario.addUsuario(user.nick,user.password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { respuesta ->
                            var user =respuesta.usuario
                            val intent = Intent(this, MenuActivity::class.java) //pasa de activity
                            startActivity(intent)
                            Toast.makeText(this, "Guardando nuevo usuario en la BD..", Toast.LENGTH_SHORT).show()
                            Log.d(TAG,"Guardando nuevo usuario en la BD")
                        },
                        { error ->
                            Toast.makeText(this, "Error añadiendo un nuevo usuario a la BD..", Toast.LENGTH_SHORT).show()
                            Log.e(TAG,"Error añadiendo un nuevo usuario a la BD -> "+error.message)
                        }
                )
    }
}
