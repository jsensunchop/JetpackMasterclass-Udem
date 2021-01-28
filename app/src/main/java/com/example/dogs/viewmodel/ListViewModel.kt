package com.example.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.model.DogBreed
import com.example.dogs.model.DogsApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    //-----------------------------------------------REMOTE API-------------------------------------------------------------------------
    private val dogService = DogsApiService()
    private val disposable = CompositeDisposable() //allow us to retrieve or observe the observable (single) that the API gives us and not having
    //to worry about it
    //----------------------------------------------------------------------------------------------------------------------------------------
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
       fetchFromRemote()
    }
    private fun fetchFromRemote(){ //traer data del api
        loading.value = true
        disposable.add(
            dogService.getDogs()//set the call to ve on a separate thread rather than a api thread
//        the reason is that we dont want this call to be made on the API on the main thread beacuse
//        that will block our main application while the call is retrieved
                .subscribeOn(Schedulers.newThread()) //get in a background thread
                .observeOn(AndroidSchedulers.mainThread())//response on a main threat
                .subscribeWith(object: DisposableSingleObserver<List<DogBreed>>(){
                    override fun onSuccess(dogList: List<DogBreed>) { //lista de dog breeds
                        dogs.value = dogList
                        dogsLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) { // mensaje de error
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear() // limpiar loq ue sea necesario
    }

}