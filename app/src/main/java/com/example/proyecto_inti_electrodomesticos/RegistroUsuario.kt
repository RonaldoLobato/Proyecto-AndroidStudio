package com.example.proyecto_inti_electrodomesticos

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton


lateinit var btnCancelar : Button
     lateinit var btnRegistrar: Button
    lateinit var btnCancelarReg: Button




class RegistroUsuario : AppCompatActivity() {

    lateinit var btnCancelar: Button
    lateinit var btnCancelarReg: Button
    lateinit var txtNombre:EditText
    lateinit var txtApellido:EditText
    lateinit var txtEmail:EditText
    lateinit var txtClave:EditText
    lateinit var txtConfirmarClave:EditText
    lateinit var btnRegistrar: Button
    lateinit var fltbtncamara: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)
        fltbtncamara=findViewById(R.id.floatbtncamara)
        btnRegistrar=findViewById(R.id.btnRegistrarReg)
        btnCancelar = findViewById(R.id.btnCancelarReg)

        fltbtncamara.setOnClickListener{
            capturaPerfil(it)
        }
        btnCancelar.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }

            btnRegistrar.setOnClickListener { view ->
            txtNombre=findViewById(R.id.txtNombre)
            txtApellido=findViewById(R.id.txtApellido)
            txtEmail=findViewById(R.id.txtEmail)
            txtClave=findViewById(R.id.txtContraseña)
            txtConfirmarClave=findViewById(R.id.txtConfirmarContraseña)

            var nombre = txtNombre.text.toString()
            var apellido = txtApellido.text.toString()
            var email = txtEmail.text.toString()
            var clave = txtClave.text.toString()
            var clave2 = txtConfirmarClave.text.toString()

            if (validacionesCampos(nombre,apellido,email,clave,clave2)){
                val builder: AlertDialog.Builder? = view?.let{
                    AlertDialog.Builder(this)
                }
                builder?.setMessage("Campos Correctos")
                    ?.setTitle("Desea Agregar el Registro")
                builder?.setPositiveButton("SI", DialogInterface.OnClickListener {
                        dialog,id-> txtNombre.setText("")
                    txtApellido.setText("")
                    txtEmail.setText("")
                    txtClave.setText("")
                    txtConfirmarClave.setText("")
                    Toast.makeText(this,"Registrado Exitoso",Toast.LENGTH_LONG).show()
                })
                builder?.setNegativeButton("NO",
                    DialogInterface.OnClickListener{dialog, id->
                        dialog.dismiss()
                    })
                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }

        }


        btnCancelarReg = findViewById(R.id.btnCancelarReg)

        btnCancelarReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun capturaPerfil(v: View?) {

    }

    private fun   validacionesCampos(nombre:String,apellido:String,Email:String,contraseña:String,contraseña2:String):Boolean{
            if(nombre.isEmpty()){
                Toast.makeText(this,"Ingresa tu nombre",Toast.LENGTH_LONG).show()
                return false
            }
            if(apellido.isEmpty()){
                Toast.makeText(this,"Ingresa tu apellido",Toast.LENGTH_LONG).show()
                return false
       }

            if(Email.isEmpty()){
                Toast.makeText(this,"Ingresa tu correo",Toast.LENGTH_LONG).show()
                return false
        }

            if(contraseña.isEmpty()){
                Toast.makeText(this,"Ingresa tu contraseña",Toast.LENGTH_LONG).show()
                return false
       }
            if(contraseña2.isEmpty()){
                Toast.makeText(this,"Confirma tu contraseña",Toast.LENGTH_LONG).show()
                return false
       }
       if(contraseña2!=contraseña){
           Toast.makeText(this,"su contraseña no coincide",Toast.LENGTH_LONG).show()
           return false
       }

    return true
  }

}


