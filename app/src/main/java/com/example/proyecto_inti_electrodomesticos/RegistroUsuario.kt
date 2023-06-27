package com.example.proyecto_inti_electrodomesticos

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.proyecto_inti_electrodomesticos.coreLobato.Usuario
import com.example.proyecto_inti_electrodomesticos.serviceLobato.UsuarioService
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegistroUsuario : AppCompatActivity() {

    lateinit var btnCancelarReg: Button
    lateinit var txtNombre: EditText
    lateinit var txtApellido: EditText
    lateinit var txtEmail: EditText
    lateinit var txtClave: EditText
    lateinit var txtConfirmarClave: EditText
    lateinit var btnRegistrar: Button
    lateinit var fltbtncamara: FloatingActionButton

    lateinit var imageViewContraseña1: ImageView
    lateinit var imageViewContraseña2: ImageView


    var usuarioService = UsuarioService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        fltbtncamara = findViewById(R.id.floatbtncamara)
        btnRegistrar = findViewById(R.id.btnRegistrarReg)
        btnCancelarReg = findViewById(R.id.btnCancelarReg)

        txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtEmail = findViewById(R.id.txtEmailRegistro)
        txtClave = findViewById(R.id.txtContraseñaRegistro)
        txtConfirmarClave = findViewById(R.id.txtConfirmarContraseña)

        /*fltbtncamara.setOnClickListener {
            capturaPerfil(it)
        }*/

        imageViewContraseña1 = findViewById(R.id.imageViewContraseña1)
        imageViewContraseña2 = findViewById(R.id.imageViewContraseña2)

        imageViewContraseña1.setOnClickListener {
            val visible = txtClave.transformationMethod != PasswordTransformationMethod.getInstance()

            if(visible){
                txtClave.transformationMethod = PasswordTransformationMethod.getInstance()
                imageViewContraseña1.setImageResource(R.drawable.ic_view)
            }else{
                txtClave.transformationMethod = HideReturnsTransformationMethod.getInstance()
                imageViewContraseña1.setImageResource(R.drawable.ic_view)
            }
            txtClave.setSelection(txtClave.text.length)
        }

        imageViewContraseña2.setOnClickListener {
            val visible = txtConfirmarClave.transformationMethod != PasswordTransformationMethod.getInstance()

            if(visible){
                txtConfirmarClave.transformationMethod = PasswordTransformationMethod.getInstance()
                imageViewContraseña2.setImageResource(R.drawable.ic_view)
            }else{
                txtConfirmarClave.transformationMethod = HideReturnsTransformationMethod.getInstance()
                imageViewContraseña2.setImageResource(R.drawable.ic_view)
            }
            txtConfirmarClave.setSelection(txtConfirmarClave.text.length)
        }


        btnCancelarReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnRegistrar.setOnClickListener { view ->

            val nombre = txtNombre.text.toString()
            val apellido = txtApellido.text.toString()
            val email = txtEmail.text.toString()
            val clave = txtClave.text.toString()
            val clave2 = txtConfirmarClave.text.toString()

            val usuario = Usuario(nombre,apellido,email,clave)

            if (validacionesCampos(nombre, apellido, email, clave, clave2)) {
                val builder: AlertDialog.Builder? = view?.let {
                    AlertDialog.Builder(this)
                }
                builder?.setMessage("Campos Correctos")
                    ?.setTitle("Desea Agregar el Registro")
                builder?.setPositiveButton("SI", DialogInterface.OnClickListener { dialog, id ->
                    txtNombre.setText("")
                    txtApellido.setText("")
                    txtEmail.setText("")
                    txtClave.setText("")
                    txtConfirmarClave.setText("")
                    registrar(usuario)
                    Toast.makeText(this, "Registrado Exitoso", Toast.LENGTH_LONG).show()
                })
                builder?.setNegativeButton("NO ",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }

        }
    }

    private fun validacionesCampos(nombre: String, apellido: String, Email: String, contraseña: String, contraseña2: String): Boolean {
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingresa tu nombre", Toast.LENGTH_LONG).show()
            return false
        }
        if (apellido.isEmpty()) {
            Toast.makeText(this, "Ingresa tu apellido", Toast.LENGTH_LONG).show()
            return false
        }

        if (Email.isEmpty()) {
            Toast.makeText(this, "Ingresa tu correo", Toast.LENGTH_LONG).show()
            return false
        }

        if (contraseña.isEmpty()) {
            Toast.makeText(this, "Ingresa tu contraseña", Toast.LENGTH_LONG).show()
            return false
        }
        if (contraseña2.isEmpty()) {
            Toast.makeText(this, "Confirma tu contraseña", Toast.LENGTH_LONG).show()
            return false
        }
        if (contraseña2 != contraseña) {
            Toast.makeText(this, "su contraseña no coincide", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
    fun registrar(v: Usuario) {
        usuarioService.insertarNuevoUsuario(v)
    }

}


