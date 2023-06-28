package com.example.proyecto_inti_electrodomesticos

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.proyecto_inti_electrodomesticos.coreLobato.ProductoLista
import com.example.proyecto_inti_electrodomesticos.coreLobato.Usuario
import com.example.proyecto_inti_electrodomesticos.serviceLobato.ProductoListaService
import com.example.proyecto_inti_electrodomesticos.serviceLobato.UsuarioService

class MenuProductoActivity : AppCompatActivity() {
        lateinit var editNombre :EditText
        lateinit var  editDescripcion :EditText
        lateinit var  editPrecio :EditText
        lateinit var spinProducto: Spinner
        lateinit var btnAgregar:Button
        lateinit var btnListado:Button
        lateinit var nombreImagen:String

    var productoListaService = ProductoListaService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_producto)


        editNombre =  findViewById(R.id.txtNombreProducto)
        editDescripcion= findViewById(R.id.txtDescripcionProducto)
        editPrecio =  findViewById(R.id.txtPrecioProducto)
        spinProducto =findViewById(R.id.productoSpinner)
        btnAgregar=findViewById(R.id.btnAgreagrProducto)
        nombreImagen="vacio"
        btnListado=findViewById(R.id.btnListarProducto)


        btnListado.setOnClickListener {
            openListadoProducto()
        }


        val electro_options = resources.getStringArray(R.array.electro_options)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, electro_options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinProducto.adapter=adapter

        spinProducto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedTheme = parent?.getItemAtPosition(position).toString()

                when (selectedTheme) {

                    "Licuadora" -> nombreImagen="p001"
                    "Lavadora" -> nombreImagen="p002"
                    "Refrigeradora" -> nombreImagen="p003"
                    "TV Smart" -> nombreImagen="p004"
                    "Batidora" -> nombreImagen="p005"
                    "Aspiradora" -> nombreImagen="p006"
                    "Olla Arrocera" -> nombreImagen="p007"
                    "Equipo de Sonido" -> nombreImagen="p008"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se seleccionó ningún tema
            }
        }

        btnAgregar.setOnClickListener { view ->
            val nombre = editNombre.text.toString()
            val descripcion = editDescripcion.text.toString()
            val precio = editPrecio.text.toString()


            if (validacionesCampos(nombreImagen, nombre, descripcion,precio)) {
                val  precio1=precio.toDouble()
                val productolista = ProductoLista(nombreImagen,nombre,descripcion,precio1)
                val builder: AlertDialog.Builder? = view?.let {
                    AlertDialog.Builder(this)
                }
                builder?.setMessage("Campos Correctos")
                    ?.setTitle("Desea Agregar el Registro")
                builder?.setPositiveButton("SI", DialogInterface.OnClickListener { dialog, id ->
                    editNombre.setText("")
                    editDescripcion.setText("")
                    editPrecio.setText("")
                    registrar(productolista)
                    Toast.makeText(this, "Registrado Exitoso", Toast.LENGTH_LONG).show()
                })
                builder?.setNegativeButton("NO ",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }
            else {
                Toast.makeText(this, "El Registro no se puede Realizar", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun validacionesCampos(imagen: String,nombre: String, descripcion: String, precio: String): Boolean {
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingresa nombre de Producto", Toast.LENGTH_LONG).show()
            return false
        }
        if (descripcion.isEmpty()) {
            Toast.makeText(this, "La descripcion esta vacia", Toast.LENGTH_LONG).show()
            return false
        }

        if (precio.isEmpty() ) {
            Toast.makeText(this, "El precio es incorrecto", Toast.LENGTH_LONG).show()
            return false
        }
        if (imagen.isEmpty()) {
            Toast.makeText(this, "Seleccione una imagen", Toast.LENGTH_LONG).show()
            return false
        }


        return true
    }

    private fun openListadoProducto(){
        val intent = Intent(this, MisProductosActivity::class.java)
        startActivity(intent)
    }
    fun registrar(v: ProductoLista) {
        productoListaService.insertarNuevoProducto(v)
    }


}

