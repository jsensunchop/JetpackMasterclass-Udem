package com.example.dogs.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

//esta dogdatabase basicamente es el objeto o la clase que va a accesar a la base de datos por nosotros
@Database(entities = arrayOf(DogBreed::class), version = 1)
abstract class DogDatabase: RoomDatabase() {
    //un singleton es un patron de desarrollo en el cual solo tenemos una instancia de la base de datos
    //o una instancia de un objeto de una clase cuando sea o cuantos tantos objetos traten de accesarla, solo habra una isntancia de la clase
    //IMPLEMENTACION DE SINGLETON
    abstract fun dogDao(): DogDao

    companion object{
        @Volatile private var instance: DogDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {//cuando sea que se invoque el dog database singleton con un context
            //se obtendra una instancia si ya fue creada y si no se sincronizara (si multiples hilos estan tratando de accesar este bloque de codigo
            // entonces solo uno podra accesarlo)
            instance ?: buildDatabase(context).also {
                //entonces retornara la instancia o se construira la base de datos para asignarla a la interface y luego retornarla
                instance = it
            }
        }

        //la forma como se construye la base de datos basado en el contexto utilizado con Room
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DogDatabase::class.java,
            "dogdatabase"
        ).build()
    }
}