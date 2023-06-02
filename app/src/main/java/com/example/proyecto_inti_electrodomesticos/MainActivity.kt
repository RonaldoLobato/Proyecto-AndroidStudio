package com.example.proyecto_inti_electrodomesticos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var btnIngresar : Button
    lateinit var btnLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnLink = findViewById(R.id.textViewLink)
        btnIngresar = findViewById(R.id.btnIniciarSesion)

        btnLink.setOnClickListener {
            val intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }

        btnIngresar.setOnClickListener {
            val intent = Intent(this, AppBarActivity::class.java)
            startActivity(intent)
        }

    }
}