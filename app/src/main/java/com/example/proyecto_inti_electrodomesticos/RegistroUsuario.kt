package com.example.proyecto_inti_electrodomesticos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class RegistroUsuario : AppCompatActivity() {

     lateinit var btnCancelar : Button
     lateinit var btnRegistrar: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        btnCancelar =findViewById(R.id.btnCancelar2)


        btnCancelar.setOnClickListener {
            val Intent = Intent(this,MainActivity::class.java)
            startActivity(Intent)
        }


    }
}