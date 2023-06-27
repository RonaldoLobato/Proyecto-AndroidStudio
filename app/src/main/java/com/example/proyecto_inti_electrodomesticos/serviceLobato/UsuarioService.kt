package com.example.proyecto_inti_electrodomesticos.serviceLobato

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.proyecto_inti_electrodomesticos.configLobato.BaseDatosSQLite
import com.example.proyecto_inti_electrodomesticos.configLobato.TABLE_USUARIO_NAME
import com.example.proyecto_inti_electrodomesticos.coreLobato.Usuario

class UsuarioService(context: Context) {
    val dbHelper = BaseDatosSQLite(context)

    //Metodo Registrar
    fun insertarNuevoUsuario(usuario: Usuario): Usuario? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("user_nombre", usuario.user_nombre)
            put("user_apellido",usuario.user_apellido)
            put("user_correo",usuario.user_correo)
            put("user_contraseña",usuario.user_contraseña)
        }
        val newRowId = db?.insert(
            TABLE_USUARIO_NAME,
            null,
            values
        )
        cerrarDB()
        return if (newRowId != null) getUsuario(newRowId.toLong()) else null
    }

    fun getUsuario(user_id: Long): Usuario?{
        val db = dbHelper.readableDatabase
        val projection = arrayOf(BaseColumns._ID, "user_nombre", "user_apellido", "user_correo", "user_contraseña")

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("$user_id")
        val cursor = db.query(TABLE_USUARIO_NAME, projection, selection, selectionArgs, null, null, null
        )
        with(cursor){
            if(cursor.count == 1){
                while(moveToNext()) {
                    val user_nombre = getString(getColumnIndexOrThrow("user_nombre"))
                    val user_apellido = getString(getColumnIndexOrThrow("user_apellido"))
                    val user_correo = getString(getColumnIndexOrThrow("user_correo"))
                    val user_contraseña = getString(getColumnIndexOrThrow("user_contraseña"))
                    val usuarioId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                    val usuarioObj = Usuario(usuarioId, user_nombre, user_apellido, user_correo, user_contraseña)

                    cerrarDB()
                    return usuarioObj
                }
            } else {
                return null
            }
        }
        return null
    }

    //Metodo para cerrar la base de datos
    fun cerrarDB(){
        dbHelper.close()
    }

}