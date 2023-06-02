package com.example.proyecto_inti_electrodomesticos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
<<<<<<< HEAD


class RegistroUsuario : AppCompatActivity() {

     lateinit var btnCancelar : Button
     lateinit var btnRegistrar: Button



=======

class RegistroUsuario : AppCompatActivity() {

    lateinit var btnCancelarReg: Button
>>>>>>> 8374df0ddec46c7e4d58e81a5fb2e4f351122b14

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

<<<<<<< HEAD
        btnCancelar =findViewById(R.id.btnCancelar2)


        btnCancelar.setOnClickListener {
            val Intent = Intent(this,MainActivity::class.java)
            startActivity(Intent)
        }


=======
        btnCancelarReg = findViewById(R.id.btnCancelarReg)

        btnCancelarReg.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

>>>>>>> 8374df0ddec46c7e4d58e81a5fb2e4f351122b14
    }



}
