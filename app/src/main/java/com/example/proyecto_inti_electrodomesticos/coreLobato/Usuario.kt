package com.example.proyecto_inti_electrodomesticos.coreLobato

class Usuario {
    var user_id: Long = 0
    var user_nombre: String
    var user_apellido: String
    var user_correo: String
    var user_contraseña: String

    constructor(
        user_id: Long,
        user_nombre: String,
        user_apellido: String,
        user_correo: String,
        user_contraseña: String
    ){
        this.user_id = user_id
        this.user_nombre = user_nombre
        this.user_apellido = user_apellido
        this.user_correo = user_correo
        this.user_contraseña = user_contraseña
    }constructor(user_nombre: String, user_apellido: String, user_correo: String, user_contraseña: String){
        this.user_nombre = user_nombre
        this.user_apellido = user_apellido
        this.user_correo = user_correo
        this.user_contraseña = user_contraseña
    }

}