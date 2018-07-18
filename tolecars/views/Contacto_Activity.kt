package com.example.ruben.tolecars.views

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ruben.tolecars.R
import kotlinx.android.synthetic.main.activity_contacto.*
import android.graphics.Color.parseColor
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.View
import android.widget.TextView
import com.example.ruben.tolecars.R.drawable.twiter
import kotlinx.android.synthetic.*


class Contacto_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto)

        val twiter = findViewById<View>(R.id.tvTwitter) as TextView
        twiter.movementMethod = LinkMovementMethod.getInstance()

        val instagram = findViewById<View>(R.id.tvInstagram) as TextView
        instagram.movementMethod = LinkMovementMethod.getInstance()

        val facebok = findViewById<View>(R.id.tvFacebbok) as TextView
        facebok.movementMethod = LinkMovementMethod.getInstance()
    }



}
