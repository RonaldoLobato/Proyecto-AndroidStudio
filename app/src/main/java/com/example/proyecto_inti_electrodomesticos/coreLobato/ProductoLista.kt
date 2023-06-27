package com.example.proyecto_inti_electrodomesticos.coreLobato

class ProductoLista {
    var cgcId:Long=0
    var cgcImagen: String=""
    var cgcNombre: String=""
    var cgcDescripcion: String=""
    var cgcPrecio: Double=0.0

    constructor(
        cgcId:Long,
        cgcImagen: String,
        cgcNombre: String,
        cgcDescripcion: String,
        cgcPrecio : Double
    ){
        this.cgcId =cgcId
        this.cgcImagen = cgcImagen
        this.cgcNombre = cgcNombre
        this.cgcDescripcion = cgcDescripcion
        this.cgcPrecio = cgcPrecio
    }
    constructor(
        cgcImagen: String,
        cgcNombre: String,
        cgcDescripcion: String,
        cgcPrecio:Double
    ){
        this.cgcImagen = cgcImagen
        this.cgcNombre = cgcNombre
        this.cgcDescripcion = cgcDescripcion
        this.cgcPrecio = cgcPrecio
    }
    constructor(nombre: String, descripcion: String, precio: Double) {
        this.cgcImagen = "P001"
        this.cgcNombre = "Jhon Del Rio Contreras"
        this.cgcDescripcion = "12345678"
        this.cgcPrecio = 90.70
    }
}