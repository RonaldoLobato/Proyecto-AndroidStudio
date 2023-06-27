package com.example.proyecto_inti_electrodomesticos.serviceLobato

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.proyecto_inti_electrodomesticos.configLobato.BaseDatosSQLite
import com.example.proyecto_inti_electrodomesticos.configLobato.TABLA_PRODUCTO
import com.example.proyecto_inti_electrodomesticos.configLobato.TABLE_USUARIO_NAME
import com.example.proyecto_inti_electrodomesticos.coreLobato.ProductoLista
import com.example.proyecto_inti_electrodomesticos.coreLobato.Usuario

class ProductoListaService(context: Context) {
    val dbHelper = BaseDatosSQLite(context)

    fun insertarNuevoProducto(productoLista: ProductoLista): ProductoLista? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("cgcNombre", productoLista.cgcNombre)
            put("cgcImagen",productoLista.cgcImagen)
            put("cgcDescripcion",productoLista.cgcDescripcion)
            put("cgcPrecio",productoLista.cgcPrecio)
        }
        val newRowId = db?.insert(
            TABLA_PRODUCTO,
            null,
            values
        )
        cerrarDB()
        return if (newRowId != null) getProducto(newRowId.toLong()) else null
    }




    fun getProducto(idProducto:Long): ProductoLista?{
        val db=dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            "cgcImagen",
            "cgcNombre",
            "cgcDescripcion",
            "cgcPrecio"
        )
        val selection= "${BaseColumns._ID}=?"
        val selectionArgs = arrayOf("${idProducto}")
        val cursor= db.query(TABLA_PRODUCTO,projection,selection,selectionArgs,null,null,null)
        with(cursor){
            if(cursor.count==1){
                while(moveToNext()){
                    val index = getInt(getColumnIndexOrThrow(BaseColumns._ID))
                    val nombre = getString(getColumnIndexOrThrow("cgcNombre"))
                    val imagen = getString(getColumnIndexOrThrow("cgcImagen"))
                    val descripcion = getString(getColumnIndexOrThrow("cgcDescripcion"))
                    val precio = getDouble(getColumnIndexOrThrow("cgcPrecio"))
                    val productoId = getLong(getColumnIndexOrThrow((BaseColumns._ID)))
                    val productoObj = ProductoLista(
                        productoId,
                        imagen,
                        nombre,
                        descripcion,
                        precio
                    )
                    cerrarDB()
                    return productoObj
                }
            }else{return null}
        }
        return null
    }

    fun getProductos():MutableMap<Int,ProductoLista>{
        val db =dbHelper.readableDatabase
        val projection = arrayOf(BaseColumns._ID,"cgcImagen","cgcNombre","cgcDescripcion","cgcPrecio")
        val cursor= db.query(TABLA_PRODUCTO,projection,null,null,null,null,null)

        val registros = mutableMapOf<Int,ProductoLista>()
        with(cursor){
            while(moveToNext()){
                val index = getInt(getColumnIndexOrThrow(BaseColumns._ID))
                val nombre = getString(getColumnIndexOrThrow("cgcNombre"))
                val imagen = getString(getColumnIndexOrThrow("cgcImagen"))
                val descripcion = getString(getColumnIndexOrThrow("cgcDescripcion"))
                val precio = getDouble(getColumnIndexOrThrow("cgcPrecio"))
                val productoId = getLong(getColumnIndexOrThrow((BaseColumns._ID)))
                val productoObj = ProductoLista(
                    productoId,
                    imagen,
                    nombre,
                    descripcion,
                    precio
                )
                registros.put(index,productoObj)
            }
        }
        cerrarDB()
        return registros
    }
    fun cerrarDB(){
        dbHelper.close()
    }
}