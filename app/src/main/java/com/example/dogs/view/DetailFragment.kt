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
import com.example.dogs.viewmodel.DetailViewModel
import com.example.dogs.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*


class DetailFragment : Fragment() {

    private var dogUuid = 0

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //passing the argumentes
        //commit (new lecture)

        /*
        buttonList.setOnClickListener {
            val action = DetailFragmentDirections.actionListFragment()
            Navigation.findNavController(it).navigate(action)
        }

         */


        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java) //viewmodel instanciado en el list fragment
        viewModel.fetch() // actualizar lo que hay en el listviewmodel

        //ahora se instanciara la dog list (recycle view)
        arguments?.let { //solo se correra la pieza de codigo sino es nulo
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid //retrieveing arguments that are passed
            //textView2.text = dogUuid.toString()
        }
        observeViewModel()
    }
    //esta funcion sirve para usar las variables que se crearon en el ListViewModel para actualizar el layout basado en los valores que se tomen
    fun observeViewModel(){
        viewModel.dogLiveData.observe(this, Observer { dogProfile ->
            dogProfile?.let { //verificacion de nulidad
                dogName.text = dogProfile.dogBreed
                dogPurpose.text = dogProfile.bredFor
                dogTemperament.text = dogProfile.temperament
                dogLifespan.text = dogProfile.lifeSpan
            }
        })
    }
}