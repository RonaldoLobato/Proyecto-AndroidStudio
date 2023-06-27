package com.example.proyecto_inti_electrodomesticos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.proyecto_inti_electrodomesticos.adaptersLobato.ProductoAdapter

import com.example.proyecto_inti_electrodomesticos.coreLobato.ProductoLista
import com.example.proyecto_inti_electrodomesticos.serviceLobato.ProductoListaService

class MisProductosActivity : AppCompatActivity() {

    var productoListaService = ProductoListaService(this)
    lateinit var listaProductoLocal:MutableMap<Int, ProductoLista>
    lateinit var recycler: RecyclerView
    lateinit var pullToRefresh: SwipeRefreshLayout

    private lateinit var nombreTextView: TextView
    private lateinit var descripcionTextView: TextView

    private val itemList: MutableList<ProductoLista> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_productos)

        descripcionTextView = findViewById(R.id.nombre_item_lista)
        nombreTextView = findViewById(R.id.descripcion_item_lista)

        pullToRefresh = findViewById(R.id.swipeRefreshLayout)
        pullToRefresh.setOnRefreshListener {
            pullToRefresh.isRefreshing= false
            updateLista()
        }
        listaProductoLocal=productoListaService.getProductos()
        recycler = findViewById(R.id.mis_productos_recycler_view)
        val adapter = ProductoAdapter(listaProductoLocal)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter =adapter

    }
    fun updateLista(){
        listaProductoLocal = productoListaService.getProductos()
        val nuevoAdapter = ProductoAdapter(listaProductoLocal)
        recycler.adapter = nuevoAdapter
    }


}