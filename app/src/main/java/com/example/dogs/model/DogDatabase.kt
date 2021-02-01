package com.example.dogs.model

import androidx.room.Database

//esta dogdatabase basicamente es el objeto o la clase que va a accesar a la base de datos por nosotros
@Database(entities = arrayOf(DogBreed::class), version = 1)
abstract class DogDatabase {
    //un singleton es un patron de desarrollo en el cual solo tenemos una instancia de la base de datos
    //o una instancia de un objeto de una clase cuando sea o cuantos tantos objetos traten de accesarla, solo habra una isntancia de la clase
    //IMPLEMENTACION DE SINGLETON
}