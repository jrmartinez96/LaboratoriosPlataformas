package com.josemartinez.proyectofinal


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.josemartinez.proyectofinal.databinding.FragmentCrearCuentaBinding

class CrearCuentaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentCrearCuentaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_crear_cuenta, container, false)

        return binding.root
    }


}
