package com.example.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogs.R
import com.example.dogs.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogListAdapter = DogListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        onViewCreated se usa cuando la vista ha sido creada y cuando se quiere enlazar algunas
        acciones a nuestros elementos, se necesita asegurarse que los elementos estan creados
        antes de accesar a ellos para evitar un null pointer exception
         */
        /*
        buttonDetails.setOnClickListener {
            val action = ListFragmentDirections.actionDetailFragment()
            //using arguments
            action.dogUuid = 5
            Navigation.findNavController(it).navigate(action)
        }
         */
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java) //viewmodel instanciado en el list fragment
        viewModel.refresh() // actualizar lo que hay en el listviewmodel

        //ahora se instanciara la dog list (recycle view)

        dogsList.apply {
            layoutManager = LinearLayoutManager(context)//esto le permite al sistema ordenar los elementos sequencialmente en forma de lista
            //attach the adapter
            adapter = dogListAdapter
        }

        refreshLayout.setOnRefreshListener { //configurando funcionalidades del refresh
            dogsList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }
     //esta funcion sirve para usar las variables que se crearon en el ListViewModel para actualizar el layout basado en los valores que se tomen
   fun observeViewModel(){
         viewModel.dogs.observe(this, Observer { dogs -> //dogs es un mutablelivedata OJO
             dogs?.let { //verificacion de nulidad
                 dogsList.visibility = View.VISIBLE
                 dogListAdapter.updateDogList(dogs)
             }
         })

         //En la vista, si existe algun eror esto lo muestra
         viewModel.dogsLoadError.observe(this, Observer { isError ->
             isError?.let {
                 listError.visibility = if(it) View.VISIBLE else View.GONE
             }
         })

         // en la vista si se esta cargando, muestra el spinner, sino desaparece
         viewModel.loading.observe(this, Observer { isLoading ->
             isLoading?.let {
                 loadingView.visibility = if(it) View.VISIBLE else View.GONE
                 //mostrar las otras vistas dependiendo del spinner
                 if (it) {
                     listError.visibility = View.GONE //ocultar vista (solo mostrar spinner)
                     dogsList.visibility = View.GONE //ocultar vista (solo mostrar spinner)
                 }
             }

         })
     }
}