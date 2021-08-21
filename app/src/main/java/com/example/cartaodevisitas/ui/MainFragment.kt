package com.example.cartaodevisitas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.cartaodevisitas.R
import com.example.cartaodevisitas.adapter.BusinessCardAdapter
import com.example.cartaodevisitas.databinding.FragmentMainBinding
import com.example.cartaodevisitas.model.BusinessCard
import com.example.cartaodevisitas.util.Image
import com.example.cartaodevisitas.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by inject()
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private lateinit var recyclerAdapter: BusinessCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setRecycler()
        insertListeners()
        return binding.root
    }

    private fun insertListeners(){
        binding.fabMainfragment.setOnClickListener{
            Navigation
                .findNavController(binding.root)
                .navigate(R.id.action_mainFragment_to_addBusinessCardFragment)
        }
        recyclerAdapter.onClickShare = { card ->
            lifecycleScope.launch(Dispatchers.IO){Image.share(requireContext(),card)}
        }
    }

    private fun setRecycler(){
        recyclerAdapter = BusinessCardAdapter(onDelete())
        binding.recycler.apply {
            setHasFixedSize(true)
            this.adapter = recyclerAdapter
        }
        setObservers()
    }

    private fun onDelete() : (BusinessCard) -> Unit
    {
        return {
            businessCard: BusinessCard ->
            viewModel.delete(businessCard)
        }
    }

    private fun setObservers(){
        viewModel.allBusinessCards.observe( viewLifecycleOwner , {
            recyclerAdapter.updateAdapter(ArrayList(it))
        })
    }
}