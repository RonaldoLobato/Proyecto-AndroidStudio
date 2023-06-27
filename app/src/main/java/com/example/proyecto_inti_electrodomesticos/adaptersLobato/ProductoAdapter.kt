package com.example.proyecto_inti_electrodomesticos.adaptersLobato

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.proyecto_inti_electrodomesticos.R
import com.example.proyecto_inti_electrodomesticos.coreLobato.ProductoLista


class ProductoAdapter(private val data:MutableMap<Int, ProductoLista>?):
    RecyclerView.Adapter<ProductoAdapter.ProductoHolder>()
{
    private val selectedItems: MutableList<ProductoLista> = mutableListOf()

    inner class ProductoHolder(val v: View): RecyclerView.ViewHolder(v){
        var imagenLista: ImageView
        var nombreLista : TextView
        var descripcionLista : TextView
        var precioLista :TextView

        init{
            nombreLista =v.findViewById(R.id.nombre_item_lista)
            imagenLista =v.findViewById(R.id.imagen_item_lista)
            descripcionLista =v.findViewById(R.id.descripcion_item_lista)
            precioLista =v.findViewById(R.id.precio_item_lista)
        }
        fun bindData(data: ProductoLista)= with(v){
            nombreLista.text="Nombre: ${data.cgcNombre}"
            val resourceId = resources.getIdentifier("${data.cgcImagen}", "drawable","com.example.proyecto_inti_electrodomesticos")
            imagenLista.setImageResource(resourceId)
            descripcionLista.text="Descripcion: ${data.cgcDescripcion}"
            precioLista.text="Precio: S/.${data.cgcPrecio.toString()}"
        }
    }
    override fun onCreateViewHolder(parent:ViewGroup,viewType: Int): ProductoHolder{
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lista_productos,parent,false)
        return ProductoHolder(itemView)
    }
    override fun getItemCount(): Int {
        return data?.size ?:0
    }

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {
        data?.let{
            it.get(position+1)?.let{it1->holder.bindData(it1)}
        }
    }
    fun getSelectedItems(): List<ProductoLista> {
        return selectedItems.toList()
    }
}