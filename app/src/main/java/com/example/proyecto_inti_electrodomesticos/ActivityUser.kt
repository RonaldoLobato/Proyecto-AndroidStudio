package com.example.proyecto_inti_electrodomesticos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.EditText
import com.example.proyecto_inti_electrodomesticos.configLobato.BaseDatosSQLite
import com.example.proyecto_inti_electrodomesticos.configLobato.TABLE_USUARIO_NAME

class ActivityUser : AppCompatActivity() {

    private lateinit var txtNombreUser: EditText
    private lateinit var txtApellidoUser: EditText
    private lateinit var txtCorreoUser: EditText
    private lateinit var txtContraseñaUser: EditText

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        txtNombreUser = findViewById(R.id.txtNombreUsuario)
        txtApellidoUser = findViewById(R.id.txtApellidoUsuario)
        txtCorreoUser = findViewById(R.id.txtCorreoUsuario)
        txtContraseñaUser = findViewById(R.id.txtContraseñaUsuario)

        // Obtener el ID del usuario pasado desde la actividad de inicio de sesión
        val usuarioId = intent.getIntExtra("usuarioId", -1)

        if (usuarioId != -1) {
            // Realizar la consulta y mostrar los datos del usuario en la interfaz
            val baseDatos = BaseDatosSQLite(this)
            val db = baseDatos.readableDatabase

            val consulta = "SELECT user_nombre, user_apellido, user_correo, user_contraseña FROM $TABLE_USUARIO_NAME WHERE ${BaseColumns._ID} = $usuarioId"
            val cursor = db.rawQuery(consulta, null)

            if (cursor.moveToFirst()) {
                val name = cursor.getString(cursor.getColumnIndex("user_nombre"))
                val lastName = cursor.getString(cursor.getColumnIndex("user_apellido"))
                val email = cursor.getString(cursor.getColumnIndex("user_correo"))
                val clave = cursor.getString(cursor.getColumnIndex("user_contraseña"))

                // Mostrar los datos en la interfaz
                txtNombreUser.setText(name)
                txtApellidoUser.setText(lastName)
                txtCorreoUser.setText(email)
                txtContraseñaUser.setText(clave)
            }

            cursor.close()
            db.close()
        }
    }
}
