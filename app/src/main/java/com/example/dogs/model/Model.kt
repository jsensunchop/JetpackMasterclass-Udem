package com.example.dogs.model

import com.google.gson.annotations.SerializedName

data class DogBreed(
    @SerializedName("id") //sacar el verdadero nombre del campo en el json del api
    //si el nombre del parametro es igual que el del api, no es necesario el Serialized
    val breedId: String?,
    @SerializedName("name")
    val dogBreed: String?,
    @SerializedName("life_span")
    val lifeSpan: String?,
    @SerializedName("breed_group")
    val breedGroup: String?,
    @SerializedName("bred_for")
    val bredFor: String?,
    @SerializedName("temperament")
    val temperament: String?,
    @SerializedName("url")
    val imageUrl: String?

)