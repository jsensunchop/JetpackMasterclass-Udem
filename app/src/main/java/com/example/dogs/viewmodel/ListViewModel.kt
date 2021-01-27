package com.example.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.model.DogBreed

class ListViewModel : ViewModel() {

    //variables tipo livedata

    //va a proveer la informacion para la lista actual de dogs que se va a traer del origen de la data
    val dogs = MutableLiveData<List<DogBreed>>()
    //notificara si esta escuchando al view model y notificara si hay algun error pero no sera especifico
    //especificara si hay algun error al adquirir la data
    val dogsLoadError = MutableLiveData<Boolean>()
    // le dira al componente que este escuchando que el sistema se esta cargando, entonces la data no ha llegado pero no hay ningun error
    //mostrara un spinner al usuario
    val loading = MutableLiveData<Boolean>()

    //TBD
    fun refresh(){
        val dog1 = DogBreed("1", "Corgi", "15 years", "breedGroup", "bredFor", "temperament", "")
        val dog2 = DogBreed("2", "Labrador", "15 years", "breedGroup", "bredFor", "temperament", "")
        val dog3 = DogBreed("3", "Rotwailer", "15 years", "breedGroup", "bredFor", "temperament", "")
        val dogList = arrayListOf<DogBreed>(dog1, dog2, dog3)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }

}