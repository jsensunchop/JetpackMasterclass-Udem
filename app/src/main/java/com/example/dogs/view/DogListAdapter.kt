package com.example.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.R
import com.example.dogs.model.DogBreed
import com.example.dogs.util.getProgressDrawable
import com.example.dogs.util.loadImage
import kotlinx.android.synthetic.main.item_dog.view.*

//standart adapter
class DogListAdapter (val dogsList: ArrayList<DogBreed>): RecyclerView.Adapter<DogListAdapter.DogViewHolder>(){

    //actualizar la lista en caso de que halla algun cambio
    fun updateDogList(newDogsList: List<DogBreed>){
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged() //notificar si hubo algun cambio en el dataset, resetear lo que sea necesario y que el sistema re-cree la lista
    }
    //creacion del dogview holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount() = dogsList.size

    //los elementos de la lista se van a transformar en vistas y vamos a adjuntar a esas vistas la informacion del dog list
    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.name.text = dogsList[position].dogBreed
        holder.view.lifespan.text = dogsList[position].lifeSpan
        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
        }
        //esta linea de codigo se enlaza con el Util.kt que usa Glide
        holder.view.imageView.loadImage(dogsList[position].imageUrl, getProgressDrawable(holder.view.imageView.context))// esto cargara automaticamente todas las imagenes de la data (perros)
//        en nuestra lista
    }

    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}