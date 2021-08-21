package com.example.cartaodevisitas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.cartaodevisitas.R
import com.example.cartaodevisitas.databinding.FragmentAddBusinessCardBinding
import com.example.cartaodevisitas.model.BusinessCard
import com.example.cartaodevisitas.viewmodel.AddBusinessCardViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject


class AddBusinessCardFragment : Fragment() {

    private val binding by lazy { FragmentAddBusinessCardBinding.inflate(layoutInflater) }
    private val viewmodel : AddBusinessCardViewModel by inject()

    override fun onResume() {
        super.onResume()

        binding.colorPalette.setOnColorSelectedListener {
            clr -> viewmodel.setColor(clr)
        }
        //Cor Padrão
        binding.colorPalette.setSelectedColor(ContextCompat.getColor(requireContext(),R.color.white))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        insertListeners()
        return binding.root
    }

    private fun insertListeners(){
        binding.btClose.setOnClickListener{
            Navigation.findNavController(binding.root).popBackStack()
        }

        binding.btConfirmar.setOnClickListener{
            if (validarCampos()){
                salvarCartao()
                Navigation.findNavController(binding.root).popBackStack()
            }
        }
    }

    private fun salvarCartao ()
    {
        viewmodel.insert(
            BusinessCard(
                binding.editNome.text.toString(),
                binding.editTelefone.text.toString(),
                binding.editEmail.text.toString(),
                binding.editEmpresa.text.toString(),
                viewmodel.getColor()
            )
        )
    }

    private fun validarCampos (): Boolean {

        when {
            binding.editNome.text.isNullOrBlank() and binding.editNome.text.isNullOrEmpty() -> {
                snack("o campo Nome está em branco")
            }
            binding.editTelefone.text.isNullOrBlank() and binding.editTelefone.text.isNullOrEmpty() -> {
                snack("o campo Telefone está em branco")
            }
            binding.editEmail.text.isNullOrBlank() and binding.editEmail.text.isNullOrEmpty() -> {
                snack("o campo E-Mail está em branco")
            }
            binding.editEmpresa.text.isNullOrBlank() and binding.editEmpresa.text.isNullOrEmpty() -> {
                snack("o campo Empresa está em branco")
            }
            else -> {
                return true
            }
        }
        return false
    }

    private fun snack (message:String, duration:Int = Snackbar.LENGTH_LONG){
        Snackbar.make(binding.root,message,duration).show()
    }
}