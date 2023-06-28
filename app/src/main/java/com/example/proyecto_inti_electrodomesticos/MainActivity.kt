package com.example.proyecto_inti_electrodomesticos

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.proyecto_inti_electrodomesticos.configLobato.BaseDatosSQLite
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    val dbHelper = BaseDatosSQLite(this)

    lateinit var btnIngresar : Button
    lateinit var btnLink: TextView

    lateinit var txtEmail: EditText
    lateinit var txtContraseña: EditText

    lateinit var imageViewContraseña: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnLink = findViewById(R.id.textViewLink)
        btnIngresar = findViewById(R.id.btnIniciarSesion)

        txtEmail = findViewById(R.id.txtEmail)
        txtContraseña = findViewById(R.id.txtContraseña)

        imageViewContraseña = findViewById(R.id.imageViewContraseña)

        imageViewContraseña.setOnClickListener {
            val visible = txtContraseña.transformationMethod != PasswordTransformationMethod.getInstance()

            if(visible){
                txtContraseña.transformationMethod = PasswordTransformationMethod.getInstance()
                imageViewContraseña.setImageResource(R.drawable.ic_view)
            }else{
                txtContraseña.transformationMethod = HideReturnsTransformationMethod.getInstance()
                imageViewContraseña.setImageResource(R.drawable.ic_view)
            }
            txtContraseña.setSelection(txtContraseña.text.length)
        }


        btnLink.setOnClickListener {
            val intent = Intent(this, RegistroUsuario::class.java)
            startActivity(intent)
        }

        btnIngresar.setOnClickListener {



            val correo = txtEmail.text.toString()
            val clave = txtContraseña.text.toString()

            val validar = validarUsuario(correo, clave)
            if (validar) {
                val usuarioId = obtenerIdUsuario(correo) // Obtener el ID del usuario actual
                val intent = Intent(this, AppBarActivity::class.java).apply {
                    putExtra("usuarioId", usuarioId) // Pasar el ID del usuario a la siguiente actividad
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
            }
        }

    }
    fun validarUsuario(user_correo: String, user_contraseña: String) : Boolean{
        val db = dbHelper.readableDatabase

        val colums = arrayOf("user_correo","user_contraseña")
        val selection = "user_correo=? AND user_contraseña=?"
        val selectionArgs = arrayOf(user_correo, user_contraseña)

        val cursor = db.query("tb_usuarios",colums,selection,selectionArgs,null,null,null)

        val resultado = cursor.moveToFirst()

        cursor.close()
        db.close()

        return resultado
    }
    // Función para obtener el ID del usuario actual
    @SuppressLint("Range")
    private fun obtenerIdUsuario(correo: String): Int {
        val db = dbHelper.readableDatabase

        val colums = arrayOf(BaseColumns._ID)
        val selection = "user_correo=?"
        val selectionArgs = arrayOf(correo)

        val cursor = db.query("tb_usuarios", colums, selection, selectionArgs, null, null, null)

        val usuarioId = if (cursor.moveToFirst()) {
            cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
        } else {
            -1 // Valor predeterminado si no se encuentra el usuario
        }

        cursor.close()
        db.close()

        return usuarioId
    }
}