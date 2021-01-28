package com.example.dogs.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DogsApiService {

    private val BASE_URL = "https://raw.githubusercontent.com"

    private val api =  Retrofit.Builder()
//   este objeto llamado por retrofit nos permite llamar al endpoint
//   y obtener la informacion
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())// como la informacion esta en JSON, Factory permite convertirla al mismo formato del modelo
//            mediante GSON podemos hacer eso y no tenemos que convertirlo manualmente
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//convierte la lista de nuestros objetos en un observable SINGLE
        .build()
        .create(DogsApi::class.java)

    fun getDogs(): Single<List<DogBreed>>{
        return api.getDogs()
    }
}