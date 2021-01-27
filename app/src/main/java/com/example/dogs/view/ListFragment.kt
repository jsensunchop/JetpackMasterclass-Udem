package com.example.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.dogs.R
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
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
    }

}