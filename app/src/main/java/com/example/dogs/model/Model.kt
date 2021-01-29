package com.example.dogs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity //Room tratara a esta data class como una entidad que puede ponerse en la base de datos
data class DogBreed(
    @ColumnInfo(name = "breed_id") //renombrar nombre de la columna en la BD
    @SerializedName("id") //sacar el verdadero nombre del campo en el json del api
    //si el nombre del parametro es igual que el del api, no es necesario el Serialized
    val breedId: String?,

    @ColumnInfo(name = "dog_name")
    @SerializedName("name")
    val dogBreed: String?,

    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    val lifeSpan: String?,

    @ColumnInfo(name = "breedGroup")
    @SerializedName("breed_group")
    val breedGroup: String?,

    @ColumnInfo(name = "bred_for")
    @SerializedName("bred_for")
    val bredFor: String?,


    @SerializedName("temperament")
    val temperament: String?,

    @ColumnInfo(name = "dog_url")
    @SerializedName("url")
    val imageUrl: String?
){
    //cada vez que esta entidad se ponga dentro de la base de datos de la libreria room
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}