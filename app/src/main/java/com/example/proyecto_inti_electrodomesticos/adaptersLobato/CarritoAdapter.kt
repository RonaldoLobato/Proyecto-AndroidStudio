package com.example.proyecto_inti_electrodomesticos.adaptersLobato

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_inti_electrodomesticos.R
import com.example.proyecto_inti_electrodomesticos.coreLobato.ProductoLista


class CarritoAdapter(private val listaCarrito: List<ProductoLista>) :
    RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val producto = listaCarrito[position]

        holder.nombreTextView.text = producto.cgcNombre
        holder.descripcionTextView.text = producto.cgcDescripcion
    }

    override fun getItemCount(): Int {
        return listaCarrito.size
    }

    inner class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombre_carrito)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcion_carrito)
        val btnActualizar: Button = itemView.findViewById(R.id.btnActualizar)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
    }
}

