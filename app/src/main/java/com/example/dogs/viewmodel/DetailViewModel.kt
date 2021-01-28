package com.example.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.model.DogBreed

class DetailViewModel: ViewModel() {


    //variables tipo livedata

    //va a proveer la informacion para la lista actual de dogs que se va a traer del origen de la data
    val dogLiveData = MutableLiveData<DogBreed>()

    //TBD
    fun fetch(){
        val dog1 = DogBreed("1", "Corgi", "15 years", "breedGroup", "bredFor", "temperament", "")



        dogLiveData.value = dog1

    }
}