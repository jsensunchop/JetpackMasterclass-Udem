package com.example.dogs.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.sql.RowId

@Dao //Data Access Object, interfaz mediante la cual accederemos a objetos en la base de datos
//interfaz similar a la que se tiene en DogsApi con RETROFIT
interface DogDao {//esta interfaz va a definir cual tipo de funciones podemos realizar en nuestra base de datos
    @Insert//consulta // insertar data a la data base, insertara parametros del tipo DogBreed
    suspend fun insertAll(vararg dogs: DogBreed):List<Long>
    //vararg:valuable arguments or multiple arguments, en ese caso de tipo DogBreed, retornara una lista(Long)
    //se utiliza suspend para ponerlo en un thread background para que android no bloquee funciones, entonces se
    //correra cuandosea que haya poder de procesamiento disponible para correr la funcion
    @Query("SELECT * FROM dogbreed")
    suspend fun getAllDogs(): List<DogBreed>

    @Query("SELECT * FROM dogbreed WHERE uuid= :dogId")
    suspend fun getDog(dogId: Int): DogBreed

    @Query("DELETE FROM dogbreed")
    suspend fun deleteAllDogs()
}