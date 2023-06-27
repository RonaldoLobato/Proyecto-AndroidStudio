package com.example.proyecto_inti_electrodomesticos.configLobato

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

//Nombre de la tabla usuario
const val TABLE_USUARIO_NAME = "tb_usuarios"
private const val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_USUARIO_NAME " + "(" + "${BaseColumns._ID} " + "INTEGER PRIMARY KEY," + "user_nombre TEXT," + "user_apellido TEXT," + "user_correo TEXT," + "user_contraseña TEXT)"
private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_USUARIO_NAME"


//Nombre de la tabla productoS
const val TABLA_PRODUCTO = "ProductoGP"
private const val SQL_CREATE_ENTRIES_PRODUCTO = "CREATE TABLE $TABLA_PRODUCTO (" + "${BaseColumns._ID} INTEGER PRIMARY KEY," +"cgcImagen, TEXT,"+ "cgcNombre TEXT," + "cgcDescripcion TEXT," +  "cgcPrecio REAL)"
private const val SQL_DELETE_ENTRIES_PRODUCTO = "DROP TABLE IF EXISTS $TABLA_PRODUCTO"

//Tabla Usuario

class BaseDatosSQLite(content: Context) :
    SQLiteOpenHelper(content, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "SQLITE_Inti.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        //Registro de Usuario
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},user_nombre, user_apellido, user_correo, user_contraseña) VALUES" + "(1,'Ronaldo','Lobato','i20211487@cibertec.edu.pe','cibertec')")
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},user_nombre, user_apellido, user_correo, user_contraseña) VALUES" + "(2,'Jhon','Del Rio','i202114178@cibertec.edu.pe','cibertec')")
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},user_nombre, user_apellido, user_correo, user_contraseña) VALUES" + "(3,'Yovanita','Dominguez','i20211965@cibertec.edu.pe','cibertec')")
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},user_nombre, user_apellido, user_correo, user_contraseña) VALUES" + "(4,'Luz','Alcala','i20211369@cibertec.edu.pe','cibertec')")
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},user_nombre, user_apellido, user_correo, user_contraseña) VALUES" + "(5,'Patrick','Ruiz','i20211287@cibertec.edu.pe','cibertec')")

        //Registro de Producto
        db.execSQL(SQL_CREATE_ENTRIES_PRODUCTO)
        db.execSQL("INSERT INTO $TABLA_PRODUCTO(cgcImagen,cgcNombre,cgcDescripcion,cgcPrecio) VALUES('p001','Licuadora','Oster marca reconocida a nivel mundial',78.80)")
        db.execSQL("INSERT INTO $TABLA_PRODUCTO(cgcImagen,cgcNombre,cgcDescripcion,cgcPrecio) VALUES('p002','Lavadora','Lg centrifuga y lava ',906.80)")
        db.execSQL("INSERT INTO $TABLA_PRODUCTO(cgcImagen,cgcNombre,cgcDescripcion,cgcPrecio) VALUES('p003','Refrigeradora','Samsung es mas fria que tu ex',78.80)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        db.execSQL(SQL_DELETE_ENTRIES_PRODUCTO)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}