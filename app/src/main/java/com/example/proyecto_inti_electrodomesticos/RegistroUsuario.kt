package com.example.proyecto_inti_electrodomesticos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegistroUsuario : AppCompatActivity() {

    lateinit var btnCancelarReg: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        btnCancelarReg = findViewById(R.id.btnCancelarReg)

        btnCancelarReg.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }



}
